
package dk.example.web.demoparser.microservice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author mnb@kmd.dk
 *
 */

public class DemoParserClientApplication {

	final static String[] names = { "John Doe", "Doe, John", "Hans-Christian Jensen", "H-C Jensen", "P. H. Kristensen",
			"Kristensen, P. H.", "Peter Hans Kristensen", "Peter H. Kristensen" };

	public static void main(String[] args) throws RestClientException, UnsupportedEncodingException {

		// Opgave 1.

		String uri = "http://localhost:8081/elsevier/parser";

		RestTemplate restTemplate = new RestTemplate();

		for (String name : names) {

			String encodenurl = uri + "/forfatter/" + URLEncoder.encode(name, "UTF-8");
			String result = restTemplate.getForObject(encodenurl, String.class);

			System.out.println(result);

		}
		// Opgave 2.

		String oneString = String.join(", ", names);

		System.out.println("Request string: " + oneString);

		String encodenurl = uri + "/forfatterliste/" + URLEncoder.encode(oneString, "UTF-8");

		String result = restTemplate.getForObject(encodenurl, String.class);

		System.out.println(result);

	}

}
