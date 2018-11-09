package com.drweb.chornyiya.api;

public enum Hash {
    SHA1("HMACSHA1");

    private String hash;

    private Hash(String hash) {
        this.hash = hash;
    }

    public String toString() {
        return this.hash;
    }
}

