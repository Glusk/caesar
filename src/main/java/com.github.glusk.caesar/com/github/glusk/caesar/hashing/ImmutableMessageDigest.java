package com.github.glusk.caesar.hashing;

import java.security.MessageDigest;

import com.github.glusk.caesar.Bytes;

/**
 * A {@code java.security.MessageDigest} functionality extension that allows
 * for method chaining and is immutable.
 */
public final class ImmutableMessageDigest {
    /** Wrapped MessageDigest instance. */
    private final MessageDigest md;

    /**
     * Creates a {@code new} ImmutableMessageDigest object by wrapping an
     * existing {@code messageDigest} instance.
     *
     * @param messageDigest the MessageDigest instance to wrap
     */
    public ImmutableMessageDigest(final MessageDigest messageDigest) {
        this.md = messageDigest;
    }

    /**
     * Clones {@code messageDigest} and returns the result, swallowing a
     * potential CloneNotSupportedException.
     *
     * @param messageDigest the MessageDigest object to clone
     * @return a copy of {@code messageDigest}
     * @throws RuntimeException if the cloning failed due to a
     *                          CloneNotSupportedException, this method bubbles
     *                          it into a RuntimeException and rethrows it
     */
    private static MessageDigest clone(final MessageDigest messageDigest) {
        try {
            return (MessageDigest) messageDigest.clone();
        } catch (CloneNotSupportedException cnse) {
            throw
                new RuntimeException(
                    String.format(
                        "The underlying MessageDigest object: %s is not "
                      + "cloneable.",
                        messageDigest
                    ),
                    cnse
                );
        }
    }

    /**
     * Updates {@code this} ImmutableMessage digest with {@code arguments} and
     * returns the result as a {@code new} ImmutableMessageDigest object.
     * <p>
     * Equivalent to:
     * <pre>
     * ImmutableMessageDigest imd = this;
     * for (Bytes arg : arguments) {
     *     imd = imd.update(arg.asArray());
     * }
     * return imd;
     * </pre>.
     *
     * @param arguments an array of {@link Bytes} to feed to {@code this}
     *                  ImmutableMessageDigest object
     * @return a {@code new} ImmutableMessageDigest object with the updated
     *         engine
     */
    public ImmutableMessageDigest update(final Bytes... arguments) {
        ImmutableMessageDigest imd = this;
        for (Bytes arg : arguments) {
            imd = imd.update(arg.asArray());
        }
        return imd;
    }

    /**
     * Updates {@code this} ImmutableMessage digest with the {@code argument}
     * array and returns the result as a {@code new} ImmutableMessageDigest
     * object.
     * <p>
     * Equivalent to:
     * <pre>
     * update(argument, 0, argument.length);
     * </pre>.
     *
     * @param argument the bytes to feed to {@code this} ImmutableMessageDigest
     *                 object
     * @return a {@code new} ImmutableMessageDigest object with the updated
     *         engine
     */
    public ImmutableMessageDigest update(final byte[] argument) {
        return update(argument, 0, argument.length);
    }

    /**
     * Updates {@code this} ImmutableMessage digest with the specified portion
     * of the {@code argument} array and returns the result as a {@code new}
     * ImmutableMessageDigest object.
     *
     * @param argument the bytes to feed to {@code this} ImmutableMessageDigest
     *                 object
     * @param offset the offset to start from in the {@code argument} array
     * @param len the number of bytes to use, starting at {@code offset}
     * @return a {@code new} ImmutableMessageDigest object with the updated
     *         engine
     */
    public ImmutableMessageDigest update(
        final byte[] argument,
        final int offset,
        final int len
    ) {
        MessageDigest clone = clone(this.md);
        clone.update(argument, offset, len);
        return new ImmutableMessageDigest(clone);
    }

   /**
    * Computes and returns the hash value value that is the result of
    * {@code this} ImmutableMessageDigest object.
    * <p>
    * Unlike with {@link java.security.MessageDigest#digest()}, there is no
    * engine reset. This method will always perform the computation on a fresh
    * copy of an internal MessageDigest object to assure the correct hash.
    *
    * @return byte array hash value that is the result of {@code this} message
    *          digest
    */
    public byte[] digest() {
        return clone(this.md).digest();
    }
}
