namespace PlytixPIM
{
    partial class Cuenta
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
            this.bExport = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.tName = new System.Windows.Forms.TextBox();
            this.tDate = new System.Windows.Forms.TextBox();
            this.tProducts = new System.Windows.Forms.TextBox();
            this.tAttributes = new System.Windows.Forms.TextBox();
            this.tCategories = new System.Windows.Forms.TextBox();
            this.tRelationships = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Arial", 48F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(187, 41);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(688, 93);
            this.label1.TabIndex = 3;
            this.label1.Text = "YOUR ACCOUNT";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // bBack
            // 
            this.bBack.Font = new System.Drawing.Font("Arial", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.bBack.Location = new System.Drawing.Point(38, 76);
            this.bBack.Name = "bBack";
            this.bBack.Size = new System.Drawing.Size(98, 30);
            this.bBack.TabIndex = 9;
            this.bBack.Text = "<- BACK";
            this.bBack.UseVisualStyleBackColor = true;
            this.bBack.Click += new System.EventHandler(this.bBack_Click);
            // 
            // bExport
            // 
            this.bExport.Location = new System.Drawing.Point(1317, 687);
            this.bExport.Name = "bExport";
            this.bExport.Size = new System.Drawing.Size(118, 43);
            this.bExport.TabIndex = 10;
            this.bExport.Text = "EXPORT (JSON)";
            this.bExport.UseVisualStyleBackColor = true;
            this.bExport.Click += new System.EventHandler(this.bExport_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(162, 198);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(194, 32);
            this.label3.TabIndex = 12;
            this.label3.Text = "Account name";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(162, 281);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(180, 32);
            this.label2.TabIndex = 13;
            this.label2.Text = "Creation date";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(162, 373);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(260, 32);
            this.label4.TabIndex = 14;
            this.label4.Text = "Number of products";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(162, 467);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(267, 32);
            this.label5.TabIndex = 15;
            this.label5.Text = "Number of attributes";
            this.label5.Click += new System.EventHandler(this.label5_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(162, 560);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(279, 32);
            this.label6.TabIndex = 16;
            this.label6.Text = "Number of categories";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.Location = new System.Drawing.Point(162, 656);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(308, 32);
            this.label7.TabIndex = 17;
            this.label7.Text = "Number of relationships";
            // 
            // tName
            // 
            this.tName.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tName.Location = new System.Drawing.Point(743, 198);
            this.tName.Name = "tName";
            this.tName.ReadOnly = true;
            this.tName.Size = new System.Drawing.Size(344, 39);
            this.tName.TabIndex = 18;
            // 
            // tDate
            // 
            this.tDate.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tDate.Location = new System.Drawing.Point(743, 278);
            this.tDate.Name = "tDate";
            this.tDate.ReadOnly = true;
            this.tDate.Size = new System.Drawing.Size(344, 39);
            this.tDate.TabIndex = 19;
            // 
            // tProducts
            // 
            this.tProducts.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tProducts.Location = new System.Drawing.Point(743, 365);
            this.tProducts.Name = "tProducts";
            this.tProducts.ReadOnly = true;
            this.tProducts.Size = new System.Drawing.Size(344, 39);
            this.tProducts.TabIndex = 20;
            // 
            // tAttributes
            // 
            this.tAttributes.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tAttributes.Location = new System.Drawing.Point(743, 460);
            this.tAttributes.Name = "tAttributes";
            this.tAttributes.ReadOnly = true;
            this.tAttributes.Size = new System.Drawing.Size(344, 39);
            this.tAttributes.TabIndex = 21;
            // 
            // tCategories
            // 
            this.tCategories.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tCategories.Location = new System.Drawing.Point(743, 557);
            this.tCategories.Name = "tCategories";
            this.tCategories.ReadOnly = true;
            this.tCategories.Size = new System.Drawing.Size(344, 39);
            this.tCategories.TabIndex = 22;
            // 
            // tRelationships
            // 
            this.tRelationships.Font = new System.Drawing.Font("Arial", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tRelationships.Location = new System.Drawing.Point(743, 656);
            this.tRelationships.Name = "tRelationships";
            this.tRelationships.ReadOnly = true;
            this.tRelationships.Size = new System.Drawing.Size(344, 39);
            this.tRelationships.TabIndex = 23;
            // 
            // Cuenta
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1482, 753);
            this.Controls.Add(this.tRelationships);
            this.Controls.Add(this.tCategories);
            this.Controls.Add(this.tAttributes);
            this.Controls.Add(this.tProducts);
            this.Controls.Add(this.tDate);
            this.Controls.Add(this.tName);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.bExport);
            this.Controls.Add(this.bBack);
            this.Controls.Add(this.label1);
            this.Name = "Cuenta";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Cuenta";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Cuenta_FormClosing);
            this.Load += new System.EventHandler(this.Cuenta_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button bBack;
        private System.Windows.Forms.Button bExport;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox tName;
        private System.Windows.Forms.TextBox tDate;
        private System.Windows.Forms.TextBox tProducts;
        private System.Windows.Forms.TextBox tAttributes;
        private System.Windows.Forms.TextBox tCategories;
        private System.Windows.Forms.TextBox tRelationships;
    }
}