package com.manhpd;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class Pkcs7ForBouncyCastleApp {

    private final static IvParameterSpec emptyIvSpec = new IvParameterSpec(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00});

    private static final String AES_ALGO = "AES";

    private static final int AES_256_SIZE = 32;

    private static final String AES_ALGO_CIPHER = "AES/CBC/PKCS7Padding";

    public static void main(String[] args)
                                throws NoSuchPaddingException, BadPaddingException,
                                InvalidAlgorithmParameterException, NoSuchAlgorithmException,
                                IllegalBlockSizeException, ShortBufferException,
                                NoSuchProviderException, InvalidKeyException {
        String text = "Hello, world!";
        byte[] keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
                0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };
        byte[] byteEncryptedData = encrypt(keyBytes, text);
        String encryptedData = new String(byteEncryptedData);
        System.out.println("Encrypted data: " + encryptedData);

        String plainText = decrypt(keyBytes, byteEncryptedData);
        System.out.println("Decrypted data: " + plainText);
    }

    private static byte[] encrypt(byte[] keyBytes, String text)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException,
            InvalidAlgorithmParameterException, ShortBufferException,
            BadPaddingException, IllegalBlockSizeException {
        Security.addProvider(new BouncyCastleProvider());
        SecretKeySpec aesKey = new SecretKeySpec(keyBytes, AES_ALGO);

        final IvParameterSpec ivSpec = emptyIvSpec;
        final Cipher cipher = Cipher.getInstance(AES_ALGO_CIPHER, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivSpec);

        byte[] rawData = text.getBytes();
        byte[] cipherText = new byte[cipher.getOutputSize(rawData.length)];
        int ctLength = cipher.update(rawData, 0, rawData.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        System.out.println(new String(cipherText));
        System.out.println(ctLength);

        return cipherText;
    }

    private static String decrypt(byte[] keyBytes, byte[] cipherText)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Security.addProvider(new BouncyCastleProvider());
        SecretKeySpec aesKey = new SecretKeySpec(keyBytes, AES_ALGO);

        final IvParameterSpec ivSpec = emptyIvSpec;
        final Cipher cipher = Cipher.getInstance(AES_ALGO_CIPHER, "BC");
        cipher.init(Cipher.DECRYPT_MODE, aesKey, ivSpec);

        String plainText = new String(cipher.doFinal(cipherText),
                                      StandardCharsets.UTF_8);
        System.out.println(plainText);

        return plainText;
    }
}
