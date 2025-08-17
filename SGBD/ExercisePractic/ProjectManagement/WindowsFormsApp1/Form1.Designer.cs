namespace WindowsFormsApp1
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
            this.Developer = new System.Windows.Forms.Label();
            this.Task = new System.Windows.Forms.Label();
            this.taskGridView = new System.Windows.Forms.DataGridView();
            this.updateButton = new System.Windows.Forms.Button();
            this.comboBoxDeveloper = new System.Windows.Forms.ComboBox();
            ((System.ComponentModel.ISupportInitialize)(this.taskGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // Developer
            // 
            this.Developer.AutoSize = true;
            this.Developer.Location = new System.Drawing.Point(22, 31);
            this.Developer.Name = "Developer";
            this.Developer.Size = new System.Drawing.Size(89, 16);
            this.Developer.TabIndex = 0;
            this.Developer.Text = "DEVELOPER";
            this.Developer.Click += new System.EventHandler(this.label1_Click);
            // 
            // Task
            // 
            this.Task.AutoSize = true;
            this.Task.Location = new System.Drawing.Point(642, 46);
            this.Task.Name = "Task";
            this.Task.Size = new System.Drawing.Size(42, 16);
            this.Task.TabIndex = 1;
            this.Task.Text = "TASK";
            // 
            // taskGridView
            // 
            this.taskGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.taskGridView.Location = new System.Drawing.Point(645, 78);
            this.taskGridView.Name = "taskGridView";
            this.taskGridView.RowHeadersWidth = 51;
            this.taskGridView.RowTemplate.Height = 24;
            this.taskGridView.Size = new System.Drawing.Size(473, 287);
            this.taskGridView.TabIndex = 3;
            // 
            // updateButton
            // 
            this.updateButton.Location = new System.Drawing.Point(530, 393);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(110, 23);
            this.updateButton.TabIndex = 4;
            this.updateButton.Text = "UPDATE";
            this.updateButton.UseVisualStyleBackColor = true;
            this.updateButton.Click += new System.EventHandler(this.updateButton_Click);
            // 
            // comboBoxDeveloper
            // 
            this.comboBoxDeveloper.Font = new System.Drawing.Font("Microsoft Sans Serif", 16.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.comboBoxDeveloper.FormattingEnabled = true;
            this.comboBoxDeveloper.Location = new System.Drawing.Point(25, 78);
            this.comboBoxDeveloper.Name = "comboBoxDeveloper";
            this.comboBoxDeveloper.Size = new System.Drawing.Size(220, 39);
            this.comboBoxDeveloper.TabIndex = 5;
            this.comboBoxDeveloper.SelectedIndexChanged += new System.EventHandler(this.comboBox1_SelectedIndexChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1175, 519);
            this.Controls.Add(this.comboBoxDeveloper);
            this.Controls.Add(this.updateButton);
            this.Controls.Add(this.taskGridView);
            this.Controls.Add(this.Task);
            this.Controls.Add(this.Developer);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.taskGridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label Developer;
        private System.Windows.Forms.Label Task;
        private System.Windows.Forms.DataGridView taskGridView;
        private System.Windows.Forms.Button updateButton;
        private System.Windows.Forms.ComboBox comboBoxDeveloper;
    }
}

