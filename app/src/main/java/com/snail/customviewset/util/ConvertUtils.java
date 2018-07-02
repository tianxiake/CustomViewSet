package com.snail.customviewset.util;

/**
 * @author yongjie on 2017/12/9.
 * 数据转换工具类
 */

public class ConvertUtils {

    private static final char[] digits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
    public static final byte[] emptyBytes = new byte[0];

    public static String byte2HexStr(byte paramByte) {
        int i = digits[(paramByte & 0xF)];
        paramByte = (byte) (paramByte >>> 4);
        return new String(new char[]{digits[(paramByte & 0xF)], (char) i});
    }

    public static String bytes2HexStr(byte[] paramArrayOfByte) {
        if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
            return null;
        }
        char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
        int i = 0;
        for (; ; ) {
            if (i >= paramArrayOfByte.length) {
                return new String(arrayOfChar);
            }
            int j = paramArrayOfByte[i];
            arrayOfChar[(i * 2 + 1)] = digits[(j & 0xF)];
            j = (byte) (j >>> 4);
            arrayOfChar[(i * 2 + 0)] = digits[(j & 0xF)];
            i += 1;
        }
    }

    public static byte char2Byte(char paramChar) {
        if ((paramChar >= '0') && (paramChar <= '9')) {
            return (byte) (paramChar - '0');
        }
        if ((paramChar >= 'a') && (paramChar <= 'f')) {
            return (byte) (paramChar - 'a' + 10);
        }
        if ((paramChar >= 'A') && (paramChar <= 'F')) {
            return (byte) (paramChar - 'A' + 10);
        }
        return 0;
    }

    public static byte hexStr2Byte(String paramString) {
        byte b2 = 0;
        byte b1 = b2;
        if (paramString != null) {
            b1 = b2;
            if (paramString.length() == 1) {
                b1 = char2Byte(paramString.charAt(0));
            }
        }
        return b1;
    }

    public static byte[] hexStr2Bytes(String paramString) {
        if ((paramString == null) || (paramString.equals(""))) {
            return emptyBytes;
        }
        byte[] arrayOfByte = new byte[paramString.length() / 2];
        int i = 0;
        for (; ; ) {
            if (i >= arrayOfByte.length) {
                return arrayOfByte;
            }
            char c1 = paramString.charAt(i * 2);
            char c2 = paramString.charAt(i * 2 + 1);
            arrayOfByte[i] = ((byte) (char2Byte(c1) * 16 + char2Byte(c2)));
            i += 1;
        }
    }

    public static byte[] intToBytes(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }


    public static byte[] longToBytes(long values) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = 64 - (i + 1) * 8;
            buffer[i] = (byte) ((values >> offset) & 0xff);
        }
        return buffer;
    }

}
