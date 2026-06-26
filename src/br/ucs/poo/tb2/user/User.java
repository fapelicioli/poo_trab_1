package br.ucs.poo.tb2.user;

import java.io.Serializable;

public class User implements Serializable{
	
	private int id;
	private String nome;
	private String senha;
	private String local;
	protected int permlevel = 0;
	
	public User(String nome, String senha, String local) {
		this.id = 0;
		this.nome = nome;
		this.senha = senha;
		this.local = local;
	}
	
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getPermlevel() {
		return permlevel;
	}
	public String getLocal() {
		return local;
	}
	
}
