package dk.example.web.demoparser.microservice.helper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import dk.example.web.demoparser.microservice.helper.AbstractTextParserHelper;
import dk.example.web.demoparser.microservice.helper.ForfatterFullnameParserHelper;
import dk.example.web.demoparser.microservice.model.Forfatter;

@Component
public class ForfatterFullnameParserHelperImpl extends AbstractTextParserHelper<Forfatter>
		implements ForfatterFullnameParserHelper {

	@Override
	public Forfatter parseForfatterFullname(String forfatterFullnameText) {
		// match teksten der ligger til venstre for det sidste whitespace i
		// strengen
		// og match det der ligger til højre for sidste whitespace i strengen.

		return doParseText(forfatterFullnameText, "^(.+?)\\s+(\\S+)$");

	}

	@Override
	public List<Forfatter> parseForfatterFullnameList(String forfatterFullnameList) {

		List<String> fullnameList = extractFullnamesFromString(forfatterFullnameList);

		List<Forfatter> forfatterList = new ArrayList<Forfatter>();
		for (String forfatterFullnameText : fullnameList) {

			forfatterList.add(parseForfatterFullname(forfatterFullnameText));

		}

		return forfatterList;

	}

	@Override
	protected Forfatter matches(String forfatterText, Pattern pattern) {

		if (forfatterText.matches("^[^,]*,[^,]*$")) {
			forfatterText = flipName(forfatterText);
		}

		Matcher matcher = pattern.matcher(forfatterText);

		Forfatter forfatter = null;
		if (matcher.find()) {

			String fornavne = matcher.group(1);
			String efternavn = matcher.group(2);

			forfatter = new Forfatter(fornavne.trim(), efternavn.trim());
		}

		return forfatter;

	}

	private String flipName(String textToBeParsed) {

		String parts[] = textToBeParsed.split(",");

		StringJoiner joiner = new StringJoiner(" ");

		joiner.add(parts[1]);
		joiner.add(parts[0]);

		return joiner.toString();

	}

	public List<String> extractFullnamesFromString(String fullNameStringLst) {

		List<String> fullNameList = new ArrayList<String>();

		String[] potentialFullnames = fullNameStringLst.split(",");

		StringJoiner joiner = null;

		for (String potentialFullname : potentialFullnames) {

			if (potentialFullname.trim().endsWith(".") || potentialFullname.trim().indexOf(' ') == -1) {

				if (Objects.isNull(joiner)) {
					joiner = new StringJoiner(", ");
					joiner.add(potentialFullname.trim());
				} else {
					joiner.add(potentialFullname.trim());
					fullNameList.add(joiner.toString());
					joiner = null;
				}
			} else {
				fullNameList.add(potentialFullname);
			}
		}
		return fullNameList;
	}

}
