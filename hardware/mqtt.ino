#include <DS3231.h>
#include <dht.h>
#include <Wire.h>
#include <SPI.h>
#include <Ethernet.h>
#include <ArduinoJson.h>
#include <PubSubClient.h>

#define DHT11_PIN A0
#define POTI_PIN A1

DS3231 clock;
dht DHT;
RTCDateTime dt;

float temperaturInC=0;
float feuchtigkeit=0;
float druckWert=0;
int poti_value=0;

// Update these with values suitable for your network.
IPAddress ip(172, 17, 34, 234);
// you can't have 2 of the same mac on your network!
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
char server[] = "docker.htl-wels.at";
// UUID and token for Octoblu 
char UUID[]  = "";
char TOKEN[] = "";
char data[80];
StaticJsonBuffer<200> jsonBuffer;

void callback(char* topic, byte* payload, unsigned int length) {
  char inData[80];

  
 Serial.print("payload: ");
 for(int i =26; i<length; i++){
   inData[(i - 26)] = (char)payload[i];
 }
 JsonObject& root = jsonBuffer.parseObject(inData);  
 int val = root["value"];

 Serial.println(val); 
  
if (val == 1){
  
  digitalWrite(6, HIGH);
  
}else if( val == 0 ){
 
  digitalWrite(6, LOW);
  
}
  
  
}

EthernetClient ethClient;
PubSubClient client(server, 1883, callback, ethClient);


void setup() {
    Serial.begin(115200);
    pinMode(POTI_PIN,INPUT);
    pinMode(DHT11_PIN,INPUT);
    
    clock.begin();
    clock.setDateTime(__DATE__, __TIME__);

  if (Ethernet.begin(mac) == 0) {
    Serial.println(F("Failed to configure Ethernet using DHCP"));
    Ethernet.begin(mac, ip);
  }
   char clientId[40] = "mb_";
    strcat(clientId, UUID);
  if (client.connect(clientId, UUID, TOKEN)) {
  
    client.subscribe(UUID);
  }
  pinMode(6, OUTPUT);
}

void loop() {
  int checkVariable = DHT.read11(DHT11_PIN);
  dt = clock.getDateTime();

  temperaturInC =DHT.temperature;
  feuchtigkeit = DHT.humidity;
  poti_value=analogRead(POTI_PIN);
  druckWert=map(poti_value,0,1023,2000,70000);
  char buf2[10];
  sprintf(buf2,"%02d:%02d:%02d",dt.hour,dt.minute,dt.second);

 Serial.print("temp: ");
 Serial.println(temperaturInC); 

 Serial.print("hum: ");
 Serial.println(feuchtigkeit);

 Serial.print("poti_value: ");
 Serial.println(poti_value);

 Serial.print("DruckWert: ");
 Serial.println(druckWert);

 sprintf(buf2,"%02d:%02d:%02d",dt.hour,dt.minute,dt.second);

 String value_timestamp = "\"time\": "+ String(buf2);
 String value_tmp = "\"temperature\": "+ String(temperaturInC);
 String value_hum = "\"humidity\": "+ String(feuchtigkeit);
 String value_druck = "\"pressure\":"+ String(druckWert);

  value_timestamp =  value_timestamp+value_tmp+value_hum+value_druck;
  
  
  String payload =  "{" + value_timestamp + "}";
  payload.toCharArray(data, (payload.length() + 1));
  client.publish("message", data);

 
 delay(1000);
 client.loop();


  
  
}
