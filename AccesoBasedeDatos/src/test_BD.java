
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test_BD {

	public static void main(String[] args) {
		
		int numeroEmpleado=2;
		String nombre = "Juan";
		String oficio = "Programador";
		int director = 1;
		int numeroDepartamento = 2;
		int comision= 100;
		int salario= 1000;
		
		try{
			//Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			//Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo","root","admin");
			
			//Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			ResultSet resul = sentencia.executeQuery("select count(*) from empleados where numEmple="+numeroEmpleado+";");
			
			resul.first();
			if(resul.getInt(1)>0){
				System.out.println("No se puede insertar El numero de empleado ya existe");
				
			}else{
				resul=sentencia.executeQuery("select count(*) from departamentos where id="+numeroDepartamento+";");
				resul.first();
				if (resul.getInt(1)<=0){
					System.out.println("No se puede Insertar, el Departamento no existe");
					
				}else{
					if(salario<=0){
						System.out.println("El salario debe ser positivo");
					}else{
						 resul = sentencia.executeQuery("select count(*) from empleados where numEmple="+director+";");
						 resul.first();
						 if(resul.getInt(1)<=0){
							 System.out.println("El director no existe");
						 }else{
							 sentencia.execute("insert into empleados values ("+numeroEmpleado+",'"+nombre+"','"+oficio+"',"+director+","+salario+","+comision+","+numeroDepartamento+");");
						 }
					}
				}
			}
			
			
			resul.close();
			sentencia.close();
			conexion.close();

		}catch (ClassNotFoundException e){e.getMessage();}
		catch (SQLException en){en.printStackTrace();}
		
	}
}
