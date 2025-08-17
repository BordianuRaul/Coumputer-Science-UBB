using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {

        SqlConnection connection;
        SqlDataAdapter adapterDeveloper;
        SqlDataAdapter adapterTask;
        DataSet dataSet;
        BindingSource bindingSourceDeveloper;
        BindingSource bindingSourceTask;

        SqlCommandBuilder sqlCommandBuilder;

        string queryDeveloper;
        string queryTask;

        public Form1()
        {
            InitializeComponent();
            FillData();
        }

        void FillData()
        {
            connection = new SqlConnection(GetConnectionString());

            queryDeveloper = "Select * from Developer";
            queryTask = " Select * from Task";

            adapterDeveloper = new SqlDataAdapter(queryDeveloper, connection);
            adapterTask = new SqlDataAdapter(queryTask, connection);
            dataSet = new DataSet();
            adapterDeveloper.Fill(dataSet, "Developer");
            adapterTask.Fill(dataSet, "Task");

            sqlCommandBuilder = new SqlCommandBuilder(adapterTask);

            dataSet.Relations.Add("DeveloperTask",
                dataSet.Tables["Developer"].Columns["ID"],
                dataSet.Tables["Task"].Columns["developerID"]);

            this.comboBoxDeveloper.DataSource = dataSet.Tables["Developer"];
            this.comboBoxDeveloper.DisplayMember = "name";
            this.comboBoxDeveloper.ValueMember = "ID";

            this.comboBoxDeveloper.SelectedIndexChanged += ComboBoxDeveloperSelectedIndexChanged;

        }

        private void ComboBoxDeveloperSelectedIndexChanged(object sender, EventArgs e)
        {
            if(comboBoxDeveloper.SelectedValue != null)
            {
                int developerID = (int) comboBoxDeveloper.SelectedValue;
                DataView taskView = new DataView(dataSet.Tables["Task"]);
                taskView.RowFilter = $"developerID= {developerID}";
                taskGridView.DataSource = taskView;

            }
        }

        string GetConnectionString()
        {
            return "Data Source=DESKTOP-UELLOC9; Initial Catalog=ProjectManagementSystem;" +
                "Integrated Security=true";

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            adapterTask.Update(dataSet, "Task");
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
    }
}
