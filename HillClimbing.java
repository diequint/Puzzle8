/* Clase encargada de implementar la función "f" y devolver
 * la mejor opción a seguir para cada paso
 */


public class HillClimbing {
	int dims, pos, score_inicial;
	int[][] tab_inicio, tab_meta;
	char prox_mov;
	boolean end=false;

	public HillClimbing(int dims, int[][] tab_inicio, int[][] tab_meta) {
		this.dims = dims;
		this.tab_inicio = tab_inicio;
		this.tab_meta = tab_meta;
	}
	
	public char nuevaIteracion() {
		if(end){	//Condición de salida
			return 'E';
		}
		score_inicial = evaluaTablero(tab_inicio);
		pos = obtenerPosicion();
		prox_mov = generaMovimientos();
		return prox_mov;
	}

	//Este método obtiene el tipo de posición en el que se encuentra la pieza
	//en blanco y genera los posibles movimientos para cada posible movimiento
	private int obtenerPosicion() {
		int posicion=0;
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				posicion++;
				if(tab_inicio[i][j]==0){
					i=dims;
					j=dims;
				}
			}
		}
		return posicion;
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
		if(band){//No evalúa y reemplaza la matriz inicial por la seleccionada
			//System.out.println("Se selecciono UP\n");
			tab_inicio=prox_mejor;
			return 0;
		}
		//System.out.println("\nEvaluando UP");
		//imprimeMatriz(prox_mejor);
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
			//System.out.println("Se selecciono DOWN\n");
			tab_inicio=prox_mejor;
			return 0;
		}
		//System.out.println("\nEvaluando DOWN");
		//imprimeMatriz(prox_mejor);
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
			//System.out.println("Se selecciono LEFT\n");
			tab_inicio=prox_mejor;
			return 0;
		}
		//System.out.println("\nEvaluando LEFT");
		//imprimeMatriz(prox_mejor);
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
			//System.out.println("Se selecciono RIGHT\n");
			tab_inicio=prox_mejor;
			return 0;
		}
		//System.out.println("\nEvaluando RIGHT");
		//imprimeMatriz(prox_mejor);
		return evaluaTablero(prox_mejor);
	}

	private int evaluaTablero(int[][] prox_mejor) {
		int correctas=0, sumaDist=0, ady;
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				if(prox_mejor[i][j]==tab_meta[i][j]){
					correctas+=1;
				}else{
					sumaDist += distManhattan(prox_mejor[i][j],i,j,tab_meta);
				}
			}
		}
		if(correctas==9){
			end=true;
		}
		ady = buscaAdyacentes(prox_mejor);
		//System.out.println("Posiciones correctas: "+correctas+" Suma Manhattan: "+sumaDist+" Numeros adyacentes: "+ady);
		//Normalizo las funciones en el rango entre 0 y 100 a excelción de h4, que su objetivo es tener valores bajos (<6)
		float h1=(correctas*100f)/(dims*dims);
		float h2=(dims*dims*dims*1f)/sumaDist;
		float h3=(ady*1f/((dims-1)*dims*2))*100;
		float h4 = preferenciaEsq(prox_mejor);
		int valorFinal = (int) ((h1/3)+(h2*2.6)+(h3/3));
		//System.out.println("Asignado: "+valorFinal+" h1: "+(h1/2.5)+"%, h2: "+(h2*2.6)+"% h3: "+(h3/3)+"%");
		return valorFinal;
	}

	private int distManhattan(int num, int iAct, int jAct, int[][] posCorrecta) {
		int iCorr=0, jCorr=0, sumaDist=0;
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				if(num==posCorrecta[i][j]){
					iCorr = i;
					jCorr = j;
					i = dims;
					j = dims;
				}
			}
		}
		if(iAct<iCorr){
			sumaDist += iCorr-iAct;
		}else{
			sumaDist += iAct-iCorr;
		}
		if(jAct<jCorr){
			sumaDist += jCorr-jAct;
		}else{
			sumaDist += jAct-jCorr;
		}
		return sumaDist;
	}

	private int buscaAdyacentes(int[][] mat){
		int num, suma=0;
		for(int i=0; i<dims; i++){
			for(int j=1; j<dims; j++){
				num = mat[i][(j-1)]+1;
				if(num == mat[i][j]){
					suma++;
				}
			}
		}
		for(int j=0; j<dims; j++){
			for(int i=1; i<dims; i++){
				num = mat[(i-1)][j]+dims;
				if(num == mat[i][j]){
					suma++;
				}
			}
		}
		return suma;
	}

	private int preferenciaEsq(int[][] mat){
		// Asigna un valor más alto a movimientos que aserquen la pieza vacía a
		// la posición deseada, con esto se evita el empate entre movimientos
		int cont=0, x_coord1=0, x_coord2=0, y_coord1=0, y_coord2=0;
		for(int i=0; i<dims; i++){
			for(int j=0; j<dims; j++){
				if(mat[i][j]==0){
					x_coord1=j;
					y_coord1=i;
					//System.out.print("\n pos["+y_coord1+"]["+x_coord1+"]");
				}
				if(tab_meta[i][j]==0){
					x_coord2=j;
					y_coord2=i;
				}
			}
		}
		if(x_coord1==x_coord2){
			cont+=3;
		}else{
			if(x_coord1==(x_coord2-1)){
				cont+=2;
			}
			if(x_coord1==(x_coord2-2)){
				cont+=1;
			}
		}
		if(y_coord1==y_coord2){
			cont+=3;
		}else{
			if(y_coord1==(y_coord2-1)){
				cont+=2;
			}
			if(y_coord1==(y_coord2-2)){
				cont+=1;
			}
		}
		//System.out.print("pos ["+y_coord1+"]["+x_coord1+"]: "+cont+"\n");
		return cont;
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