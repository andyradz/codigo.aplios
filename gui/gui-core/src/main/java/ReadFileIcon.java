
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.AccessControlContext;
import java.security.AccessController;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

public class ReadFileIcon extends SecurityManager {
	public static Image iconToImage(final Icon icon) {

		return ((ImageIcon) icon).getImage();
	}

	public static void main(final String[] args) {

		// get current security context
		final AccessControlContext con = AccessController.getContext();

		// set the policy file as the system securuty policy
		// System.setProperty("java.security.policy", "file:/C:/java.policy");

		// create a security manager
		final ReadFileIcon sm = new ReadFileIcon();

		// set the system security manager
		System.setSecurityManager(sm);

		// perform the check
		// sm.checkPermission(new FilePermission("c:\\Documents and Settings", "read"), con);
		sm.checkPermission(new FilePermission(
			"d://saved.png", "read"), con);

		// print a message if we passed the check
		System.out.println("Allowed!");

		final File ff = Paths.get("d:/arialbd.ttf")
				.toFile();

		Icon icon = null;
		icon = FileSystemView.getFileSystemView()
				.getSystemIcon(ff);
		final Image image = ReadFileIcon.iconToImage(icon);

		// cast it to bufferedimage
		final BufferedImage buffered = (BufferedImage) image;

		try {
			// save to file
			final File outputfile = new File(
				"d://saved.png");
			ImageIO.write(buffered, "png", outputfile);
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
