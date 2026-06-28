package br.ucs.poo.tb2.menu;

import java.util.ArrayList;
import java.util.Scanner;

import br.ucs.poo.tb2.cadastro.*;
import br.ucs.poo.tb2.db.*;
import br.ucs.poo.tb2.excecoes.StockException;
import br.ucs.poo.tb2.user.*;

public class Menu {
	
	private Scanner entrada = new Scanner(System.in);
	private User loggeduser = null;
	private TableFornecedor tabelaf;
	private TableProduto tabelap;
	private TableTransportadora tabelat;
	private TableUser tabelau;
	private ArrayList<Produto> carrinho = new ArrayList<Produto>();
	private ArrayList<Produto> res = new ArrayList<Produto>();
	
	public void inicio(TableFornecedor f, TableProduto p, TableTransportadora t, TableUser u) {
		
		this.tabelaf = f;
		this.tabelap = p;
		this.tabelat = t;
		this.tabelau = u;
		
		System.out.println("\nSelecione o sistema a ser acessado:");
		System.out.println("1 - Sistema compra");
		System.out.println("2 - Sistema interno");
		System.out.println("3 - sair\n");
		
		int selection = entrada.nextInt();
		
		switch(selection) {
			case 1:
				this.baseExterno();
				break;
			case 2:
				this.loginInterno();
				break;
			case 3:
				return;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.inicio(f,p,t,u);
				break;
		}
	}
	
	private void baseExterno() {
		System.out.println("\nDeseja logar ou registrar uma nova conta?:");
		System.out.println("1 - Logar");
		System.out.println("2 - Registrar");
		System.out.println("3 - sair\n");
		
		int selection = entrada.nextInt();
		
		switch(selection) {
			case 1:
				this.loginExterno();
				break;
			case 2:
				this.registroExterno();
				break;
			case 3:
				return;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.baseExterno();
				break;
		}
	}
	
	private void loginExterno() {
		
		System.out.println("\nInsira o nome do usuario: ");
		String nome = entrada.next();
		User user = this.tabelau.consulta(nome);
		
		if(user == null || user.getLocal() == "Interno") {
			System.out.println("Usuario nao encontrado");
			this.baseExterno();
		}
		
		System.out.println("Insira a senha do usuário: ");
		String senha = entrada.next();
		if(senha.compareTo(user.getSenha()) != 0) {
			System.out.println("Senha incorreta");
			this.loginExterno();
		} else {
			loggeduser = user;
			this.principalExterno();
		}
		
	}
	
	private void registroExterno() {
		
		System.out.println("\nInsira o nome do usuario: ");
		String nome = entrada.next();
		User user = this.tabelau.consulta(nome);
		if(!(user == null || user.getLocal() == "Interno")) {
			System.out.println("\nNome de usuario invalido, tente novamente.");
			registroExterno();
		} else {
			System.out.println("\nInsira a senha: ");
			String senha =  entrada.next();
			
			User newUser = new Cliente(nome, senha);
			tabelau.incluir(newUser);
			System.out.println("\nUsuario cadastrado, realize o login a seguir.\n");
			loginExterno();
		}
	}
	
	private void loginInterno() {
		
		System.out.println("\nInsira o nome do usuario: ");
		String nome = entrada.next();
		User user = this.tabelau.consulta(nome);
		
		if(user == null || user.getLocal() == "Externo") {
			System.out.println("Usuario nao encontrado");
			this.loginInterno();
		}
		
		System.out.println("Insira a senha do usuário: ");
		String senha = entrada.next();
		if(senha.compareTo(user.getSenha()) != 0) {
			System.out.println("Senha incorreta");
			this.loginInterno();
		} else {
			loggeduser = user;
			this.principal();
		}
	}
	
	
	private void principal() {
		
		System.out.println("\nSelecione o modulo:");
		System.out.println("1 - Usuarios");
		System.out.println("2 - Fornecedores");
		System.out.println("3 - Produtos");
		System.out.println("4 - Transportadoras");
		System.out.println("5 - sair\n");
		
		int selection = entrada.nextInt();
		
		switch(selection) {
			case 1:
				this.users();
				break;
			case 2:
				this.fornecedores();
				break;
			case 3:
				this.produtos();
				break;
			case 4:
				this.transportadoras();
				break;
			case 5:
				return;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.principal();
				break;
		}
	
	}
	
	private void principalExterno() {
		
		System.out.println("\nSelecione o modulo:");
		System.out.println("1 - Comprar");
		System.out.println("2 - Meus pedidos");
		System.out.println("3 - Alterar senha");
		System.out.println("4 - sair\n");
		
		int selection = entrada.nextInt();
		
		switch(selection) {
			case 1:
				this.comprar();
				break;
			case 2:
				this.pedidos();
				break;
			case 3:
				this.alterarSenha();
				break;
			case 4:
				return;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.principalExterno();
				break;
		}
	}
	
	private void alterarSenha() {
		System.out.println("\nInsira a senha atual:");
		String senha = entrada.next();
		if(senha.compareTo(this.loggeduser.getSenha()) != 0) {
			System.out.println("\nSenha incorreta. Tente novamente.");
		} else {
			System.out.println("\nSelecione a nova senha:");
			senha = entrada.next();
			System.out.println("\nNova senha registrada com sucesso.\n");
		}
		this.principalExterno();
	}
	
	private void comprar() {
		System.out.println("\nSelecione o modulo:");
		System.out.println("1 - Pesquisa de produtos por codigo");
		System.out.println("2 - Pesquisa de produtos por palavra");
		System.out.println("3 - Todos os produtos");
		System.out.println("4 - Meu carrinho");
		System.out.println("5 - sair\n");
		
		int selection = entrada.nextInt();
		
		switch(selection) {
		case 1:
			this.pesquisaCodigo();
			break;
		case 2:
			this.pesquisaPalavra();
			break;
		case 3:
			this.pesquisaTodos();
		case 4:
			this.carrinho();
			break;
		case 5:
			return;
		default:
			System.out.println("Selecione uma opcao valida.");
			this.comprar();
			break;
		}
	}
	
	private void pesquisaCodigo() {
		this.res.clear();
		System.out.println("\nInsira o código do produto:");
		int cod = entrada.nextInt();
		
		for(Produto prod : this.tabelap.consultaCompleta().values()) {
			if(prod.getId() == cod) {
				res.add(prod);
			}
		}
		
		this.resultado();
	}
	
	private void pesquisaPalavra() {
		this.res.clear();
		System.out.println("\nInsira a palavra a ser pesquisada:");
		String palavra = entrada.next();
		
		for(Produto prod : this.tabelap.consultaCompleta().values()) {
			if(prod.getNome().contains(palavra) || prod.getSku().contains(palavra)) {
				res.add(prod);
			}
		}
		
		this.resultado();
	}
	
	private void pesquisaTodos() {
		this.res.clear();
		for(Produto prod : this.tabelap.consultaCompleta().values()) {
			res.add(prod);
		}
		this.resultado();
	}
	
	private void finalizaPedido() {
		Pedido newPedido = new Pedido(this.carrinho, this.loggeduser.getId());
		for(Produto prod : this.carrinho) {
			try {
				(this.tabelap.consulta(prod.getId())).removeEstoque(1);
			} catch (StockException e) {
				e.printStackTrace();
			}
		}
		((Cliente) this.loggeduser).addPedido(newPedido);
		this.carrinho.clear();
	}

	private void resultado() {
		
		if(res.size() == 0) {
			System.out.println("Nenhum produto encontrado.\n");
			this.comprar();
		} else {
			int pos = 1;
			String quant;
			for(Produto prod : res) {
				
				if(prod.getQuantidade() > 0) {
					quant = " | Quantidade disponivel: " + prod.getQuantidade() + " |";
				} else {
					quant = " | Quantidade disponivel: 0 |";
				}
				
				System.out.println("| " + pos + " | ID: " + prod.getId() + " | Nome: " + prod.getNome() + quant);
				
				pos += 1;
			}
			
			System.out.println("\nSelecione a posicao do produto que deseja comprar ou digite 0 para retornar:\n");
			int selection = entrada.nextInt();
			if(selection == 0) {
				this.comprar();
				this.res.clear();
			} else if(selection >= pos) {
				System.out.println("Insira uma opcao valida.\n");
				this.resultado();
			} else {
				int quantCompra = 1;
				System.out.println("Insira a quantidade que deseja comprar:\n");
				quantCompra = entrada.nextInt();
				
				if(quantCompra > res.get(selection).getQuantidade()) {
					System.out.println("O produto nao tem a quantidade necessaria, estoque atual: " + res.get(selection).getQuantidade());
					this.resultado();
				} else {
					for(int i = 0; i < quantCompra; i++) {
						this.carrinho.add(res.get(selection - 1));
					}
					System.out.println("Produto(s) adicionado ao carrinho, o que deseja fazer?");
					System.out.println("1 - Continuar comprando");
					System.out.println("2 - Ir para o carrinho para finalizar o pedido");
					selection = entrada.nextInt();
					if(selection == 2) {
						this.carrinho();
					} else {
						this.comprar();
					}
				}
				this.res.clear();
			}
		}
	}
	
	private void carrinho() {
		float total = 0;
		if(this.carrinho.size() == 0) {
			System.out.println("Seu carrinho está vazio. Adicione itens para realizar uma compra.\n");
			this.comprar();
		} else {
			for(Produto prod : this.carrinho) {
				System.out.println("| ID: " + prod.getId() + " | Nome: " + prod.getNome() + " | Valor: R$" + prod.getValor() + " |");
				total += prod.getValor();
			}
			System.out.println("\n | Valor total: R$"+total);
			System.out.println("| Oque deseja fazer? | 1- Voltar | 2- Finalizar pedido |");
			int selection = entrada.nextInt();
			switch(selection) {
				case 1:
					this.comprar();
					break;
				case 2:
					this.finalizaPedido();
					break;
				default:
					System.out.println("Selecione uma opcao valida.");
					this.carrinho();
					break;
			}
		}
	}
	
	private void pedidos() {
		if(((Cliente) this.loggeduser).getPedidos().values().size() == 0 ) {
			System.out.println("Nenhum pedido encontrado.");
			this.principalExterno();
		} else {
			System.out.println("Pedidos:");
			for(Pedido ped : ((Cliente) this.loggeduser).getPedidos().values()) {
				System.out.println("| Numero: " + ped.getId() + " | Status: " + ped.getStatus() + " | Data de pedido: " + ped.getDataCompra() + " | Valor: " + ped.getValorTotal() + " |");
			}
			System.out.println("Digite 0 para retornar ou o numero do pedido para mais detalhes:");
			int selection = entrada.nextInt();
			
			if(selection == 0) {
				this.principalExterno();
			} else {
				if(selection > ((Cliente) this.loggeduser).getPedidos().size()) {
					System.out.println("Digite um numero valido de pedido.");
					this.pedidos();
				} else {
					Pedido pedido = ((Cliente) this.loggeduser).getPedidos().get(selection);
					System.out.println("Pedido " + pedido.getId());
					System.out.println("\nStatus: " + pedido.getStatus());
					System.out.println("Data de compra: " + pedido.getDataCompra());
					if(pedido.getStatus() == "Enviado") {
						System.out.println("Data de envio: " + pedido.getDataEnvio());
					}
					if(pedido.getStatus() != "Cancelado") {
						System.out.println("\n| Selecione a operacao que deseja realizar: | 1 - Cancelar pedido | 2 - Voltar |");
						selection = entrada.nextInt();
						if(selection == 1) {
							pedido.setStatus("Cancelado");
							System.out.println("Pedido cancelado.");
							this.pedidos();
						} else if(selection == 2){
							this.pedidos();
						} else {
							System.out.println("Selecione uma opcao valida.");
							this.pedidos();
						}
					} else {
						System.out.println("Data de cancelamento: " + pedido.getDataCancelamento());
						System.out.println("\n| Selecione a operacao que deseja realizar: | 1 - Voltar |");
						selection = entrada.nextInt();
						if(selection == 1) {
							this.pedidos();
						} else {
							System.out.println("Selecione uma opcao valida.");
							this.pedidos();
						}
					}
				}
			}
		}
	}
	
	private void users() {
		String nome;
		String senha;
		int id;
		
		System.out.println("\nSelecione a operacao:");
		System.out.println("1 - Alterar senha");
		if(loggeduser.getPermlevel() == 1) {
			System.out.println("2 - Cadastrar usuario");
			System.out.println("3 - Alterar senha de usuario");
			System.out.println("4 - Consulta usuarios");
			System.out.println("5 - Exclui usuario");
			System.out.println("6 - Menu principal\n");
		} else {
			System.out.println("2 - Menu principal\n");
		}
		
		int selection = entrada.nextInt();
		
		if(loggeduser.getPermlevel() == 1) {
			switch(selection) {
				case 1:
					this.trocasenha();
					this.users();
					break;
				case 2:
					System.out.println("Insira o nome do usuário:\n");
					nome = entrada.next();
					System.out.println("Insira a senha do usuário:\n");
					senha = entrada.next();
					int temp = 0;
					while(temp == 0) {
						System.out.println("Insira o nivel de permissao:");
						System.out.println("1 - usuario");
						System.out.println("2 - administrador");
						selection = entrada.nextInt();
						if(selection == 1) {
							tabelau.incluir(new User(nome,senha));
							temp = 1;
						} else if (selection == 2) {
							tabelau.incluir(new Admin(nome,senha));
							temp = 1;
						} else {
							System.out.println("Selecione uma opcao valida.");
						}
					}
					System.out.println("Usuario cadastrado.");
					this.users();
					break;
				case 3:
					System.out.println("Insira o id do usuário:");
					id = entrada.nextInt();
					User tempuser = tabelau.consulta(id);
					if(tempuser == null) {
						System.out.println("Usuario nao encontrado.");
					} else {
					System.out.println("Insira a nova senha do usuário:");
						senha = entrada.next();
						tempuser.setSenha(senha);
						tabelau.alterar(id, tempuser);
						System.out.println("Senha de usuario atualizada.");
						this.users();
					}
					break;
				case 4:
					System.out.println("\nUsuarios:");
					for(User i : tabelau.consultaCompleta().values()) {
						System.out.println("| id: " + i.getId() + " | Nome: " + i.getNome() + " | Permissao: " + i.getClass().getSimpleName() + " |");
					}
					this.users();
					break;
				case 5:
					System.out.println("Insira o id do usuário para ser excluido:");
					id = entrada.nextInt();
					tabelau.excluir(id);
					System.out.println("Usuario excluido.");
					this.users();
					break;
				case 6:
					this.principal();
					break;
				default:
					System.out.println("Selecione uma opcao valida.");
					this.users();
					break;
			}
		} else {
			if(selection == 1) {
				this.trocasenha();
				this.users();
			} else if(selection == 2) {
				this.principal();
			} else {
				System.out.println("Selecione uma opcao valida.");
				this.users();
			}
		}

	}
	
	private void trocasenha() {
		System.out.println("Insira a nova senha do usuário:");
		String senha = entrada.next();
		loggeduser.setSenha(senha);
		tabelau.alterar(loggeduser.getId(), loggeduser);
		System.out.println("Senha de usuario atualizada.");
	}
	
	private void fornecedores() {
		
		String nome;
		String cnpj;
		int id;
		Fornecedor fornecedor;
		
		System.out.println("\nSelecione a operacao:");
		System.out.println("1 - Consultas de fornecedores");
		if(loggeduser.getPermlevel() == 1) {
			System.out.println("2 - Cadastrar fornecedor");
			System.out.println("3 - Alterar fornecedor");
			System.out.println("4 - Excluir fornecedor");
			System.out.println("5 - Menu principal\n");
		} else {
			System.out.println("2 - Menu principal\n");
		}
		
		int selection = entrada.nextInt();
		
		if(selection == 1) {
			 this.consultaFornecedor();
			 this.fornecedores();
		} else {
			if(loggeduser.getPermlevel() == 1) {
				switch(selection) {
					case 2:
						System.out.println("Insira o nome do fornecedor:\n");
						nome = entrada.next();
						System.out.println("Insira o cnpj do fornecedor:\n");
						cnpj = entrada.next();
						fornecedor = new Fornecedor(nome,cnpj);
						tabelaf.incluir(fornecedor);
						System.out.println("Fornecedor cadastrado.");
						this.fornecedores();
						break;
					case 3:
						this.alteraFornecedor();
						this.fornecedores();
						break;
					case 4:
						System.out.println("Insira o id do fornecedor para ser excluido:");
						id = entrada.nextInt();
						tabelaf.excluir(id);
						System.out.println("Fornecedor excluido.");
						this.fornecedores();
						break;
					case 5:
						this.principal();
						break;
					default:
						System.out.println("Selecione uma opcao valida.");
						this.fornecedores();
						break;
				}
			} else {
				if(selection == 2) {
					this.principal();
				} else {
					System.out.println("Selecione uma opcao valida.");
					this.fornecedores();
				}
			}
		}
	}
	
	private void alteraFornecedor() {
		String nome;
		String cnpj;
		int id;
		
		System.out.println("Qual dado do fornecedor deseja alterar?");
		System.out.println("1 - Nome");
		System.out.println("2 - CNPJ");
		System.out.println("3 - Cancelar");
		
		int selection = entrada.nextInt();
		Fornecedor tempf;
		
		switch(selection) {
			case 1:
				System.out.println("Insira o id do fornecedor que deseja alterar:");
				id = entrada.nextInt();
				tempf = tabelaf.consulta(id);
				if(tempf == null) {
					System.out.println("Fornecedor nao encontrado.");
				} else {
					System.out.println("Insira o novo nome para o fornecedor:");
					nome = entrada.next();
					tempf.setNome(nome);
					tabelaf.alterar(id, tempf);
					System.out.println("Nome do fornecedor alterado.");
				}
				break;
			case 2:
				System.out.println("Insira o id do fornecedor que deseja alterar:");
				id = entrada.nextInt();
				tempf = tabelaf.consulta(id);
				if(tempf == null) {
					System.out.println("Fornecedor nao encontrado.");
				} else {
					System.out.println("Insira o novo CNPJ para o fornecedor:");
					cnpj = entrada.next();
					tempf.setCnpj(cnpj);
					tabelaf.alterar(id, tempf);
					System.out.println("CNPJ do fornecedor alterado.");
				}
				break;
			case 3:
				break;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.alteraFornecedor();
				break;
		}
	}
	
	private void consultaFornecedor() {
		
		int id;
		String nome;
		Fornecedor tempf;
		ArrayList<Produto> tempp;
		
		System.out.println("\nSelecione a consulta:");
		System.out.println("1 - Consulta de fornecedor por id");
		System.out.println("2 - Consulta de fornecedor por nome");
		System.out.println("3 - Consulta produtos de fornecedor");
		System.out.println("4 - Listar fornecedores");
		System.out.println("5 - Cancelar");
		
		int selection = entrada.nextInt();
	
		switch(selection) {
			case 1:
				System.out.println("Insira o id do fornecedor:\n");
				id = entrada.nextInt();
				tempf = tabelaf.consulta(id);
				if(tempf == null) {
					System.out.println("Fornecedor nao encontrado.");
				}else {
					System.out.println("| id: " + tempf.getId() + " | Nome: " + tempf.getNome() + " | Cnpj: " + tempf.getCnpj() + " |");
				}
				break;
			case 2:
				System.out.println("Insira o Nome do fornecedor:\n");
				nome = entrada.next();
				tempf = tabelaf.consulta(nome);
				if(tempf == null) {
					System.out.println("Fornecedor nao encontrado.");
				}else {
					System.out.println("| id: " + tempf.getId() + " | Nome: " + tempf.getNome() + " | Cnpj: " + tempf.getCnpj() + " |");
				}
				break;
			case 3:
				System.out.println("Insira o id do fornecedor:\n");
				id = entrada.nextInt();
				tempf = tabelaf.consulta(id);
				if(tempf == null) {
					System.out.println("Fornecedor nao encontrado.");
				}else {
					System.out.println("| id: " + tempf.getId() + " | Nome: " + tempf.getNome() + " | Cnpj: " + tempf.getCnpj() + " |\nProdutos do fornecedor:");
					tempp = tempf.getProdutos();
					for(Produto i : tempp) {
						System.out.println("| id: " + i.getId() + " | Nome: " + i.getNome() + " | Sku: " + i.getSku() + " |");
					}
				}
				break;
			case 4:
				System.out.println("\nFornecedores:");
				for(Fornecedor i : tabelaf.consultaCompleta().values()) {
					System.out.println("| id: " + i.getId() + " | Nome: " + i.getNome() + " | Cnpj: " + i.getCnpj() + " |");
				}
				break;
			case 5:
				break;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.consultaFornecedor();
				break;
		}
	}
	
	private void produtos() {
		
		String nome;
		String sku;
		int idf;
		int id;
		Produto produto;
		float valor;
		int quant;
		
		System.out.println("\nSelecione a operacao:");
		System.out.println("1 - Consulta produtos");
		if(loggeduser.getPermlevel() == 1) {
			System.out.println("2 - Cadastrar produto");
			System.out.println("3 - Alterar produto");
			System.out.println("4 - Excluir produto");
			System.out.println("5 - Menu principal\n");
		} else {
			System.out.println("2 - Menu principal\n");
		}
		
		int selection = entrada.nextInt();
		
		if(selection == 1) {
			 this.consultaProdutos();
			 this.produtos();
		} else {
			if(loggeduser.getPermlevel() == 1) {
				switch(selection) {
					case 2:
						System.out.println("Insira o nome do produto:");
						nome = entrada.next();
						System.out.println("Insira o sku do produto:");
						sku = entrada.next();
						System.out.println("Insira o id do fornecedor do produto:");
						idf = entrada.nextInt();
						System.out.println("Insira o valor do produto:");
						valor = entrada.nextFloat();
						System.out.println("Insira a quantidade do produto em estoque:");
						quant = entrada.nextInt();
						produto = new Produto(nome,sku,idf, valor, quant);
						tabelap.incluir(produto);
						produto = tabelap.consultaSku(sku);
						tabelat.consulta(idf).addProduto(produto);
						System.out.println("Produto cadastrado.");
						this.produtos();
						break;
					case 3:
						this.alteraProduto();
						this.produtos();
						break;
					case 4:
						System.out.println("Insira o id do produto para ser excluido:");
						id = entrada.nextInt();
						idf = tabelap.consulta(id).getIdFornecedor();
						tabelaf.consulta(idf).removeProduto(tabelap.consulta(id));
						tabelap.excluir(id);
						System.out.println("Produto excluido.");
						this.produtos();
						break;
					case 5:
						this.principal();
						break;
					default:
						System.out.println("Selecione uma opcao valida.");
						this.produtos();
						break;
				}
			} else {
				if(selection == 2) {
					this.principal();
				} else {
					System.out.println("Selecione uma opcao valida.");
					this.produtos();
					}
				}	
			}
	}
	
	private void alteraProduto() {
		String nome;
		String sku;
		int idf;
		int id;
		
		System.out.println("Qual dado do produto deseja alterar?");
		System.out.println("1 - Nome");
		System.out.println("2 - Sku");
		System.out.println("3 - id da transportadora");
		System.out.println("4 - Cancelar");
		
		int selection = entrada.nextInt();
		Produto tempp;
		
		switch(selection) {
			case 1:
				System.out.println("Insira o id do produto que deseja alterar:");
				id = entrada.nextInt();
				tempp = tabelap.consulta(id);
				if(tempp == null) {
					System.out.println("Produto nao encontrado.");
				} else {
					System.out.println("Insira o novo nome do produto:");
					nome = entrada.next();
					idf = tempp.getIdFornecedor();
					tabelat.consulta(idf).removeProduto(tempp);
					tempp.setNome(nome);
					tabelap.incluir(tempp);
					tabelat.consulta(idf).addProduto(tempp);
					System.out.println("Produto alterado com sucesso.");
				}
				break;
			case 2:
				System.out.println("Insira o id do produto que deseja alterar:");
				id = entrada.nextInt();
				tempp = tabelap.consulta(id);
				if(tempp == null) {
					System.out.println("Produto nao encontrado.");
				} else {
					System.out.println("Insira o sku do produto:");
					sku = entrada.next();
					idf = tempp.getIdFornecedor();
					tabelat.consulta(idf).removeProduto(tempp);
					tempp.setSku(sku);
					tabelap.incluir(tempp);
					tabelat.consulta(idf).addProduto(tempp);
					System.out.println("Produto alterado com sucesso.");
				}
				break;
			case 3:
				System.out.println("Insira o id do produto que deseja alterar:");
				id = entrada.nextInt();
				tempp = tabelap.consulta(id);
				if(tempp == null) {
					System.out.println("Produto nao encontrado.");
				} else {
					System.out.println("Insira o id do fornecedor do produto:");
					idf = entrada.nextInt();
					int oldidf = tempp.getIdFornecedor();
					tabelat.consulta(oldidf).removeProduto(tempp);
					tempp.setIdFornecedor(idf);
					tabelap.incluir(tempp);
					tabelat.consulta(idf).addProduto(tempp);
					System.out.println("Produto alterado com sucesso.");
				}
				break;
			case 4:
				break;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.alteraProduto();
				break;
		}
	}
	
	private void consultaProdutos() {
		
		int id;
		String nome;
		String sku;
		Produto produto;
		
		System.out.println("\nSelecione a consulta:");
		System.out.println("1 - Consulta de produto por id:");
		System.out.println("2 - Consulta de produto por nome");
		System.out.println("3 - Consulta produtos por sku");
		System.out.println("4 - Listar produtos");
		System.out.println("5 - Cancelar");
		
		int selection = entrada.nextInt();
		
		switch(selection) {
			case 1:
				System.out.println("Insira o id do produto que deseja consultar:");
				id = entrada.nextInt();
				produto = tabelap.consulta(id);
				if(produto == null) {
					System.out.println("Produto nao encontrado.");
				} else {
					System.out.println("| id: " + produto.getId() + " | Nome: " + produto.getNome() + " | Sku: " + produto.getSku() + " | Fornecedor: " + tabelat.consulta(produto.getIdFornecedor()).getNome() + " |");
				}
				break;
			case 2:
				System.out.println("Insira o nome do produto que deseja consultar:");
				nome = entrada.next();
				produto = tabelap.consulta(nome);
				if(produto == null) {
					System.out.println("Produto nao encontrado.");
				} else {
					System.out.println("| id: " + produto.getId() + " | Nome: " + produto.getNome() + " | Sku: " + produto.getSku() + " | Fornecedor: " + tabelat.consulta(produto.getIdFornecedor()).getNome() + " |");
				}
				break;
			case 3:
				System.out.println("Insira o sku do produto que deseja consultar:");
				sku = entrada.next();
				produto = tabelap.consultaSku(sku);
				if(produto == null) {
					System.out.println("Produto nao encontrado.");
				} else {
					System.out.println("| id: " + produto.getId() + " | Nome: " + produto.getNome() + " | Sku: " + produto.getSku() + " | Fornecedor: " + tabelat.consulta(produto.getIdFornecedor()).getNome() + " |");
				}
				break;
			case 4:
				System.out.println("Produtos cadastrados:");
				for(Produto i : tabelap.consultaCompleta().values()) {
					System.out.println("| id: " + i.getId() + " | Nome: " + i.getNome() + " | Sku: " + i.getSku() + " | Fornecedor: " + tabelaf.consulta(i.getIdFornecedor()).getNome() + " |");
				}
				break;
			case 5:
				break;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.alteraProduto();
				break;
		}
	}
	
	private void transportadoras() {
		
		String nome;
		String cnpj;
		int id, idp, idc;
		int temp;
		Transportadora transportadora;
		Produto produto;
		
		System.out.println("\nSelecione a operacao:");
		System.out.println("1 - Consultas de transportadoras");
		if(loggeduser.getPermlevel() == 1) {
			System.out.println("2 - Cadastrar transportadora");
			System.out.println("3 - Alterar transportadora");
			System.out.println("4 - Excluir transportadora");
			System.out.println("5 - Adiciona carga");
			System.out.println("6 - Remove carga");
			System.out.println("7 - Menu principal\n");
		} else {
			System.out.println("2 - Menu principal\n");
		}
		
		int selection = entrada.nextInt();
		
		if(selection == 1) {
			 this.consultaTransportadora();
			 this.transportadoras();
		} else {
			if(loggeduser.getPermlevel() == 1) {
				switch(selection) {
					case 2:
						System.out.println("Insira o nome da transportadora:");
						nome = entrada.next();
						System.out.println("Insira o cnpj da transportadora:");
						cnpj = entrada.next();
						transportadora = new Transportadora(nome,cnpj);
						tabelat.incluir(transportadora);
						System.out.println("Transportadora cadastrada.");
						this.transportadoras();
						break;
					case 3:
						this.alteraTransportadora();
						this.transportadoras();
						break;
					case 4:
						System.out.println("Insira o id da transportadora para ser excluida:");
						id = entrada.nextInt();
						tabelat.excluir(id);
						System.out.println("Transportadora excluida.");
						this.transportadoras();
						break;
					case 5:
						System.out.println("Insira o id da transportadora para adicionar a carga:");
						ArrayList<Produto> produtos = new ArrayList<Produto>();
						id = entrada.nextInt();
						transportadora = tabelat.consulta(id);
						if(transportadora == null) {
							System.out.println("Transportadora nao encontrada");
							this.consultaTransportadora();
						} else {
							temp = 0;
							while(temp != 2) {
								System.out.println("Insira o id do produto para ser adicionado a carga:");
								idp = entrada.nextInt();
								produto = tabelap.consulta(idp);
								if(produto == null ) {
									System.out.println("Produto nao encontrado, insira um id valido.");
								} else {
									produtos.add(produto);
									System.out.println("Produto adicionado. Deseja adicionar mais um produto? 1 - Sim 2 - Nao");
									temp = entrada.nextInt();
								}
							}
							transportadora.addCarga(new Carga(produtos));;
							System.out.println("Carga adicionada com sucesso.");
							this.transportadoras();
						}
						break;
					case 6:
						System.out.println("Insira o id da transportadora para remover a carga:");
						id = entrada.nextInt();
						transportadora = tabelat.consulta(id);
						if(transportadora == null) {
							System.out.println("Transportadora nao encontrada");
							this.consultaTransportadora();
						} else {
							System.out.println("Insira o id da carga a ser removida:");
							idc = entrada.nextInt();
							transportadora.removeCarga(idc);
							System.out.println("Carga removida com sucesso.");
						}
						break;
					case 7:
						this.principal();
						break;
					default:
						System.out.println("Selecione uma opcao valida.");
						this.transportadoras();
						break;
				}
			} else {
				if(selection == 2) {
					this.principal();
				} else {
					System.out.println("Selecione uma opcao valida.");
					this.fornecedores();
				}
			}
		}
	}
	
	private void alteraTransportadora() {
		String nome;
		String cnpj;
		int id;
		
		System.out.println("Qual dado da transportadora deseja alterar?");
		System.out.println("1 - Nome");
		System.out.println("2 - CNPJ");
		System.out.println("3 - Cancelar");
		
		int selection = entrada.nextInt();
		Transportadora tempt;
		
		switch(selection) {
			case 1:
				System.out.println("Insira o id da transportadora que deseja alterar:");
				id = entrada.nextInt();
				tempt = tabelat.consulta(id);
				if(tempt == null) {
					System.out.println("Transportadora nao encontrada.");
				} else {
					System.out.println("Insira o novo nome para a transportadora:");
					nome = entrada.next();
					tempt.setNome(nome);
					tabelat.alterar(id, tempt);
					System.out.println("Nome do fornecedor alterado.");
				}
				break;
			case 2:
				System.out.println("Insira o id da transportadora que deseja alterar:");
				id = entrada.nextInt();
				tempt = tabelat.consulta(id);
				if(tempt == null) {
					System.out.println("Transportadora nao encontrada.");
				} else {
					System.out.println("Insira o novo CNPJ para a transportadora:");
					cnpj = entrada.next();
					tempt.setCnpj(cnpj);
					tabelat.alterar(id, tempt);
					System.out.println("CNPJ da transportadora alterado.");
				}
				break;
			case 3:
				break;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.alteraTransportadora();
				break;
		}
	}
	
	private void consultaTransportadora() {
		
		int id;
		String nome;
		Transportadora tempt;
		
		System.out.println("\nSelecione a consulta:");
		System.out.println("1 - Consulta de transportadora por id");
		System.out.println("2 - Consulta de transportadora por nome");
		System.out.println("3 - Consulta cargas da transportadora");
		System.out.println("4 - Listar transportadoras");
		System.out.println("5 - Cancelar");
		
		int selection = entrada.nextInt();
	
		switch(selection) {
			case 1:
				System.out.println("Insira o id da transportadora:");
				id = entrada.nextInt();
				tempt = tabelat.consulta(id);
				if(tempt == null) {
					
				} else {
					System.out.println("| id: " + tempt.getId() + " | Nome: " + tempt.getNome() + " | Cnpj: " + tempt.getCnpj() + " |");
				}
				break;
			case 2:
				System.out.println("Insira o Nome da transportadora:");
				nome = entrada.next();
				tempt = tabelat.consulta(nome);
				if(tempt == null) {
					
				} else {
					System.out.println("| id: " + tempt.getId() + " | Nome: " + tempt.getNome() + " | Cnpj: " + tempt.getCnpj() + " |");
				}
				break;
			case 3:
				System.out.println("Insira o id da transportadora:");
				id = entrada.nextInt();
				tempt = tabelat.consulta(id);
				if(tempt == null) {
					System.out.println("Transportadora nao encontrada.");
				} else {
					System.out.println("\nCargas para a transportadora " + tempt.getNome() + ":");
					for(Carga i : tempt.getCargas().values()) {
						System.out.println("\nProdutos na carga " + i.getId() + ":");
						for(Produto j : i.getProdutos()) {
							System.out.println("| id: " + j.getId() + " | Nome: " + j.getNome() + " | Sku: " + j.getSku() + " | Fornecedor: " + tabelaf.consulta(j.getIdFornecedor()).getNome() + " |");
						}
					}
				}
				break;
			case 4:
				System.out.println("\nTransportadoras:");
				for(Fornecedor i : tabelat.consultaCompleta().values()) {
					System.out.println("| id: " + i.getId() + " | Nome: " + i.getNome() + " | Cnpj: " + i.getCnpj() + " |");
				}
				break;
			case 5:
				break;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.consultaTransportadora();
				break;
		}
	}
	
}
