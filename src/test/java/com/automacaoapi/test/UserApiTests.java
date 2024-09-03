package com.automacaoapi.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.automacaoapi.pojo.User;
import com.automacaoapi.pojo.UserResponse;
import com.automacaoapi.pojo.UserWithoutJob;
import com.automacaoapi.pojo.UserWithoutName;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserApiTests {
    Faker faker = new Faker();
    static final String DATEFORMAT = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }


    @Test
    public void testCreateUser() {
        String name = faker.name().fullName();
        String job = faker.job().position();

        User user = new User(name, job);

        Response response = 
            given()
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .extract().response();

        UserResponse userResponse = response.as(UserResponse.class);
        Assert.assertEquals(name, userResponse.getName());
        Assert.assertEquals(job, userResponse.getJob());
        Assert.assertNotNull(userResponse.getId());
        Assert.assertTrue("A data não está no formato esperado", userResponse.getCreatedAt().matches(DATEFORMAT));
    }

    @Test
    public void testCreateUserWithEmptyName() {
        String job = faker.job().position();

        UserWithoutName user = new UserWithoutName(job);

        Response response = 
            given()
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .extract().response();
    }

    @Test
    public void testCreateUserWithEmptyJob() {
        String name = faker.name().fullName();

        UserWithoutJob user = new UserWithoutJob(name);

        Response response = 
            given()
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .extract().response();

    }

    @Test
    public void testCreateEmptyUser() {

        Response response = 
            given()
                .contentType(ContentType.JSON)
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .extract().response();
    }


}
 