package org.jwellman.swing.jpanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Simple implementation of image backed JPanel inspired by:
 * https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
 * 
 * Note, however, a more robust version would use JXImagePanel from swingx API.
 * 
 * @author rwellman
 *
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(String filename) {
       try {                          
	    	URL url = getClass().getResource(filename);
	    	if (url == null)
	    		image = ImageIO.read(new File(filename));	
	    	else 
	    		image = ImageIO.read(url);
       } catch (IOException ex) {
            System.out.println("Problem loading image");
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics g2 = g.create();       
//        g2.clipRect(0, 0, 200, 200);
        g2.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null); // this was the original implementation
        // but it needed the clip defined so that textareas would wrap correctly;
        // I am not yet sure of the exact root cause.
    }

}