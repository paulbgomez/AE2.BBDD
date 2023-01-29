package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCoche;
import modelo.persistencia.DaoCocheMySql;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            printMenu();
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            exit = handleOption(option);
        }
        
        scanner.close();
	}
	
	// Add un coche al stock
    private static void addCoche() {
        Scanner scanner = new Scanner(System.in);
        Coche coche = new Coche();
        System.out.print("Introduce la matrícula del coche: ");
        coche.setMatricula(scanner.next());
        System.out.print("Introduce la marca del coche: ");
        coche.setMarca(scanner.next());
        System.out.print("Introduce el modelo del coche: ");
        coche.setModelo(scanner.next());
        System.out.print("Introduce el color del coche: ");
        coche.setColor(scanner.next());
        
        DaoCoche dCoche = new DaoCocheMySql();
        boolean alta = dCoche.alta(coche);
        if (alta) {
        	System.out.println("Coche anhadido");
        } else {
        	System.out.println("Coche NO anhadido");
        }
    }

    

    // lista toda la tabla coches
    private static void listarCoches(Connection con) {
    	try {
    		PreparedStatement sentencia = con.prepareStatement("SELECT * FROM COCHES");
    		ResultSet rs = sentencia.executeQuery();
    		while (rs.next()) {//preguntamos si hay mas filas
				System.out.print(rs.getInt("ID"));//DAME EL VALOR DE LA COLUMNA ID
				System.out.print(" - "); 
				System.out.print(rs.getString("MATRICULA"));
				System.out.print(" - "); 
				System.out.print(rs.getString("MODELO"));
				System.out.print(" - ");
				System.out.print(rs.getString("COLOR"));
				System.out.print(" - ");
				System.out.print(rs.getString("MARCA"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Error al realizar el listado de productos");
			System.out.println(e.getMessage());
		}		
    }
    
    // imprimimos el menu
	private static void printMenu() {
		System.out.println("###########################################################################");
        System.out.println("#                                                                         #");
        System.out.println("#  > 1. Añadir nuevo coche                                                #");
        System.out.println("#  > 2. Borrar coche por id                                               #");
        System.out.println("#  > 3. Consulta coche por id                                             #");
        System.out.println("#  > 4. Modificar coche por id                                            #");
        System.out.println("#  > 5. Listado de coches                                                 #");
        System.out.println("#  > 6. Gestion de pasajeros                                              #");
        System.out.println("#  > 7. Terminar el programa                                              #");
        System.out.println("#                                                                         #");
        System.out.println("###########################################################################");
    }
	
    // imprimimos el submenu
	private static void printSubMenu() {
		System.out.println("###########################################################################");
        System.out.println("#                                                                         #");
        System.out.println("#  > 1. Añadir nuevo pasajero                                             #");
        System.out.println("#  > 2. Borrar pasajero por id                                            #");
        System.out.println("#  > 3. Consulta pasajero por id                                          #");
        System.out.println("#  > 4. Añadir pasajero a coche                                           #");
        System.out.println("#  > 4. Elimnar pasajero de coche                                         #");
        System.out.println("#  > 5. Listar pasajero de un coche                                       #");
        System.out.println("#  > 6. Terminar el programa                                              #");
        System.out.println("#                                                                         #");
        System.out.println("###########################################################################");
    }
	
    // switch para el menu
    public static boolean handleOption(int option) {
        switch (option) {
            case 1:
            	addCoche();
                break;
            case 2:
//            	deleteById(coches);
                break;
            case 3:
//            	busquedaId(coches);
                break;
            case 4:
            	// Modificar coche
//            	listarCoches(coches);
                break;
            case 5:
//            	listarCoches(coches);
                break;
            case 6:
            	printSubMenu();
                break;
                // terminar programa
            case 7:
//            	llenarAlmacen(coches);
            	break;
            default:
                System.out.println("Opción no válida.");
        }
        return false;
    }

}
