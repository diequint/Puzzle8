/* Clase encargada de abrir el archivo txt que recibe de entrada,
 * procesar sus datos y generar la salida ya sea al mismo archivo
 * o a pantalla.
 */

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileAccessHelper {
	String nomFichero="puzzle.txt", linea;
	StringTokenizer st = null;
	int dims;
	int[][] tab_inicio, tab_meta;
	File fichero;
	Scanner s = null;

	FileAccessHelper(String[] args) {
		if(args.length!=0){
			nomFichero = args[0];
		}
		fichero = new File(nomFichero);
		//System.out.println("Fichero "+nomFichero);
		
		try {
			// Se obtiene el tamaño del problema
			s = new Scanner(fichero);
			dims = Integer.valueOf(s.nextLine());

			// Se obtienen los tableros
			tab_inicio = new int[dims][dims];
			for (int i=0; i<dims; i++) {
				linea = s.nextLine();
				st = new StringTokenizer(linea, ",");
				for (int j=0; j<dims;j++) {
					tab_inicio[i][j] = Integer.valueOf(st.nextToken());
				}
			}
			tab_meta = new int[dims][dims];
			for (int i=0; i<dims; i++) {
				linea = s.nextLine();
				st = new StringTokenizer(linea, ",");
				for (int j=0; j<dims;j++) {
					tab_meta[i][j] = Integer.valueOf(st.nextToken());
				}
			}
		} catch (Exception ex) {
			//No existen suficientes lineas o hay error en el formato
			System.out.println("Error: "+ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Error: "+ex2.getMessage());
			}
		}
	}

	public int getDimensiones() {
		return dims;
	}

	public int[][] getTab_inicio() {
		return tab_inicio;
	}

	public int[][] getTab_meta() {
		return tab_meta;
	}

	@Override
	public String toString() {
		String salida="Dimensiones: "+dims+"\nTablero inicial:\n";
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				salida=salida+tab_inicio[i][j]+" ";
			}
			salida=salida+"\n";
		}
		salida=salida+"Tablero meta:";
		for(int i=0; i<3; i++){
			salida=salida+"\n";
			for(int j=0; j<3; j++){
				salida=salida+tab_meta[i][j]+" ";
			}
		}
		return salida;
	}

}
