namespace Practic2
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
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.dgvZones = new System.Windows.Forms.DataGridView();
            this.dgvParkingLots = new System.Windows.Forms.DataGridView();
            this.update = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgvZones)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvParkingLots)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(42, 41);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(38, 16);
            this.label1.TabIndex = 0;
            this.label1.Text = "Zone";
            this.label1.Click += new System.EventHandler(this.label1_Click_1);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(704, 41);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(71, 16);
            this.label2.TabIndex = 1;
            this.label2.Text = "ParkingLot";
            // 
            // dgvZones
            // 
            this.dgvZones.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvZones.Location = new System.Drawing.Point(45, 92);
            this.dgvZones.Name = "dgvZones";
            this.dgvZones.RowHeadersWidth = 51;
            this.dgvZones.RowTemplate.Height = 24;
            this.dgvZones.Size = new System.Drawing.Size(526, 348);
            this.dgvZones.TabIndex = 2;
            // 
            // dgvParkingLots
            // 
            this.dgvParkingLots.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvParkingLots.Location = new System.Drawing.Point(597, 92);
            this.dgvParkingLots.Name = "dgvParkingLots";
            this.dgvParkingLots.RowHeadersWidth = 51;
            this.dgvParkingLots.RowTemplate.Height = 24;
            this.dgvParkingLots.Size = new System.Drawing.Size(502, 348);
            this.dgvParkingLots.TabIndex = 3;
            // 
            // update
            // 
            this.update.Location = new System.Drawing.Point(494, 470);
            this.update.Name = "update";
            this.update.Size = new System.Drawing.Size(189, 87);
            this.update.TabIndex = 4;
            this.update.Text = "UDPATE";
            this.update.UseVisualStyleBackColor = true;
            this.update.Click += new System.EventHandler(this.update_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1132, 694);
            this.Controls.Add(this.update);
            this.Controls.Add(this.dgvParkingLots);
            this.Controls.Add(this.dgvZones);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dgvZones)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvParkingLots)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.DataGridView dgvZones;
        private System.Windows.Forms.DataGridView dgvParkingLots;
        private System.Windows.Forms.Button update;
    }
}

