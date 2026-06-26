package br.ucs.poo.tb2.db;

import java.util.ArrayList;

import br.ucs.poo.tb2.cadastro.*;
import br.ucs.poo.tb2.user.*;

public class DbBuilder {
	TableFornecedor tabelaf;
	TableProduto tabelap;
	TableTransportadora tabelat;
	TableUser tabelau;
	
	public DbBuilder(TableFornecedor tabelaf, TableProduto tabelap, TableTransportadora tabelat, TableUser tabelau) {
		this.tabelaf = tabelaf;
		this.tabelap = tabelap;
		this.tabelat = tabelat;
		this.tabelau = tabelau;
	}
	
	public void inicializa() {
		
		Admin admbase = new Admin("admin", "admin");
		User userbase = new User("user","user");
		
		tabelau.incluir(admbase);
		tabelau.incluir(userbase);
		
		
		Fornecedor fornecedor = new Fornecedor("P & J LTDA","30.973.354/0001-83");
		tabelaf.incluir(fornecedor);
		Produto produto = new Produto("Porta madeirada","P001.1",1,250.00f,10);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Porta preta","P001.2",1,250.00f,15);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Porta branca","P001.3",1,250.00f,5);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Janela vitral transparente","J001.1",1,150.00f,15);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Janela vitral colorida","J001.2",1,150.00f,5);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Janela madeirada ipe","J002.1",1,150.00f,12);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Janela madeirada acacia","J002.2",1,150.00f,4);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		
		
		fornecedor = new Fornecedor("Cica-Cola Refrigerantes & Bebidas","84.157.771/0001-87");
		tabelaf.incluir(fornecedor);
		produto = new Produto("Refrigerante de cola","R100.1",2,2.50f,20);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Refrigerante de laranja","R100.2",2,2.50f,25);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Refrigerante de cereja","R100.5",2,3.00f,30);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Agua mineral sem gas","A101.1",2,1.50f,50);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Agua mineral com gas","A101.2",2,1.50f,50);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Suco de uva","S256.1",2,3.00f,20);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Agua tonica","A113.1",2,2.00f,40);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		
		
		fornecedor = new Fornecedor("SaborFood Salgados & Doces","57.415.130/0001-02");
		tabelaf.incluir(fornecedor);
		produto = new Produto("Coxinha congelada","C4968.1",3,45.50f,5);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Pao preto caseiro","Pc101.1",3,8.00f,3);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Cereal de frutas","Ce142.1",3,20.25f,2);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Pao de forma caseiro","Pc100.1",3,8.00f,5);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Lasanha congelada de frango","C4985.1",3,40.99f,10);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Lasanha congelada 4 queijos","C4985.4",3,40.99f,15);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Sorvete de limao","S2690.8",3,18.50f,25);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		
		
		Transportadora transportadora = new Transportadora("Carro Rapido Transportes","60.266.161/0001-53");
		ArrayList<Produto> produtos= new ArrayList<Produto>();
		produtos.add(tabelap.consulta(15));
		produtos.add(tabelap.consulta(18));
		produtos.add(tabelap.consulta(20));
		Carga carga = new Carga(produtos);
		transportadora.addCarga(carga);
		
		produtos = new ArrayList<Produto>();
		produtos.add(tabelap.consulta(2));
		produtos.add(tabelap.consulta(3));
		produtos.add(tabelap.consulta(5));
		produtos.add(tabelap.consulta(6));
		produtos.add(tabelap.consulta(7));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);
		
		produtos = new ArrayList<Produto>();
		produtos.add(tabelap.consulta(9));
		produtos.add(tabelap.consulta(10));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);
		
		produtos = new ArrayList<Produto>();
		produtos.add(tabelap.consulta(11));
		produtos.add(tabelap.consulta(21));
		produtos.add(tabelap.consulta(3));
		produtos.add(tabelap.consulta(6));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);
		tabelat.incluir(transportadora);
		
		produtos = new ArrayList<Produto>();
	    transportadora = new Transportadora("Voa Vento","53.168.642/0001-60");
		produtos= new ArrayList<Produto>();
		produtos.add(tabelap.consulta(15));
		produtos.add(tabelap.consulta(18));
		produtos.add(tabelap.consulta(20));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);
		
		produtos = new ArrayList<Produto>();
		produtos.add(tabelap.consulta(2));
		produtos.add(tabelap.consulta(3));
		produtos.add(tabelap.consulta(5));
		produtos.add(tabelap.consulta(6));
		produtos.add(tabelap.consulta(7));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);

		produtos = new ArrayList<Produto>();
		produtos.add(tabelap.consulta(9));
		produtos.add(tabelap.consulta(10));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);

		produtos = new ArrayList<Produto>();		
		produtos.add(tabelap.consulta(11));
		produtos.add(tabelap.consulta(21));
		produtos.add(tabelap.consulta(3));
		produtos.add(tabelap.consulta(6));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);
		tabelat.incluir(transportadora);
		
		produtos = new ArrayList<Produto>();
	    transportadora = new Transportadora("Rio Lento","62.502.352/0001-00");
		produtos= new ArrayList<Produto>();
		produtos.add(tabelap.consulta(15));
		produtos.add(tabelap.consulta(18));
		produtos.add(tabelap.consulta(20));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);

		produtos = new ArrayList<Produto>();
		produtos.add(tabelap.consulta(2));
		produtos.add(tabelap.consulta(3));
		produtos.add(tabelap.consulta(5));
		produtos.add(tabelap.consulta(6));
		produtos.add(tabelap.consulta(7));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);

		produtos = new ArrayList<Produto>();
		produtos.add(tabelap.consulta(9));
		produtos.add(tabelap.consulta(10));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);
		
		produtos = new ArrayList<Produto>();
		produtos.add(tabelap.consulta(11));
		produtos.add(tabelap.consulta(21));
		produtos.add(tabelap.consulta(3));
		produtos.add(tabelap.consulta(6));
		carga = new Carga(produtos);
		transportadora.addCarga(carga);
		tabelat.incluir(transportadora);
		
	}
}
