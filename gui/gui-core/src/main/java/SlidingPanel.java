/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author root
 */
public class SlidingPanel extends JPanel implements StateListener {

	final int		EXPAND		= 1;
	final int		COLLAPSE	= 0;
	int				state		= this.COLLAPSE;
	JPanel			slideContainer;
	SlideAnimator	slider		= null;
	TitlePanel		titlePanel	= null;

	public SlidingPanel(final JComponent slideComponent, final String title, final Image imageIcon,
			final boolean isExpand) {

		this.slideContainer = new SlideContainer();
		setLayout(new BoxLayout(
			this, BoxLayout.Y_AXIS));
		setBorder(null);
		this.slider = new SlideAnimator(
			this.slideContainer, slideComponent);
		this.titlePanel = new TitlePanel(
			title, imageIcon, slideComponent.getPreferredSize().width + 15);
		add(this.titlePanel);
		add(this.slideContainer);

		this.slideContainer.setVisible(false);
		if (isExpand)
			toggleState();
	}

	@Override
	public void reset() {

		this.titlePanel.toggleState(false);
		this.slideContainer.setVisible(false);
		validate();
	}

	@Override
	public void toggleState() {

		if (this.state == this.COLLAPSE) {
			this.titlePanel.toggleState(true);
			this.slider.showAt();
			validate();
			this.state = this.EXPAND;
		}
		else {
			this.titlePanel.toggleState(false);
			this.slideContainer.setVisible(false);
			validate();
			this.state = this.COLLAPSE;
		}
	}
}
