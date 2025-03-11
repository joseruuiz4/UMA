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
    public partial class CrearRelacion : Form
    {
        public CrearRelacion()
        {
            InitializeComponent();
            CrearRelacionLoad();
        }

        public void CrearRelacionLoad()
        {
            Consulta consulta = new Consulta();
            string query = "SELECT sku, label FROM Producto;";
            DataTable res = consulta.Select(query);
            foreach (DataRow row in res.Rows)
            {
                listBox1.Items.Add(new { Sku = row["sku"], Label = row["label"] });
                listBox2.Items.Add(new { Sku = row["sku"], Label = row["label"] });
            }

            listBox1.DisplayMember = "Label";
            listBox1.ValueMember = "Sku";
            listBox2.DisplayMember = "Label";
            listBox2.ValueMember = "Sku";
        }

        private void bBack_Click(object sender, EventArgs e)
        {
            Relacion relacion = new Relacion();
            relacion.Show();
            this.Hide();
        }

        private void CrearRelacionClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }


        private void bAdd_Click(object sender, EventArgs e)
        {
            String name = nombreBox.Text;
            if (string.IsNullOrEmpty(name))
            {
                MessageBox.Show("Please enter a name");
                return;
            }

            if (listBox1.SelectedItems.Count < 1)
            {
                MessageBox.Show("Please select a product");
                return;
            }

            if (listBox2.SelectedItems.Count < 1)
            {
                MessageBox.Show("Please select a related product");
                return;
            }

            String product = ((dynamic)listBox1.SelectedItem).Sku.ToString();
            String relatedProduct = ((dynamic)listBox2.SelectedItem).Sku.ToString();

            Consulta consulta = new Consulta();
            string query = $"INSERT INTO Relacion (nombre, Producto, ProductoRelacionado) VALUES ('{name}', '{product}', '{relatedProduct}');";
            consulta.Insert(query);

            //Volver a la pantalla de relaciones
            Relacion relacion = new Relacion();
            relacion.Show();
            this.Hide();

        }
    }
}
