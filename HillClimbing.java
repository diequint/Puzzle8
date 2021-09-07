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
		score_inicial = evaluaTablero(tab_inicio);
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
		switch(pos){// 4 posiciones de aristas, 4 esquinas y posición central
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
		if(opc<=score_inicial){	// Solo son válidos los resultados que tienen una
			return 'N';			// mayor evaluación que el anterior (tab_inicial)
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
	
	private int moveUp(boolean band) {
		int[][] prox_mejor = new int[dims][dims];
		for(int i=0; i<dims; i++){	// Copio en matriz temporal
			for(int j=0; j<dims; j++){
				prox_mejor[i][j] = tab_inicio[i][j];
			}
		}
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				if(tab_inicio[i][j]==0){
					prox_mejor[i][j]=tab_inicio[(i-1)][j];
					prox_mejor[(i-1)][j]=0;
					i=dims;	// Una vez efectuado el cambio
					j=dims;	// termino de ejecutar los ciclos
				}
			}
		}
		if(band){//No evalúa y reemplaza la matriz inicial para futuras iteraciones
			System.out.println("Se selecciono UP\n");
			tab_inicio=prox_mejor;
			return 0;
		}
		System.out.println("\nEvaluando UP");
		imprimeMatriz(prox_mejor);
		return evaluaTablero(prox_mejor);
	}

	private int moveDown(boolean band) {
		int[][] prox_mejor = new int[dims][dims];
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				prox_mejor[i][j] = tab_inicio[i][j];
			}
		}
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				if(tab_inicio[i][j]==0){
					prox_mejor[i][j]=tab_inicio[(i+1)][j];
					prox_mejor[(i+1)][j]=0;
					i=dims;
					j=dims;
				}
			}
		}
		if(band){
			System.out.println("Se selecciono DOWN\n");
			tab_inicio=prox_mejor;
			return 0;
		}
		System.out.println("\nEvaluando DOWN");
		imprimeMatriz(prox_mejor);
		return evaluaTablero(prox_mejor);
	}

	private int moveLeft(boolean band) {
		int[][] prox_mejor = new int[dims][dims];
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				prox_mejor[i][j] = tab_inicio[i][j];
			}
		}
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				if(tab_inicio[i][j]==0){
					prox_mejor[i][j]=tab_inicio[i][(j-1)];
					prox_mejor[i][(j-1)]=0;
					i=dims;
					j=dims;
				}
			}
		}
		if(band){
			System.out.println("Se selecciono LEFT\n");
			tab_inicio=prox_mejor;
			return 0;
		}
		System.out.println("\nEvaluando LEFT");
		imprimeMatriz(prox_mejor);
		return evaluaTablero(prox_mejor);
	}

	private int moveRight(boolean band) {
		int[][] prox_mejor = new int[dims][dims];
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				prox_mejor[i][j] = tab_inicio[i][j];
			}
		}
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				if(tab_inicio[i][j]==0){
					prox_mejor[i][j]=tab_inicio[i][(j+1)];
					prox_mejor[i][(j+1)]=0;
					i=dims;
					j=dims;
				}
			}
		}
		if(band){
			System.out.println("Se selecciono RIGHT\n");
			tab_inicio=prox_mejor;
			return 0;
		}
		System.out.println("\nEvaluando RIGHT");
		imprimeMatriz(prox_mejor);
		return evaluaTablero(prox_mejor);
	}

	private int evaluaTablero(int[][] prox_mejor) {
		int correctas=0;
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				if(prox_mejor[i][j]==tab_meta[i][j]){
					correctas+=1;
				}
			}
		}
		System.out.println("Posiciones correctas: "+correctas);
		return correctas;
	}
	
	public void imprimeMatriz(int[][] mat){
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				System.out.print(mat[i][j]+" ");
			}
			System.out.print("\n");
		}
	}

}