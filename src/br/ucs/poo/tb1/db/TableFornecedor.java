package br.ucs.poo.tb1.db;

import java.util.Map;
import java.util.TreeMap;
import br.ucs.poo.tb1.cadastro.Fornecedor;

public class TableFornecedor{
	
	Map<Integer, Fornecedor> data = new TreeMap<>();
	
	public void incluir(int id, Fornecedor fornecedor) {
		data.put(id, fornecedor);
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
			if(temp == nome) {
				return i;
			}
		}
		return null;
	}

}
