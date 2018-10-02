package com.codigo.aplios.checksum.explorer.view;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class rtrrrrr {

    private JFrame frame;

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    rtrrrrr window = new rtrrrrr();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Create the application.
     */
    public rtrrrrr() {

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
