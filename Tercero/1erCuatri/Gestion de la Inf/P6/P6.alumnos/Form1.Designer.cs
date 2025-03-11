
namespace P6
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
            this.listaProductos = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.insertar = new System.Windows.Forms.Button();
            this.actualizar = new System.Windows.Forms.Button();
            this.borrar = new System.Windows.Forms.Button();
            this.limpiar = new System.Windows.Forms.Button();
            this.salir = new System.Windows.Forms.Button();
            this.seleccionar = new System.Windows.Forms.Button();
            this.listaProveedores = new System.Windows.Forms.ListBox();
            this.cajaId = new System.Windows.Forms.TextBox();
            this.cajaNombre = new System.Windows.Forms.TextBox();
            this.cajaPrecio = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.listaProductos)).BeginInit();
            this.SuspendLayout();
            // 
            // listaProductos
            // 
            this.listaProductos.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.listaProductos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.listaProductos.Location = new System.Drawing.Point(352, 64);
            this.listaProductos.Name = "listaProductos";
            this.listaProductos.Size = new System.Drawing.Size(670, 214);
            this.listaProductos.TabIndex = 0;
            this.listaProductos.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.listaProductos_CellContentClick);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(349, 310);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(32, 25);
            this.label1.TabIndex = 1;
            this.label1.Text = "ID";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(349, 360);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(104, 25);
            this.label2.TabIndex = 2;
            this.label2.Text = "NOMBRE";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(349, 403);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(123, 25);
            this.label3.TabIndex = 3;
            this.label3.Text = "PRECIO (€)";
            // 
            // insertar
            // 
            this.insertar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.insertar.Location = new System.Drawing.Point(352, 479);
            this.insertar.Name = "insertar";
            this.insertar.Size = new System.Drawing.Size(155, 33);
            this.insertar.TabIndex = 4;
            this.insertar.Text = "INSERTAR";
            this.insertar.UseVisualStyleBackColor = true;
            this.insertar.Click += new System.EventHandler(this.insertar_Click);
            // 
            // actualizar
            // 
            this.actualizar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.actualizar.Location = new System.Drawing.Point(529, 479);
            this.actualizar.Name = "actualizar";
            this.actualizar.Size = new System.Drawing.Size(155, 33);
            this.actualizar.TabIndex = 5;
            this.actualizar.Text = "ACTUALIZAR";
            this.actualizar.UseVisualStyleBackColor = true;
            this.actualizar.Click += new System.EventHandler(this.actualizar_Click);
            // 
            // borrar
            // 
            this.borrar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.borrar.Location = new System.Drawing.Point(706, 479);
            this.borrar.Name = "borrar";
            this.borrar.Size = new System.Drawing.Size(155, 33);
            this.borrar.TabIndex = 6;
            this.borrar.Text = "BORRAR";
            this.borrar.UseVisualStyleBackColor = true;
            this.borrar.Click += new System.EventHandler(this.borrar_Click);
            // 
            // limpiar
            // 
            this.limpiar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.limpiar.Location = new System.Drawing.Point(883, 479);
            this.limpiar.Name = "limpiar";
            this.limpiar.Size = new System.Drawing.Size(155, 33);
            this.limpiar.TabIndex = 7;
            this.limpiar.Text = "LIMPIAR";
            this.limpiar.UseVisualStyleBackColor = true;
            this.limpiar.Click += new System.EventHandler(this.limpiar_Click);
            // 
            // salir
            // 
            this.salir.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.salir.Location = new System.Drawing.Point(31, 319);
            this.salir.Name = "salir";
            this.salir.Size = new System.Drawing.Size(286, 193);
            this.salir.TabIndex = 8;
            this.salir.Text = "SALIR";
            this.salir.UseVisualStyleBackColor = true;
            this.salir.Click += new System.EventHandler(this.salir_Click);
            // 
            // seleccionar
            // 
            this.seleccionar.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.seleccionar.Location = new System.Drawing.Point(31, 263);
            this.seleccionar.Name = "seleccionar";
            this.seleccionar.Size = new System.Drawing.Size(286, 39);
            this.seleccionar.TabIndex = 9;
            this.seleccionar.Text = "SELECCIONAR";
            this.seleccionar.UseVisualStyleBackColor = true;
            this.seleccionar.Click += new System.EventHandler(this.button6_Click);
            // 
            // listaProveedores
            // 
            this.listaProveedores.FormattingEnabled = true;
            this.listaProveedores.Location = new System.Drawing.Point(31, 67);
            this.listaProveedores.Name = "listaProveedores";
            this.listaProveedores.Size = new System.Drawing.Size(286, 173);
            this.listaProveedores.TabIndex = 11;
            // 
            // cajaId
            // 
            this.cajaId.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cajaId.Location = new System.Drawing.Point(552, 310);
            this.cajaId.Name = "cajaId";
            this.cajaId.Size = new System.Drawing.Size(470, 29);
            this.cajaId.TabIndex = 12;
            // 
            // cajaNombre
            // 
            this.cajaNombre.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cajaNombre.Location = new System.Drawing.Point(552, 360);
            this.cajaNombre.Name = "cajaNombre";
            this.cajaNombre.Size = new System.Drawing.Size(470, 29);
            this.cajaNombre.TabIndex = 13;
            // 
            // cajaPrecio
            // 
            this.cajaPrecio.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cajaPrecio.Location = new System.Drawing.Point(552, 403);
            this.cajaPrecio.Name = "cajaPrecio";
            this.cajaPrecio.Size = new System.Drawing.Size(470, 29);
            this.cajaPrecio.TabIndex = 14;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 26.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(33, 25);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(284, 39);
            this.label4.TabIndex = 15;
            this.label4.Text = "PROVEEDORES";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 26.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(568, 25);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(238, 39);
            this.label5.TabIndex = 16;
            this.label5.Text = "PRODUCTOS";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1054, 554);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.cajaPrecio);
            this.Controls.Add(this.cajaNombre);
            this.Controls.Add(this.cajaId);
            this.Controls.Add(this.listaProveedores);
            this.Controls.Add(this.seleccionar);
            this.Controls.Add(this.salir);
            this.Controls.Add(this.limpiar);
            this.Controls.Add(this.borrar);
            this.Controls.Add(this.actualizar);
            this.Controls.Add(this.insertar);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.listaProductos);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.listaProductos)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView listaProductos;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button insertar;
        private System.Windows.Forms.Button actualizar;
        private System.Windows.Forms.Button borrar;
        private System.Windows.Forms.Button limpiar;
        private System.Windows.Forms.Button salir;
        private System.Windows.Forms.Button seleccionar;
        private System.Windows.Forms.ListBox listaProveedores;
        private System.Windows.Forms.TextBox cajaId;
        private System.Windows.Forms.TextBox cajaNombre;
        private System.Windows.Forms.TextBox cajaPrecio;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;

    }
}

