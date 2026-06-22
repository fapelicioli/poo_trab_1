package br.ucs.poo.tb2.user;

public class Admin extends User{

	public Admin(String nome, String senha) {
		super(nome, senha);
		super.permlevel = 1;
	}
	
}