
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//Ver como hacerlo con una base de datos nueva

public class Test_Mysql {

	public static void main(String[] args) {
		try {  
		     Class.forName("com.mysql.jdbc.Driver");  
		   } catch (ClassNotFoundException e1) {  
		 e1.printStackTrace();  
		   } 
		  
		   try {  
		Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "admin");  
		     
		DatabaseMetaData dbmd = conexion.getMetaData(); //Creamos objeto
		ResultSet resul = null;
		
		String nombre = dbmd.getDatabaseProductName();
		String driver = dbmd.getDriverName();
		String url = dbmd.getURL();
		String usuario = dbmd.getUserName();
		
		System.out.println("INFROMACIÓN SOBRE LA BASE DE DATOS:");
		System.out.println("===================================");
		System.out.println("Nombre 	: "+ nombre);
		System.out.println("Driver 	: "+ driver);
		System.out.println("URL 	: "+ url);
		System.out.println("Usuario : "+ usuario);
		
		//Obtener información de las tablas y vistas que hay
		resul = dbmd.getTables(null, null, null, new String []{"TABLE"});
		
		while(resul.next()){
			String catalogo = resul.getString(1); 	//columna 1 que devuelve ResulSet
			String esquema = resul.getString(2);	//columna 2
			String tabla = resul.getString(3);		//columna 3
			String tipo = resul.getString(4);		//columna 4
			System.out.println(tipo + " - Catalogo: "+ catalogo + ", Esquema : "+esquema+", Nombre: "+tabla);
		}
		Statement sentencia=conexion.createStatement();
		ResultSet rs=sentencia.executeQuery("select * from departamentos");
		ResultSetMetaData rsmd=rs.getMetaData();				
		int nColum=rsmd.getColumnCount();
		String nula;
		System.out.println("Numero de columnas recuperadas: "+ nColum);
		for (int i = 1; i <=nColum; i++) {
			System.out.println("Columna "+i+":");
			System.out.println(" Nombre : "+rsmd.getColumnName(i));
			System.out.println(" Tipo : "+rsmd.getColumnType(i));
			if (rsmd.isNullable(1)==0) {
				nula="NO";
			}
			else nula="SI";
			System.out.println(" Puede ser nula?: "+nula);
			System.out.println(" Maximo ancho de la columna: " +rsmd.getColumnDisplaySize(i));
		}
		
		conexion.close(); //cerrar conexion
	} 
	
	catch (SQLException e) {e.printStackTrace();}
	}  

	

}
