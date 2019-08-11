package org.jwellman.swing.text;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Some improvements to JDK HTMLEditorKit.HTMLFactory including:<ul>
 * <li>Support for 'span' tags</li>
 * </ul>
 * 
 * @author rwellman
 *
 */
public class BetterHTMLFactory extends HTMLEditorKit.HTMLFactory implements ViewFactory {

	private static final long serialVersionUID = 1L;

	/**
	 * Override the create() method to account for SPAN tags.
	 * 
	 * For now... just call super.create() to do a bit of experimenting with the debugger
	 */
    public View create(Element elem) {
    	
    	View view = super.create(elem);
    	
        final AttributeSet attrs = elem.getAttributes();
        final Object elementName = attrs.getAttribute(AbstractDocument.ElementNameAttribute);
        final Object o = (elementName != null) ? null 
        		: attrs.getAttribute(StyleConstants.NameAttribute);

        if (o instanceof HTML.Tag) {
            final HTML.Tag kind = (HTML.Tag) o;
            if (kind == HTML.Tag.SPAN) {
                System.out.println("create <span> view here...");                
            }
        }
        
    	return view;
    }
    
}
