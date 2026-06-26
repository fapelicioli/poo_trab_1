package br.ucs.poo.tb2.user;

import java.util.Map;
import java.util.TreeMap;

import br.ucs.poo.tb2.cadastro.Pedido;

public class Cliente extends User{
	
	private Map<Integer,Pedido> pedidos;
	
	public Cliente(String nome, String senha) {
		super(nome, senha, "Externo");
		pedidos = new TreeMap<>();
	}
	
	public Map<Integer, Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(Map<Integer,Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public void addPedido(Pedido pedido) {
		pedido.setId(this.pedidos.size() + 1);
		this.pedidos.put(pedido.getId(), pedido);
	}

}
