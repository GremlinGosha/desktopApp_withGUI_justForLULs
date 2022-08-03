package com.gmail.gremlin.gosha.demoBean;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class DemoBeanApplication {
	String url;
	GUI userInterface;

	public static void main(String[] args) {
		DemoBeanApplication app = new DemoBeanApplication();
		app.working();
	}

	void working(){
		System.out.println("Let's start))");
		url = "http://localhost:8080/clients";
		userInterface = new GUI();
		userInterface.go();
		userInterface.setMaster(this);
	}


	void addNewClient(String name, String email, String number) {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Client> requestBody = new HttpEntity<>(new Client(name,email,number));

		Client check = requestBody.getBody();
		System.out.println("Check Name:    " + check.getName());
		System.out.println("Check Email:   " + check.getEmail());
		System.out.println("Check Phone:   " + check.getPhone());
		//Client newClient2 = restTemplate.postForObject(url,requestBody,Client.class);
		restTemplate.postForObject(url,requestBody,Void.class);
	}

	void showAllClient() {
//		EXAMPLE
//		RestTemplate restTemplate = new RestTemplate();
//		String fooResourceUrl
//				= "http://localhost:8080/spring-rest/foos";
//		ResponseEntity<String> response
//				= restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
//		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

//		ResponseEntity<List<Rate>> rateResponse =
//				restTemplate.exchange("https://bitpay.com/api/rates",
//						HttpMethod.GET, null, new ParameterizedTypeReference<List<Rate>>() {
//						});
//		List<Rate> rates = rateResponse.getBody();


		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Client>> clientResponse =
				restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Client>>() {});
		List<Client> clients = clientResponse.getBody();

		String msg = new String();
		for (Client client : clients) {
			msg = msg + "Name: " + client.getName() + " Email: " + client.getEmail() + " Phone: " + client.getPhone() + "\n";
		}

		System.out.println("Clients (msg) ==" + msg);

		userInterface.outputAllClient(msg);
	}

	void showClient(int id) {
		RestTemplate restTemplate = new RestTemplate();

		String resourceUrl = url+"/"+id;
		System.out.println(resourceUrl);
		Client client = restTemplate.getForObject(resourceUrl, Client.class);

		userInterface.outputClient(client.getName(),client.getEmail(),client.getPhone());
	}

	void editClient(int id, String name, String email, String number) {
		RestTemplate restTemplate = new RestTemplate();

		String resourceUrl = url+"/"+id;
		System.out.println(resourceUrl);

		HttpHeaders header = new HttpHeaders();
		header.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		Client updatedClient = new Client();
		updatedClient.setName(name);
		updatedClient.setEmail(email);
		updatedClient.setPhone(number);

		HttpEntity<Client> requestBody = new HttpEntity<>(updatedClient, header);
		restTemplate.exchange(resourceUrl,HttpMethod.PUT,requestBody, Void.class);
	}

	void deleteClient (int id) {
		String resourceUrl = url+"/"+id;
		System.out.println(resourceUrl);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(resourceUrl);
	}
}
