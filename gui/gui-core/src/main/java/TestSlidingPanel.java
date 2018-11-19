/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 *
 * @author root
 */
public class TestSlidingPanel extends JPanel {

	public TestSlidingPanel() {

		final SlidePaneFactory factory = SlidePaneFactory.getInstance();
		BookForm bookForm = new BookForm();
		factory.add(bookForm, "Slide1", new ImageIcon(
			"images/title.png").getImage(), true);

		bookForm = new BookForm();
		factory.add(bookForm, "Slide2", new ImageIcon(
			"images/title.png").getImage());

		bookForm = new BookForm();
		factory.add(bookForm, "Slide3", new ImageIcon(
			"images/title.png").getImage(), false);

		add(factory);

	}

	public static void main(final String s[]) {

		final JFrame frame = new JFrame();
		frame.setSize(330, 400);

		// frame.setLayout(new FlowLayout());
		final JScrollPane pane = new JScrollPane();
		pane.setViewportView(new TestSlidingPanel());
		frame.add(pane);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
