using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace P5
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            foreach (Proveedor p in Proveedor.ListaProveedores())
            {
                proveedoresLista.Items.Add(p);
            }

        }



        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

  

        private void label2_Click_1(object sender, EventArgs e)
        {

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            productosTabla.DataSource = null;

            cajaID.Text = "";
            cajaNombre.Text = "";
            cajaPrecio.Text = "";

            proveedoresLista.ClearSelected();

        }

 

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void label8_Click(object sender, EventArgs e)
        {

        }

        private void label6_Click(object sender, EventArgs e)
        {

        }

        private void Seleccionar_Click(object sender, EventArgs e)
        {
            Proveedor p = (Proveedor)proveedoresLista.SelectedItem;

            List<Producto> lista = Producto.ListaProductos(p);

            productosTabla.DataSource = lista;
            productosTabla.Refresh();
        }



        private void productosTabla_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow filaSelecionada = productosTabla.Rows[e.RowIndex];

                cajaID.Text = filaSelecionada.Cells["ID"].Value.ToString();
                cajaNombre.Text = filaSelecionada.Cells["NOMBRE"].Value.ToString();
                cajaPrecio.Text = filaSelecionada.Cells["PRECIO"].Value.ToString();

            }
        }

        private void bActualizar_Click(object sender, EventArgs e)
        {
            string nombre = cajaNombre.Text;
            Proveedor provSeleccionado = (Proveedor)proveedoresLista.SelectedItem;
            float precio;

            if (!float.TryParse(cajaPrecio.Text, out precio))
            {
                MessageBox.Show("Ingrese un precio válido");
                return;
            }

            if (cajaID.Text.Equals(""))
            {
                MessageBox.Show("Seleccione un producto");
                return;
            }

            Producto productoSeleccionado = new Producto(int.Parse(cajaID.Text));




            if (!nombre.Equals(productoSeleccionado.NOMBRE))
            {
                productoSeleccionado.NOMBRE = nombre;
            }

            if (!precio.Equals(productoSeleccionado.PRECIO))
            {
                productoSeleccionado.PRECIO = precio;
            }

            List<Producto> nuevosProductos = Producto.ListaProductos(provSeleccionado);
            productosTabla.DataSource = nuevosProductos;
            productosTabla.Refresh();
            cajaID.Text = "";
            cajaNombre.Text = "";
            cajaPrecio.Text = "";
        }

        private void bInsertar_Click(object sender, EventArgs e)
        {
            string nombre = cajaNombre.Text;
            float precio;

            if (!float.TryParse(cajaPrecio.Text, out precio))
            {
                MessageBox.Show("Ingrese un precio válido");
                return;
            }

            Proveedor prov = (Proveedor)proveedoresLista.SelectedItem;
            if (prov == null)
            {
                MessageBox.Show("Ingrese un proveedor válido");
            }

            Producto prod = new Producto(nombre, prov, precio);

            List<Producto> listanueva = Producto.ListaProductos(prov);
            productosTabla.DataSource = listanueva;

            productosTabla.Refresh();
            cajaID.Text = "";
            cajaNombre.Text = "";
            cajaPrecio.Text = "";
        }

        private void bBorrar_Click(object sender, EventArgs e)
        {
            Proveedor pr = (Proveedor)proveedoresLista.SelectedItem;
            if (pr == null)
            {
                MessageBox.Show("Seleccione un proveedor");
                return;
            }

            if (cajaID.Text.Equals(""))
            {
                MessageBox.Show("Seleccione el producto que quiere borrar");
                return;
            }

            Producto productoBorrar = new Producto(int.Parse(cajaID.Text));
            productoBorrar.Borrar();

            List<Producto> listaActualizada = Producto.ListaProductos(pr);
            productosTabla.DataSource = listaActualizada;
            productosTabla.Refresh();

            cajaID.Text = "";
            cajaNombre.Text = "";
            cajaPrecio.Text = "";
        }

        private void Salir_Click(object sender, EventArgs e)
        {
            this.Close();
        }



        private void cajaID_TextChanged(object sender, EventArgs e)
        {

        }

        private void cajaPrecio_TextChanged(object sender, EventArgs e)
        {

        }

        private void cajaNombre_TextChanged(object sender, EventArgs e)
        {

        }

  

    }
}
