package es.uma.rysd.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import es.uma.rysd.entities.*;

public class SWClient {
	// Complete the application name
	private final String app_name = "CamperoPollo";
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
		int res = 0;
		//	Handle possible exceptions appropriately

		try {
			// Create the corresponding URL: https://swapi.dev/api/{resource}/ replacing resource with the parameter
			URL urlname = new URL (url_api + resource + "/");
			
			// Create the connection from the URL
			HttpsURLConnection connection = (HttpsURLConnection) urlname.openConnection();
			
			// Add the headers User-Agent and Accept (see the statement)
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name + "-" + year);


			// Indicate that it is a GET request
			connection.setRequestMethod("GET");



			// Check that the response code received is correct
			int responseCode = connection.getResponseCode(); // Nos permite obtener el codigo que se ha obtenido de la respuesta. Obtenemos un numero entero que será 200
			//Para saber si la consulta se ha hecho correctamente
			
			if(responseCode / 100 != 2) {
				System.err.println("ERROR: " + responseCode + connection.getResponseMessage());
				return 0; //Si no es correcto salimos del metodo sin que haga nada
			}
			
			
			

			// Deserialize the response to ResourceCountResponse			
			Gson parser = new Gson();
			InputStream in = null;
			try {
				in = connection.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Get the InputStream from the connection
			ResourceCountResult c = parser.fromJson(new InputStreamReader(in), ResourceCountResult.class);
			// TODO: Return the number of elements
			res = c.count;
			
			
			
			
		} catch (MalformedURLException e) {
			// Auto-generated catch block
			System.err.println("URL incorrecta");
			e.printStackTrace();
		} catch (IOException e) {
			// Auto-generated catch block

			e.printStackTrace();
		}


		
		// Return the number of elements
		return res;
	}

	public Person getPerson(String urlname){
		Person p = null;
		// Just in case it comes as http, we change it to https
		urlname = urlname.replaceAll("http:", "https:");
		
		URL url;

		// Handle possible exceptions appropriately
		try {
			url = new URL(urlname);
			
			// Create the connection from the received URL
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			//Add the headers User-Agent and Accept (see the statement)
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name + "-" + year);
			
			// Indicate that it is a GET request
			connection.setRequestMethod("GET");

			// Check that the response code received is correct
			int responseCode = connection.getResponseCode(); // Nos permite obtener el codigo que se ha obtenido de la respuesta. Obtenemos un numero entero que será 200

			if(responseCode / 100 != 2) {
				System.err.println("ERROR: " + responseCode + connection.getResponseMessage());
				return null; //Si no es correcto salimos del metodo sin que haga nada
			}
			
			
			// Deserialize the response to Person
			Gson parser = new Gson(); //Creamos el parser con la clase Gson
			InputStream in = connection.getInputStream();
			// Get the InputStream from the connection
			p = parser.fromJson(new InputStreamReader(in), Person.class); //Para los otros metodos seria people.class, planet.class ...
			

		}catch (MalformedURLException e) {
			// Auto-generated catch block
			System.err.println("URL incorrecta");
			e.printStackTrace();
		} catch (IOException e) {
			// Auto-generated catch block
			System.err.println("Error: no se puede conectar");

			e.printStackTrace();
		}

		
		// From the URL in the homeworld field, get the planet data and store it in the homeplanet attribute
		World world = getWorld(p.homeworld);
		p.homeplanet = world;
		

		return p;
	}

	public World getWorld(String urlname) {
		World p = null;
		// Just in case it comes as http, we change it to https
		urlname = urlname.replaceAll("http:", "https:");
		
		URL url;

		// Handle possible exceptions appropriately
		try {
			url = new URL(urlname);
			
			// Create the connection from the received URL
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			//Add the headers User-Agent and Accept (see the statement)
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name + "-" + year);
			
			// Indicate that it is a GET request
			connection.setRequestMethod("GET");

			// Check that the response code received is correct
			int responseCode = connection.getResponseCode(); // Nos permite obtener el codigo que se ha obtenido de la respuesta. Obtenemos un numero entero que será 200

			if(responseCode / 100 != 2) {
				System.err.println("ERROR: " + responseCode + connection.getResponseMessage());
				return null; //Si no es correcto salimos del metodo sin que haga nada
			}
			
			
			// Deserialize the response to Person
			Gson parser = new Gson(); //Creamos el parser con la clase Gson
			InputStream in = connection.getInputStream();
			// Get the InputStream from the connection
			p = parser.fromJson(new InputStreamReader(in), World.class); //Para los otros metodos seria people.class, planet.class ...
			

		}catch (MalformedURLException e) {
			// Auto-generated catch block
			System.err.println("URL incorrecta");
			e.printStackTrace();
		} catch (IOException e) {
			// Auto-generated catch block

			e.printStackTrace();
		}


		

		return p;
	}

	public Person searchPersonByName(String name){
		Person p = null;
		// Handle possible exceptions appropriately
		try {
			
			// Create the connection from the URL (url_api + name processed - see the statement)
			name = URLEncoder.encode(name,"utf-8");

			URL url = new URL(url_api + "/people/?search=" + name + "/");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			// Add the headers User-Agent and Accept (see the statement)
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name + "-" + year);
			
			// Indicate that it is a GET request
			connection.setRequestMethod("GET");
					
			// Check that the response code received is correct
			int responseCode = connection.getResponseCode(); // Nos permite obtener el codigo que se ha obtenido de la respuesta. Obtenemos un numero entero que será 200

			if(responseCode / 100 != 2) {
				System.err.println("ERROR: " + responseCode + connection.getResponseMessage());
				return null; //Si no es correcto salimos del metodo sin que haga nada
			}
			
			// Deserialize the response to SearchResponse -> Use the first position of the array as the result
			Gson parser = new Gson();
			InputStream in = connection.getInputStream();
			QueryResponse r = parser.fromJson(new InputStreamReader(in), QueryResponse.class);
			
			if(r.count > 0) {
				p = r.results[0];
				
				// From the URL in the homeworld field, get the planet data and store it in the homeplanet attribute
				
				World w = getWorld(p.homeworld);
				p.homeplanet = w;
			}
					
			
		} catch (MalformedURLException e) {
			System.err.println("URL incorrecta");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}



		return p;
	}
	
	public Vehicle getVehicle(String urlname) {
		Vehicle p = null;
		// Just in case it comes as http, we change it to https
		urlname = urlname.replaceAll("http:", "https:");
		
		URL url;

		// Handle possible exceptions appropriately
		try {
			url = new URL(urlname);
			
			// Create the connection from the received URL
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			//Add the headers User-Agent and Accept (see the statement)
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name + "-" + year);
			
			// Indicate that it is a GET request
			connection.setRequestMethod("GET");

			// Check that the response code received is correct
			int responseCode = connection.getResponseCode(); // Nos permite obtener el codigo que se ha obtenido de la respuesta. Obtenemos un numero entero que será 200

			if(responseCode / 100 != 2) {
				System.err.println("ERROR: " + responseCode + connection.getResponseMessage());
				return null; //Si no es correcto salimos del metodo sin que haga nada
			}
			
			
			// Deserialize the response to Person
			Gson parser = new Gson(); //Creamos el parser con la clase Gson
			InputStream in = connection.getInputStream();
			// Get the InputStream from the connection
			p = parser.fromJson(new InputStreamReader(in), Vehicle.class); //Para los otros metodos seria people.class, planet.class ...
			

		}catch (MalformedURLException e) {
			// Auto-generated catch block
			System.err.println("URL incorrecta");
			e.printStackTrace();
		} catch (IOException e) {
			// Auto-generated catch block

			e.printStackTrace();
		}


		

		return p;
	}
	

}
