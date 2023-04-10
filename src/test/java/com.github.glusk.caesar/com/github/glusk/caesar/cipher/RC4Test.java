package com.github.glusk.caesar.cipher;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.Hex;
import com.github.glusk.caesar.PlainText;

public class RC4Test {
    /** RFC6229 memory offsets for the RC4 output stream validation. */
    private static final int[] RFC_6229_OFFSETS = new int[] {
        0x000, 0x010, 0x0f0, 0x100, 0x1f0, 0x200,
        0x2f0, 0x300, 0x3f0, 0x400, 0x5f0, 0x600,
        0x7f0, 0x800, 0xbf0, 0xc00, 0xff0, 0x1000
    };
    @Test
    public void wikiTestVector1() {
        assertEquals(
            new RC4(new PlainText("Key"))
                .update(new PlainText("Plaintext"))
                .output(),
            new Hex("BBF316E8D940AF0AD3")
        );
    }
    @Test
    public void wikiTestVector2() {
        assertEquals(
            new RC4(new PlainText("Wiki"))
                .update(new PlainText("pedia"))
                .output(),
            new Hex("1021BF0420")
        );
    }
    @Test
    public void wikiTestVector3() {
        assertEquals(
            new RC4(new PlainText("Secret"))
                .update(new PlainText("Attack at dawn"))
                .output(),
            new Hex("45A01F645FC35B383552544B9BF5")
        );
    }
    @Test
    public void rfc6229TestVectorByteIndexKey40Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("0102030405")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
            new Hex("b2396305f03dc027ccc3524a0a1118a8").asArray(),
            new Hex("6982944f18fc82d589c403a47a0d0919").asArray(),
            new Hex("28cb1132c96ce286421dcaadb8b69eae").asArray(),
            new Hex("1cfcf62b03eddb641d77dfcf7f8d8c93").asArray(),
            new Hex("42b7d0cdd918a8a33dd51781c81f4041").asArray(),
            new Hex("6459844432a7da923cfb3eb4980661f6").asArray(),
            new Hex("ec10327bde2beefd18f9277680457e22").asArray(),
            new Hex("eb62638d4f0ba1fe9fca20e05bf8ff2b").asArray(),
            new Hex("45129048e6a0ed0b56b490338f078da5").asArray(),
            new Hex("30abbcc7c20b01609f23ee2d5f6bb7df").asArray(),
            new Hex("3294f744d8f9790507e70f62e5bbceea").asArray(),
            new Hex("d8729db41882259bee4f825325f5a130").asArray(),
            new Hex("1eb14a0c13b3bf47fa2a0ba93ad45b8b").asArray(),
            new Hex("cc582f8ba9f265e2b1be9112e975d2d7").asArray(),
            new Hex("f2e30f9bd102ecbf75aaade9bc35c43c").asArray(),
            new Hex("ec0e11c479dc329dc8da7968fe965681").asArray(),
            new Hex("068326a2118416d21f9d04b2cd1ca050").asArray(),
            new Hex("ff25b58995996707e51fbdf08b34d875").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
}
