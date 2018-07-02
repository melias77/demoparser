package dk.example.web.demoparser.microservice.service;

import java.util.List;

import dk.example.web.demoparser.microservice.model.Forfatter;

public interface ForfatterParserService {

	Forfatter parseForfatterFullname(String forfatterText);

	List<Forfatter> parseForfatterFullnameList(String forfatterTextlist);

}
