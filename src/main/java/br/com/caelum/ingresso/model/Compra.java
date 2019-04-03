package br.com.caelum.ingresso.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Compra {
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Ingresso> ingressos;

	/**
	 * @deprecated hibernate only
	 */
	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public Compra(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
}
