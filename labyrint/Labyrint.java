import java.util.Scanner;
import java.io.File;

public class Labyrint {
    protected Rute[][] labyrint;
    protected int antRader;
    protected int antKolonner;

    public Labyrint(String filnavn) {
        lesInnLabyrint(filnavn);
        settNaboer();
    }

    // Metode for å lese inn fil til labyrint
    private void lesInnLabyrint(String filnavn) {
        Scanner scanner = null;
        String linje = "";
        String[] biter = null;

        try {
            scanner = new Scanner(new File(filnavn));

            // Første linje som henholdsvis inneholder to heltall for antall rader og antall kolonner
            linje = scanner.nextLine();
    
            biter = linje.split(" ");
            antRader = Integer.parseInt(biter[0]);
            antKolonner = Integer.parseInt(biter[1]);
    
            labyrint = new Rute[antRader][antKolonner];
        }

        catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }


        // Leser inn labyrinten
        try {
            for (int i = 0; i < antRader; i++) {
                linje = scanner.nextLine();
                for (int j = 0; j < antKolonner; j++) {

                    // Sort rute ('#')
                    if (linje.charAt(j) == '#') {
                        labyrint[i][j] = new SortRute(i, j, this);
                    }

                    // Åpning:
                    // 1: '.' på første eller siste rad = åpning
                    // 2: '.' på første eller siste kolonne = åpning
                    else if ( (linje.charAt(j) == '.') && (i == 0 || i == antRader - 1) || (j == 0 || j == antKolonner - 1) ) {
                        labyrint[i][j] = new Aapning(i, j, this);
                    }

                    // Hvit rute ('.')
                    else {
                        labyrint[i][j] = new HvitRute(i, j, this);
                    }
                }
            }
            scanner.close();
        }

        catch(Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void settNaboer() {
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                Rute rute = labyrint[i][j];

                // nord
                if (i > 0) {
                    rute.nord = labyrint[i - 1][j];
                }

                // syd
                if (i < antRader - 1) {
                    rute.syd = labyrint[i + 1][j];
                }

                // vest
                if (j > 0) {
                    rute.vest = labyrint[i][j - 1];
                }

                // oest
                if (j < antKolonner - 1) {
                    rute.oest = labyrint[i][j + 1];
                }
            }
        }
    }

    public Rute hentRute(int rad, int kol) {
        return labyrint[rad][kol];
    }

    // Kaller på metoden finn i den ruta som ligger på posisjon (rad, kol) i labyrinten
    public void finnUtveiFra(int rad, int kol) {
        Rute rute = hentRute(rad, kol);
        
        System.out.println("Aapninger: ");
        rute.finn(null);
    }

    // Looper gjennom 2d-arrayet for å printe labyrinten (samme format som i tekst-filen)
    @Override
    public String toString() {
        String streng = "";

        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                streng += labyrint[i][j];
            }
            streng += "\n";
        }

        return streng;
    }
}
