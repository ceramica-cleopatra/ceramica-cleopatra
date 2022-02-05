package com.dms.driverTracking.ccgOracleSync;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@EnableScheduling
@Component
public class TrnsOrdrStatusSync {

	/*@Scheduled(fixedDelay = 600000)
	public void syncTrnsOrdrStatus() {
		String filePath = System.getProperty("user.home") + System.getProperty("file.separator") + "syncModifiedDate.txt";
		final String url = "http://lgx.ccg.com.eg:80", db = "CCG", username = "EAI", password = "EA!@2o20";
		File file = new File(filePath);
		Date startDate = new Date();
		Date lastModifiedDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try (ReversedLinesFileReader reader = new ReversedLinesFileReader(file, StandardCharsets.UTF_8)) {
			String lastLine = reader.readLine();
			lastModifiedDate = dateFormat.parse(lastLine);
		}catch(java.text.ParseException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		final XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();
		final XmlRpcClient client = new XmlRpcClient();
		try {
			common_config.setServerURL(new URL(String.format("%s/xmlrpc/2/common", url)));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		int uid = 0;
		try {
			uid = (int) client.execute(common_config, "authenticate",
					Arrays.asList(db, username, password, Collections.EMPTY_MAP));
		} catch (XmlRpcException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(uid); // print the id of authenticated username
		XmlRpcClient models = null;
		try {
			models = new XmlRpcClient() {
				{
					setConfig(new XmlRpcClientConfigImpl() {
						{
							setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
						}
					});
				}
			};
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Object saleOrderIds=null;
		try {
			saleOrderIds= Arrays.asList((Object[])models.execute("execute_kw",
					Arrays.asList(db, uid, password,"x_orders", "search",Arrays.asList(Arrays.asList(
					Arrays.asList("write_date", ">", "20200327T232808Z"))) //Order Number
					)));
		//	System.out.println(saleOrderIds.toString());
		} catch (XmlRpcException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		Object SaleOrderRes;
		try {
			SaleOrderRes = Arrays.asList((Object[])models.execute("execute_kw",
					Arrays.asList(db, uid, password,"x_orders", "read",Arrays.asList(saleOrderIds),
					new HashMap() {{put("fields", Arrays.asList( //add any field you need to retrieved
					"id",
					"create_date",
					"create_uid",
					"__last_update",
					"write_date",
					"write_uid",
					"x_Order_Rand",
					"x_customer_lat",
					"x_customer_long",
					"x_name",
					"x_price",
					"x_qty",
					"x_studio_arrived_date",
					"x_studio_comments",
					"x_studio_customer_address",
					"x_studio_customer_name",
					"x_studio_lat",
					"x_studio_long",
					"x_studio_phone",
					"x_studio_status"
					));
					}}
					)));
		//	System.out.println(SaleOrderRes.toString());
			FileUtils.writeStringToFile(file, dateFormat.format(startDate)+"\n",true);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
				

	}*/
}
