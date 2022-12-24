package com.example.bracket;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {


    private byte encryptionKey[] = {9, 43, 65, 33, 76, 88, 22, -34, -55, -102, 81, 3, 42, 85, -23, -48};
    private Cipher cipher, decipher;

    {
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (NoSuchPaddingException ex) {
            ex.printStackTrace();
        }
    }

    {
        try {
            decipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (NoSuchPaddingException ex) {
            ex.printStackTrace();
        }
    }

    private SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey, "AES");


    //Encryption and Decryption Methods
    public String AESEncryptionMethod(String string) {
        byte[] stringByte = string.getBytes();
        byte[] encryptedByte = new byte[string.length()];

        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encryptedByte = cipher.doFinal(stringByte);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        //Convert encryptedByte to string and return it
        String returnString = null;
        try {
            returnString = new String(encryptedByte, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return returnString;
    }

    public String AESDecryptionMethod(String string) throws UnsupportedEncodingException {
        byte[] EncryptedByte = string.getBytes("ISO-8859-1");
        String decryptedString = string;

        byte[] decryption;

        try {
            decipher.init(cipher.DECRYPT_MODE, secretKeySpec);
            decryption = decipher.doFinal(EncryptedByte);
            decryptedString = new String(decryption);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return decryptedString;

    }
}
