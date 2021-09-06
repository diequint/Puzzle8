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
		char mov=colina.nuevaIteracion();
		//U-Up, D-Down, L-Left, R-Right, N-No hay solución, E-Solucion
		//TODO: Colocarlo en un ciclo while con condición != N ó E
		System.out.println("\nMejor movimiento:"+mov);
	}
	
}
