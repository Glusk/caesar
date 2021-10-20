package com.github.glusk.caesar;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/** A byte sequence constructed from plain text. */
public final class PlainText extends AbstractBytes {
    /** Byte sequence representation of {@code this} PlainText. */
    private final Bytes plainTextAsBytes;

    /**
     * Creates a new byte sequence from an {@code utf8String}.
     *
     * @param utf8String an UTF-8 encoded String
     */
    public PlainText(final String utf8String) {
        this(utf8String, StandardCharsets.UTF_8);
    }

    /**
     * Creates a new byte sequence from a {@code string} with the specified
     * {@code charset}.
     *
     * @param string the String argument
     * @param charset the charset of {@code string}
     */
    public PlainText(final String string, final Charset charset) {
        this.plainTextAsBytes =
            new AbstractBytes() {
                @Override
                public byte[] asArray() {
                    return string.getBytes(charset);
                }
            };
    }

    @Override
    public byte[] asArray() {
      return plainTextAsBytes.asArray();
    }
}
