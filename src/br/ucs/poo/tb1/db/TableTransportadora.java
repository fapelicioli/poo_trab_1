package br.ucs.poo.tb1.db;

import java.util.Map;
import java.util.TreeMap;
import br.ucs.poo.tb1.cadastro.Transportadora;

public class TableTransportadora{

	Map<Integer, Transportadora> data = new TreeMap<>();
	
	public void incluir(int id, Transportadora transportadora) {
		data.put(id, transportadora);
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
			if(temp == nome) {
				return i;
			}
		}
		return null;
	}
	
	public Map<Integer, Transportadora> consultaCompleta() {
		return this.data;
	}

}
