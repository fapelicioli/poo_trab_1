package br.ucs.poo.tb2.menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

import br.ucs.poo.tb2.cadastro.Pedido;
import br.ucs.poo.tb2.cadastro.Produto;
import br.ucs.poo.tb2.cadastro.Transportadora;
import br.ucs.poo.tb2.db.*;
import br.ucs.poo.tb2.excecoes.StockException;
import br.ucs.poo.tb2.user.Cliente;
import br.ucs.poo.tb2.user.User;

public class Externo extends Menu{
	
	private ArrayList<Produto> carrinho = new ArrayList<Produto>();
	private ArrayList<Produto> res = new ArrayList<Produto>();
	int frete = 0;
	
	protected Externo(TableFornecedor f, TableProduto p, TableTransportadora t, TableUser u) {
		this.tabelaf = f;
		this.tabelap = p;
		this.tabelat = t;
		this.tabelau = u;
	}
	
	protected void base() {
		System.out.println("\nDeseja logar ou registrar uma nova conta?:");
		System.out.println("1 - Logar");
		System.out.println("2 - Registrar");
		System.out.println("3 - sair\n");
		
		try {
			int selection = entrada.nextInt();
		
			switch(selection) {
				case 1:
					this.login();
					break;
				case 2:
					this.registro();
					break;
				case 3:
					return;
				default:
					System.out.println("Selecione uma opcao valida.");
					this.base();
					break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Selecione uma opcao valida.");
			entrada.next();
			this.base();
		}
	}
	
	private void login() {
		
		System.out.println("\nInsira o nome do usuario: ");
		String nome = entrada.next();
		User user = this.tabelau.consulta(nome);
		
		if(user == null || user.getLocal() == "Interno") {
			System.out.println("Usuario nao encontrado");
			this.base();
		}
		
		System.out.println("Insira a senha do usuário: ");
		String senha = entrada.next();
		if(senha.compareTo(user.getSenha()) != 0) {
			System.out.println("Senha incorreta");
			this.login();
		} else {
			loggeduser = user;
			this.principal();
		}
		
	}
	
	private void registro() {
		
		System.out.println("\nInsira o nome do usuario: ");
		String nome = entrada.next();
		User user = this.tabelau.consulta(nome);
		if(!(user == null || user.getLocal() == "Interno")) {
			System.out.println("\nNome de usuario invalido, tente novamente.");
			registro();
		} else {
			System.out.println("\nInsira a senha: ");
			String senha =  entrada.next();
			
			User newUser = new Cliente(nome, senha);
			tabelau.incluir(newUser);
			System.out.println("\nUsuario cadastrado, realize o login a seguir.\n");
			login();
		}
	}
	
	private void principal() {
		
		System.out.println("\nSelecione o modulo:");
		System.out.println("1 - Comprar");
		System.out.println("2 - Meus pedidos");
		System.out.println("3 - Alterar senha");
		System.out.println("4 - sair\n");
		
		try {
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
					this.principal();
					break;
			}
		} catch(InputMismatchException e) {
			System.out.println("Selecione uma opcao valida.");
			entrada.next();
			this.principal();
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
		this.principal();
	}
	
	private void comprar() {
		System.out.println("\nSelecione o modulo:");
		System.out.println("1 - Pesquisa de produtos por codigo");
		System.out.println("2 - Pesquisa de produtos por palavra");
		System.out.println("3 - Todos os produtos");
		System.out.println("4 - Meu carrinho");
		System.out.println("5 - sair\n");
		
		try {
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
		} catch (InputMismatchException e) {
			System.out.println("Selecione uma opcao valida.");
			entrada.next();
			this.comprar();
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
				if(!prod.getNome().contains("Frete")) {
					(this.tabelap.consulta(prod.getId())).removeEstoque(1);
				}
			} catch (StockException e) {
				e.printStackTrace();
			}
		}
		((Cliente) this.loggeduser).addPedido(newPedido);
		System.out.println("Pedido finalizado.");
		this.salvaUsuario();
		this.carrinho.clear();
		this.principal();
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
					quant = " | Quantidade disponivel: 0 (Produto indisponivel) |";
				}
				
				System.out.println("| " + pos + " | ID: " + prod.getId() + " | Nome: " + prod.getNome() + " | Valor: R$" + prod.getValor() + quant);
				
				pos += 1;
			}
			
			System.out.println("\nSelecione a posicao do produto que deseja comprar ou digite 0 para retornar:\n");
			
			try {
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
			} catch (InputMismatchException e) {
				System.out.println("Insira uma opcao valida.\n");
				entrada.next();
				this.resultado();
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
			System.out.println("\n| Valor total: R$"+total);
			if(this.frete == 0) {
				System.out.println("| Oque deseja fazer? | 1- Voltar | 2- Selecionar frete |");
				try {
					int selection = entrada.nextInt();
					switch(selection) {
						case 1:
							this.comprar();
							break;
						case 2:
							int count = 1;
							System.out.println("Selecione a transportadora:");
							for(Transportadora transp : this.tabelat.consultaCompleta().values()) {
								System.out.println("| Pos: " + count + " | Nome: " + transp.getNome() + " | Opcoes de frete: " + transp.getProdutos().size() + " |");
								count += 1;
							}
							System.out.println("Indique a posicao da transportadora desejada: ");
							selection = entrada.nextInt();
							if(selection < count) {
								Transportadora transp = this.tabelat.consultaCompleta().get(selection);
								count = 1;
								for(Produto fret : transp.getProdutos()) {
									System.out.println("| Pos: " + count + " | Tipo: " + fret.getNome() + " | Valor: R$" + fret.getValor() + " |");
									count += 1;
								}
								System.out.println("Escolha o frete: ");
								selection = entrada.nextInt();
								carrinho.add(transp.getProdutos().get(selection - 1));
								this.frete += 1;
								System.out.println("Frete selecionado.");
							} else {
								System.out.println("Transportadora selecionada invalida, tente novamente.");
							}
							this.carrinho();
							break;
						default:
							System.out.println("Selecione uma opcao valida.");
							this.carrinho();
							break;
					}
				} catch(InputMismatchException e) {
					System.out.println("Selecione uma opcao valida.");
					entrada.next();
					this.carrinho();
				}
			} else {
				System.out.println("| Oque deseja fazer? | 1- Voltar | 2- Finalizar pedido |");
				try {
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
				} catch(InputMismatchException e) {
					System.out.println("Selecione uma opcao valida.");
					entrada.next();
					this.carrinho();
				}
			}
		}
	}
	
	private void pedidos() {
		if(((Cliente) this.loggeduser).getPedidos().values().size() == 0 ) {
			System.out.println("Nenhum pedido encontrado.");
			this.principal();
		} else {
			System.out.println("Pedidos:");
			for(Pedido ped : ((Cliente) this.loggeduser).getPedidos().values()) {
				System.out.println("| Numero: " + ped.getId() + " | Status: " + ped.getStatus() + " | Data de pedido: " + ped.getDataCompra().getData() + " | Valor: " + ped.getValorTotal() + " |");
			}
			System.out.println("Digite 0 para retornar ou o numero do pedido para mais detalhes:");
			
			try {
				int selection = entrada.nextInt();
			
				if(selection == 0) {
					this.principal();
				} else {
					if(selection > ((Cliente) this.loggeduser).getPedidos().size()) {
						System.out.println("Digite um numero valido de pedido.");
						this.pedidos();
					} else {
						Pedido pedido = ((Cliente) this.loggeduser).getPedidos().get(selection);
						System.out.println("| Pedido " + pedido.getId());
						System.out.println("\n| Status: " + pedido.getStatus());
						System.out.println("| Data de compra: " + pedido.getDataCompra().getData());
						if(pedido.getStatus().equals("Enviado")) {
							System.out.println("| Data de envio: " + pedido.getDataEnvio());
						}
						if(pedido.getStatus().equals("Cancelado")) {
							System.out.println("Data de cancelamento: " + pedido.getDataCancelamento());
						}
						System.out.println("| Produtos no pedido: |");
						float valortot = 0;
						for(Produto prod : pedido.getProdutos()) {
							System.out.println("| ID: " + prod.getId() + " | Nome: " + prod.getNome() + " | Valor: R$" + prod.getValor() + " |");
							valortot += prod.getValor();
						}
						System.out.println("| Valor total: R$" + valortot + " |");
						if(!pedido.getStatus().equals("Cancelado")) {
							System.out.println("\n| Selecione a operacao que deseja realizar: | 1 - Cancelar pedido | 2 - Voltar |");
							selection = entrada.nextInt();
							if(selection == 1) {
								pedido.setStatus("Cancelado");
								this.salvaUsuario();
								System.out.println("| Pedido cancelado.");
								this.pedidos();
							} else if(selection == 2){
								this.pedidos();
							} else {
								System.out.println("Selecione uma opcao valida.");
								this.pedidos();
							}
						} else {
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
			} catch (InputMismatchException e){
				System.out.println("Selecione uma opcao valida.");
				entrada.next();
				this.pedidos();
			}
		}
	}
	
	private void salvaUsuario() {
		int id = this.loggeduser.getId();
		this.tabelau.alterar(id, loggeduser);
		tabelau.salvar();
	}
}
