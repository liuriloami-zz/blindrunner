package osphi.blindrunner.mecanica;

public class Mapa5 extends Mapa {
	private boolean arestas[][][][];
	private final int tamanhoMapa = 15;	

	public int getTamanho() {
		return this.tamanhoMapa;
	}
	
	public Mapa5(Jogador jogador, Monstro monstro) {
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

		this.arestas[0][0][1][0] = this.arestas[1][0][2][0] = this.arestas[2][0][3][0] = this.arestas[3][0][4][0] = this.arestas[4][0][5][0] =
		this.arestas[5][0][6][0] = this.arestas[7][0][8][0] = this.arestas[8][0][9][0] = this.arestas[9][0][10][0] = this.arestas[10][0][11][0] = this.arestas[11][0][12][0] = this.arestas[12][0][13][0] = this.arestas[13][0][14][0] = true;
		this.arestas[1][1][2][1] = this.arestas[2][1][3][1] = this.arestas[3][1][4][1] = this.arestas[5][1][6][1] = this.arestas[6][1][7][1] = this.arestas[8][1][9][1] = this.arestas[10][1][11][1] = this.arestas[11][1][12][1] = this.arestas[12][1][13][1] = true;
		this.arestas[2][2][3][2] = this.arestas[5][2][6][2] = this.arestas[9][2][10][2] = this.arestas[10][2][11][2] = this.arestas[11][2][12][2] = true;
		this.arestas[1][3][2][3] = this.arestas[3][3][4][3] = this.arestas[4][3][5][3] = this.arestas[10][3][11][3] = this.arestas[11][3][12][3] = true;
		this.arestas[0][4][1][4] = this.arestas[8][4][9][4] = this.arestas[9][4][10][4] = this.arestas[10][4][11][4] = this.arestas[11][4][12][4] = true;
		this.arestas[3][5][4][5] = this.arestas[4][5][5][5] = this.arestas[5][5][6][5] = this.arestas[0][5][1][5] = this.arestas[8][5][9][5] = this.arestas[9][5][10][5] = this.arestas[10][5][11][5] = this.arestas[11][5][12][5] = true;
		this.arestas[2][6][3][6] = this.arestas[3][6][4][6] = this.arestas[4][6][5][6] = this.arestas[5][6][6][6] = this.arestas[9][6][10][6] = this.arestas[10][6][11][6] = this.arestas[11][6][12][6] = true;
		this.arestas[2][7][3][7] = this.arestas[3][7][4][7] = this.arestas[4][7][5][7] = this.arestas[5][7][6][7] = this.arestas[6][7][7][7] = this.arestas[7][7][8][7] = this.arestas[9][7][10][7] = this.arestas[10][7][11][7] = true;
		this.arestas[0][8][1][8] = this.arestas[2][8][3][8] = this.arestas[4][8][5][8] = this.arestas[5][8][6][8] = this.arestas[7][8][8][8] = this.arestas[8][8][9][8] = this.arestas[9][8][10][8] = true;
		this.arestas[2][9][3][9] = this.arestas[3][9][4][9] = this.arestas[4][9][5][9] = this.arestas[5][9][6][9] = this.arestas[8][9][9][9] = this.arestas[10][9][11][9] = true;
		this.arestas[1][10][2][10] = this.arestas[2][10][3][10] = this.arestas[3][10][4][10] = this.arestas[4][10][5][10] = this.arestas[5][10][6][10] = this.arestas[6][10][7][10] = this.arestas[7][10][8][10] = this.arestas[8][10][9][10] = this.arestas[9][10][10][10] = this.arestas[10][10][11][10] = this.arestas[11][10][12][10] = true;
		this.arestas[3][11][4][11] = this.arestas[5][11][6][11] = this.arestas[7][11][8][11] = this.arestas[9][11][10][11] = this.arestas[11][11][12][11] = true;
		this.arestas[2][12][3][12] = this.arestas[4][12][5][12] = this.arestas[6][12][7][12] = this.arestas[8][12][9][12] = this.arestas[10][12][11][12] = true;
		this.arestas[1][13][2][13] = this.arestas[3][13][4][13] = this.arestas[4][13][5][13] =this.arestas[5][13][6][13] = this.arestas[6][13][7][13] = this.arestas[7][13][8][13] = this.arestas[8][13][9][13] = this.arestas[9][13][10][13] = this.arestas[10][13][11][13] = this.arestas[11][13][12][13] = this.arestas[12][13][13][13] = true;
		this.arestas[0][14][1][14] = this.arestas[1][14][2][14] = this.arestas[2][14][3][14] = this.arestas[3][14][4][14]  = this.arestas[4][14][5][14] = this.arestas[5][14][6][14] = this.arestas[6][14][7][14] = this.arestas[7][14][8][14] = this.arestas[8][14][9][14] = this.arestas[9][14][10][14] = this.arestas[10][14][11][14] = this.arestas[11][14][12][14] = this.arestas[12][14][13][14] = this.arestas[13][14][14][14] = true;

		
		
		this.arestas[0][0][0][1] = this.arestas[0][1][0][2] = this.arestas[0][2][0][3] = this.arestas[0][3][0][4] = this.arestas[0][4][0][5] = this.arestas[0][5][0][6] = this.arestas[0][6][0][7] = this.arestas[0][7][0][8] = this.arestas[0][8][0][9] = this.arestas[0][9][0][10] = this.arestas[0][10][0][11] = this.arestas[0][11][0][12] = this.arestas[0][12][0][13] = this.arestas[0][13][0][14] = true;
		this.arestas[1][1][1][2] = this.arestas[1][2][1][3] = this.arestas[1][3][1][4] = this.arestas[1][4][1][5] = this.arestas[1][6][1][7] = this.arestas[1][7][1][8] = this.arestas[1][8][1][9] = this.arestas[1][9][1][10] = this.arestas[1][10][1][11] = this.arestas[1][11][1][12] = this.arestas[1][12][1][13] = true;
		this.arestas[2][2][2][3] = this.arestas[2][3][2][4] = this.arestas[2][4][2][5] = this.arestas[2][8][2][9] = this.arestas[2][11][2][12] = true;
		this.arestas[3][2][3][3] = this.arestas[3][3][3][4] = this.arestas[3][5][3][6] = this.arestas[3][11][3][12] = this.arestas[3][13][3][14] = true;
		this.arestas[4][1][4][2] = this.arestas[4][6][4][7] = this.arestas[4][7][4][8] = this.arestas[4][11][4][12] = true;
		this.arestas[5][1][5][2] = this.arestas[5][2][5][3] = this.arestas[5][3][5][4] = this.arestas[5][4][5][5] = this.arestas[5][11][5][12] = true;
		this.arestas[6][2][6][3] = this.arestas[6][3][6][4] = this.arestas[6][8][6][9] = this.arestas[6][11][6][12] = true;
		this.arestas[7][1][7][2] = this.arestas[7][2][7][3] = this.arestas[7][3][7][4] = this.arestas[7][4][7][5] = this.arestas[7][5][7][6] = this.arestas[7][6][7][7] = this.arestas[7][8][7][9] = this.arestas[7][3][7][4] = this.arestas[7][11][7][12] = true;
		this.arestas[8][1][8][2] = this.arestas[8][2][8][3] = this.arestas[8][5][8][6] = this.arestas[8][6][8][7] = this.arestas[8][8][8][9] = this.arestas[8][9][8][10] = this.arestas[8][10][8][11] = this.arestas[8][11][8][12] = true;
		this.arestas[9][1][9][2] = this.arestas[9][2][9][3] = this.arestas[9][3][9][4] = this.arestas[9][6][9][7] = this.arestas[9][11][9][12] = true;
		this.arestas[10][8][10][9] = this.arestas[10][11][10][12] = true;
		this.arestas[11][7][11][8] = this.arestas[11][8][11][9] = this.arestas[11][11][11][12] = true;
		this.arestas[12][1][12][2] = this.arestas[12][2][12][3] = this.arestas[12][4][12][5] = this.arestas[12][5][12][6] = this.arestas[12][6][12][7] = this.arestas[12][7][12][8] = this.arestas[12][8][12][9] = this.arestas[12][11][12][12] = true;
		this.arestas[13][1][13][2] = this.arestas[13][2][13][3] = this.arestas[13][3][13][4] = this.arestas[13][4][13][5] = this.arestas[13][5][13][6] = this.arestas[13][6][13][7] = this.arestas[13][7][13][8] = this.arestas[13][8][13][9] = this.arestas[13][9][13][10] = this.arestas[13][10][13][11] = this.arestas[13][11][13][12] = this.arestas[13][12][13][13] = true;
		this.arestas[14][0][14][1] = this.arestas[14][1][14][2] = this.arestas[14][2][14][3] = this.arestas[14][3][14][4] = this.arestas[14][4][14][5] = this.arestas[14][5][14][6] = this.arestas[14][6][14][7] = this.arestas[14][7][14][8] = this.arestas[14][8][14][9] = this.arestas[14][9][14][10] = this.arestas[14][10][14][11] = this.arestas[14][11][14][12] = this.arestas[14][12][14][13] = this.arestas[14][13][14][14] = true;

/*		this.arestas[9][2][9][3] = this.arestas[9][3][9][4] = false;
		this.arestas[8][3][8][4] = true;
		this.arestas[11][9][12][9] = true;
		this.arestas[7][9][7][10] = true;
		this.arestas[13][13][13][14] = true;
		this.arestas[7][13][7][14] = true;
		this.arestas[6][4][6][5] = true;
		this.arestas[5][4][5][5] = false;
		this.arestas[3][6][4][6] = false;
		this.arestas[2][6][2][7] = true;
		this.arestas[1][5][1][6] = true;
		this.arestas[1][7][1][8] = false;*/
		jogador.setPos(7.6,4.6);
		jogador.setAngulo(90);
		monstro.setPos(7.6,2.6);
		
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
		return 7.5;
	}
	
	public double getObjetivoY() {
		return 0.5;
	}
	
}
