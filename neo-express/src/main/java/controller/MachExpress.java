package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.UIManager;

import view.MachExpressFrame;

/**
 * 
 * @author paawak
 */
public class MachExpress {

    public MachExpress() {
    }

    /**
     * @param args
     *            the command line arguments
     */

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setLookNFeel();
                MachExpressFrame frame = MachExpressFrame.INSTANCE;
                // centerWindow(frame,1000,900);
                fullScreen(frame);
                frame.setVisible(true);
            }
        });
    }

    /**
     * @param args
     *            the command line arguments
     * 
     */
    public static void centerWindow(javax.swing.JFrame frame, int width,
            int height) {
        Window win = new Window(frame);
        frame.setSize(width, height);
        win.setSize(width, height);
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        // If larger than screen, reduce window width or height
        if (screenDim.width < win.getSize().width) {
            win.setSize(screenDim.width, win.getSize().height);
        }
        if (screenDim.height < win.getSize().height) {
            win.setSize(win.getSize().width, screenDim.height);
        }
        int x = (screenDim.width - win.getSize().width) / 2;
        int y = (screenDim.height - win.getSize().height) / 2;
        frame.setBounds(x, y, width, height);
    }

    public static void fullScreen(javax.swing.JFrame frame) {
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(scr.width, scr.height - 40);
    }

    public static void setLookNFeel() {

        try {

            System.out.println("### MachExpress.setLookNFeel() using "
                    + System.getProperty("java.home"));
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"
            // );
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()
            // );
            // UIManager.setLookAndFeel(UIManager.
            // getCrossPlatformLookAndFeelClassName());
            // UIManager.setLookAndFeel(
            // "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            // UIManager.setLookAndFeel(
            // "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );//*/

        } catch (Exception e) {
        }

    }

}
