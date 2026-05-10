package br.ucs.poo.tb1.db;

import java.util.Map;
import java.util.TreeMap;
import br.ucs.poo.tb1.cadastro.Produto;

public class TableProduto{
	
	Map<Integer, Produto> data = new TreeMap<>();
	
	public void incluir(int id, Produto produto) {
		data.put(id, produto);
	}

	public void alterar(int id, Produto produto) {
		data.remove(id);
		data.put(id, produto);
	}

	public void excluir(int id) {
		data.remove(id);
	}

	public Produto consulta(int id) {
		return data.get(id);
	}
	
	public Produto consulta(String nome) {
		for(Produto i : data.values()) {
			String temp = i.getNome();
			if(temp == nome) {
				return i;
			}
		}
		return null;
	}
	
	public Produto consultaSku(String sku) {
		for(Produto i : data.values()) {
			String temp = i.getSku();
			if(temp == sku) {
				return i;
			}
		}
		return null;
	}
	
	public Map<Integer, Produto> consultaCompleta() {
		return this.data;
	}

}
