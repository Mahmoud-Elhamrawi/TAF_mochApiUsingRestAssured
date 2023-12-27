package TestCases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC04_DeleteUser {


    @Test
    public void deleteUser()
    {
        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .pathParam("id",58)
                .when().delete("/{user}/{id}") ;

        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() , 200 );


    }



    @Test(dependsOnMethods = "deleteUser")
    public void getOneUser()
    {
        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .pathParam("id",58)
                .when().get("/{user}/{id}") ;
        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() ,404);


    }



}
