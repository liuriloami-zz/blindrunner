package osphi.blindrunner.mecanica;

public class Mapa3 extends Mapa {
	private boolean arestas[][][][];
	private final int tamanhoMapa = 10;	

	public int getTamanho() {
		return this.tamanhoMapa;
	}
	
	public Mapa3(Jogador jogador, Monstro monstro) {
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
		
		this.arestas[0][0][1][0] = this.arestas[1][0][2][0] = this.arestas[2][0][3][0] = this.arestas[5][0][6][0] = this.arestas[6][0][7][1] = this.arestas[7][0][8][0] = this.arestas[8][0][9][0] = true;
		this.arestas[1][1][2][1] = this.arestas[4][1][5][1] = this.arestas[6][1][7][1] = this.arestas[7][1][8][1] = true;
		this.arestas[0][2][1][2] = this.arestas[1][2][2][2] = this.arestas[2][2][3][2] = this.arestas[3][2][4][2] = this.arestas[4][2][5][2] = this.arestas[5][2][6][2] = this.arestas[6][2][7][2] = true;
		this.arestas[0][3][1][3] = this.arestas[1][3][2][3] = this.arestas[2][3][3][3] = this.arestas[3][3][4][3] = this.arestas[6][3][7][3] = this.arestas[7][3][8][3] = this.arestas[8][3][9][3] = true;
		this.arestas[1][4][2][4] = this.arestas[2][4][3][4] = this.arestas[4][4][5][4] = this.arestas[5][4][6][4] = this.arestas[6][4][7][4] = this.arestas[7][4][8][4] = this.arestas[8][4][9][4] = true;
		this.arestas[2][5][3][5] = this.arestas[3][5][4][5] = this.arestas[4][5][5][5] = this.arestas[7][5][8][5] = true;
		this.arestas[0][6][1][6] = this.arestas[1][6][2][6] = this.arestas[2][6][3][6] = this.arestas[6][6][7][6] = this.arestas[7][6][8][6] = this.arestas[8][6][9][6] = true;
		this.arestas[1][7][2][7] = this.arestas[3][7][4][7] = this.arestas[5][7][6][7] = this.arestas[6][7][7][7] = this.arestas[7][7][8][7] = this.arestas[8][7][9][7] = true;
		this.arestas[0][8][1][8] = this.arestas[2][8][3][8] = this.arestas[4][8][5][8] = this.arestas[6][8][7][8] = this.arestas[7][8][8][8] = this.arestas[8][8][9][8] = true;
		this.arestas[1][9][2][9] = this.arestas[2][9][3][9] = this.arestas[3][9][4][9] = this.arestas[4][9][5][9] = this.arestas[5][9][6][9] = this.arestas[6][9][7][9] = this.arestas[7][9][8][9] = this.arestas[8][9][9][9] = true;
		
		this.arestas[0][0][0][1] = this.arestas[0][1][0][2] = this.arestas[0][2][0][3] = this.arestas[0][3][0][4] = this.arestas[0][4][0][5] = this.arestas[0][5][0][6] = this.arestas[0][6][0][7] = this.arestas[0][7][0][8] = this.arestas[0][8][0][9] = true;
		this.arestas[1][1][1][2] = this.arestas[1][4][1][5] = this.arestas[1][5][1][6] = this.arestas[1][7][1][8] = this.arestas[1][8][1][9] = true;
		this.arestas[2][4][2][5] = this.arestas[2][7][2][8] = true;
		this.arestas[3][0][3][1] = this.arestas[3][1][3][2] = this.arestas[3][5][3][6] = this.arestas[3][7][3][8] = true;
		this.arestas[4][0][4][1] = this.arestas[4][1][4][2] = this.arestas[4][3][4][4] = this.arestas[4][5][4][6] = this.arestas[4][6][4][7] = this.arestas[4][7][4][8] = true;
		this.arestas[5][0][5][1] = this.arestas[5][2][5][3] = this.arestas[5][3][5][4] = this.arestas[5][4][5][5] = this.arestas[5][5][5][6] = this.arestas[5][6][5][7] = this.arestas[5][7][5][8] = this.arestas[5][8][5][9] = true;
		this.arestas[6][2][6][3] = this.arestas[6][4][6][5] = this.arestas[6][5][6][6] = this.arestas[6][6][6][7] = this.arestas[6][7][6][8] = true;
		this.arestas[7][1][7][2] = true;
		this.arestas[8][1][8][2] = this.arestas[8][2][8][3] = this.arestas[8][5][8][6] = true;
		this.arestas[9][0][9][1] = this.arestas[9][1][9][2] = this.arestas[9][2][9][3] = this.arestas[9][3][9][4] = this.arestas[9][4][9][5] = this.arestas[9][5][9][6] = this.arestas[9][7][9][8] = this.arestas[9][8][9][9] = true;
		
		//Inserir um mapa aqui
		jogador.setPos(4.6,0.6);
		jogador.setAngulo(270);
		monstro.setPos(7.6,5.6);
		
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