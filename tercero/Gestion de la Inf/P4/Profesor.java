import java.util.*;

public class Profesor extends Miembros_UMA
{

	private Departamento departamento;
	BD miBD = new BD(BD_SERVER,BD_NAME);			


	public Profesor(String nif)
    {
		// Crea el objeto cargando sus valores de la base de datos
		super(nif);
		
		List<Object[]> resultados = miBD.Select("SELECT * FROM PROFESOR WHERE NIF = '"+ super.getNif()+ "';");
				if(!resultados.isEmpty()) {
				Object[] tupla = resultados.get(0);
				this.departamento = (Departamento) tupla[1];
				
		}
    }


	public Profesor(String nif, String Nombre, String Apellido, int edad, Departamento departamento)
    {
		super(nif, Nombre, Apellido, edad);
		// Crea el objeto y lo inserta en la base de datos
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Insert("INSERT INTO PROFESOR VALUES("
				+ "'" + nif + "', "
				+ departamento + ");");
		this.departamento = departamento;
    }

        
	public Departamento getDepartamento() 
	{
		return departamento;
	}

	public void setDepartamento(Departamento departamento) 
	{
		// Actualiza el atributo en memoria y en la base de datos
		miBD.Update("UPDATE PROFESOR SET DEPARTAMENTO = '" + departamento 
				+ "' WHERE NIF = '" + super.getNif() + "';");
		this.departamento = departamento;
	}

	public void Borrar()
	{
    			// Borra el objeto de la base de datos e inutiliza sus atributos
		miBD.Delete("DELETE * FROM PROFESOR WHERE NIF = '" + super.getNif() + "';");
		this.departamento = null;	
	}


}