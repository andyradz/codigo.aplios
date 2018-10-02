package com.codigo.aplios.gui.core.kinect;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class ControlPanel
        extends JPanel
        implements ActionListener,
        ChangeListener {

    private static final long serialVersionUID = -9057632069715002986L;

    private static final int RATE = 25;										// 25 Hz

    private static final int STRUT = 8;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private static final DecimalFormat pf = new DecimalFormat("0%");

    private final DisplayPanel view;

    private final Ensemble model;

    private final Histogram histogram;

    private final JButton runButton = new JButton();

    private final JButton resetButton = new JButton();

    private final JButton plusButton = new JButton();

    private final JButton minusButton = new JButton();

    private final JLabel paintLabel = new JLabel();

    private final JLabel countLabel = new JLabel();

    private final JLabel collisionLabel = new JLabel();

    private final JSpinner spinner = new JSpinner();

    private final JLabel histLabel = new JLabel();

    private final Timer timer = new Timer(1000 / ControlPanel.RATE, this);

    /**
     * Construct a control panel.
     */
    public ControlPanel(final DisplayPanel view, final Ensemble model) {

        this.view = view;
        this.model = model;
        this.histogram = new Histogram(model.getAtoms());
        this.timer.setInitialDelay(200);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {

                // Catch a breath while resizing.
                if (ControlPanel.this.timer.isRunning())
                    ControlPanel.this.timer.restart();
            }

        });
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        this.runButton.setText("Run");
        this.runButton.setActionCommand("run");
        this.runButton.addActionListener(this);
        panel.add(this.runButton);

        this.resetButton.setText("Reset");
        this.resetButton.setActionCommand("reset");
        this.resetButton.addActionListener(this);
        panel.add(this.resetButton);

        this.plusButton.setText("Atoms +");
        this.plusButton.setActionCommand("plus");
        this.plusButton.addActionListener(this);
        panel.add(this.plusButton);

        this.minusButton.setText("Atoms -");
        this.minusButton.setActionCommand("minus");
        this.minusButton.addActionListener(this);
        panel.add(this.minusButton);

        panel.add(Box.createVerticalStrut(ControlPanel.STRUT));
        final JRadioButton colorButton = new JRadioButton("Color");
        colorButton.setMnemonic(KeyEvent.VK_C);
        colorButton.setActionCommand("color");
        colorButton.setSelected(false);
        panel.add(colorButton);

        final JRadioButton grayButton = new JRadioButton("Gradient");
        grayButton.setMnemonic(KeyEvent.VK_G);
        grayButton.setActionCommand("gradient");
        grayButton.setSelected(true);
        panel.add(grayButton);

        final ButtonGroup group = new ButtonGroup();
        group.add(colorButton);
        group.add(grayButton);
        colorButton.addActionListener(this);
        grayButton.addActionListener(this);

        panel.add(Box.createVerticalStrut(ControlPanel.STRUT));
        panel.add(this.paintLabel);
        panel.add(this.countLabel);
        panel.add(this.collisionLabel);

        panel.add(Box.createVerticalStrut(ControlPanel.STRUT));
        final JLabel rateLabel = new JLabel("Update (Hz):");
        panel.add(rateLabel);
        this.spinner.setModel(new SpinnerNumberModel(ControlPanel.RATE, 5, 50, 5));
        this.spinner.addChangeListener(this);
        this.spinner.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(this.spinner);

        panel.add(Box.createVerticalStrut(ControlPanel.STRUT));
        panel.add(this.histLabel);
        panel.add(this.histogram);

        this.add(panel);
        this.toggle();
    }

    /**
     * Return the defualt button.
     */
    public JButton getDefaultButton() {

        return this.runButton;
    }

    private void toggle() {

        if (this.timer.isRunning()) {
            this.timer.stop();
            this.runButton.setText("Start");
        } else {
            this.timer.start();
            this.runButton.setText("Stop");
        }
    }

    /**
     * Handle buttons and timer.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {

        final Object source = e.getSource();
        final String cmd = e.getActionCommand();
        if ((source == this.timer) && (cmd == null)) {
            this.view.repaint();
            final long pt = this.view.getPaintTime();
            if (pt < 2)
                this.paintLabel.setText("Paint: ~1");
            else
                this.paintLabel.setText("Paint: " + this.view.getPaintTime());
            this.countLabel.setText("Atoms: " +
                    this.model.getAtoms()
                            .size());
            this.collisionLabel.setText("Collide: " + ControlPanel.pf.format(this.model.getCollisionRate()));
            this.histLabel.setText("Velocity: " + ControlPanel.df.format(this.histogram.getAverage()));
            this.histogram.repaint();
        } else if ("run".equals(cmd))
            this.toggle();
        else if ("reset".equals(cmd)) {
            this.model.initAtoms();
            this.timer.restart();
        } else if ("plus".equals(cmd))
            this.model.addAtoms();
        else if ("minus".equals(cmd))
            this.model.removeAtoms();
        else if ("color".equals(cmd))
            this.view.useGradient(false);
        else if ("gradient".equals(cmd))
            this.view.useGradient(true);
    }

    /**
     * Handle spinners.
     */
    @Override
    public void stateChanged(final ChangeEvent e) {

        final Object source = e.getSource();
        if (source == this.spinner) {
            final int rate = ((Number)this.spinner.getValue()).intValue();
            this.timer.setDelay(1000 / rate);
        }
    }

}
