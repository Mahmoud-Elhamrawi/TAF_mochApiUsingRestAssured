package TestCases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hemanthsridhar.CSVUtils;
import com.github.hemanthsridhar.lib.ExtUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import serilization.addUser;

import static io.restassured.RestAssured.given;

public class TC01_AddUser {



    @DataProvider(name = "userData")
    public static Object[][] provider() throws Exception{
         String path = "./src\\test\\resources\\user.csv";
        ExtUtils ext = new CSVUtils(path, true);
        return ext.parseData() ;
    }




    @Test(dataProvider = "userData")
    public void addUser(String name , String avatar  ,String country  , String admin, String id) throws JsonProcessingException {
        addUser user = new addUser();
        user.setName(name);
        user.setAvatar(avatar);
        user.setCountry(country);
        user.setAdmin(admin);
        user.setId(id);
        ObjectMapper mapper = new ObjectMapper();
        String data  = mapper.writeValueAsString(user);


        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .contentType(ContentType.JSON)
                .body(data)

                .when().post("/{user}");
        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() , 201);

    }










    @DataProvider(name = "newIDs")
    public Object [] idsAdd()
    {
         Object ids []  ={52,53,54,55} ;
        return ids ;
    }

    @Test(dataProvider = "newIDs" , dependsOnMethods = "addUser")
    public void gatAddingUser(int id)
    {
        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .pathParam("id",id)
                .when().get("/{user}/{id}");
        res.prettyPrint();
        Assert.assertEquals(res.statusCode() , 200);
    }




    @Test
    public void addOneUser() throws JsonProcessingException {
        addUser user = new addUser();
        user.setName("ahmed_labin");
        user.setAvatar("https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1159.jpg");
        user.setCountry("kafrDoir");
        user.setAdmin("true");
        ObjectMapper mapper = new ObjectMapper();
        String data  = mapper.writeValueAsString(user);


        Response res = given().baseUri("https://650faf7054d18aabfe9a2cdf.mockapi.io/api/v1")
                .pathParam("user","users")
                .contentType(ContentType.JSON)
                .body(data)

                .when().post("/{user}");
        res.prettyPrint() ;
        Assert.assertEquals(res.statusCode() , 201);

    }






}
