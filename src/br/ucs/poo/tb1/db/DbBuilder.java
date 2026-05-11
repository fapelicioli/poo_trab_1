package br.ucs.poo.tb1.db;

import br.ucs.poo.tb1.cadastro.*;
import br.ucs.poo.tb1.user.*;

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
		Produto produto = new Produto("Porta madeirada","P001.1",1);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Porta preta","P001.2",1);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Porta branca","P001.3",1);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Janela vitral transparente","J001.1",1);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Janela vitral colorida","J001.2",1);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Janela madeirada ipe","J002.1",1);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		produto = new Produto("Janela madeirada acacia","J002.2",1);
		tabelap.incluir(produto);
		tabelaf.consulta(1).addProduto(produto);
		
		
		fornecedor = new Fornecedor("Cica-Cola Refrigerantes & Bebidas","84.157.771/0001-87");
		tabelaf.incluir(fornecedor);
		produto = new Produto("Refrigerante de cola","R100.1",2);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Refrigerante de laranja","R100.2",2);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Refrigerante de cereja","R100.5",2);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Agua mineral sem gas","A101.1",2);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Agua mineral com gas","A101.2",2);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Suco de uva","S256.1",2);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		produto = new Produto("Agua tonica","A113.1",2);
		tabelap.incluir(produto);
		tabelaf.consulta(2).addProduto(produto);
		
		
		fornecedor = new Fornecedor("SaborFood Salgados & Doces","57.415.130/0001-02");
		tabelaf.incluir(fornecedor);
		produto = new Produto("Coxinha congelada","C4968.1",3);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Pao preto caseiro","Pc101.1",3);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Cereal de frutas","Ce142.1",3);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Pao de forma caseiro","Pc100.1",3);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Lasanha congelada de frango","C4985.1",3);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Lasanha congelada 4 queijos","C4985.4",3);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		produto = new Produto("Sorvete de limao","S2690.8",3);
		tabelap.incluir(produto);
		tabelaf.consulta(3).addProduto(produto);
		
		
		
	}
}
