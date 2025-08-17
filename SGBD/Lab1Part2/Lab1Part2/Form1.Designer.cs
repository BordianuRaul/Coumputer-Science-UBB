namespace Lab1Part2
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
            this.dataGridViewParent = new System.Windows.Forms.DataGridView();
            this.dataGridViewChild = new System.Windows.Forms.DataGridView();
            this.ParentLabel = new System.Windows.Forms.Label();
            this.ChildLabel = new System.Windows.Forms.Label();
            this.updateButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewParent)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewChild)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewParent
            // 
            this.dataGridViewParent.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewParent.Location = new System.Drawing.Point(12, 87);
            this.dataGridViewParent.Name = "dataGridViewParent";
            this.dataGridViewParent.RowHeadersWidth = 51;
            this.dataGridViewParent.RowTemplate.Height = 24;
            this.dataGridViewParent.Size = new System.Drawing.Size(657, 303);
            this.dataGridViewParent.TabIndex = 0;
            // 
            // dataGridViewChild
            // 
            this.dataGridViewChild.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewChild.Location = new System.Drawing.Point(843, 87);
            this.dataGridViewChild.Name = "dataGridViewChild";
            this.dataGridViewChild.RowHeadersWidth = 51;
            this.dataGridViewChild.RowTemplate.Height = 24;
            this.dataGridViewChild.Size = new System.Drawing.Size(566, 303);
            this.dataGridViewChild.TabIndex = 1;
            // 
            // ParentLabel
            // 
            this.ParentLabel.AutoSize = true;
            this.ParentLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ParentLabel.Location = new System.Drawing.Point(12, 56);
            this.ParentLabel.Name = "ParentLabel";
            this.ParentLabel.Size = new System.Drawing.Size(76, 25);
            this.ParentLabel.TabIndex = 2;
            this.ParentLabel.Text = "Airport";
            this.ParentLabel.Click += new System.EventHandler(this.label1_Click);
            // 
            // ChildLabel
            // 
            this.ChildLabel.AutoSize = true;
            this.ChildLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ChildLabel.Location = new System.Drawing.Point(838, 47);
            this.ChildLabel.Name = "ChildLabel";
            this.ChildLabel.Size = new System.Drawing.Size(117, 25);
            this.ChildLabel.TabIndex = 3;
            this.ChildLabel.Text = "ChildTable";
            this.ChildLabel.Click += new System.EventHandler(this.ChildLabel_Click);
            // 
            // updateButton
            // 
            this.updateButton.Font = new System.Drawing.Font("Lucida Console", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.updateButton.Location = new System.Drawing.Point(697, 451);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(105, 23);
            this.updateButton.TabIndex = 4;
            this.updateButton.Text = "UPDATE";
            this.updateButton.UseVisualStyleBackColor = true;
            this.updateButton.Click += new System.EventHandler(this.button1_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1421, 727);
            this.Controls.Add(this.updateButton);
            this.Controls.Add(this.ChildLabel);
            this.Controls.Add(this.ParentLabel);
            this.Controls.Add(this.dataGridViewChild);
            this.Controls.Add(this.dataGridViewParent);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewParent)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewChild)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewParent;
        private System.Windows.Forms.DataGridView dataGridViewChild;
        private System.Windows.Forms.Label ParentLabel;
        private System.Windows.Forms.Label ChildLabel;
        private System.Windows.Forms.Button updateButton;
    }
}

