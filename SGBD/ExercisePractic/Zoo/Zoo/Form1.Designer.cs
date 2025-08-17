namespace Zoo
{
    partial class Form1
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
            this.classLabel = new System.Windows.Forms.Label();
            this.specieLabel = new System.Windows.Forms.Label();
            this.gridViewSpecie = new System.Windows.Forms.DataGridView();
            this.button1 = new System.Windows.Forms.Button();
            this.comboBoxClass = new System.Windows.Forms.ComboBox();
            ((System.ComponentModel.ISupportInitialize)(this.gridViewSpecie)).BeginInit();
            this.SuspendLayout();
            // 
            // classLabel
            // 
            this.classLabel.AutoSize = true;
            this.classLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 13.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.classLabel.Location = new System.Drawing.Point(40, 38);
            this.classLabel.Name = "classLabel";
            this.classLabel.Size = new System.Drawing.Size(73, 29);
            this.classLabel.TabIndex = 0;
            this.classLabel.Text = "Class";
            // 
            // specieLabel
            // 
            this.specieLabel.AutoSize = true;
            this.specieLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.specieLabel.Location = new System.Drawing.Point(696, 50);
            this.specieLabel.Name = "specieLabel";
            this.specieLabel.Size = new System.Drawing.Size(102, 32);
            this.specieLabel.TabIndex = 2;
            this.specieLabel.Text = "Specie";
            // 
            // gridViewSpecie
            // 
            this.gridViewSpecie.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.gridViewSpecie.Location = new System.Drawing.Point(509, 124);
            this.gridViewSpecie.Name = "gridViewSpecie";
            this.gridViewSpecie.RowHeadersWidth = 51;
            this.gridViewSpecie.RowTemplate.Height = 24;
            this.gridViewSpecie.Size = new System.Drawing.Size(592, 316);
            this.gridViewSpecie.TabIndex = 3;
            // 
            // button1
            // 
            this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button1.Location = new System.Drawing.Point(337, 485);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(208, 47);
            this.button1.TabIndex = 4;
            this.button1.Text = "UPDATE";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // comboBoxClass
            // 
            this.comboBoxClass.FormattingEnabled = true;
            this.comboBoxClass.Location = new System.Drawing.Point(45, 124);
            this.comboBoxClass.Name = "comboBoxClass";
            this.comboBoxClass.Size = new System.Drawing.Size(121, 24);
            this.comboBoxClass.TabIndex = 5;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1302, 703);
            this.Controls.Add(this.comboBoxClass);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.gridViewSpecie);
            this.Controls.Add(this.specieLabel);
            this.Controls.Add(this.classLabel);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.gridViewSpecie)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label classLabel;
        private System.Windows.Forms.Label specieLabel;
        private System.Windows.Forms.DataGridView gridViewSpecie;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ComboBox comboBoxClass;
    }
}

