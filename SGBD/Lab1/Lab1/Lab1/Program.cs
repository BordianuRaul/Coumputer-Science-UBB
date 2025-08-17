using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;
using System.Security.Cryptography.X509Certificates;

namespace Lab1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string connectionData = "Data Source=DESKTOP-UELLOC9;" + "Initial Catalog=Lab3_Week8;Integrated Security=true";
            SqlConnection conn = new SqlConnection(connectionData);

            conn.Open();

            string selectStatement = "SELECT * FROM Airport";
            SqlCommand command = new SqlCommand(selectStatement, conn);

            using(SqlDataReader reader = command.ExecuteReader())
            {
                while (reader.Read())
                {
                    Console.WriteLine($"{reader[0]}, {reader[1]}, {reader[2]}");
                }
            }

            Console.WriteLine("Press any key to exit...");
            Console.ReadKey();
        }
    }
}
