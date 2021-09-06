/* Programa que resuelve el algoritmo 8 puzzle mediante
 * una búsqueda basado en el algoritmo de la colina 
 * tomando como criterio de evaluación una función "f"
 * previamente diseñada.
 */

public class Puzzle8 {

	public static void main(String[] args) {
		FileAccessHelper fileHelper = new FileAccessHelper(args);
		System.out.println(fileHelper.toString());
	}
	
}
