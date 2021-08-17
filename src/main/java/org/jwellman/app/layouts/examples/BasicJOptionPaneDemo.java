package org.jwellman.app.layouts.examples;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class BasicJOptionPaneDemo extends BasicWithPanels {

	private List<JOptionPane> listOfOptionPanes;
	
	public BasicJOptionPaneDemo() {
		super();
		
		this.name = "JOptionPane Demo";	
	}
	
	protected Component createCenterComponent() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		
		listOfOptionPanes = new ArrayList<>();
		
		JOptionPane pane ; // reusable objref

		pane = createpane(); // reusable objref
		pane.setMessage("[INFORMATION_MESSAGE]");
	    // pane.setOptionType(JOptionPane.OK_OPTION);
		pane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		panel.add(pane); listOfOptionPanes.add(pane);

		pane = createpane(); // reusable objref
		pane.setMessage("[WARNING_MESSAGE]");
	    pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		pane.setMessageType(JOptionPane.WARNING_MESSAGE);
		panel.add(pane); listOfOptionPanes.add(pane);

		pane = createpane(); // reusable objref
		pane.setMessage("[ERROR_MESSAGE]");
	    // pane.setOptionType(JOptionPane.OK_OPTION);
		pane.setMessageType(JOptionPane.ERROR_MESSAGE);
		panel.add(pane); listOfOptionPanes.add(pane);

		pane = createpane(); // reusable objref
		pane.setMessage("[PLAIN_MESSAGE-no icon]");
	    // pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		pane.setMessageType(JOptionPane.PLAIN_MESSAGE);
		panel.add(pane); listOfOptionPanes.add(pane);

		pane = createpane(); // reusable objref
		pane.setMessage("[QUESTION_MESSAGE]");
	    pane.setOptionType(JOptionPane.YES_NO_OPTION);
		pane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		panel.add(pane); listOfOptionPanes.add(pane);

		pane = createpane(); // reusable objref
		pane.setMessage(new Object[] { "Select a value: ", createslider(pane) });
	    pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		pane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		panel.add(pane); listOfOptionPanes.add(pane);

		pane = createpane(); // reusable objref
		pane.setMessage("Choose a value:");
	    pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		pane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		pane.setSelectionValues(new Object[] {"somevalue", "somevalue", "somevalue", "somevalue", "somevalue", "somevalue"});
		panel.add(pane); listOfOptionPanes.add(pane);

		pane = createpane(); // reusable objref
		pane.setMessage("Enter your name:");
		pane.setWantsInput(true);
		pane.setInitialSelectionValue("your name");
	    pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		pane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		panel.add(pane); listOfOptionPanes.add(pane);

		pane = createpane(); // reusable objref
		pane.setMessage("[message]");
	    pane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		pane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		panel.add(pane); listOfOptionPanes.add(pane);

		return panel;
	}

	protected JOptionPane createpane() {
		final JOptionPane pane = new JOptionPane();
		pane.setBorder(BorderFactory.createTitledBorder("JOptionPane"));
		
		return pane;
	}
	
	protected JSlider createslider(final JOptionPane optionPane) {
	    JSlider slider = new JSlider();
	    slider.setMajorTickSpacing(10);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    
	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	        JSlider theSlider = (JSlider) changeEvent.getSource();
	        if (!theSlider.getValueIsAdjusting()) {
	          optionPane.setInputValue(new Integer(theSlider.getValue()));
	        }
	      }
	    };
	    slider.addChangeListener(changeListener);
	    
	    return slider;
    }
	
}
