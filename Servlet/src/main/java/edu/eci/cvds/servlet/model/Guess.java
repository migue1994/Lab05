package edu.eci.cvds.servlet.model;

import java.util.ArrayList;
import java.util.Random;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "guessBean")
@SessionScoped
public class Guess {
	
	private final int maximo=100000;
	private final int penal=10000;
	private final int rango=5;
	private int premio;
	private int guessNum,intentos;
	private boolean winner,gameOver;
	private int guessUser;
	private String estado;
	private ArrayList<Integer> listaIntentos;
	
	
	public Guess() {
		restart();
	}
	
	private int randomNum() {
		Random random = new Random(System.currentTimeMillis());
		int num = random.nextInt(100)+1;
		random.setSeed(System.currentTimeMillis());	
		return num;
	}
	
	/*
	 * Recibe un intento de adivinanza;
	 */
	public void guess() {
		if(!this.gameOver && !this.winner) {
			if(guessUser!=this.guessNum) {
				int resta=this.premio-penal;
				if(resta>=0) {
					this.premio-=this.penal;
					if(this.premio==0) this.gameOver=true;
				}
			}
			else {
				this.gameOver=true;
				this.winner=true;
			}
			this.listaIntentos.add(this.intentos,guessUser);
			this.intentos++;
			
		}
	}
	
	
	public void restart() {
		this.premio=maximo;
		this.guessNum=randomNum();
		this.intentos=0;
		this.winner=false;
		this.gameOver=false;
		this.guessUser=0;
		this.setEstado("En juego");
		this.listaIntentos=new ArrayList<Integer>();
		inicializarLista(this.listaIntentos,(int)(maximo/penal));
	}
	
	private void inicializarLista(ArrayList<Integer> lista, int n) {
		for(int i=0;i<n;i++) lista.add(i,0);
	}
	
	public int getPremio() {
		return this.premio;
	}

	public void setPremio(int premio) {
		this.premio=premio;
	}

	public int getGuessUser() {
		return this.guessUser;
	}

	public void setGuessUser(int guessUser) {
		this.guessUser=guessUser;
	}
	
	public int getIntentos() {
		return this.intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos=intentos;
	}
	
	public String getEstado() {
		if(!gameOver) {
			this.estado="En juego";
		}
		else {
			if(winner) this.estado="Has ganado";
			else this.estado="Has perdido";
		}
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado=estado;
	}
	
	public int getGuessNum() {
		return this.guessNum;
	}
	
	public void getGuessNum(int guessNum) {
		this.guessNum=guessNum;
	}
	
	public ArrayList<Integer> getListaIntentos(){
		ArrayList<Integer>l=new ArrayList<Integer>();
		int desde,hasta,j=0;
		
		if(this.intentos<this.rango) {
			desde=0;hasta=this.rango;
		}
		else {
			desde=this.intentos-this.rango;hasta=this.rango+desde;
		}
		for(int i=desde;i<hasta;i++) {
			l.add(j,listaIntentos.get(i));
			j++;
		}
		
		return l;
	}
	
	public void setListaIntentos(ArrayList<Integer> listaIntentos){
		this.listaIntentos=listaIntentos;
	}
	
}





