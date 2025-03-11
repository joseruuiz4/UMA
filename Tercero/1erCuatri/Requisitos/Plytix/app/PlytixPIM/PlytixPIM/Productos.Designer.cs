namespace PlytixPIM
{
    partial class Productos
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.bBack = new System.Windows.Forms.Button();
            this.labelProducts = new System.Windows.Forms.Label();
            this.tablaProductos = new System.Windows.Forms.DataGridView();
            this.bAddProduct = new System.Windows.Forms.Button();
            this.bEditProduct = new System.Windows.Forms.Button();
            this.bDeleteProducts = new System.Windows.Forms.Button();
            this.bCsv = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.tablaProductos)).BeginInit();
            this.SuspendLayout();
            // 
            // bBack
            // 
            this.bBack.Location = new System.Drawing.Point(24, 53);
            this.bBack.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.bBack.Name = "bBack";
            this.bBack.Size = new System.Drawing.Size(56, 19);
            this.bBack.TabIndex = 0;
            this.bBack.Text = "<- BACK";
            this.bBack.UseVisualStyleBackColor = true;
            this.bBack.Click += new System.EventHandler(this.bBack_Click);
            // 
            // labelProducts
            // 
            this.labelProducts.AutoSize = true;
            this.labelProducts.Font = new System.Drawing.Font("Arial", 48F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelProducts.Location = new System.Drawing.Point(124, 26);
            this.labelProducts.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelProducts.Name = "labelProducts";
            this.labelProducts.Size = new System.Drawing.Size(595, 75);
            this.labelProducts.TabIndex = 2;
            this.labelProducts.Text = "YOUR PRODUCTS";
            // 
            // tablaProductos
            // 
            this.tablaProductos.AllowUserToAddRows = false;
            this.tablaProductos.AllowUserToDeleteRows = false;
            this.tablaProductos.ColumnHeadersHeight = 29;
            this.tablaProductos.Location = new System.Drawing.Point(205, 150);
            this.tablaProductos.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.tablaProductos.Name = "tablaProductos";
            this.tablaProductos.ReadOnly = true;
            this.tablaProductos.RowHeadersWidth = 51;
            this.tablaProductos.RowTemplate.Height = 75;
            this.tablaProductos.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.tablaProductos.Size = new System.Drawing.Size(732, 335);
            this.tablaProductos.TabIndex = 3;
            this.tablaProductos.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            this.tablaProductos.DataError += new System.Windows.Forms.DataGridViewDataErrorEventHandler(this.tablaProductos_DataError);
            // 
            // bAddProduct
            // 
            this.bAddProduct.Location = new System.Drawing.Point(735, 559);
            this.bAddProduct.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.bAddProduct.Name = "bAddProduct";
            this.bAddProduct.Size = new System.Drawing.Size(101, 43);
            this.bAddProduct.TabIndex = 4;
            this.bAddProduct.Text = "Add Product";
            this.bAddProduct.UseVisualStyleBackColor = true;
            this.bAddProduct.Click += new System.EventHandler(this.bAddProduct_Click);
            // 
            // bEditProduct
            // 
            this.bEditProduct.Location = new System.Drawing.Point(862, 559);
            this.bEditProduct.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.bEditProduct.Name = "bEditProduct";
            this.bEditProduct.Size = new System.Drawing.Size(101, 43);
            this.bEditProduct.TabIndex = 5;
            this.bEditProduct.Text = "Edit Product";
            this.bEditProduct.UseVisualStyleBackColor = true;
            this.bEditProduct.Click += new System.EventHandler(this.bEditProduct_Click);
            // 
            // bDeleteProducts
            // 
            this.bDeleteProducts.Location = new System.Drawing.Point(991, 559);
            this.bDeleteProducts.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.bDeleteProducts.Name = "bDeleteProducts";
            this.bDeleteProducts.Size = new System.Drawing.Size(101, 43);
            this.bDeleteProducts.TabIndex = 6;
            this.bDeleteProducts.Text = "Delete Products";
            this.bDeleteProducts.UseVisualStyleBackColor = true;
            this.bDeleteProducts.Click += new System.EventHandler(this.bDeleteProducts_Click);
            // 
            // bCsv
            // 
            this.bCsv.Location = new System.Drawing.Point(602, 559);
            this.bCsv.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.bCsv.Name = "bCsv";
            this.bCsv.Size = new System.Drawing.Size(101, 43);
            this.bCsv.TabIndex = 7;
            this.bCsv.Text = "Generate CSV";
            this.bCsv.UseVisualStyleBackColor = true;
            this.bCsv.Click += new System.EventHandler(this.bCsv_Click);
            // 
            // Productos
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1112, 612);
            this.Controls.Add(this.bCsv);
            this.Controls.Add(this.bDeleteProducts);
            this.Controls.Add(this.bEditProduct);
            this.Controls.Add(this.bAddProduct);
            this.Controls.Add(this.tablaProductos);
            this.Controls.Add(this.labelProducts);
            this.Controls.Add(this.bBack);
            this.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.Name = "Productos";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Productos";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Productos_FormClosing);
            this.Load += new System.EventHandler(this.Productos_Load);
            ((System.ComponentModel.ISupportInitialize)(this.tablaProductos)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button bBack;
        private System.Windows.Forms.Label labelProducts;
        private System.Windows.Forms.DataGridView tablaProductos;
        private System.Windows.Forms.Button bAddProduct;
        private System.Windows.Forms.Button bEditProduct;
        private System.Windows.Forms.Button bDeleteProducts;
        private System.Windows.Forms.Button bCsv;
    }
}