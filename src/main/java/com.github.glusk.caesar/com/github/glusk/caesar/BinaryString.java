package com.github.glusk.caesar;

import jakarta.xml.bind.DatatypeConverter;

/** {@code Bytes} backed by a binary string.*/
public final class BinaryString implements Bytes {

    /** Hexadecimal (Base16) encoding radix. */
    private static final int HEX_RADIX = 16;
    /** Base64 encoding radix. */
    private static final int BASE_64_RADIX = 64;

    /** Binary string encoded in base {@code radix}. */
    private final String binaryString;
    /** Base of the encoded {@code binaryString}. */
    private final int radix;

    /**
     * Constructs a new BinaryString from {@code hexString}.
     * <p>
     * Equivalent to:
     * <pre>
     * new BinaryString(hexString, 16);
     * </pre>
     *
     * @param hexString hexadecimal binary string
     */
    public BinaryString(final String hexString) {
        this(hexString, HEX_RADIX);
    }

    /**
     * Constructs a new BinaryString.
     * <p>
     * If {@code radix} is not supported by this class, {@code asArray()} will
     * throw an {@code IllegalArgumentException}. Currently, the following
     * radixes are supported:
     * <ul>
     *   <li>16</li>
     *   <li>64</li>
     * </ul>
     *
     * @param binaryString binary string in {@code radix} base
     * @param radix The radix in which {@code binaryString} is encoded.
     *              This object currently supports only radixes {@code 16}
     *              and {@code 64}.
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public BinaryString(final String binaryString, final int radix) {
        this.binaryString = binaryString;
        this.radix = radix;
    }

    /**
     * Returns parsed binary string as a byte array.
     *
     * @throws IllegalArgumentException if the {@code radix} passed through the
     *                                  constructor is not supported
     */
    @Override
    public byte[] asArray() {
        switch (radix) {
            case HEX_RADIX:
                return DatatypeConverter.parseHexBinary(binaryString);
            case BASE_64_RADIX:
                return DatatypeConverter.parseBase64Binary(binaryString);
            default:
                throw
                    new IllegalArgumentException(
                        String.format("Radix \"%d\" not supported", radix)
                    );
        }
    }
}
