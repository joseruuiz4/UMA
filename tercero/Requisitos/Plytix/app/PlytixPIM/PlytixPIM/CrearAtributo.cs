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
    public partial class CrearAtributo : Form
    {

        

        public CrearAtributo()
        {
            InitializeComponent();
            
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void bBack_Click(object sender, EventArgs e)
        {
            Atributos atributos = new Atributos();

            atributos.Show();

            this.Hide();
        }

        private void CrearAtributo_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void bSubmit_Click(object sender, EventArgs e)
        {
            Consulta consulta = new Consulta();
            string nombre = textName.Text;
            if(textName.Text == "")
            {
                MessageBox.Show("Enter a name");
                return;
            }

            if(desplegableTipo.SelectedItem == null)
            {
                MessageBox.Show("Select a type");
                return;
            }
            string tipo = desplegableTipo.SelectedItem.ToString();
            

            consulta.Insert("INSERT INTO Atributo (nombre,tipo) VALUES ('" + nombre + "','" + tipo + "');");

            Consulta bd2 = new Consulta();

            List<Object[]> lista = bd2.SelectEscalar("SELECT sku FROM Producto");
            foreach (Object[] array in lista)
            {
                Consulta bd1 = new Consulta();
                bd1.Insert("INSERT INTO ValorAtributo (producto_sku, atributo_nombre, valor) VALUES (" + int.Parse(array[0].ToString()) + ",'" + nombre + "', '')");
            }


            Atributos atributos = new Atributos();

            atributos.Show();

            this.Hide();
        }

        private void CrearAtributo_Load(object sender, EventArgs e)
        {

        }
    }
}
