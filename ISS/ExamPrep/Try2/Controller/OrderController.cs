using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Migrations.Model;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ExamPrep.Model;

namespace ExamPrep.Controller
{
    internal class OrderController
    {
        private DbContext Context;
        public OrderController(DbContext Context)
        {
            this.Context = Context;
        }

        public void AddOrder(string CustomerID, int ProductID, int Quantity, decimal TotalAmount)
        {
            try
            {
                var Order = new Order()
                {
                    CustomerId = CustomerID,
                    ProductId = ProductID,
                    Quantity = Quantity,
                    TotalAmount = TotalAmount
                };
                this.Context.Orders.add(Order);
                this.Context.SaveChanges();
                
            }
            catch (Exception Exception)
            {
                if(Environment.GetEnvironmentVariable("LOGGING") == "file")
                {
                    File.AppendAllText("log.txt", $"Error: {Exception.Message}\n");
                }
                else
                {
                    throw Exception;
                }
            }
        }
    }
}
