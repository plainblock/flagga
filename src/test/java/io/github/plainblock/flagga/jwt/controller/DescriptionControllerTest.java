package io.github.plainblock.flagga.jwt.controller;

import io.github.plainblock.flagga.jwt.controller.constant.Mapping;
import org.junit.jupiter.api.Test;

public class DescriptionControllerTest extends TokenControllerTestBase {

    private static final String CONTROLLER = "jwt";

    @Test
    void description() throws Exception {
        String endpoint = Mapping.BASE;
        executeGet(CONTROLLER, endpoint);
    }

}
