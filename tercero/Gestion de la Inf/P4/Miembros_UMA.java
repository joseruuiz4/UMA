import java.util.*;

public class Miembros_UMA
{
	protected static String BD_SERVER = "localhost";
	protected static String BD_NAME = "UMA";
    
	private String nif;
	private String Nombre;
	private String Apellidos;
	private int Edad;
	
	public static List<Miembros_UMA> ListaMiembros_UMA()
	{
		List<Miembros_UMA> lista = new ArrayList<Miembros_UMA>();
		BD miBD = new BD(BD_SERVER,BD_NAME);			
        for( Object[] tupla : miBD.Select("SELECT NIF FROM MIEMBROS_UMA;"))
        {
        	String nif = (String)tupla[0];
        	lista.add(new Miembros_UMA(nif));
        }
        return lista;

	}
	
	public Miembros_UMA(String nif)
	{
		// Crea el objeto cargando sus valores de la base de datos
		BD miBD = new BD(BD_SERVER,BD_NAME);			
        Object[] tupla = miBD.Select("SELECT * FROM MIEMBROS_UMA WHERE NIF='"+nif+"';").get(0);

		this.nif = (String)tupla[0];
		this.Nombre = (String)tupla[1];
		this.Apellidos = (String)tupla[2];
		this.Edad = (int)tupla[3];
	}
	
	public Miembros_UMA(String nif, String Nombre, String Apellido, int edad)
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Insert("INSERT INTO MIEMBROS_UMA VALUES("
				+ "'" + nif + "',"
				+ "'" + Nombre + "',"
				+ "'" + Apellido + "',"
				+ edad + ");");
		this.nif = nif;
		this.Nombre=Nombre;
		this.Apellidos=Apellido;
		this.Edad=edad;	
	}

	public String getNif() 
	{
		return nif;
	}

	public void setNif(String nif) 
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE MIEMBROS_UMA SET NIF = '" + nif + "'"
				+ " WHERE NIF = '" + this.nif + "';");
		this.nif = nif;
	}

	public String getNombre() 
	{
		return Nombre;
	}

	public void setNombre(String nombre) 
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE MIEMBROS_UMA SET NOMBRE = '" + nombre + "'"
				+ " WHERE NIF = '" + this.nif + "';");
		this.Nombre = nombre;
	}

	public String getApellidos() 
	{
		return Apellidos;
	}

	public void setApellidos(String apellidos) 
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE MIEMBROS_UMA SET APELLIDOS = '" + apellidos + "'"
				+ " WHERE NIF = '" + this.nif + "';");
		this.Apellidos = apellidos;
	}

	public int getEdad() 
	{
		return Edad;
	}

	public void setEdad(int edad) 
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE MIEMBROS_UMA SET EDAD = " + edad 
				+ " WHERE NIF = '" + this.nif + "';");
		this.Edad = edad;
	}
	
	public void Borrar()
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Delete("DELETE FROM MIEMBROS_UMA WHERE NIF = '" + this.nif + "';");
		this.nif = null;
		this.Nombre=null;
		this.Apellidos=null;
		this.Edad=-1;	
	}
	

	@Override
	public int hashCode() 
	{
		return  ((nif == null) ? 0 : nif.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Miembros_UMA other = (Miembros_UMA) obj;
		return  (nif == other.nif) ||  (nif.equals(other.nif));
	}
	
	public String toString()
	{
		return getNombre()  + " " + getApellidos();
	}

}