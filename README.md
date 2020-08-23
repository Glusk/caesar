# Caesar

![Logo](logo.png)

[![Build Status](https://travis-ci.com/Glusk/caesar.svg?branch=master)](https://travis-ci.com/Glusk/caesar)
[![Build status](https://ci.appveyor.com/api/projects/status/kvotrwt9pqn0dbg5/branch/master?svg=true)](https://ci.appveyor.com/project/Glusk/caesar/branch/master)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/36750977c17d4e238c343aac4d9913f2)](https://www.codacy.com/manual/Glusk/caesar?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Glusk/caesar&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/Glusk/caesar/badge.svg?branch=master)](https://coveralls.io/github/Glusk/caesar?branch=master)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.glusk/caesar/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.glusk/caesar)
[![javadoc](https://javadoc.io/badge2/com.github.glusk/caesar/javadoc.svg)](https://javadoc.io/doc/com.github.glusk/caesar)

[![LoC](https://tokei.rs/b1/github/glusk/caesar)](https://github.com/Glusk/caesar)
[![Hits-of-Code](https://hitsofcode.com/github/glusk/caesar?branch=master)](https://hitsofcode.com/view/github/glusk/caesar?branch=master)

An object-oriented approach to cryptography in Java.

---

## Hashing

Hashing is easy with Caesar:

``` java
// MessageDigest md = ...

byte[] result = 
    new Hash(
        new ImmutableMessageDigest(md),
        new PlainText("password123")
    ).asArray();
```
You can also use `ImmutableMessageDigest`'s *fluid* API:
``` java
// MessageDigest md = ...

byte[] result =
    new ImmutableMessageDigest(md)
        .update(new PlainText("password123"))
        .digest();
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
// MessageDigest md = ...
// byte[] b1 = ...
// byte[] b2 = ...
// byte[] b3 = ...
ImmutableMessageDigest imd = new ImmutableMessageDigest(md);

System.out.println(
    new Hash(
        imd,
        new Hash(
            imd,
            new WrappedBytes(b1)
        ),
        new WrappedBytes(b2),
        new WrappedBytes(b3)
    ).asHexString()
);
```

## Releases

Use the [release](./release.sh) script with the following arguments:

1. `release` - the next release version
2. `snapshot` - the next snapshot version
3. `dryRun` (optional) - if set to `true`, the changes will not be pushed
   to the remote repository

Example:

```
./release.sh 0.1.1 0.1.2-SNAPSHOT
```

---

<div>Logo icon made by <a href="https://www.flaticon.com/free-icon/caesar-cipher_1792163" title="Pixelmeetup">Pixelmeetup</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
