package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCode {
	private final String STR = "1234567890qwertyyuiopasdfghjklzxcvbnm"
			+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String[] fonts = {  "Small Fonts", "Jokerman","Adobe Gothic Std" };
	private int width = 70;
	private int height = 35;
	private int imageType = 1;
	private Color bgColor = new Color(255, 255, 255);
	private String text;
	private Random ran = new Random();

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getImageType() {
		return imageType;
	}

	public void setImageType(int imageType) {
		this.imageType = imageType;
	}

	private Font getFont() {
		int style = ran.nextInt(4);
		int size = ran.nextInt(5) + 24;
		return new Font(fonts[ran.nextInt(fonts.length)], style, size);
	}

	private char getChar() {
		return STR.charAt(ran.nextInt(STR.length()));
	}

	private Color getColor() {
		return new Color(ran.nextInt(150), ran.nextInt(150), ran.nextInt(150));
	}

	private BufferedImage createImage() {
		BufferedImage image = new BufferedImage(width, height, imageType);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		g2.setColor(getBgColor());
		g2.fillRect(0, 0, width, height);
		return image;
	}

	private void drawLine(BufferedImage image, int num) {
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		for (int i = 0; i < num; i++) {
			g2.setStroke(new BasicStroke(1.5f));
			g2.setColor(getColor());
			g2.drawLine(0, ran.nextInt(getHeight()), ran.nextInt(getWidth()), 0);
		}
	}

	public BufferedImage getImage(int num) {
		BufferedImage image = createImage();
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String str = new StringBuffer(String.valueOf(getChar())).toString();
			sb.append(str);
			float x = ((float) i * 1.0F * (float) width) / 4F;
			g2.setColor(getColor());
			g2.setFont(getFont());
			g2.drawString(str, x, height - 5);
		}
		text = sb.toString();
		drawLine(image, num);
		return image;
	}

	public  void output(BufferedImage image, OutputStream out)
			throws IOException {
		ImageIO.write(image, "JPEG", out);
	}

	public String getText() {
		return text;
	}

}
