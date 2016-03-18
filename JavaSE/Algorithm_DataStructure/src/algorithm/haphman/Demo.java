package algorithm.haphman;

import java.io.UnsupportedEncodingException;

/**
 * @author Andrii_Tkachuk
 * @since 4/15/2015
 */
public class Demo {

    private final static String BINARY_FORMAT = "00000000";

    public static void main(String[] args) {
        String text = "beep boop beer!";
        Haphman haphman = new Haphman(text);
        haphman.compress();
        displayBinaryString(text);
        System.out.println(haphman.getBinaryString());
    }

    private static void displayBinaryString(String text) {
        try {
            byte[] infoBin = text.getBytes("UTF-8");
            for (byte b : infoBin) {
                String oneByte = Integer.toBinaryString(b);
                System.out.print((BINARY_FORMAT + oneByte).substring(oneByte.length()) + " ");
            }
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
