using System;
using System.Collections.Generic;
using System.Data;
using System.Data.OleDb;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace PlytixPIM
{
    internal class Consulta

    {

        private ConexionMySQL conexionMySQL;



        public Consulta()

        {

            conexionMySQL = new ConexionMySQL();

        }

        public List<object[]> SelectEscalar(string consulta)
        {
            List<object[]> resultados = new List<object[]>();

            try
            {
                using (MySqlConnection connection = conexionMySQL.GetConnection())
                {
                    using (MySqlCommand cmd = new MySqlCommand(consulta, connection))
                    {
                        using (MySqlDataReader reader = cmd.ExecuteReader())
                        {
                            while (reader.Read())
                            {
                                object[] fila = new object[reader.FieldCount];
                                reader.GetValues(fila);
                                resultados.Add(fila);
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                MessageBox.Show($"Error en SELECT: {e.Message}");
            }

            return resultados;
        }






        public DataTable Select(string consulta)
        {


            DataTable tabla = new DataTable();

            try
            {
                using (MySqlConnection connection = conexionMySQL.GetConnection())
                {
                    using (MySqlCommand cmd = new MySqlCommand(consulta, connection))
                    {
                        using (MySqlDataAdapter adapter = new MySqlDataAdapter(cmd))
                        {
                            adapter.Fill(tabla); // Llena el DataTable con los resultados de la consulta
                        }
                    }
                }
            }
            catch (Exception e)
            {
                MessageBox.Show($"Error en SELECT: {e.Message}");
            }

            return tabla;
        }

    

        



        public void Update(string consulta)
        {
            EjecutarComando(consulta, "UPDATE");
        }

        public void Delete(string consulta)
        {
            EjecutarComando(consulta, "DELETE");
        }



        public void Insert(string consulta)
        {
            EjecutarComando(consulta, "INSERT");
        }

        private void EjecutarComando(string consulta, string tipo)
        {
            try
            {
                using (var connection = conexionMySQL.GetConnection()) // Usando la instancia
                {
                    using (var command = new MySqlCommand(consulta, connection))
                    {
                        var filasAfectadas = command.ExecuteNonQuery();
                        
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error en {tipo}: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

    }
}
