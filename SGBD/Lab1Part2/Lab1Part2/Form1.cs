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
using System.Configuration;

namespace Lab1Part2
{
    public partial class Form1 : Form
    {

        SqlConnection conenction;
        SqlDataAdapter dataAdapterAirport;
        SqlDataAdapter dataAdapterChild;
        DataSet dataSet;
        BindingSource bindingSourceAirport;
        BindingSource bindingSourceApron;

        SqlCommandBuilder sqlCommandBuilder;

        string queryAirport;
        string queryChild;

        public Form1()
        {
            InitializeComponent();
            fillData();
        }
        private void fillData()
        {
            try
            {
                this.conenction = new SqlConnection(this.getConnectionString());
                this.queryAirport = ConfigurationManager.AppSettings["Airport_Select_All"];
                this.queryChild = ConfigurationManager.AppSettings["Child_Select_All"]; ;

                this.dataAdapterAirport = new SqlDataAdapter(this.queryAirport, this.conenction);
                this.dataAdapterChild = new SqlDataAdapter(this.queryChild, this.conenction);

                this.dataSet = new DataSet();
                this.dataAdapterAirport.Fill(this.dataSet, ConfigurationManager.AppSettings["Airport"]);
                this.dataAdapterChild.Fill(this.dataSet, ConfigurationManager.AppSettings["ChildTable"]);

                this.sqlCommandBuilder = new SqlCommandBuilder(dataAdapterChild);

                dataSet.Relations.Add("ParentChild",
                    dataSet.Tables[ConfigurationManager.AppSettings["Airport"]].Columns[ConfigurationManager.AppSettings["AirportPK"]],
                    dataSet.Tables[ConfigurationManager.AppSettings["ChildTable"]].Columns[ConfigurationManager.AppSettings["ChildFK"]]);

                this.dataGridViewParent.DataSource = dataSet.Tables[ConfigurationManager.AppSettings["Airport"]];
                this.dataGridViewChild.DataSource = this.dataGridViewParent.DataSource;
                this.dataGridViewChild.DataMember = "ParentChild";

                this.sqlCommandBuilder.GetUpdateCommand();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }

        }

        private string getConnectionString()
        {
            return ConfigurationManager.ConnectionStrings["connection_string"].ConnectionString.ToString();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                this.dataAdapterChild.Update(this.dataSet, ConfigurationManager.AppSettings["ChildTable"]);
            }catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }
        }

        private void ChildLabel_Click(object sender, EventArgs e)
        {

        }
    }
}
