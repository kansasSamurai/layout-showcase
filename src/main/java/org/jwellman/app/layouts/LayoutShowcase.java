package org.jwellman.app.layouts;

import org.jwellman.foundation.Foundation;
import org.jwellman.foundation.uContext;
import org.jwellman.foundation.extend.SimpleMain;
import org.jwellman.foundation.interfaces.uiCustomTheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = WebMvcAutoConfiguration.class)
public class LayoutShowcase extends SimpleMain implements uiCustomTheme {

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(LayoutShowcase.class);
		app.setWebEnvironment(false);
		// app.run(args);
		
		new LayoutShowcase().startup(true, args);
	}

	private LayoutShowcase startup(boolean asMainFrame, String[] args) {

        // Prepare - User Interface Context
        final uContext context = uContext.createContext();
        context.setTheme(this);
        context.setDimension(85);

        // Step 1 - Initialize Swing
        final Foundation f = Foundation.get();
        f.init(); // context

        // Step 2 - Create your UIs in JPanel(s)
        mainui = f.registerUI("viewer", new LayoutBrowser() ); 
        // new GolfScore() LayoutBrowser()) ToolbarLayout()

        // Step 3 - Use Foundation to create your "window"; give it your UI.
        window = f.useWindow(mainui);
        // Step 3a (optional) - Customize your window
        window.setTitle("Layout Showcase"); 
        window.setResizable(true);

        // Step 4a - Create data models, controllers, and other non-UI objects
        // n/a
        
        // Step 4b (optional)- Associate models with views
        // n/a

        // Step 5 - Display your User Interface
        f.showGUI();

        return this;
    }

	@Override
	public void doCustomTheme() {
		
	}
}
