package br.ucs.poo.tb1.user;

public class User {
	
	int id = 0;
	String nome = "user";
	String senha = "default";
	final int permlevel = 0;
	
	public User(int id, String nome, String senha) {
		super();
		this.id = id;
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
