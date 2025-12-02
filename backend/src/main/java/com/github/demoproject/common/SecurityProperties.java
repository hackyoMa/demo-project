package com.github.demoproject.common;

import com.github.demoproject.util.EncryptUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.Ed25519Signer;
import com.nimbusds.jose.crypto.Ed25519Verifier;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.OctetKeyPair;
import com.nimbusds.jose.jwk.gen.OctetKeyPairGenerator;
import com.nimbusds.jose.util.Base64URL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

/**
 * SecurityProperties
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Data
@Component
@ConfigurationProperties("security")
public class SecurityProperties {

    private String[] allWhitelist;
    private Map<HttpMethod, String[]> whitelist;
    private Secret secret;

    @Data
    public static class Secret {
        public static final JWSAlgorithm ALGORITHM = JWSAlgorithm.Ed25519;

        private String keyId;
        private OctetKeyPair privateKey;
        private OctetKeyPair publicKey;
        private Ed25519Signer signer;
        private Ed25519Verifier verifier;

        public Secret(Path privateKey, Path publicKey) throws IOException, JOSEException {
            if (!Files.exists(privateKey) || !Files.exists(publicKey)) {
                OctetKeyPair keyPair = new OctetKeyPairGenerator(Curve.Ed25519).generate();
                writeKey(privateKey, keyPair.getDecodedD(), KeyType.PRIVATE);
                writeKey(publicKey, keyPair.getDecodedX(), KeyType.PUBLIC);
            }
            byte[] decodedD = loadKey(privateKey);
            this.keyId = EncryptUtil.blake3(decodedD);
            this.privateKey = new OctetKeyPair.Builder(Curve.Ed25519, Base64URL.encode(loadKey(publicKey)))
                    .d(Base64URL.encode(decodedD)).keyID(this.keyId).build();
            this.publicKey = this.privateKey.toPublicJWK();
            this.signer = new Ed25519Signer(this.privateKey);
            this.verifier = new Ed25519Verifier(this.publicKey);
        }

        private static byte[] loadKey(Path path) throws IOException {
            return EncryptUtil.base64ToBytes(Files.readString(path)
                    .replaceAll(".*?-----", "")
                    .replaceAll("\\s+", ""));
        }

        private static void writeKey(Path path, byte[] key, KeyType type) throws IOException {
            Path parent = path.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            String keyString = type.getHeader() + "\n"
                    + EncryptUtil.bytesToBase64(key, 64) + "\n"
                    + type.getFooter() + "\n";
            Files.writeString(path, keyString, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }

        @Getter
        @AllArgsConstructor
        private enum KeyType {
            PRIVATE("-----BEGIN PRIVATE KEY-----", "-----END PRIVATE KEY-----"),
            PUBLIC("-----BEGIN PUBLIC KEY-----", "-----END PUBLIC KEY-----");

            private final String header;
            private final String footer;
        }
    }

}
