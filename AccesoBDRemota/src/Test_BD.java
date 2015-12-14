import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test_BD {

	public static void main(String[] args) {
		try{
			//Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			//Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://172.16.100.5:3306/abel","abel","abel9007");
			
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
