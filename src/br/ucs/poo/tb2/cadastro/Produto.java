package br.ucs.poo.tb2.cadastro;

import br.ucs.poo.tb2.excecoes.*;

public class Produto extends Cadastro{
	
	private String sku;
	private int idFornecedor;
	private int quantidade;
	private float valor;
	
	public Produto(String nome, String sku, int idFornecedor, float valor) {
		this.id = 0;
		this.nome = nome;
		this.sku = sku;
		this.idFornecedor = idFornecedor;
		this.quantidade = 0;
	}
	public Produto(String nome, String sku, int idFornecedor,float valor, int quant) {
		this.id = 0;
		this.nome = nome;
		this.sku = sku;
		this.idFornecedor = idFornecedor;
		this.quantidade = quant;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public void removeEstoque(int quantidade)throws StockException{
		if(quantidade > this.quantidade) {
			throw new StockException("\nQuantidade insuficiente em estoque, estoque atual: " + this.quantidade);
		} else {
			this.quantidade -= quantidade;
		}
	}
	
}
