package dk.example.web.demoparser.microservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.example.web.demoparser.microservice.helper.ForfatterFullnameParserHelper;
import dk.example.web.demoparser.microservice.model.Forfatter;
import dk.example.web.demoparser.microservice.service.ForfatterParserService;

@Service
public class ForfatterParserServiceImpl implements ForfatterParserService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private ForfatterFullnameParserHelper forfatterFullnameParserHelper;

	@Autowired
	public void setForfatterFullnameParserHelper(ForfatterFullnameParserHelper forfatterFullnameParserHelper) {
		this.forfatterFullnameParserHelper = forfatterFullnameParserHelper;
	}

	@Override
	public Forfatter parseForfatterFullname(String forfatterText) {
		return forfatterFullnameParserHelper.parseForfatterFullname(forfatterText);

	}

	@Override
	public List<Forfatter> parseForfatterFullnameList(String forfatterTextlist) {

		return forfatterFullnameParserHelper.parseForfatterFullnameList(forfatterTextlist);

	}

}
