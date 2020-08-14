# Caesar

![Logo](logo.png)

[![Build Status](https://travis-ci.com/Glusk/caesar.svg?branch=master)](https://travis-ci.com/Glusk/caesar)
[![Coverage Status](https://coveralls.io/repos/github/Glusk/caesar/badge.svg?branch=master)](https://coveralls.io/github/Glusk/caesar?branch=master)

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
<div>Logo icon made by <a href="https://www.flaticon.com/free-icon/caesar-cipher_1792163" title="Pixelmeetup">Pixelmeetup</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
