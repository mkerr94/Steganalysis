import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class LSB {

	BufferedImage getImageBuffer(String path) throws IOException {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return img;
	}

	byte[] extractHiddenBytes(BufferedImage img) {
		final int HIDDEN_FILE_SIZE = 83;
		byte[] hiddenBytes = new byte[HIDDEN_FILE_SIZE];

		return hiddenBytes;
	}

	ArrayList<int[]> printRBG(BufferedImage img) {
		ArrayList<int[]> pixel = new ArrayList<>();

		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				pixel.add(img.getRaster().getPixel(x, y, new int[3]));
//					System.out
//							.println(pixel[0] + " - " + pixel[1] + " - " + pixel[2] + " - " + (img.getWidth() * y + x));
				
			}
		}
		return pixel;
	}
}
