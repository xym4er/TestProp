package com.drweb.chornyiya;

import com.drweb.chornyiya.api.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class Totp {
    private final Clock clock;
    private final String secret;

    public Totp(String arg1, Clock arg2) {
        super();
        this.secret = arg1;
        this.clock = arg2;
    }

    private int bytesToInt(byte[] arg4) {
        int v0 = arg4[arg4.length - 1] & 15;
        return (arg4[v0 + 3] & 0xFF | ((arg4[v0] & 0x7F) << 24 | (arg4[v0 + 1] & 0xFF) << 16 | (arg4[v0 + 2] & 0xFF) << 8)) % Digits.SIX.getValue();
    }

    private int hash(String arg4, long arg5) {
        byte[] v4_3;
        byte[] v0 = new byte[0];
        try {
            v4_3 = new Hmac(Hash.SHA1, Base32.decode(arg4), arg5).digest();
            return this.bytesToInt(v4_3);
        }
        catch(Base32.DecodingException v4) {
            v4.printStackTrace();
        }
        catch(InvalidKeyException v4_1) {
            v4_1.printStackTrace();
        }
        catch(NoSuchAlgorithmException v4_2) {
            v4_2.printStackTrace();
        }

        v4_3 = v0;
    label_17:
        return this.bytesToInt(v4_3);
    }

    private String leftPadding(int arg4) {
        return String.format("%06d", Integer.valueOf(arg4));
    }

    public String notNow(long arg2) {
        return this.leftPadding(this.hash(this.secret, arg2));
    }
}

