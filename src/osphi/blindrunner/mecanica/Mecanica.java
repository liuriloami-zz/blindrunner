package osphi.blindrunner.mecanica;

import java.io.IOException;

import android.app.Activity;
import osphi.blindrunner.MySoundPool;

public class Mecanica {
	private Mapa mapa;
	private Jogador jogador;
	private Monstro monstro;
	private double girar;
	private double andar;
	private boolean colisao;
	private boolean fimDeJogo;
	private boolean vitoria;
	private final double aceleracaoMonstro = 0;
	private final double velInicialMonstro = 0.1;
	private final int fps = 60;
	double l, r;
	
	public Mecanica (Activity activity, int level) throws IllegalArgumentException, IllegalStateException, IOException, InterruptedException {
		
		MySoundPool.startPlayer();
		MySoundPool.play("monsterId", 0.0f, 0.0f, true);
		MySoundPool.play("exitId", 0.0f, 0.0f, true);
		
		
		this.jogador = new Jogador();
		
		this.monstro = new Monstro(velInicialMonstro/fps);
		this.monstro.setAceleracao(aceleracaoMonstro);
		switch(level) {
		case 1:
			this.mapa = new Mapa1(this.jogador, this.monstro);
			break;
		case 2:
			this.mapa = new Mapa1(this.jogador, this.monstro);
			break;
		case 3:
			this.mapa = new Mapa1(this.jogador, this.monstro);
			break;
		case 4:
			this.mapa = new Mapa1(this.jogador, this.monstro);
			break;
		case 5:
			this.mapa = new Mapa1(this.jogador, this.monstro);
			break;
		
		}
		this.mapa = new Mapa(this.jogador, this.monstro);
		this.girar = 0;
		this.andar = 0;
		this.colisao = false;
		this.fimDeJogo = false;
		this.vitoria = false;
	}

	public synchronized void girar (double graus) {
		if (graus == 90)
			MySoundPool.play("playerTurningId", 0.25f, 0.75f, false);
		else
			MySoundPool.play("playerTurningId", 0.75f, 0.25f, false);
		this.girar = graus;		
	}

	public synchronized void andar (double passos) {
		MySoundPool.play("playerId", 0.75f, 0.75f, false);
		this.andar = passos;
	}
	
	public synchronized int executar () {
		
		MySoundPool.setVolume("exitId", (float) mapDistanceExit(true)/2.0f, (float) mapDistanceExit(false)/2.0f);
		MySoundPool.setVolume("monsterId", (float) mapDistanceMonster(true), (float) mapDistanceMonster(false));
		
		if (Math.abs(this.girar) == 90)
			this.jogador.girar(this.girar);
		
		if (this.girar > 0) {
			this.girar -= 90.0f/(this.fps * 0.55);
			MySoundPool.setVolume("playerTurningId", (float)(0.75 - 0.75*this.girar/90.0), 0.75f);
		} else if (this.girar < 0) {
			this.girar += 90.0f/(this.fps * 0.55);
			MySoundPool.setVolume("playerTurningId", 0.75f, (float)(0.75 + 0.75*this.girar/90.0));
		}
		
		this.colisao = this.mapa.colisao(this.jogador.getX(), this.jogador.getY(), this.jogador.getNewX(andar/fps), this.jogador.getNewY(andar/fps));
		if (this.colisao == false) {
			this.vitoria = this.jogador.andar(this.mapa, this.andar/fps);
		} else {
			this.andar = 0;
			MySoundPool.play("wallId", 1.0f, 1.0f, false);
			MySoundPool.stopOne("playerId");
		}
		
		if (this.vitoria == true) {
			MySoundPool.play("freedomId", 1.0f, 1.0f, false);
			return 2;
		}
		
		this.girar = 0;
		if (this.andar > 0)
			this.andar -= 0.01;
		else if (this.andar < 0)
			this.andar += 0.016;

		if (Math.abs(this.andar) < 0.01 && this.andar != 0) {
			this.andar = 0;
			MySoundPool.stopOne("playerId");
		}
		 
		this.fimDeJogo = this.monstro.perseguir(this.mapa, this.jogador.getX(), this.jogador.getY());
		if (this.fimDeJogo == true) {
			MySoundPool.play("gameoverId", 1.0f, 1.0f, false);
			return 1;
		}
		
		this.monstro.acelerar();
		
		return 0;
	}
	
	public double mapDistanceExit(boolean left)
	{
		double ratio, distance, volume;
		boolean sameLine = false;
		
		distance = Math.sqrt(Math.pow(mapa.getObjetivoX() - jogador.getX(), 2) + 
				Math.pow(mapa.getObjetivoY() - jogador.getY(), 2));
		
		volume = 0.6 + 0.4*Math.pow(Math.E, -distance/5);
		
		switch((int)jogador.getAngulo()) {
		case 0:
			ratio = -(mapa.getObjetivoY() - jogador.getY())/15.0f;
			if (Math.floor(jogador.getY()) == Math.floor(mapa.getObjetivoY()))
				sameLine = true;
			break;
		case 90:
			ratio = (mapa.getObjetivoX() - jogador.getX())/15.0f;
			if (Math.floor(jogador.getX()) == Math.floor(mapa.getObjetivoX()))
				sameLine = true;
			break;
		case 180:
			ratio = (mapa.getObjetivoY() - jogador.getY())/15.0f;
			if (Math.floor(jogador.getY()) == Math.floor(mapa.getObjetivoY()))
				sameLine = true;
			break;
		case 270:
			ratio = -(mapa.getObjetivoX() - jogador.getX())/15.0f;
			if (Math.floor(jogador.getX()) == Math.floor(mapa.getObjetivoX()))
				sameLine = true;
			break;
		default:
			ratio = 0;
			break;
		}

		if (left)
			if (sameLine == true)
				return volume;
			else if (ratio < 0)
				return volume*0.9;
			else
				return volume*0.3;
		else
			if (sameLine == true)
				return volume;
			else if (ratio > 0)
				return volume*0.9;
			else
				return volume*0.3;
	}
	
public double mapDistanceMonster(boolean left)
{
	double ratio, distance, volume;
	boolean sameLine = false;
	
	distance = Math.sqrt(Math.pow(monstro.getX() - jogador.getX(), 2) + 
			Math.pow(monstro.getY() - jogador.getY(), 2));

	volume = 0.2 + 0.8*Math.pow(Math.E, -(distance-1)/2);
			
	MySoundPool.setRate("monsterId", (float)(0.5f + 1.5*Math.pow(volume,2)));
	
	switch((int)jogador.getAngulo()) {
	case 0:
		ratio = -(monstro.getY() - jogador.getY())/15.0f;
		if (Math.floor(jogador.getY()) == Math.floor(monstro.getY()))
			sameLine = true;
		break;
	case 90:
		ratio = (monstro.getX() - jogador.getX())/15.0f;
		if (Math.floor(jogador.getX()) == Math.floor(monstro.getX()))
			sameLine = true;
		break;
	case 180:
		ratio = (monstro.getY() - jogador.getY())/15.0f;
		if (Math.floor(jogador.getY()) == Math.floor(monstro.getY()))
			sameLine = true;
		break;
	case 270:
		ratio = -(monstro.getX() - jogador.getX())/15.0f;
		if (Math.floor(jogador.getX()) == Math.floor(monstro.getX()))
			sameLine = true;
		break;
	default:
		ratio = 0;
		break;
	}
	
	if (mapa.colisao(jogador.getX(), jogador.getY(), monstro.getX(),  monstro.getY()) == false && 
			distance < 0.75) return 0;
	
	if (left)
		if (sameLine == true)
			return volume;
		else if (ratio < 0)
			return volume*0.9;
		else
			return volume*0.3;
	else
		if (sameLine == true)
			return volume;
		else if (ratio > 0)
			return volume*0.9;
		else
			return volume*0.3;
}	
	public synchronized Jogador getJogador() {
		return this.jogador;
	}
	
	public synchronized Monstro getMonstro() {
		return this.monstro;
	}
}

