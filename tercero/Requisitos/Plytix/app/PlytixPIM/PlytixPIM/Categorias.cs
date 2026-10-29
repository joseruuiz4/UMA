using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PlytixPIM
{
    public partial class Categorias : Form
    {

        
        
        public Categorias()
        {
            InitializeComponent();
            
        }

        private void Categorias_Load(object sender, EventArgs e)
        {
            Consulta consulta = new Consulta();
            string query = @"
            SELECT Categoria.nombre AS 'Category', COUNT(ProductoCategoria.producto) AS 'Number of products'
            FROM Categoria
            LEFT JOIN ProductoCategoria ON Categoria.nombre = ProductoCategoria.categoria
            GROUP BY Categoria.nombre
            ORDER BY Categoria.fecha_creacion;";

            DataTable res = consulta.Select(query);
            tablaCategorias.DataSource = res;
            tablaCategorias.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            tablaCategorias.ClearSelection();
        }

        private void Categorias_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void bBack_Click(object sender, EventArgs e)
        {
            Inicio incio = new Inicio();
            incio.Show();
            this.Hide();
        }

        private void bAddCategory_Click(object sender, EventArgs e)
        {
            
            if(tablaCategorias.Rows.Count < 3)
            {
                CrearCategoria crearCategoria = new CrearCategoria();
                crearCategoria.Show();
                this.Hide();
            }
            else
            {
                MessageBox.Show("You cannot add more than 3 categories with the free plan");
            }
        }

        private void bDeleteCategory_Click(object sender, EventArgs e)
        {
            if (tablaCategorias.SelectedRows.Count > 0)
            {
                string nombre = tablaCategorias.SelectedRows[0].Cells[0].Value.ToString(); //nombre categoria
                
                if (int.Parse(tablaCategorias.SelectedRows[0].Cells[1].Value.ToString()) > 0)
                {

                        DialogResult resultado = MessageBox.Show(
                        "This category has associated products.\n Are you sure to DELETE it?", // Mensaje
                        "Confirmation",                                                           // Título de la ventana
                        MessageBoxButtons.YesNo,                                                  // Botones disponibles
                        MessageBoxIcon.Question                                                   // Icono que se muestra
                        );

                    if (resultado == DialogResult.Yes)
                    {
                        Consulta consulta1 = new Consulta();
                        consulta1.Update("DELETE FROM ProductoCategoria WHERE categoria = '" + nombre + "'");

                        Consulta consulta = new Consulta();
                        consulta.Delete("DELETE FROM Categoria WHERE nombre='" + nombre + "'");

                        this.Categorias_Load(sender, e);
                    }
                    else
                    {
                        return;
                    }
                }
                else
                {
                    Consulta c2 = new Consulta();
                    c2.Delete("DELETE FROM Categoria WHERE nombre='" + nombre + "'");
                    this.Categorias_Load(sender, e);
                }
            }
        }
    }
}
