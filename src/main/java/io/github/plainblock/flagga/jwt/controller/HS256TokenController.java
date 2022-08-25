package io.github.plainblock.flagga.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.plainblock.flagga.jwt.Config;
import io.github.plainblock.flagga.jwt.controller.constant.Header;
import io.github.plainblock.flagga.jwt.controller.constant.Mapping;
import io.github.plainblock.flagga.jwt.service.HS256TokenService;

@RestController
@RequestMapping(Mapping.HS256)
public class HS256TokenController extends TokenControllerBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(HS256TokenController.class);

    private final HS256TokenService service;
    private final String flag;

    @Autowired
    public HS256TokenController(HS256TokenService service, @Value(Config.FLAG_HS256) String flag) {
        this.service = service;
        this.flag = flag;
    }

    @GetMapping(Mapping.TOKEN)
    String token() {
        return service.generateToken() + RN;
    }

    @GetMapping(Mapping.DECODE)
    String decode(@RequestHeader(name = Header.TOKEN, required = false) String token) {
        loggingHeader(LOGGER, token, null);
        if (token == null || token.isBlank()) {
            return TOKEN_REQUIRED_ERROR;
        }
        return service.decodeToken(token) + RN;
    }

    @GetMapping(Mapping.VERIFY)
    String verify(@RequestHeader(name = Header.TOKEN, required = false) String token) {
        loggingHeader(LOGGER, token, null);
        if (token == null || token.isBlank()) {
            return TOKEN_REQUIRED_ERROR;
        }
        return service.verifyToken(token) + RN;
    }

    @GetMapping(Mapping.FLAG)
    String flag(@RequestHeader(name = Header.FLAG, required = false) String flag) {
        loggingHeader(LOGGER, null, flag);
        if (flag == null || flag.isBlank()) {
            return FLAG_REQUIRED_ERROR;
        }
        if (flag.equals(this.flag)) {
            return FLAG_CORRECT_RESPONSE;
        }
        return FLAG_WRONG_RESPONSE;
    }

}
