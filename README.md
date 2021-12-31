# Projekt 3 z predmetu BPC-BDS
Tento repozitár bol vytvorený ako súčasť 3. projektu z BPC-BDS. Projekt sa zameriava na správu úživateľov a systém rezervovania leteniek pre užívateľov. Do aplikácie sa vieme prihlásiť bud ako admin alebo ako pasazier a to pomocou hesla a e-mailu. Všetky potrebné dependecies sa nachádzajú v `site/dependecies.html`. Tento súbor bol vygenerovaný pomocou mavenu.
# Backups
Na vytvorenie záloh sa používa `backup.sh`, ktorý pridáme ako *crontab -e* a následne nastavíme kedy sa má spúšťanie záloh vykonávať ako `0 0 * * * path/bds-airport-gui/backup.sh`
# Požiadavky
- Git
- PostgreSQL
- Java OpenJDK 11 or newer
- Maven 3.8.4
# Spustenie
- `git clone https://github.com/tearexJS/bds-airport-gui.git`
- `cd bds-airport-gui/bds-airport`
- `mvn clean install`
- `cd target/`
- `java -jar bds-airport-1.0.0.jar`
