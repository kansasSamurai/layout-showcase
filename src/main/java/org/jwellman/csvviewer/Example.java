package org.jwellman.csvviewer;

import java.awt.Component;

import javax.swing.Icon;

public interface Example {

	public Icon getIcon();
	
	public Component getComponent();
	
	public void setId(String identifier);
	
	public String getId();
	
	public String getName();
	
	public String getTextDescription();
		
}
