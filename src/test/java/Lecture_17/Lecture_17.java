package Lecture_17;

import Lecture_17.Users.Root;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Lecture_17 {
@BeforeTest
public void precondition(){
    baseURI=("https://reqres.in/");
}


    @Test(priority = 1)
    public void get_test(){
        Response response=given().when().get("/api/users/2");
        response.then().assertThat().statusCode(200);
        System.out.println(response.then().extract().response().jsonPath().get("data").toString());
        Assert.assertEquals(Integer.parseInt(response.then().extract().response().jsonPath().get("data.id").toString()),2);
        Assert.assertEquals(response.then().extract().response().jsonPath().get("data.email").toString(),"janet.weaver@reqres.in");
        System.out.println(response.as(Root.class).data.id);
        System.out.println(response.as(Root.class).toString());
    }

    @Test(priority = 2)
    public void post_test(){
        Response response=given().when().header("Content-Type","application/json").and().body(getJsonData("create")).post("/api/users/2");
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(response.then().extract().jsonPath().get("name"),"morpheus");
        Assert.assertEquals(response.then().extract().jsonPath().get("job"),"leader");
    }

    @Test(priority = 2)
    public void user_test(){
        Response response=given().when().get("/api/users/3");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("data.id",equalTo(3))
                        .assertThat().body("support.text",containsString("ReqRes"));
    }

    @Test(priority = 2)
    public void jsonSchemaTest_test(){
        Response response=given().when().get("/api/users/3");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchema(getJsonData("jsonSchemaUser")));
    }

    @Test(priority = 2)
    public void jsonSchemaTestUsers_test(){
        Response response=given().when().get("/api/users?page=2");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchema(getJsonData("jsonSchemaUserArray")));
    }

    private String getJsonData(String name){
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/java/Lecture_17/Request/"+name+".json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
