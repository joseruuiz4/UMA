namespace PlytixPIM
{
    partial class Categorias
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
            this.labelCategories = new System.Windows.Forms.Label();
            this.tablaCategorias = new System.Windows.Forms.DataGridView();
            this.bAddCategory = new System.Windows.Forms.Button();
            this.bBack = new System.Windows.Forms.Button();
            this.bDeleteCategory = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.tablaCategorias)).BeginInit();
            this.SuspendLayout();
            // 
            // labelCategories
            // 
            this.labelCategories.AutoSize = true;
            this.labelCategories.Font = new System.Drawing.Font("Arial", 48F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelCategories.Location = new System.Drawing.Point(112, 29);
            this.labelCategories.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelCategories.Name = "labelCategories";
            this.labelCategories.Size = new System.Drawing.Size(451, 75);
            this.labelCategories.TabIndex = 0;
            this.labelCategories.Text = "CATEGORIES";
            // 
            // tablaCategorias
            // 
            this.tablaCategorias.AllowUserToAddRows = false;
            this.tablaCategorias.AllowUserToDeleteRows = false;
            this.tablaCategorias.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.tablaCategorias.Location = new System.Drawing.Point(230, 142);
            this.tablaCategorias.Margin = new System.Windows.Forms.Padding(2);
            this.tablaCategorias.Name = "tablaCategorias";
            this.tablaCategorias.ReadOnly = true;
            this.tablaCategorias.RowHeadersWidth = 51;
            this.tablaCategorias.RowTemplate.Height = 24;
            this.tablaCategorias.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.tablaCategorias.Size = new System.Drawing.Size(707, 277);
            this.tablaCategorias.TabIndex = 1;
            // 
            // bAddCategory
            // 
            this.bAddCategory.Location = new System.Drawing.Point(797, 532);
            this.bAddCategory.Margin = new System.Windows.Forms.Padding(2);
            this.bAddCategory.Name = "bAddCategory";
            this.bAddCategory.Size = new System.Drawing.Size(140, 57);
            this.bAddCategory.TabIndex = 2;
            this.bAddCategory.Text = "Add New Category";
            this.bAddCategory.UseVisualStyleBackColor = true;
            this.bAddCategory.Click += new System.EventHandler(this.bAddCategory_Click);
            // 
            // bBack
            // 
            this.bBack.Location = new System.Drawing.Point(20, 52);
            this.bBack.Margin = new System.Windows.Forms.Padding(2);
            this.bBack.Name = "bBack";
            this.bBack.Size = new System.Drawing.Size(62, 24);
            this.bBack.TabIndex = 4;
            this.bBack.Text = "<- BACK";
            this.bBack.UseVisualStyleBackColor = true;
            this.bBack.Click += new System.EventHandler(this.bBack_Click);
            // 
            // bDeleteCategory
            // 
            this.bDeleteCategory.Location = new System.Drawing.Point(963, 532);
            this.bDeleteCategory.Margin = new System.Windows.Forms.Padding(2);
            this.bDeleteCategory.Name = "bDeleteCategory";
            this.bDeleteCategory.Size = new System.Drawing.Size(140, 57);
            this.bDeleteCategory.TabIndex = 5;
            this.bDeleteCategory.Text = "Delete Category";
            this.bDeleteCategory.UseVisualStyleBackColor = true;
            this.bDeleteCategory.Click += new System.EventHandler(this.bDeleteCategory_Click);
            // 
            // Categorias
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.ClientSize = new System.Drawing.Size(1112, 612);
            this.Controls.Add(this.bDeleteCategory);
            this.Controls.Add(this.bBack);
            this.Controls.Add(this.bAddCategory);
            this.Controls.Add(this.tablaCategorias);
            this.Controls.Add(this.labelCategories);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "Categorias";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Categorias";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Categorias_FormClosing);
            this.Load += new System.EventHandler(this.Categorias_Load);
            ((System.ComponentModel.ISupportInitialize)(this.tablaCategorias)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelCategories;
        private System.Windows.Forms.DataGridView tablaCategorias;
        private System.Windows.Forms.Button bAddCategory;
        private System.Windows.Forms.Button bBack;
        private System.Windows.Forms.Button bDeleteCategory;
    }
}