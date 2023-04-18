package com.github.glusk.caesar.cipher;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import com.github.glusk.caesar.Bytes;
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
    public void noOutputIfNoInput() {
        assertEquals(
            new RC4(new PlainText("Key")).output(),
            Bytes.wrapped(new byte[0])
        );
    }
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
    @Test
    public void rfc6229TestVectorByteIndexKey56Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("01020304050607")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("293f02d47f37c9b633f2af5285feb46b").asArray(),
           new Hex("e620f1390d19bd84e2e0fd752031afc1").asArray(),
           new Hex("914f02531c9218810df60f67e338154c").asArray(),
           new Hex("d0fdb583073ce85ab83917740ec011d5").asArray(),
           new Hex("75f81411e871cffa70b90c74c592e454").asArray(),
           new Hex("0bb87202938dad609e87a5a1b079e5e4").asArray(),
           new Hex("c2911246b612e7e7b903dfeda1dad866").asArray(),
           new Hex("32828f91502b6291368de8081de36fc2").asArray(),
           new Hex("f3b9a7e3b297bf9ad804512f9063eff1").asArray(),
           new Hex("8ecb67a9ba1f55a5a067e2b026a3676f").asArray(),
           new Hex("d2aa902bd42d0d7cfd340cd45810529f").asArray(),
           new Hex("78b272c96e42eab4c60bd914e39d06e3").asArray(),
           new Hex("f4332fd31a079396ee3cee3f2a4ff049").asArray(),
           new Hex("05459781d41fda7f30c1be7e1246c623").asArray(),
           new Hex("adfd3868b8e51485d5e610017e3dd609").asArray(),
           new Hex("ad26581c0c5be45f4cea01db2f3805d5").asArray(),
           new Hex("f3172ceffc3b3d997c85ccd5af1a950c").asArray(),
           new Hex("e74b0b9731227fd37c0ec08a47ddd8b8").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorByteIndexKey64Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("0102030405060708")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("97ab8a1bf0afb96132f2f67258da15a8").asArray(),
           new Hex("8263efdb45c4a18684ef87e6b19e5b09").asArray(),
           new Hex("9636ebc9841926f4f7d1f362bddf6e18").asArray(),
           new Hex("d0a990ff2c05fef5b90373c9ff4b870a").asArray(),
           new Hex("73239f1db7f41d80b643c0c52518ec63").asArray(),
           new Hex("163b319923a6bdb4527c626126703c0f").asArray(),
           new Hex("49d6c8af0f97144a87df21d91472f966").asArray(),
           new Hex("44173a103b6616c5d5ad1cee40c863d0").asArray(),
           new Hex("273c9c4b27f322e4e716ef53a47de7a4").asArray(),
           new Hex("c6d0e7b226259fa9023490b26167ad1d").asArray(),
           new Hex("1fe8986713f07c3d9ae1c163ff8cf9d3").asArray(),
           new Hex("8369e1a965610be887fbd0c79162aafb").asArray(),
           new Hex("0a0127abb44484b9fbef5abcae1b579f").asArray(),
           new Hex("c2cdadc6402e8ee866e1f37bdb47e42c").asArray(),
           new Hex("26b51ea37df8e1d6f76fc3b66a7429b3").asArray(),
           new Hex("bc7683205d4f443dc1f29dda3315c87b").asArray(),
           new Hex("d5fa5a3469d29aaaf83d23589db8c85b").asArray(),
           new Hex("3fb46e2c8f0f068edce8cdcd7dfc5862").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorByteIndexKey80Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("0102030405060708090a")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("ede3b04643e586cc907dc21851709902").asArray(),
           new Hex("03516ba78f413beb223aa5d4d2df6711").asArray(),
           new Hex("3cfd6cb58ee0fdde640176ad0000044d").asArray(),
           new Hex("48532b21fb6079c9114c0ffd9c04a1ad").asArray(),
           new Hex("3e8cea98017109979084b1ef92f99d86").asArray(),
           new Hex("e20fb49bdb337ee48b8d8dc0f4afeffe").asArray(),
           new Hex("5c2521eacd7966f15e056544bea0d315").asArray(),
           new Hex("e067a7031931a246a6c3875d2f678acb").asArray(),
           new Hex("a64f70af88ae56b6f87581c0e23e6b08").asArray(),
           new Hex("f449031de312814ec6f319291f4a0516").asArray(),
           new Hex("bdae85924b3cb1d0a2e33a30c6d79599").asArray(),
           new Hex("8a0feddbac865a09bcd127fb562ed60a").asArray(),
           new Hex("b55a0a5b51a12a8be34899c3e047511a").asArray(),
           new Hex("d9a09cea3ce75fe39698070317a71339").asArray(),
           new Hex("552225ed1177f44584ac8cfa6c4eb5fc").asArray(),
           new Hex("7e82cbabfc95381b080998442129c2f8").asArray(),
           new Hex("1f135ed14ce60a91369d2322bef25e3c").asArray(),
           new Hex("08b6be45124a43e2eb77953f84dc8553").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorByteIndexKey128Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("0102030405060708090a0b0c0d0e0f10")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("9ac7cc9a609d1ef7b2932899cde41b97").asArray(),
           new Hex("5248c4959014126a6e8a84f11d1a9e1c").asArray(),
           new Hex("065902e4b620f6cc36c8589f66432f2b").asArray(),
           new Hex("d39d566bc6bce3010768151549f3873f").asArray(),
           new Hex("b6d1e6c4a5e4771cad79538df295fb11").asArray(),
           new Hex("c68c1d5c559a974123df1dbc52a43b89").asArray(),
           new Hex("c5ecf88de897fd57fed301701b82a259").asArray(),
           new Hex("eccbe13de1fcc91c11a0b26c0bc8fa4d").asArray(),
           new Hex("e7a72574f8782ae26aabcf9ebcd66065").asArray(),
           new Hex("bdf0324e6083dcc6d3cedd3ca8c53c16").asArray(),
           new Hex("b40110c4190b5622a96116b0017ed297").asArray(),
           new Hex("ffa0b514647ec04f6306b892ae661181").asArray(),
           new Hex("d03d1bc03cd33d70dff9fa5d71963ebd").asArray(),
           new Hex("8a44126411eaa78bd51e8d87a8879bf5").asArray(),
           new Hex("fabeb76028ade2d0e48722e46c4615a3").asArray(),
           new Hex("c05d88abd50357f935a63c59ee537623").asArray(),
           new Hex("ff38265c1642c1abe8d3c2fe5e572bf8").asArray(),
           new Hex("a36a4c301ae8ac13610ccbc12256cacc").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorByteIndexKey192Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("0102030405060708090a0b0c0d0e0f101112131415161718")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
            new Hex("0595e57fe5f0bb3c706edac8a4b2db11").asArray(),
            new Hex("dfde31344a1af769c74f070aee9e2326").asArray(),
            new Hex("b06b9b1e195d13d8f4a7995c4553ac05").asArray(),
            new Hex("6bd2378ec341c9a42f37ba79f88a32ff").asArray(),
            new Hex("e70bce1df7645adb5d2c4130215c3522").asArray(),
            new Hex("9a5730c7fcb4c9af51ffda89c7f1ad22").asArray(),
            new Hex("0485055fd4f6f0d963ef5ab9a5476982").asArray(),
            new Hex("591fc66bcda10e452b03d4551f6b62ac").asArray(),
            new Hex("2753cc83988afa3e1688a1d3b42c9a02").asArray(),
            new Hex("93610d523d1d3f0062b3c2a3bbc7c7f0").asArray(),
            new Hex("96c248610aadedfeaf8978c03de8205a").asArray(),
            new Hex("0e317b3d1c73b9e9a4688f296d133a19").asArray(),
            new Hex("bdf0e6c3cca5b5b9d533b69c56ada120").asArray(),
            new Hex("88a218b6e2ece1e6246d44c759d19b10").asArray(),
            new Hex("6866397e95c140534f94263421006e40").asArray(),
            new Hex("32cb0a1e9542c6b3b8b398abc3b0f1d5").asArray(),
            new Hex("29a0b8aed54a132324c62e423f54b4c8").asArray(),
            new Hex("3cb0f3b5020a98b82af9fe154484a168").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorByteIndexKey256Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex(
                    "0102030405060708090a0b0c0d0e0f10"
                  + "1112131415161718191a1b1c1d1e1f20"
                )
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("eaa6bd25880bf93d3f5d1e4ca2611d91").asArray(),
           new Hex("cfa45c9f7e714b54bdfa80027cb14380").asArray(),
           new Hex("114ae344ded71b35f2e60febad727fd8").asArray(),
           new Hex("02e1e7056b0f623900496422943e97b6").asArray(),
           new Hex("91cb93c787964e10d9527d999c6f936b").asArray(),
           new Hex("49b18b42f8e8367cbeb5ef104ba1c7cd").asArray(),
           new Hex("87084b3ba700bade955610672745b374").asArray(),
           new Hex("e7a7b9e9ec540d5ff43bdb12792d1b35").asArray(),
           new Hex("c799b596738f6b018c76c74b1759bd90").asArray(),
           new Hex("7fec5bfd9f9b89ce6548309092d7e958").asArray(),
           new Hex("40f250b26d1f096a4afd4c340a588815").asArray(),
           new Hex("3e34135c79db010200767651cf263073").asArray(),
           new Hex("f656abccf88dd827027b2ce917d464ec").asArray(),
           new Hex("18b62503bfbc077fbabb98f20d98ab34").asArray(),
           new Hex("8aed95ee5b0dcbfbef4eb21d3a3f52f9").asArray(),
           new Hex("625a1ab00ee39a5327346bddb01a9c18").asArray(),
           new Hex("a13a7c79c7e119b5ab0296ab28c300b9").asArray(),
           new Hex("f3e4c0a2e02d1d01f7f0a74618af2b48").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorSha256Key40Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("833222772a")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("80ad97bdc973df8a2e879e92a497efda").asArray(),
           new Hex("20f060c2f2e5126501d3d4fea10d5fc0").asArray(),
           new Hex("faa148e99046181fec6b2085f3b20ed9").asArray(),
           new Hex("f0daf5bab3d596839857846f73fbfe5a").asArray(),
           new Hex("1c7e2fc4639232fe297584b296996bc8").asArray(),
           new Hex("3db9b249406cc8edffac55ccd322ba12").asArray(),
           new Hex("e4f9f7e0066154bbd125b745569bc897").asArray(),
           new Hex("75d5ef262b44c41a9cf63ae14568e1b9").asArray(),
           new Hex("6da453dbf81e82334a3d8866cb50a1e3").asArray(),
           new Hex("7828d074119cab5c22b294d7a9bfa0bb").asArray(),
           new Hex("adb89cea9a15fbe617295bd04b8ca05c").asArray(),
           new Hex("6251d87fd4aaae9a7e4ad5c217d3f300").asArray(),
           new Hex("e7119bd6dd9b22afe8f89585432881e2").asArray(),
           new Hex("785b60fd7ec4e9fcb6545f350d660fab").asArray(),
           new Hex("afecc037fdb7b0838eb3d70bcd268382").asArray(),
           new Hex("dbc1a7b49d57358cc9fa6d61d73b7cf0").asArray(),
           new Hex("6349d126a37afcba89794f9804914fdc").asArray(),
           new Hex("bf42c3018c2f7c66bfde524975768115").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorSha256Key56Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("1910833222772a")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("bc9222dbd3274d8fc66d14ccbda6690b").asArray(),
           new Hex("7ae627410c9a2be693df5bb7485a63e3").asArray(),
           new Hex("3f0931aa03defb300f060103826f2a64").asArray(),
           new Hex("beaa9ec8d59bb68129f3027c96361181").asArray(),
           new Hex("74e04db46d28648d7dee8a0064b06cfe").asArray(),
           new Hex("9b5e81c62fe023c55be42f87bbf932b8").asArray(),
           new Hex("ce178fc1826efecbc182f57999a46140").asArray(),
           new Hex("8bdf55cd55061c06dba6be11de4a578a").asArray(),
           new Hex("626f5f4dce652501f3087d39c92cc349").asArray(),
           new Hex("42daac6a8f9ab9a7fd137c6037825682").asArray(),
           new Hex("cc03fdb79192a207312f53f5d4dc33d9").asArray(),
           new Hex("f70f14122a1c98a3155d28b8a0a8a41d").asArray(),
           new Hex("2a3a307ab2708a9c00fe0b42f9c2d6a1").asArray(),
           new Hex("862617627d2261eab0b1246597ca0ae9").asArray(),
           new Hex("55f877ce4f2e1ddbbf8e13e2cde0fdc8").asArray(),
           new Hex("1b1556cb935f173337705fbb5d501fc1").asArray(),
           new Hex("ecd0e96602be7f8d5092816cccf2c2e9").asArray(),
           new Hex("027881fab4993a1c262024a94fff3f61").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorSha256Key64Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("641910833222772a")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("bbf609de9413172d07660cb680716926").asArray(),
           new Hex("46101a6dab43115d6c522b4fe93604a9").asArray(),
           new Hex("cbe1fff21c96f3eef61e8fe0542cbdf0").asArray(),
           new Hex("347938bffa4009c512cfb4034b0dd1a7").asArray(),
           new Hex("7867a786d00a7147904d76ddf1e520e3").asArray(),
           new Hex("8d3e9e1caefcccb3fbf8d18f64120b32").asArray(),
           new Hex("942337f8fd76f0fae8c52d7954810672").asArray(),
           new Hex("b8548c10f51667f6e60e182fa19b30f7").asArray(),
           new Hex("0211c7c6190c9efd1237c34c8f2e06c4").asArray(),
           new Hex("bda64f65276d2aacb8f90212203a808e").asArray(),
           new Hex("bd3820f732ffb53ec193e79d33e27c73").asArray(),
           new Hex("d0168616861907d482e36cdac8cf5749").asArray(),
           new Hex("97b0f0f224b2d2317114808fb03af7a0").asArray(),
           new Hex("e59616e469787939a063ceea9af956d1").asArray(),
           new Hex("c47e0dc1660919c11101208f9e69aa1f").asArray(),
           new Hex("5ae4f12896b8379a2aad89b5b553d6b0").asArray(),
           new Hex("6b6b098d0c293bc2993d80bf0518b6d9").asArray(),
           new Hex("8170cc3ccd92a698621b939dd38fe7b9").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorSha256Key80Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("8b37641910833222772a")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("ab65c26eddb287600db2fda10d1e605c").asArray(),
           new Hex("bb759010c29658f2c72d93a2d16d2930").asArray(),
           new Hex("b901e8036ed1c383cd3c4c4dd0a6ab05").asArray(),
           new Hex("3d25ce4922924c55f064943353d78a6c").asArray(),
           new Hex("12c1aa44bbf87e75e611f69b2c38f49b").asArray(),
           new Hex("28f2b3434b65c09877470044c6ea170d").asArray(),
           new Hex("bd9ef822de5288196134cf8af7839304").asArray(),
           new Hex("67559c23f052158470a296f725735a32").asArray(),
           new Hex("8bab26fbc2c12b0f13e2ab185eabf241").asArray(),
           new Hex("31185a6d696f0cfa9b42808b38e132a2").asArray(),
           new Hex("564d3dae183c5234c8af1e51061c44b5").asArray(),
           new Hex("3c0778a7b5f72d3c23a3135c7d67b9f4").asArray(),
           new Hex("f34369890fcf16fb517dcaae4463b2dd").asArray(),
           new Hex("02f31c81e8200731b899b028e791bfa7").asArray(),
           new Hex("72da646283228c14300853701795616f").asArray(),
           new Hex("4e0a8c6f7934a788e2265e81d6d0c8f4").asArray(),
           new Hex("438dd5eafea0111b6f36b4b938da2a68").asArray(),
           new Hex("5f6bfc73815874d97100f086979357d8").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorSha256Key128Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("ebb46227c6cc8b37641910833222772a")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("720c94b63edf44e131d950ca211a5a30").asArray(),
           new Hex("c366fdeacf9ca80436be7c358424d20b").asArray(),
           new Hex("b3394a40aabf75cba42282ef25a0059f").asArray(),
           new Hex("4847d81da4942dbc249defc48c922b9f").asArray(),
           new Hex("08128c469f275342adda202b2b58da95").asArray(),
           new Hex("970dacef40ad98723bac5d6955b81761").asArray(),
           new Hex("3cb89993b07b0ced93de13d2a11013ac").asArray(),
           new Hex("ef2d676f1545c2c13dc680a02f4adbfe").asArray(),
           new Hex("b60595514f24bc9fe522a6cad7393644").asArray(),
           new Hex("b515a8c5011754f59003058bdb81514e").asArray(),
           new Hex("3c70047e8cbc038e3b9820db601da495").asArray(),
           new Hex("1175da6ee756de46a53e2b075660b770").asArray(),
           new Hex("00a542bba02111cc2c65b38ebdba587e").asArray(),
           new Hex("5865fdbb5b48064104e830b380f2aede").asArray(),
           new Hex("34b21ad2ad44e999db2d7f0863f0d9b6").asArray(),
           new Hex("84a9218fc36e8a5f2ccfbeae53a27d25").asArray(),
           new Hex("a2221a11b833ccb498a59540f0545f4a").asArray(),
           new Hex("5bbeb4787d59e5373fdbea6c6f75c29b").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorSha256Key192Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex("c109163908ebe51debb46227c6cc8b37641910833222772a")
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("54b64e6b5a20b5e2ec84593dc7989da7").asArray(),
           new Hex("c135eee237a85465ff97dc03924f45ce").asArray(),
           new Hex("cfcc922fb4a14ab45d6175aabbf2d201").asArray(),
           new Hex("837b87e2a446ad0ef798acd02b94124f").asArray(),
           new Hex("17a6dbd664926a0636b3f4c37a4f4694").asArray(),
           new Hex("4a5f9f26aeeed4d4a25f632d305233d9").asArray(),
           new Hex("80a3d01ef00c8e9a4209c17f4eeb358c").asArray(),
           new Hex("d15e7d5ffaaabc0207bf200a117793a2").asArray(),
           new Hex("349682bf588eaa52d0aa1560346aeafa").asArray(),
           new Hex("f5854cdb76c889e3ad63354e5f7275e3").asArray(),
           new Hex("532c7ceccb39df3236318405a4b1279c").asArray(),
           new Hex("baefe6d9ceb651842260e0d1e05e3b90").asArray(),
           new Hex("e82d8c6db54e3c633f581c952ba04207").asArray(),
           new Hex("4b16e50abd381bd70900a9cd9a62cb23").asArray(),
           new Hex("3682ee33bd148bd9f58656cd8f30d9fb").asArray(),
           new Hex("1e5a0b8475045d9b20b2628624edfd9e").asArray(),
           new Hex("63edd684fb826282fe528f9c0e9237bc").asArray(),
           new Hex("e4dd2e98d6960fae0b43545456743391").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
    @Test
    public void rfc6229TestVectorSha256Key256Bit() {
        ByteBuffer output = ByteBuffer.wrap(
            new RC4(
                new Hex(
                    "1ada31d5cf688221c109163908ebe51d"
                  + "ebb46227c6cc8b37641910833222772a"
                )
            )
            .update(
                new Hex("00".repeat(0x1010))
            ).output().asArray()
        );
        byte[][] expected = new byte[][] {
           new Hex("dd5bcb0018e922d494759d7c395d02d3").asArray(),
           new Hex("c8446f8f77abf737685353eb89a1c9eb").asArray(),
           new Hex("af3e30f9c095045938151575c3fb9098").asArray(),
           new Hex("f8cb6274db99b80b1d2012a98ed48f0e").asArray(),
           new Hex("25c3005a1cb85de076259839ab7198ab").asArray(),
           new Hex("9dcbc183e8cb994b727b75be3180769c").asArray(),
           new Hex("a1d3078dfa9169503ed9d4491dee4eb2").asArray(),
           new Hex("8514a5495858096f596e4bcd66b10665").asArray(),
           new Hex("5f40d59ec1b03b33738efa60b2255d31").asArray(),
           new Hex("3477c7f764a41baceff90bf14f92b7cc").asArray(),
           new Hex("ac4e95368d99b9eb78b8da8f81ffa795").asArray(),
           new Hex("8c3c13f8c2388bb73f38576e65b7c446").asArray(),
           new Hex("13c4b9c1dfb66579eddd8a280b9f7316").asArray(),
           new Hex("ddd27820550126698efaadc64b64f66e").asArray(),
           new Hex("f08f2e66d28ed143f3a237cf9de73559").asArray(),
           new Hex("9ea36c525531b880ba124334f57b0b70").asArray(),
           new Hex("d5a39e3dfcc50280bac4a6b5aa0dca7d").asArray(),
           new Hex("370b1c1fe655916d97fd0d47ca1d72b8").asArray(),
        };
        byte[] actual = new byte[16];
        for (int i = 0; i < RFC_6229_OFFSETS.length; i++) {
            output.slice().position(RFC_6229_OFFSETS[i]).get(actual);
            assertArrayEquals(expected[i], actual);
        }
    }
}
