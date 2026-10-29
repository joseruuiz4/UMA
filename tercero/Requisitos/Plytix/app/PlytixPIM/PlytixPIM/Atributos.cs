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
    public partial class Atributos : Form
    {


        
        public Atributos()
        {
            InitializeComponent();
            
        }

        private void Atributos_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void bBack_Click(object sender, EventArgs e)
        {
            Inicio inicio = new Inicio();

            inicio.Show();

            this.Hide();
        }

        private void Atributos_Load(object sender, EventArgs e)
        {
            Consulta consulta = new Consulta(); 
            
            var atributos = consulta.Select("SELECT nombre as ATTRIBUTE, tipo AS TYPE FROM Atributo ORDER BY fecha_creacion");
            tablaAtributos.DataSource = atributos;
            tablaAtributos.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            tablaAtributos.ClearSelection();
        }

        private void bAddAttribute_Click(object sender, EventArgs e)
        {
            
            if (tablaAtributos.Rows.Count < 5)
            {
                CrearAtributo crearAtributo = new CrearAtributo();

                crearAtributo.Show();

                this.Hide();
            }
            else
            {
                MessageBox.Show("You cannot add more than 5 attributes with the free plan");
            }
        }

        private void bDeleteAttribute_Click(object sender, EventArgs e)
        {
            if(tablaAtributos.SelectedRows.Count > 0)
            {
                

                string name = (tablaAtributos.SelectedRows[0].Cells["ATTRIBUTE"].Value.ToString());
                
                Consulta c3 = new Consulta();
                int numeroProductos = int.Parse(c3.SelectEscalar("SELECT COUNT(*) FROM ValorAtributo WHERE atributo_nombre='" + name + "' AND valor <> ''")[0][0].ToString());

                if(numeroProductos > 0)
                {


                    DialogResult resultado = MessageBox.Show(
                        "This attribute has associated products.\n Are you sure to DELETE it?", // Mensaje
                        "Confirmation",         // Título de la ventana
                        MessageBoxButtons.YesNo, // Botones disponibles
                        MessageBoxIcon.Question // Icono que se muestra
                        );

                    if (resultado == DialogResult.Yes)
                    {
                        Consulta consulta = new Consulta();
                        consulta.Delete("DELETE FROM ValorAtributo WHERE atributo_nombre='" + name + "'");
                        Consulta consulta2 = new Consulta();
                        consulta2.Delete("DELETE FROM Atributo WHERE nombre='" + name + "'");
                    }
                    else
                    {
                        return;
                    }
                }
                else
                {
                    Consulta consulta4 = new Consulta();
                    consulta4.Delete("DELETE FROM Atributo WHERE nombre='" + name + "'");
                }


                

                

                this.Atributos_Load(sender, e);
            }
        }

        private void bEditAttribute_Click(object sender, EventArgs e)
        {
            if (tablaAtributos.SelectedRows.Count > 0)
            {
                
                string name = tablaAtributos.SelectedRows[0].Cells["ATTRIBUTE"].Value.ToString();
                string type = tablaAtributos.SelectedRows[0].Cells["TYPE"].Value.ToString();


                EditarAtributo editarAtributo = new EditarAtributo(name,type);

                editarAtributo.Show();

                this.Hide();
            }
        }
    }
}
