package lab05_métodosordenamiento_eq4;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class LAB05_MétodosOrdenamiento_EQ4 extends Component {

    static FileReader fr;
    static Scanner entrada;
    static String datVector = "";
    static int op = 0;
    static int con = 0;
    static int low = 0;
    static int high;
    static String menu = "";
    static String num = "";

    public static void main(String[] args) {
        entrada = new Scanner(System.in);
        LAB05_MétodosOrdenamiento_EQ4 obj = new LAB05_MétodosOrdenamiento_EQ4();
        ArrayList<Integer> array = new ArrayList<Integer>();
        try {
            if (obj.rutaArchivo()) {
                obj.leerArchivo();
                obj.llenarVector(array);
                int[] vector = new int[array.size()];
                for (int i = 0; i < array.size(); i++) {
                    vector[i] = array.get(i);
                }

                high = vector.length;
                do {
                    menu = "";
                    menu += "****** Menú ******\n";
                    menu += "1 ordenamiento internos\n";
                    menu += "2 ordenamientos externos \n";
                    menu += "3 Salir \n";
                    menu += "Elija una opción";
                    op = Integer.parseInt(JOptionPane.showInputDialog(menu));
                    switch (op) {
                        case 1:
                            do {
                                menu = "";
                                menu += "****** Menú ******\n";
                                menu += "1 ordenamiento burbuja\n";
                                menu += "2 ordenamiento Quicksort\n";
                                menu += "3 ordenamiento ShellSort\n";
                                menu += "4 ordenamiento Radix\n";
                                menu += "5 Regresar menu anterior \n";
                                menu += "Elija una opción";
                                op = Integer.parseInt(JOptionPane.showInputDialog(menu));
                                switch (op) {
                                    case 1:
                                        System.out.println("su vector es:");
                                        obj.imprimirVector(vector);
                                        obj.ordenamientoBurbuja(vector, high);
                                        System.out.println("");
                                        System.out.println("Se ordenara el vector...");
                                        obj.imprimirVector(vector);
                                        break;
                                    case 2:
                                        System.out.println("su vector es:");
                                        obj.imprimirVector(vector);
                                        obj.quickSort(vector, low, high - 1);
                                        System.out.println("");
                                        System.out.println("Se ordenara el vector...");
                                        obj.imprimirVector(vector);
                                        break;
                                    case 3:
                                        System.out.println("su vector es:");
                                        obj.imprimirVector(vector);
                                        obj.ordenacionShell(vector, high);
                                        System.out.println("");
                                        System.out.println("Se ordenara el vector...");
                                        obj.imprimirVector(vector);
                                        break;
                                    case 4:
                                        System.out.println("su vector es:");
                                        obj.imprimirVector(vector);
                                        obj.ordenacionRadix(vector);
                                        System.out.println("");
                                        System.out.println("Se ordenara el vector...");
                                        obj.imprimirVector(vector);
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "selecciona un numero del 1 al 5");
                                        break;
                                }
                            } while (op != 5);
                            break;
                        case 2:
                            do {
                                menu = "";
                                menu += "****** Menú ******\n";
                                menu += "1 ordenamiento por intercalacion\n";
                                menu += "2 ordenamiento por Mezcla directa\n";
                                menu += "3 ordenamiento Mezcla natural\n";
                                menu += "4 Regresar menu anterior \n";
                                menu += "Elija una opción";
                                op = Integer.parseInt(JOptionPane.showInputDialog(menu));
                                switch (op) {
                                    case 1:
                                        System.out.println("su vector es:");
                                        obj.imprimirVector(vector);
                                        obj.intercalacion(vector, high);
                                        System.out.println("");
                                        System.out.println("Se ordenara el vector...");
                                        obj.imprimirVector(vector);
                                        break;
                                    case 2:
                                        System.out.println("su vector es:");
                                        obj.imprimirVector(vector);
                                        obj.mezclaNatural(vector);
                                        System.out.println("");
                                        System.out.println("Se ordenara el vector...");
                                        obj.imprimirVector(vector);
                                        break;
                                    case 3:
                                        System.out.println("su vector es:");
                                        obj.imprimirVector(vector);
                                        obj.mezclaDirecta(vector, low, high-1);
                                        System.out.println("");
                                        System.out.println("Se ordenara el vector...");
                                        obj.imprimirVector(vector);
                                        break;
                                    default:
                                        JOptionPane.showMessageDialog(null, "selecciona un numero del 1 al 4");
                                        break;
                                }
                            } while (op != 4);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "selecciona un numero del 1 al 3");
                            break;
                    }
                } while (op != 3);
            }
        } catch (NumberFormatException e) {
            System.err.println("Alguno de los datos en el archivo no es un número, verifique sus datos");
        } catch (IndexOutOfBoundsException p) {
            System.err.println("La posición especificada no existe");
        }

    }

    public boolean rutaArchivo() {
        boolean encontrado = false;

        try {
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
            file.setFileFilter(filter);
            file.showOpenDialog(this);
            /**
             * abrimos el archivo seleccionado
             */
            File ruta = file.getSelectedFile();
            if (ruta != null) {
                fr = new FileReader(ruta);
                encontrado = true;
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.err.println("Archivo no encontrado");
        }
        return encontrado;
    }

    public void leerArchivo() {
        BufferedReader br = new BufferedReader(fr);
        String datos = null;
        try {
            datos = br.readLine();
            while (datos != null) {
                datVector += datos + "\n";
                datos = br.readLine();
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.err.println("Ha ocurrido un error");
        }
    }

    public void llenarVector(ArrayList<Integer> vector) {
        for (int i = 0; i < datVector.length(); i++) {
            int aux = datVector.charAt(i) + "".hashCode();

            if (aux != 32 && aux != 10) {
                num += datVector.charAt(i);
            } else {
                if (num.hashCode() != 0) {

                    vector.add(Integer.parseInt(num));
                }
                num = "";
            }
        }
    }

    public void imprimirVector(int vector[]) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + "  ");
        }
    }

    public void ordenamientoBurbuja(int vector[], int n) {
        int aux;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (vector[i] > vector[j]) {
                    aux = vector[i];
                    vector[i] = vector[j];
                    vector[j] = aux;
                }
            }
        }
    }

    public void quickSort(int[] v, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }
        int pivote = v[inicio];
        int izq = inicio + 1;
        int der = fin;
        while (izq <= der) {
            while (izq <= fin && v[izq] < pivote) {
                izq++;
            }
            while (der > inicio && v[der] >= pivote) {
                der--;
            }
            if (izq < der) {
                int tmp = v[izq];
                v[izq] = v[der];
                v[der] = tmp;
            }
        }
        if (der > inicio) {
            int tmp = v[inicio];
            v[inicio] = v[der];
            v[der] = tmp;
        }
        quickSort(v, inicio, der - 1);
        quickSort(v, der + 1, fin);
    }

// Algoritmo de ordenacion ShellSort
    public void ordenacionShell(int[] v, int N) {
        int incremento = N;
        do {
            incremento = incremento / 2;
            for (int k = 0; k < incremento; k++) {
                for (int i = incremento + k; i < N; i += incremento) {
                    int j = i;
                    while (j - incremento >= 0 && v[j] < v[j - incremento]) {
                        int tmp = v[j];
                        v[j] = v[j - incremento];
                        v[j - incremento] = tmp;
                        j -= incremento;
                    }
                }
            }
        } while (incremento > 1);
    }

    public void ordenacionRadix(int[] v) {
        int max = 1;     // cantidad de repeticiones
        int nbytes = 4;     // numero de bytes a desplazar
        int nColas = (int) Math.pow(2, nbytes);
        // Creación e inicialización del arreglo de colas
        Queue<Integer>[] cola = new LinkedList[nColas];
        for (int i = 0; i < nColas; i++) {
            cola[i] = new LinkedList<Integer>();
        }

        int div = 0;        // posición a comparar
        for (int i = 0; i < max; i++) {
            // parte 1: recorrer el vector  para guardar cada elemento
            // en la cola correspondiente
            for (int numero : v) {
                // buscar el mayor número del vector
                if (i == 0) {
                    if (numero > max) {
                        max = numero;
                    }
                }
                // calcular en qué cola debe ir cada número
                int numCola = (numero >> div) & 0xf;
                cola[numCola].add(numero);
            }
            div = div + nbytes;

            // parte 2: recorrer las colas en orden para poner cada
            // elemento en el vector;
            int j = 0;
            for (Queue<Integer> c : cola) {
                while (!c.isEmpty()) {
                    v[j++] = c.remove();
                }
            }
            // la primera vez se actualiza el número de veces que se
            // debe ejecutar el proceso
            if (i == 0) {
                max = (int) (Math.log(max) / Math.log(nColas)) + 1;
            }
        }
    }

    public void intercalacion(int vector[], int n) {
        int i, k, aux;
        boolean band = false;
        for (k = 1; k < n; k++) {
            aux = vector[k];
            i = k - 1;
            band = false;
            while (i >= 0 && !band) {
                if (aux < vector[i]) {
                    vector[i + 1] = vector[i];
                    i--;
                } else {
                    band = true;
                }
            }
            vector[i + 1] = aux;
        }
    }

    // Ordena usando mezcla natural
// Parametros: el array a ordenar
    public void mezclaNatural(int arr[]) {
        if (arr.length <= 1) {
            return;
        }

        int tam1 = arr.length / 2;
        int tam2 = arr.length - tam1;

        int primeraMitad[] = new int[tam1];
        int segundaMitad[] = new int[tam2];

        System.arraycopy(arr, 0, primeraMitad, 0, tam1);
        System.arraycopy(arr, tam1, segundaMitad, 0, tam2);

       mezclaNatural(primeraMitad);
        mezclaNatural(segundaMitad);

        merge(primeraMitad, segundaMitad, arr);
    }

    private static void merge(int[] fuente1, int[] fuente2, int[] dest) {
// indices de los 3 array
        int srcIndex1 = 0;
        int srcIndex2 = 0;
        int destIndex = 0;

// merge hasta que uno de los arrays fuentes este vacio
        while (srcIndex1 < fuente1.length && srcIndex2 < fuente2.length) {
            if (fuente1[srcIndex1] < fuente2[srcIndex2]) {
                dest[destIndex] = fuente1[srcIndex1];
                srcIndex1++;
            } else {
                dest[destIndex] = fuente2[srcIndex2];
                srcIndex2++;
            }
            destIndex++;
        }

        if (srcIndex1 < fuente1.length) {
            System.arraycopy(fuente1, srcIndex1, dest, destIndex,
                    fuente1.length - srcIndex1);
        } else {
            System.arraycopy(fuente2, srcIndex2, dest, destIndex,
                    fuente2.length - srcIndex2);
        }
    } // fin de merge();

     public void mezclaDirecta(int[] array, int lo, int n) {
		int low = lo;
		int high = n;
		if(low >= high) {
			return;
		}
		
		int middle = (low + high)/2;
		mezclaDirecta(array, low, middle);
		mezclaDirecta(array, middle+1, high);
		int end_low = middle;
		int start_high = middle + 1;
		while((lo <= end_low) && (start_high <= high)) {
			if(array[low] < array[start_high]) {
				low++;
			}else {
				int Temp = array[start_high];
				for(int k = start_high - 1; k >= low; k--) {
					array[k + 1] = array[k];
				}
				array[low] = Temp;
				low++;
				end_low++;
				start_high++;
			}
		}
	}

}
