package algorithm.haphman;

/**
 * @author Andrii_Tkachuk
 * @since 4/15/2015
 */
public class Demo {

    public static void main(String[] args) {
        String sourceText = "beep boop beer!";
        Haphman haphman = new Haphman(sourceText);
        haphman.compress();

        System.out.println("Size source bits: " + (sourceText.getBytes().length * 8));
        System.out.println("Size compressed bits: " + haphman.getCompressedBits().length());
    }
}
