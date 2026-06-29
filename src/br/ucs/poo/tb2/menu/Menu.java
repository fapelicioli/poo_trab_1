package br.ucs.poo.tb2.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.ucs.poo.tb2.db.*;
import br.ucs.poo.tb2.user.*;

public class Menu {
	
	protected Scanner entrada = new Scanner(System.in);
	protected User loggeduser = null;
	protected TableFornecedor tabelaf;
	protected TableProduto tabelap;
	protected TableTransportadora tabelat;
	protected TableUser tabelau;
	
	
	public void inicio(TableFornecedor f, TableProduto p, TableTransportadora t, TableUser u) {
		
		this.tabelaf = f;
		this.tabelap = p;
		this.tabelat = t;
		this.tabelau = u;
		
		System.out.println("\nSelecione o sistema a ser acessado:");
		System.out.println("1 - Sistema compra");
		System.out.println("2 - Sistema interno");
		System.out.println("3 - sair\n");
		
		try {
			int selection = entrada.nextInt();
			
			switch(selection) {
				case 1:
					new Externo(f,p,t,u).base();
					break;
				case 2:
					new Interno(f,p,t,u).login();
					break;
				case 3:
					return;
				default:
					System.out.println("Selecione uma opcao valida.");
					this.inicio(f,p,t,u);
					break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Selecione uma opcao valida.");
			entrada.next();
			this.inicio(f,p,t,u);
		}

	}
}
