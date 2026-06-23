package br.ucs.poo.tb2;

import java.io.File;

import br.ucs.poo.tb2.db.*;
import br.ucs.poo.tb2.menu.*;

public class Main {

	public static void main(String[] args) {
		//inicializacao de bases de dados
		//System.out.println("Working Directory = " + System.getProperty("user.dir"));
		File diretorio = new File(System.getProperty("user.dir"));
		TableFornecedor tabelaf = new TableFornecedor(diretorio);
		TableProduto tabelap = new TableProduto(diretorio);
		TableTransportadora tabelat = new TableTransportadora(diretorio);
		TableUser tabelau = new TableUser(diretorio);
		Menu menu = new Menu();
		
		//inicializacao de dados nas bases
		new DbBuilder(tabelaf, tabelap, tabelat, tabelau).inicializa();
		
		//roda menus
		menu.login(tabelaf,tabelap,tabelat,tabelau);
		
	}

}
