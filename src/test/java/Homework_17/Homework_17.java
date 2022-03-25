package Homework_17;

import Homework_17.Users.Data;
import Lecture_17.Users.Root;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Homework_17 {

    @BeforeTest
    public void precondition() {
        baseURI = ("https://reqres.in/");
    }

    @Test(priority = 1)
    private void getAllUsers_test() {
        Response response = given().when().get("/api/users?page=2");
        response.then().assertThat().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        List<Data> allUsers = jsonPath.getList("data", Data.class);
        int numberOfUsersOnPage = allUsers.size();
        Assert.assertEquals(numberOfUsersOnPage, Integer.parseInt(jsonPath.get("per_page").toString()));
    }

    @Test(priority = 2)
    private void getSingleUser_test() {
        Response response = given().when().get("/api/users/3");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(Integer.parseInt(response.then().extract().response().jsonPath().get("data.id").toString()), 3);
        Assert.assertEquals(response.then().extract().response().jsonPath().get("data.email").toString(), "emma.wong@reqres.in");
        Assert.assertEquals(response.then().extract().response().jsonPath().get("data.last_name").toString(), "Wong");
    }

    @Test(priority = 3)
    private void getSingleUserNotFound_test() {
        Response response = given().when().get("/api/users/36");
        response.then().assertThat().statusCode(404);
    }

    @Test(priority = 4)
    public void postCreateSingleUser_test() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .and()
                .body(getRequestBody("create"))
                .post("/api/users/2");
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(response.then().extract().jsonPath().get("name"), "Alesya");
        Assert.assertEquals(response.then().extract().jsonPath().get("job"), "QA automation");
    }

    @Test(priority = 5)
    public void putUpdateSingleUser_test() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .and()
                .body(getRequestBody("update"))
                .put("/api/users/2");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(response.then().extract().jsonPath().get("name"), "Alesya");
        Assert.assertEquals(response.then().extract().jsonPath().get("job"), "Manual QA");
        Assert.assertTrue(response.then().extract().jsonPath().get("updatedAt").toString().contains(LocalDate.now().toString()));
    }

    @Test(priority = 6)
    public void patchUpdateUserName_test() {
        Response response = given().body(getRequestBody("update"))
                .when()
                .contentType(ContentType.JSON)
                .patch("/api/users/2");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(response.then().extract().jsonPath().get("name"), "Alesya Pesetskaya");
        Assert.assertEquals(response.then().extract().jsonPath().get("job"), "Manual QA");
        Assert.assertTrue(response.then().extract().jsonPath().get("updatedAt").toString().contains(LocalDate.now().toString()));
    }

    @Test(priority = 7)
    public void putUpdateUserNameEmptyJob_test() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .and()
                .body(getRequestBody("updateUsername"))
                .put("/api/users/2");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(response.then().extract().jsonPath().get("name"), "Alesya Pesetskaya");
        Assert.assertNull(response.then().extract().jsonPath().get("job"));
        Assert.assertTrue(response.then().extract().jsonPath().get("updatedAt").toString().contains(LocalDate.now().toString()));
    }

    @Test(priority = 8)
    private void headSingleUser_test() {
        given().when().request("HEAD", "/api/users/2").then().assertThat().statusCode(200);
    }

    @Test(priority = 9)
    public void validateResponseTimeInSeconds_test() {
        given().when().get("api/users?page=2").then().time(lessThan(3L), TimeUnit.SECONDS);
    }

    @Test(priority = 10)
    public void deleteUser_test() {
        given()
                .when()
                .header("Content-Type", "application/json")
                .and()
                .body(getRequestBody("delete"))
                .delete()
                .then()
                .assertThat()
                .statusCode(204);
    }

    @Test(priority = 11)
    public void postSuccessfulLogin_test() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .and()
                .body(getRequestBody("login"))
                .post("api/login");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(response.then().extract().jsonPath().get("token"), "QpwL5tke4Pnpja7X4");
    }

    @Test(priority = 12)
    public void postUnsuccessfulLogin_test() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .and()
                .body(getRequestBody("unsuccessfulRegister"))
                .post("api/login");
        response.then().assertThat().statusCode(400);
        Assert.assertEquals(response.then().extract().jsonPath().get("error"), "Missing password");
    }

    @Test(priority = 13)
    public void postUnsuccessfulRegister_test() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .and()
                .body(getRequestBody("unsuccessfulRegister"))
                .post("api/register");
        response.then().assertThat().statusCode(400);
        Assert.assertEquals(response.then().extract().jsonPath().get("error"), "Missing password");
    }

    @Test(priority = 14)
    public void postSuccessfulRegister_test() {
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .and()
                .body(getRequestBody("successfulRegister"))
                .post("api/register");
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(response.then().extract().jsonPath().get("id").toString(), "4");
        Assert.assertEquals(response.then().extract().jsonPath().get("token"), "QpwL5tke4Pnpja7X4");
    }

    @Test(priority = 15)
    private void resourceNotFound_test() {
        Response response = given().when().get("/api/unknown/23");
        response.then().assertThat().statusCode(404);
    }

    private String getRequestBody(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/java/Homework_17/Request/" + fileName + ".json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
