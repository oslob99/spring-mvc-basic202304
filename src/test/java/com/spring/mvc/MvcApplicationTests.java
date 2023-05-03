package com.spring.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
class MvcApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void uriBuilderTest() {

		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/board/detail")
				.queryParam("bno", 1)
				.queryParam("pageNo", 33)
				.queryParam("type", "title")
				.queryParam("keyword", "aaaa");

		System.out.println(builder.toUriString());
	}

}
