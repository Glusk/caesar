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
 * Immutable Cipher can be thought of as a state machine.
 * <p>
 * Method {@code init()} is not part of ImmutableCipher. Implementations
 * of this interface are expected to perform all the necessary initialization
 * in their corresponding constructors.
 * <p>
 * Every call to
 * {@code update()} updates the current state with {@code input} bytes
 * and returns it as a new state. This is different to what
 * the Java Standard Library class {@code Cipher} does, where the state of the
 * <em>existing</em> {@code Cipher} object is updated.
 * <p>
 * Every call to {@code output()} outputs either the cipher output for the
 * current state.
 * <p>
 * Calls to {@code update()} can be chained but output does not carry over to
 * the next state, only the state does. Consider the following code snippet:
 * <pre>
 * // ImmutableCipher cipher = ...
 * // Bytes i1 = ...
 * // Bytes i2 = ...
 * Bytes output = cipher.update(i1).update(i2).output();
 * </pre>
 * In the example above, {@code output} only holds the result of
 * encrypting/decrypting {@code i2}.
 */
interface ImmutableCipher {
    ImmutableCipher update(Bytes input);
    Bytes output();
}
