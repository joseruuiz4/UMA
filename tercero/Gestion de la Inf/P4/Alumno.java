import java.util.ArrayList;
import java.util.List;

public class Alumno extends Miembros_UMA
{
	private String titulacion;
	private int asignaturas;
	
	BD miBD = new BD(BD_SERVER,BD_NAME);			

	
	public Alumno(String nif) 
	{
		super(nif);
		List<Object[]> resultados = miBD.Select("SELECT * FROM ALUMNO WHERE NIF = '"+ super.getNif()+ "';");
		if(!resultados.isEmpty()) {
		Object[] tupla = resultados.get(0);
		this.titulacion = (String) tupla[1];
		this.asignaturas = (int) tupla[2];
		}
		}


	
	public Alumno(String nif, String Nombre, String Apellido, int edad, String titulacion, int ASIGNATURAS) 
	{
		super(nif, Nombre, Apellido, edad);
		// Crea el objeto y lo inserta en la base de datos
		miBD.Insert("INSERT INTO ALUMNO VALUES("
				+ "'" + nif + "',"
				+ "'" + titulacion + "',"
				+ ASIGNATURAS + ");");
		this.titulacion = titulacion;
		this.asignaturas=ASIGNATURAS;

	}

	
	public String getTitulacion() 
	{
		return titulacion;
	}


	public void setTitulacion(String titulacion) 
	{
		// Actualiza el atributo en memoria y en la base de datos
		miBD.Update("UPDATE ALUMNO SET TITULACION = " + titulacion 
				+ " WHERE NIF = '" + super.getNif() + "';");
		this.titulacion = titulacion;
	}


	public int getASIGNATURAS() 
	{
		return asignaturas;
	}


	public void setASIGNATURAS(int ASIGNATURAS) 
	{
		// Actualiza el atributo en memoria y en la base de datos
		miBD.Update("UPDATE ALUMNO SET ASIGNATURAS = " + ASIGNATURAS 
				+ " WHERE NIF = '" + super.getNif() + "';");
		this.asignaturas = ASIGNATURAS;
	}
	
	public void Borrar()
	{
    			// Borra el objeto de la base de datos e inutiliza sus atributos
		miBD.Delete("DELETE FROM ALUMNO WHERE NIF = '" + this.getNif() + "';");
		this.asignaturas = -1;	
		this.titulacion = null;
	}


}