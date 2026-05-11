package br.ucs.poo.tb1.menu;

import java.util.ArrayList;
import java.util.Scanner;
import br.ucs.poo.tb1.db.*;
import br.ucs.poo.tb1.user.*;
import br.ucs.poo.tb1.cadastro.*;

public class Menu {
	
	Scanner entrada = new Scanner(System.in);
	User loggeduser = null;
	TableFornecedor tabelaf;
	TableProduto tabelap;
	TableTransportadora tabelat;
	TableUser tabelau;
	
	public void login(TableFornecedor f, TableProduto p, TableTransportadora t, TableUser u) {
		
		this.tabelaf = f;
		this.tabelap = p;
		this.tabelat = t;
		this.tabelau = u;
		
		System.out.println("\nInsira o nome do usuário: ");
		String nome = entrada.next();
		User user = u.consulta(nome);
		
		if(user == null) {
			System.out.println("Usuario nao encontrado");
			this.login(f,p,t,u);
		}
		
		System.out.println("Insira a senha do usuário: ");
		String senha = entrada.next();
		if(senha.compareTo(user.getSenha()) != 0) {
			System.out.println("Senha incorreta");
			this.login(f,p,t,u);
		} else {
			loggeduser = user;
			this.principal();
		}
	}
	
	
	public void principal() {
		
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
						produto = new Produto(nome,sku,idf);
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
