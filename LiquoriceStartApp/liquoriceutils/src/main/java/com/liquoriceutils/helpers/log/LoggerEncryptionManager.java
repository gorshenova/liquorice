package com.liquoriceutils.helpers.log;

import com.liquoriceutils.BuildConfig;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Used to output logs.
 * Logs are output to the release mode in an encrypted form, the debug mode without any encryption.
 */
public class LoggerEncryptionManager {

    private static final String ALGORITHM_TYPE = "AES";
    private static final String ALGORITHM_TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    private static final String CHARSET_ENCODING = "UTF-8";

    public static String getEncodedBase64Key(String sourceString) throws UnsupportedEncodingException, GeneralSecurityException {
        return encrypt(BuildConfig.loggerEncryptionPasswordKey, sourceString);
    }

    public static String getDecodeBase64Key(String sourceString) throws UnsupportedEncodingException, GeneralSecurityException {
        return decrypt(BuildConfig.loggerEncryptionPasswordKey, sourceString);
    }

    private static String encrypt(String key, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(BuildConfig.loggerEncryptionInitVector.getBytes(CHARSET_ENCODING));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARSET_ENCODING), ALGORITHM_TYPE);

            Cipher cipher = Cipher.getInstance(ALGORITHM_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return StringUtils.newStringUtf8(Base64.encodeBase64(encrypted, false));
        } catch (Exception ex) {
            Logger.error(LoggerEncryptionManager.class, ex.getMessage());
        }

        return null;
    }

    private static String decrypt(String key, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(BuildConfig.loggerEncryptionInitVector.getBytes(CHARSET_ENCODING));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARSET_ENCODING), ALGORITHM_TYPE);

            Cipher cipher = Cipher.getInstance(ALGORITHM_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(StringUtils.getBytesUtf8(encrypted)));

            return new String(original);
        } catch (Exception ex) {
            Logger.error(LoggerEncryptionManager.class, ex.getMessage());
        }

        return null;
    }
}
