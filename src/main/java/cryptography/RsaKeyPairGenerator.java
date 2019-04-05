package cryptography;

import javax.crypto.Cipher;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

public final class RsaKeyPairGenerator {

    private KeyPairGenerator keyPairGenerator;

    private KeyFactory keyFactory;

    private Cipher cipher;

    public RsaKeyPairGenerator() throws Exception {
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        keyFactory = KeyFactory.getInstance("RSA");

        cipher = Cipher.getInstance("RSA");
    }

    public static void main(String[] args) throws Exception {
        RsaKeyPairGenerator rkpg = new RsaKeyPairGenerator();

        KeyPair keyPair = rkpg.getPair();

        System.out.println(">>> Public key:");
        System.out.println(keyPair.getPublic());

        System.out.println(">>> Private key:");
        System.out.println(keyPair.getPrivate());


        KeySpecPair keySpecPair = rkpg.getKeySpecPair();

        rkpg.saveToFile("public.key", keySpecPair.getRsaPublicKeySpec().getModulus(), keySpecPair.getRsaPublicKeySpec().getPublicExponent());
        rkpg.saveToFile("private.key", keySpecPair.getRsaPrivateKeySpec().getModulus(), keySpecPair.getRsaPrivateKeySpec().getPrivateExponent());

        byte[] data = {1, 2, 3};

        byte[] encrypted = rkpg.rsaEncrypt(data);

        byte[] decrypted = rkpg.rsaDecrypt(encrypted);

        System.out.println(">>> Data");
        System.out.println(Arrays.toString(data));

        System.out.println(">>> Encrypted");
        System.out.println(Arrays.toString(encrypted));

        System.out.println(">>> Decrypted");
        System.out.println(Arrays.toString(decrypted));

    }

    public KeyPair getPair() {
        return keyPairGenerator.genKeyPair();
    }

    public KeySpecPair getKeySpecPair() throws Exception {
        KeyPair keyPair = getPair();

        RSAPublicKeySpec rsaPublicKeySpec =
                keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);

        RSAPrivateKeySpec rsaPrivateKeySpec =
                keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);

        return new KeySpecPair(rsaPublicKeySpec, rsaPrivateKeySpec);
    }

    public void saveToFile(String fileName, BigInteger mod, BigInteger exponent) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));

        try {
            oos.writeObject(mod);
            oos.writeObject(exponent);
        } catch (Exception e) {
            throw new IOException("Unexpected error", e);
        } finally {
            oos.close();
        }
    }

    public KeyTuple readKeyFromFile(String fileName) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));

        try {
            BigInteger modulus = (BigInteger) ois.readObject();
            BigInteger exponent = (BigInteger) ois.readObject();

            return new KeyTuple(modulus, exponent);
        } catch (Exception e) {
            throw new RuntimeException("Spurius serialization error", e);
        } finally {
            ois.close();
        }
    }

    public PublicKey readPublicKey() throws Exception {
        KeyTuple keyTuple = readKeyFromFile("public.key");

        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(keyTuple.getModulus(), keyTuple.getExponent());

        return keyFactory.generatePublic(rsaPublicKeySpec);
    }

    public PrivateKey readPrivateKey() throws Exception {
        KeyTuple keyTuple = readKeyFromFile("private.key");

        RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(keyTuple.getModulus(), keyTuple.getExponent());

        return keyFactory.generatePrivate(rsaPrivateKeySpec);
    }

    public byte[] rsaEncrypt(byte[] data) throws Exception {
        PublicKey publicKey = readPublicKey();

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    public byte[] rsaDecrypt(byte[] data) throws Exception {
        PrivateKey privateKey = readPrivateKey();

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    public static final class KeyTuple {

        private final BigInteger modulus;

        private final BigInteger exponent;

        public KeyTuple(final BigInteger modulus, final BigInteger exponent) {
            this.modulus = modulus;
            this.exponent = exponent;
        }

        public BigInteger getExponent() {
            return exponent;
        }

        public BigInteger getModulus() {
            return modulus;
        }
    }

    public static final class KeySpecPair {

        private final RSAPublicKeySpec rsaPublicKeySpec;
        private final RSAPrivateKeySpec rsaPrivateKeySpec;

        public KeySpecPair(final RSAPublicKeySpec rsaPublicKeySpec, final RSAPrivateKeySpec rsaPrivateKeySpec) {
            this.rsaPublicKeySpec = rsaPublicKeySpec;
            this.rsaPrivateKeySpec = rsaPrivateKeySpec;
        }

        public RSAPublicKeySpec getRsaPublicKeySpec() {
            return rsaPublicKeySpec;
        }

        public RSAPrivateKeySpec getRsaPrivateKeySpec() {
            return rsaPrivateKeySpec;
        }
    }

}