package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

/// represents a font that you can use to render a String
public class DrawableFont
{
	public static final DrawableFont // idk why, but you need / in front of file name
		CAPS_57 = new DrawableFont("/font_57_caps.png", " ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1);

	final BufferedImage[] CHARS;        // all the diff chars in separate images
	final String REPRESENTATIVE_STRING; // the characters that the loaded spritesheet represents
	final int CHAR_WIDTH, CHAR_HEIGHT;  // the width, height of each individual character
	final int SPACING;                  // how many pixels to put in between each character when rendering

	private DrawableFont(String resPath, String representativeString, int spacing)
	{
		CHARS = new BufferedImage[representativeString.length()];
		this.REPRESENTATIVE_STRING = representativeString;
		this.SPACING = spacing;

		BufferedImage sheet = Util.loadImage(resPath);

		CHAR_WIDTH = sheet.getWidth() / representativeString.length();
		CHAR_HEIGHT = sheet.getHeight();

		for (int i = 0; i < representativeString.length(); i += 1)
			CHARS[i] = sheet.getSubimage(i * CHAR_WIDTH, 0, CHAR_WIDTH, CHAR_HEIGHT);
	}

	/**
	 * renders given string as BufferedImage.
	 * NOTE: does not pre-check to see if the string can be rendered with font
	 * you'll get an ArrayIndexOutOfBoundsException if you try to render an unrenderable string.
	 *
	 * @param string the string to render
	 * @return the rendered BufferedImage
	 */
	public BufferedImage renderString(String string)
	{
		BufferedImage rendered = new BufferedImage(CHAR_WIDTH * string.length() + SPACING * Math.max(0, string.length() - 1), CHAR_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) rendered.getGraphics();

		for (int i = 0; i < string.length(); i += 1) {
			g2d.drawImage(CHARS[REPRESENTATIVE_STRING.indexOf(string.charAt(i))], Util.IDENTITY_TRANSFORM, i * (CHAR_WIDTH + SPACING), 0);
		}

		return rendered;
	}
}
