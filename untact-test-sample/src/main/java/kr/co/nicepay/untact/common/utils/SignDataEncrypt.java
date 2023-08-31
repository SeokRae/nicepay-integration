package kr.co.nicepay.untact.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class SignDataEncrypt {

    public static final String ALGORITHM = "SHA-256";

    private SignDataEncrypt() {
    }

    public static String encryptSHA256(String hashParam) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(hashParam.getBytes());
            byte[] md5Sig = md.digest();
            StringBuilder sb = new StringBuilder();

            for (byte b : md5Sig) {
                String hex = Integer.toHexString(255 & b);
                if (hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}