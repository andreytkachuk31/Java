package algorithm.md5;

import java.math.BigInteger;

/**
 * MD5 - https://ru.wikipedia.org/wiki/MD5, http://dml.compkaluga.ru/forum/index.php?showtopic=24657
 *
 * @author Andrii_Tkachuk
 * @since 11.05.2016
 */
public class MD5 {

    private static final int[] INIT_VECTOR = new int[]{0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476};

    private static final int[] SHIFT_AMTS = {
            7, 12, 17, 22,
            5, 9, 14, 20,
            4, 11, 16, 23,
            6, 10, 15, 21
    };

    private static final int[] TABLE_T = new int[64];

    static {
        for (int i = 0; i < 64; i++) {
            TABLE_T[i] = (int) (long) ((1L << 32) * Math.abs(Math.sin(i + 1)));
        }
    }

    public void encode(byte[] message) {
        int messageLenBytes = message.length;
        int numBlocks = ((messageLenBytes + 8) >>> 6) + 1; // ((messageLenBytes + 8) / 64) + 1
        int totalLen = numBlocks << 6; // numBlocks * 64

        message = flowEqualization(message, totalLen, messageLenBytes); //step 1
        message = addLengthMessage(message, totalLen, messageLenBytes); //step 2
        int[] initVector = initBufferAndCalculate(message, numBlocks); // step 3, 4
        byte[] md5Hash = getMd5Hash(initVector); //step 5

        System.out.println(toHexString(md5Hash));
    }

    /**
     * Шаг 1: Выравнивание потока
     */
    private byte[] flowEqualization(byte[] message, int totalLen, int messageLenBytes) {
        byte[] result = new byte[totalLen];
        byte[] paddingBytes = new byte[totalLen - messageLenBytes];
        paddingBytes[0] = (byte) 0x80;

        System.arraycopy(message, 0, result, 0, messageLenBytes);
        System.arraycopy(paddingBytes, 0, result, messageLenBytes, paddingBytes.length);

        return result;
    }

    /**
     * Шаг 2: Добавление длины сообщения
     */
    private byte[] addLengthMessage(byte[] message, int totalLen, int messageLenBytes) {
        long messageLenBits = (long) messageLenBytes << 3; // messageLenBytes * 8
        for (int i = 0; i < 8; i++) {
            message[totalLen - 8 + i] = (byte) messageLenBits;
            messageLenBits >>>= 8;
        }
        return message;
    }

    /**
     * Шаг 3 и 4: Инициализация буфера и вычисление в цикле
     */
    private int[] initBufferAndCalculate(byte[] message, int numBlocks) {
        int a = INIT_VECTOR[0];
        int b = INIT_VECTOR[1];
        int c = INIT_VECTOR[2];
        int d = INIT_VECTOR[3];

        for (int i = 0; i < numBlocks; i++) {
            int originalA = a;
            int originalB = b;
            int originalC = c;
            int originalD = d;

            int[] buffer = devideBlock16(message, i);

            /* Этап 1 */
            a = stageF(a, b, c, d, buffer[0], TABLE_T[0], SHIFT_AMTS[0]);
            d = stageF(d, a, b, c, buffer[1], TABLE_T[1], SHIFT_AMTS[1]);
            c = stageF(c, d, a, b, buffer[2], TABLE_T[2], SHIFT_AMTS[2]);
            b = stageF(b, c, d, a, buffer[3], TABLE_T[3], SHIFT_AMTS[3]);

            a = stageF(a, b, c, d, buffer[4], TABLE_T[4], SHIFT_AMTS[0]);
            d = stageF(d, a, b, c, buffer[5], TABLE_T[5], SHIFT_AMTS[1]);
            c = stageF(c, d, a, b, buffer[6], TABLE_T[6], SHIFT_AMTS[2]);
            b = stageF(b, c, d, a, buffer[7], TABLE_T[7], SHIFT_AMTS[3]);

            a = stageF(a, b, c, d, buffer[8], TABLE_T[8], SHIFT_AMTS[0]);
            d = stageF(d, a, b, c, buffer[9], TABLE_T[9], SHIFT_AMTS[1]);
            c = stageF(c, d, a, b, buffer[10], TABLE_T[10], SHIFT_AMTS[2]);
            b = stageF(b, c, d, a, buffer[11], TABLE_T[11], SHIFT_AMTS[3]);

            a = stageF(a, b, c, d, buffer[12], TABLE_T[12], SHIFT_AMTS[0]);
            d = stageF(d, a, b, c, buffer[13], TABLE_T[13], SHIFT_AMTS[1]);
            c = stageF(c, d, a, b, buffer[14], TABLE_T[14], SHIFT_AMTS[2]);
            b = stageF(b, c, d, a, buffer[15], TABLE_T[15], SHIFT_AMTS[3]);

            /* Этап 2 */
            a = stageG(a, b, c, d, buffer[1], TABLE_T[16], SHIFT_AMTS[4]);
            d = stageG(d, a, b, c, buffer[6], TABLE_T[17], SHIFT_AMTS[5]);
            c = stageG(c, d, a, b, buffer[11], TABLE_T[18], SHIFT_AMTS[6]);
            b = stageG(b, c, d, a, buffer[0], TABLE_T[19], SHIFT_AMTS[7]);

            a = stageG(a, b, c, d, buffer[5], TABLE_T[20], SHIFT_AMTS[4]);
            d = stageG(d, a, b, c, buffer[10], TABLE_T[21], SHIFT_AMTS[5]);
            c = stageG(c, d, a, b, buffer[15], TABLE_T[22], SHIFT_AMTS[6]);
            b = stageG(b, c, d, a, buffer[4], TABLE_T[23], SHIFT_AMTS[7]);

            a = stageG(a, b, c, d, buffer[9], TABLE_T[24], SHIFT_AMTS[4]);
            d = stageG(d, a, b, c, buffer[14], TABLE_T[25], SHIFT_AMTS[5]);
            c = stageG(c, d, a, b, buffer[3], TABLE_T[26], SHIFT_AMTS[6]);
            b = stageG(b, c, d, a, buffer[8], TABLE_T[27], SHIFT_AMTS[7]);

            a = stageG(a, b, c, d, buffer[13], TABLE_T[28], SHIFT_AMTS[4]);
            d = stageG(d, a, b, c, buffer[2], TABLE_T[29], SHIFT_AMTS[5]);
            c = stageG(c, d, a, b, buffer[7], TABLE_T[30], SHIFT_AMTS[6]);
            b = stageG(b, c, d, a, buffer[12], TABLE_T[31], SHIFT_AMTS[7]);

            /* Этап 3 */
            a = stageH(a, b, c, d, buffer[5], TABLE_T[32], SHIFT_AMTS[8]);
            d = stageH(d, a, b, c, buffer[8], TABLE_T[33], SHIFT_AMTS[9]);
            c = stageH(c, d, a, b, buffer[11], TABLE_T[34], SHIFT_AMTS[10]);
            b = stageH(b, c, d, a, buffer[14], TABLE_T[35], SHIFT_AMTS[11]);

            a = stageH(a, b, c, d, buffer[1], TABLE_T[36], SHIFT_AMTS[8]);
            d = stageH(d, a, b, c, buffer[4], TABLE_T[37], SHIFT_AMTS[9]);
            c = stageH(c, d, a, b, buffer[7], TABLE_T[38], SHIFT_AMTS[10]);
            b = stageH(b, c, d, a, buffer[10], TABLE_T[39], SHIFT_AMTS[11]);

            a = stageH(a, b, c, d, buffer[13], TABLE_T[40], SHIFT_AMTS[8]);
            d = stageH(d, a, b, c, buffer[0], TABLE_T[41], SHIFT_AMTS[9]);
            c = stageH(c, d, a, b, buffer[3], TABLE_T[42], SHIFT_AMTS[10]);
            b = stageH(b, c, d, a, buffer[6], TABLE_T[43], SHIFT_AMTS[11]);

            a = stageH(a, b, c, d, buffer[9], TABLE_T[44], SHIFT_AMTS[8]);
            d = stageH(d, a, b, c, buffer[12], TABLE_T[45], SHIFT_AMTS[9]);
            c = stageH(c, d, a, b, buffer[15], TABLE_T[46], SHIFT_AMTS[10]);
            b = stageH(b, c, d, a, buffer[2], TABLE_T[47], SHIFT_AMTS[11]);

            /* Этап 4 */
            a = stageI(a, b, c, d, buffer[0], TABLE_T[48], SHIFT_AMTS[12]);
            d = stageI(d, a, b, c, buffer[7], TABLE_T[49], SHIFT_AMTS[13]);
            c = stageI(c, d, a, b, buffer[14], TABLE_T[50], SHIFT_AMTS[14]);
            b = stageI(b, c, d, a, buffer[5], TABLE_T[51], SHIFT_AMTS[15]);

            a = stageI(a, b, c, d, buffer[12], TABLE_T[52], SHIFT_AMTS[12]);
            d = stageI(d, a, b, c, buffer[3], TABLE_T[53], SHIFT_AMTS[13]);
            c = stageI(c, d, a, b, buffer[10], TABLE_T[54], SHIFT_AMTS[14]);
            b = stageI(b, c, d, a, buffer[1], TABLE_T[55], SHIFT_AMTS[15]);

            a = stageI(a, b, c, d, buffer[8], TABLE_T[56], SHIFT_AMTS[12]);
            d = stageI(d, a, b, c, buffer[15], TABLE_T[57], SHIFT_AMTS[13]);
            c = stageI(c, d, a, b, buffer[6], TABLE_T[58], SHIFT_AMTS[14]);
            b = stageI(b, c, d, a, buffer[13], TABLE_T[59], SHIFT_AMTS[15]);

            a = stageI(a, b, c, d, buffer[4], TABLE_T[60], SHIFT_AMTS[12]);
            d = stageI(d, a, b, c, buffer[11], TABLE_T[61], SHIFT_AMTS[13]);
            c = stageI(c, d, a, b, buffer[2], TABLE_T[62], SHIFT_AMTS[14]);
            b = stageI(b, c, d, a, buffer[9], TABLE_T[63], SHIFT_AMTS[15]);

            a += originalA;
            b += originalB;
            c += originalC;
            d += originalD;
        }

        return new int[]{a, b, c, d};
    }

    /**
     * Шаг 5: Результат вычислений
     */
    private byte[] getMd5Hash(int[] initVector) {
        byte[] md5 = new byte[16];
        int count = 0;
        for (int n : initVector) {
            for (int j = 0; j < 4; j++) {
                md5[count++] = (byte) n;
                n >>>= 8;
            }
        }
        return md5;
    }

    private int[] devideBlock16(byte[] message, int i) {
        int[] buffer = new int[16];
        int index = i << 6; // index * 32
        for (int j = 0; j < 64; j++, index++) {
            buffer[j >>> 2] = (message[index] << 24) | (buffer[j >>> 2] >>> 8);
        }
        return buffer;
    }

    /* [abcd k i s] a = b + ((a + F(b,c,d) + buffer[k] + T[i]) <<< s). */
    private int stageF(int a, int b, int c, int d, int x, int t, int s) {
        a = a + f(b, c, d) + x + t;
        a = (a << s | a >>> -s);
        return a + b;
    }

    /* [abcd k i s] a = b + ((a + G(b,c,d) + buffer[k] + T[i]) <<< s). */
    private int stageG(int a, int b, int c, int d, int x, int t, int s) {
        a = a + g(b, c, d) + x + t;
        a = (a << s | a >>> -s);
        return a + b;
    }

    /* [abcd k i s] a = b + ((a + H(b,c,d) + buffer[k] + T[i]) <<< s). */
    private int stageH(int a, int b, int c, int d, int x, int t, int s) {
        a = a + h(b, c, d) + x + t;
        a = (a << s | a >>> -s);
        return a + b;
    }

    /* [abcd k i s] a = b + ((a + I(b,c,d) + buffer[k] + T[i]) <<< s). */
    private int stageI(int a, int b, int c, int d, int x, int t, int s) {
        a = a + i(b, c, d) + x + t;
        a = (a << s | a >>> -s);
        return a + b;
    }

    private int f(int x, int y, int z) {
        return (x & y) | (~x & z);
    }

    private int g(int x, int y, int z) {
        return (x & z) | (~z & y);
    }

    private int h(int x, int y, int z) {
        return x ^ y ^ z;
    }

    private int i(int x, int y, int z) {
        return y ^ (~z | x);
    }

    public String toHexString(byte[] bytes) {
        BigInteger bigInt = new BigInteger(1, bytes);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static void main(String[] args) {
        new MD5().encode("habrahabr".getBytes());
    }
}
