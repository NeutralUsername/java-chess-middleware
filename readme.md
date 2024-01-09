# chess
-Fabian Stuck (ic22b113@technikum-wien.at)

## requirements:

Must Have Features
 
- server welcher zwei zugehörigen frontends erlaubt, gegeneinander schach zu spielen.
- verbindung/matchmaking wird automatisch beim starten der frontends im hintergrund durchgeführt.
- repräsentation des spielfelds über textblock im ui. züge per text-feld input und button druck zum senden.
- simple version der schach regeln. keine promotion, kein castling, kein en passant.
- kein doppelzug. neues spiel wird gestartet indem server + frontends neu gestartet/geöffnet werden.

Should have features

- Beim start des frontends wird jedem client eine id zugewiesen.
- mit dieser id kann man sich zu anderen aktiven clients verbinden und ein spiel starten.
- komplexere schach regeln (queen promotion, castling, en passant, doppelzug).
- id kann über mouseclick in die zwischenablage kopiert werden um sie einfacher zu teilen.
 
Nice to have features

- spiel kann über button druck beendet werden und man wird anschließend zurück in die lobby gebracht, wo man sich erneut mit anderen spielern verbinden kann.
- middelware architektur, die es ermöglicht, das system einfach mit neuen funktionalitäten zu erweitern.
- repräsentation des spielbretts als formatierter raster aus quadraten mit entsprechendem symbol um die spielfiguren eindeutig zu indentifizieren.
- speichern der züge in standard algebraischer schach notation.

Overkill

- der server kann "theoretisch unendlich viele" (abhängig von maximaler anzahl an threads und u.u anderen constraints) spiele gleichzeitig managen.
- drag und drop steuerung um züge zu machen.
- highlighting des quadrats das gerade gedragged wird und jenes, über welches gerade gedragged wird.
- individueller timer für beide spieler. nach ablauf ist kein zug mehr gültig. timer läuft nur für den spieler, der gerade an der reihe ist.


## grobe übersicht der funktionalität

die middleware nutzt die java-chess-logic und speichert den aktuellen zustand der spiele im arbeitsspeicher und ordnet die socket-verbindungen den spielern zu.

Das frontend verbindet sich zur middleware über java sockets(tcp).
die spieler sehen am frontend eine repräsentation des aktuellen spielbretts und können dort über drag und drop züge veranlassen.
die middleware überprüft daraufhin die legalität des zugs und führt ihn gegebenenfalls aus und propagiert die änderung zum frontend und der andere spieler ist an der reihe.

spieler können zu jedem zeitpunkt das spiel über einen knopf verlassen.
wenn beide spieler das spiel verlassen haben gibt es keine referenz mehr zum spiel und es wird dann irgendwann von der garbage-collection entfernt.

unmittelbar nach dem verlassen des spiels kann ein neues spiel gestartet werden, ohne das frontend programm neu zu starten.


