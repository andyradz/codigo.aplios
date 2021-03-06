

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;

public class SlideAnimator extends Object {

	protected static final int		ANIMATION_TIME		= 500;
	protected static final int		DISPOSE_TIME		= 10000;
	protected static final float	ANIMATION_TIME_F	= SlideAnimator.ANIMATION_TIME;
	protected static final int		ANIMATION_DELAY		= 50;

	JPanel			container;
	JComponent		contents;
	AnimatingSheet	animatingSheet;
	Rectangle		desktopBounds;
	Dimension		tempWindowSize;
	Timer			animationTimer;
	long			animationStart;
	boolean			visible	= true;

	public SlideAnimator() {

		initDesktopBounds();
	}

	public SlideAnimator(final JPanel panel, final JComponent contents) {

		this();
		this.container = panel;
		this.contents = contents;
		this.animatingSheet = new AnimatingSheet();
	}

	public void Dispose() {

		this.visible = false;
	}

	protected void initDesktopBounds() {

		final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.desktopBounds = env.getMaximumWindowBounds();
	}

	public void setContents() {

		this.visible = true;
		final JWindow tempWindow = new JWindow();
		tempWindow.getContentPane()
				.add(this.contents);
		tempWindow.pack();
		this.tempWindowSize = tempWindow.getSize();
		tempWindow.getContentPane()
				.removeAll();
		this.animatingSheet.setSource(this.contents);
		this.container.removeAll();
		this.container.add(this.animatingSheet);
	}

	public void showAt() {

		setContents();
		final ActionListener animationLogic = e -> {
			final long elapsed = System.currentTimeMillis() - SlideAnimator.this.animationStart;
			if (elapsed > SlideAnimator.ANIMATION_TIME) {
				SlideAnimator.this.container.removeAll();
				SlideAnimator.this.container.add(SlideAnimator.this.contents);
				SlideAnimator.this.container.revalidate();
				SlideAnimator.this.animationTimer.stop();
			}
			else {
				final float progress = elapsed / SlideAnimator.ANIMATION_TIME_F;
				int animatingHeight = (int) (progress * SlideAnimator.this.tempWindowSize.getHeight());
				animatingHeight = Math.max(animatingHeight, 1);
				SlideAnimator.this.animatingSheet.setAnimatingHeight(animatingHeight);
				SlideAnimator.this.container.setVisible(true);
				SlideAnimator.this.container.repaint();
				SlideAnimator.this.container.revalidate();
			}
		};
		this.animationTimer = new Timer(
			SlideAnimator.ANIMATION_DELAY, animationLogic);
		this.animationStart = System.currentTimeMillis();
		this.animationTimer.start();
	}

	@SuppressWarnings("serial")
	class AnimatingSheet extends JPanel {

		Dimension		animatingSize	= new Dimension(
			0, 1);
		JComponent		source;
		BufferedImage	offscreenImage;

		public AnimatingSheet() {

			super();
			setOpaque(true);
		}

		public void setSource(final JComponent source) {

			this.source = source;
			this.animatingSize.width = source.getWidth();
			makeOffscreenImage(source);
		}

		public void setAnimatingHeight(final int height) {

			this.animatingSize.height = height;
			setSize(this.animatingSize);
		}

		private void makeOffscreenImage(final JComponent source) {

			final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			final GraphicsConfiguration gfxConfig = ge.getDefaultScreenDevice()
					.getDefaultConfiguration();
			this.offscreenImage = gfxConfig.createCompatibleImage(source.getWidth(), source.getHeight());
			final Graphics2D offscreenGraphics = (Graphics2D) this.offscreenImage.getGraphics();
			// windows workaround
			offscreenGraphics.setColor(source.getBackground());
			offscreenGraphics.fillRect(0, 0, source.getWidth(), source.getHeight());
			// paint from source to offscreen buffer
			source.paint(offscreenGraphics);
		}

		@Override
		public Dimension getPreferredSize() {

			return this.animatingSize;
		}

		@Override
		public Dimension getMinimumSize() {

			return this.animatingSize;
		}

		@Override
		public Dimension getMaximumSize() {

			return this.animatingSize;
		}

		@Override
		public void update(final Graphics g) {

			// override to eliminate flicker from
			// unneccessary clear
			paint(g);
		}

		@Override
		public void paint(final Graphics g) {

			// get the top-most n pixels of source and
			// paint them into g, where n is height
			// (different from sheet example, which used bottom-most)
			final BufferedImage fragment = this.offscreenImage.getSubimage(0, 0, this.source.getWidth(),
					this.animatingSize.height);
			g.drawImage(fragment, 0, 0, this);
		}
	}
}