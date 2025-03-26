package org.example.app.task.service;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class TaskServiceTest extends Assertions {

    /** Test of {@link TaskService#findTaskList(Long)}. */
    @Test
    public void testFindTaskList() {

        given().when().get("/task/list/1").then().statusCode(200)
                .body(jsonEquals("{\"id\":1,\"version\":0,\"title\":\"Shopping List\"}"));
    }

    @Test
    public void testFindTaskItem(){
        given().when().get("/task/item/11").then().statusCode(200).body(jsonEquals(
                "{\"id\":11,\"version\":0,\"completed\":false,\"starred\":true,\"title\":\"Milk\"}"));
    }

    @Test
    public void testDeleteTaskList(){
        given().when().delete("/task/list/1").then().statusCode(204);
    }

    @Test
    public void testDeleteTaskItem(){
        given()
                .when()
                .delete("/task/item/11")
                .then()
                .statusCode(204);
    }

    @Test
    public void testSaveTaskListList(){
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"id\":8,\"version\":0,\"title\":\"Finish training\"}")
                .when()
                .post("/task/list")
                .then()
                .statusCode(201);

        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"id\":1,\"version\":0,\"title\":\"Shopping Liste\"}")
                .when()
                .post("/task/list")
                .then()
                .statusCode(200);
    }

    @Test
    public void testSaveTaskItem(){

        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"id\":11,\"version\":0,\"completed\":false,\"starred\":true,\"title\":\"Milk\"}")
                .when()
                .post("/task/item")
                .then()
                .statusCode(200);

        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"id\":11,\"version\":0,\"completed\":false,\"starred\":true,\"title\":\"Milk\"}")
                .when()
                .post("/task/item")
                .then()
                .statusCode(201);
    }

}
