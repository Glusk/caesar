# Caesar

![Logo](logo.png)

[![Build Status](https://app.travis-ci.com/Glusk/caesar.svg?branch=master)](https://app.travis-ci.com/Glusk/caesar)
[![Build status](https://ci.appveyor.com/api/projects/status/kvotrwt9pqn0dbg5/branch/master?svg=true)](https://ci.appveyor.com/project/Glusk/caesar/branch/master)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/36750977c17d4e238c343aac4d9913f2)](https://www.codacy.com/manual/Glusk/caesar?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Glusk/caesar&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/Glusk/caesar/badge.svg?branch=master)](https://coveralls.io/github/Glusk/caesar?branch=master)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.glusk/caesar/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.glusk/caesar)
[![javadoc](https://javadoc.io/badge2/com.github.glusk/caesar/javadoc.svg)](https://javadoc.io/doc/com.github.glusk/caesar)

[![LoC](https://tokei.rs/b1/github/glusk/caesar)](https://github.com/Glusk/caesar)
[![Hits-of-Code](https://hitsofcode.com/github/glusk/caesar?branch=master)](https://hitsofcode.com/view/github/glusk/caesar?branch=master)

An object-oriented approach to cryptography in Java.

---

## Motivation

This project aims to replace/wrap the following JDK APIs: 
[`MessageDigest`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/security/MessageDigest.html),
[`Mac`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/javax/crypto/Mac.html) and
[`Cipher`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/javax/crypto/Cipher.html).

[cactoos-crypto](https://github.com/g4s8/cactoos-crypto) is a direct alternative to this project.

## Hashing

In order to start using Caesar's hashing utilities you first have to wrap a `MessageDigest`
instance inside a new [`ImmutableMessageDigest`](https://javadoc.io/doc/com.github.glusk/caesar/latest/com.github.glusk.caesar/com/github/glusk/caesar/hashing/ImmutableMessageDigest.html)
object:

``` java
ImmutableMessageDigest imd =
    new ImmutableMessageDigest(
        MessageDigest.getInstance(/* ... */)
    );
```

Once you obtain an `ImmutableMessageDigest` instance, you can perform the hashing:

``` java
// ImmutableMessageDigest imd = ...
Bytes result = new Hash(imd, new PlainText("password123"));
```
You can also use `ImmutableMessageDigest`'s *fluid* API:
``` java
// ImmutableMessageDigest imd = ...
byte[] result = imd.update(new PlainText("password123")).digest();
```

### Embedded hashes

You can pass the result of one hashing operation as an argument to
another.

Suppose we wanted to compute the following hash:
<pre>
H(H(b1), b2, b3)
</pre>

This is how it would be done with Caesar:
``` java
// byte[] b1 = ...
// byte[] b2 = ...
// byte[] b3 = ...
// ImmutableMessageDigest imd = ...

Bytes result = 
    new Hash(
        imd,
        new Hash(
            imd,
            () -> b1
        ),
        () -> b2,
        () -> b3
    );
```

### HMAC

This is how you compute `HMAC("Key", "Message")` with Caesar, using the
`SHA-256` hash function:

``` java
Bytes hmac =
    new Hmac(
        new ImmutableMessageDigest(
            MessageDigest.getInstance("SHA-256")
        ),
        new PlainText("Key"),
        new PlainText("Message")
    );
```

## Releases

Use the [release](./release.sh) script with the following arguments:

1.  `release` - the next release version

2.  `snapshot` - the next snapshot version

3.  `dryRun` (optional) - if set to `true`, the changes will not be pushed
   to the remote repository

Example:

``` bash
./release.sh 0.1.1 0.1.2-SNAPSHOT
```

---

<div>Logo icon made by <a href="https://www.flaticon.com/free-icon/caesar-cipher_1792163" title="Pixelmeetup">Pixelmeetup</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
