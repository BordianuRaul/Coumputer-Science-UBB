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

namespace Zoo
{
    public partial class Form1 : Form
    {
        SqlConnection connection;
        SqlDataAdapter adaptercClass;
        SqlDataAdapter adapterSpecie;
        DataSet dataSet;
        BindingSource bindingSourceClass;
        BindingSource bindingSourceSpecie;

        SqlCommandBuilder sqlCommandBuilder;

        string queryClass;
        string querySpecie;

        public Form1()
        {
            InitializeComponent();
            this.fillData();

        }

        private string getConnectionString()
        {
            return "Data Source=DESKTOP-UELLOC9; Initial Catalog=Zoo;" +
                "Integrated Security=true";
        }
        
        private void fillData()
        {
            connection = new SqlConnection(this.getConnectionString());

            queryClass = "SELECT * FROM Class";
            querySpecie = "SELECT * FROM Specie";

            adaptercClass = new SqlDataAdapter(queryClass, connection);
            adapterSpecie = new SqlDataAdapter(querySpecie, connection);

            dataSet = new DataSet();

            adaptercClass.Fill(dataSet, "Class");
            adapterSpecie.Fill(dataSet, "Specie");

            sqlCommandBuilder = new SqlCommandBuilder(adapterSpecie);

            this.comboBoxClass.DataSource = dataSet.Tables["Class"];
            this.comboBoxClass.DisplayMember = "name";
            this.comboBoxClass.ValueMember = "ID";

            this.comboBoxClass.SelectedIndexChanged += ComboBoxClasSelectedIndexChanged;
        }

        private void ComboBoxClasSelectedIndexChanged(object sender, EventArgs e)
        {
            if(comboBoxClass.SelectedValue != null)
            {
                int classID = (int)comboBoxClass.SelectedValue;
                DataView specieView = new DataView(dataSet.Tables["Specie"]);
                specieView.RowFilter = $"classID = {classID}";
                gridViewSpecie.DataSource = specieView;

            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            adapterSpecie.Update(dataSet, "Specie");
        }
    }
}
