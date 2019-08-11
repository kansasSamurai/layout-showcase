package org.jwellman.swing.text;

import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;

/**
 * 
 * @author rwellman
 *
 */
public class BetterHTMLEditorKit extends HTMLEditorKit {

	private static final long serialVersionUID = 1L;

	private static final BetterHTMLFactory betterFactory = new BetterHTMLFactory();
	
	public BetterHTMLEditorKit() {
		super();
	}
	
	@Override
    public ViewFactory getViewFactory() {
        return betterFactory;
    }

}
