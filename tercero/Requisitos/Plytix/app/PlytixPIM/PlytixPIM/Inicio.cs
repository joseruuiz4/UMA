using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Imaging;
using System.Drawing.Text;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PlytixPIM
{
    public partial class Inicio : Form
    {
        private Image originalImage;


        public Inicio()
        {

            InitializeComponent();
            originalImage = pictureBox1.Image;


        }

        private void bSeeProducts_Click(object sender, EventArgs e)
        {
            Productos productos = new Productos();

            productos.Show();

            this.Hide();
        }

        private void bSeeCategories_Click(object sender, EventArgs e)
        {
            Categorias categorias = new Categorias();

            categorias.Show();

            this.Hide();
        }

        private void Inicio_Load(object sender, EventArgs e)
        {
           

        }

        private void Inicio_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void bSeeAtributes_Click(object sender, EventArgs e)
        {
            Atributos atributos = new Atributos();

            atributos.Show();

            this.Hide();
        }

        private void bSeeRelationships_Click(object sender, EventArgs e)
        {
            
            Relacion relacion = new Relacion();

            relacion.Show();

            this.Hide();
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            Cuenta cuenta= new Cuenta();
            cuenta.Show();

            this.Hide();
        }

        private void pictureBox1_MouseEnter(object sender, EventArgs e)
        {

            pictureBox1.Image = pictureBox1.ErrorImage;

        }

        private void pictureBox1_MouseLeave(object sender, EventArgs e)
        {
            pictureBox1.Image = originalImage;
            
        }



        

        
    }
}
