package meat.gfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

/// misc tools
public class Util
{
	// a transform that does nothing; given to Graphics2D.drawImage; (idk if this is good practice)
	public static final AffineTransformOp IDENTITY_TRANSFORM = new AffineTransformOp(new AffineTransform(), AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

	// R=0, G=0, B=0, A=0
	public static final Color COLOR_CLEAR = new Color(0, 0, 0, 0);

	/**
	 * gets a BufferedImage from the resources folder
	 * NOTE: need to start path with /, where / is the resources folder.
	 *
	 * @param resPath the path to the image in relation to the resources folder
	 * @return the loaded image
	 */
	public static BufferedImage loadImage(String resPath)
	{
		try {
			return ImageIO.read(Util.class.getResourceAsStream(resPath));
		} catch (IOException e) {
			System.err.printf("Could not load BufferedImage from `%s`.\n", resPath);
			e.printStackTrace();

			return null;
		}
	}
}
