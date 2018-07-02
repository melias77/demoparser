package dk.example.web.demoparser.microservice.helper;

import java.util.regex.Pattern;

public abstract class AbstractTextParserHelper<T> {

	public T doParseText(String textToBeParsed, String patternToBeUsed) {

		Pattern pattern = Pattern.compile(patternToBeUsed);
		
		return matches(textToBeParsed, pattern);

	}

	protected abstract T matches(String textToBeParsed, Pattern pattern);
}
