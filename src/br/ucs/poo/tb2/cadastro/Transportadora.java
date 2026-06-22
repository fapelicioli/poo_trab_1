package br.ucs.poo.tb2.cadastro;

import java.util.Map;
import java.util.TreeMap;

public class Transportadora extends Fornecedor{

	private Map<Integer,Carga> cargas;
	
	public Transportadora(String nome, String cnpj) {
		super(nome, cnpj);
		cargas = new TreeMap<>();
	}
	public Map<Integer, Carga> getCargas() {
		return cargas;
	}
	public void setCargas(Map<Integer,Carga> cargas) {
		this.cargas = cargas;
	}
	public void addCarga(Carga carga) {
		carga.setId(this.cargas.size() + 1);
		this.cargas.put(carga.getId(), carga);
	}
	public void removeCarga(int id) {
		this.cargas.remove(id);
	}
}