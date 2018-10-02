package com.codigo.aplios.sdk.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainEditor {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Circuit Editor");
        // Creates a frame with a title of "Paint it"

        final Container content = frame.getContentPane();
        // Creates a new container
        content.setLayout(new BorderLayout());
        // sets the layout

        final PadDraw drawPad = new PadDraw();
        // creates a new padDraw, which is pretty much the paint program

        content.add(drawPad, BorderLayout.CENTER);
        // sets the padDraw in the center

        // for the task bar
        final JTextArea taskbar = new JTextArea();

        taskbar.setVisible(true);
        taskbar.setBackground(Color.lightGray);
        taskbar.setEditable(false);

        // adding the taskbar to the bottom-part
        content.add(taskbar, BorderLayout.SOUTH);

        final JPanel panel = new JPanel();
        // creates a JPanel
        panel.setPreferredSize(new Dimension(32,
                68));
        panel.setMinimumSize(new Dimension(32,
                68));
        panel.setMaximumSize(new Dimension(32,
                68));
        // This sets the size of the panel

        final JButton clearButton = new JButton("CLEAR");
        clearButton.addActionListener(e -> {
            drawPad.clear();
            taskbar.setText(null);
        });

        final JButton orButton = new JButton("OR");
        orButton.addActionListener(e -> {
            drawPad.addOR();
            taskbar.setText("Click to add an OR-Gate");
        });

        final JButton wireButton = new JButton("WIRE");
        wireButton.addActionListener(e -> {
            drawPad.wire();
            taskbar.setText("Right Click to stop adding Wires.");
        });

        panel.add(clearButton);
        panel.add(wireButton);
        panel.add(orButton);
        // adds the buttons to the panel

        content.add(panel, BorderLayout.NORTH);
        // sets the panel to the upper portion

        frame.setSize(1000, 700);
        // sets the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // makes it so you can close
        frame.setResizable(false);
        // so that u can't change the dafault size
        frame.setVisible(true);
        // makes it so you can see it
    }

}

class PadDraw
        extends JComponent {

    Image image;

    Graphics2D graphics2D;
    // this is what we'll be using to draw on

    // Graphics or;
    int currentX, currentY, oldX, oldY, tmpX, tmpY;

    int xpos, ypos;

    boolean mousedragActv, desn = true, orclkactv = false;

    MouseListener line_connct = new MouseAdapter() {

        // When mouse is clicked we detect if it's right-click.
        // for right click we reset the cursor but the image unchanged
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                PadDraw.this.reset();
                System.out.println("Right Button Clicked");
            }
            System.out.println(PadDraw.this.oldX +
                    "," +
                    PadDraw.this.oldY +
                    "--" +
                    PadDraw.this.currentX +
                    "," +
                    PadDraw.this.currentY +
                    "=Click");
        }

        // At Moues-Press with Left-Click we only set the starting point
        @Override
        public void mousePressed(MouseEvent e) {
            PadDraw.this.mousedragActv = false;
            if (PadDraw.this.desn && e.getButton() == MouseEvent.BUTTON1) {
                PadDraw.this.oldX = e.getX();
                PadDraw.this.oldY = e.getY();
                PadDraw.this.desn = false;
                System.out.println(PadDraw.this.oldX +
                        "," +
                        PadDraw.this.oldY +
                        "--" +
                        PadDraw.this.currentX +
                        "," +
                        PadDraw.this.currentY +
                        "=Press");
            }
        }

        // At Mouse-Release we draw the line actually
        @Override
        public void mouseReleased(MouseEvent e) {
            PadDraw.this.mousedragActv = false;
            PadDraw.this.currentX = e.getX();
            PadDraw.this.currentY = e.getY();
            System.out.println(PadDraw.this.oldX +
                    "," +
                    PadDraw.this.oldY +
                    "--" +
                    PadDraw.this.currentX +
                    "," +
                    PadDraw.this.currentY +
                    "=Release");
            if (PadDraw.this.graphics2D != null &&
                    PadDraw.this.desn == false &&
                    e.getButton() == MouseEvent.BUTTON1) {
                PadDraw.this.graphics2D.drawLine(PadDraw.this.oldX, PadDraw.this.oldY,
                        PadDraw.this.currentX, PadDraw.this.currentY);
                PadDraw.this.oldX = PadDraw.this.currentX;
                PadDraw.this.oldY = PadDraw.this.currentY;
                PadDraw.this.desn = false;
            }
            PadDraw.this.repaint();
        }

    };

    MouseMotionListener line_show = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            PadDraw.this.tmpX = e.getX();
            PadDraw.this.tmpY = e.getY();
            PadDraw.this.mousedragActv = true;
            PadDraw.this.repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            PadDraw.this.mousedragActv = false;
        }

    };

    MouseListener add_or_ms = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            PadDraw.this.xpos = e.getX();
            PadDraw.this.ypos = e.getY();
            System.out.println("OR CLICKED");
            PadDraw.this.graphics2D.drawArc(PadDraw.this.xpos - 60, PadDraw.this.ypos - 20, 120, 40, 270, 180);
            PadDraw.this.graphics2D.drawArc(PadDraw.this.xpos - 30, PadDraw.this.ypos - 20, 55, 40, 270, 180);
            PadDraw.this.graphics2D.drawLine(PadDraw.this.xpos + 60, PadDraw.this.ypos, PadDraw.this.xpos + 90,
                    PadDraw.this.ypos);
            PadDraw.this.repaint();
        }

    };

    // Now for the constructors
    public PadDraw() {
        this.setDoubleBuffered(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        // System.out.println(oldX+","+oldY+"--"+currentX+","+currentY);
        if (this.image == null) {
            this.image = this.createImage(this.getSize().width, this.getSize().height);
            this.graphics2D = (Graphics2D)this.image.getGraphics();
            this.graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.clear();
        }
        g.drawImage(this.image, 0, 0, null);
        if (this.mousedragActv)
            g.drawLine(this.oldX, this.oldY, this.tmpX, this.tmpY);
    }

    // this is the painting bit
    // if it has nothing on it then
    // it creates an image the size of the window
    // sets the value of Graphics as the image
    // sets the rendering
    // runs the clear() method
    // then it draws the image
    public void clear() {
        this.graphics2D.setPaint(Color.white);
        this.graphics2D.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.graphics2D.setColor(Color.black);
        this.reset();
        this.defaultmouse();
        this.repaint();
    }
    // this is the clear
    // it sets the colors as white
    // then it fills the window with white
    // thin it sets the color back to black

    public void addOR() {
        this.reset();
        this.addMouseListener(this.add_or_ms);
        System.out.println("In the ADD_OR");
        // this.removeMouseListener(addorbyms);
    }

    public void wire() {
        this.defaultmouse();
        this.addMouseListener(this.line_connct);
        this.addMouseMotionListener(this.line_show);
    }

    public void reset() {
        this.oldX = this.oldY = this.tmpX = this.tmpY = this.currentX = this.currentY = 0;
        this.desn = true;
        this.defaultmouse();
    }

    public void defaultmouse() {
        this.removeMouseListener(this.line_connct);
        this.removeMouseMotionListener(this.line_show);
        this.removeMouseListener(this.add_or_ms);
    }

}
