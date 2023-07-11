import java.sql.*;
import java.util.Scanner;


public class mi_crud {

	public static void main(String[] args) {
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/listado_db";
		String USER = "root";
		String PASS = "";

		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String query = "CREATE TABLE IF NOT EXISTS usuarios (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(200), email VARCHAR(200), PRIMARY KEY (id))";
			stmt.executeUpdate(query);
			
			String choice = "";
			
	
				
			

			try (Scanner scan = new Scanner(System.in)) {

				System.out.println("1. Agregar usuario");
				System.out.println("2. Recuperar usuario por id");
				System.out.println("3. Mostrar todos los usuarios");
				System.out.println("4. Editar datos de un usuario");
				System.out.println("5. Eliminr usuario");
				

				System.out.print("Eliga su opción: ");
				choice = scan.nextLine();

				switch (choice) {

				case "1":

					// Agregar usuario

					System.out.print("Ingtrese el nombre de usuario: ");
					String name = scan.nextLine();

					System.out.print("Ingtrese el correo electrónico: ");
					String email = scan.nextLine();

					query = "INSERT INTO usuarios (name, email) VALUES ('" + name + "','" + email + "')";

					int resultado = stmt.executeUpdate(query);

					if (resultado > 0) {
						System.out.print("Usuario agregado!");
					}

					break;

				case "2":

					// Recuperar usuario por id

					System.out.print("Ingrese el id del usuario: ");
					int id = scan.nextInt();

					query = "SELECT * FROM usuarios WHERE id = " + id;
					ResultSet rs = stmt.executeQuery(query);

					if (rs.next()) {
						System.out.println("ID: " + rs.getInt("id"));
						System.out.println("Nombre: " + rs.getString("name"));
						System.out.println("Correo: " + rs.getString("email"));
					} else {
						System.out.println("Usuario no encontrado");
					}

					break;
					
				case "3":
					
					// Mostrar todos los usuarios

					query = "SELECT * FROM usuarios ";
					ResultSet rs1 = stmt.executeQuery(query);
					
					while(rs1.next()) {
						String id_usuario = rs1.getString(1) ;
						String nombre_usuario = rs1.getString(2) ;
						String correo_usuario = rs1.getString(3) ;
						
						System.out.print(id_usuario +" ");
						System.out.print(nombre_usuario +" ");
						System.out.print(correo_usuario);
						System.out.println("");
					}
					
					break;

				case "4":

					// Editar datos de usuarios
					
					System.out.print("Ingrese el ID del usuario: ");
					id = scan.nextInt();

					scan.nextLine();

					System.out.print("Ingrese el nuevo nombre: ");
					name = scan.nextLine();

					System.out.print("Ingrese el nuevo correo: ");
					email = scan.nextLine();

					query = "UPDATE usuarios SET name = '" + name + "', email = '" + email + "' WHERE id = " + id;

					int result = stmt.executeUpdate(query);

					if (result > 0) {
						System.out.println("Actualizado!");
					} else {
						System.out.println("Usuario no encontrado!");
					}

					break;

				case "5":

					// Eliminar un usuario

					System.out.print("Ingrese el ID del usuario a eliminar: ");
					id = scan.nextInt();

					query = "DELETE FROM usuarios WHERE id = " + id;

					result = stmt.executeUpdate(query);

					if (result > 0) {
						System.out.println("Usuario eliminado!");
					} else {
						System.out.println("Usuario no encontrado!");
					}

					break;
				}
			}

			stmt.close();
			conn.close();
			

		} catch (Exception e) {
			System.out.print("Error: " + e.getMessage());
		}
		

	}

}
