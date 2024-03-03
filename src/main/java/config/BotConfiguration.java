package config;

public class BotConfiguration {

    public static String HELP_TEXT = """
            **🤖​⚡MANUALE BotTurboCoinBot⚡🤖​**
            BotTurboCoinBot è un bot realizzato da ​🧑🏻‍💻​*Hussain Mohammad Umar*, studente dell' Istituto di Istruzione Superiore Luigi Cerebotani​🏫.
            Questo bot serve per aiutare gli utenti ad avere accesso a diverse informazioni grazie ai specifici comandi preimpostati.
            🧙‍♂️​I comandi disponibili fino ad ora sono:
            ​*👋​/ciao*: per salutare il bot;
            ️​*🌤/meteo*: Ritorna le condizioni atmosferiche della località selezionata;
            *🍹/cocktail*: Ritorna le informazioni del cocktail selezionato;
            *​📃/circolari*: Ritorna l'ultima circolare pubblicata dall' Istruzione Superiore Luigi Cerebotani;
            *​📰/news*: Ritorna le ultime notizie italiane;
            *📷​/img*: Ritorna un'immagine;
            *📧​/tempmail*: Genera una email temporanea utilizzabile;
            *📜​/quote*: Genera un quote casuale;
            *🔎​/nmap*: Cerca tutte le porte attive di un indirizzo ip;
            *🎵​/mp3*: Converte i link di youtube in mp3;
            *🎼​ /lyrics*: Cerca i lyrics di una canzone scelta;
            *🎮​ /game*: Avvia un gioco;
            *🦠​ /hack*: hackera il sistema di prenotazione di una biblioteca.
            Per conoscere le caratteristiche dei comandi nei dettagli scrivere il comando /help dopo l'istruzione.
            """;
    
    public static String METEO_HELP_TEXT = """
            **​⚡MANUALE meteo⚡​**
            Comando che serve per controllare le condizioni meteorologiche di una città.\nI comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */nomeDellaLocalitàScelta*: Il bot risponde con tutte le informzioni sulla Meteorologia del luogo scelto;
            */close*: Il bot non controlla o risponde ai comandi riguardanti il meteo.
            """;
    
    public static String COCKTAIL_HELP_TEXT = """
            **​⚡MANUALE cocktail⚡​**
            Comando che serve per comunicare con il DataBase dei cocktail. 
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */random*: Il bot risponde con un cocktail randomico;
            */ingredienti*: Il bot risponde con la lista di tutti gli ingredienti utilizzati per la produzione dei cocktail;
            */tutti*: Il bot risponde con la lista di tutti i cocktail che riesce a procurarsi;
            */close*: Il bot non controlla o risponde ai comandi dei cocktail.
            """;
    
    public static String CIRCOLARI_HELP_TEXT = """
            **​⚡MANUALE circolari⚡​**
            Comando che serve per controllare le circolari presenti nel sito della scuole.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */ultima*: Il bot risponde con l'ultima circolare pubblicata;
            */ataEDocenti*: Il bot risponde con l'ultima circolare pubblicata indirizzata al personale Ata e ai Docenti;
            */studenti*: Il bot risponde con l'ultima circolare pubblicata indirizzate alle famiglie degli studenti;
            */close*: Il bot non controlla o risponde ai comandi delle circolari.
            """;
    
    public static String NEWS_HELP_TEXT = """
            **​⚡MANUALE news⚡​**
            Comando che serve per controllare le ultime notizie in italia.
            Sono divise in categorie, in base alle tue preferenze.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */cronaca*: Il bot risponde con l'ultima notizia di cronaca;
            */economia*: Il bot risponde con l'ultima notizia del settore economico;
            */intrattenimento*: Il bot risponde con l'ultima notizia del settore dell'intrattenimento;
            */salute*: Il bot risponde con l'ultima notizia nell'ambito della salute;
            */scienza*: Il bot risponde con l'ultima notizia del settore della scienza;
            */sport*: Il bot risponde con l'ultima notizia del settore dello sport;
            */tecnologia*: Il bot risponde con l'ultima notizia del settore della tecnologia;
            */close*: Il bot non controlla o risponde ai comandi delle news italiane.
            """;
    
    public static String IMG_HELP_TEXT = """
            **​⚡MANUALE img⚡​**
            Comando che serve per cercare le immagini di un argomento.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */cosavuoicercare*: Il bot risponde con la prima immagine che trova per largomento scelto;
            */close*: Il bot non controlla o risponde ai comandi delle img.
            """;
    
    public static String MAIL_HELP_TEXT = """
            **​⚡MANUALE tempmail⚡​**
            Comando che serve per la creazione di una email temporanea che l'utente potrà utilizzare.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */generate*: Il bot risponde con la nuova email generata;
            */emailricevente*: Il bot manda una email all' indirizzo specificato;
            */close*: Il bot non controlla o risponde ai comandi delle tempmail.
            """;
    
    public static String QUOTE_HELP_TEXT = """
            **​⚡MANUALE quote⚡​**
            Comando che serve per la creazione di una email temporanea che l'utente potrà utilizzare.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */random*: Il bot risponde con un quote famoso generato in modo casuale;
            */close*: Il bot non controlla o risponde ai comandi dei quote.
            """;
    
    public static String NMAP_HELP_TEXT = """
            **​⚡MANUALE nmap⚡​**
            Comando che serve per la ricerca di tutte le porte attive di un determinato indirizzo ip selezionato.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */indirizzo_ip*: Il bot risponde con la lista delle porte attive dell'indirizzo ip;
            */close*: Il bot non controlla o risponde ai comandi di nmap.
            *⚠️​N.B:⚠️* Il comando ci mette tempo a essere eseguito perché i controlli che deve fare sono molti e a livello di macchina pesanti, pertanto è vivamente consigliato di non digitare​ nessun altro comando fino a quando non vedete una risposta perché potrebbe portare il bot a smettere di funzionare.
            """;
    
    public static String TRANSLATOR_HELP_TEXT = """
            **​⚡MANUALE translate⚡​**
            Comando che serve per la traduzione di un testo in qualunque lingua in italiano.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */testo_da_tradurre*: Il bot risponde con la traduzione in italiano del testo scritto;
            */close*: Il bot non controlla o risponde ai comandi di nmap.
            """;
    
    public static String MP3_HELP_TEXT = """
            **​⚡MANUALE mp3⚡​**
            Comando che serve per il download di un video attraverso il link di youtube.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */link_da_scaricare*: Il bot risponde con il video da scaricare in formato mp3;
            */close*: Il bot non controlla o risponde ai comandi di mp3.
            *⚠️​N.B:⚠️* Il comando ci mette tempo a rispondere, inoltre video più lunghi di 4 minuti potrebbero non essere convertiti a causa dei limiti imposti dai programmatori.
            """;
    
    public static String LYRICS_HELP_TEXT = """
            **​⚡MANUALE lyrics⚡​**
            Comando che serve per trovare il testo di una canzone, attraverso un titolo
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */titolo_da_cercare*: Il bot risponde con i lyrics che riesce a trovare per la canzone;
            */close*: Il bot non controlla o risponde ai comandi di /lyrics.
            """;
    
    public static String HACK_HELP_TEXT = """
            **​⚡MANUALE hack⚡​**
            Comando che serve per intasare la casella di prenotazione della biblioteca comunale di Tavernole.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */start*: Il bot inizia l'attacco e risponde con il file json di risposta;
            */close*: Il bot non controlla o risponde ai comandi di mp3.
            *⚠️​N.B:⚠️* Il programatore non è responsabile dei vostri utilizzi, in caso di denuncia voi siete i responsabili dell'utilizzo dell'applicativo, perché è stato programmato per puri scopi educativi e didattici.
            """;
    
    public static String GAME_HELP_TEXT = """
            **​⚡MANUALE game⚡​**
            Comando che serve per ricevere il link per il gioco.
            I comandi utilizzabili sono:
            ️​*/help*: Il bot risponde con questo messaggio, nella quale spiega le funzionalità del comando;
            */start*: Il bot invia il link git per scaricare il file jar con il gioco;
            */close*: Il bot non controlla o risponde ai comandi di /game.
            *⚠️​N.B:⚠️* Il file .jar per essere eseguito necessita che nel dispositivo ci sia installato java, altrimenti il giovo non verrà avviato.
            """;

}
