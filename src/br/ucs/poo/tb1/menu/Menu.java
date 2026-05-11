package br.ucs.poo.tb1.menu;

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
	
	public void login(TableUser tabela) {
		System.out.println("\nInsira o nome do usuário: ");
		String nome = entrada.nextLine();
		User user = tabela.consulta(nome);
		
		if(user == null) {
			System.out.println("Usuario nao encontrado");
			this.login(tabela);
		}
		
		System.out.println("Insira a senha do usuário: ");
		String senha = entrada.nextLine();
		if(senha != user.getSenha()) {
			System.out.println("Senha incorreta");
			this.login(tabela);
		} else {
			loggeduser = user;
		}
	}
	
	
	public void principal(TableFornecedor f, TableProduto p, TableTransportadora t, TableUser u) {
		
		this.tabelaf = f;
		this.tabelap = p;
		this.tabelat = t;
		this.tabelau = u;
		
		loggeduser = tabelau.consulta("admin");
		
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
				this.principal(this.tabelaf, this.tabelap, this.tabelat, this.tabelau);
		}
	
	}
	
	public void users() {
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
				case 2:
					System.out.println("Insira o nome do usuário:");
					nome = entrada.nextLine();
					System.out.println("Insira a senha do usuário:");
					senha = entrada.nextLine();
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
				case 3:
					System.out.println("Insira o id do usuário:");
					id = entrada.nextInt();
					System.out.println("Insira a nova senha do usuário:");
					senha = entrada.nextLine();
					User tempuser = tabelau.consulta(id);
					tempuser.setSenha(senha);
					tabelau.alterar(id, tempuser);
					System.out.println("Senha de usuario atualizada.");
					this.users();
				case 4:
					System.out.println("\nUsuarios:");
					for(User i : tabelau.consultaCompleta().values()) {
						System.out.println("| Id: " + i.getId() + " | Nome: " + i.getNome() + " | Permissao: " + i.getClass().getSimpleName() + " |");
					}
					this.users();
				case 5:
					System.out.println("Insira o id do usuário para ser excluido:");
					id = entrada.nextInt();
					tabelau.excluir(id);
					System.out.println("Usuario excluido.");
					this.users();
				case 6:
					this.principal(this.tabelaf, this.tabelap, this.tabelat, this.tabelau);
				default:
					System.out.println("Selecione uma opcao valida.");
					this.users();
			}
		} else {
			if(selection == 1) {
				this.trocasenha();
				this.users();
			} else if(selection == 2) {
				this.principal(this.tabelaf, this.tabelap, this.tabelat, this.tabelau);
			} else {
				System.out.println("Selecione uma opcao valida.");
				this.users();
			}
		}

	}
	
	public void trocasenha() {
		System.out.println("Insira a nova senha do usuário:");
		String senha = entrada.nextLine();
		loggeduser.setSenha(senha);
		tabelau.alterar(loggeduser.getId(), loggeduser);
		System.out.println("Senha de usuario atualizada.");
	}
	
	public void fornecedores() {
		
		String nome;
		String cnpj;
		int id;
		Fornecedor fornecedor;
		
		System.out.println("\nSelecione a operacao:");
		System.out.println("1 - Consulta fornecedores");
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
			
		} else {
			if(loggeduser.getPermlevel() == 1) {
				switch(selection) {
					case 2:
						System.out.println("Insira o nome do fornecedor:");
						nome = entrada.nextLine();
						System.out.println("Insira o cnpj do fornecedor:");
						cnpj = entrada.nextLine();
						fornecedor = new Fornecedor(nome,cnpj,null);
						tabelaf.incluir(fornecedor);
						System.out.println("Fornecedor cadastrado.");
						this.fornecedores();
					case 3:
						this.alteraFornecedor();
						this.fornecedores();
					case 4:
						System.out.println("Insira o id do fornecedor para ser excluido:");
						id = entrada.nextInt();
						tabelaf.excluir(id);
						System.out.println("Fornecedor excluido.");
						this.fornecedores();
					case 5:
						this.principal(this.tabelaf, this.tabelap, this.tabelat, this.tabelau);
					default:
						System.out.println("Selecione uma opcao valida.");
						this.fornecedores();
				}
			} else {
				if(selection == 2) {
					this.principal(this.tabelaf, this.tabelap, this.tabelat, this.tabelau);
				} else {
					System.out.println("Selecione uma opcao valida.");
					this.fornecedores();
				}
			}
		}
	}
	
	public void alteraFornecedor() {
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
				System.out.println("Insira o Id dofornecedor deseja alterar:");
				id = entrada.nextInt();
				System.out.println("Insira o novo nome para o fornecedor:");
				nome = entrada.nextLine();
				tempf = tabelaf.consulta(id);
				tempf.setNome(nome);
				System.out.println("Nome do fornecedor alterado.");
			case 2:
				System.out.println("Insira o Id dofornecedor deseja alterar:");
				id = entrada.nextInt();
				System.out.println("Insira o novo CNPJ para o fornecedor:");
				cnpj = entrada.nextLine();
				tempf = tabelaf.consulta(id);
				tempf.setCnpj(cnpj);
				System.out.println("CNPJ do fornecedor alterado.");
			case 3:
				break;
			default:
				System.out.println("Selecione uma opcao valida.");
				this.alteraFornecedor();
		}
	}
	
	public void produtos() {
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
	}
	
	public void transportadoras() {
		
	}
	
}
