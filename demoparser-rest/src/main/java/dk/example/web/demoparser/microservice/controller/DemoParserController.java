
package dk.example.web.demoparser.microservice.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dk.example.web.demoparser.microservice.service.ForfatterParserService;

@RestController
@RequestMapping("/elsevier")
public class DemoParserController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private ForfatterParserService forfatterParserService;

	@Autowired
	public void setForfatterService(ForfatterParserService forfatterParserService) {
		this.forfatterParserService = forfatterParserService;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public ResponseEntity<?> ping() {
		String message = " -->rest service is available.......";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	
	
	@RequestMapping(value = "/parser/forfatter/{forfatterText:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> parseForfatterText(@PathVariable("forfatterText") String forfatterText) {

		return new ResponseEntity<>(forfatterParserService.parseForfatterFullname(decode(forfatterText)), HttpStatus.OK);

	}

	@RequestMapping(value = "/parser/forfatterliste/{forfatterListe:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> parseForfatterTextList(@PathVariable("forfatterListe") String forfatterListe) {

		return new ResponseEntity<>(forfatterParserService.parseForfatterFullnameList(decode(forfatterListe)), HttpStatus.OK);

	}

	private String decode(String value)  {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			return value;
		}
	}

}
