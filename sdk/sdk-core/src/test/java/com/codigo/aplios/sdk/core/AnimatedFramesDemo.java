package com.codigo.aplios.sdk.core;

// https://www.ntu.edu.sg/home/ehchua/programming/java/J8b_Game_2DGraphics.html
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class AnimatedFramesDemo
        extends JPanel {
    // Named-constants

    static final int CANVAS_WIDTH = 640;

    static final int CANVAS_HEIGHT = 480;

    public static final String TITLE = "Animated Frame Demo";

    private final String[] imgFilenames = {"images/pacman_1.png", "images/pacman_2.png", "images/pacman_3.png"};

    private Image[] imgFrames;																					// array
    // of
    // Images
    // to
    // be
    // animated

    private int currentFrame = 0;																		// current
    // frame
    // number

    private final int frameRate = 5;																		// frame
    // rate
    // in
    // frames
    // per
    // second

    private int imgWidth, imgHeight;		// width
    // and
    // height
    // of
    // the
    // image

    private double x = 100.0, y = 80.0;	// (x,y)
    // of
    // the
    // center
    // of
    // image

    private final double speed = 8;				// displacement
    // in
    // pixels
    // per
    // move

    private double direction = 0;	// in
    // degrees

    private final double rotationSpeed = 1;	// in
    // degrees
    // per
    // move

    // Used to carry out the affine transform on images
    private final AffineTransform transform = new AffineTransform();

    /**
     * Constructor to set up the GUI components
     */
    public AnimatedFramesDemo() {

        // Setup animation
        this.loadImages(this.imgFilenames);
        final Thread animationThread = new Thread() {
            @Override
            public void run() {

                while (true) {
                    AnimatedFramesDemo.this.update(); // update the position and image
                    AnimatedFramesDemo.this.repaint(); // Refresh the display
                    try {
                        Thread.sleep(1000 / AnimatedFramesDemo.this.frameRate); // delay and yield to other threads
                    } catch (final InterruptedException ex) {
                    }
                }
            }

        };
        animationThread.start(); // start the thread to run animation

        // Setup GUI
        this.setPreferredSize(new Dimension(AnimatedFramesDemo.CANVAS_WIDTH, AnimatedFramesDemo.CANVAS_HEIGHT));
    }

    /**
     * Helper method to load all image frames, with the same height and width
     */
    private void loadImages(final String[] imgFileNames) {

        final int numFrames = imgFileNames.length;
        this.imgFrames = new Image[numFrames]; // allocate the array
        URL imgUrl = null;
        for (int i = 0; i < numFrames; ++i) {
            imgUrl = this.getClass()
                    .getClassLoader()
                    .getResource(imgFileNames[i]);
            if (imgUrl == null)
                System.err.println("Couldn't find file: " + imgFileNames[i]);
            else
                try {
                    this.imgFrames[i] = ImageIO.read(imgUrl); // load image via URL
                } catch (final IOException ex) {
                    ex.printStackTrace();
                }
        }
        this.imgWidth = this.imgFrames[0].getWidth(null);
        this.imgHeight = this.imgFrames[0].getHeight(null);
    }

    /**
     * Update the position based on speed and direction of the sprite
     */
    public void update() {

        this.x += this.speed * Math.cos(Math.toRadians(this.direction)); // x-position
        if (this.x >= AnimatedFramesDemo.CANVAS_WIDTH)
            this.x -= AnimatedFramesDemo.CANVAS_WIDTH;
        else if (this.x < 0)
            this.x += AnimatedFramesDemo.CANVAS_WIDTH;
        this.y += this.speed * Math.sin(Math.toRadians(this.direction)); // y-position
        if (this.y >= AnimatedFramesDemo.CANVAS_HEIGHT)
            this.y -= AnimatedFramesDemo.CANVAS_HEIGHT;
        else if (this.y < 0)
            this.y += AnimatedFramesDemo.CANVAS_HEIGHT;
        this.direction += this.rotationSpeed; // update direction based on rotational speed
        if (this.direction >= 360)
            this.direction -= 360;
        else if (this.direction < 0)
            this.direction += 360;
        ++this.currentFrame; // display next frame
        if (this.currentFrame >= this.imgFrames.length)
            this.currentFrame = 0;
    }

    /**
     * Custom painting codes on this JPanel
     */
    @Override
    public void paintComponent(final Graphics g) {

        super.paintComponent(g); // paint background
        this.setBackground(Color.WHITE);
        final Graphics2D g2d = (Graphics2D)g;

        this.transform.setToIdentity();
        // The origin is initially set at the top-left corner of the image.
        // Move the center of the image to (x, y).
        this.transform.translate(this.x - (this.imgWidth / 2), this.y - (this.imgHeight / 2));
        // Rotate about the center of the image
        this.transform.rotate(Math.toRadians(this.direction), this.imgWidth / 2, this.imgHeight / 2);
        // Apply the transform to the image and draw
        g2d.drawImage(this.imgFrames[this.currentFrame], this.transform, null);
    }

    /**
     * The Entry main method
     */
    public static void main(final String[] args) {

        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame(AnimatedFramesDemo.TITLE);
            frame.setContentPane(new AnimatedFramesDemo());
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // center the application window
            frame.setVisible(true);
        });
    }

}
