package com.github.glusk.caesar.hashing;

import com.github.glusk.caesar.Bytes;

/** A byte sequence that is the result of a hashing operation. */
public final class Hash implements Bytes {
    /** The ImmutableMessageDigest instance to perform the hashing with. */
    private final ImmutableMessageDigest imd;
    /** The hash arguments list. */
    private final Bytes[] arguments;

    /**
     * Creates a new Hash instance by specifying the
     * ImmutableMessageDigest instance that will perform the hashing,
     * and a list of {@code arguments} to hash.
     *
     * @param imd the ImmutableMessageDigest instance that will be used to hash
     *            {@code args}
     * @param arguments a variable length arguments list to hash
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public Hash(
        final ImmutableMessageDigest imd,
        final Bytes... arguments
    ) {
        this.imd = imd;
        this.arguments = arguments;
    }

    /**
     * Performs hashing of the {@code arguments} passed through the constructor
     * and returns the result as a byte array.
     *
     * @return the result of hashing the {@code arguments} with {@code imd}
     */
    @Override
    public byte[] asArray() {
        return imd.update(arguments).digest();
    }
}
