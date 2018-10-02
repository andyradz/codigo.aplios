package com.codigo.aplios.gui.core.kinect;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// https://sites.google.com/site/drjohnbmatthews/kineticmodel#refs
public class KineticModel
        extends JFrame {

    private static final long serialVersionUID = 144301054447361066L;

    public static void main(final String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(() -> new KineticModel());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block

        }
    }

    /**
     * Construct a KineticModel frame.
     */
    public KineticModel() {
        this.setTitle("KineticModel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(KineticModel.kineticModelPanel(this));
        this.pack();
        this.setVisible(true);
    }

    /**
     * Return a KineticModel panel. The optional frame parameter is used for the enclosing JFrame's default button. A
     * null frame is ignored.
     *
     * @param frame
     *              the enclosing JFrame
     * @return
     */
    public static JPanel kineticModelPanel(final JFrame frame) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final Ensemble model = new Ensemble();
        final DisplayPanel view = new DisplayPanel(model);
        panel.add(view, BorderLayout.CENTER);
        final ControlPanel controls = new ControlPanel(view,
                model);
        panel.add(controls, BorderLayout.EAST);
        if (frame != null)
            frame.getRootPane()
                    .setDefaultButton(controls.getDefaultButton());
        return panel;
    }

}
