package br.ucs.poo.tb2.db;

import java.util.Map;
import java.util.TreeMap;

import br.ucs.poo.tb2.cadastro.Transportadora;

public class TableTransportadora{

	private Map<Integer, Transportadora> data;
	private int counter;
	
	public TableTransportadora() {
		data = new TreeMap<>();
		counter = 1;
	}
	
	public int incluir(Transportadora transportadora) {
		int id = counter;
		transportadora.setId(id);
		data.put(id, transportadora);
		counter++;
		return id;
	}

	public void alterar(int id, Transportadora transportadora) {
		data.remove(id);
		data.put(id, transportadora);
	}

	public void excluir(int id) {
		data.remove(id);
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