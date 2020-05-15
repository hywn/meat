package gfx;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Util
{
	public static final AffineTransformOp IDENTITY_TRANSFORM = new AffineTransformOp(new AffineTransform(), AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

	public static BufferedImage loadImage(String resPath)
	{
		System.out.println("hi");
		try {
			return ImageIO.read(Util.class.getResourceAsStream(resPath));
		} catch (IOException e) {
			System.err.printf("Could not load BufferedImage from `%s`.\n", resPath);
			e.printStackTrace();

			return null;
		}
	}
}
