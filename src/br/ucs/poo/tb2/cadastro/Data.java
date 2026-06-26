package br.ucs.poo.tb2.cadastro;

import java.io.Serializable;
import java.time.*;

public class Data implements Serializable{
	private int dia;
	private int mes;
	private int ano;
	
	public Data(){
		this.dia = LocalDate.now().getDayOfMonth();
		this.mes = LocalDate.now().getMonthValue();
		this.ano = LocalDate.now().getYear();
	}

	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public String getData() {
		return this.dia + "/" + this.mes + "/" + this.ano;
	}
	
}
