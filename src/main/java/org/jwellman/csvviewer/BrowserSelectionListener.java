package org.jwellman.csvviewer;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jwellman.swing.layout.BetterCardLayout;

public class BrowserSelectionListener implements ListSelectionListener {

	private JList<Example> browser;
	private BetterCardLayout cardLayout;
	
	public BrowserSelectionListener(JList<Example> list, BetterCardLayout layout) {
		browser = list;
		cardLayout = layout;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		
		final Example ex = browser.getSelectedValue();
		cardLayout.show(ex.getId());
		System.out.println("switch ui here: " + ex.getName());

	}

}
