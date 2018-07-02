package dk.example.web.demoparser.microservice.helper;

import java.util.List;

import dk.example.web.demoparser.microservice.model.Forfatter;

public interface ForfatterFullnameParserHelper {

	Forfatter parseForfatterFullname(String forfatterFullnameText);

	List<Forfatter> parseForfatterFullnameList(String forfatterFullnameList);

}
