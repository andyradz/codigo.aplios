package com.codigo.aplios.sdk.core;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.basic.BasicButtonUI;

public class RoundedCornerButtonUI
        extends BasicButtonUI {

    private static final float arcwidth = 16.0f;

    private static final float archeight = 16.0f;

    protected static final int focusstroke = 2;

    protected final Color fc = new Color(100, 150, 255, 200);

    protected final Color ac = new Color(230, 230, 230);

    protected final Color rc = Color.ORANGE;

    protected Shape shape;

    protected Shape border;

    protected Shape base;

    @Override
    protected void installDefaults(final AbstractButton b) {

        super.installDefaults(b);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setBackground(new Color(250, 250, 250));
        this.initShape(b);
    }

    @Override
    protected void installListeners(final AbstractButton b) {

        final BasicButtonListener listener = new BasicButtonListener(b) {
            @Override
            public void mousePressed(final MouseEvent e) {

                final AbstractButton b = (AbstractButton)e.getSource();
                RoundedCornerButtonUI.this.initShape(b);
                if (RoundedCornerButtonUI.this.shape.contains(e.getX(), e.getY()))
                    super.mousePressed(e);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {

                if (RoundedCornerButtonUI.this.shape.contains(e.getX(), e.getY()))
                    super.mouseEntered(e);
            }

            @Override
            public void mouseMoved(final MouseEvent e) {

                if (RoundedCornerButtonUI.this.shape.contains(e.getX(), e.getY()))
                    super.mouseEntered(e);
                else
                    super.mouseExited(e);
            }

        };
        if (listener != null) {
            b.addMouseListener(listener);
            b.addMouseMotionListener(listener);
            b.addFocusListener(listener);
            b.addPropertyChangeListener(listener);
            b.addChangeListener(listener);
        }
    }

    @Override
    public void paint(final Graphics g, final JComponent c) {

        final Graphics2D g2 = (Graphics2D)g;
        final AbstractButton b = (AbstractButton)c;
        final ButtonModel model = b.getModel();
        this.initShape(b);
        // ContentArea
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (model.isArmed()) {
            g2.setColor(this.ac);
            g2.fill(this.shape);
        } else if (b.isRolloverEnabled() && model.isRollover())
            this.paintFocusAndRollover(g2, c, this.rc);
        else if (b.hasFocus())
            this.paintFocusAndRollover(g2, c, this.fc);
        else {
            g2.setColor(c.getBackground());
            g2.fill(this.shape);
        }
        // Border
        g2.setPaint(c.getForeground());
        g2.draw(this.shape);

        g2.setColor(c.getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        super.paint(g2, c);
    }

    private void initShape(final JComponent c) {

        if (!c.getBounds()
                .equals(this.base)) {
            this.base = c.getBounds();
            this.shape = new RoundRectangle2D.Float(
                    0, 0, c.getWidth() - 1, c.getHeight() - 1, RoundedCornerButtonUI.arcwidth,
                    RoundedCornerButtonUI.archeight);
            this.border = new RoundRectangle2D.Float(
                    RoundedCornerButtonUI.focusstroke, RoundedCornerButtonUI.focusstroke,
                    c.getWidth() - 1 - (RoundedCornerButtonUI.focusstroke * 2),
                    c.getHeight() - 1 - (RoundedCornerButtonUI.focusstroke * 2), RoundedCornerButtonUI.arcwidth,
                    RoundedCornerButtonUI.archeight);
        }
    }

    private void paintFocusAndRollover(final Graphics2D g2, final JComponent c, final Color color) {

        g2.setPaint(new GradientPaint(0, 0, color, c.getWidth() - 1, c.getHeight() - 1, color.brighter(), true));
        g2.fill(this.shape);
        g2.setColor(c.getBackground());
        g2.fill(this.border);
    }

}
