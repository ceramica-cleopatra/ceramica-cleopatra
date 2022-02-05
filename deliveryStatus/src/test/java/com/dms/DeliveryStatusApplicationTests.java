package com.dms;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class DeliveryStatusApplicationTests {
	private static String createOrdrStatusUrl;
	private static RestTemplate restTemplate;
	private static HttpHeaders headers;
	private static JSONObject ordrStatusJsonObject;
	@BeforeClass
	public static void runBeforeAllTestMethods() {
		createOrdrStatusUrl = "http://localhost:8092/deliveryStatus/updateOrdrStatus";
	    restTemplate = new RestTemplate();
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("secured", "true");
	    ordrStatusJsonObject = new JSONObject();
	    try {
			ordrStatusJsonObject.put("ordrId", 1);
			ordrStatusJsonObject.put("deliveryDate", "01-01-2020 23:00:00");
			ordrStatusJsonObject.put("status", "success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
	@Test
	public void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull()
	  throws IOException {
	/*    HttpEntity<String> request = 
	      new HttpEntity<String>(ordrStatusJsonObject.toString(), headers);
	    
	    String ordrStatus = 
	      restTemplate.postForObject(createOrdrStatusUrl, request, String.class);
	    ObjectMapper objectMapper= new ObjectMapper();
	    JsonNode root = objectMapper.readTree(ordrStatus);
	    System.out.println("ordrStatus>>>>>"+ordrStatus);
	    assertNotNull(ordrStatus);
	    assertNotNull(root);
	    assertNotNull(root.path("ordrId").asText());
	    assertNotNull(root.path("status").asText());*/
	}
}
