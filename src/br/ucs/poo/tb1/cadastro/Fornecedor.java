package br.ucs.poo.tb1.cadastro;

import java.util.ArrayList;

public class Fornecedor extends Cadastro{

	private String cnpj;
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	public Fornecedor(String nome, String cnpj) {
		this.id = 00;
		this.nome = nome;
		this.cnpj = cnpj;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
