package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.Bytes;

/**
 * An Immutable Cipher.
 * <p>
 * This interface has been modeled after the {@code javax.crypto.Cipher}.
 * The naming convention for the three key methods of that class:
 * <ul>
 *   <li>{@code init()},</li>
 *   <li>{@code update()}, and</li>
 *   <li>{@code doFinal()}</li>
 * </ul>
 * has been followed closely with some crucial changes.
 * <p>
 * Method {@code init()} is not part of ImmutableCipher. Implementations
 * of this interface are expected to perform all the necessary initialization
 * in their corresponding constructors.
 * <p>
 * Every call to {@code update()} encrypts or decrypts {@code input} Bytes.
 * The result is a {@code new} ImmutableCipher object with the updated state
 * and the {@code output()} that corresponds to the {@code input}.
 * <p>
 * This is different to what
 * the Java Standard Library class {@code Cipher} does, where the state of the
 * <em>existing</em> {@code Cipher} object is updated.
 * <p>
 * Calls to {@code update()} can be chained. In that case, only the output
 * of the last encryption/decryption operation can be retrieved.
 * Consider the following code snippet:
 * <pre>
 * // ImmutableCipher cipher = ...
 * // Bytes i1 = ...
 * // Bytes i2 = ...
 * Bytes result = cipher.update(i1).update(i2).output();
 * </pre>
 * In the example above, {@code result} holds the encrypted/decrypted Bytes
 * for input {@code i2}.
 */
public interface ImmutableCipher {
    /**
     * Creates and returns a new ImmutableCipher that's the result of
     * processing {@code this} ImmutableCipher with {@code input} Bytes.
     *
     * @param input the encrypted or decrypted message to process by
     *              {@code this} ImmutableCipher
     * @return a new ImmutableCipher that's the result of updating {@code this}
     *         ImmutableCipher with {@code input} bytes.
     */
    ImmutableCipher update(Bytes input);
    /**
     * Returns the output of {@code this} ImmutableCipher.
     *
     * @return the encrypted or decrypted message
     */
    Bytes output();
}
