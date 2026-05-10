package br.ucs.poo.tb1.cadastro;

import java.util.ArrayList;

public class Fornecedor extends Cadastro{

	String cnpj;
	ArrayList<Produto> produtos;
	
	public Fornecedor(String nome, String cnpj, ArrayList<Produto> produtos) {
		this.id = 0;
		this.nome = nome;
		this.cnpj = cnpj;
		this.produtos = produtos;
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
