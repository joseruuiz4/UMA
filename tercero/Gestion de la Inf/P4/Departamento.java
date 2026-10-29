import java.util.ArrayList;
import java.util.List;

public class  Departamento
{
    private static String BD_SERVER = "localhost";
    private static String BD_NAME = "UMA";
    
	private String codigo;
	private String nombre;
	
	public static List<Departamento> ListaDepartamentos()
	{
		List<Departamento> lista = new ArrayList<Departamento>();
		BD miBD = new BD(BD_SERVER,BD_NAME);			
        for( Object[] tupla : miBD.Select("SELECT CODIGO FROM DEPARTAMENTO;"))
        {
        	String codigo = (String)tupla[0];
        	lista.add(new Departamento(codigo));
        }
        return lista;

	}
	public Departamento(String codigo)
	{
		// Crea el objeto cargando sus valores de la base de datos
		BD miBD = new BD(BD_SERVER,BD_NAME);			
        Object[] tupla = miBD.Select("SELECT * FROM DEPARTAMENTO WHERE CODIGO='"+codigo+"';").get(0);
		this.codigo = (String)tupla[0];
		this.nombre = (String)tupla[1];
	}
	
	public Departamento(String codigo, String nombre)
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Insert("INSERT INTO DEPARTAMENTO VALUES('" + codigo + "', '" + nombre + "');");
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public String getCodigo() 
	{
		return codigo;
	}
	public void setCodigo(String codigo) 
	{
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	miBD.Update("UPDATE DEPARTAMENTO SET CODIGO = '" +  codigo	+ "' WHERE CODIGO='"+codigo+"';");
		this.codigo = codigo;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	miBD.Update("UPDATE DEPARTAMENTO SET NOMBRE = '" +  nombre	+ "' WHERE CODIGO='"+codigo+"';");
		this.nombre = nombre;
	}
	
	public void Borrar()
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);			
        miBD.Delete("DELETE FROM DEPARTAMENTO WHERE CODIGO='"+codigo+"';");
		this.codigo = null;
		this.nombre = null;
	}
	
	@Override
	public int hashCode() 
	{
		return  ((codigo == null) ? 0 : codigo.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Departamento other = (Departamento) obj;
		return  (codigo == other.codigo) ||  (codigo.equals(other.codigo));
	}

	
}
