package org.jwellman.csvviewer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jwellman.csvviewer.examples.BasicWithPanels;
import org.jwellman.csvviewer.examples.JEditorPane;
import org.jwellman.csvviewer.examples.SimpleBorderLayout;
import org.jwellman.csvviewer.examples.ToolbarLayout;
import org.jwellman.csvviewer.examples.TransparentScrollableTextArea;
import org.jwellman.csvviewer.examples.TransparentTextArea;
import org.jwellman.csvviewer.jswing.ch19.TextForm;
import org.jwellman.swing.layout.BetterCardLayout;

@SuppressWarnings({"serial","rawtypes","unchecked","unused"})
public class LayoutBrowser extends JPanel implements ListSelectionListener {

	private JList jlistBrowser;
	private JPanel jpnlBrowser;
	private JPanel jpnlViewer;
	private BetterCardLayout layoutViewer = new BetterCardLayout();

	public LayoutBrowser() {		
		this.setLayout(new BorderLayout());		
		this.add(this.createSelector(), BorderLayout.WEST);
		this.add(this.createViewer(), BorderLayout.CENTER);
	}

	private Component createViewer() {
		final JPanel panel = jpnlViewer = new JPanel();
		layoutViewer.setParent(panel);		
		
		int index = 1;
		for (Example example : this.examples) {
			example.setId("" + index++);
			panel.add(example.getComponent(), example.getId());
		}

		return panel;
	}

	private Component createSelector() {
		final JPanel panel = jpnlBrowser = new JPanel();
		panel.setLayout(new BorderLayout());
		
		jlistBrowser = new JList<Example>(examples);
		jlistBrowser.setVisibleRowCount(6);
		jlistBrowser.setCellRenderer(new ExampleRenderer());
		jlistBrowser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlistBrowser.addListSelectionListener(
				new BrowserSelectionListener(jlistBrowser, layoutViewer));
		
		JScrollPane pane = new JScrollPane(jlistBrowser);
		pane.setBorder(null); // some L&Fs add border to scrollpanes
		
		panel.add(pane, BorderLayout.CENTER);		
		return panel;
	}
	
	private Example[] examples = {
	    new SimpleBorderLayout()
	    ,new BasicWithPanels()
	    ,new ToolbarLayout()
	    ,new JEditorPane()
	    ,new TransparentTextArea()
	    ,new TransparentScrollableTextArea()
	    ,new TextForm()
	    ,new ToolbarLayout()
	    ,new ToolbarLayout()
	    ,new ToolbarLayout()
	};

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		
		// final JList jList = (JList)e.getSource();
		final Example ex = (Example)jlistBrowser.getSelectedValue();
		System.out.println("switch ui here: " + ex.getName());
	}
	
}
