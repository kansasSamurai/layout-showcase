package org.jwellman.csvviewer.buttons;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;

abstract public class AFlatButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	public final static String PROFILE = "Profile";
	public final static String LIBRARY = "Library";
	public final static String BUGREPORT = "Report Bug";
	
	protected static boolean LIGHT_THEME = true;
	
	protected final static Map<String,String> LINKS;
	protected final static Map<String,String> SELECTED;
	static {
		LINKS = new HashMap<>();
		LINKS.put(PROFILE, "/icons/actions/ic_account_circle_white_24dp.png");
		LINKS.put(LIBRARY, "/icons/actions/ic_book_white_24dp.png");
		LINKS.put(BUGREPORT, "/icons/actions/ic_bug_report_white_24dp.png");
		
		SELECTED = new HashMap<>();
		SELECTED.put(PROFILE, "/icons/actions/ic_account_circle_black_24dp.png");
		SELECTED.put(LIBRARY, "/icons/actions/ic_book_black_24dp.png");
		SELECTED.put(BUGREPORT, "/icons/actions/ic_bug_report_black_24dp.png");
	}		
	
	public AFlatButton() {
		super();
	}

	public AFlatButton(String label) {
		super(label);
	}

	protected final Image getImage(String resource) {
		try {
			final Map<String,String> map = LIGHT_THEME ? SELECTED : LINKS;
			final Image image = ImageIO.read(getClass().getResource(map.get(resource)));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected final Image getSelectedImage(String resource) {
		try {
			final Map<String,String> map = LIGHT_THEME ? LINKS : SELECTED;
			final Image image = ImageIO.read(getClass().getResource(map.get(resource)));
			System.out.println("height: " + image.getHeight(null) + " width: " + image.getWidth(null));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}	
		
}
