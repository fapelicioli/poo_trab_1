package br.ucs.poo.tb2.db;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

import br.ucs.poo.tb2.user.User;

public class TableUser implements Serializable{

	private Map<Integer, User> data;
	private int counter;
	File database;
	
	public TableUser(File diretorio) {
		
		try {
			database = new File(diretorio.getPath()+"/data/tableusuario.dat");
			database.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		data = new TreeMap<>();
		counter = 1;
	}
	
	public void SetDatabase(File diretorio) {
		database = new File(diretorio.getPath()+"/data/tableusuario.dat");
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

	public int incluir(User user) {
		int id = counter;
		user.setId(id);
		data.put(id, user);
		counter++;
		salvar();
		return id;
	}

	public void alterar(int id, User user) {
		data.remove(id);
		data.put(id, user);
		salvar();
	}

	public void excluir(int id) {
		data.remove(id);
		salvar();
	}

	public User consulta(int id) {
		return data.get(id);
	}
	
	public User consulta(String nome) {
		for(User i : data.values()) {
			String temp = i.getNome();
			if(temp.compareTo(nome) == 0) {
				return i;
			}
		}
		return null;
	}

	public Map<Integer, User> consultaCompleta() {
		return this.data;
	}
	
}
