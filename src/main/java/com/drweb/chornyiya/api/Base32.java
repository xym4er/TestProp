package com.drweb.chornyiya.api;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Locale;

public class Base32 {
    public class DecodingException extends Exception {
        public DecodingException(String arg1) {
            super(arg1);
        }
    }

    private String ALPHABET;
    private HashMap CHAR_MAP;
    private char[] DIGITS;
    private static Base32 INSTANCE;
    private int MASK;
    private static SecureRandom RANDOM;
    private int SHIFT;

    static {
        Base32.RANDOM = new SecureRandom();
        Base32.INSTANCE = new Base32("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567");
    }

    protected Base32(String arg4) {
        super();
        this.ALPHABET = arg4;
        this.DIGITS = this.ALPHABET.toCharArray();
        this.MASK = this.DIGITS.length - 1;
        this.SHIFT = Integer.numberOfTrailingZeros(this.DIGITS.length);
        this.CHAR_MAP = new HashMap();
        int v4;
        for(v4 = 0; v4 < this.DIGITS.length; ++v4) {
            this.CHAR_MAP.put(Character.valueOf(this.DIGITS[v4]), Integer.valueOf(v4));
        }
    }

    public static byte[] decode(String arg1) throws DecodingException {
        return Base32.getInstance().decodeInternal(arg1);
    }

    protected byte[] decodeInternal(String encoded) throws Base32.DecodingException {
        encoded = encoded.trim().replaceAll("-", "").replaceAll(" ", "");
        encoded = encoded.replaceFirst("[=]*$", "");
        encoded = encoded.toUpperCase(Locale.US);
        if (encoded.length() == 0) {
            return new byte[0];
        } else {
            int encodedLength = encoded.length();
            int outLength = encodedLength * this.SHIFT / 8;
            byte[] result = new byte[outLength];
            int buffer = 0;
            int next = 0;
            int bitsLeft = 0;
            char[] arr$ = encoded.toCharArray();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                char c = arr$[i$];
                if (!this.CHAR_MAP.containsKey(c)) {
                    throw new Base32.DecodingException("Illegal character: " + c);
                }

                buffer <<= this.SHIFT;
                buffer |= (Integer)this.CHAR_MAP.get(c) & this.MASK;
                bitsLeft += this.SHIFT;
                if (bitsLeft >= 8) {
                    result[next++] = (byte)(buffer >> bitsLeft - 8);
                    bitsLeft -= 8;
                }
            }

            return result;
        }
    }

    static Base32 getInstance() {
        return Base32.INSTANCE;
    }
}

