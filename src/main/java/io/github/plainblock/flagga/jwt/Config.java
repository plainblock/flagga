package io.github.plainblock.flagga.jwt;

public class Config {

    public static final String JWT_SECRET = "${jwt.secret}";
    public static final String FLAG_NONE = "${jwt.flag.none}";
    public static final String FLAG_HS256 = "${jwt.flag.hs256}";

}
