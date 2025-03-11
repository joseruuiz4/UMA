using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;


namespace PlytixPIM
{
    public partial class Csv : Form
    {
        public Csv()
        {
            InitializeComponent();
        }

        private void bBack_Click(object sender, EventArgs e)
        {
            Productos productos = new Productos();
            productos.Show();

            this.Hide();
        }

        private void Csv_Load(object sender, EventArgs e)
        {
            listCategorias.Items.Clear();
            Consulta consulta = new Consulta();
            List <Object[]> lista = consulta.SelectEscalar("SELECT nombre FROM Categoria");
            foreach (Object[] obj in lista)
            {
                listCategorias.Items.Add(obj[0].ToString());
            }


            Consulta consulta1 = new Consulta();
            var productos = consulta1.Select("SELECT p.thumbnail AS 'Thumbnail', " +
                "p.sku AS 'SKU', " +
                "p.label AS 'Label', " +
                "MAX(CASE WHEN pc.RowNum = 1 THEN pc.cat END) AS 'Category 1', " +
                "MAX(CASE WHEN pc.RowNum = 2 THEN pc.cat END) AS 'Category 2', " +
                "MAX(CASE WHEN pc.RowNum = 3 THEN pc.cat END) AS 'Category 3' " +
                "FROM Producto p " +
                "JOIN ( " +
                "    SELECT pc.producto as prod, c.nombre as cat, ROW_NUMBER() OVER (PARTITION BY pc.producto ORDER BY c.nombre) AS RowNum " +
                "    FROM ProductoCategoria pc " +
                "    JOIN Categoria c ON pc.categoria = c.nombre " +
                ") pc ON p.sku = pc.prod " +
                "GROUP BY p.thumbnail, p.sku, p.label;");
            tablaProductos.DataSource = productos;
            tablaProductos.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;

            /*DataGridViewImageColumn imgCol = new DataGridViewImageColumn();
            imgCol = (DataGridViewImageColumn)tablaProductos.Columns[0];
            imgCol.ImageLayout = DataGridViewImageCellLayout.Zoom;
            tablaProductos.ClearSelection();*/

            listCategorias.ClearSelected();
            tablaProductos.ClearSelection();
        }

        private void Csv_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void bClear_Click(object sender, EventArgs e)
        {
            listCategorias.ClearSelected();
            
        }

        

        private void listCategorias_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listCategorias.SelectedItem != null)
            {

                string categoriaSeleccionada = listCategorias.SelectedItem.ToString();

                // Crear la consulta SQL para filtrar los productos
                string consultaSQL = @"
                    SELECT p.thumbnail AS 'Thumbnail',
                           p.sku AS 'SKU',
                           p.label AS 'Label',
                           MAX(CASE WHEN pc.RowNum = 1 THEN pc.categoria END) AS 'Category 1',
                           MAX(CASE WHEN pc.RowNum = 2 THEN pc.categoria END) AS 'Category 2',
                           MAX(CASE WHEN pc.RowNum = 3 THEN pc.categoria END) AS 'Category 3'
                    FROM Producto p
                    JOIN (
                        SELECT pc.producto, c.nombre AS categoria, 
                               ROW_NUMBER() OVER (PARTITION BY pc.producto ORDER BY c.nombre) AS RowNum
                        FROM ProductoCategoria pc
                        JOIN Categoria c ON pc.categoria = c.nombre
                    ) pc ON p.sku = pc.producto
                    WHERE p.sku IN (
                        SELECT pc.producto
                        FROM ProductoCategoria pc
                        JOIN Categoria c ON pc.categoria = c.nombre
                        WHERE c.nombre = '" + categoriaSeleccionada + @"'
                    )
                    GROUP BY p.thumbnail, p.sku, p.label;
                ";

                // Ejecutar la consulta y actualizar el DataGridView
                Consulta consulta = new Consulta();
                var productosFiltrados = consulta.Select(consultaSQL);

                tablaProductos.DataSource = productosFiltrados;
                tablaProductos.ClearSelection();

                DataGridViewImageColumn imgCol = new DataGridViewImageColumn();
                imgCol = (DataGridViewImageColumn)tablaProductos.Columns[0];
                imgCol.ImageLayout = DataGridViewImageCellLayout.Zoom;
            }
            else
            {
                this.Csv_Load(sender, e);
            }
        }

        private void bGenerate_Click(object sender, EventArgs e)
        {
            if (tablaProductos.Rows.Count == 0)
            {
                MessageBox.Show($"There is no products ont the list", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            try
            {
                // Ruta donde se generará el archivo CSV
                string filePath = "temp.csv";
                using (StreamWriter writer = new StreamWriter(filePath))
                {



                    // Escribir cabecera
                    writer.WriteLine("SKU,Title,FullfilledBy,Amazon_SKU,Price,OfferPrimer");

                    List<Object[]> productos = new List<Object[]>();
                    if (listCategorias.SelectedItem != null)
                    {
                        Consulta consulta1 = new Consulta();

                        productos = consulta1.SelectEscalar("SELECT p.gtin AS 'GTIN'," +
                             "p.sku AS 'SKU'," +
                             "p.label AS 'Label' " +
                             "FROM Producto p " +
                             "JOIN ProductoCategoria pc ON pc.producto = p.sku " +
                             "WHERE pc.categoria='" + listCategorias.SelectedItem.ToString() + "'");
                    }
                    else
                    {
                        Consulta consulta1 = new Consulta();
                        productos = consulta1.SelectEscalar("SELECT gtin AS 'GTIN'," +
                            "sku AS 'SKU'," +
                            "label AS 'Label' " +
                            "FROM Producto");

                    }


                    Consulta c1 = new Consulta();
                    string cuenta = c1.SelectEscalar("SELECT nombre FROM Cuenta")[0][0].ToString();


                    Consulta c2 = new Consulta();
                    DataTable resp = c2.Select("SELECT nombre FROM Atributo WHERE Tipo IN ('Real', 'Entero') ORDER BY fecha_creacion;");

                    if (resp.Rows.Count < 1)
                    {
                        MessageBox.Show($"You have no float or integer type attribute. CSV cannot be generated.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        File.Delete(filePath);
                        return;
                    }
                    string atributo = resp.Rows[0]["nombre"].ToString();


                    // Buscar el primer atributo de tipo float o int
                    foreach (Object[] producto in productos)
                    {
                        string gtin = producto[0].ToString();
                        string sku = producto[1].ToString();
                        string label = producto[2].ToString();

                        Consulta c4 = new Consulta();
                        var atributoNum = c4.Select("SELECT valor FROM ValorAtributo WHERE producto_sku = " + sku + " AND atributo_nombre = '" + atributo + "' AND valor <> ''");
                        if (atributoNum.Rows.Count == 0)
                        {
                            MessageBox.Show($"The product \"" + label + "\" has no value defined for the attribute \"" + atributo + "\". " +
                                "It will not be included in the CSV.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        }
                        else
                        {
                            string precio = atributoNum.Rows[0]["valor"].ToString();

                            // Preparar fila
                            string fila = $"{sku},{label},{cuenta},{gtin},{precio},False";
                             writer.WriteLine(fila);
                        }
                        

                        
                    }
                    writer.Close();
                }

                string finalPath = "productos.csv";
                string contenido = File.ReadAllText(filePath);
                File.WriteAllText(finalPath, contenido);
                File.Delete(filePath);

                MessageBox.Show("CSV file successfully generated.", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            catch (Exception ex)
            {
                MessageBox.Show($"An error ocurred: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
