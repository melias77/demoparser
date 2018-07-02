package dk.example.web.demoparser.microservice.model;

public class Forfatter {

	private final String fornavne;
	private final String efternavn;

	public Forfatter(final String fornavne, final String efternavn) {
		super();
		this.fornavne = fornavne;
		this.efternavn = efternavn;
	}

	public String getFornavne() {
		return fornavne;
	}

	public String getEfternavn() {
		return efternavn;
	}

}
