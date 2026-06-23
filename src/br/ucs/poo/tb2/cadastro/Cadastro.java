package br.ucs.poo.tb2.cadastro;

import java.io.Serializable;

public abstract class Cadastro implements Serializable{
	
	protected int id;
	protected String nome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
