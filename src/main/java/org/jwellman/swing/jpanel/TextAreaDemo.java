package org.jwellman.swing.jpanel;

import java.awt.*;
import javax.swing.*;

/**
 * 
 *  Credit:
 *  http://www.camick.com/java/source/ScrollablePanel.java
 *  https://tips4java.wordpress.com/2009/12/20/scrollable-panel/
 * 
 * @author rwellman
 * 
 */
public class TextAreaDemo {

    public static void main(String[] args) {
        int version = 2;
        
        final JPanel panel = version == 1
                ? new JPanel( new BorderLayout() )
                :  new ScrollablePanel();
        panel.setLayout( new BorderLayout() );
              
        if (version == 2) {
            ((ScrollablePanel)panel).setScrollableWidth(
                    ScrollablePanel.ScrollableSizeHint.FIT);
        }

        JTextArea textArea1 = new JTextArea(5, 20);
        textArea1.setText("1\n2\n3\n4\n5\n6\n7\n8");
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        panel.add(new JScrollPane(textArea1), BorderLayout.NORTH);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(panel));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
