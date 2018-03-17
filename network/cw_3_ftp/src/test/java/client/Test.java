package client;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test {
	
	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	public static void SendRequest(String isbn) throws ClientProtocolException, IOException {

		String requestUrl = "https://www.apress.com/gp/product-search/ajax/prices";
		
		try {
			
			Gson gson = new Gson();
			String requestBody = gson.toJson(Arrays.asList(new ABCD()));
			
			CloseableHttpClient client = HttpClientBuilder.create().build();
			RequestConfig config = RequestConfig
		      .custom()
		      .setConnectTimeout(5000)
		      .setConnectionRequestTimeout(5000)
		      .setSocketTimeout(5000)
		      .build();
			
			HttpPost 
				request = new HttpPost(requestUrl);
				request.setConfig(config);
				
				StringEntity requestEntity = new StringEntity(requestBody);
				request.setEntity(requestEntity);
				
				request.setHeader("Content-Type", "application/json");
				request.setHeader("Host", "www.apress.com");
				
				
			CloseableHttpResponse response = client.execute(request);
			
			for ( Header header : request.getAllHeaders() ) {
				System.out.println(header.getName() + "  :   " + header.getValue());
			}
			
			System.out.println(response.getStatusLine().toString());
			
			System.out.println(EntityUtils.toString(request.getEntity()));
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
//			System.out.println(responseString);
		  
			response.close();
		  
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
	    }
		
		
	}

	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
		
		SendRequest("");
	}
	

}




class ABCD {
	
	@Expose @SerializedName("id")
	private String id;
	
	@Expose @SerializedName("type")
	private String type;
	
	public ABCD() {
		this.id = "9781484228968";
		this.type = "book";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
