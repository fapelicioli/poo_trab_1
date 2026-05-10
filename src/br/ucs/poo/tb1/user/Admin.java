package br.ucs.poo.tb1.user;

public class Admin extends User{

	final int permlevel = 1;

	public Admin(String nome, String senha) {
		super(nome, senha);
	}
	
}
