package org.jwellman.foundation;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
//import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

//import net.sourceforge.napkinlaf.NapkinLookAndFeel;
//import net.sourceforge.napkinlaf.NapkinTheme;
import org.jwellman.foundation.swing.IWindow;
import org.jwellman.foundation.swing.XFrame;
import org.jwellman.foundation.swing.XInternalFrame;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import net.sourceforge.napkinlaf.NapkinLookAndFeel;
import net.sourceforge.napkinlaf.NapkinTheme;
import net.sourceforge.napkinlaf.sketch.sketchers.JotSketcher;

/**
 * The most basic of Swing initialization requirements.
 *
 * @author Rick Wellman
 */
@SuppressWarnings("unused")
public class Stone {

/** The user's entry point UI in a JPanel */
protected JPanel panel;

/** The JDesktopPane used with Desktop apps */
private JDesktopPane desktop;

/** A user interface context object */
private uContext context;

/** Guards the init() method */
protected boolean isInitialized;

/** Indicates desktop mode */
protected boolean isDesktop;

/** The "controlling" JFrame */
protected XFrame frame;

/** The internal frame used in desktop mode */
protected XInternalFrame internalFrame;

// TODO add this comment to LAF_WEB // << not yet available for layout-showcase

// Look and Feel (LAF) identifiers
private static final int LAF_MATCHES_SETTING = 1;
private static final int LAF_WEB = 2;
private static final int LAF_NAPKIN = 3;
private static final int LAF_SYSTEM = 4;
private static final int LAF_NIMROD = 5;
private static final int LAF_JTATTOO = 6;
private static final int LAF_DARCULA = 7;
private static final int LAF_CHOSEN = LAF_JTATTOO;

    public Foundation init() {
        return init(null);
    }

	public Foundation init(uContext c) {
	
	    if (!isInitialized) {
	        isInitialized = true;
	
	        // Log the application classpath for debugging purposes
	        System.out.println("----- Application Classpath -----");
	        final ClassLoader cl = ClassLoader.getSystemClassLoader();
	        final URL[] urls = ((URLClassLoader)cl).getURLs();
	        for (URL url: urls){
	            System.out.println(url.getFile());
	        }
	
	        // Apply anti-aliasing for better rendering (particulary fonts)
	        // The following may have some subtle system dependent behavior:
	        // http://stackoverflow.com/questions/179955/how-do-you-enable-anti-aliasing-in-arbitrary-java-apps
	        // Try System.setProperty("awt.useSystemAAFontSettings", "lcd"); and you should get ClearType
			// https://www.javalobby.org/java/forums/t98492.html
			//      System.setProperty("awt.useSystemAAFontSettings","lcd");
			//      System.setProperty("swing.aatext", "true");
	
	        // Make sure our window decorations come from the look and feel.
	        JFrame.setDefaultLookAndFeelDecorated(true); 
	        // I changed my mind... I think the OS frame makes more sense
	        // I've changed my mind several times... if you want the OS frame... 
	        // set the corresponding LAF.... there... done.
	
	        // Conditionally apply context settings...
	        context = (c != null) ? c : uContext.createContext();
	        if (context.getTheme() != null) { context.getTheme().doCustomTheme(); }
	
	        // Prefer Nimbus over default look and feel.
	        try {
	            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                final String name = info.getName(); System.out.println(name);
	                if ("Metal".equals(name)) { // Metal, Nimbus, CDE/Motif, Windows , Windows Classic                   
	
	
	 		            // Some LnF/Themes use properties (JTattoo, ...)
			            Properties props = new Properties();
	
	          			final int version = LAF_SYSTEM; // LAF_NAPKIN; // LAF_SYSTEM; // LAF_WEB; // LAF_MATCHES_SETTING;
	                    switch (version) {
	                        case LAF_MATCHES_SETTING:
	                            // http://robertour.com/2016/04/25/quickly-improving-java-metal-look-feel/
	                            // https://thebadprogrammer.com/swing-uimanager-keys/
	                            if ("Metal".equals(name)) { // Metal, Nimbus, CDE/Motif, Windows , Windows Classic
	
	                            	// MetalLookAndFeel.setCurrentTheme(new RedTheme());
	
	                                // http://robertour.com/2016/04/25/quickly-improving-java-metal-look-feel/
	                                // https://thebadprogrammer.com/swing-uimanager-keys/                                                               
	                                UIManager.put("swing.boldMetal", Boolean.FALSE);
	                                UIManager.put("Button.background",  Color.decode("#eeeeee"));
	                                UIManager.put("ToggleButton.background",  Color.decode("#eeeeee"));
	                                // UIManager.put("Button.border", new CompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(2, 2, 2, 2)));
	                                // UIManager.put("ToggleButton.border", new CompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(2, 2, 2, 2)));
	
	                                // setUIFont( new javax.swing.plaf.FontUIResource("Segoe UI", Font.PLAIN, 14) );
	
	                            }
	
	                            UIManager.setLookAndFeel(info.getClassName());
	                            
	                            /*
	                             * From DefaultMetalTheme:
	                                private static final String[] defaultNames = {
	                                    "swing.plaf.metal.controlFont",
	                                    "swing.plaf.metal.systemFont",
	                                    "swing.plaf.metal.userFont",
	                                    "swing.plaf.metal.controlFont",
	                                    "swing.plaf.metal.controlFont",
	                                    "swing.plaf.metal.smallFont"
	                                };
	
	                                -Dswing.plaf.metal.userFont=Calibri-18
	                                -Dswing.plaf.metal.smallFont=Calibri-12
	                                -Dswing.plaf.metal.systemFont=Consolas-18
	                                -Dswing.plaf.metal.controlFont=Tahoma-24
	                             */
	
	//                            final MetalLookAndFeel lnf = ((MetalLookAndFeel)UIManager.getLookAndFeel());
	                            final MetalTheme currentmetaltheme = MetalLookAndFeel.getCurrentTheme();
	
	                            System.out.println("MetalTheme user font: " + currentmetaltheme.getUserTextFont().getFontName());
	                            System.out.println("MetalTheme small font: " + currentmetaltheme.getSubTextFont().getName());
	                            System.out.println("MetalTheme system font: " + currentmetaltheme.getSystemTextFont().getName());
	                            System.out.println("MetalTheme control font: " + currentmetaltheme.getControlTextFont().getName());
	
	                            break;
	                        case 2:
	                            UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel"); // works but need to upgrade to 1.29 from 1.27
	                            break;
	                            
	                        case LAF_NAPKIN:
	                            // A user defined napkin theme; compatible with NapkinLAF 1.3-SNAPSHOT+
	                            int scrawlSize = 14;
	                            NapkinTheme def = new NapkinTheme(
	                                    "papersack", "Paper Sack Theme", // Name/Description
	                                    Color.BLACK, // PenColor : (for drawing lines)
	                                    Color.RED, // CheckColor : Checkboxes, ProgressBar, and Default Button
	                                    Color.RED, // new Color(0xf50000), // RadioColor :
	                                    Color.RED, // HighlightColor : (Focus Indicator)
	                                    new Color(0xff, 0xff, 0x00, 0x80), // RolloverColor : JButton, ...
	                                    Color.RED, // SelectionColor : SelectedText, Active JFrame/InternalFrame, JMenu, JList, JToolbar
	                                    new Font("Segoe Print", Font.PLAIN, scrawlSize), // Calibri / Segoe UI
	                                    new Font("Segoe Print", Font.BOLD, scrawlSize), // Segoe UI
	                                    new Font("Veteran Typewriter", Font.PLAIN, 24), // Another Typewriter, Tox Typewriter, Veteran Typewriter
//	                                    NapkinTheme.Manager.tryToLoadFont("FeltTipRoman.ttf").deriveFont(Font.BOLD, 18),
//	                                    scrawl.deriveFont(Font.PLAIN, scrawlSize), // TextFont :
//	                                    scrawlBold.deriveFont(Font.BOLD, scrawlSize), // BoldTextFont :
//	                                    fixed.deriveFont(Font.PLAIN, scrawlSize), // FixedFont :
	                                    new JotSketcher(), 
	                                    NapkinTheme.Manager.background("napkin.jpg"), // paper 
	                                    NapkinTheme.Manager.background("erasure.png"), // erasure (mask)
	                                    NapkinTheme.Manager.background("napkin-original.jpg", 80, 80, 50, 40), // popup paper
	                                    new Color(0xff, 0xff, 0x00, 0x80)); // popup highlight color
	                            NapkinTheme.Manager.addTheme(def);

	                            String[] themeNames = NapkinTheme.Manager.themeNames();
	                            String themeToUse = "papersack"; // napkin | blueprint
	                            NapkinTheme.Manager.setCurrentTheme(themeToUse);
	                            
	                            LookAndFeel laf = new NapkinLookAndFeel();
	                            UIManager.setLookAndFeel(laf);
	                            break;
	                            
	                        case 4:
	                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                            break;
	                        case LAF_NIMROD:
	                        	doNimrodLAF();	                        	
	                            break;
	                            
	                        case LAF_JTATTOO: // JTATTOO_LAF
	                        	doJTattooLAF();
	                            break;
	
	                        case LAF_DARCULA:
	                        	doDarculaLAF();
	                        	break;
	                        	
	                        default:
	                        	break;
	                    }
	
	                    break;
	                }
	//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	            }
	        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
	            // If Nimbus is not available, you can set the GUI to another look and feel.
	        }
	
	    }
	    
	    return (Foundation) this;
	} // end method

	private void doDarculaLAF() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.bulenkov.darcula.DarculaLaf"); 		
	}

	private void doNimrodLAF() throws UnsupportedLookAndFeelException {
    	final NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();

    	NimRODTheme nt = null;
    	
    	int theme = 3;
    	switch (theme) {
    	case 1: // this has been testedand works
        	UIManager.setLookAndFeel( new com.nilo.plaf.nimrod.NimRODLookAndFeel());
        	break;
    	case 2: // this has been tested and works
    		nt = new NimRODTheme();
    		// syracuse
      		nt.setPrimary1( new Color(0xEB1F00) ); // scroll thumb border
    		nt.setPrimary2( new Color(0xF52900) ); // jtable.selection, scroll thumb, checkbox bgnd, text focus(highlighter)
    		nt.setPrimary3( new Color(0xFF3300) );
    		// firehat
//                        		nt.setPrimary1( new Color(0xB80000) ); // scroll thumb border
//                        		nt.setPrimary2( new Color(0xC20000) ); // jtable.selection, scroll thumb, checkbox bgnd, text focus(highlighter)
//                        		nt.setPrimary3( new Color(0xCC0000) );
    		nt.setSecondary1( new Color(0x1F1F1F) );
    		nt.setSecondary2( new Color(0x292929) );
    		nt.setSecondary3( new Color(0x333333) );
    		nt.setBlack( new Color(0xCCCCCC) ); // text components (button, jtable, etc.)
    		nt.setWhite( new Color(0x666666) ); // text bgnd
    		nt.setFont(new Font("Consolas",Font.PLAIN,16));
    		
//                        		NimRODLF = new NimRODLookAndFeel();
    		NimRODLookAndFeel.setCurrentTheme(nt);
    		UIManager.setLookAndFeel(NimRODLF);
    		break;
    	case 3:                        		
    		// greyscale , blueberry , NimRODThemeFile_rix_mint_segoeui
    		// themes/nimrod/NimRODThemeFile_rix_mint_segoeui.theme
    		nt = new NimRODTheme("NimRODThemeFile_rix_royale_calibri.theme");
    		//NimRODLF = new NimRODLookAndFeel();
    		NimRODLookAndFeel.setCurrentTheme(nt);
    		UIManager.setLookAndFeel(NimRODLF);                        		
    		break;
    	default:
        	UIManager.setLookAndFeel( new com.nilo.plaf.nimrod.NimRODLookAndFeel());
    			
    	}
	}

	private void doJTattooLAF() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	
	    // Some LnF/Themes use properties (JTattoo, ...)
	    Properties props = new Properties();
	    
	    boolean useTattooTheme = true;
	    if (useTattooTheme) {
	       
	        boolean demo = false;
	        if (demo) {
	
	            props.put("logoString", "my company");
	            props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");
	           
	            props.put("selectionBackgroundColor", "180 240 197");
	            props.put("menuSelectionBackgroundColor", "180 240 197");
	            
	            props.put("controlColor", "218 254 230");
	            props.put("controlColorLight", "218 254 230");
	            props.put("controlColorDark", "180 240 197");
	
	            props.put("buttonColor", "218 230 254");
	            props.put("buttonColorLight", "255 255 255");
	            props.put("buttonColorDark", "244 242 232");
	
	            props.put("rolloverColor", "218 254 230");
	            props.put("rolloverColorLight", "218 254 230");
	            props.put("rolloverColorDark", "180 240 197");
	
	            props.put("windowTitleForegroundColor", "0 0 0");
	            props.put("windowTitleBackgroundColor", "180 240 197");
	            props.put("windowTitleColorLight", "218 254 230");
	            props.put("windowTitleColorDark", "180 240 197");
	            props.put("windowBorderColor", "218 254 230");                                   
	        }
	
	        props.put("subTextFont", "Consolas BOLD 10"); // ???
	        props.put("userTextFont", "Calibri PLAIN 14"); // JLabel, JCheckbox, Tab Titles, ... // Aluminium only respects:  TableHeaders, Checkboxes, (I assume RadioButtons), ...
	        props.put("menuTextFont", "Calibri PLAIN 12"); // JMenu, ...
	        props.put("systemTextFont", "Baskerville BOLD 24"); 
	        props.put("controlTextFont", "Calibri PLAIN 14"); // JButton, ... // Aluminium does not respect this... well... maybe it does, I just don't know what components it affects yet?
	        props.put("windowTitleFont", "Calibri PLAIN 16"); // JFrame, (JInternalFrame I asume), ...
	
	        // set your theme
	        // SmartLookAndFeel.setCurrentTheme(props);                                                              
	
	    }
	   
	    final int tattooLNF = 3;
	    switch (tattooLNF) {
	    case 1:
	        if (useTattooTheme) FastLookAndFeel.setCurrentTheme(props);
	        UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");                            
	        break;
	
	    case 2:
	        if (useTattooTheme) SmartLookAndFeel.setCurrentTheme(props);
	        UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
	        break;
	
	    case 3:
	        if (useTattooTheme) AluminiumLookAndFeel.setCurrentTheme(props);
	        UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");                            
	        break;
	
	    }
	
	}

    /**
     * http://robertour.com/2016/04/25/quickly-improving-java-metal-look-feel/
     * 
     * @param f
     */
    private static void setUIFont (javax.swing.plaf.FontUIResource f){
        final Enumeration<?> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            final Object key = keys.nextElement();
            final Object value = UIManager.get (key);
            if (value != null 
            		&& value instanceof javax.swing.plaf.FontUIResource) 
            	UIManager.put (key, f);
        }
    }

public IWindow useDesktop(JPanel ui) {

    isDesktop = true; // store the mode

    if (internalFrame == null) {
        panel = ui; // Store a reference to the JPanel

        desktop = new JDesktopPane(); // a specialized layered pane
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE); // Make dragging a little faster but perhaps uglier.

        // Create the JFrame
        frame = new XFrame("Foundation Desktop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(context.getDimension());
        frame.setContentPane(desktop);

        showGUI(); // in desktop mode, this should only show the jframe

        internalFrame = new XInternalFrame("Your UI", true, true, true, true);
        internalFrame.setBounds(10, 10, 225, 125);
        // internalFrame.setVisible(true); // [B]
        internalFrame.add(ui);
        internalFrame.setMaximizable(false);
        internalFrame.setClosable(false);
        desktop.add(internalFrame);

        this.initializeOtherWindows();

    }

    return internalFrame; // frame;
} // end method

public IWindow useWindow(JPanel ui) {

    // Store a reference to the JPanel
    panel = ui;

    // Create the JFrame
    frame = new XFrame("Your UI in Foundation Window");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // setUndecorated(true) would be invoked here if the user has not called
    // JFrame.setDefaultLookAndFeelDecorated(true); ... see the next comment.
    // setUndecorated(true); 
    
    // To use a background color with transparency, you *MUST* either:
    // 1. preceed this call with a call to: setUndecorated(true)  -or-
    // 2. preceed this call with a call to: JFrame.setDefaultLookAndFeelDecorated(false)
    // frame.setBackground(new Color(0,0,0,128 + (16*5)));
    // TODO 1/11/2022... hopefully temporary:  I have disabled the call to setBackground()
    // because the rest of the class does not play well with it (per comments 1 and 2 above)
    // However, I would like to add it back in once I have merged branch neo
    // with branch master.
    
    // frame.setSize(450, 250);
    if (panel != null) frame.add(panel);

    return frame;
}

public void showGUI() {

    if (internalFrame != null) {
        // Start the GUI on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() { @Override public void run() {
                    internalFrame.pack();
                    internalFrame.setVisible(true);
                }
            });
    }
    else if (frame != null) {
        // Start the GUI on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() { @Override public void run() {

                // tried setting background with alpha here... did not work.
                
                // Display the window.
                if (!isDesktop) frame.pack(); // [A]
                frame.setSize(context.getDimension());
                frame.setLocationRelativeTo(null); // [C]
                frame.setVisible(true);

            } }
        );
    }
    else {
        throw new RuntimeException("You have not chosen a window type: useWindow() or useDesktop() ");
    }

} // end method

public JDesktopPane getDesktop() {
    return desktop;
}

/* ========== Footnotes =====================================================
[A] The swing documentation says that pack() makes the frame "displayable"
    I originally thought that meant "visible" but it doesn't (or at least
    it doesn't work that way).  A:  "displayable" means that the component,
    or its root container, have a native-peer.
[B] We intentionally defer showing the internal frame until the user/designer
    calls showGUI() from his code.
[C] Centers the frame/window on the native desktop.
/* ========================================================================== */

    /**
     * This is basically a noop in Stone since it only supports a single
     * application window.  Other levels will definitely override this.
     */
    protected void initializeOtherWindows() {}

} // end class
