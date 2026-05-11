package br.ucs.poo.tb1.cadastro;

import java.util.ArrayList;

public class Carga{
	private int id;
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	public Carga(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	public void addProduto(Produto produto) {
		this.produtos.add(produto);
	}
	public void removeProduto(Produto produto) {
		this.produtos.remove(produto);
	}
	
}
