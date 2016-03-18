import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Andrii_Tkachuk
 * @since 12/26/2014
 */
public class ZipSteam {

    public static void zipFile(String inputFile, String zipFilePath) {
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFilePath)));
            zipOutputStream.putNextEntry(new ZipEntry(inputFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(zipOutputStream);
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {

            }
        }
    }
}
