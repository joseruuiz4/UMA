namespace PlytixPIM
{
    partial class Inicio
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Inicio));
            this.label1 = new System.Windows.Forms.Label();
            this.bSeeProducts = new System.Windows.Forms.Button();
            this.bSeeCategories = new System.Windows.Forms.Button();
            this.bSeeAtributes = new System.Windows.Forms.Button();
            this.bSeeRelationships = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Arial", 48F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(234, 24);
            this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(712, 75);
            this.label1.TabIndex = 0;
            this.label1.Text = "WELCOME TO PLYTIX";
            // 
            // bSeeProducts
            // 
            this.bSeeProducts.Font = new System.Drawing.Font("Arial Narrow", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bSeeProducts.Location = new System.Drawing.Point(40, 151);
            this.bSeeProducts.Margin = new System.Windows.Forms.Padding(2);
            this.bSeeProducts.Name = "bSeeProducts";
            this.bSeeProducts.Size = new System.Drawing.Size(310, 179);
            this.bSeeProducts.TabIndex = 1;
            this.bSeeProducts.Text = "SEE PRODUCTS";
            this.bSeeProducts.UseVisualStyleBackColor = true;
            this.bSeeProducts.Click += new System.EventHandler(this.bSeeProducts_Click);
            // 
            // bSeeCategories
            // 
            this.bSeeCategories.Font = new System.Drawing.Font("Arial Narrow", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bSeeCategories.Location = new System.Drawing.Point(410, 151);
            this.bSeeCategories.Margin = new System.Windows.Forms.Padding(2);
            this.bSeeCategories.Name = "bSeeCategories";
            this.bSeeCategories.Size = new System.Drawing.Size(310, 179);
            this.bSeeCategories.TabIndex = 2;
            this.bSeeCategories.Text = "SEE CATEGORIES";
            this.bSeeCategories.UseVisualStyleBackColor = true;
            this.bSeeCategories.Click += new System.EventHandler(this.bSeeCategories_Click);
            // 
            // bSeeAtributes
            // 
            this.bSeeAtributes.Font = new System.Drawing.Font("Arial Narrow", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bSeeAtributes.Location = new System.Drawing.Point(787, 151);
            this.bSeeAtributes.Margin = new System.Windows.Forms.Padding(2);
            this.bSeeAtributes.Name = "bSeeAtributes";
            this.bSeeAtributes.Size = new System.Drawing.Size(310, 179);
            this.bSeeAtributes.TabIndex = 3;
            this.bSeeAtributes.Text = "SEE ATTRIBUTES";
            this.bSeeAtributes.UseVisualStyleBackColor = true;
            this.bSeeAtributes.Click += new System.EventHandler(this.bSeeAtributes_Click);
            // 
            // bSeeRelationships
            // 
            this.bSeeRelationships.Font = new System.Drawing.Font("Arial Narrow", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bSeeRelationships.Location = new System.Drawing.Point(386, 393);
            this.bSeeRelationships.Margin = new System.Windows.Forms.Padding(2);
            this.bSeeRelationships.Name = "bSeeRelationships";
            this.bSeeRelationships.Size = new System.Drawing.Size(356, 179);
            this.bSeeRelationships.TabIndex = 4;
            this.bSeeRelationships.Text = "SEE RELATIONSHIPS";
            this.bSeeRelationships.UseVisualStyleBackColor = true;
            this.bSeeRelationships.Click += new System.EventHandler(this.bSeeRelationships_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.ErrorImage = ((System.Drawing.Image)(resources.GetObject("pictureBox1.ErrorImage")));
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(1028, 32);
            this.pictureBox1.Margin = new System.Windows.Forms.Padding(2);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(51, 51);
            this.pictureBox1.TabIndex = 5;
            this.pictureBox1.TabStop = false;
            this.pictureBox1.Click += new System.EventHandler(this.pictureBox1_Click);
            this.pictureBox1.DoubleClick += new System.EventHandler(this.pictureBox1_Click);
            this.pictureBox1.MouseEnter += new System.EventHandler(this.pictureBox1_MouseEnter);
            this.pictureBox1.MouseLeave += new System.EventHandler(this.pictureBox1_MouseLeave);
            // 
            // Inicio
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1112, 612);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.bSeeRelationships);
            this.Controls.Add(this.bSeeAtributes);
            this.Controls.Add(this.bSeeCategories);
            this.Controls.Add(this.bSeeProducts);
            this.Controls.Add(this.label1);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "Inicio";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Inicio";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Inicio_FormClosing);
            this.Load += new System.EventHandler(this.Inicio_Load);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button bSeeProducts;
        private System.Windows.Forms.Button bSeeCategories;
        private System.Windows.Forms.Button bSeeAtributes;
        private System.Windows.Forms.Button bSeeRelationships;
        private System.Windows.Forms.PictureBox pictureBox1;
    }
}