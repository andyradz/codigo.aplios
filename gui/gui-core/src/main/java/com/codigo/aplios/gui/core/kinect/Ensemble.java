package com.codigo.aplios.gui.core.kinect;

import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

class Ensemble {

    private static final Random random = new Random();

    private static final Color[] colors = GradientImage.createColorArray();

    private static final int MAX_ATOMS = Ensemble.colors.length * 10;

    private static final int MIN_RADIUS = 20;

    private static final Image[] images = GradientImage.createImageArray(Ensemble.MIN_RADIUS);

    private static final int VSCALE = 5;

    private int left = 0;

    private int top = 0;

    private int right = 500;

    private int bottom = 500;

    private int iterations = 0;

    private int collisions = 0;

    private final ArrayList<Particle> atoms;

    private final Ellipse2D ellipse = new Ellipse2D.Double();

    private Vector p1 = new Vector();									// position

    private Vector p2 = new Vector();

    private Vector v1 = new Vector();									// velocity

    private Vector v2 = new Vector();

    private Vector n = new Vector();									// normal vector

    private Vector un = new Vector();									// unit normal

    private Vector ut = new Vector();									// unit tangent

    /**
     * Construct a container of atoms.
     */
    public Ensemble() {
        this.atoms = new ArrayList<>(Ensemble.MAX_ATOMS);
    }

    /**
     * Set the container boundaries.
     */
    public void setWalls(final int l, final int t, final int r, final int b) {
        this.left = l;
        this.top = t;
        this.right = r;
        this.bottom = b;
        this.resetCollisionRate();
    }

    /**
     * Advance each atom.
     */
    public void iterate(final Particle atom1) {
        this.iterations++;
        this.p1 = atom1.getPosition(this.p1);
        this.v1 = atom1.getVelocity(this.v1);
        this.p1.add(this.v1.scale(Ensemble.VSCALE));
        atom1.setPosition(this.p1);
        for (int i = this.atoms.indexOf(atom1) + 1; i < this.atoms.size(); i++) {
            final Particle atom2 = this.atoms.get(i);
            this.collideAtoms(atom1, atom2);
        }
        this.collideWalls(atom1);
    }

    /**
     * Get an atom's shape.
     */
    public Shape getShape(final Particle atom) {
        final double radius = atom.getR();
        final double diameter = 2 * radius;
        this.p1 = atom.getPosition(this.p1);
        this.ellipse.setFrame(this.p1.x - radius, this.p1.y - radius, diameter, diameter);
        return this.ellipse;
    }

    // Check for collision between atom1 & atom2
    private void collideAtoms(final Particle a1, final Particle a2) {
        final double radius = a1.getR() + a2.getR();
        this.p1 = a1.getPosition(this.p1);
        this.p2 = a2.getPosition(this.p2);
        this.n = this.n.set(this.p1)
                .subtract(this.p2);
        if (this.n.norm() < radius) {
            // Move to start of collision
            final double dr = (radius - this.n.norm()) / 2;
            this.un = this.un.set(this.n)
                    .unitVector();
            this.p1.add(this.un.scale(dr));
            this.un = this.un.set(this.n)
                    .unitVector();
            this.p2.add(this.un.scale(-dr));
            a1.setPosition(this.p1);
            a2.setPosition(this.p2);
            // Find normal and tangential components of v1/v2
            this.n = this.n.set(this.p1)
                    .subtract(this.p2);
            this.un = this.un.set(this.n)
                    .unitVector();
            this.ut = this.ut.set(-this.un.y, this.un.x);
            this.v1 = a1.getVelocity(this.v1);
            this.v2 = a2.getVelocity(this.v2);
            final double v1n = this.un.dot(this.v1);
            final double v1t = this.ut.dot(this.v1);
            final double v2n = this.un.dot(this.v2);
            final double v2t = this.ut.dot(this.v2);
            // Calculate new v1/v2 in normal direction
            final double m1 = a1.getM();
            final double m2 = a2.getM();
            final double v1nNew = ((v1n * (m1 - m2)) + (2d * m2 * v2n)) / (m1 + m2);
            final double v2nNew = ((v2n * (m2 - m1)) + (2d * m1 * v1n)) / (m1 + m2);
            // Update velocities with sum of normal & tangential components
            this.v1 = this.v1.set(this.un)
                    .scale(v1nNew);
            this.v2 = this.v2.set(this.ut)
                    .scale(v1t);
            a1.setVelocity(this.v1.add(this.v2));
            this.v1 = this.v1.set(this.un)
                    .scale(v2nNew);
            this.v2 = this.v2.set(this.ut)
                    .scale(v2t);
            a2.setVelocity(this.v1.add(this.v2));
        }
    }

    // Check for collision with wall
    private void collideWalls(final Particle atom) {
        final double radius = atom.getR();
        this.p1 = atom.getPosition(this.p1);
        this.v1 = atom.getVelocity(this.v1);
        if (this.p1.x < (this.left + radius)) {
            this.p1.x = this.left + radius;
            this.v1.x = -this.v1.x;
            this.collisions++;
        }
        if (this.p1.y < (this.top + radius)) {
            this.p1.y = this.top + radius;
            this.v1.y = -this.v1.y;
            this.collisions++;
        }
        if (this.p1.x > (this.right - radius)) {
            this.p1.x = this.right - radius;
            this.v1.x = -this.v1.x;
            this.collisions++;
        }
        if (this.p1.y > (this.bottom - radius)) {
            this.p1.y = this.bottom - radius;
            this.v1.y = -this.v1.y;
            this.collisions++;
        }
        atom.setPosition(this.p1);
        atom.setVelocity(this.v1);
    }

    /**
     * Add atoms to half of max.
     */
    public void initAtoms() {
        this.atoms.clear();
        for (int i = 0; i < (Ensemble.MAX_ATOMS / Ensemble.colors.length / 2); i++)
            this.addAtoms();
        this.resetCollisionRate();
    }

    /**
     * Add one atom of each color.
     */
    public void addAtoms() {
        if (this.atoms.size() >= Ensemble.MAX_ATOMS)
            return;
        for (int i = 0; (i < Ensemble.colors.length) && (this.atoms.size() <= Ensemble.MAX_ATOMS); i++) {
            final Particle atom = new Particle();
            atom.setPosition((Ensemble.random.nextDouble() * (this.right - this.left)) + this.left, (Ensemble.random.nextDouble() * (this.bottom - this.top)) + this.top);
            final int id = i % Ensemble.colors.length;
            atom.setColor(Ensemble.colors[id]);
            atom.setImage(Ensemble.images[id]);
            atom.setR(id + Ensemble.MIN_RADIUS);
            double vx = Ensemble.random.nextDouble();
            if (Ensemble.random.nextBoolean())
                vx = -vx;
            double vy = Ensemble.random.nextDouble();
            if (Ensemble.random.nextBoolean())
                vy = -vy;
            atom.setVelocity(vx, vy);
            this.atoms.add(atom);
        }
    }

    /**
     * Remove one atom of each color.
     */
    public void removeAtoms() {
        for (int i = 0; (i < Ensemble.colors.length) && (this.atoms.size() > 0); i++)
            this.atoms.remove(0);
    }

    /**
     * Get the ratio of wall collisions to iterations.
     */
    public double getCollisionRate() {
        if (this.iterations == 0)
            return 0;
        final double rate = (4d * this.collisions) / this.iterations;
        return Math.min(rate, 1d);
    }

    /**
     * Get the list of atoms.
     */
    public ArrayList<Particle> getAtoms() {
        return this.atoms;
    }

    /**
     * Reset the collision counter; called when container resized.
     */
    private void resetCollisionRate() {
        this.iterations = 0;
        this.collisions = 0;
    }

}
