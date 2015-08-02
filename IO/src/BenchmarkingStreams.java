import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Andrii_Tkachuk
 * @since 12/25/2014
 */
public class BenchmarkingStreams {

    private static final int BUFFER_SIZE = 1024;

    public static String readFileInputStreamWithoutBuffer(String fileName) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            int ch = -1;
            while ((ch = fileInputStream.read()) != -1) {
                result.write(ch);
            }
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return result.toString();
    }

    public static String readFileBufferedInputStreamWithoutBuffer(String fileName) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
            int ch = -1;
            while ((ch = bufferedInputStream.read()) != -1) {
                result.write(ch);
            }
        } finally {
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        }
        return result.toString();
    }

    public static String readFileBufferedInputStreamWithBuffer(String fileName) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        BufferedInputStream fileInputStream = null;
        try {
            fileInputStream = new BufferedInputStream(new FileInputStream(fileName));
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = -1;
            while ((len = fileInputStream.read(buffer)) != -1) {
                result.write(buffer, 0, len);
            }
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return result.toString();
    }

    public static String readFileChannel(String fileName) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        FileChannel fileChannel = null;
        try {
            fileChannel = new FileInputStream(fileName).getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                result.write(byteBuffer.array());
                byteBuffer.clear();
            }
        } finally {
            if (fileChannel != null) {
                fileChannel.close();
            }
        }
        return result.toString();
    }

    public static void runTestStreams(String fileName) throws IOException {
        long start = System.currentTimeMillis();
        BenchmarkingStreams.readFileInputStreamWithoutBuffer(fileName);
        System.out.println("Input stream without buffer: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        BenchmarkingStreams.readFileBufferedInputStreamWithoutBuffer(fileName);
        System.out.println("Buffered input stream without buffer: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        BenchmarkingStreams.readFileBufferedInputStreamWithBuffer(fileName);
        System.out.println("Buffered input stream with buffer: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        BenchmarkingStreams.readFileChannel(fileName);
        System.out.println("Channel: " + (System.currentTimeMillis() - start));
    }
}
