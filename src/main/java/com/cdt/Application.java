package com.cdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

/*
curl --location --request GET 'http://localhost:8080/api/rest/helloworld' \
--header 'Authorization: Basic dXNlcjp1c2Vy' \
--header 'Cookie: JSESSIONID=9B5740E81ACD00C175938B2EB8589721'
 */
//‘Basic ’ + base64(<username>+’:’+<password>)