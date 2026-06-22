package br.ucs.poo.tb2.db;

import java.util.Map;
import java.util.TreeMap;

import br.ucs.poo.tb2.cadastro.Produto;

public class TableProduto{
	
	private Map<Integer, Produto> data;
	private int counter;
	
	public TableProduto() {
		data = new TreeMap<>();
		counter = 1;
	}
	
	public int incluir(Produto produto) {
		int id = counter;
		produto.setId(id);
		data.put(id, produto);
		counter++;
		return id;
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
			if(temp.compareTo(nome) == 0) {
				return i;
			}
		}
		return null;
	}
	
	public Produto consultaSku(String sku) {
		for(Produto i : data.values()) {
			String temp = i.getSku();
			if(temp.compareTo(sku) == 0) {
				return i;
			}
		}
		return null;
	}
	
	public Map<Integer, Produto> consultaCompleta() {
		return this.data;
	}

}