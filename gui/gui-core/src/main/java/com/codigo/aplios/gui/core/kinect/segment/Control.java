package com.codigo.aplios.gui.core.kinect.segment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Control
        extends JFrame
        implements ActionListener {

    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final Timer myTimer = new Timer();
        myTimer.schedule(new CoolClockTimer(), 0, 1000);

    }

    /**
     * JFrame used for pop up messages.
     */
    JFrame popUpFrame;

    /**
     * The clock digit display JPanel
     */
    Display displayPanel;

    /**
     * Instance of the CoolClockTimer for passing UI interaction information
     */
    CoolClockTimer coolClock;

    /**
     * Constructor. Initializes the GUI, GUI components, and necessary variables with appropriate settings.
     *
     * @param myClock
     *                an instance of the CoolClockTimer that created this Control object, used for passing UI information
     *                (button presses) to the clock controller
     */
    public Control(final CoolClockTimer myClock) {
        super("Hyperclock 2000");

        // Set coolClock
        this.coolClock = myClock;

        // Set layout (how panels are organized within frame)
        // Alan, feel free to change this if need be
        this.setLayout(new FlowLayout());

        // Sets the size, width px X height px
        // Note: this includes the ~40px top bar
        this.setSize(1500, 600);

        // Make the background black
        this.getContentPane()
                .setBackground(Color.BLACK);

        // Puts the window in the middle of the screen
        this.setLocationRelativeTo(null);

        // Make the window not resizable
        this.setResizable(true);

        // Exit the application when the "X" button is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.displayPanel = new Display();
        this.getContentPane()
                .add(this.displayPanel);

        // Button Interface Code
        // buttonPanel will hold all of the buttons
        // the GridLayout will arange the buttons in a 2 row grid, 0 means it can have any number of columns
        final JPanel buttonPanel = new JPanel(new GridLayout(2,
                0));
        buttonPanel.setPreferredSize(new Dimension(1500,
                150));

        // Create the buttons
        // Top Row
        final JButton addHour = new JButton("Hour +");
        final JButton addMinute = new JButton("Minutes +");
        final JButton addSecond = new JButton("Seconds +");
        final JButton toggleFormat = new JButton("Toggle 24 hour/12 hour format");
        // Bottom Row
        final JButton subHour = new JButton("Hour -");
        final JButton subMinute = new JButton("Minutes -");
        final JButton subSecond = new JButton("Seconds -");
        final JButton togglePause = new JButton("Start/Pause");

        // Add actionListeners for the buttons
        addHour.addActionListener(this);
        addMinute.addActionListener(this);
        addSecond.addActionListener(this);
        toggleFormat.addActionListener(this);

        subHour.addActionListener(this);
        subMinute.addActionListener(this);
        subSecond.addActionListener(this);
        togglePause.addActionListener(this);

        // Add the buttons to our buttonPanel
        buttonPanel.add(addHour);
        buttonPanel.add(addMinute);
        buttonPanel.add(addSecond);
        buttonPanel.add(toggleFormat);

        buttonPanel.add(subHour);
        buttonPanel.add(subMinute);
        buttonPanel.add(subSecond);
        buttonPanel.add(togglePause);

        // Add the buttonPanel to our JFrame
        this.getContentPane()
                .add(buttonPanel);

        // Reveal ourselves to the world
        this.setVisible(true);

        // For pop up messages
        this.popUpFrame = new JFrame("Dialogue");
    }

    /**
     * Handles GUI event responses such as button presses.
     *
     * @param event
     *              the event that has occurred
     * @post the appropriate response to the event will be executed
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Hour +":
                this.coolClock.addTime(3600);
                break;
            case "Minutes +":
                this.coolClock.addTime(60);
                break;
            case "Seconds +":
                this.coolClock.addTime(1);
                break;
            case "Start/Pause":
                this.coolClock.togglePause();
                break;
            case "Toggle 24 hour/12 hour format":
                this.coolClock.toggleHourFormat();
                break;
            case "Hour -":
                this.coolClock.addTime(-3600);
                break;
            case "Minutes -":
                this.coolClock.addTime(-60);
                break;
            case "Seconds -":
                this.coolClock.addTime(-1);
                break;
            default:
                this.displayMessage("ERROR: Unrecognized event");
                break;
        }
    }

    /**
     * Calls setDisplay in displayPanel
     *
     * @param digits
     *               a four digit number to be displayed on the clock face
     * @param colon
     *               true if colon should be displayed, false if it should not
     * @param msg
     *               the message to be displayed next to the digit display (am/pm)
     */
    public void setDisplay(final int[] digits, final boolean colon, final String msg) {
        this.displayPanel.setDisplay(digits, colon, msg);
    }

    /**
     * Displays a message popup for errors or other messages.
     *
     * @param msg
     *            The string to be displayed in the dialogue window
     */
    public void displayMessage(final String msg) {
        JOptionPane.showMessageDialog(this.popUpFrame, msg);
    }

}
