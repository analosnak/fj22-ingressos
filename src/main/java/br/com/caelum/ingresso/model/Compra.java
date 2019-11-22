package br.com.caelum.ingresso.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Compra {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String cpf;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Ingresso> ingressos = new ArrayList<>();

	@Deprecated
	public Compra() {}
	
	public Compra(Carrinho carrinho) {
		carrinho.getIngressos()
		.forEach(ingresso -> ingressos.add(ingresso));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
}
