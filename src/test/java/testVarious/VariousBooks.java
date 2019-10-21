package testVarious;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VariousBooks {
			
		@Test
		public void AddBook() throws IOException
		{
			//Put in for the number of Lines of your sheet
			for(int i = 1;i<=99;i++) {
			
			String row = "livro"+i; 	
			dataDrivenList d=new dataDrivenList();
			ArrayList data=d.getDataL(row);
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("name", data.get(1));
			map.put("isbn", data.get(2));
			map.put("aisle", data.get(3));
			map.put("author", data.get(4));
			
			RestAssured.baseURI="http://216.10.245.166";
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			request.body(map);
			
			Response response = request.post("/Library/Addbook.php");
				
			//Show Response
			String responseString = response.asString();
			System.out.println(responseString);
			
			
			//Take the fields that you want to validate
			int statusCode = response.getStatusCode();
			
			//Validate them 
			Assert.assertEquals(statusCode, 200);
				
		}
		}
		
}

