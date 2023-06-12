# Paradiesspiel
# Laboraufgaben SoSe 2023
Im Labor „Programmieren“ werden aufeinander aufbauende Aufgaben gestellt, die insgesamt auf 4 Serien verteilt werden. Die Aufgaben unterstützen Sie beim Erlernen des Programmierens in Java. Dabei wird sukzessive ein Spiel implementiert, welches durch das Abspeichern und Laden von Spielzuständen ergänzt und durch eine GUI erweitert wird.
Für dieses Labor können Sie maximal 10 Punkte erhalten.
Die Aufgaben sollen in Zweiergruppen bearbeitet und präsentiert werden. Die Anmeldung zum Labor erfolgt über Moodle.
Zum Austausch des Codes innerhalb der Gruppe verwenden Sie das bereitgestellte SVN- Repository, welches unter dem Link https://code.ostfalia.de/svn/i-prog-ss2023/GruppeXXX/ (wobei XXX die dreistellige Gruppennummer ist) erreichbar ist. Eine Anleitung zu SVN ist in Moodle zu finden.
Das Labor ist vorrangig für Abnahmegespräche gedacht. Bitte bereiten Sie Ihre Lösung entsprechend vor. Wenn Sie Verständnisschwierigkeiten haben (egal ob es Java oder aufgabenbezogen ist), nutzen Sie am besten die Informatik-Lounge (Termine siehe Stundenplan), um Ihre Fragen zu klären.
Legen Sie bitte für jede Aufgabenserie ein eigenes Projekt mit dem Projektnamen "ProgSS23Ax" an, wobei x die Aufgabennummer ist und 𝑥𝑥 ∈ {1, ... ,4}. Ihr Code liegt im Unterordner src. Als Paketnamen für die Implementierung verwenden Sie "de.ostfalia.prog.ss23" und für die Testfälle "de.ostfalia.prog.ss23.test".
Um Ihre eigene Fortschritte während der Implementierung zu überprüfen, stellen wir JUnit4- Testfälle zur Verfügung.
Alle Code-Einreichungen in Moodle und im SVN werden mit einer Plagiatserkennungssoftware verglichen und bei Verdacht von einem Mitarbeiter überprüft. Sollten Code-Einreichungen als Plagiate identifiziert werden, wird die Abnahme von allen Beteiligten für die betroffene Serie aberkannt.
# Vorgeschichte
Als Vorlage diente uns das Spiel „Das Gänsespiel", welches eines der ältesten Brettspiele Europas ist und dessen Urform womöglich in Altägypten zu finden ist.
Das seit 3000 v. Chr. überlieferte Spiel Mehen (zu Deutsch „eingerollt“), dessen kreisrundes Spielbrett die Form einer eingerollten Schlange hat, wird als Vorläufer des Gänsespiels verstanden. Das Gänsespiel, wie man es heutzutage kennt, ist in Europa wahrscheinlich seit dem 15. Jahrhundert bekannt.
Abbildung 1 - Quelle: https://www.tradgames.org.uk/features/featured-games.htm
Das ursprüngliche Spiel handelt von Lebewesen, die ins Paradies kommen wollen. Die Abfolge der Felder kann als Abbild des menschlichen Lebensweges interpretiert werden, der von einem ungewissen Schicksal bestimmt wird und auf dem sich Glück und Pech, Vorwärtskommen und Zurückfallen, Gewinn und Verlust stetig abwechseln, allerdings immer mit dem Ziel, das sogenannte Paradies zu erreichen.
Das Spiel gibt es in unterschiedlichen Varianten mit den unterschiedlichsten Figuren. Im Internet finden Sie diverse Beispiele, wie das Spiel bzw. der Spielplan aussehen kann. Die Regeln und die Ereignisse der Felder variieren auch stark.
Die hier zu implementierende Simulation ist eine angepasste Version, welche verschiedene Spielregeln vereinbart. Das von uns angepasste Spiel haben wir „Paradiesspiel“ genannt.
# Das Paradiesspiel
Paradiesspiel ist ein Spiel, welches von 2 bis 6 Personen mit zwei Würfeln gespielt wird. Jeder Spieler bekommt eine Farbe zugewiesen und erhält am Anfang in der Regel 2 Figuren. Gewonnen hat der Spieler, der zuerst alle seine Figuren im Paradies hat.
# Würfel
Es wird mit zwei unterschiedlichen Würfeln gespielt:
Würfel 1: entscheidet welcher Spieler an der Reihe ist und beinhaltet dafür auf jede Seite eine Farbe. Um unnötige Würfe zu vermeiden, ist die Anzahl der Würfelseiten gleich der Anzahl der Spieler.
Würfel 2: besteht aus Zahlen von 1 bis 6 und gibt an, wie viele Felder sich die Figur, die bespielt werden soll, bewegen soll (in der Regel vorwärts). Bei jedem Zug wird zweimal mit diesem Würfel gewürfelt.
# Figur
Die Figuren bewegen sich auf dem Spielbrett, indem sie in der Regel von Feld zu Feld vorwärts rücken. Ausschlaggebend für die Bewegung sind sowohl die zufällig gewürfelten Augenzahlen als auch das Ereignis, welches mit dem Feld gekoppelt ist, wo die Figur ihre Bewegung beendet (das Zielfeld).
Um die automatische Testbarkeit zu gewährleisten, müssen die Figuren standardisierte Namen tragen. Die Figuren der Farbe BLAU müssen daher „BLAU-A“ und „BLAU-B“ heißen, der Farbe ROT, „ROT-A“ und „ROT-B“. Alle weiteren Farben sind analog zu benennen.
# Spielbrett
Das Spielbrett besteht aus 64 Feldern. Darunter: • Startfeld
• Paradies
• Ereignisfelder • Standardfelder
# Startfeld
Das Startfeld liegt in der Regel auf Feld Nr. 0.
Zu Beginn des Spiels starten alle Spieler mit all ihren Figuren außerhalb des Weges auf einem Startfeld.
# Paradies
Das Paradies liegt in der Regel auf Feld Nr. 63.
Ziel der Reise ist das Paradies. Figuren, die es dorthin geschafft haben, können es nicht mehr verlassen. Das Paradies kann allerdings nur mit einem direkten Wurf erreicht werden. Wenn die gewürfelte Augenzahl höher ist, als für den Einzug ins Paradies benötigt wird, muss die Figur die restlichen Zahlen vom Paradies zurückziehen.
Zum Beispiel:
Figur A ist auf Feld Nr. 61 und würfelt eine 5: Die Figur landet am Ende des Zuges auf dem Feld Nr. 60 (616263626160).
# Ereignisfelder
Manche Felder bringen gewisse Ereignisse mit sich, welche wie folgt definiert sind: • Pech
Die „Pech“-Felder befinden sich in der Regel auf Position 5 und 9
Aktion: die Figur muss um die gewürfelten Augenzahlen zurückgezogen werden.
• Brücke
Das „Brücke“-Feld befindet sich in der Regel auf Position 6
Aktion: in der Regel führt dieses Feld dazu, dass die Figur insgesamt 6 Felder auf direkten Weg überspringt, ohne es dabei als Schritt mitzuzählen. Dieser Bonus tritt allerdings nur in Kraft, wenn die Figur sich vorwärtsbewegt. Dabei ist es ganz egal, ob das „Brücke“- Feld Ziel der Bewegung war (siehe Beispiel a) oder nicht. Sollte das Feld nicht das Ziel sein, so wird die Bewegung von dort normal weitergeführt (siehe Beispiel b). Sollte die Figur sich dennoch rückwärts bewegen, tritt der Bonus nur in Kraft, wenn das „Brücke“- Feld das Ziel der Bewegung war (siehe Beispiel c), ansonsten wird die Bewegung der Figur normal weitergeführt (siehe Beispiel d). Beachten Sie, dass das „Brücke“-Feld das einzige ist, dessen Aktion sofort ausgeführt wird, auch wenn die Figur das erwartete Ziel noch nicht erreicht hat.
Beispiele:
(a) Figur A ist auf Feld Nr. 4 und würfelt eine 2: Die Figur landet am Ende des Zuges auf
dem Feld Nr. 12 (456 / 12).
(b) Figur A ist auf Feld Nr. 4 und würfelt eine 5: Die Figur landet am Ende des Zuges auf
dem Feld Nr. 15 (456 / 12131415).
(c) Figur A ist auf Feld Nr. 15 und würfelt eine 9: Die Figur landet am Ende des Zuges
wegen eines weiteren Ereignisfeldes auf dem Feld Nr. 12, obwohl die Brücke eigentlich
das Ziel der Bewegung war (1516...2423...76 / 12).
(d) Figur A ist auf Feld Nr. 13 und würfelt eine 11: Die Figur landet am Ende des Zuges wegen eines weiteren Ereignisfeldes auf dem Feld Nr. 2. Die Brücke führt zu keinem besonderen Ereignis, zumal sie nicht das Ziel der Bewegung war. (13  14  ...  24
23...765...2).
• Glück
Die „Glück“-Felder befinden sich in der Regel auf Position 14, 18, 27, 32, 36 und 50 Aktion: die Figur darf um die gewürfelten Augenzahlen vorrücken
• Labyrinth
Das „Labyrinth“-Feld befindet sich in der Regel auf Position 19
Aktion: wer sich im Labyrinth verirrt, muss erstmal wieder zu Kräfte kommen. Der Spieler muss eine Runde aussetzen.
• Desaster
Die „Desaster“-Felder befinden sich in der Regel auf Position 24, 41 und 54
Aktion: die Figur muss um den doppelten Wert der gewürfelten Augenzahlen zurückgezogen werden.
• Aufschwung
Das „Aufschwung“-Feld befindet sich in der Regel auf Position 52
Aktion: wenn die Figur mit zwei Sechsern das Feld erreichte, darf sie direkt ins Paradies ziehen ansonsten zieht die Figur wie gewohnt weiter.
• Neuanfang
Das „Neuanfang“-Feld befindet sich in der Regel auf Position 58 Aktion: das Leben ist hart. Die Figur muss zurück zum Startfeld.
# Standardfelder
Alle übrigen Felder sind gewöhnliche Felder auf dem Weg ins Paradies und ohne besondere Eigenschaften.
# Spielstart
Nachdem in Erfahrung gebracht wurde, wie viele Spieler spielen möchten, wird jedem eine Farbe zugewiesen, und zwar der Reihe nach, wie sie in der Aufzählungsklasse Farbe vorkommen. Darüber hinaus werden alle Figuren in der Regel auf dem Feld Nr. 0 positioniert. Sie können dennoch, wenn es erforderlich ist, auf einem beliebigen Feld positioniert werden.
# Spielverlauf
Spieler am Zug: Ein beliebiger Spieler würfelt zuerst mit dem Farbenwürfel. Es ist dabei egal, welcher Spieler würfelt, zumal der Würfel anhand der Farbe zufällig entscheidet, welcher Spieler spielen darf.
Augenzahl: Der Spieler am Zug würfelt anschließend zweimal mit dem Zahlenwürfel. Die Addition der zufällig gewürfelten Augenzahlen gib an, wie viele Felder (i.d.R.) vorwärts eine Figur sich bewegen soll.
Figur: Der Spieler am Zug entscheidet, welche Figur bespielt werden soll, und bewegt sie so viele Felder (i.d.R.) nach vorne, wie die Addition der Augenzahlen es hergibt. Es ist als selbstverständlich zu verstehen, dass ein Spieler nur seine eigenen Figuren bewegen darf. Sollte er „fremde“ Figuren bewegen wollen, gilt der Zug als vollzogen ohne dass Figuren sich bewegt haben.
Ereignis: Wenn die Figur auf ein Ereignisfeld kommt, wird das Ereignis, das auf dem Feld beschrieben ist, umgesetzt, sofern es sich um das Ziel der Figur handelt. Die einzige Ausnahme hier ist das „Brücke“-Feld, dessen Ereignis sofort umgesetzt wird, ungeachtet dessen, ob die Figur ihr Ziel bereits erreicht hat oder nicht. Bedenken Sie, dass durch ein Ereignisfeld eine Kettenreaktion ausgelöst werden kann.
Wenn alle Aktionen erledigt sind, wird erneut mit dem Farbenwürfel gewürfelt und die oben beschriebenen Aktionen wiederholten sich.
# Spielende
Gewonnen hat der Spieler, der als erster alle seine Figuren ins Paradies gebracht hat. Nachdem der erste Spieler gewonnen hat, steht der Gewinner fest und alle weiteren Züge haben keinen Einfluss mehr auf das Spielergebnis.

# Laboraufgaben
Das oben beschriebene Spiel wird während des Semesters sukzessive implementiert. Es ist daher wichtig, beim Entwurf darauf zu achten, die Implementierung offen für Erweiterungen und Änderungen zu halten. Insbesondere sollen einige Spieleigenschaften – wie die Startposition der Figuren – beim Spielstart anpassbar sein.
# Aufgabe 1 – Figuren bewegen sich und berücksichtigen einen Teil der Ereignisfelder Ziel: Spieler können ihre Figuren durch das Spielbrett in Richtung Paradies bewegen.
Teilimplementierung des Spiels
• Implementieren Sie das Interface IParadiesspiel sowie die zugehörige Klasse Paradiesspiel in das zuvor erstellte Projekt. Die Klasse Paradiesspiel muss mit den zwei Konstruktoren vervollständigt werden, welche im Interface als Kommentar zu finden
sind:public Paradiesspiel(Farbe... farben)
public Paradiesspiel(String conf, Farbe... farben)
• Implementieren Sie darüber hinaus geeignete Klassen, so dass das Spiel wie oben beschrieben gespielt werden kann.
• Das „Paradies“-Feld ist vollständig zu implementieren.
• Setzen Sie die Programmierung des Spiels fort, indem Sie folgende Ereignisfelder
implementieren: o Brücke
o Glück
o Aufschwung
• Figuren können entlang des Weges auf dem Spielbrett gezogen werden.
• Der Gewinner kann ermittelt werden.
• Das Spiel kann auf einer Konsolenausgabe gespielt werden.
# Aufgabe 2 – Figuren berücksichtigen weitere Ereignisfelder und behandeln Ausnahmen
Ziel: Vererbungshierarchie der Felder erweitern und Ausnahme behandeln.
Weiterimplementierung des Spiels
• Erstellen Sie ein neues Projekt nach den bereits bekannten Vorgaben.
• Erweitern Sie das Projekt um das Paket de.ostfalia.prog.ws22.exceptions
• Setzen Sie die Programmierung des Spiels fort, indem Sie folgende Ereignisfelder
implementieren: o Pech
o Desaster
o Neuanfang
• Erweitern Sie die Implementierung, indem Sie folgende Ausnahmen implementieren:
o FalscheSpielerzahlException extends Exception: für den Fall, dass die Anzahl der Spieler nicht zwischen 2 und 6 ist
o UngueltigePositionExceptionextendsRuntimeException:fürdenFall,dass eine Figur zu Beginn auf einem nicht zulässigen Feld positioniert werden soll. Unzulässige Felder sind die, die auf Grund des damit verbundenen Ereignisses keine Figur beibehalten können (zum Beispiel das „Glück“-Feld).
• Das Spiel kann nach wie vor auf einer Konsolenausgabe gespielt werden.
# Aufgabe 3 – Spiel vervollständigen bzw. personalisieren und Datei lesen/schreiben Ziel: Datei lesen/schreiben.
Weiterimplementierung des Standardspiels
• Erstellen Sie ein neues Projekt nach den bereits bekannten Vorgaben.
• Setzen Sie die Programmierung des Spiels fort, indem Sie folgendes Ereignisfeld
implementieren: o Labyrinth
• Vervollständigen Sie die Implementierung, indem die Klasse Paradiesspiel nun auch das Interface ISpeicherbar implementiert. Es sind alle Methoden aus dem Interface zu überschreiben und mit geeigneten Implementierung zu versehen. Wählen Sie ein Datenformat aus und untersuchen Sie, welche Information dieses enthalten muss.
• Implementieren Sie darüber hinaus folgende Ausnahme:
o DateiLeerException extends RuntimeException: für den Fall, dass die Datei
leer ist (kein Inhalt) und daher für das Laden eines Spiels unbrauchbar.
• Das Spiel kann nach wie vor auf einer Konsolenausgabe gespielt werden.
Neue Implementierung des Spiels in einer angepassten Variante
• Erstellen Sie ein neues Spiel, welches eine angepasste Version des nun fertiggestellten Standardspiels darstellt. In dieser, nennen wir es „Sommeredition“ gelten folgende Regeln:
o Hauptklasse: ParadiesspielSommer
o Figur: jeder Spieler spielt mit 3 Figuren
o Das Spielbrett besteht aus 71 Felder.
o Das „Paradies“-Feld ist nach wie vor das letzte Feld.
o Das „Brücke“-Feld wird zusätzlich zu seiner regulären Position auch noch auf dem
Feld Nr. 42 positioniert.
o Das „Labyrinth“-Feld wird zusätzlich zu seiner regulären Position auch noch auf
Feld Nr. 46 positioniert.
o Das „Glück“-Feld auf Position Nr. 18 entfällt
# Aufgabe 4 – GUI implementieren
Ziel: Entwicklung einer graphischen Oberfläche.
Weiterimplementierung des Spiels
• Erstellen Sie ein neues Projekt nach den bereits bekannten Vorgaben.
• Implementieren Sie mit dem Framework JavaFX eine graphische Benutzeroberfläche für die Standard Spielsimulation. Dieses muss, soweit es noch nicht geschehen, mit der zur Java Version passenden Version nachträglich installiert werden. Für die Erstellung des GUI-Layouts kann der grafische WYSIWYG-Editor Scene Builder von Oracle oder GluonHQ
verwendet werden.
• In diesem Aufgabeteil soll ein Hauptfenster für eine GUI implementiert werden, welche
mindestens folgende Funktionalitäten anbietet:
o Die Anzahl der Spieler kann ausgewählt werden
o Eine graphische Anzeige des Spielfeldes ist erkennbar
o Farbe am Zug und die gewürfelten Augenzahlen sind erkennbar
• Vervollständigen Sie die graphische Benutzeroberfläche für die Spielsimulation so, dass o die Figuren sich auf dem Spielfeld bewegen
o die zu ziehende Figur ausgewählt werden kann
o der Gewinner angezeigt wird
o das Spiel geladen/gespeichert werden kann
• Beachten Sie, dass für die Realisierung der GUI das MVC-Pattern verwendet werden
muss.
• Beachten Sie darüber hinaus, dass die bereits fertiggestellte Spiellogik durch die GUI
nicht verändert werden soll.
• Das Spiel muss sowohl via Konsolenausgabe als auch via graphische Benutzeroberfläche
gespielt werden können.
