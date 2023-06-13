package core.security;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class Salt {
    private static final int LENGTH = 32;

    public static char[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[LENGTH];
        random.nextBytes(salt);
        return StandardCharsets.UTF_8.decode(ByteBuffer.wrap(
                Base64.getEncoder().encode(salt))).array();
    }
}
