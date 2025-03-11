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
    public partial class EditarProducto : Form
    {
        private List<String> categoriasIniciales;

        private int sku;
        public EditarProducto(int sku)
        {
            InitializeComponent();
            this.sku = sku;
        }

        private void labelCategories_Click(object sender, EventArgs e)
        {

        }

        private void label10_Click(object sender, EventArgs e)
        {

        }

        private void bUploadImage_Click(object sender, EventArgs e)
        {

        }

        private void imagenBox_Click(object sender, EventArgs e)
        {

        }

        private void label9_Click(object sender, EventArgs e)
        {

        }

        private void label8_Click(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void label6_Click(object sender, EventArgs e)
        {

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void label7_Click(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void bBack_Click(object sender, EventArgs e)
        {
            Productos productos = new Productos();

            productos.Show();

            this.Hide();
        }

        private void labelBox_TextChanged(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void gtinBox_TextChanged(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void bSubmit_Click(object sender, EventArgs e)
        {
            string label = labelBox.Text;
            int skuNuevo = int.Parse(skuBox.Text);
            string gtin = gtinBox.Text;
            if (gtin.Length != 14)
            {
                MessageBox.Show("El GTIN debe tener 14 dígitos.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            var categoriasNuevas = listaCategorias.CheckedItems;
            if (categoriasNuevas.Count == 0)
            {
                MessageBox.Show("Por favor, seleccione como mínimo una categoría.", "Campos vacíos", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

           if (string.IsNullOrEmpty(label) || string.IsNullOrEmpty(skuNuevo.ToString()) || string.IsNullOrEmpty(gtin))
            {
                MessageBox.Show("Por favor, rellene todos los campos antes de continuar.", "Campos vacíos", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            int aux = this.sku;

            Consulta consulta3 = new Consulta();
            consulta3.Update("UPDATE Producto SET label='" + label + "',sku=" + skuNuevo + ",gtin= " + gtin + " WHERE sku=" + aux);

            var listaGeneral = listaCategorias.Items;
            for (int i = 0; i < listaGeneral.Count; i++)
            {
                int indexInicial = -1;
                int indexNueva = -1;
                for (int j = 0; j < categoriasIniciales.Count; j++)
                    if (listaGeneral[i].ToString() == categoriasIniciales[j].ToString())
                        indexInicial = j;
                for (int j = 0; j < categoriasNuevas.Count; j++)
                    if (listaGeneral[i].ToString() == categoriasNuevas[j].ToString())
                        indexNueva = j;
                Consulta c4 = new Consulta();
                if (indexInicial < 0 && indexNueva >= 0)
                    c4.Insert("INSERT INTO ProductoCategoria VALUES (" + skuNuevo + ", '" + categoriasNuevas[indexNueva] + "');");
                else if (indexInicial >= 0 && indexNueva < 0)
                    c4.Delete("DELETE FROM ProductoCategoria " +
                        "WHERE producto = " + skuNuevo + " AND categoria = '" + categoriasIniciales[indexInicial] + "';");
            }

            Consulta c6 = new Consulta();
            int numAtributos = int.Parse(c6.SelectEscalar("SELECT COUNT(*) FROM Atributo")[0][0].ToString());

            List<TextBox> textboxes = new List<TextBox>();
            List<Label> labels = new List<Label>();

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

            
            for (int i=0; i < numAtributos; i++)
            {
                Consulta c8 = new Consulta();
                int veces = int.Parse(c8.SelectEscalar("SELECT COUNT(*) FROM ValorAtributo WHERE producto_sku=" + aux +" AND atributo_nombre = '" + labels[i].Text + "'")[0][0].ToString());
                string atributo = textboxes[i].Text;
                if (veces > 0)
                {
                    Consulta c7 = new Consulta();
                    c7.Update("UPDATE ValorAtributo SET valor='" + atributo + "', producto_sku = "+ skuNuevo + " WHERE producto_sku=" + aux + " AND atributo_nombre='" + labels[i].Text +"'");
                }
                else
                {
                    Consulta c9 = new Consulta();
                    c9.Insert("INSERT INTO ValorAtributo (producto_sku, atributo_nombre, valor) VALUES (" + skuNuevo + ", '" + labels[i].Text + "', '" + atributo + "')");
                }
            }
            Productos productos = new Productos();
            productos.Show();
            this.Hide();
        }

        private void skuBox_TextChanged(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void listaCategorias_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void EditarProducto_Load(object sender, EventArgs e)
        {
            //Cargar categorias en lista
            Consulta consulta2 = new Consulta();

            DataTable res = consulta2.Select("SELECT nombre FROM Categoria");

            List<String> list = new List<String>();

            foreach (DataRow fila in res.Rows)
            {
                list.Add(fila["nombre"].ToString());
            }

            foreach (string cat in list)
            {
                listaCategorias.Items.Add(cat);
            }

            Consulta consulta3 = new Consulta();

            DataTable res2 = consulta3.Select("SELECT gtin,label FROM Producto WHERE sku=" + this.sku + ";");
            foreach(DataRow fila in res2.Rows)
            {
                labelBox.Text = fila["label"].ToString();
                gtinBox.Text = fila["gtin"].ToString();
                skuBox.Text = sku.ToString();
                label1.Focus();
            }

            categoriasIniciales = new List<string>();
            Consulta c4 = new Consulta();
            DataTable categorias = c4.Select("SELECT categoria FROM ProductoCategoria WHERE producto = " + this.sku);
            for (int i = 0; i < listaCategorias.Items.Count; i++)
            {
                foreach (DataRow categ in categorias.Rows)
                {
                    if (listaCategorias.Items[i].ToString() == categ["categoria"].ToString())
                    {
                        listaCategorias.SetItemChecked(i, true);
                        categoriasIniciales.Add(categ["categoria"].ToString());
                    }
                }
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

            Consulta c5 = new Consulta();
            DataTable sol = c5.Select("SELECT a.nombre FROM Atributo a JOIN ValorAtributo v ON a.nombre=v.atributo_nombre WHERE v.producto_sku="+ this.sku +" ORDER BY a.fecha_creacion");
           

            for (int i = 0; i < 5; i++)
            {
                if (i >= numAtributos)
                {
                    labels[i].Hide();
                    textboxes[i].Hide();
                }
                else
                {
                    String nombre = sol.Rows[i]["Nombre"].ToString();
                    labels[i].Text = nombre;
                    Consulta c7 = new Consulta();
                    DataTable valores = c7.Select("SELECT valor FROM ValorAtributo WHERE atributo_nombre = '" + nombre + "' AND producto_sku = "+ this.sku);
                    
                    if (valores.Rows.Count > 0)
                    {
                        String valor = valores.Rows[0]["valor"].ToString();
                        textboxes[i].Text = valor;
                    }else
                    {
                        textboxes[i].Text = "";
                    }
                }
            }
        }

        private void EditarProducto_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }
    }
}
