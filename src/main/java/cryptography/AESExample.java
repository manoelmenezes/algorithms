package cryptography;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public final class AESExample {

    private Cipher cipher;

    private KeyGenerator keyGenerator;

    private InitializationVectorGenerator initializationVectorGenerator;

    private SecretKeySpec secretKeySpec;

    private IvParameterSpec ivParameterSpec;

    public AESExample(final KeyGenerator keyGenerator,
                      final InitializationVectorGenerator initializationVectorGenerator) throws Exception {
        this.keyGenerator = keyGenerator;
        this.initializationVectorGenerator = initializationVectorGenerator;

        // This gave her a cipher in ECB mode, where every block is encrypted separately.
        // DO NOT DO THIS IN PRODUCTION BECAUSE IT CAN LEAK THE STRUCTURE OF THE PLAIN TEXT
        // USE OTHER BLOCK MODE SUCH AS COUNTER BLOCK MODE OR OUTPUT FEEDBACK MODE.
        //this.cipher = Cipher.getInstance("AES");

        // Notice the extended format of the cipher name: AES is still the name of the encryption
        // algorithm; CBC is the block mode (CTR and OFB are also supported); PKCS5PADDING specifies
        // the padding— that is, how to make the data up to a multiple of the block size if it otherwise
        // wouldn't be. Padding is not terribly exciting; suffice it to say for now that if you're not sure,
        // use PKCS5PADDING.
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        this.secretKeySpec = new SecretKeySpec(keyGenerator.generate(), "AES");
        this.ivParameterSpec = new IvParameterSpec(initializationVectorGenerator.getIV(0));
    }

    public static void main(String[] args) throws Exception {
        AESExample aesExample = new AESExample(new KeyGenerator(), new InitializationVectorGenerator());

        PlainTextGenerator ptg = new PlainTextGenerator();

        byte[] plainText = ptg.generate();

        byte[] encrypted = aesExample.encrypt(plainText);

        byte[] decrypted = aesExample.decrypt(encrypted);

        System.out.println(">>> Plain text");
        System.out.println(Arrays.toString(plainText));

        System.out.println(">>> Encrypted");
        System.out.println(Arrays.toString(encrypted));

        System.out.println(">>> Decrypted");
        System.out.println(Arrays.toString(decrypted));
    }

    public byte[] encrypt(byte[] data) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        return cipher.doFinal(data);
    }

    public byte[] decrypt(byte[] data) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        return cipher.doFinal(data);
    }

}