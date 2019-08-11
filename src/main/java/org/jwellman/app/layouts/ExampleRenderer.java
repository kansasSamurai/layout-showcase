package org.jwellman.app.layouts;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings({ "serial", "rawtypes" })
public class ExampleRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0x7395AE);

	public ExampleRenderer() {
        setOpaque(true);
        setIconTextGap(12);
        setBorder(BorderFactory.createEmptyBorder(4,4,4,24));
	}
	
	@Override
	public Component getListCellRendererComponent(
			JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	{
		final Example example = (Example)value;
		this.setIcon(example.getIcon());
		this.setText(example.getName());
		
        if(isSelected) {
            setBackground(HIGHLIGHT_COLOR);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }

		return this;
	}

}
