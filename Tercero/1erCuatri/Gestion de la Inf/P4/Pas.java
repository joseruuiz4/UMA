import java.util.List;

public class Pas extends Miembros_UMA
{
	private int horas;
	private String puesto;
	BD miBD = new BD(BD_SERVER,BD_NAME);

        
	public Pas(String nif) 
	{
		// Crea el objeto cargando sus valores de la base de datos
		super(nif);
		List<Object[]> resultados = miBD.Select("SELECT * FROM PAS WHERE NIF = '"+ super.getNif()+ "';");
		if(!resultados.isEmpty()) {
				Object[] tupla = resultados.get(0);
				this.horas = (int) tupla[1];
				this.puesto= (String) tupla[2];
			}

	}
	
	public Pas(String nif, String Nombre, String Apellido, int edad, int horas, String puesto)
	{
		// Crea el objeto y lo inserta en la base de datos
		super(nif, Nombre, Apellido, edad);
		// Crea el objeto y lo inserta en la base de datos

		miBD.Insert("INSERT INTO PAS VALUES("
				+ "'" + nif + "',"
				+ "'" + horas + "','"
				+ puesto + "');");
		this.horas = horas;
		this.puesto=puesto;
	}

	public int getHoras() 
	{
		return horas;
	}

	public void setHoras(int horas) 
	{
		// Actualiza el atributo en memoria y en la base de datos
		miBD.Update("UPDATE PAS SET HORAS = '" + horas+ "' WHERE NIF = '"+ super.getNif()+ "' ;");
		this.horas = horas;
	}

	public String getPuesto() 
	{
		return puesto;
	}

	public void setPuesto(String puesto) 
	{
		// Actualiza el atributo en memoria y en la base de datos
		miBD.Update("UPDATE PAS SET PUESTO = " + puesto 
				+ " WHERE NIF = '" + super.getNif() + "';");
		this.puesto = puesto;
	}

	public void Borrar()
	{
    			// Borra el objeto de la base de datos e inutiliza sus atributos
		miBD.Delete("DELETE * FROM PAS WHERE NIF = '" + super.getNif() + "';");
		this.horas = -1;	
		this.puesto = null;
	}
	

}