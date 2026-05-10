package br.ucs.poo.tb1.db;

import java.util.Map;
import java.util.TreeMap;
import br.ucs.poo.tb1.user.User;

public class TableUser{

	Map<Integer, User> data = new TreeMap<>();
	
	public void incluir(int id, User user) {
		data.put(id, user);
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
			if(temp == nome) {
				return i;
			}
		}
		return null;
	}

}
