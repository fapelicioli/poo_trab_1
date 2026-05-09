package br.ucs.poo.tb1.user;

public class Admin extends User{

	final int permlevel = 1;

	public Admin(int id, String nome, String senha) {
		super(id, nome, senha);
	}
	
}
