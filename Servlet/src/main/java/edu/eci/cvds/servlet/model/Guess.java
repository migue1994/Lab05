package edu.eci.cvds.servlet.model;

import java.util.Random;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "guessBean")
@ApplicationScoped
public class Guess {
	
	private final int maximo=100000;
	private final int penal=10000;
	private int premio;
	private int guessNum;
	private boolean winner,gameOver;
	
	
	public Guess() {
		restart();
	}
	
	private int randomNum() {
		Random random = new Random(System.currentTimeMillis());
		int num = random.nextInt(100);
		random.setSeed(System.currentTimeMillis());	
		return num;
	}
	
	/*
	 * Recibe un intento de adivinanza;
	 */
	public void guess(int intento) {
		if(intento!=this.guessNum) {
			int resta=this.premio-penal;
			if(resta>=0)this.premio-=penal;
			else this.gameOver=true;
		}
		else {
			this.gameOver=true;
			this.winner=true;
		}
	}
	
	
	public void restart() {
		this.premio=maximo;
		this.guessNum=randomNum();
		this.winner=false;
		this.gameOver=false;
	}
	
	public int getPremio() {
		return this.premio;
	}

	public void setNum() {
		
	}
	
}
