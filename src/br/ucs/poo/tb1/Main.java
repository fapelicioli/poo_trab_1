package br.ucs.poo.tb1;

import br.ucs.poo.tb1.cadastro.*;
import br.ucs.poo.tb1.db.*;
import br.ucs.poo.tb1.user.*;
import br.ucs.poo.tb1.menu.*;

public class Main {

	public static void main(String[] args) {
		//inicializacao de base de dados
		TableFornecedor tabelaf = new TableFornecedor();
		TableProduto tabelap = new TableProduto();
		TableTransportadora tabelat = new TableTransportadora();
		TableUser tabelau = new TableUser();
		Menu menu = new Menu();
		
		//Inclui dados base na db
		Admin admbase = new Admin("admin", "admin");
		User userbase = new User("user","user");
		
		tabelau.incluir(admbase);
		tabelau.incluir(userbase);
		
		//menu.login(tabelau);
		
		menu.principal(tabelaf, tabelap, tabelat,tabelau);
		
	}

}
