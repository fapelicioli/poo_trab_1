package br.ucs.poo.tb2.cadastro;

import java.util.ArrayList;

public class Pedido extends Carga{

	private int idCliente;
    private String status;
    private Data compra;
    private Data cancelamento;
    private Data envio;
	
	public Pedido(ArrayList<Produto> produtos, int idCliente) {
		super(produtos);
		this.compra = new Data();
		this.idCliente = idCliente;
		this.status = "Criado";
		this.cancelamento = null;
		this.envio = null;
	}

	public float getValorTotal() {
		float total = 0;
		
		for(Produto prod : this.produtos) {
			total += prod.getValor();
		}
		
		return total;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}

	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Data getDataCompra() {
		return compra;
	}
	public void setDataCompra(Data data) {
		this.compra = data;
	}

	public Data getDataCancelamento() {
		return cancelamento;
	}
	public void setDataCancelamento(Data data) {
		this.cancelamento = data;
	}

	public Data getDataEnvio() {
		return envio;
	}
	public void setDataEnvio(Data data) {
		this.envio = data;
	}
	
}
