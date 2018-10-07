import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class LSB {

    /**
     * Reads in the stego-object to attack
     * @param path File path to the stego-object
     * @return A BufferedImage representation of the stego-object
     * @throws IOException
     */
	protected BufferedImage get_buffered_image(String path) throws IOException {
        return ImageIO.read(new File(path));
	}

    /**
     * Performs an LSB attack on a given stego-object. Initially this was done by specifying likely areas of concealment
     * (corners of the image, first and last line of the image) and then converting the extracted LSB's to plain text and
     * visually inspecting the results. Through this we noticed that the hidden message was on the very bottom of the image, so we
     * adjusted the method to only read that line. We then worked down from the whole width in multiples of 8 until we had the
     * precise location of the hidden message. Hence the hard coded co-ordinate values, 223 and 639.
     * @param img Stego-object to attack represented as a BufferedImage in order to access the RGB channels
     * @return A Collection of least significant bit values for each RGb channel.
     */
	protected List<Integer> decode(BufferedImage img) {
        List<Integer> extracted_lsbs = new ArrayList<>();
        IntStream.iterate(223, j -> j >= 0, j -> j - 1)
                .forEach(j -> {
                    byte red_byte = (byte) new Color(img.getRGB(j, 639)).getRed();
                    byte green_byte = (byte) new Color(img.getRGB(j, 639)).getGreen();
                    byte blue_byte = (byte) new Color(img.getRGB(j, 639)).getBlue();
                    int lsb_red = red_byte & 0x1;
                    int lsb_green = green_byte & 0x1;
                    int lsb_blue = blue_byte & 0x1;
                    extracted_lsbs.addAll(Arrays.asList(lsb_red, lsb_green, lsb_blue));
                });
		return extracted_lsbs;
	}

    /**
     * Takes a binary string and converts it to plain text
     * @param target Binary string to convert
     * @return The converted string
     */
	protected String binary_to_text(String target){
        StringBuilder converted_text = new StringBuilder();
        Arrays.stream(target.split("(?<=\\G.{8})"))
                .forEach(s ->
                        converted_text.append((char) Integer.parseInt(s, 2)));
        return converted_text.toString();
    }
}