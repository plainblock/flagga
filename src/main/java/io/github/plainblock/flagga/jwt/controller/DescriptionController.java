package io.github.plainblock.flagga.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.plainblock.flagga.jwt.controller.constant.Mapping;

@RestController
@RequestMapping(Mapping.BASE)
public class DescriptionController {

    @GetMapping
    String description() {
        return """
               Description
                "JWT" is abbreviation　of "JSON Web Token".
                In "jwt" endpoint, you try to falsify JWT.
               
               Endpoints
                - GET /jwt/none/token   -> Generate JWT without signing.
                - GET /jwt/none/decode  -> Decode JWT without signing. Please set JWT to "Token" header.
                - GET /jwt/none/verify  -> Verify JWT without signing. If admin flag of JWT is true, return "none" endpoint's flag string. Please set JWT to "Token" header.
                - GET /jwt/none/flag    -> Judge flag string is correct. Please set "none" endpoint's flag string to "Flag" header.
                - GET /jwt/hs256/token  -> Generate JWT signing with HMAC256 algorithm.
                - GET /jwt/hs256/decode -> Decode JWT signing with HMAC256 algorithm. Please set JWT to "Token" header.
                - GET /jwt/hs256/verify -> Verify JWT signing with HMAC256 algorithm. If admin flag of JWT is true, return "hs256" endpoint's flag string. Please set JWT to "Token" header.
                - GET /jwt/hs256/flag   -> Judge flag string is correct. Please set "hs256" endpoint's flag string to "Flag" header.
               """;
    }

}
