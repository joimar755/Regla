int pin_led = 27;  // Pin del LED

void setup() {
  Serial.begin(9600);      // Comunicación serial a 9600 baudios
  pinMode(pin_led, OUTPUT);
  digitalWrite(pin_led, LOW);
}

void loop() {
  if (Serial.available() > 0) {
    char dato = Serial.read();  // Leer el dato enviado desde Java

    if (dato == '1') {
      digitalWrite(pin_led, HIGH);   // Encender LED
      Serial.println("LED ENCENDIDO");
    }
    else if (dato == '0') {
      digitalWrite(pin_led, LOW);    // Apagar LED
      Serial.println("LED APAGADO");
    }
  }
}
