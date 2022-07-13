# TenMinutes!
Ein Plugin zur Challenge "MINECRAFT ABER 10 MINUTEN = NEUER YOUTUBER" von BastiGHG.
https://www.youtube.com/user/kompetenzGHG

# Regeln
Die Regeln sind simpel. Die Spieler dürfen sich nicht über Strategien, den aktuellen Fortschritt oder sonstiges unterhalten, während das Projekt läuft.

10 Minuten hat jeder Spieler Zeit, um voran zu kommen. Danach wird dieser vom Server geworfen und der nächste Spieler darf joinen.
Wurde Minecraft nicht während eines Spieler-Zlykus durchgespielt, wird die Reihenfolge neu gemischt und es geht von vorne los.

# Installation
1. Lade dir die neueste Version des Plugins [hier](https://github.com/LittleKing205/TenMinutesPlugin/releases) herunter.
2. Sobald der Download abgeschlossen ist, musst du die .jar in den plugins Ordner deines Server verschieben.
3. Damit du das Plugin vollständig einrichten kannst, musst du deinen Server jetzt neu starten.
4. Wenn du nicht weißt, was du tust, folge den Schritten in [Konfigurations-Anleitung](https://github.com/LittleKing205/TenMinutesPlugin/blob/master/README.md#konfiguration)

# Konfiguration
**den Discord Bot erstellen**

Damit du und deine Mitspier erinnert werden können, musst du unseren Discord Bot einrichten.
1. Erstelle dir dazu [hier](https://discord.com/developers/applications) eine neue Applikation.
2. Du wirst in diesem Fenster nun aufgefordert, der Applikation einen Namen zu geben. Diesen Namen wird der Bot später übernehmen.
3. Um jetzt auch den Bot erstellen zu können, klicke in der Seitenleiste auf "Bot", dann auf Add Bot und bestätige deine Aktion.
4. Navigiere in den Ordner plugins und öffne die config.yaml. Generiere jetzt den Bot Token neu und füge ihn in der config.yaml ein.
```yaml
discordBotToken: "DEIN TOKEN"
```
**Discord einrichten und konfigurieren**

1. Solltest du noch keinen Discord Server haben, erstelle dir einen neuen.
2. Erstelle folgende Rollen:


```yaml
discordBotToken: "DEIN BOT TOKEN"
registredRole: "ROLLE FÜR NEUE SPIELER"
activeRole: "ANGEMELDETE SPIELER"
serverId: "DEINE SERVERID"
playerAutoActive: true / false
```
