package io.github.plainblock.flagga.jwt.service;

public class TokenServiceTestBase {

    static final String EXPECTED_SUBJECT = "user";
    static final String EXPECTED_MESSAGE = "This is test token.";

    static final String EXPIRED_NONE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJtc2ciOiJUaGlzIGlzIHRlc3QgdG9rZW4uIiwic3ViIjoidXNlciIsImlzcyI6InBsYWluYmxvY2siLCJhZG0iOmZhbHNlLCJleHAiOjE2NDg1NzQ1MzIsImlhdCI6MTY0ODU3MDkzMn0.";
    static final String EXPIRED_HS256_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtc2ciOiJUaGlzIGlzIHRlc3QgdG9rZW4uIiwic3ViIjoidXNlciIsImlzcyI6InBsYWluYmxvY2siLCJhZG0iOmZhbHNlLCJleHAiOjE2NDg1NzU4MzIsImlhdCI6MTY0ODU3MjIzMn0.yJtl7psWeKaTKE2XaJ4F6QemCLKyOGfuLJdx-d77mrk";

    static final String NONE_TOKEN_STRING = "\"alg\":\"none\",\"iss\":\"plainblock\",\"sub\":\"user\",\"msg\":\"This is test token.\",\"adm\":false";
    static final String HS256_TOKEN_STRING = "\"alg\":\"HS256\",\"iss\":\"plainblock\",\"sub\":\"user\",\"msg\":\"This is test token.\",\"adm\":false";

    static TokenService service;
    
}
