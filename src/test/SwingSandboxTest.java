package test;

import javax.swing.JFrame;

public class SwingSandboxTest {
    public static void main(String[] args) {
        JFrame f = new JFrame("titolo");
        f.setSize(1800, 900);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
