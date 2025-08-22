#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <DHT.h>

const char* ssid = "Raul";
const char* password = "raul1234";

#define DHTPIN 2        // Use GPIO2
#define DHTTYPE DHT11   // Define DHT sensor type

DHT dht(DHTPIN, DHTTYPE);
ESP8266WebServer server(80);

// Function to generate HTML page
String generateWebPage() {
  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();

  if (isnan(temperature) || isnan(humidity)) {
    return "<html><head><meta http-equiv='refresh' content='5'></head><body><h1>Failed to read from DHT sensor!</h1></body></html>";
  }

  String html = "<!DOCTYPE html>";
  html += "<html><head>";
  html += "<meta name='viewport' content='width=device-width, initial-scale=1'>";
  html += "<meta http-equiv='refresh' content='5'>"; // Auto refresh every 5 sec
  html += "<style>";
  html += "body { font-family: Arial, sans-serif; text-align: center; background-color: #f4f4f4; color: #333; padding: 20px;}";
  html += ".container { max-width: 400px; margin: 0 auto; background: white; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);}";
  html += "h1 { color: #007BFF; }";
  html += ".sensor-data { font-size: 24px; font-weight: bold; }";
  html += "</style>";
  html += "</head><body>";

  html += "<div class='container'>";
  html += "<h1>ESP8266 DHT11 Sensor</h1>";
  html += "<p class='sensor-data'> Temperature: " + String(temperature) + " C</p>";
  html += "<p class='sensor-data'> Humidity: " + String(humidity) + " %</p>";
  html += "<p>Auto-refresh every 5 sec</p>";
  html += "</div>";

  html += "</body></html>";
  
  return html;
}

// Function to serve the webpage
void handleRoot() {
  server.send(200, "text/html", generateWebPage());
}

void setup() {
  Serial.begin(115200);
  dht.begin();

  Serial.println("\nConnecting to Wi-Fi...");
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("\nWi-Fi connected!");
  Serial.print("ESP8266 IP Address: ");
  Serial.println(WiFi.localIP());

  server.on("/", handleRoot);
  server.begin();
  Serial.println("Web server started!");
}

void loop() {
  server.handleClient();
}
