using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Globalization;

namespace P6
{
    public partial class Form1 : Form
    {

        private P_567_2024Entities context;

        public Form1()
        {
            InitializeComponent();
            context = new P_567_2024Entities();
            Load += Form1_Load;
        }

        private void Form1_Load(object sender, EventArgs e)
        {

            var proveedores = context.Proveedor.Select(p => new { Display = "(" + p.CIF + ")" + " " + p.NOMBRE, p.CIF }).ToList();

            listaProveedores.DataSource = proveedores;
            listaProveedores.DisplayMember = "Display";
            listaProveedores.ValueMember = "CIF";


        }

        private void button6_Click(object sender, EventArgs e)
        {
            if (listaProveedores.SelectedValue != null)
            {
                string proveedorCIF = listaProveedores.SelectedValue.ToString();
                string proveedorNombre = context.Proveedor
                    .Where(p => p.CIF == proveedorCIF)
                    .Select(p => p.NOMBRE)
                    .FirstOrDefault();


                var productos = context.Producto
                    .Where(p => p.PROVEEDOR == proveedorCIF)
                    .Select(p => new
                    {
                        p.ID,
                        p.NOMBRE,
                        PROVEEDOR = "(" + proveedorCIF + ") " + proveedorNombre,
                        p.PRECIO
                    })
                    .ToList();
                listaProductos.DataSource = productos;


            }
        }

        private void salir_Click(object sender, EventArgs e)
        {
            context.Dispose();
            Close();
        }

        private void insertar_Click(object sender, EventArgs e)
        {
            if (listaProveedores.SelectedValue != null)
            {
                if (!string.IsNullOrWhiteSpace(cajaNombre.Text))
                {
                    string precioTexto = cajaPrecio.Text.Replace(',', '.');
                    float precio;
                    if (float.TryParse(precioTexto, System.Globalization.NumberStyles.Number,
                                    System.Globalization.CultureInfo.InvariantCulture, out precio))
                    {
                        var nuevoProducto = new Producto
                        {
                            NOMBRE = cajaNombre.Text,
                            PROVEEDOR = listaProveedores.SelectedValue.ToString(),
                            PRECIO = precio
                        };

                        context.Producto.Add(nuevoProducto);
                        context.SaveChanges();
                        button6_Click(sender, e);
                        cajaId.Clear();
                        cajaNombre.Clear();
                        cajaPrecio.Clear();
                    }
                    else
                    {
                        MessageBox.Show("Introduzca un precio válido");
                    }


                }
                else
                {
                    MessageBox.Show("Introduzca un nombre válido");
                }
            }
            else
            {
                MessageBox.Show("Seleccione un proveedor antes de insertar un producto");
            }
        }

        private void actualizar_Click(object sender, EventArgs e)
        {
            if (listaProveedores.SelectedItem != null)
            {
                if (listaProductos.SelectedRows.Count != 0)
                {
                    if (!string.IsNullOrWhiteSpace(cajaNombre.Text))
                    {
                        string precioTexto = cajaPrecio.Text.Replace(',', '.');
                        float precioNuevo;
                        if (float.TryParse(precioTexto, System.Globalization.NumberStyles.Number,
                            System.Globalization.CultureInfo.InvariantCulture, out precioNuevo))
                        {
                            int idProducto = int.Parse(cajaId.Text);
                            var producto = context.Producto.SingleOrDefault(p => p.ID == idProducto);
                            if (producto != null)
                            {
                                producto.NOMBRE = cajaNombre.Text;
                                producto.PRECIO = precioNuevo;
                                context.SaveChanges();

                                button6_Click(sender, e);
                                cajaId.Clear();
                                cajaNombre.Clear();
                                cajaPrecio.Clear();
                            }
                        }
                        else
                        {
                            MessageBox.Show("Introduzca un precio valido");
                        }
                    }
                    else
                    {
                        MessageBox.Show("Introduzca un nombre válido");
                    }
                }
                else
                {
                    MessageBox.Show("Seleccione un producto para modificar");
                }
            }
            else
            {
                MessageBox.Show("Seleccione un proveedor antes de modificar un producto");
            }
        }

        private void borrar_Click(object sender, EventArgs e)
        {
            if (listaProveedores.SelectedItem != null)
            {
                if (listaProductos.SelectedRows.Count != 0)
                {
                    int idProducto = int.Parse(cajaId.Text);
                    var productoEliminar = context.Producto.SingleOrDefault(p => p.ID == idProducto);
                    if (productoEliminar != null)
                    {
                        context.Producto.Remove(productoEliminar);
                        context.SaveChanges();

                        button6_Click(sender, e);
                        cajaId.Clear();
                        cajaNombre.Clear();
                        cajaPrecio.Clear();
                    }
                }
                else
                {
                    MessageBox.Show("Seleccione un prodcuto para borrar");
                }
            }
            else
            {
                MessageBox.Show("Seleccione un proveedor del que borrar un producto");
            }
        }

        private void limpiar_Click(object sender, EventArgs e)
        {
            listaProveedores.ClearSelected();
            listaProductos.DataSource = null;
            cajaId.Clear();
            cajaNombre.Clear();
            cajaPrecio.Clear();
        }

        private void listaProductos_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow filaSelecionada = listaProductos.Rows[e.RowIndex];

                cajaId.Text = filaSelecionada.Cells["ID"].Value.ToString();
                cajaNombre.Text = filaSelecionada.Cells["NOMBRE"].Value.ToString();
                cajaPrecio.Text = filaSelecionada.Cells["PRECIO"].Value.ToString();

            }
        }





        
    }
}
