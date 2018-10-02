package com.codigo.aplios.gui.core.kinect;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * GradientImage is a static factory for images of atoms filled with RadialGradientPaint.
 *
 * @author John B. Matthews
 */
final class GradientImage
        extends Object {

    private static final int ALPHA = 0x3f;

    private static final Color[] colors = {new Color(0xff, 0x00, 0x00, 0xff),
        new Color(0x00, 0xff, 0x00, 0xff), new Color(0x00, 0x00, 0xff, 0xff), new Color(0x00, 0xff, 0xff, 0xff),
        new Color(0xff, 0x00, 0xff, 0xff), new Color(0xff, 0xff, 0x00, 0xff)};

    private static final Color[] alphas = {new Color(0xff, 0x00, 0x00, GradientImage.ALPHA),
        new Color(0x00, 0xff, 0x00, GradientImage.ALPHA), new Color(0x00, 0x00, 0xff, GradientImage.ALPHA),
        new Color(0x00, 0xff, 0xff, GradientImage.ALPHA), new Color(0xff, 0x00, 0xff, GradientImage.ALPHA),
        new Color(0xff, 0xff, 0x00, GradientImage.ALPHA)};

    private static Ellipse2D ball;

    private static RadialGradientPaint rgp;

    private static BufferedImage image;

    GradientImage() {

    }

    ;

	/** Return a static array of Colors. */
	public static Color[] createColorArray() {

        return GradientImage.colors;
    }

    /**
     * Return a static array of Images.
     */
    public static Image[] createImageArray(final int minR) {

        final Image[] images = new Image[GradientImage.colors.length];
        for (int i = 0; i < GradientImage.colors.length; i++) {
            final int radius = minR + i;
            final int diameter = radius * 2;
            GradientImage.ball = new Ellipse2D.Double(0, 0, diameter, diameter);
            GradientImage.rgp = new RadialGradientPaint(
                    new Point2D.Double(radius, radius), new Point2D.Double(0, radius), GradientImage.colors[i],
                    GradientImage.alphas[i]);
            GradientImage.image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
            final Graphics2D g2 = GradientImage.image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setComposite(AlphaComposite.Clear);
            g2.fillRect(0, 0, diameter, diameter);
            g2.setComposite(AlphaComposite.Src);
            g2.setPaint(GradientImage.rgp);
            g2.fill(GradientImage.ball);
            images[i] = GradientImage.image;
        }
        return images;
    }

}

class RadialGradientPaint
        implements Paint {

    private final Point2D point;

    private final Point2D radius;

    private final Color pointColor, backgroundColor;

    /**
     * Construct an acyclic, radial, gradient paint centered on p, proportional to the length of r, spanning c1 to c2.
     */
    public RadialGradientPaint(final Point2D p, final Point2D r, final Color c1, final Color c2) {

        if (r.distance(0, 0) <= 0)
            throw new IllegalArgumentException("Radius > 0 required.");
        this.point = p;
        this.radius = r;
        this.pointColor = c1;
        this.backgroundColor = c2;
    }

    @Override
    public PaintContext createContext(final ColorModel cm, final Rectangle deviceBounds, final Rectangle2D userBounds,
            final AffineTransform xform, final RenderingHints hints) {

        final Point2D transformedPoint = xform.transform(this.point, null);
        final Point2D transformedRadius = xform.deltaTransform(this.radius, null);
        return new RadialGradientContext(transformedPoint, transformedRadius, this.pointColor, this.backgroundColor);
    }

    @Override
    public int getTransparency() {

        final int a1 = this.pointColor.getAlpha();
        final int a2 = this.backgroundColor.getAlpha();
        return (((a1 & a2) == 0xff) ? Transparency.OPAQUE : Transparency.TRANSLUCENT);
    }

}

class RadialGradientContext
        implements PaintContext {

    private final Point2D point;

    private final Point2D radius;

    private final Color c1, c2;

    public RadialGradientContext(final Point2D p, final Point2D r, final Color c1, final Color c2) {

        this.point = p;
        this.radius = r;
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public void dispose() {

    }

    @Override
    public ColorModel getColorModel() {

        return ColorModel.getRGBdefault();
    }

    @Override
    public Raster getRaster(final int x, final int y, final int w, final int h) {

        final int[] ia = new int[w * h * 4];
        int ix = 0;
        for (int row = 0; row < h; row++)
            for (int col = 0; col < w; col++) {
                final double pointDistance = this.point.distance(col + x, row + y);
                final double radialDistance = this.radius.distance(0, 0);
                final double dr = Math.min(pointDistance / radialDistance, 1.0);
                ia[ix++] = (int)(this.c1.getRed() + (dr * (this.c2.getRed() - this.c1.getRed())));
                ia[ix++] = (int)(this.c1.getGreen() + (dr * (this.c2.getGreen() - this.c1.getGreen())));
                ia[ix++] = (int)(this.c1.getBlue() + (dr * (this.c2.getBlue() - this.c1.getBlue())));
                ia[ix++] = (int)(this.c1.getAlpha() + (dr * (this.c2.getAlpha() - this.c1.getAlpha())));
            }
        final WritableRaster raster = this.getColorModel()
                .createCompatibleWritableRaster(w, h);
        raster.setPixels(0, 0, w, h, ia);
        return raster;
    }

}
