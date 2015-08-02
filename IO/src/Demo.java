import java.io.IOException;

/**
 * @author Andrii_Tkachuk
 * @since 12/26/2014
 */
public class Demo {

    private static final String FILE_NAME = "benchmarking_streams.txt";
    private static final String ZIP_FILE_PATH = "archive.zip";

    public static void main(String[] args) throws IOException {
        BenchmarkingStreams.runTestStreams(FILE_NAME);
        ZipSteam.zipFile(FILE_NAME, ZIP_FILE_PATH);
    }

}
