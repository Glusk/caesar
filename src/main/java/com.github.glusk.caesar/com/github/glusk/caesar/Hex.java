package com.github.glusk.caesar;

/**
 * A hexadecimal encoded byte sequence.
 * <p>
 * This class conforms to the following BNF definition of a hex string, as per
 * specified in <a href="https://www.ietf.org/rfc/rfc1778.txt">RFC1778</a>:
 *
 * <pre>{@code
 * <d> ::= '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9'
 * <hex-digit> ::= <d> | 'a' | 'b' | 'c' | 'd' | 'e' | 'f' |
 *                  'A' | 'B' | 'C' | 'D' | 'E' | 'F'
 * <hex-string> ::= <hex-digit> | <hex-digit> <hex-string>
 * }</pre>
 *
 * Any string that doesn't conform the the above definition will be
 * considered an illegal argument to {@link #Hex(String)}.
 * <p>
 * An empty string ({@code ""}) is not a valid hexadecimal string.
 */
public final class Hex implements Bytes {
    /**
     * A regex pattern to check the validity of the input {@code hexString}.
     */
    private static final String HEX_PATTERN = "^[a-fA-F0-9]+$";
    /** Hexadecimal radix - 16. */
    private static final int HEX_RADIX = 16;

    /** A hexadecimal encoded string that represents this byte sequence. */
    private final String hexString;

    /**
     * Constructs a new byte sequence from {@code hexString}.
     *
     * @param hexString a hexadecimal encoded string argument
     * @throws IllegalArgumentException if {@code hexString} is not a valid
     *                                  hexadecimal string
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public Hex(final String hexString) {
        if (!hexString.matches(HEX_PATTERN)) {
            throw new IllegalArgumentException(
                String.format(
                    "'%s' is not a valid hex string!",
                    hexString
                )
            );
        }
        this.hexString = hexString;
    }

    @Override
    public byte[] asArray() {
        String zeroPadded = hexString;
        if (hexString.length() % 2 != 0) {
            zeroPadded = "0" + hexString;
        }

        int hexLength = zeroPadded.length();
        byte[] result = new byte[hexLength / 2];
        for (int i = 0; i < result.length; i++) {
            int left = 2 * i;
            int right = 2 * i + 1;
            result[i] =
                Byte.parseByte(
                    zeroPadded.substring(left, right + 1),
                    HEX_RADIX
                );
        }
        return result;
    }
}
