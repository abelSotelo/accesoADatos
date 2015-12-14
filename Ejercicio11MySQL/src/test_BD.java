
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test_BD {

	public static void main(String[] args) {
		
		int numeroDepartamento = 2;
		
		try{
			//Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			//Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","root","admin");
			
			PreparedStatement st= conexion.prepareStatement("select apellido,salario,oficio,departamentos.nombre,AVG(salario) AS salarioMedio,COUNT(numEmple) from empleados,departamentos where empleados.numDep = ? and empleados.numDep=departamentos.id;");
			
			st.setInt(1, numeroDepartamento);
			
			ResultSet resultado = st.executeQuery();
			while(resultado.next()){
				System.out.println(resultado.getString(1)+ " "+ resultado.getInt(2)+" "+ resultado.getString(3)+ " "+ resultado.getString(4));
				
			}
			
			resultado.close();
			st.close();
			conexion.close();

		}catch (ClassNotFoundException e){e.getMessage();}
		catch (SQLException en){en.printStackTrace();}
		
	}
}
