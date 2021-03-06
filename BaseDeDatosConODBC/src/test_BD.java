import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test_BD {

	public static void main(String[] args) {
		
		try{
			//Cargar el driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:odbc:ejemploODBC");
			
			//Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia.executeQuery("select * from Departamentos");
			
			//Recorremos el resultado para visualizar cada fila
			//Se hace un bucle mientras haya registros, se van visualizando
			
			while(resul.next()){
				System.out.println(resul.getInt(1)+ " "+ resul.getString(2));
				
			}
			
			resul.close();
			sentencia.close();
			conexion.close();

		}catch (ClassNotFoundException e){e.getMessage();}
		catch (SQLException en){en.printStackTrace();}
		

	}

}
