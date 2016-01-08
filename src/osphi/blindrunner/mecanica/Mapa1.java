package osphi.blindrunner.mecanica;

public class Mapa1 extends Mapa {
	private boolean arestas[][][][];
	private final int tamanhoMapa = 5;	

	public int getTamanho() {
		return this.tamanhoMapa;
	}
	
	public Mapa1(Jogador jogador, Monstro monstro) {
		this.arestas = new boolean[tamanhoMapa][tamanhoMapa][tamanhoMapa][tamanhoMapa];
		
		for (int i = 0; i < this.tamanhoMapa; i++)
			for (int j = 0; j < this.tamanhoMapa; j++)
				for (int k = 0; k < this.tamanhoMapa; k++)
					for (int l = 0; l < this.tamanhoMapa; l++) {
						if (i == k && j == l)
							this.arestas[i][j][k][l] = true;
						else
							this.arestas[i][j][k][l] = false;
					}
		
		this.arestas[2][0][3][0] = this.arestas[3][0][4][0] = true;
		this.arestas[0][1][1][1] = this.arestas[1][1][2][1] = true;
		this.arestas[0][2][1][2] = this.arestas[1][2][2][2] = true;
		this.arestas[2][3][3][3] = true;
		this.arestas[0][4][1][4] = this.arestas[1][4][2][4] = this.arestas[3][4][4][4] = true;
		
		this.arestas[0][0][0][1] = this.arestas[0][2][0][3] = this.arestas[0][3][0][4] = true;
		this.arestas[1][0][1][1] = this.arestas[1][3][1][4] = true;
		this.arestas[2][0][2][1] = this.arestas[2][1][2][2] = this.arestas[2][2][2][3] = this.arestas[2][3][2][4] = true;
		this.arestas[3][0][3][1] = this.arestas[3][2][3][3] = this.arestas[3][3][3][4] = true;
		this.arestas[4][0][4][1] = this.arestas[4][1][4][2] = this.arestas[4][2][4][3] = this.arestas[4][3][4][4] = true;
		
		//Inserir um mapa aqui
		jogador.setPos(0.6,0.6);
		jogador.setAngulo(270);
		monstro.setPos(1.6,3.6);
		
	}

	public boolean colisao(double x1, double y1, double x2, double y2) {
		
		x1 = Math.floor(x1);
		y1 = Math.floor(y1);
		x2 = Math.floor(x2);
		y2 = Math.floor(y2);
		
		if (x2 < 0 || x2 >= this.tamanhoMapa || y2 < 0 || y2 >= this.tamanhoMapa)
			return true;
		
		if (this.arestas[(int)Math.min(x1,x2)][(int)Math.min(y1,y2)][(int)Math.max(x1,x2)][(int)Math.max(y1,y2)] == false)
			return true;
		else
			return false;
	}

	public double getObjetivoX() {
		return 4.5;
	}
	
	public double getObjetivoY() {
		return 4.5;
	}
	
}