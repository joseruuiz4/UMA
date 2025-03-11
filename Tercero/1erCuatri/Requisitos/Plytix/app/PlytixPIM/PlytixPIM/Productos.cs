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
    public partial class Productos : Form
    {

        
        
        public Productos()
        {
            InitializeComponent();
        }  
            

        private void bBack_Click(object sender, EventArgs e)
        {
            Inicio inicio = new Inicio();

            inicio.Show();

            this.Hide();
        }

        private void Productos_Load(object sender, EventArgs e)
        {
          
            Consulta consulta = new Consulta();
            DataTable res = consulta.Select("SELECT Nombre FROM Atributo ORDER BY fecha_creacion");

            String atributo1 = null;
            String atributo2 = null;
            String atributo3 = null;


            int numAtributos = res.Rows.Count;
            if (numAtributos >= 3)
            {

                atributo1 = res.Rows[0]["Nombre"].ToString();
                atributo2 = res.Rows[1]["Nombre"].ToString();
                atributo3 = res.Rows[2]["Nombre"].ToString();
                Consulta consulta1 = new Consulta();
                var productos = consulta1.Select(
                    "SELECT p.thumbnail as thumbnail," +
                    "       p.sku AS sku, " +
                    "       p.label AS Label, " +
                    "       MAX(CASE WHEN a.Nombre = '" + atributo1 + "' THEN va.valor END) AS " + atributo1 + ", " +
                    "       MAX(CASE WHEN a.Nombre = '" + atributo2 + "' THEN va.valor END) AS " + atributo2 + ", " +
                    "       MAX(CASE WHEN a.Nombre = '" + atributo3 + "' THEN va.valor END) AS " + atributo3 + " " +
                    "FROM Producto p " +
                    "LEFT JOIN ValorAtributo va ON p.sku = va.producto_sku " +
                    "LEFT JOIN Atributo a ON va.atributo_nombre = a.nombre " +
                    "GROUP BY p.thumbnail, p.sku, p.label;"
                );
                tablaProductos.DataSource = productos;

            }
            else if(numAtributos == 2)
            {
                atributo1 = res.Rows[0]["Nombre"].ToString();
                atributo2 = res.Rows[1]["Nombre"].ToString();

                Consulta consulta4 = new Consulta();
                var productos2 = consulta4.Select(
                    "SELECT p.thumbnail as thumbnail," +
                    "       p.sku AS sku, " +
                    "       p.label AS Label, " +
                    "       MAX(CASE WHEN a.Nombre = '" + atributo1 + "' THEN va.valor END) AS " + atributo1 + ", " +
                    "       MAX(CASE WHEN a.Nombre = '" + atributo2 + "' THEN va.valor END) AS " + atributo2 + " " +
                    "FROM Producto p " +
                    "LEFT JOIN ValorAtributo va ON p.sku = va.producto_sku " +
                    "LEFT JOIN Atributo a ON va.atributo_nombre = a.nombre " +
                    "GROUP BY p.thumbnail, p.sku, p.label;"
                );
                tablaProductos.DataSource = productos2;

            }
            else if(numAtributos == 1)
            {
                atributo1 = res.Rows[0]["Nombre"].ToString();
                Consulta consulta5 = new Consulta();
                var productos3 = consulta5.Select(
                    "SELECT p.thumbnail as thumbnail," +
                    "       p.sku AS sku, " +
                    "       p.label AS Label, " +
                    "       MAX(CASE WHEN a.Nombre = '" + atributo1 + "' THEN va.valor END) AS " + atributo1 + " " +
                    "FROM Producto p " +
                    "LEFT JOIN ValorAtributo va ON p.sku = va.producto_sku " +
                    "LEFT JOIN Atributo a ON va.atributo_nombre = a.nombre " +
                    "GROUP BY p.thumbnail, p.sku, p.label;"
                );
                tablaProductos.DataSource = productos3;

            }
            tablaProductos.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            tablaProductos.ClearSelection();

            
        
            /*Consulta consulta = new Consulta();
            var productos = consulta.Select("SELECT thumbnail AS 'Thumbnail'," +
                "sku AS 'SKU'," +
                "label AS 'Label'," +
                "categoria_nombre AS 'Category' " +
                "FROM Producto");
            tablaProductos.DataSource = productos;
            tablaProductos.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;


            tablaProductos.RowTemplate.Height = 120;
            DataGridViewImageColumn imgCol = new DataGridViewImageColumn();
            imgCol = (DataGridViewImageColumn)tablaProductos.Columns[0];
            imgCol.ImageLayout = DataGridViewImageCellLayout.Zoom;*/
            



        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void Productos_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void bAddProduct_Click(object sender, EventArgs e)
        {
            CrearProducto crearProducto = new CrearProducto();

            crearProducto.Show();
            this.Hide();
        }

        private void bDeleteProducts_Click(object sender, EventArgs e)
        {
            if (tablaProductos.SelectedRows.Count > 0) {
                


                int skuBorrar = int.Parse(tablaProductos.SelectedRows[0].Cells["SKU"].Value.ToString());

                Consulta consultaRelacion = new Consulta();
                DataTable relaciones = consultaRelacion.Select("SELECT COUNT(*) AS Cantidad FROM Relacion WHERE producto = " + skuBorrar);

                int cantidadRelaciones = int.Parse(relaciones.Rows[0]["Cantidad"].ToString());
                if (cantidadRelaciones > 0)
                {

                    DialogResult resultado = MessageBox.Show(
                            "This product has an associated relationship.\n Are you sure to DELETE it?", // Mensaje
                            "Confirmation",                                                           // Título de la ventana
                            MessageBoxButtons.YesNo,                                                  // Botones disponibles
                            MessageBoxIcon.Question                                                   // Icono que se muestra
                            );

                    if (resultado != DialogResult.Yes)
                    {
                        // Si el usuario selecciona "No", cancelar la operación
                        return;
                    }
                }

                Consulta c1 = new Consulta();
                c1.Delete("DELETE FROM ValorAtributo WHERE producto_sku=" + skuBorrar);

                Consulta c2 = new Consulta();
                c2.Delete("DELETE FROM ProductoCategoria WHERE producto = " + skuBorrar);

                Consulta c3 = new Consulta();
                c3.Delete("DELETE FROM Producto WHERE sku=" + skuBorrar);
               
                this.Productos_Load(sender, e);
            }


            

        }

        private void bEditProduct_Click(object sender, EventArgs e)
        {

            if (tablaProductos.SelectedRows.Count > 0) {

                int sku = int.Parse(tablaProductos.SelectedRows[0].Cells["SKU"].Value.ToString());

                EditarProducto editarProducto = new EditarProducto(sku);

                editarProducto.Show();

                this.Hide();
            }
        }

        private void tablaProductos_DataError(object sender, DataGridViewDataErrorEventArgs e)
        {
            
        }

        private void bCsv_Click(object sender, EventArgs e)
        {
            Csv csv = new Csv();

            csv.Show();


            this.Hide();
        }
    }
}
