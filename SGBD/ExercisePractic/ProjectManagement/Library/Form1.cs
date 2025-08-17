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

namespace Library
{
    public partial class Form1 : Form
    {

        SqlConnection connection;
        SqlDataAdapter adapterPublisher;
        SqlDataAdapter adapterBook;
        DataSet dataSet;
        BindingSource bindingSourcePublisher;
        BindingSource bindingSourceBook;

        SqlCommandBuilder sqlCommandBuilder;

        string queryPublisher;
        string queryBook;

        public Form1()
        {
            InitializeComponent();
            fillData();
        }

        private void fillData()
        {
            connection = new SqlConnection(this.getConnectionString());
            queryPublisher = "Select * from Publisher";
            queryBook = "Select * from Book";

            adapterPublisher = new SqlDataAdapter(queryPublisher, connection);
            adapterBook = new SqlDataAdapter(queryBook, connection);
            dataSet = new DataSet();
            adapterPublisher.Fill(dataSet, "Publisher");
            adapterBook.Fill(dataSet, "Book");

            sqlCommandBuilder = new SqlCommandBuilder(adapterBook);

            dataSet.Relations.Add("PublisherBook",
                dataSet.Tables["Publisher"].Columns["ID"],
                dataSet.Tables["Book"].Columns["publisherID"]);

            this.publisherGridView.DataSource = dataSet.Tables["Publisher"];
            this.bookGridView.DataSource = this.publisherGridView.DataSource;
            this.bookGridView.DataMember = "PublisherBook";

            sqlCommandBuilder.GetUpdateCommand();
            
        }

        private string getConnectionString()
        {
            return "Data Source=DESKTOP-UELLOC9; Initial Catalog=Library;" +
                "Integrated Security=true";
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label1_Click_1(object sender, EventArgs e)
        {

        }

        private void UPDATE_Click(object sender, EventArgs e)
        {
            adapterBook.Update(dataSet, "Book");
        }
    }
}
