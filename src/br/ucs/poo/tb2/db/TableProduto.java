package br.ucs.poo.tb2.db;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

import br.ucs.poo.tb2.cadastro.Produto;

public class TableProduto implements Serializable{
	
	private Map<Integer, Produto> data;
	private int counter;
	File database;
	private static final long serialVersionUID = 1L;
	
	public TableProduto(File diretorio) {
		database = new File(diretorio.getPath()+"/data/tableproduto.dat");
		try {
			database.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = new TreeMap<>();
		counter = 1;
	}
	
	public void SetDatabase(File diretorio) {
		database = new File(diretorio.getPath()+"/data/tableproduto.dat");
		salvar();
	}
	
	public void salvar() {
		try {
			FileOutputStream dataOut = new FileOutputStream(database.getPath());
			ObjectOutputStream objectOut = new ObjectOutputStream(dataOut);
			objectOut.writeObject(this);
			objectOut.close();
			dataOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int incluir(Produto produto) {
		int id = counter;
		produto.setId(id);
		data.put(id, produto);
		counter++;
		salvar();
		return id;
	}

	public void alterar(int id, Produto produto) {
		data.remove(id);
		data.put(id, produto);
		salvar();
	}

	public void excluir(int id) {
		data.remove(id);
		salvar();
	}

	public Produto consulta(int id) {
		return data.get(id);
	}
	
	public Produto consulta(String nome) {
		for(Produto i : data.values()) {
			String temp = i.getNome();
			if(temp.compareTo(nome) == 0) {
				return i;
			}
		}
		return null;
	}
	
	public Produto consultaSku(String sku) {
		for(Produto i : data.values()) {
			String temp = i.getSku();
			if(temp.compareTo(sku) == 0) {
				return i;
			}
		}
		return null;
	}
	
	public Map<Integer, Produto> consultaCompleta() {
		return this.data;
	}

}
