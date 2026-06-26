package br.ucs.poo.tb2.user;

public class Admin extends User{

	public Admin(String nome, String senha) {
		super(nome, senha, "Interno");
		super.permlevel = 1;
	}
	
}
