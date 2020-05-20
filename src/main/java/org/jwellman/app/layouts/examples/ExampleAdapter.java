package org.jwellman.app.layouts.examples;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jwellman.app.layouts.Example;

@SuppressWarnings("serial")
public class ExampleAdapter extends JPanel implements Example, SwingConstants {

	private Icon image;
	
	private String id;

	protected String name;
	
	protected String textDescription;
	
	protected static final Color TRANSPARENT = new Color(0,0,0,0);

	private String imagePath = "/images/examples/swing.gif";
	
	public ExampleAdapter() {
		super();
	}
	
	public ExampleAdapter(LayoutManager layout) {
		super(layout);
	}

	@Override
	public Icon getIcon() {
	    if (image == null) {
	    	URL url = getClass().getResource(this.getImagePath());
	    	if (url == null)
	    		image = new ImageIcon(this.getImagePath());
	    	else 
		        image = new ImageIcon(getClass().getResource(this.getImagePath()));	
	    }
	    // System.out.println("iconheight: " + image.getIconHeight());
        return image;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getTextDescription() {		
		return this.textDescription;
	}

	protected void setImagePath(String path) {
		this.imagePath = path;
	}
	
	protected String getImagePath() {
		return imagePath;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Component getComponent() {
		return this;
	}

}
