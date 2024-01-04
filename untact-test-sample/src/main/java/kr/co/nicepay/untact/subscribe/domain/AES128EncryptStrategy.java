package kr.co.nicepay.untact.subscribe.domain;

import io.micrometer.common.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES128EncryptStrategy implements EncryptPolicy {
    private static final String CIPHER_AES128_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String ENCRYPTION_ALGORITHM = "AES";

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public String encrypt(String data, String key) {
        if (StringUtils.isBlank(data)) {
            throw new IllegalArgumentException("암호화할 데이터가 존재하지 않습니다.");
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.substring(0, 16).getBytes(), ENCRYPTION_ALGORITHM);

            Cipher cipher = Cipher.getInstance(CIPHER_AES128_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encrypted = cipher.doFinal(data.getBytes());

            return bytesToHex(encrypted);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}