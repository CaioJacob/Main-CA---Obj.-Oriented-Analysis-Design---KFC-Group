package adminsoftware;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Minimal password hashing helper to replace unimplemented methods.
 * NOTE: this is a pragmatic salted SHA-256 fallback for development/testing.
 * Replace with a real bcrypt implementation (jBCrypt) for production.
 */
public final class BCrypt {
    private static final SecureRandom RANDOM = new SecureRandom();

    // Generate a random salt (Base64 url-safe, no padding)
    public static String gensalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(salt);
    }

    // Hash password using salt. Returns "salt$hash" where hash is Base64(SHA-256(salt + password))
    public static String hashpw(String password, String salt) {
        if (password == null || salt == null) {
            throw new IllegalArgumentException("password and salt must be non-null");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
            return salt + "$" + encoded;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    // Verify plain password against stored value produced by hashpw
    public static boolean checkpw(String password, String stored) {
        if (password == null || stored == null) return false;
        int idx = stored.indexOf('$');
        if (idx <= 0) return false;
        String salt = stored.substring(0, idx);
        String expected = hashpw(password, salt);
        return expected.equals(stored);
    }

    // Prevent instantiation
    private BCrypt() {}
}
