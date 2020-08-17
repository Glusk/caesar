# Caesar

![Logo](logo.png)

[![Build Status](https://travis-ci.com/Glusk/caesar.svg?branch=master)](https://travis-ci.com/Glusk/caesar)
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
        new DigestArgument("password123")
    ).asArray();
```
You can also use `ImmutableMessageDigest`'s *fluid* API:
``` java
// MessageDigest md = ...

byte[] result =
    new ImmutableMessageDigest(md)
        .update(new DigestArgument("password123"))
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

byte[] result = 
    new Hash(
        imd,
        new Hash(
            imd,
            new DigestArgument(b1)
        ),
        new DigestArgument(b2),
        new DigestArgument(b3)
    ).asArray();
```

## Releases

Run this from the command line:

```
mvn release:prepare -DreleaseVersion=<TAG_NUMBER>
```

where `<TAG_NUMBER>` is the new [semver](http://www.semver.org) release number.

The release plugin will:
1. bump the project version to `<TAG_NUMBER>` and commit the change
2. create a new tag `<TAG_NUMBER>`
3. bump the version to `<TAG_NUMBER>-SNAPSHOT` and commit the change
4. push to local commits and tag to remote origin

The above steps will in turn trigger an automated deployment/publish proces on
Travis CI and deploy a new release to Maven Central.

You have to clean up afterward because we aren't using maven release plugin to
deploy:

```
mvn release:clean
```

That's it!

---

<div>Logo icon made by <a href="https://www.flaticon.com/free-icon/caesar-cipher_1792163" title="Pixelmeetup">Pixelmeetup</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
