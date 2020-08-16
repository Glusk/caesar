package com.github.glusk.caesar.hashing;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.github.glusk.caesar.Bytes;
import com.github.glusk.caesar.internal.BinaryString;
import com.github.glusk.caesar.internal.Identity;

/**
 * A message digest argument.
 * <p>
 * An argument to a hash operation is typically a plain text, a binary string
 * or a byte array. This class funnels all these different sources into one
 * object - a byte sequence.
 */
public final class DigestArgument implements Bytes {
    /** Byte sequence representation of {@code this} DigestArgument. */
    private final Bytes argument;

    /**
     * Creates a new DigestArgument from an {@code utf8String}.
     *
     * @param utf8String an UTF-8 encoded String argument
     */
    public DigestArgument(final String utf8String) {
        this(utf8String, StandardCharsets.UTF_8);
    }

    /**
     * Creates a new DigestArgument from a {@code string} with the specified
     * {@code charset}.
     *
     * @param string the String argument
     * @param charset the charset of {@code string}
     */
    public DigestArgument(final String string, final Charset charset) {
        this(new Identity(string.getBytes(charset)));
    }

    /**
     * Creates a new DigestArgument from the specified {@code binaryString} in
     * a given {@code radix} (or base).
     *
     * @param binaryString binary string in {@code radix} base
     * @param radix The radix in which {@code binaryString} is encoded.
     *              Currently, only radixes 16 and 64 are supported.
     */
    public DigestArgument(final String binaryString, final int radix) {
        this(new BinaryString(binaryString, radix));
    }

    /**
     * Creates a new DigestArgument from the specified byte array
     * {@code argument}.
     *
     * @param argument the byte array argument
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public DigestArgument(final byte... argument) {
        this(new Identity(argument));
    }

    /**
     * Creates a new DigestArgument from the specified {@code argument}.
     *
     * @param argument the byte sequence argument
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public DigestArgument(final Bytes argument) {
        this.argument = argument;
    }

    @Override
    public byte[] asArray() {
      return argument.asArray();
    }
}
