
namespace P5
{
    partial class Form1
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.proveedoresLista = new System.Windows.Forms.ListBox();
            this.Seleccionar = new System.Windows.Forms.Button();
            this.Salir = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.limpiar = new System.Windows.Forms.Button();
            this.productosTabla = new System.Windows.Forms.DataGridView();
            this.label3 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.bActualizar = new System.Windows.Forms.Button();
            this.bInsertar = new System.Windows.Forms.Button();
            this.bBorrar = new System.Windows.Forms.Button();
            this.cajaNombre = new System.Windows.Forms.TextBox();
            this.cajaID = new System.Windows.Forms.TextBox();
            this.cajaPrecio = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.productosTabla)).BeginInit();
            this.SuspendLayout();
            // 
            // proveedoresLista
            // 
            this.proveedoresLista.FormattingEnabled = true;
            this.proveedoresLista.Location = new System.Drawing.Point(36, 88);
            this.proveedoresLista.Name = "proveedoresLista";
            this.proveedoresLista.Size = new System.Drawing.Size(246, 160);
            this.proveedoresLista.TabIndex = 1;
            this.proveedoresLista.SelectedIndexChanged += new System.EventHandler(this.listBox1_SelectedIndexChanged);
            // 
            // Seleccionar
            // 
            this.Seleccionar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Seleccionar.Location = new System.Drawing.Point(36, 272);
            this.Seleccionar.Name = "Seleccionar";
            this.Seleccionar.Size = new System.Drawing.Size(246, 39);
            this.Seleccionar.TabIndex = 3;
            this.Seleccionar.Text = "Seleccionar";
            this.Seleccionar.UseVisualStyleBackColor = true;
            this.Seleccionar.Click += new System.EventHandler(this.Seleccionar_Click);
            // 
            // Salir
            // 
            this.Salir.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Salir.Location = new System.Drawing.Point(36, 335);
            this.Salir.Name = "Salir";
            this.Salir.Size = new System.Drawing.Size(246, 238);
            this.Salir.TabIndex = 4;
            this.Salir.Text = "Salir";
            this.Salir.UseVisualStyleBackColor = true;
            this.Salir.Click += new System.EventHandler(this.Salir_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(61, 40);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(196, 37);
            this.label1.TabIndex = 6;
            this.label1.Text = "Proveedores";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(613, 40);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(162, 37);
            this.label2.TabIndex = 7;
            this.label2.Text = "Productos";
            this.label2.Click += new System.EventHandler(this.label2_Click_1);
            // 
            // limpiar
            // 
            this.limpiar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.limpiar.Location = new System.Drawing.Point(882, 531);
            this.limpiar.Name = "limpiar";
            this.limpiar.Size = new System.Drawing.Size(155, 42);
            this.limpiar.TabIndex = 9;
            this.limpiar.Text = "LIMPIAR";
            this.limpiar.UseVisualStyleBackColor = true;
            this.limpiar.Click += new System.EventHandler(this.button3_Click);
            // 
            // productosTabla
            // 
            this.productosTabla.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.productosTabla.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.productosTabla.Location = new System.Drawing.Point(351, 88);
            this.productosTabla.Name = "productosTabla";
            this.productosTabla.Size = new System.Drawing.Size(686, 203);
            this.productosTabla.TabIndex = 12;
            this.productosTabla.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.productosTabla_CellContentClick);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(346, 385);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(104, 25);
            this.label3.TabIndex = 16;
            this.label3.Text = "NOMBRE";
            this.label3.Click += new System.EventHandler(this.label3_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(346, 321);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(32, 25);
            this.label6.TabIndex = 19;
            this.label6.Text = "ID";
            this.label6.Click += new System.EventHandler(this.label6_Click);
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label8.Location = new System.Drawing.Point(346, 447);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(123, 25);
            this.label8.TabIndex = 21;
            this.label8.Text = "PRECIO (€)";
            this.label8.Click += new System.EventHandler(this.label8_Click);
            // 
            // bActualizar
            // 
            this.bActualizar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bActualizar.Location = new System.Drawing.Point(528, 531);
            this.bActualizar.Name = "bActualizar";
            this.bActualizar.Size = new System.Drawing.Size(155, 42);
            this.bActualizar.TabIndex = 22;
            this.bActualizar.Text = "ACTUALIZAR";
            this.bActualizar.UseVisualStyleBackColor = true;
            this.bActualizar.Click += new System.EventHandler(this.bActualizar_Click);
            // 
            // bInsertar
            // 
            this.bInsertar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bInsertar.Location = new System.Drawing.Point(351, 531);
            this.bInsertar.Name = "bInsertar";
            this.bInsertar.Size = new System.Drawing.Size(155, 42);
            this.bInsertar.TabIndex = 23;
            this.bInsertar.Text = "INSERTAR";
            this.bInsertar.UseVisualStyleBackColor = true;
            this.bInsertar.Click += new System.EventHandler(this.bInsertar_Click);
            // 
            // bBorrar
            // 
            this.bBorrar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bBorrar.Location = new System.Drawing.Point(708, 531);
            this.bBorrar.Name = "bBorrar";
            this.bBorrar.Size = new System.Drawing.Size(155, 42);
            this.bBorrar.TabIndex = 24;
            this.bBorrar.Text = "BORRAR";
            this.bBorrar.UseVisualStyleBackColor = true;
            this.bBorrar.Click += new System.EventHandler(this.bBorrar_Click);
            // 
            // cajaNombre
            // 
            this.cajaNombre.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cajaNombre.Location = new System.Drawing.Point(528, 385);
            this.cajaNombre.Multiline = true;
            this.cajaNombre.Name = "cajaNombre";
            this.cajaNombre.Size = new System.Drawing.Size(509, 30);
            this.cajaNombre.TabIndex = 25;
            this.cajaNombre.TextChanged += new System.EventHandler(this.cajaNombre_TextChanged);
            // 
            // cajaID
            // 
            this.cajaID.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cajaID.Location = new System.Drawing.Point(528, 321);
            this.cajaID.Multiline = true;
            this.cajaID.Name = "cajaID";
            this.cajaID.Size = new System.Drawing.Size(509, 30);
            this.cajaID.TabIndex = 26;
            this.cajaID.TextChanged += new System.EventHandler(this.cajaID_TextChanged);
            // 
            // cajaPrecio
            // 
            this.cajaPrecio.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cajaPrecio.Location = new System.Drawing.Point(528, 447);
            this.cajaPrecio.Multiline = true;
            this.cajaPrecio.Name = "cajaPrecio";
            this.cajaPrecio.Size = new System.Drawing.Size(509, 30);
            this.cajaPrecio.TabIndex = 27;
            this.cajaPrecio.TextChanged += new System.EventHandler(this.cajaPrecio_TextChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1078, 603);
            this.Controls.Add(this.cajaPrecio);
            this.Controls.Add(this.cajaID);
            this.Controls.Add(this.cajaNombre);
            this.Controls.Add(this.bBorrar);
            this.Controls.Add(this.bInsertar);
            this.Controls.Add(this.bActualizar);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.productosTabla);
            this.Controls.Add(this.limpiar);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.Salir);
            this.Controls.Add(this.Seleccionar);
            this.Controls.Add(this.proveedoresLista);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.productosTabla)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox proveedoresLista;
        private System.Windows.Forms.Button Seleccionar;
        private System.Windows.Forms.Button Salir;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button limpiar;
        private System.Windows.Forms.DataGridView productosTabla;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Button bActualizar;
        private System.Windows.Forms.Button bInsertar;
        private System.Windows.Forms.Button bBorrar;
        private System.Windows.Forms.TextBox cajaNombre;
        private System.Windows.Forms.TextBox cajaID;
        private System.Windows.Forms.TextBox cajaPrecio;
    }
}

