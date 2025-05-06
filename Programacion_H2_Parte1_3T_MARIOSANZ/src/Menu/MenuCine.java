package Menu;

import java.sql.*;
import java.util.Scanner;

public class MenuCine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ CINE ---");
            System.out.println("1 - Ver películas");
            System.out.println("2 - Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                mostrarPeliculas();
            } else if (opcion == 2) {
                System.out.println("Saliendo del sistema");
            } else {
                System.out.println("Opción no válida.");
            }
        } while (opcion != 2);

        scanner.close();
    }

    public static void mostrarPeliculas() {
        String url = "jdbc:mysql://localhost:3306/cine_mariosanz";
        String usuario = "root";
        String contraseña = "1234";
        
        String consulta = "SELECT p.codigo, p.titulo, p.genero, p.duracion, p.clasificacion, d.nombre AS director "
                        + "FROM peliculas p "
                        + "JOIN directores d ON p.id_director = d.id";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                int duracion = rs.getInt("duracion");
                String clasificacion = rs.getString("clasificacion");
                String director = rs.getString("director");
                
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Código: " + codigo + " | Título: " + titulo + " | Género: " + genero + " | Duración: " + duracion + " min | Clasificación: " + clasificacion + " | Director: " + director);
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
            }

            stmt.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
