package introsde.rest.adapters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ejb.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import org.json.*;



@SuppressWarnings("deprecation")
@Stateless
@LocalBean
@Path("/adapter")
public class Adapter {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces({ MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getInfo() {
		System.out.println("Getting api information...");
		return "Hello! This is an adapter service by M.Haver";
	}
     //Getting a motivation quote from quotesondesign
     @GET
     @Path("/getQuote")
     public Response getQuote() throws ClientProtocolException, IOException {
        
        String ENDPOINT = "http://quotesondesign.com/api/3.0/api-3.0.json";

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(ENDPOINT);
        HttpResponse response = client.execute(request);
        
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        
        JSONObject o = new JSONObject(result.toString());
        
        if(response.getStatusLine().getStatusCode() == 200){
            return Response.ok(o.toString()).build();
         }
        
        return Response.status(204).build();
        
     }
   //Getting a motivation quote from forismatic
     @GET
     @Path("/getQuote2")
     public Response getQuote2() throws ClientProtocolException, IOException {
        
        String ENDPOINT = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(ENDPOINT);
        HttpResponse response = client.execute(request);
        
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        
        JSONObject o = new JSONObject(result.toString());
        
        if(response.getStatusLine().getStatusCode() == 200){
            return Response.ok(o.toString()).build();
         }
        
        return Response.status(204).build();
        
     }
}