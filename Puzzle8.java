/* Programa que resuelve el algoritmo 8 puzzle mediante
 * una búsqueda basado en el algoritmo de la colina 
 * tomando como criterio de evaluación una función "f"
 * previamente diseñada.
 */

public class Puzzle8 {

	public static void main(String[] args) {
		FileAccessHelper fileHelper = new FileAccessHelper(args);
		System.out.println(fileHelper.toString());
		int dims = fileHelper.getDimensiones();
		int[][] tab_inicio = fileHelper.getTab_inicio();
		int[][] tab_meta = fileHelper.getTab_meta();
		HillClimbing colina = new HillClimbing(dims, tab_inicio, tab_meta);
		char mov=colina.nuevaIteracion();	//Primera iteración
        String movimientos="";
        if(!((mov == 'N') || (mov == 'E'))){
            movimientos=movimientos+mov;
        }
        mov=colina.nuevaIteracion();
		//La primera iteración está separada porque al primero no se le
		//coloca la coma antes de la letra como al resto
        while(!((mov == 'N') || (mov == 'E'))){
            movimientos=movimientos+","+mov;
            mov=colina.nuevaIteracion();
        }
		if(mov == 'N'){
			System.out.println("\nNo se encontro solucion. Movimientos:");
		}else{
			System.out.println("\nSe encontro esta solucion:");
		}
        System.out.println(movimientos);
	}
	
}
