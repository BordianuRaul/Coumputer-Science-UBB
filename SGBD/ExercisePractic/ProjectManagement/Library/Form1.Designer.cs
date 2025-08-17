namespace Library
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
            this.publisherLabel = new System.Windows.Forms.Label();
            this.bookLabel = new System.Windows.Forms.Label();
            this.publisherGridView = new System.Windows.Forms.DataGridView();
            this.bookGridView = new System.Windows.Forms.DataGridView();
            this.UPDATE = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.publisherGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.bookGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // publisherLabel
            // 
            this.publisherLabel.AutoSize = true;
            this.publisherLabel.Location = new System.Drawing.Point(32, 24);
            this.publisherLabel.Name = "publisherLabel";
            this.publisherLabel.Size = new System.Drawing.Size(63, 16);
            this.publisherLabel.TabIndex = 0;
            this.publisherLabel.Text = "Publisher";
            this.publisherLabel.Click += new System.EventHandler(this.label1_Click);
            // 
            // bookLabel
            // 
            this.bookLabel.AutoSize = true;
            this.bookLabel.Location = new System.Drawing.Point(737, 24);
            this.bookLabel.Name = "bookLabel";
            this.bookLabel.Size = new System.Drawing.Size(39, 16);
            this.bookLabel.TabIndex = 1;
            this.bookLabel.Text = "Book";
            this.bookLabel.Click += new System.EventHandler(this.label1_Click_1);
            // 
            // publisherGridView
            // 
            this.publisherGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.publisherGridView.Location = new System.Drawing.Point(35, 62);
            this.publisherGridView.Name = "publisherGridView";
            this.publisherGridView.RowHeadersWidth = 51;
            this.publisherGridView.RowTemplate.Height = 24;
            this.publisherGridView.Size = new System.Drawing.Size(467, 272);
            this.publisherGridView.TabIndex = 2;
            // 
            // bookGridView
            // 
            this.bookGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.bookGridView.Location = new System.Drawing.Point(548, 62);
            this.bookGridView.Name = "bookGridView";
            this.bookGridView.RowHeadersWidth = 51;
            this.bookGridView.RowTemplate.Height = 24;
            this.bookGridView.Size = new System.Drawing.Size(461, 272);
            this.bookGridView.TabIndex = 3;
            // 
            // UPDATE
            // 
            this.UPDATE.Location = new System.Drawing.Point(438, 387);
            this.UPDATE.Name = "UPDATE";
            this.UPDATE.Size = new System.Drawing.Size(167, 35);
            this.UPDATE.TabIndex = 4;
            this.UPDATE.Text = "button1";
            this.UPDATE.UseVisualStyleBackColor = true;
            this.UPDATE.Click += new System.EventHandler(this.UPDATE_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1021, 585);
            this.Controls.Add(this.UPDATE);
            this.Controls.Add(this.bookGridView);
            this.Controls.Add(this.publisherGridView);
            this.Controls.Add(this.bookLabel);
            this.Controls.Add(this.publisherLabel);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.publisherGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.bookGridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label publisherLabel;
        private System.Windows.Forms.Label bookLabel;
        private System.Windows.Forms.DataGridView publisherGridView;
        private System.Windows.Forms.DataGridView bookGridView;
        private System.Windows.Forms.Button UPDATE;
    }
}

