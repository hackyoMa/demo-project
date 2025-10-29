package com.github.demoproject.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.Blake3;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * EncryptUtil
 *
 * @author hackyo
 * @since 2022/4/1
 */
public final class EncryptUtil {

    public static String bytesToHex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }

    public static byte[] hexToBytes(String hex) throws DecoderException {
        return Hex.decodeHex(hex);
    }

    public static String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] base64ToBytes(String base64) {
        return Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
    }

    public static String blake3(String original) {
        Blake3 hasher = Blake3.initHash();
        hasher.update(original.getBytes(StandardCharsets.UTF_8));
        byte[] hash = new byte[32];
        hasher.doFinalize(hash);
        return bytesToHex(hash);
    }

}
