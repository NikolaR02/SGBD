import java.sql.*;

public class Metadatos {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Debe proporcionar el nombre del archivo de la base de datos.");
            return;
        }

        String dbName = args[0];

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/datos/" + dbName)) {
            DatabaseMetaData meta = conn.getMetaData();

            // Obtener el número total de tablas
            ResultSet tables = meta.getTables(null, null, null, new String[]{"TABLE"});
            int numTables = 0;

            System.out.println("Tablas:");

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                int numRecords = getNumRecords(conn, tableName);
                numTables++;

                System.out.println("Tabla " + tableName + ": " + numRecords + " registros");

                // Obtener las columnas de la tabla
                ResultSet columns = meta.getColumns(null, null, tableName, null);

                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                
                    boolean isForeignKey = isForeignKey(conn, tableName, columnName);

                    System.out.println(columnName + " " + columnType +
                            
                            (isForeignKey ? " Ajena" : ""));
                }

                System.out.println();
            }

            System.out.println("Total de tablas: " + numTables);

            // Obtener los subprogramas almacenados
            boolean supportsStoredProcedures = meta.supportsStoredProcedures();
            System.out.println("Subprogramas: " + (supportsStoredProcedures ? "soportados" : "no soportados"));

            if (supportsStoredProcedures) {
                ResultSet procedures = meta.getProcedures(null, null, null);

                int numProcedures = 0;

                while (procedures.next()) {
                    String procedureName = procedures.getString("PROCEDURE_NAME");
                    String procedureType = procedures.getString("PROCEDURE_TYPE").equals("1") ?
                            "Procedimiento" : "Función";
                    numProcedures++;

                    System.out.println(procedureName + " " + procedureType);
                }

                System.out.println("Total de subprogramas: " + numProcedures);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los metadatos: " + e.getMessage());
        }
    }

    private static int getNumRecords(Connection conn, String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        stmt.close();
        return count;
    }

    private static boolean isForeignKey(Connection conn, String tableName, String columnName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet foreignKeys = meta.getImportedKeys(null, null, tableName);
        boolean isForeignKey = false;

        while (foreignKeys.next()) {
            if (columnName.equals(foreignKeys.getString("FKCOLUMN_NAME"))) {
                isForeignKey = true;
                break;
            }
        }

        foreignKeys.close();
        return isForeignKey;
    }
}








