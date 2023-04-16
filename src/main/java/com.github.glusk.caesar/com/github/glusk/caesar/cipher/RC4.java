package com.github.glusk.caesar.cipher;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.Xor;

/**
 * An RC4 Cipher implementation.
 * <p>
 * <strong>Security Considerations</strong><br>
 * From <a href="https://www.rfc-editor.org/rfc/rfc6229#section-3">RFC 6229| 3. Security Considerations</a>:<br>
 * <em><q>
 * The RC4 algorithm does not meet the basic criteria required for an
 * encryption algorithm, as its output is distinguishable from random.
 * The use of RC4 continues to be recommended against; in particular,
 * its use in new specifications is discouraged.
 * </q></em>
 */
public final class RC4 implements ImmutableCipher {
    /** An integer bitmask with only the least significant 8 bits set. */
    private static final int UNSIGNED_BYTE_BITMASK = 0xff;
    /** Internal state: S - the permutation array. */
    @SuppressWarnings("checkstyle:membername")
    private final Bytes S;
    /** Internal state: index pointer i. */
    private final int i;
    /** Internal state: index pointer j. */
    private final int j;
    /**
     * The result of the previous encryption/decryption operation that
     * led to the current state.
     */
    private final Bytes output;

    /**
     * Create a new RC4 Cipher by providing the {@code key} to initialize
     * the internal cipher state.
     *
     * @param key
     */
    public RC4(final Bytes key) {
        this(new RC4InitialState(key), 0, 0, Bytes.wrapped(new byte[0]));
    }
    /**
     * Creates a new RC4 Cipher by providing the internal state along
     * with the result of the previous encryption/decryption operation.
     * <p>
     * More info on notation used: https://en.wikipedia.org/wiki/RC4
     * <p>
     * Only meant for internal use!
     *
     * @param S internal state: S - the permutation array
     * @param i internal state: index pointer i
     * @param j internal state: index pointer j
     * @param output the result of the previous encryption/decryption
     *               operation that led to the current state
     */
    @SuppressWarnings({
        "checkstyle:hiddenfield",
        "checkstyle:parametername"
    })
    private RC4(final Bytes S, final int i, final int j, final Bytes output) {
        this.S = S;
        this.i = i;
        this.j = j;
        this.output = output;
    }

    /**
     * {@inheritDoc}}
     * <p>
     * This method implements the: Pseudo-random generation algorithm (PRGA).
     * More info: https://en.wikipedia.org/wiki/RC4#Pseudo-random_generation_algorithm_(PRGA)
     */
    @Override
    @SuppressWarnings({
        "checkstyle:hiddenfield",
        "checkstyle:localvariablename"
    })
    public ImmutableCipher update(final Bytes input) {
        int inputLength = input.asArray().length;
        byte[] K = new byte[inputLength];
        byte[] S = this.S.asArray();
        for (int i = this.i, j = this.j, n = 0; n < inputLength; n++) {
            // i := (i + 1) mod 256
            // j := (j + S[i]) mod 256
            i = (i + 1) % S.length;
            j = (j + (S[i] & UNSIGNED_BYTE_BITMASK)) % S.length;
            // swap values of S[i] and S[j]
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            // K := S[(S[i] + S[j]) mod 256]
            K[n] = (byte) S[(
                    (S[i] & UNSIGNED_BYTE_BITMASK)
                  + (S[j] & UNSIGNED_BYTE_BITMASK)
                ) % S.length];
        }
        return new RC4(
            Bytes.wrapped(S), i, j,
            new Xor(Bytes.wrapped(K), input)
        );
    }

    @Override
    public Bytes output() {
        return this.output;
    }
}
