import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class LSB {

	protected BufferedImage get_buffered_image(String path) throws IOException {
        return ImageIO.read(new File(path));
	}

	protected List<Integer> decode(BufferedImage img) {
        List<Integer> extracted_lsbs = new ArrayList<>();
        IntStream.iterate(216 - 1, j -> j >= 0, j -> j - 1)
                .mapToObj(j -> new Color(img.getRGB(j, 639)))
                .forEach(current_pixel -> {
                    byte red_byte = (byte) current_pixel.getRed();
                    byte green_byte = (byte) current_pixel.getGreen();
                    byte blue_byte = (byte) current_pixel.getBlue();
                    int lsb_red = red_byte & 0x1;
                    int lsb_green = green_byte & 0x1;
                    int lsb_blue = blue_byte & 0x1;
                    extracted_lsbs.addAll(Arrays.asList(lsb_red, lsb_green, lsb_blue));
        });
		return extracted_lsbs;
	}

	protected String binary_to_text(String target){
        StringBuilder converted_text = new StringBuilder();
        Arrays.stream(target.split("(?<=\\G.{8})"))
                .forEach(s ->
                        converted_text.append((char) Integer.parseInt(s, 2)));
        return converted_text.toString();
    }
}
