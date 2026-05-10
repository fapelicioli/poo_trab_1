package br.ucs.poo.tb1.cadastro;

public class Produto extends Cadastro{
	
	String sku;
	int idFornecedor;
	
	public Produto(String nome, String sku, int idFornecedor) {
		this.id = 0;
		this.nome = nome;
		this.sku = sku;
		this.idFornecedor = idFornecedor;
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
	
	
}
