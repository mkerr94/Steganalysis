import java.awt.image.BufferedImage;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        LSB lsb = new LSB();
        BufferedImage img = lsb.getImageBuffer();
    }
}
