import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Gestion {
	public static final String DRIVER_SQLITE = "org.sqlite.JDBC";
	public static final String CONECTOR_SQLITE = "jdbc:sqlite:src/datos/hospital";
	
    public static void doctores() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName(DRIVER_SQLITE);
            c = DriverManager.getConnection(CONECTOR_SQLITE);
            c.setAutoCommit(false);
            System.out.println("Conexion hecha con exito ");
            System.out.println("");
            System.out.println( "Datos de todos los doctores \n");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM doctores;" );
            while ( rs.next() ) {
            	//Nif, Nombre, Edad, Especialidad
            	String nif = rs.getString("nif");
                String nombre = rs.getString("nombre");
                String especialidad = rs.getString("especialidad");
                Integer edad = rs.getInt("edad");
                
               
                System.out.println( "Nif = " + nif);
                System.out.println( "Nombre = " + nombre );
                System.out.println( "Especialidad = " + especialidad );
                System.out.println( "Edad = " + edad );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Todo listo jefe la consulta y los datos listos y mostrados");
    }
    
    
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    
    public static void pacientes(){// Muestra todos los datos de todos los pacientes
    	Connection c = null;
        Statement stmt = null;
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        try {
            Class.forName(DRIVER_SQLITE);
            c = DriverManager.getConnection(CONECTOR_SQLITE);
            c.setAutoCommit(false);
            System.out.println("Conexion hecha con exito ");
            System.out.println("");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM pacientes;" );
            while ( rs.next() ) {
            	//Nif, Nombre, Tfno
            	String nif = rs.getString("nif");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("tfno");
                
                System.out.println( "Datos de todos los pacientes \n");
                System.out.println( "Nif = " + nif);
                System.out.println( "Nombre = " + nombre );
                System.out.println( "Telefono = " + telefono );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Todo listo jefe la consulta y los datos listos y mostrados");
    }
    
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    public static void visitas(){//: Muestra todos los datos de todas las visitas.
    	Connection c = null;
        Statement stmt = null;
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        try {
            Class.forName(DRIVER_SQLITE);
            c = DriverManager.getConnection(CONECTOR_SQLITE);
            c.setAutoCommit(false);
            System.out.println("Conexion hecha con exito ");
            System.out.println("");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM visitas;" );
            while ( rs.next() ) {
            	//Nif, Nombre, Tfno
            	String nif_doctor = rs.getString("nif_doctor");
                String nif_paciente = rs.getString("nif_paciente");
                String fecha = rs.getString("fecha");
                
                System.out.println( "Datos de las visitas \n");
                System.out.println( "Nif_doctor = " + nif_doctor);
                System.out.println( "Nif_paciente = " + nif_paciente );
                System.out.println( "Fecha = " + fecha );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Todo listo jefe la consulta y los datos listos y mostrados");
    }

  //*******************************************************************************************
  //*******************************************************************************************
  //*******************************************************************************************
  //*******************************************************************************************
  //*******************************************************************************************
  //*******************************************************************************************
    
      // Método para conectar a la base de datos
      public static Connection connect() {
        Connection conn = null;
        try {
        	
          // Ruta de la base de datos
          String url = CONECTOR_SQLITE;
          // Conectarse a la base de datos
          conn = DriverManager.getConnection(url);
          System.out.println("Conexión a SQLite establecida.");
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
        return conn;
      }

      // Método para insertar un nuevo registro en la tabla Doctores
      public static void nuevoDoctor(String nif, String nombre, int edad, String especialidad) {
        String sql = "INSERT INTO doctores(nif, nombre, edad, especialidad) VALUES(?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, nif);
          pstmt.setString(2, nombre);
          pstmt.setInt(3, edad);
          pstmt.setString(4, especialidad);
          pstmt.executeUpdate();
          System.out.println("Alta realizada con exito");
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
      }

      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
    
   // Método para insertar un nuevo registro en la tabla Doctores
      public static void nuevoPaciente(String nif, String nombre, String tfno) {
        String sql = "INSERT INTO pacientes(nif, nombre, tfno) VALUES(?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, nif);
          pstmt.setString(2, nombre);
          pstmt.setString(3, tfno);
          pstmt.executeUpdate();
          System.out.println("Alta realizada con exito");
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
      }
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      
      
      public static void nuevaVisita(String nif_doctor, String nif_paciente, String fecha) {//Inserta un nuevo registro en la tabla Visitas.
    	  String sql = "INSERT INTO visitas(nif_doctor, nif_paciente, fecha) VALUES(?,?,?)";
          try (Connection conn = connect();
               PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nif_doctor );
            pstmt.setString(2, nif_paciente);
            pstmt.setString(3, fecha);
            pstmt.executeUpdate();
            System.out.println("Alta realizada con exito");
          } catch (SQLException e) {
            System.out.println(e.getMessage());
          }
      }
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************  
      public static void cumpleanos(String doctor) {
    	    String query = "UPDATE doctores SET edad = edad + 1 WHERE Nif = ?";
    	    
    	    try (Connection conn = DriverManager.getConnection(CONECTOR_SQLITE);
    	         PreparedStatement stmt = conn.prepareStatement(query)) {
    	      stmt.setString(1, doctor);
    	      stmt.executeUpdate();
    	    } catch (SQLException e) {
    	      System.out.println(e.getMessage());
    	    }
      }
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************
      //*******************************************************************************************  
      
      public static void tfnoFijo() {
    	  String query = "UPDATE Pacientes SET Tfno = '950' || substr(Tfno, 4) WHERE Tfno LIKE '9__%'";
        
        try (Connection conn = DriverManager.getConnection(CONECTOR_SQLITE);
             PreparedStatement stmt = conn.prepareStatement(query)) {
          stmt.executeUpdate();
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
      }
    public static void main(String[] args) {
    	tfnoFijo();
    	//cumpleanos("99999999I");
    	//nuevaVisita("666666666F","11111111A","2010-12-12");
    	//nuevoPaciente("44444444D","Federico","644444444");
    	//nuevoDoctor("99999999I","Paco",34,"MAMOGRAFO");
    	//visitas();
    	//doctores();
    	pacientes();
    }
}
