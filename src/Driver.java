import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        LSB lsb = new LSB();

        StringBuilder extracted_lsbs_as_string = new StringBuilder();
        lsb.decode(lsb.get_buffered_image("img.bmp")).forEach(r -> extracted_lsbs_as_string.append(r));

        System.out.println(lsb.binary_to_text(extracted_lsbs_as_string.toString()));
    }
}
