package osphi.blindrunner.mecanica;

public class Monstro {
	private double x, y;
	private double velocidade;
	private double aceleracao;
	private int[][][] parent;
	private int[][] dist;
	private int[] caminho;
	
	public Monstro (double velInicial) {
		this.velocidade = velInicial;
		this.x = this.y = this.aceleracao = 0;
		this.parent = null;
		caminho = new int[2];
	}

	public void setAceleracao(double aceleracao) {
		this.aceleracao = aceleracao;
	}
	
	public void buscaProfundidade(Mapa mapa, int x1, int y1) {
		if (mapa.colisao(x1, y1, x1+1, y1) == false && dist[x1+1][y1] > dist[x1][y1] + 1) {
			dist[x1+1][y1] = dist[x1][y1] + 1;
			parent[x1+1][y1][0] = x1;
			parent[x1+1][y1][1] = y1;
			buscaProfundidade(mapa, x1+1, y1);
		}
		if (mapa.colisao(x1, y1, x1, y1+1) == false && dist[x1][y1+1] > dist[x1][y1] + 1) {
			dist[x1][y1+1] = dist[x1][y1] + 1;
			parent[x1][y1+1][0] = x1;
			parent[x1][y1+1][1] = y1;
			buscaProfundidade(mapa, x1, y1+1);
		}
		if (mapa.colisao(x1, y1, x1, y1-1) == false && dist[x1][y1-1] > dist[x1][y1] + 1) {
			dist[x1][y1-1] = dist[x1][y1] + 1;
			parent[x1][y1-1][0] = x1;
			parent[x1][y1-1][1] = y1;
			buscaProfundidade(mapa, x1, y1-1);
		}
		if (mapa.colisao(x1, y1, x1-1, y1) == false && dist[x1-1][y1] > dist[x1][y1] + 1) {
			dist[x1-1][y1] = dist[x1][y1] + 1;
			parent[x1-1][y1][0] = x1;
			parent[x1-1][y1][1] = y1;
			buscaProfundidade(mapa, x1-1, y1);
		}
	}

	public void getCaminho(Mapa mapa, int x1, int y1, int x2, int y2) {
		for (int i = 0; i < mapa.getTamanho(); i++)
			for (int j = 0; j < mapa.getTamanho(); j++)
				dist[i][j] = 1000;
		parent[x1][y1][0] = x1;
		parent[x1][y1][1] = y1;
		dist[x1][y1] = 0;
		buscaProfundidade(mapa, x1, y1);
		
		int x = x2;
		int y = y2;
		
		while(dist[x][y] > 1) {
			x2 = parent[x][y][0];
			y2 = parent[x][y][1];
			x = x2;
			y = y2;
		}
		
		caminho[0] = x;
		caminho[1] = y;
	}
	
	public boolean perseguir(Mapa mapa, double jogadorX, double jogadorY) {
	
		if (parent == null) {
			parent = new int[mapa.getTamanho()][mapa.getTamanho()][2];
			dist = new int[mapa.getTamanho()][mapa.getTamanho()];
			
		}
		
		if (Math.floor(jogadorX) == Math.floor(this.x) && Math.floor(jogadorY) == Math.floor(this.y))
			return true;
		
		if (Math.abs(caminho[0]+0.5 - this.x) < 0.5 && Math.abs(caminho[1]+0.5 - this.y) < 0.5) {
			getCaminho(mapa, (int) this.x, (int)this.y, 
				(int)jogadorX, (int)jogadorY);
		}
		
		double angulo = Math.atan2(caminho[1]-this.y+0.5, caminho[0]-this.x+0.5) * 360.0f /3.14159;
		if (angulo < 0) angulo += 360.0f;
		
		
		double newX = this.x - this.velocidade*Math.sin(angulo);
		double newY = this.y + this.velocidade*Math.cos(angulo);
		
		this.x = newX;
		this.y = newY;
		

		return false;
	}

	public void acelerar() {
		this.velocidade += this.aceleracao;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setPos (double x, double y) {
		this.x = x;
		this.y = y;
		caminho[0] = (int)x;
		caminho[1] = (int)y;
	}
}