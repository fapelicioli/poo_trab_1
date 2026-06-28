package br.ucs.poo.tb2;

import java.io.*;

import br.ucs.poo.tb2.db.*;
import br.ucs.poo.tb2.menu.*;

public class Main {

	public static void main(String[] args) {
		//inicializacao de bases de dados
		//System.out.println("Working Directory = " + System.getProperty("user.dir"));
		File diretorio = new File(System.getProperty("user.dir"));
		
		TableFornecedor tabelaf = null;
		try {
			FileInputStream dataIn = new FileInputStream(diretorio.getPath() + "/data/tablefornecedor.dat");
			ObjectInputStream in = new ObjectInputStream(dataIn);
			tabelaf = (TableFornecedor) in.readObject();
			dataIn.close();
			in.close();
			tabelaf.SetDatabase(diretorio);
		} catch (FileNotFoundException | EOFException e) {
			tabelaf = new TableFornecedor(diretorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TableProduto tabelap = null;
		try {
			FileInputStream dataIn = new FileInputStream(diretorio.getPath() + "/data/tableproduto.dat");
			ObjectInputStream in = new ObjectInputStream(dataIn);
			tabelap = (TableProduto) in.readObject();
			dataIn.close();
			in.close();
			tabelap.SetDatabase(diretorio);
		} catch (FileNotFoundException | EOFException e) {
			tabelap = new TableProduto(diretorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TableTransportadora tabelat = null;
		try {
			FileInputStream dataIn = new FileInputStream(diretorio.getPath() + "/data/tabletransportadora.dat");
			ObjectInputStream in = new ObjectInputStream(dataIn);
			tabelat = (TableTransportadora) in.readObject();
			dataIn.close();
			in.close();
			tabelat.SetDatabase(diretorio);
		} catch (FileNotFoundException | EOFException e) {
			tabelat = new TableTransportadora(diretorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TableUser tabelau = null;
		try {
			FileInputStream dataIn = new FileInputStream(diretorio.getPath() + "/data/tableusuario.dat");
			ObjectInputStream in = new ObjectInputStream(dataIn);
			tabelau = (TableUser) in.readObject();
			dataIn.close();
			in.close();
			tabelau.SetDatabase(diretorio);
		} catch (FileNotFoundException | EOFException e) {
			tabelau = new TableUser(diretorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Menu menu = new Menu();
		
		//inicializacao de dados nas bases
		new DbBuilder(tabelaf, tabelap, tabelat, tabelau).inicializa();
		
		//roda menus
		menu.inicio(tabelaf,tabelap,tabelat,tabelau);
		
	}

}
