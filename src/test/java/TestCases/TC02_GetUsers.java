package TestCases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import serilization.addUser;

import static io.restassured.RestAssured.given;

public class TC02_GetUsers {

    //https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1/users
    @Test
    public void getAllUsers()
    {
        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .with().get("/{user}");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode() ,200);
        Assert.assertEquals(res.body().jsonPath().getInt("id[0]") , 1);
    }


    @DataProvider(name = "idUsers")
    public Object []ids()
    {
        addUser user = new addUser();
        Object idUsers [] ={1,2,3,4} ;
        return idUsers ;
    }
    @Test(dataProvider = "idUsers")
    public void getSpecificUser(int id) {
        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user", "users")
                .pathParam("id", id)
                .when().get("/{user}/{id}");
        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 200);
        for (int i = 0; i < ids().length; i++) {
            Assert.assertEquals(res.body().jsonPath().getInt("id"), id);
        }

    }

    @Test
    public void getOneUser(int id , String name)
    {
        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .pathParam("id",id)
                .when().get("/{user}/{id}") ;
        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() ,200);
        Assert.assertEquals(res.body().jsonPath().getInt("id") ,  id);
        Assert.assertEquals(res.body().jsonPath().getString("name") ,  name);

    }

    @Test
    public void getOneUserAbstract()
    {
        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .pathParam("id","58")
                .when().get("/{user}/{id}") ;
        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() ,200);
        Assert.assertEquals(res.body().jsonPath().getString("name") , "ahmed_labin");
        Assert.assertEquals(res.body().jsonPath().getString("admin") , "true");



    }

}
