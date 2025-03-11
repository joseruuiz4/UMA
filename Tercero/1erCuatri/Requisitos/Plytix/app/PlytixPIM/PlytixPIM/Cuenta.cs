using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Text.Json;

namespace PlytixPIM
{
    public partial class Cuenta : Form
    {
        public Cuenta()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void bExport_Click(object sender, EventArgs e)
        {
            
            Consulta consulta = new Consulta();
            DataTable datatable = consulta.Select("SELECT (SELECT COUNT(*) FROM Producto) AS 'Numero De Productos', (SELECT COUNT(*) FROM Categoria) AS 'Numero De Categorias', (SELECT COUNT(*) FROM Relacion) AS 'Numero De Relaciones', (SELECT COUNT(*) FROM Atributo) AS 'Numero De Atributos';");

            var lista = new List<Dictionary<string, object>>();




            foreach (DataRow row in datatable.Rows)
            {
                var fila = new Dictionary<string, object>();
                fila.Add("Nombre de la Cuenta", tName.Text);
                fila.Add("Fecha de Creacion", tDate.Text);
                foreach (DataColumn col in datatable.Columns)
                {
                    fila[col.ColumnName] = row[col]; // Añadir el valor de cada columna a la fila
                }
                lista.Add(fila); // Añadir la fila a la lista
            }

            string json = JsonSerializer.Serialize(lista, new JsonSerializerOptions { WriteIndented = true });
            string filePath = "productos.json";
            File.WriteAllText(filePath, json);

            Console.WriteLine($"Archivo JSON guardado en: {Path.GetFullPath(filePath)}");


        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void Cuenta_Load(object sender, EventArgs e)
        {
            Consulta c5 = new Consulta();
            tName.Text = c5.SelectEscalar("SELECT nombre FROM Cuenta")[0][0].ToString();
            Consulta c6 = new Consulta();
            tDate.Text = DateTime.Parse(c6.SelectEscalar("SELECT fechaCreacion FROM Cuenta")[0][0].ToString()).ToString("MM/dd/yyyy");

            Consulta c1 = new Consulta();

            string numProductos = c1.SelectEscalar("SELECT COUNT(*) FROM Producto")[0][0].ToString();
            tProducts.Text = numProductos;

            Consulta c2 = new Consulta();
            string numCategorias = c2.SelectEscalar("SELECT COUNT(*) FROM Categoria")[0][0].ToString();
            tCategories.Text = numCategorias;

            Consulta c3 = new Consulta();
            string numRelaciones = c3.SelectEscalar("SELECT COUNT(*) FROM Relacion")[0][0].ToString();
            tRelationships.Text = numRelaciones;

            Consulta c4 = new Consulta();
            string numAtributos = c4.SelectEscalar("SELECT COUNT(*) FROM Atributo")[0][0].ToString();
            tAttributes.Text = numAtributos;



        }

        private void Cuenta_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void bBack_Click(object sender, EventArgs e)
        {
            Inicio inicio = new Inicio();
            inicio.Show();
            this.Hide();
        }
    }
}
