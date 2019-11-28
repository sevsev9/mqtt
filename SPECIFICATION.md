## Ausganssituation
Wir haben (detaillierte) Informationen über MqTT und folgende Tools stehen uns zur Verfügung:


- "Generator" für Publisher
- Realisierungen eines Subscribers in JodeJS & Java
- detaillierte Infos über Aufbau von Topics / ErrorHandling
- Subscriber kann Daten von Topics persistent halten
Aufgabenstellung
Nun soll in einem gemeinsamen Projekt folgendes gelöst werden:

HW-Simulator
TinkerCAD

## Publisher
Der Publisher generiert pro festzulegende Zeiteinheit (z.B. alle 10 sec) Daten (Öldaten  - Pipeline (!)); alle dabei verwendeten Daten werden beim Generieren einer neuen Message verändert (d.h. beispielsweise, dass die Temperatur schwankt) - Zufallsgenerator
 Es ist festzulegen, wenn vom Publisher ein Alarm/Fehler ausgelöst wird (z.B. Druckabfall, Ausfall eines Sensors)  Alarm-Topic! / Zufallsgenerator
Zu überlegen ist auch die Berücksichtigung einer minimalen Schwankungsbreite (z.B. wenn die Werte nicht mehr als 5 % schwanken muss anstatt dem 10sec-Intervalls nur alle 180/240 sec eine Message generiert werden.
Ein Generator, der dies alles erfüllt, wird mindestens 3 mal gestartet.
Der Broker ist auswechselbar, primär wird jedoch mit dem docker.htl-wels.at-Brocker gearbeitet.

## NodeJS/REsT-Service
Eine NodeJS-basierte Express-Application wird als Subscriber für alle  Sensordaten (ohne Fehlermeldungen) eingesetzt, wobei die Daten in einer JSON-Datei zu speichern sind. Später soll dann eine MongoDB eingesetzt werden. Der Express-Server stellt ein REST-API zur Verfügung, der auf Anfrage alle innerhalb eines bestimmten Zeitraums anfallenden Sensordaten zur Verfügung stellt. Die Anfragen können nur berechtigtige User durchführen. Die Sensordaten sind ebenfalls selektierbar.

## Java/REsT-Service für Alarme
Eine Java-basierte Application wird als Subscriber für alle Alarme/Fehler eingesetzt. Der Java-HTTPServer stellt ein REST-API zur Verfügung, der auf Anfrage alle innerhalb eines bestimmten Zeitraums anfallenden Alarme/Fehler zur Verfügung stellt (Fehler, Datum, Meldestelle); Die Fehler sind in einer relationalen DB zu speichern.

## WebClient
Als WebClient wird eine auf VUE basierte App eingesetzt, welche den Dialog mit dem WebServer auf einfache WEise gestattet. Das Styling ist anfangs recht einfach zu gestalten & ist später von Bootstrap abzulösen.

## Organisatorisches
Es ist für das Projekt ein gemeinsames GitHub-Repo einzurichten und ALLE (ausser HW-SImulator) liefern in dieses Repo.
Als Datenstruktur im Remote-Traffic wird JSON eingesetzt - die notwendigen Strukturen sind zu definieren.
