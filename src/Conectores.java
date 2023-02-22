import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conectores {
	public static final String DRIVER_SQLITE = "org.sqlite.JDBC";
	public static final String DRIVER_APACHE = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String CONECTOR_SQLITE = "jdbc:sqlite:src/db/hospital";
	public static final String CONECTOR_APACHE = "jdbc:derby:src/db/consultorio.Apache";
	
	public static void main(String[] argvs) {
		pediatras();
		//recientes();
	}
	public static void pediatras() {
		try {
			// Cargar el driver
			Class.forName(DRIVER_SQLITE);

			// Establecer la conexión con la BD
			Connection conexion = DriverManager.getConnection(CONECTOR_SQLITE);

			// Preparar la consulta
			Statement sentencia = conexion.createStatement();
			ResultSet respuesta = sentencia.executeQuery("SELECT * FROM doctores where Especialidad ='pediatra'");

			// Recorremos la respuesta para visualizar cada fila
			while (respuesta.next()) {
				System.out.println(respuesta.getString("Nombre"));
			}

			respuesta.close();
			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException cnfE) {
			cnfE.printStackTrace();
		}  catch (SQLException sqlE) {
			System.err.println("\nHubo problemas con la base de datos:");
			System.err.println("Mensaje:\t"+sqlE.getMessage());
			System.err.println("Estado SQL:\t"+sqlE.getSQLState());
			System.err.println("Codigo Error:\t"+sqlE.getErrorCode());
		}
	}
	
	public static void recientes() {
		try {
			// Cargar el driver
			Class.forName(DRIVER_APACHE);
			// Establecer la conexión con la BD
			Connection conexion = DriverManager.getConnection(CONECTOR_APACHE);

			// Preparar la consulta
			Statement sentencia = conexion.createStatement();
			//ResultSet respuesta = sentencia.executeQuery("SELECT * FROM doctores where Especialidad ='pediatra'");
			ResultSet respuesta = sentencia.executeQuery("SELECT p.Nombre FROM pacientes p JOIN visitas v ON p.Nif = v.Nif_paciente WHERE v.Fecha > '2015-06-01'");

			// Recorremos la respuesta para visualizar cada fila
			while (respuesta.next()) {
				System.out.println(respuesta.getString("Nombre"));
			}

			respuesta.close();
			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException cnfE) {
			cnfE.printStackTrace();
		}  catch (SQLException sqlE) {
			System.err.println("\nHubo problemas con la base de datos:");
			System.err.println("Mensaje:\t"+sqlE.getMessage());
			System.err.println("Estado SQL:\t"+sqlE.getSQLState());
			System.err.println("Codigo Error:\t"+sqlE.getErrorCode());
		}
	}
}


