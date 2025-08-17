using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assigment2
{
    public enum LogLevel
    {
        INFO,
        WARN,
        ERROR
    }


    public class Logger
    {
        private string logFilePath = @"C:\\Users\\Raul\\Desktop\\ISS\\Assigment2\\Assigment2\bid_system_logs.txt";
        private List<string> bufferedLogs = new List<string>();

        public void Log(string message, LogLevel level = LogLevel.INFO)
        {
            string logEntry = $"{DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss")} [{level}] - {message}\n";


            if (level == LogLevel.ERROR)
            {
                WriteToFile(logEntry);
            }
            else
            {
                bufferedLogs.Add(logEntry);
            }


            if (level == LogLevel.INFO || bufferedLogs.Count >= 10)
            {
                FlushBufferedLogs();
            }
        }

        public void FlushBufferedLogs()
        {
            File.AppendAllLines(logFilePath, bufferedLogs);
            bufferedLogs.Clear();
        }

        private void WriteToFile(string logEntry)
        {
            File.AppendAllText(logFilePath, logEntry);
        }
    }

    public class BiddingSystem
    {
        private Logger logger;

        public BiddingSystem(Logger logger)
        {
            this.logger = logger;
        }

        public void PlaceBid(string bidder, decimal amount)
        {
            try
            {
                logger.Log($"Bid placed by {bidder} for ${amount}", LogLevel.INFO);
            }
            catch (Exception ex)
            {
                logger.Log($"Error placing bid by {bidder}: {ex.Message}", LogLevel.ERROR);
            }
        }
    }

    internal class Program
    {
        static void Main(string[] args)
        {
            Logger logger = new Logger();
            BiddingSystem biddingSystem = new BiddingSystem(logger);

            biddingSystem.PlaceBid("User123", 100);
            biddingSystem.PlaceBid("User456", 150);

            logger.FlushBufferedLogs();
        }
    }
}
