package io.github.plainblock.flagga.jwt.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.plainblock.flagga.jwt.controller.constant.Header;
import io.github.plainblock.flagga.jwt.domain.constant.TestConstant;
import io.github.plainblock.flagga.jwt.domain.generator.HS256TokenGenerator;
import io.github.plainblock.flagga.jwt.domain.generator.NoneTokenGenerator;
import io.github.plainblock.flagga.jwt.domain.generator.TokenGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
public class TokenControllerTestBase {

	static TokenGenerator noneTokenGenerator;
	static TokenGenerator hs256TokenGenerator;
	
	MockMvc mockMvc;
	
	@BeforeAll
	static void before() {
		noneTokenGenerator = new NoneTokenGenerator();
		hs256TokenGenerator = new HS256TokenGenerator(TestConstant.SECRET);
	}

	@BeforeEach
	void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation)).build();
	}
	
	void executeGet(String controller, String endpoint) throws Exception {
		executeGet(controller, endpoint, null, null);
	}
	
	void executeGet(String controller, String endpoint, String token) throws Exception {
		executeGet(controller, endpoint, token, null);
	}

	void executeGet(String controller, String endpoint, String token, String flag) throws Exception {
		MockHttpServletRequestBuilder builder = get(endpoint);
		if (token != null && !token.isBlank()) {
			builder.header(Header.TOKEN, token);
		}
		if (flag != null && !flag.isBlank()) {
			builder.header(Header.FLAG, flag);
		}
		this.mockMvc.perform(builder).andExpect(status().isOk()).andDo(document(controller + "/{method-name}"));
	}

}
