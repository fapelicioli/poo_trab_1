package br.ucs.poo.tb2;

import br.ucs.poo.tb2.db.*;
import br.ucs.poo.tb2.menu.*;

public class Main {

	public static void main(String[] args) {
		//inicializacao de bases de dados
		TableFornecedor tabelaf = new TableFornecedor();
		TableProduto tabelap = new TableProduto();
		TableTransportadora tabelat = new TableTransportadora();
		TableUser tabelau = new TableUser();
		Menu menu = new Menu();
		
		//inicializacao de dados nas bases
		new DbBuilder(tabelaf, tabelap, tabelat, tabelau).inicializa();
		
		//roda menus
		menu.login(tabelaf,tabelap,tabelat,tabelau);
		
	}

}