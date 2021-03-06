package com.codigo.aplios.gui.core.brushes;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Gradient {

    // -----------------------------------------------------------------------------------------------------------------
    public static BufferedImage getThreeWayGradient(final int size, final Color primaryLeft, final Color primaryRight,
            final Color shadeColor) {

        final BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        final Graphics2D g = image.createGraphics();
        final GradientPaint primary = new GradientPaint(0f, 0f, primaryLeft, size, 0f, primaryRight);
        final int rC = shadeColor.getRed();
        final int gC = shadeColor.getGreen();
        final int bC = shadeColor.getBlue();
        final GradientPaint shade = new GradientPaint(0f, 0f, new Color(rC, gC, bC, 0), 0f, size, shadeColor);
        g.setPaint(primary);
        g.fillRect(0, 0, size, size);
        g.setPaint(shade);
        g.fillRect(0, 0, size, size);

        g.dispose();
        return image;
    }

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * Presumed to have a layout that shows multiple components.
     */
    public static void addGradient(final JPanel p, final int s, final Color pL, final Color pR, final Color sh) {

        final JLabel l = new JLabel(new ImageIcon(Gradient.getThreeWayGradient(s, pL, pR, sh)));
        p.add(l);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static void main(final String[] args) {

        final Runnable r = () -> {

            final JPanel gui = new JPanel(new GridLayout(2, 4, 1, 1));
            Gradient.addGradient(gui, 100, Color.YELLOW, Color.RED, Color.GREEN);
            Gradient.addGradient(gui, 100, Color.GREEN, Color.YELLOW, Color.RED);
            Gradient.addGradient(gui, 100, Color.RED, Color.GREEN, Color.YELLOW);
            Gradient.addGradient(gui, 100, Color.BLUE, Color.MAGENTA, Color.PINK);
            Gradient.addGradient(gui, 100, Color.WHITE, Color.RED, Color.BLACK);
            Gradient.addGradient(gui, 100, Color.RED, Color.GREEN, Color.BLACK);
            Gradient.addGradient(gui, 100, Color.BLUE, Color.PINK, Color.BLACK);
            Gradient.addGradient(gui, 100, Color.BLUE, Color.CYAN, Color.BLACK);
            JOptionPane.showMessageDialog(null, gui);
        };
        SwingUtilities.invokeLater(r);
    }

    // -----------------------------------------------------------------------------------------------------------------
}
