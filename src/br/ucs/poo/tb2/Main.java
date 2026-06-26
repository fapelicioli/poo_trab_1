package br.ucs.poo.tb2;

import java.io.*;

import br.ucs.poo.tb2.db.*;
import br.ucs.poo.tb2.menu.*;

public class Main {

	public static void main(String[] args) {
		//inicializacao de bases de dados
		//System.out.println("Working Directory = " + System.getProperty("user.dir"));
		File diretorio = new File(System.getProperty("user.dir"));
		
		
		//TableFornecedor tabelaf = new TableFornecedor(diretorio);
		TableFornecedor tabelaf = null;
		try {
			FileInputStream dataIn = new FileInputStream(diretorio.getPath() + "/data/tablefornecedor.dat");
			ObjectInputStream in = new ObjectInputStream(dataIn);
			tabelaf = (TableFornecedor) in.readObject();
			dataIn.close();
			in.close();
			tabelaf.SetDatabase(diretorio);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//TableProduto tabelap = new TableProduto(diretorio);
		TableProduto tabelap = null;
		try {
			FileInputStream dataIn = new FileInputStream(diretorio.getPath() + "/data/tableproduto.dat");
			ObjectInputStream in = new ObjectInputStream(dataIn);
			tabelap = (TableProduto) in.readObject();
			dataIn.close();
			in.close();
			tabelap.SetDatabase(diretorio);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//TableTransportadora tabelat = new TableTransportadora(diretorio);
		TableTransportadora tabelat = null;
		try {
			FileInputStream dataIn = new FileInputStream(diretorio.getPath() + "/data/tabletransportadora.dat");
			ObjectInputStream in = new ObjectInputStream(dataIn);
			tabelat = (TableTransportadora) in.readObject();
			dataIn.close();
			in.close();
			tabelat.SetDatabase(diretorio);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//TableUser tabelau = new TableUser(diretorio);
		TableUser tabelau = null;
		Menu menu = new Menu();
		try {
			FileInputStream dataIn = new FileInputStream(diretorio.getPath() + "/data/tableusuario.dat");
			ObjectInputStream in = new ObjectInputStream(dataIn);
			tabelau = (TableUser) in.readObject();
			dataIn.close();
			in.close();
			tabelau.SetDatabase(diretorio);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//inicializacao de dados nas bases
		//new DbBuilder(tabelaf, tabelap, tabelat, tabelau).inicializa();
		
		//roda menus
		menu.login(tabelaf,tabelap,tabelat,tabelau);
		
	}

}
