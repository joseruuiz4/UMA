import java.io.*;
import java.util.*;

public class principal {

	public static void main(String[] args) {
		cargaMontes();
		Consulta_A();
		Consulta_B();
		Consulta_C();

		System.out.println("Ejecución Finalizada");
	}

	public static void cargaMontes() {
		BD miBD = new BD("localhost", "rediam");
		for (Object[] tupla : miBD.Select("SELECT * FROM Provincias;")) {
			System.out.println(tupla[0].toString() + "\t" + tupla[1].toString());
		}

		try {
			String linea = "";

			BufferedReader br = new BufferedReader(new FileReader("montes.txt"));

			while ((linea = br.readLine()) != null) {
				String[] montes = linea.split(";");
				miBD.Insert("INSERT INTO Montes VALUES( " + montes[0] + ",'" + montes[1] + "','" + montes[2] + "',"
						+ montes[3] + "," + montes[4] + ");");
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void Consulta_A() {
		try {
			BD miBD = new BD("localhost", "rediam");
			List<Object[]> lista = miBD.Select(
					"SELECT P.Provincia, M.ESPACIO_NATURAL, M.SUPERFICIE FROM Montes M, Provincias P WHERE M.COD_PROVINCIA = P.CODIGO AND M.SUPERFICIE > 10000;");
			String Prov = "";
			for (int i = 0; i < lista.size(); i++) {
				if (!Prov.equals(lista.get(i)[0])) {
					Prov = (String) lista.get(i)[0];
					System.out.println("ESPACIOS NATURALES CON MÁS DE 10000m2 DE " + lista.get(i)[0]);
				}
				System.out.println("\t" + lista.get(i)[1] + " " + lista.get(i)[2]);

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void Consulta_B() {
		try{
			String CodigoSolicitado = "";
			System.out.println("\nCodigo de Provincia: ");
			Scanner scanner = new Scanner(System.in);
			CodigoSolicitado = scanner.next();
			scanner.close();
			
			BD miBD = new BD("localhost", "rediam");
			List<Object[]> lista = miBD.Select(
					"SELECT P.Provincia, M.ESPACIO_NATURAL, M.SUPERFICIE, M.NUMERO_MONTES FROM Montes M, Provincias P WHERE M.COD_PROVINCIA = P.Codigo AND P.Codigo = '" + CodigoSolicitado+ "' ;");

			String Prov = "";
			for (int i = 0; i < lista.size(); i++) {
				if (!Prov.equals(lista.get(i)[0])) {
					Prov = (String) lista.get(i)[0];
					System.out.println("ESPACIOS NATURALES DE " + lista.get(i)[0]);
				}
				System.out.println("\t" + lista.get(i)[1] + " " + lista.get(i)[2] + " " + lista.get(i)[3]);

			}
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void Consulta_C() {
		try {
			BD miBD = new BD("localhost", "rediam");
			List<Object[]> lista = miBD.Select(
					 "SELECT P.Provincia , sum(M.SUPERFICIE) as sumaSuperficies, sum(M.NUMERO_MONTES) as sumaMontes FROM Montes M, Provincias P WHERE M.COD_PROVINCIA = P.Codigo GROUP BY P.Provincia; ");
					String Prov = "";
			for (int i = 0; i < lista.size(); i++) {
				if (!Prov.equals(lista.get(i)[0])) {
					Prov = (String) lista.get(i)[0];
					System.out.println("PROVINCIA: " + lista.get(i)[0]);
				}
				System.out.println("\t" + "TOTAL SUPERFICIE: " + lista.get(i)[1] +"\n TOTAL NUMERO MONTES: " + lista.get(i)[2]);

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
