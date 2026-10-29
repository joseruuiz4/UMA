namespace PlytixPIM
{
    partial class Atributos
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
            this.label1 = new System.Windows.Forms.Label();
            this.tablaAtributos = new System.Windows.Forms.DataGridView();
            this.bAddAttribute = new System.Windows.Forms.Button();
            this.bEditAttribute = new System.Windows.Forms.Button();
            this.bDeleteAttribute = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.tablaAtributos)).BeginInit();
            this.SuspendLayout();
            // 
            // bBack
            // 
            this.bBack.Location = new System.Drawing.Point(27, 47);
            this.bBack.Margin = new System.Windows.Forms.Padding(2);
            this.bBack.Name = "bBack";
            this.bBack.Size = new System.Drawing.Size(56, 19);
            this.bBack.TabIndex = 1;
            this.bBack.Text = "<- BACK";
            this.bBack.UseVisualStyleBackColor = true;
            this.bBack.Click += new System.EventHandler(this.bBack_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Arial", 48F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(131, 21);
            this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(635, 75);
            this.label1.TabIndex = 2;
            this.label1.Text = "YOUR ATTRIBUTES";
            // 
            // tablaAtributos
            // 
            this.tablaAtributos.AllowUserToAddRows = false;
            this.tablaAtributos.AllowUserToDeleteRows = false;
            this.tablaAtributos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.tablaAtributos.Location = new System.Drawing.Point(202, 145);
            this.tablaAtributos.Margin = new System.Windows.Forms.Padding(2);
            this.tablaAtributos.Name = "tablaAtributos";
            this.tablaAtributos.ReadOnly = true;
            this.tablaAtributos.RowHeadersWidth = 51;
            this.tablaAtributos.RowTemplate.Height = 24;
            this.tablaAtributos.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.tablaAtributos.Size = new System.Drawing.Size(698, 336);
            this.tablaAtributos.TabIndex = 3;
            // 
            // bAddAttribute
            // 
            this.bAddAttribute.Font = new System.Drawing.Font("Arial Narrow", 10.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bAddAttribute.Location = new System.Drawing.Point(749, 557);
            this.bAddAttribute.Margin = new System.Windows.Forms.Padding(2);
            this.bAddAttribute.Name = "bAddAttribute";
            this.bAddAttribute.Size = new System.Drawing.Size(98, 46);
            this.bAddAttribute.TabIndex = 4;
            this.bAddAttribute.Text = "Add Attribute";
            this.bAddAttribute.UseVisualStyleBackColor = true;
            this.bAddAttribute.Click += new System.EventHandler(this.bAddAttribute_Click);
            // 
            // bEditAttribute
            // 
            this.bEditAttribute.Font = new System.Drawing.Font("Arial Narrow", 10.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bEditAttribute.Location = new System.Drawing.Point(878, 557);
            this.bEditAttribute.Margin = new System.Windows.Forms.Padding(2);
            this.bEditAttribute.Name = "bEditAttribute";
            this.bEditAttribute.Size = new System.Drawing.Size(98, 46);
            this.bEditAttribute.TabIndex = 5;
            this.bEditAttribute.Text = "Edit Attribute";
            this.bEditAttribute.UseVisualStyleBackColor = true;
            this.bEditAttribute.Click += new System.EventHandler(this.bEditAttribute_Click);
            // 
            // bDeleteAttribute
            // 
            this.bDeleteAttribute.Font = new System.Drawing.Font("Arial Narrow", 10.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bDeleteAttribute.Location = new System.Drawing.Point(1004, 557);
            this.bDeleteAttribute.Margin = new System.Windows.Forms.Padding(2);
            this.bDeleteAttribute.Name = "bDeleteAttribute";
            this.bDeleteAttribute.Size = new System.Drawing.Size(98, 46);
            this.bDeleteAttribute.TabIndex = 6;
            this.bDeleteAttribute.Text = "Delete Attribute";
            this.bDeleteAttribute.UseVisualStyleBackColor = true;
            this.bDeleteAttribute.Click += new System.EventHandler(this.bDeleteAttribute_Click);
            // 
            // Atributos
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1112, 612);
            this.Controls.Add(this.bDeleteAttribute);
            this.Controls.Add(this.bEditAttribute);
            this.Controls.Add(this.bAddAttribute);
            this.Controls.Add(this.tablaAtributos);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.bBack);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "Atributos";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Atributos";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Atributos_FormClosing);
            this.Load += new System.EventHandler(this.Atributos_Load);
            ((System.ComponentModel.ISupportInitialize)(this.tablaAtributos)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button bBack;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridView tablaAtributos;
        private System.Windows.Forms.Button bAddAttribute;
        private System.Windows.Forms.Button bEditAttribute;
        private System.Windows.Forms.Button bDeleteAttribute;
    }
}