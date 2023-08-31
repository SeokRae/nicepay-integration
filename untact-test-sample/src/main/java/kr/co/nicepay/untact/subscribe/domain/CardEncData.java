package kr.co.nicepay.untact.subscribe.domain;

import io.micrometer.common.util.StringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Getter
@ToString
public class CardEncData {
    private static final String CIPHER_AES128_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String CIPHER_AES256_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final int KEY_LENGTH = 16;
    private final String encData;

    @Builder
    public CardEncData(
            String cardNo, String expYear, String expMonth, String idNo, String cardPw, EncDataType encDataType, String merchantSecretKey
    ) {
        CardEncDataValidator.validation(cardNo, expYear, expMonth, idNo, cardPw);
        String originData = makePlainText(cardNo, expYear, expMonth, idNo, cardPw);
        if (EncDataType.isAES258(encDataType)) {
            this.encData = encryptAES256(originData, merchantSecretKey.getBytes(StandardCharsets.UTF_8), merchantSecretKey);
        } else {
            this.encData = encryptAES128(originData, merchantSecretKey.substring(0, 16).getBytes(StandardCharsets.UTF_8));
        }
    }

    public static String encryptAES128(String originData, byte[] secretKey) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, ENCRYPTION_ALGORITHM);

            Cipher cipher = Cipher.getInstance(CIPHER_AES128_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encrypted = cipher.doFinal(originData.getBytes());

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

    public static String encryptAES256(String value, byte[] bytes, String key) {
        if (StringUtils.isBlank(value)) {
            return Strings.EMPTY;
        }
        try {
            SecretKeySpec sKeySpec = new SecretKeySpec(bytes, ENCRYPTION_ALGORITHM);

            Cipher cipher = Cipher.getInstance(CIPHER_AES256_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, new IvParameterSpec(key.substring(0, 16).getBytes()));

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return bytesToHex(encrypted); // Hex Encoding without javax.xml.bind.DatatypeConverter
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
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // 암호화 파라미터
    private String makePlainText(String cardNo, String expYear, String expMonth, String idNo, String cardPw) {
        return "cardNo=" + cardNo + "&" + "expYear=" + expYear + "&" + "expMonth=" + expMonth + "&" + "idNo=" + idNo + "&" + "cardPw=" + cardPw;
    }

    public enum EncDataType {
        A1,
        A2;

        public static boolean isAES258(EncDataType encDataType) {
            return A2.equals(encDataType);
        }
    }

    public static final class CardEncDataValidator {
        private static final String INVALID_CARD_NO = "카드번호는 16자리 입니다.";
        private static final String INVALID_EXP_YEAR = "유효기간 년도(YY)는 2자리 입니다.";
        private static final String INVALID_EXP_MONTH = "유효기간 월(MM)은 2자리 입니다.";
        private static final String INVALID_ID_NO = "주민등록번호(YYMMDD)는 6자리 입니다.";
        private static final String INVALID_CARD_PW = "카드비밀번호(비밀번호 앞 2자리)는 2자리 입니다.";
        private CardEncDataValidator() {
        }

        public static void validation(String cardNo, String expYear, String expMonth, String idNo, String cardPw) {

            if (cardNo == null || cardNo.length() != 16) {
                throw new IllegalArgumentException(INVALID_CARD_NO);
            }
            if (expYear == null || expYear.length() != 2) {
                throw new IllegalArgumentException(INVALID_EXP_YEAR);
            }
            if (expMonth == null || expMonth.length() != 2) {
                throw new IllegalArgumentException(INVALID_EXP_MONTH);
            }
            if (idNo == null || idNo.length() != 6) {
                throw new IllegalArgumentException(INVALID_ID_NO);
            }
            if (cardPw == null || cardPw.length() != 2) {
                throw new IllegalArgumentException(INVALID_CARD_PW);
            }
        }
    }
}