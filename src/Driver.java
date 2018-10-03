import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) throws IOException {
        LSB lsb = new LSB();
        BufferedImage img = lsb.getImageBuffer("img.bmp");
        ArrayList<int[]> pixel = lsb.printRBG(img);
        BufferedImage img2 = lsb.getImageBuffer("comp.bmp");
        ArrayList<int[]> pixel2 = lsb.printRBG(img2);
        String bCode = "";
        for(int i=0; i<pixel.size(); i++) {
        	if(pixel2.get(i)[0] != pixel.get(i)[0] && pixel2.get(i)[1] != pixel.get(i)[1] && pixel2.get(i)[2] != pixel.get(i)[2]) {
        		System.out.println("Original: " + pixel2.get(i)[0] + " " + pixel2.get(i)[1] + " " + pixel2.get(i)[2]);
        		System.out.println("Steg: " + pixel.get(i)[0] + " " + pixel.get(i)[1] + " " + pixel.get(i)[2]);
        		bCode += String.valueOf((pixel.get(i)[0]-pixel2.get(i)[0])) + String.valueOf((pixel.get(i)[1]-pixel2.get(i)[1])) + String.valueOf((pixel.get(i)[2]-pixel2.get(i)[2]));
        	}
        }
        System.out.println(bCode);
    }
}
