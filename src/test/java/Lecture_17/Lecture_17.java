package Lecture_17;

import Lecture_17.Users.Root;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Lecture_17 {
    @BeforeTest
    public void precondition() {
        baseURI = ("https://reqres.in/");
    }

    @Test(priority = 1)
    public void onlinerCatalog_Test() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();
        //Apple catalog
        HttpRequest catalog = HttpRequest
                .newBuilder()
                .uri(new URI("https://catalog.onliner.by/sdapi/catalog.api/search/mobile?on_sale=1&mfr[0]=apple&group=1"))
                .GET().build();
        HttpResponse<String> responseCatalog = httpClient.send(catalog, HttpResponse.BodyHandlers.ofString());
        JsonObject catalogObject = gson.fromJson(responseCatalog.body(), JsonObject.class);
        String productID = catalogObject.getAsJsonArray("products").get(0).getAsJsonObject().get("id").getAsString();
        String productKey = catalogObject.getAsJsonArray("products").get(0).getAsJsonObject().get("key").getAsString();
        String productName = catalogObject.getAsJsonArray("products").get(0).getAsJsonObject().get("name").getAsString();
        //position
        HttpRequest positions = HttpRequest
                .newBuilder()
                .uri(new URI("https://catalog.onliner.by/sdapi/shop.api/products/" + productKey + "/positions"))
                .GET().build();
        HttpResponse<String> responsePosition = httpClient.send(positions, HttpResponse.BodyHandlers.ofString());
        JsonObject positionsObject = gson.fromJson(responsePosition.body(), JsonObject.class);
        String positionID = positionsObject.getAsJsonObject("positions").getAsJsonArray("primary").get(0).getAsJsonObject().get("id").getAsString();
        String shopID = positionsObject.getAsJsonObject("positions").getAsJsonArray("primary").get(0).getAsJsonObject().get("shop_id").getAsString();
        //cartID
        Cart cartBody = Cart.builder().position_id(positionID).product_id(productID).shop_id(shopID).quantity(1).product_key(productKey).build();
        HttpRequest cart = HttpRequest
                .newBuilder()
                .uri(new URI("https://catalog.onliner.by/sdapi/cart.api/detached-cart/add/"))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(cartBody)))
                .build();
        HttpResponse<String> responseCart = httpClient.send(cart, HttpResponse.BodyHandlers.ofString());
        JsonObject cartObject = gson.fromJson(responseCart.body(), JsonObject.class);
        String cartID=cartObject.get("cart_id").getAsString();
        //Get cart
        HttpRequest getCart = HttpRequest
                .newBuilder()
                .uri(new URI("https://catalog.onliner.by/sdapi/cart.api/v2/detached-cart/"+cartID))
                .GET().build();
        HttpResponse<String> responseGetCart = httpClient.send(getCart, HttpResponse.BodyHandlers.ofString());
        JsonObject getBinObject = gson.fromJson(responseGetCart.body(), JsonObject.class);
        String actualProductName=getBinObject
                .getAsJsonArray("position_groups")
                .get(0)
                .getAsJsonObject()
                .getAsJsonArray("positions")
                .get(0)
                .getAsJsonObject()
                .get("product")
                .getAsJsonObject()
                .get("name")
                .getAsString();
        Assert.assertEquals(actualProductName,productName);
    }

    @Test(priority = 1)
    public void get_test() {
        Response response = given().when().get("/api/users/2");
        response.then().assertThat().statusCode(200);
        System.out.println(response.then().extract().response().jsonPath().get("data").toString());
        Assert.assertEquals(Integer.parseInt(response.then().extract().response().jsonPath().get("data.id").toString()), 2);
        Assert.assertEquals(response.then().extract().response().jsonPath().get("data.email").toString(), "janet.weaver@reqres.in");
        System.out.println(response.as(Root.class).data.id);
        System.out.println(response.as(Root.class).toString());
    }

    @Test(priority = 2)
    public void post_test() {
        Response response = given().when().header("Content-Type", "application/json").and().body(getJsonData("create")).post("/api/users/2");
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(response.then().extract().jsonPath().get("name"), "morpheus");
        Assert.assertEquals(response.then().extract().jsonPath().get("job"), "leader");
    }

    @Test(priority = 2)
    public void user_test() {
        Response response = given().when().get("/api/users/3");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("data.id", equalTo(3))
                .assertThat().body("support.text", containsString("ReqRes"));
    }

    @Test(priority = 2)
    public void jsonSchemaTest_test() {
        Response response = given().when().get("/api/users/3");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchema(getJsonData("jsonSchemaUser")));
    }

    @Test(priority = 2)
    public void jsonSchemaTestUsers_test() {
        Response response = given().when().get("/api/users?page=2");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchema(getJsonData("jsonSchemaUserArray")));
    }

    private String getJsonData(String name) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/java/Lecture_17/Request/" + name + ".json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
