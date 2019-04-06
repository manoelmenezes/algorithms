package cryptography;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PasswordBasedEncryption {

    public static byte[] encrypt(byte[] data, char[] password,
                                 byte[] salt, int noIterations) {
        try {
            String method = "PBEWithMD5AndTripleDES";
            SecretKeyFactory kf = SecretKeyFactory.getInstance(method);
            PBEKeySpec keySpec = new PBEKeySpec(password);
            SecretKey key = kf.generateSecret(keySpec);
            Cipher ciph = Cipher.getInstance(method);
            PBEParameterSpec params = new PBEParameterSpec(salt, noIterations);
            return ciph.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Spurious encryption error");
        }
    }

}
