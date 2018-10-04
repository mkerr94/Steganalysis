import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class LSB {
    public static void main(String[] args) throws IOException {
        LSB lsb = new LSB();
        List<Integer> result = lsb.decode(lsb.getImageBuffer("img.bmp"));
        lsb.displayLSBS(result);
    }
	BufferedImage getImageBuffer(String path) throws IOException {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return img;
	}

	List<Integer> decode(BufferedImage img) {
        List<Integer> extracted_lsbs = new ArrayList<>();
        for (int j = img.getWidth()-1; j >= 0; j--) {
            Color current_pixel = new Color(img.getRGB(j, 639));
            byte red_byte = (byte) current_pixel.getRed();
            byte green_byte = (byte) current_pixel.getGreen();
            byte blue_byte = (byte) current_pixel.getBlue();
            int lsb_red = red_byte & 0x1;
            int lsb_green = green_byte & 0x1;
            int lsb_blue = blue_byte & 0x1;
            extracted_lsbs.add(lsb_red);
            extracted_lsbs.add(lsb_green);
            extracted_lsbs.add(lsb_blue);
        }
		return extracted_lsbs;
	}

    void displayLSBS(List<Integer> integers) {
        integers.forEach(System.out::print);
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
