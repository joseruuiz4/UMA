using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PlytixPIM
{
    public partial class CrearProducto : Form
    {
        public CrearProducto()
        {
            InitializeComponent();
        }

        private void CrearProducto_Load(object sender, EventArgs e)
        {
            Consulta consulta2 = new Consulta();

            DataTable res = consulta2.Select("SELECT nombre FROM Categoria");

            List<String> list = new List<String>();

            foreach (DataRow fila in res.Rows)
            {
                list.Add(fila["nombre"].ToString());
            }
            
            foreach(string cat in list)
            {
                listaCategorias.Items.Add(cat);
            }

            Consulta c6 = new Consulta();
            int numAtributos = int.Parse(c6.SelectEscalar("SELECT COUNT(*) FROM Atributo")[0][0].ToString());

            List<Label> labels = new List<Label>();
            List<TextBox> textboxes = new List<TextBox>();

            labels.Add(labela1);
            labels.Add(labela2);
            labels.Add(labela3);
            labels.Add(labela4);
            labels.Add(labela5);

            textboxes.Add(texta1);
            textboxes.Add(texta2);
            textboxes.Add(texta3);
            textboxes.Add(texta4);
            textboxes.Add(texta5);

            for(int i = 0; i<5; i++)
            {
                if (i >= numAtributos)
                {
                    labels[i].Hide();
                    textboxes[i].Hide();
                }
                else
                {
                    Consulta c9 = new Consulta();
                    labels[i].Text = c9.SelectEscalar("SELECT nombre FROM Atributo ORDER BY fecha_creacion")[i][0].ToString();
                }
            }

        }


        private byte[] GetImageBytes()
        {
            using (MemoryStream ms = new MemoryStream())
            {
                imagenBox.Image.Save(ms, imagenBox.Image.RawFormat);
                return ms.ToArray();
            }
        }




        private void bSubmit_Click(object sender, EventArgs e)
        {
            
            string label = labelBox.Text;
            string sku = skuBox.Text;
            string gtin = gtinBox.Text;
            if (gtin.Length != 14)
            {
                MessageBox.Show("The GTIN must have 14 digits", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            
                
            if (listaCategorias.CheckedItems.Count == 0)
            {
                MessageBox.Show("Please, select at least ONE category.", "Empty fields", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }

            //byte[] img = GetImageBytes();

            // falta meter el thumbnail tambien

            else if (string.IsNullOrEmpty(label) || string.IsNullOrEmpty(sku) || string.IsNullOrEmpty(gtin))
            {
                // Mostrar mensaje de error
                MessageBox.Show("Please, fill in all the fields before continue.", "Empty fields", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
            else
            {
                Consulta consulta = new Consulta();
                consulta.Insert("INSERT INTO Producto (label, sku, gtin) VALUES ('" + label + "', '" + sku + "', '" + gtin + "')");

                foreach (var categoria in listaCategorias.CheckedItems)
                {
                    Consulta c4 = new Consulta();
                    c4.Insert("INSERT INTO ProductoCategoria VALUES (" + sku + ", '" + categoria.ToString() + "');");
                }

                Consulta c2 = new Consulta();
                List<Object[]> lista = c2.SelectEscalar("SELECT nombre FROM Atributo ORDER BY fecha_creacion");
                List<String> atributos = new List<String>();

                List<TextBox> textboxes = new List<TextBox>();
                textboxes.Add(texta1);
                textboxes.Add(texta2);
                textboxes.Add(texta3);
                textboxes.Add(texta4);
                textboxes.Add(texta5);

                foreach (Object[] array in lista)
                {
                    atributos.Add(array[0].ToString());
                }
            
                for(int i = 0; i < atributos.Count; i++)
                {
                    
                    Consulta c3 = new Consulta();
                    string valor = textboxes[i].Text;
                    c3.Insert("INSERT INTO ValorAtributo (producto_sku, atributo_nombre, valor) VALUES ('" + sku + "', '" + atributos[i] + "', '" + valor + "')");
                }
                Productos productos = new Productos();
                productos.Show();
                this.Hide();
            }
        }

        private void bBack_Click_1(object sender, EventArgs e)
        {
            Productos productos = new Productos();

            productos.Show();

            this.Hide();
        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void bUploadImage_Click(object sender, EventArgs e)
        {
            OpenFileDialog opf = new OpenFileDialog();

            opf.Filter = "Choose Image(*.JPG;*.PNG;*.JPEG)|*.jpg;*.png;*.jpeg";

            if(opf.ShowDialog() == DialogResult.OK)
            {
                imagenBox.Image = Image.FromFile(opf.FileName);
            }
        }

        private void CrearProducto_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void label10_Click(object sender, EventArgs e)
        {

        }
    }
}