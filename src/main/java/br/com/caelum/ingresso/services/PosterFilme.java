package br.com.caelum.ingresso.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PosterFilme {
	@JsonProperty("Poster")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
