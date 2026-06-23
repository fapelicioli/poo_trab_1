package br.ucs.poo.tb2.db;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import br.ucs.poo.tb2.cadastro.Fornecedor;

public class TableFornecedor{
	
	private Map<Integer, Fornecedor> data;
	private int counter;
	File database;
	
	public TableFornecedor(File diretorio) {
		
		try {
			database = new File(diretorio.getPath()+"/data/tablefornecedor.dat");
			database.createNewFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		data = new TreeMap<>();
		counter = 1;
	}
	
	public int incluir(Fornecedor fornecedor) {
		int id = counter;
		fornecedor.setId(id);
		data.put(id, fornecedor);
		try {
			FileOutputStream dataOut = new FileOutputStream(database.getPath());
			ObjectOutputStream objectOut = new ObjectOutputStream(dataOut);
			objectOut.writeObject(fornecedor);
			objectOut.close();
			dataOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		counter++;
		return id;
	}

	public void alterar(int id, Fornecedor fornecedor) {
		data.remove(id);
		data.put(id, fornecedor);
	}

	public void excluir(int id) {
		data.remove(id);
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
