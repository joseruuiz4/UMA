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
    public partial class Relacion : Form
    {
        public Relacion()
        {
            InitializeComponent();
        }

        private void Relacion_Load(object sender, EventArgs e)
        {
            Consulta consulta = new Consulta();
            string query = @"
            SELECT nombre as 'Name', Producto as 'Product', ProductoRelacionado as 'Related Product'
            FROM Relacion
            ORDER BY fecha_creacion;";

            DataTable res = consulta.Select(query);

            // Por cada SKU en la tabla de relaciones, se busca el nombre del producto y el nombre del producto relacionado

            tablaRelaciones.DataSource = res;
            tablaRelaciones.ClearSelection();
        }

        private void Relacion_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void bBack_Click(object sender, EventArgs e)
        {
            Inicio inicio = new Inicio();
            inicio.Show();
            this.Hide();
        }

        private void bAddRelation_Click(object sender, EventArgs e)
        {
            CrearRelacion crearRelacion = new CrearRelacion();
            crearRelacion.Show();
            this.Hide();
        }

    }
}
