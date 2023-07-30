package com.codestates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SpringBootApplication
public class Section3Application {

	public static void main(String[] args) {
		// Spring 애플리케이션을 부트스트랩하고 실행
		// 부트스트랩? 애플리케이션이 실행되기 전에 여러가지 설정 작업을 수행하여 실행 가능한 애플리케이션으로 만드는 단계를 의미
		SpringApplication.run(Section3Application.class, args);

		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

		UriComponents uriComponents = UriComponentsBuilder
				.newInstance()
				.scheme("http")
				.host("worldtimeapi.org")
				.path("/api/timezone/{continents}/{city}")
				.encode()
				.build();

		URI uri	= uriComponents.expand("Asia", "Seoul").toUri();

		String result = restTemplate.getForObject(uri, String.class);
		System.out.println(result);
	}
}

/*
@SpringBootApplication
1. 자동 구성 활성화
2. 패키지 내에 @Component가 붙은 클래스를 검색한 후(Scan) Spring Bean으로 등록하는 기능을 활성화
3. @Configuration이 붙은 클래스를 자동으로 찾아주고 추가적으로 Spring Bean으로 등록하는 기능을 활성화
*/
