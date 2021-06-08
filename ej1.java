import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ej1 {

	public static void insertar(Scanner teclado) {
		
		
		
		try {
			Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Productos","root","");
		
				PreparedStatement ps;
				
				 ps = conexion.prepareStatement("INSERT INTO Productos (id, nombre, cantidad, precio) VALUES(?, ?, ?, ?)");
				 System.out.println("Introduce el n�mero de id");
				 int id=teclado.nextInt();
				
				 System.out.println("Introduce el nombre del producto");
				 String nombre=teclado.next();
				
				 System.out.println("Introduce la cantidad de productos");
				 int cantidad=teclado.nextInt();
				 
				 System.out.println("Introduce el precio del producto");
				 double precio=teclado.nextDouble();
				
				 ps.setInt(1, id );
				 ps.setString(2, nombre);
				 ps.setInt(3, cantidad);
				 ps.setDouble(4, precio);
				
				 ps.executeUpdate();
				System.out.println("Producto insertado correctamente"+"\n");
				
				
		
		}catch(Exception e) {
			System.out.println("El Producto insertado est� repetido"+"\n");
		}
	}
	
	public static void mostrar() {
		try {
			Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Productos","root","");
		
				Statement miState=(Statement) conexion.createStatement();
				
			
				
				ResultSet resultado=miState.executeQuery("SELECT * FROM Productos");
				System.out.println("ID|Nombre|Cantidad|Precio");
				while(resultado.next()) {
					System.out.println(resultado.getString("id")+ " |"+ resultado.getString("nombre")+"|"+resultado.getString("cantidad")+"|"+resultado.getString("precio"));
				}
				System.out.println(" ");
		}catch(Exception e) {
			System.out.println("Error de conexi�n");
		}
	}
	
	public static void modificar(Scanner teclado) {
	
		try {
			int opciones1;
			do {
				System.out.println("Escoge el campo a modificar");
				System.out.println("1. Nombre del producto");
				System.out.println("2. Cantidad");
				System.out.println("3. Precio");
				System.out.println("0. Salir");
				opciones1=teclado.nextInt();
				switch(opciones1) {
				case 1:
					Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Productos","root","");
					
					PreparedStatement ps;
					ps=conexion.prepareStatement("UPDATE productos SET nombre=? WHERE id=?");
					System.out.println("Introduce nuevo nombre");
					String nombre=teclado.next();
					System.out.println("Introduce la id del producto a modificar");
					int id=teclado.nextInt();
					ps.setString(1, nombre);
					ps.setInt(2, id);
					ps.executeUpdate();
					System.out.println("Producto modificado correctamente"+"\n");
					break;
				case 2:
					Connection conexion2=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Productos","root","");
					
					
					ps=conexion2.prepareStatement("UPDATE productos SET cantidad=? WHERE id=?");
					System.out.println("Introduce nuevo cantidad");
					int cantidad=teclado.nextInt();
					System.out.println("Introduce la id del producto a modificar");
					id=teclado.nextInt();
					ps.setInt(1, cantidad);
					ps.setInt(2, id);
					ps.executeUpdate();
					System.out.println("Producto modificado correctamente"+"\n");
					break;
				case 3:
					Connection conexion3=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Productos","root","");
					
					
					ps=conexion3.prepareStatement("UPDATE productos SET precio=? WHERE id=?");
					System.out.println("Introduce nuevo precio");
					double precio=teclado.nextDouble();
					System.out.println("Introduce la id del producto a modificar");
					id=teclado.nextInt();
					ps.setDouble(1, precio);
					ps.setInt(2, id);
					ps.executeUpdate();
					System.out.println("Producto modificado correctamente"+"\n");
					break;
				}
			}while(opciones1!=0);
			
		}catch (Exception e) {
			System.out.println("Error");
		}
		
	}
	
	public static void eliminar(Scanner teclado) {
		
		try {
		Connection conexion=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Productos","root","");
		
		PreparedStatement ps;
		
		 ps = conexion.prepareStatement("DELETE FROM productos WHERE id=?");
		 System.out.println("Introduce el n�mero de id");
		 int id=teclado.nextInt();
		
		
		
		 ps.setInt(1, id );
		
		 ps.executeUpdate();
		
		System.out.println("Eliminado correctamente"+"\n");
		

		}catch(Exception e) {
			System.out.println("Error, no se pudo eliminar"+"\n");
		}
			}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado=new Scanner(System.in);
		int opciones;
		do {
			System.out.println("1. Insertar productos");
			System.out.println("2. Modificar producto");
			System.out.println("3. Eliminar producto");
			System.out.println("4. Mostrar productos");
			System.out.println("0. Salir");
			opciones =teclado.nextInt();
			
				switch(opciones) {
				case 1:
					insertar(teclado);
					break;
				case 2:
					modificar(teclado);
					break;
				case 4:
					mostrar();
					break;
				case 3:
					eliminar(teclado);
				}
		}while(opciones!=0);
		
		System.out.println("Gracias por usar la base de datos");
	}
	
}
