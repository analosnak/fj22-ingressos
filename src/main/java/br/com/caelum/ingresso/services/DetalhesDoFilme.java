package br.com.caelum.ingresso.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetalhesDoFilme {
	@JsonProperty("Poster")
	private String imagem;
	@JsonProperty("Year")
	private String ano;
	@JsonProperty("Director")
	private String diretores;

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getDiretores() {
		return diretores;
	}

	public void setDiretores(String diretor) {
		this.diretores = diretor;
	}

}
