package com.drweb.chornyiya;

import com.drweb.chornyiya.api.Clock;
import org.apache.commons.codec.binary.Base32;

public class Main2 {


    public static void main(String[] args) {
    String str = new Totp(new Base32().encodeToString(hexToBytes("808d0c4d824b014166b3ca0cd37cbaca")), new Clock(0)).notNow((System.currentTimeMillis() - 0) / (((long)1000)) / (((long)300)));
        System.out.println(str);
    }

    private static final byte[] hexToBytes(String arg6) {
        byte[] v0 = new byte[arg6.length() / 2];
        int v1 = 0;
        while(v1 < arg6.length()) {
            if(v1 >= arg6.length()) {
            }
            else {
                v0[v1 / 2] = ((byte)((Character.digit(arg6.charAt(v1), 16) << 4) + Character.digit(arg6.charAt(v1 + 1), 16)));
                v1 += 2;
                continue;
            }

            return v0;
        }

        return v0;
    }

}