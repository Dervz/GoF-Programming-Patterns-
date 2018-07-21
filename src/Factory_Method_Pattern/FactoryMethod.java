package Factory_Method_Pattern;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * We start by developing our PRODUCT interface {@link EncryptionAlgorithm} since it has the most dependencies.
 * The Creator {@link Encryptor} interface directly depends on it and the ConcreteProduct implementations
 * such as {@link Sha256EncryptionAlgorithm} and {@link Sha512EncryptionAlgorithm} inherit from it
 *
 * Next, we develop the CREATOR interface {@link Encryptor}. In our case, we will make our Creator an abstract class.
 * That class contains an operation that allows a client to supply the plaintext to be encrypted.
 * Also, it gets the file name that the encrypted text should be saved to.
 *
 * With both of the abstractions in place {@link Encryptor} and {@link EncryptionAlgorithm},
 * we move onto creating the ConcreteProduct implementations
 * (since the ConcreteCreator instances will depend on these implementations).
 * In our example, we will create implementations for two encryption algorithms: (1) {@link Sha512EncryptionAlgorithm} and (2) {@link Sha256EncryptionAlgorithm}
 *
 * With our ConcreteProduct implementations complete, we can now create our two corresponding
 * ConcreteCreator implementations: (1) {@link Sha256Encryptor} and (2) {@link Sha512Encryptor}
 *
 * We can now supply our selected ABSTRACT_PRODUCT to a client and have the BEHAVIOR OF OUR SYSTEM VARY DEPENDING ON THE CONCRETE TYPE USED.
 * For example, suppose we create a new PersistedFile class {@link PersistedFile} that consumes the contents of a file (and maybe performs some formatting),
 * encrypts the contents, and persists it to disk.
 * In this case, we can vary the runtime behavior of this class by supplying a concrete implementation of Encryptor
 *
 * ************************************************************************************************************************
 * So the benefits of this pattern is that:
 * If you have to know more than the product to construct product A, product B or product C,
 * and you can't have direct access to that knowledge, then Factory Method Pattern is useful.
 * Then you are using the factory to act as a knowledge center for producing needed objects.
 *
 * Maybe those objects need a reference to some object X, which the factory can provide,
 * but your code in the place where you want to construct A, B or C can't or shouldn't have access to X.
 * Maybe when you have X you create A and B but if you have Y type then you create C.
 *
 * Also consider that some objects might need 20 dependencies to create; what then?
 * Going to hunt for those dependencies in a place where they should not be accessible might be problematic.
 */

public class FactoryMethod {
    public static void main(String[] args) {

        /**
         * creates an object of {@link PersistedFile} that encodes input and puts it in the file
         * all of which are specified in the parameters of its constructor
         */
        PersistedFile file256Encryption = new PersistedFile("D:/JavaProjects/GoF-Programming-Patterns-/src/Factory_Method_Pattern/sha256Encyption.txt",
                "Hello, world!", new Sha256Encryptor());
        file256Encryption.persist();

        PersistedFile file512Encryption = new PersistedFile("D:/JavaProjects/GoF-Programming-Patterns-/src/Factory_Method_Pattern/sha512Encyption.txt",
                "Encrypt me one more time!", new Sha256Encryptor());
        file512Encryption.persist();
    }

}


/**
 * PRODUCT ABSTRACTION
 * abstraction for the Encryption Algorithm
 */
interface EncryptionAlgorithm {
    String encrypt(String plaintext);
}


/**
 * CREATOR ABSTRACTION
 * abstraction for the Encryptor
 */
abstract class Encryptor {

    public void writeToDisk(String plaintext, String filename) {

        EncryptionAlgorithm encryptionAlgorithm = getEncryptionAlgorithm();
        String encryptedText = encryptionAlgorithm.encrypt(plaintext);

        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            outputStream.write(encryptedText.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method must be overriden.
     * The CONCRETE_CREATOR which extends this abstraction must return
     * corresponding CONCRETE_PRODUCT in this method.
     *
     * @return {@link EncryptionAlgorithm} PRODUCT TYPE. NOTE ITS NOT CONCRETE.
     * CONCRETE_PRODUCT sample must be specified by the subclasses
     */
    public abstract EncryptionAlgorithm getEncryptionAlgorithm();

}

/**
 * First class of the CONCRETE_PRODUCT.
 */
class Sha256EncryptionAlgorithm implements EncryptionAlgorithm {

    @Override
    public String encrypt(String plaintext) {
        return DigestUtils.sha256Hex(plaintext);
    }

}

/**
 * Second class of the CONCRETE_PRODUCT.
 */
class Sha512EncryptionAlgorithm implements EncryptionAlgorithm {

    @Override
    public String encrypt(String plaintext) {
        return DigestUtils.sha512Hex(plaintext);
    }

}

/**
 * First class of the CONCRETE_CREATOR.
 * All what CONCRETE_CREATOR class does is returning the corresponding CONCRETE_PRODUCT.
 * So Sha256Encryptor returns its product Sha256EncryptionAlgorithm.
 */
class Sha256Encryptor extends Encryptor {

    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return new Sha256EncryptionAlgorithm();
    }

}

/**
 * Second class of the CONCRETE_CREATOR.
 */
class Sha512Encryptor extends Encryptor {

    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return new Sha512EncryptionAlgorithm();
    }

}