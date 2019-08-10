package org.jwellman.csvviewer.examples;

import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jwellman.csvviewer.Example;

@SuppressWarnings("serial")
public class ExampleAdapter extends JPanel implements Example {

	private Icon image;
	
	private String id;

	protected String name;
	
	protected String textDescription;
	
	// TODO need a default icon/imagePath
	private String imagePath = "";
	
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
