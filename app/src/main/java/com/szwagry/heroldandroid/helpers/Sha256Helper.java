package com.szwagry.heroldandroid.helpers;

import android.util.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by ragnar on 10/3/15.
 */
public class Sha256Helper {

    private static byte[] getHashTable(String password) {
        MessageDigest digest=null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        digest.reset();
        return digest.digest(password.getBytes());
    }
    private static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
    }

    public static String getHash(String password){
        byte[] digest = getHashTable(password);
        return bin2hex(digest);
    }

    public static String getSalt(){
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        return bin2hex(salt);
    }


}
