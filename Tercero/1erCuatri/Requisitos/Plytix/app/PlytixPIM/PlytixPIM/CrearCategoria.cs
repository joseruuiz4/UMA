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


    


    public partial class CrearCategoria : Form
    {


       
        public CrearCategoria()
        {
            InitializeComponent();
            
        }

        private void labelCategories_Click(object sender, EventArgs e)
        {

        }

        private void bSubmit_Click(object sender, EventArgs e)
        {
            Consulta consulta = new Consulta();
            string nombreCategoria = textCatName.Text;
            consulta.Insert("INSERT INTO Categoria (nombre) VALUES ('" + nombreCategoria + "')");

            Categorias categorias = new Categorias();
            categorias.Show();
            this.Hide();



        }

        private void CrearCategoria_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Categorias categorias = new Categorias();
            categorias.Show();
            this.Hide();
        }
    }
}
