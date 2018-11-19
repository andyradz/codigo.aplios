/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author root
 */
public class SlidePaneFactory extends Box {

	StateListener		listener;
	List<StateListener>	listeList	= new ArrayList<>();

	private SlidePaneFactory(final boolean isGroup) {

		super(BoxLayout.Y_AXIS);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {

				if (e.getSource() instanceof SlidePaneFactory)
					return;
				for (final StateListener lister : SlidePaneFactory.this.listeList) {
					if (((JPanel) e.getSource()).getParent() == lister) {
						lister.toggleState();
						continue;
					}
					if (isGroup)
						lister.reset();
				}
			}
		});
	}

	public static SlidePaneFactory getInstance(final boolean isGroup) {

		return new SlidePaneFactory(
			isGroup);
	}

	public static SlidePaneFactory getInstance() {

		return SlidePaneFactory.getInstance(false);
	}

	public void add(final JComponent slideComponent) {

		add(slideComponent, null, null, false);
	}

	public void add(final JComponent slideComponent, final String title) {

		add(slideComponent, title, null, false);
	}

	public void add(final JComponent slideComponent, final String title, final Image imageIcon) {

		add(slideComponent, title, imageIcon, false);
	}

	public void add(final JComponent slideComponent, final String title, final Image imageIcon,
			final boolean isExpand) {

		this.listener = null;
		this.listener = new SlidingPanel(
			slideComponent, title, imageIcon, isExpand);
		super.add((JPanel) this.listener);
		super.add(Box.createVerticalGlue());
		this.listeList.add(this.listener);
	}
}
