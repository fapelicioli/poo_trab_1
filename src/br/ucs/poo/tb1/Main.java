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
		Fornecedor fornecedor1 = new Fornecedor("azul","12345");
		Fornecedor fornecedor2 = new Fornecedor("rosa","12345");
		Produto produto1 = new Produto("Porta","P001.1",1);
		Produto produto2 = new Produto("Porta verde","P001.2",1);
		
		
		tabelau.incluir(admbase);
		tabelau.incluir(userbase);
		
		tabelaf.incluir(fornecedor1);
		tabelaf.incluir(fornecedor2);
		
		tabelap.incluir(produto1);
		tabelaf.consulta(1).addProduto(produto1);
		tabelap.incluir(produto2);
		tabelaf.consulta(1).addProduto(produto2);
		
		//menu.login(tabelau);
		
		menu.principal(tabelaf, tabelap, tabelat,tabelau);
		
	}

}
