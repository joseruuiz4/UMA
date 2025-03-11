namespace PlytixPIM
{
    partial class Csv
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
            this.label1 = new System.Windows.Forms.Label();
            this.bBack = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.listCategorias = new System.Windows.Forms.ListBox();
            this.tablaProductos = new System.Windows.Forms.DataGridView();
            this.bClear = new System.Windows.Forms.Button();
            this.bGenerate = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.tablaProductos)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Arial", 48F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(167, 20);
            this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(533, 75);
            this.label1.TabIndex = 4;
            this.label1.Text = "GENERATE CSV";
            // 
            // bBack
            // 
            this.bBack.Font = new System.Drawing.Font("Arial", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bBack.Location = new System.Drawing.Point(37, 46);
            this.bBack.Margin = new System.Windows.Forms.Padding(2);
            this.bBack.Name = "bBack";
            this.bBack.Size = new System.Drawing.Size(74, 24);
            this.bBack.TabIndex = 10;
            this.bBack.Text = "<- BACK";
            this.bBack.UseVisualStyleBackColor = true;
            this.bBack.Click += new System.EventHandler(this.bBack_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(121, 235);
            this.label2.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(193, 26);
            this.label2.TabIndex = 11;
            this.label2.Text = "Filter by category:";
            // 
            // listCategorias
            // 
            this.listCategorias.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.listCategorias.FormattingEnabled = true;
            this.listCategorias.ItemHeight = 25;
            this.listCategorias.Location = new System.Drawing.Point(98, 288);
            this.listCategorias.Margin = new System.Windows.Forms.Padding(2);
            this.listCategorias.Name = "listCategorias";
            this.listCategorias.Size = new System.Drawing.Size(249, 129);
            this.listCategorias.TabIndex = 12;
            this.listCategorias.SelectedIndexChanged += new System.EventHandler(this.listCategorias_SelectedIndexChanged);
            // 
            // tablaProductos
            // 
            this.tablaProductos.AllowUserToAddRows = false;
            this.tablaProductos.AllowUserToDeleteRows = false;
            this.tablaProductos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.tablaProductos.Location = new System.Drawing.Point(468, 181);
            this.tablaProductos.Margin = new System.Windows.Forms.Padding(2);
            this.tablaProductos.Name = "tablaProductos";
            this.tablaProductos.ReadOnly = true;
            this.tablaProductos.RowHeadersWidth = 51;
            this.tablaProductos.RowTemplate.Height = 24;
            this.tablaProductos.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.tablaProductos.Size = new System.Drawing.Size(605, 304);
            this.tablaProductos.TabIndex = 13;
            // 
            // bClear
            // 
            this.bClear.Location = new System.Drawing.Point(179, 449);
            this.bClear.Margin = new System.Windows.Forms.Padding(2);
            this.bClear.Name = "bClear";
            this.bClear.Size = new System.Drawing.Size(82, 36);
            this.bClear.TabIndex = 14;
            this.bClear.Text = "CLEAR SELECTION";
            this.bClear.UseVisualStyleBackColor = true;
            this.bClear.Click += new System.EventHandler(this.bClear_Click);
            // 
            // bGenerate
            // 
            this.bGenerate.Location = new System.Drawing.Point(959, 538);
            this.bGenerate.Margin = new System.Windows.Forms.Padding(2);
            this.bGenerate.Name = "bGenerate";
            this.bGenerate.Size = new System.Drawing.Size(82, 36);
            this.bGenerate.TabIndex = 15;
            this.bGenerate.Text = "GENERATE CSV";
            this.bGenerate.UseVisualStyleBackColor = true;
            this.bGenerate.Click += new System.EventHandler(this.bGenerate_Click);
            // 
            // Csv
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1112, 612);
            this.Controls.Add(this.bGenerate);
            this.Controls.Add(this.bClear);
            this.Controls.Add(this.tablaProductos);
            this.Controls.Add(this.listCategorias);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.bBack);
            this.Controls.Add(this.label1);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "Csv";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Csv";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Csv_FormClosing);
            this.Load += new System.EventHandler(this.Csv_Load);
            ((System.ComponentModel.ISupportInitialize)(this.tablaProductos)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button bBack;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ListBox listCategorias;
        private System.Windows.Forms.DataGridView tablaProductos;
        private System.Windows.Forms.Button bClear;
        private System.Windows.Forms.Button bGenerate;
    }
}