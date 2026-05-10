package br.ucs.poo.tb1.user;

public class User {
	
	int id;
	String nome;
	String senha;
	final int permlevel = 0;
	
	public User(String nome, String senha) {
		this.id = 0;
		this.nome = nome;
		this.senha = senha;
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
	
}
