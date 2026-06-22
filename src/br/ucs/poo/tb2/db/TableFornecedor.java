package br.ucs.poo.tb2.db;

import java.util.Map;
import java.util.TreeMap;

import br.ucs.poo.tb2.cadastro.Fornecedor;

public class TableFornecedor{
	
	private Map<Integer, Fornecedor> data;
	private int counter;
	
	public TableFornecedor() {
		data = new TreeMap<>();
		counter = 1;
	}
	
	public int incluir(Fornecedor fornecedor) {
		int id = counter;
		fornecedor.setId(id);
		data.put(id, fornecedor);
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