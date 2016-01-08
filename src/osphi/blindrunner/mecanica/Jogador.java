package osphi.blindrunner.mecanica;

public class Jogador {
	private double x, y;
	private double angulo;

	public Jogador () {
		this.x = this.y = 0;
	}

	public double getAngulo() {
		return this.angulo;
	}
	
	public void setAngulo (double angulo) {
		this.angulo = angulo;
	}
	
	public void girar (double graus) {
		this.angulo += graus;
		if (this.angulo < 0) 
			this.angulo += 360;
		this.angulo %= 360;
	}
	
	public boolean andar (Mapa mapa, double andar) {
		if (Math.sqrt((mapa.getObjetivoX()-this.getNewX(andar))*(mapa.getObjetivoX()-this.getNewX(andar)) + 
				(mapa.getObjetivoY()-this.getNewY(andar))*(mapa.getObjetivoY()-this.getNewY(andar))) < 0.5) {
			return true;
		} else {
			this.x = this.getNewX(andar);
			this.y = this.getNewY(andar);
			return false;
		}
		
	}
	
	public double getX () {
		return this.x;
	}
	public double getY () {
		return this.y;
	}
	public double getNewX (double passo) {
		return this.x + ( passo * Math.cos((this.angulo/360.0)*(2*Math.PI) ) );
	}

	public double getNewY (double passo) {
		return this.y + ( passo * Math.sin((this.angulo/360.0)*(2*Math.PI) ) );
	}
	
	public void setPos (double x, double y) {
		this.x = x;
		this.y = y;
	}
}