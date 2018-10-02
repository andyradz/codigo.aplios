package com.codigo.aplios.gui.core.kinect;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;
import javax.swing.JPanel;

class DisplayPanel
        extends JPanel {

    private static final long serialVersionUID = 8767596966664532390L;

    private static final int ROWS = 20;

    private static final int COLS = DisplayPanel.ROWS;

    private static final Random random = new Random();

    private final Ensemble model;

    private final Rectangle2D.Double r = new Rectangle2D.Double();

    private BufferedImage image;

    private TexturePaint paint;

    private boolean useGradient = true;

    private long paintTime;

    private final int[] iArray = {0, 0, 0, 255};

    /**
     * Construct a display panel.
     */
    public DisplayPanel(final Ensemble model) {
        this.model = model;
        this.setPreferredSize(new Dimension(700,
                600));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final Component c = (Component)e.getSource();
                model.setWalls(DisplayPanel.COLS, DisplayPanel.ROWS, c.getWidth() - DisplayPanel.COLS, c.getHeight() - DisplayPanel.ROWS);
            }

        });
    }

    /**
     * Paint the display.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        if (this.image == null)
            this.initImage();
        if (this.model.getAtoms()
                .isEmpty())
            this.model.initAtoms();
        final long start = System.currentTimeMillis();
        final WritableRaster raster = this.image.getRaster();
        for (int row = 0; row < DisplayPanel.ROWS; row++)
            for (int col = 0; col < DisplayPanel.COLS; col++) {
                final int v = DisplayPanel.random.nextInt(256);
                this.iArray[0] = 255;
                this.iArray[1] = v;
                this.iArray[2] = 0;
                raster.setPixel(col, row, this.iArray);
            }
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        final Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setPaint(this.paint);
        this.r.setRect(0, 0, this.getWidth(), DisplayPanel.ROWS);
        g2D.fill(this.r);
        this.r.setRect(0, this.getHeight() - DisplayPanel.ROWS, this.getWidth(), DisplayPanel.ROWS);
        g2D.fill(this.r);
        this.r.setRect(0, 0, DisplayPanel.COLS, this.getHeight());
        g2D.fill(this.r);
        this.r.setRect(this.getWidth() - DisplayPanel.COLS, 0, DisplayPanel.COLS, this.getHeight());
        g2D.fill(this.r);
        for (final Particle atom : this.model.getAtoms()) {
            this.model.iterate(atom);
            final Shape shape = this.model.getShape(atom);
            if (this.useGradient) {
                final Image atomImage = atom.getImage();
                final int x = (int)shape.getBounds2D()
                        .getX();
                final int y = (int)shape.getBounds2D()
                        .getY();
                g2D.drawImage(atomImage, x, y, null);
            } else {
                g2D.setPaint(atom.getColor());
                g2D.fill(shape);
            }
        }
        this.paintTime = System.currentTimeMillis() - start;
    }

    /**
     * Initialize offscreen buffer and paint.
     */
    private void initImage() {
        this.image = (BufferedImage)this.createImage(DisplayPanel.COLS, DisplayPanel.ROWS);
        this.r.setRect(0, 0, DisplayPanel.ROWS, DisplayPanel.COLS);
        this.paint = new TexturePaint(this.image,
                this.r);
    }

    /**
     * Return time taken in paintComponent.
     */
    public long getPaintTime() {
        return this.paintTime;
    }

    /**
     * Specify color (true) or gray (false).
     */
    public void useGradient(final boolean state) {
        this.useGradient = state;
    }

}
