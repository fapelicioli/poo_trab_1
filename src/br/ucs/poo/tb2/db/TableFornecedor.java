package br.ucs.poo.tb2.db;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import br.ucs.poo.tb2.cadastro.Fornecedor;

public class TableFornecedor implements Serializable{
	
	private Map<Integer, Fornecedor> data;
	private int counter;
	File database;
	private static final long serialVersionUID = 1L;
	
	public TableFornecedor(File diretorio) {
		database = new File(diretorio.getPath()+"/data/tablefornecedor.dat");
		try {
			database.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = new TreeMap<>();
		counter = 1;
	}
	
	public void SetDatabase(File diretorio) {
		database = new File(diretorio.getPath()+"/data/tablefornecedor.dat");
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
	
	public int incluir(Fornecedor fornecedor) {
		int id = counter;
		fornecedor.setId(id);
		data.put(id, fornecedor);
		counter++;
		salvar();
		return id;
	}

	public void alterar(int id, Fornecedor fornecedor) {
		data.remove(id);
		data.put(id, fornecedor);
		salvar();
	}

	public void excluir(int id) {
		data.remove(id);
		salvar();
	}

	public Fornecedor consulta(int id) {
		return data.get(id);
	}
	
	public Fornecedor consulta(String nome) {
		for(Fornecedor i : data.values()) {
			String temp = i.getNome();
			if(temp.compareTo(nome) == 0) {
				return i;
			}
		}
		return null;
	}
	
	public Map<Integer, Fornecedor> consultaCompleta() {
		return this.data;
	}

}
