using System.Data.SqlClient;
using System.Data;
using System.Windows.Forms;
using System;

public partial class Form1 : Form
{
    SqlConnection connection;
    SqlDataAdapter adapterZone;
    SqlDataAdapter adapterParkingLot;
    DataSet dataSet;
    BindingSource bindingSourceZone;
    BindingSource bindingSourceParkingLot;

    SqlCommandBuilder sqlCommandBuilder;

    string queryZone;
    string queryParkingLot;

    public Form1()
    {
        InitializeComponent();
        fillData();
    }

    private void fillData()
    {
        connection = new SqlConnection(this.getConnectionString());
        queryZone = "Select * from Zone";
        queryParkingLot = "Select * from ParkingLot";

        adapterZone = new SqlDataAdapter(queryZone, connection);
        adapterParkingLot = new SqlDataAdapter(queryParkingLot, connection);
        dataSet = new DataSet();
        adapterZone.Fill(dataSet, "Zone");
        adapterParkingLot.Fill(dataSet, "ParkingLot");

        sqlCommandBuilder = new SqlCommandBuilder(adapterParkingLot);

        dataSet.Relations.Add("ZoneParkingLot",
            dataSet.Tables["Zone"].Columns["ID"],
            dataSet.Tables["ParkingLot"].Columns["ZoneID"]);

        bindingSourceZone = new BindingSource();
        bindingSourceParkingLot = new BindingSource();

        bindingSourceZone.DataSource = dataSet;
        bindingSourceZone.DataMember = "Zone";

        bindingSourceParkingLot.DataSource = bindingSourceZone;
        bindingSourceParkingLot.DataMember = "ZoneParkingLot";

        this.dgvZones.DataSource = bindingSourceZone;
        this.dgvParkingLots.DataSource = bindingSourceParkingLot;

        sqlCommandBuilder.GetUpdateCommand();
    }

    private string getConnectionString()
    {
        return "Data Source=DESKTOP-UELLOC9; Initial Catalog=ParkingLot;" +
            "Integrated Security=true";
    }

    private void label1_Click(object sender, EventArgs e)
    {

    }

    private void label2_Click(object sender, EventArgs e)
    {

    }

    private void Form1_Load(object sender, EventArgs e)
    {

    }

    private void updateButton_Click(object sender, EventArgs e)
    {
        adapterParkingLot.Update(dataSet, "ParkingLot");
    }

}
