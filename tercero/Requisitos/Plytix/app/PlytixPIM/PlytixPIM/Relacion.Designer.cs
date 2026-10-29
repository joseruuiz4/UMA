namespace PlytixPIM
{
    partial class Relacion
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
            this.labelProducts = new System.Windows.Forms.Label();
            this.bBack = new System.Windows.Forms.Button();
            this.bAddRelation = new System.Windows.Forms.Button();
            this.tablaRelaciones = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.tablaRelaciones)).BeginInit();
            this.SuspendLayout();
            // 
            // labelProducts
            // 
            this.labelProducts.AutoSize = true;
            this.labelProducts.Font = new System.Drawing.Font("Arial", 48F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelProducts.Location = new System.Drawing.Point(99, 27);
            this.labelProducts.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelProducts.Name = "labelProducts";
            this.labelProducts.Size = new System.Drawing.Size(750, 75);
            this.labelProducts.TabIndex = 3;
            this.labelProducts.Text = "YOUR RELATIONSHIPS";
            // 
            // bBack
            // 
            this.bBack.Location = new System.Drawing.Point(22, 60);
            this.bBack.Margin = new System.Windows.Forms.Padding(2);
            this.bBack.Name = "bBack";
            this.bBack.Size = new System.Drawing.Size(56, 19);
            this.bBack.TabIndex = 4;
            this.bBack.Text = "<- BACK";
            this.bBack.UseVisualStyleBackColor = true;
            this.bBack.Click += new System.EventHandler(this.bBack_Click);
            // 
            // bAddRelation
            // 
            this.bAddRelation.Location = new System.Drawing.Point(949, 545);
            this.bAddRelation.Name = "bAddRelation";
            this.bAddRelation.Size = new System.Drawing.Size(97, 23);
            this.bAddRelation.TabIndex = 5;
            this.bAddRelation.Text = "ADD RELATION";
            this.bAddRelation.UseVisualStyleBackColor = true;
            this.bAddRelation.Click += new System.EventHandler(this.bAddRelation_Click);
            // 
            // tablaRelaciones
            // 
            this.tablaRelaciones.AllowUserToAddRows = false;
            this.tablaRelaciones.AllowUserToDeleteRows = false;
            this.tablaRelaciones.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.tablaRelaciones.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.tablaRelaciones.Location = new System.Drawing.Point(323, 205);
            this.tablaRelaciones.Name = "tablaRelaciones";
            this.tablaRelaciones.ReadOnly = true;
            this.tablaRelaciones.Size = new System.Drawing.Size(517, 209);
            this.tablaRelaciones.TabIndex = 6;
            // 
            // Relacion
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1112, 612);
            this.Controls.Add(this.tablaRelaciones);
            this.Controls.Add(this.bAddRelation);
            this.Controls.Add(this.bBack);
            this.Controls.Add(this.labelProducts);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "Relacion";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Relacion";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Relacion_FormClosing);
            this.Load += new System.EventHandler(this.Relacion_Load);
            ((System.ComponentModel.ISupportInitialize)(this.tablaRelaciones)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelProducts;
        private System.Windows.Forms.Button bBack;
        private System.Windows.Forms.Button bAddRelation;
        private System.Windows.Forms.DataGridView tablaRelaciones;
    }
}