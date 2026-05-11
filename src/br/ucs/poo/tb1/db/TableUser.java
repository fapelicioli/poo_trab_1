package br.ucs.poo.tb1.db;

import java.util.Map;
import java.util.TreeMap;
import br.ucs.poo.tb1.user.User;

public class TableUser{

	Map<Integer, User> data;
	int counter;
	
	public TableUser() {
		data = new TreeMap<>();
		counter = 1;
	}

	public int incluir(User user) {
		int id = counter;
		user.setId(id);
		data.put(id, user);
		counter++;
		return id;
	}

	public void alterar(int id, User user) {
		data.remove(id);
		data.put(id, user);
	}

	public void excluir(int id) {
		data.remove(id);
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
