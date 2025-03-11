using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Text;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PlytixPIM
{
    public partial class EditarAtributo : Form
    {

        
        private string nameOld;
        private string typeOld;
        
        
        public EditarAtributo(string nameOld,string typeOld)
        {
            InitializeComponent();
            

            
            this.nameOld = nameOld;
            this.typeOld = typeOld;
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void bBack_Click(object sender, EventArgs e)
        {
            Atributos atributos = new Atributos();

            atributos.Show();

            this.Hide();
        }

        private void EditarAtributo_Load(object sender, EventArgs e)
        {
            textName.Text = nameOld;
            desplegableTipo.Text = typeOld;
        }

        private void bSubmit_Click(object sender, EventArgs e)
        {
            if (!typeOld.Equals(desplegableTipo.SelectedItem.ToString()))
            {
                Consulta consulta = new Consulta();
                consulta.Update("UPDATE Atributo SET tipo='" + desplegableTipo.SelectedItem.ToString() + "' WHERE nombre='" + nameOld + "'");
            }


            if (!nameOld.Equals(textName.Text))
            {
                Consulta consulta = new Consulta();
                consulta.Update("UPDATE Atributo SET nombre='" + textName.Text + "' WHERE nombre='" + nameOld + "'");
            }

            

            Atributos atributos = new Atributos();
            atributos.Show();
            this.Hide();
        
        }

        private void EditarAtributo_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }
    }
}
