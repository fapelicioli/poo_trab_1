package br.ucs.poo.tb2.db;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

import br.ucs.poo.tb2.cadastro.Transportadora;

public class TableTransportadora implements Serializable{

	private Map<Integer, Transportadora> data;
	private int counter;
	File database;
	private static final long serialVersionUID = 1L;
	
	public TableTransportadora(File diretorio) {
		database = new File(diretorio.getPath()+"/data/tabletransportadora.dat");
		try {
			database.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = new TreeMap<>();
		counter = 1;
	}
	
	public void SetDatabase(File diretorio) {
		database = new File(diretorio.getPath()+"/data/tabletransportadora.dat");
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
	
	public int incluir(Transportadora transportadora) {
		int id = counter;
		transportadora.setId(id);
		data.put(id, transportadora);
		counter++;
		salvar();
		return id;
	}

	public void alterar(int id, Transportadora transportadora) {
		data.remove(id);
		data.put(id, transportadora);
		salvar();
	}

	public void excluir(int id) {
		data.remove(id);
		salvar();
	}

	public Transportadora consulta(int id) {
		return data.get(id);
	}
	
	public Transportadora consulta(String nome) {
		for(Transportadora i : data.values()) {
			String temp = i.getNome();
			if(temp.compareTo(nome) == 0) {
				return i;
			}
		}
		return null;
	}
	
	public Map<Integer, Transportadora> consultaCompleta() {
		return this.data;
	}

}
