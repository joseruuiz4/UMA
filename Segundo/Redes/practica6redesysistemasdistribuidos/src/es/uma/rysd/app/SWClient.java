package es.uma.rysd.app;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import es.uma.rysd.entities.*;

public class SWClient {
	// TODO: Complete the application name
	private final String app_name = "";
	private final int year = 2024;

	private final String url_api = "https://swapi.dev/api/";

	// Auxiliary methods provided

	// Gets the URL of the resource id of the type resource
	public String buildResourceUrl(String resource, Integer id){
		return url_api + resource + "/" + id + "/";
	}

	// Given a resource URL, gets its ID
	public Integer extractIdFromUrl(String url){
		String[] parts = url.split("/");

		return Integer.parseInt(parts[parts.length-1]);
	}

	// Queries a resource and returns how many elements it has
	public int countResources(String resource){
		// TODO: Handle possible exceptions appropriately

		// TODO: Create the corresponding URL: https://swapi.dev/api/{resource}/ replacing resource with the parameter

		// TODO: Create the connection from the URL

		// TODO: Add the headers User-Agent and Accept (see the statement)

		// TODO: Indicate that it is a GET request

		// TODO: Check that the response code received is correct

		// TODO: Deserialize the response to ResourceCountResponse
		Gson parser = new Gson();
		InputStream in = null; // TODO: Get the InputStream from the connection
		ResourceCountResult c = parser.fromJson(new InputStreamReader(in), ResourceCountResult.class);
		// TODO: Return the number of elements
		return 0;
	}

	public Person getPerson(String urlname) {
		Person p = null;
		// Just in case it comes as http, we change it to https
		urlname = urlname.replaceAll("http:", "https:");

		// TODO: Handle possible exceptions appropriately

		// TODO: Create the connection from the received URL

		// TODO: Add the headers User-Agent and Accept (see the statement)

		// TODO: Indicate that it is a GET request

		// TODO: Check that the response code received is correct

		// TODO: Deserialize the response to Person

		// TODO: For questions 2 and 3 (do not need to complete this for question 1)
		// TODO: From the URL in the homeworld field, get the planet data and store it in the homeplanet attribute

		return p;
	}

	public World getWorld(String urlname) {
		World p = null;
		// Just in case it comes as http, we change it to https
		urlname = urlname.replaceAll("http:", "https:");

		// TODO: Handle possible exceptions appropriately

		// TODO: Create the connection from the received URL

		// TODO: Add the headers User-Agent and Accept (see the statement)

		// TODO: Indicate that it is a GET request

		// TODO: Check that the response code received is correct

		// TODO: Deserialize the response to Planet

		return p;
	}

	public Person searchPersonByName(String name){
		Person p = null;
		// TODO: Handle possible exceptions appropriately

		// TODO: Create the connection from the URL (url_api + name processed - see the statement)

		// TODO: Add the headers User-Agent and Accept (see the statement)

		// TODO: Indicate that it is a GET request

		// TODO: Check that the response code received is correct

		// TODO: Deserialize the response to SearchResponse -> Use the first position of the array as the result

		// TODO: For questions 2 and 3 (do not need to complete this for question 1)
		// TODO: From the URL in the homeworld field, get the planet data and store it in the homeplanet attribute

		return p;
	}

}
