package com.dio.parking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.dio.parking.controller.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT extends AbstractContainerBase {

	@LocalServerPort
	private int ramdomPort;
	
	@BeforeEach
	public void setUpTest() {
		RestAssured.port = ramdomPort;
	}
	
	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given()
				.auth()
				.basic("user", "Dio@123456")
				.when()
				.get("/parking")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	void whenCreateThenCheckIsCreated() {
		
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("branco");
		createDTO.setLicense("KSN-3843");
		createDTO.setModel("Uno");
		createDTO.setState("GO");
		
		RestAssured.given()
				.when()
				.auth()
				.basic("user", "Dio@123456")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(createDTO)
				.post("/parking")
				.then()
				.statusCode(HttpStatus.CREATED.value())
				.body("license", Matchers.equalTo("KSN-3843"))
				.body("color", Matchers.equalTo("branco"));
	}

}
