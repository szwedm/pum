Date format to use in project: YYYY-MM-DD (as String)
  
Java version: 1.8.0_242  
Android Gradle plugin version: 4.0.1  
Gradle version: 6.3  
Compile SDK version: 29 (Android 10.0 (Q))  
Build tools version: 29.0.3  
Target SDK version: 27  
Min SDK version: 23  
  
--- Pobieranie danych z DB ---  
Dla kazdego modelu (Player, Team, Match) utworzylem odpowiedni DAO (PlayerDao, TeamDao, MatchDao).  
DAO pozwala wykonywac operacje na DB, czyli w celu uzyskania obiektu z DB nalezy posluzyc sie DAO.  
Kazda metoda w DAO opatrzona adnotacja "@Query" sluzy do pozyskania obiektu/ow z DB.  
Przykladowo, metoda z PlayerDao:  
  
    @Query("SELECT * FROM player")  
    List<Player> getAll();  
  
Zwroci liste wszystkich zawodnikow (List<Player>) z tabeli player.  

Jak wywolac te metode w activity?  
  
    db.playerDao().getAll();  
  
czyli potrzebny jest obiekt bazy danych (db).  
Obiekt ten moze odwolac sie do odpowiedniego DAO (db.playerDao()),  
a nastepnie wykonac metode z DAO (db.playerDao().getAll()).  

Z takiej listy najlepiej utworzyc obiekt ArrayList<Player>:  
  
    ArrayList<Player> players = new ArrayList<>(db.playerDao().getAll());  
  
to pozwoli na odwolywanie sie do obiektow "player" za pomoca kolekcji ArrayList.