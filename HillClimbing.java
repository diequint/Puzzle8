/* Clase encargada de implementar la función "f" y devolver
 * la mejor opción a seguir para cada paso
 */

public class HillClimbing {
	int dims, pos, score_inicial;
	int[][] tab_inicio, tab_meta;
	char prox_mov;

	public HillClimbing(int dims, int[][] tab_inicio, int[][] tab_meta) {
		this.dims = dims;
		this.tab_inicio = tab_inicio;
		this.tab_meta = tab_meta;
	}
	
	public char nuevaIteracion() {
		pos = obtenerPosicion();
		prox_mov = generaMovimientos();
		return prox_mov;
	}

	//Este método obtiene el tipo de posición en el que se encuentra la pieza
	//en blanco y genera los posibles movimientos para cada posible movimiento
	private int obtenerPosicion() {
		int movs_posibles=0;
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				movs_posibles++;
				if(tab_inicio[i][j]==0){
					i=dims;
					j=dims;
				}
			}
		}
		return movs_posibles;
	}
	
	private char generaMovimientos() {
		int[] calif = new int[] { 0, 0, 0, 0};
		int opc=0;
		switch(pos){
			case 1:
				calif[3] = moveRight(false);
				calif[1] = moveDown(false);
				break;
			case 2:
				calif[2] = moveLeft(false);
				calif[3] = moveRight(false);
				calif[1] = moveDown(false);
				break;
			case 3:
				calif[2] = moveLeft(false);
				calif[1] = moveDown(false);
				break;
			case 4:
				calif[0] = moveUp(false);
				calif[3] = moveRight(false);
				calif[1] = moveDown(false);
				break;
			case 5:
				calif[0] = moveUp(false);
				calif[2] = moveLeft(false);
				calif[3] = moveRight(false);
				calif[1] = moveDown(false);
				break;
			case 6:
				calif[0] = moveUp(false);
				calif[2] = moveLeft(false);
				calif[1] = moveDown(false);
				break;
			case 7:
				calif[0] = moveUp(false);
				calif[3] = moveRight(false);
				break;
			case 8:
				calif[0] = moveUp(false);
				calif[2] = moveLeft(false);
				calif[3] = moveRight(false);
				break;
			case 9:
				calif[0] = moveUp(false);
				calif[2] = moveLeft(false);
				break;
		}
		for(int i=0; i<4; i++){
			if(calif[i]>opc){
				opc=calif[i];
			}
		}
		if(opc<=0){		// TODO: Reemplazar el 0 con el valor de
			return 'N';	// la evaluación del tablero inicial
		}
		if(opc==calif[0]){
			moveUp(true);
			return 'U';
		}
		if(opc==calif[1]){
			moveDown(true);
			return 'D';
		}
		if(opc==calif[2]){
			moveLeft(true);
			return 'L';
		}
		moveRight(true);
		return 'R';
	}
	//Temporalmente se colocan valores arbitrarios
	private int moveUp(boolean band) {
		return 1;
	}

	private int moveDown(boolean band) {
		return 2;
	}

	private int moveLeft(boolean band) {
		return 3;
	}

	private int moveRight(boolean band) {
		return 4;
	}
	
}
