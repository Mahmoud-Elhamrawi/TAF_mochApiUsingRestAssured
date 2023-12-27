package TestCases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import serilization.addUser;

import static io.restassured.RestAssured.given;

public class TC03_UpdateUser {
    TC02_GetUsers getUsers ;




    @Test
    public void updateUser() throws JsonProcessingException {
        addUser user = new addUser();
        user.setName("ahmed_elgandy20");
        user.setCountry("cairo");
        user.setAdmin("false");
        user.setAvatar("https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/8860.jpg");

        ObjectMapper mapper = new ObjectMapper() ;
        String dataUpdate =  mapper.writeValueAsString(user);

        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .pathParam("id",58)
                .contentType(ContentType.JSON)
                .body(dataUpdate)
                .when().put("/{user}/{id}") ;
        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() , 200);


        getUsers = new TC02_GetUsers() ;
        getUsers.getOneUser(58 ,"ahmed_elgandy20" );
    }




    @Test
    public void updateUserSpecificData() throws JsonProcessingException {
        addUser user = new addUser();
        user.setName("sanea");
        user.setCountry("plastin");

        ObjectMapper mapper = new ObjectMapper() ;
        String dataUpdate =  mapper.writeValueAsString(user);

        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .pathParam("id",53)
                .contentType(ContentType.JSON)
                .body(dataUpdate)
                .when().patch("/{user}/{id}") ;
        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() , 200);


        getUsers = new TC02_GetUsers() ;
        getUsers.getOneUser(53 ,"sanea" );
    }







}
