# TenMinutes!
Ein Plugin zur Challenge "MINECRAFT ABER 10 MINUTEN = NEUER YOUTUBER" von BastiGHG.
https://www.youtube.com/user/kompetenzGHG

# Regeln
Die Regeln sind simpel. Die Spieler dürfen sich nicht über Strategien, den aktuellen Fortschritt oder sonstiges unterhalten, während das Projekt läuft.

10 Minuten hat jeder Spieler Zeit, um voran zu kommen. Danach wird dieser vom Server geworfen und der nächste Spieler darf joinen.
Wurde Minecraft nicht während eines Spieler-Zlykus durchgespielt, wird die Reihenfolge neu gemischt und es geht von vorne los.

# Installation
1. Lade dir die neueste Version des Plugins [hier](https://github.com/LittleKing205/TenMinutesPlugin/releases/latest) herunter.
2. Sobald der Download abgeschlossen ist, musst du die .jar in den plugins Ordner deines Server verschieben.
3. Damit du das Plugin vollständig einrichten kannst, musst du deinen Server jetzt neu starten.
4. Wenn du nicht weißt, was du tust, befolge den Anweisungen in der [Konfigurations-Anleitung](https://github.com/LittleKing205/TenMinutesPlugin/blob/master/README.md#konfiguration)

# Konfiguration
**den Discord Bot erstellen**

Damit du und deine Mitspieler erinnert werden können, musst du unseren Discord Bot einrichten.
1. Erstelle dir dazu [hier](https://discord.com/developers/applications) eine neue Applikation.
2. Du wirst in diesem Fenster dazu aufgefordert, der Applikation einen Namen zu geben. Diesen Namen wird der Bot später übernehmen.
3. Um jetzt auch den Bot erstellen zu können, klicke in der Seitenleiste auf *Bot*, dann auf *Add Bot* und bestätige deine Aktion.
4. Navigiere in den Ordner *plugins* und öffne die *config.yaml*. Generiere jetzt den Bot Token neu und füge ihn in die *config.yaml* ein.
```yaml
discordBotToken: "DEIN TOKEN"
```
**Discord einrichten und konfigurieren**

1. Solltest du noch keinen Discord Server haben, erstelle dir einen neuen.
2. Lade deinen Discord Bot auf den eben erstellten Discord Server ein.
- Klicke im [Developerportal](https://discord.com/developers/applications) auf deine Applikation
  - Gehe dann zu OAuth2 und auf den URL Generator
    - Bei den Scopes wählst du Bot aus. Folgende Berechtigungen musst du dem Bot geben, damit er funktionieren kann:

    | Berechtigung | Beschreibung |
    | ------------ | ------------ |
    | Manage Roles |  |
    | Send Messages | platzhalter |
4. Erstelle eine Rolle, die dem Bot hilft, die Discordnamen mit den Minecraftnamen der Spieler zu verknüpfen. Trage den Namen der Rolle in die *config.yaml* ein. Diese Rolle musst du aber nicht erstellen, dass macht der Bot automatisch, wenn du bei *registredRole* nichts eingibst.
```yaml
registredRole: "Verknüpft"   # eigene Rolle
registredRole: ""            # automatisch erstellte Rolle
```
4. Du benötigst noch eine zweite Rolle für Spieler, die in der Warteschlange auf ihren nächsten Versuch warten. Erstelle dazu eine Rolle und füge den Namen der Rolle bei *activeRole:* in die *config.yaml* ein oder lasse *activeRole:* frei, damit der Bot die Rolle selbst erstellt.
```yaml
activeRole: "Warteschlange" # eigene Rolle
activeRole: ""              # automatisch erstelle Rolle
```
Du kannst dich hierbei noch entscheiden, ob du alle oder nur ausgewählte Spieler mitspielen lassen möchtest.
```yaml
playerAutoActive: true    # alle Spieler
playerAutoActive: false   # ausgewählte Spieler
```
- Spieler whitelisten
  - Solltest du *playerAutoActive* auf *false* setzen, kannst du auf deinem Discord Server Spieler mit dem Befehl */active add Discordname* Spieler zur Warteschlange hinzufügen.


Deine config.yaml sollte am Ende Beispielweise so aussehen. Natürlich musst du deine eigenen Werte eintragen und auf keinen Fall unsere 1:1 übernehmen.
```yaml
discordBotToken: "OTk2ODc3NzM5ODUwMjE5NTQy.GJCxX_.eV3QktOWlziJbPgjy7dQX_9djWtOv6Wt2FgljE"
registredRole: "Wartet"
activeRole: "Teilnehmer"
playerAutoActive: true
```
