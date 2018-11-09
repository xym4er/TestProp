package com.drweb.chornyiya.api;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Hmac {
    private final long currentInterval;
    private final Hash hash;
    private final byte[] secret;

    public Hmac(Hash arg1, byte[] arg2, long arg3) {
        super();
        this.hash = arg1;
        this.secret = arg2;
        this.currentInterval = arg3;
    }

    public byte[] digest() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] v0 = ByteBuffer.allocate(8).putLong(this.currentInterval).array();
        Mac v1 = Mac.getInstance(this.hash.toString());
        v1.init(new SecretKeySpec(this.secret, "RAW"));
        return v1.doFinal(v0);
    }
}

