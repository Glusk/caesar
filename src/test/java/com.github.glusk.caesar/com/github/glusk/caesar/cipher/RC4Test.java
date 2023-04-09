package com.github.glusk.caesar.cipher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.Hex;
import com.github.glusk.caesar.PlainText;

public class RC4Test {
    @Test
    public void wikiTestVector1() {
        assertEquals(
            new RC4()
                .init(new PlainText("Key"))
                .update(new PlainText("Plaintext"))
                .doFinal(),
            new Hex("BBF316E8D940AF0AD3")
        );
    }
    @Test
    public void wikiTestVector2() {
        assertEquals(
            new RC4()
                .init(new PlainText("Wiki"))
                .update(new PlainText("pedia"))
                .doFinal(),
            new Hex("1021BF0420")
        );
    }
    @Test
    public void wikiTestVector3() {
        assertEquals(
            new RC4()
                .init(new PlainText("Secret"))
                .update(new PlainText("Attack at dawn"))
                .doFinal(),
            new Hex("45A01F645FC35B383552544B9BF5")
        );
    }
}
