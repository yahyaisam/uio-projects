import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.util.concurrent.CountDownLatch;

public class Oblig5Hele {
	public static void main(String[] args) {
		Monitor2 syke = new Monitor2(); // Beholder for "True"
		Monitor2 friske = new Monitor2(); // Beholder for "False"

		final int antTraader = 16;

        String mappenavn = args[0];
        String filnavn = args[0] + "/metadata.csv";

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(filnavn));
            String tempFilnavn = "";

            // Henter filer
            String[] biter;
            while (scanner.hasNextLine()) {
                tempFilnavn = mappenavn + "/" + scanner.nextLine();
                biter = tempFilnavn.split(",");
                
                if (biter[1].equals("True")) {
                    syke.hentFilnavnBeholder().add(biter[0]);
                }

                else if (biter[1].equals("False")) {
                    friske.hentFilnavnBeholder().add(biter[0]);
                }

                else {
                    System.out.println("Noe gikk galt.");
                }
            }
        }

        catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    
        // System.out.println(syke.hentFilnavnBeholder());
        // System.out.println();
        // System.out.println(friske.hentFilnavnBeholder());
        // System.out.println();
        //System.out.println(friske.hentBeholder());

        CountDownLatch cdl = new CountDownLatch(antTraader);
        for (int i = 0; i < antTraader; i++) {
            if (i < 8) {
                Thread traad = new Thread(new LeseTrad(syke, cdl));
                traad.start();
            }

            else {
                Thread traad = new Thread(new LeseTrad(friske, cdl));
                traad.start();
            }
        }
        
        try {
            cdl.await();
        }
        
        catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

		// Fletting her
        cdl = new CountDownLatch(antTraader);
        for (int i = 0; i < antTraader; i++) {
            if (i < 8) {
                Thread traad = new Thread(new FletteTrad(syke, cdl));
                traad.start();
            }

            else {
                Thread traad = new Thread(new FletteTrad(friske, cdl));
                traad.start();
            }
        }

        try {
            cdl.await();
        }
        
        catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

        // System.out.println(syke.hentBeholder());
        // System.out.println();
        // System.out.println(friske.hentBeholder());




        //Teste output syke
        String keySyke = "";
        int flestForekomsterSyke = 0;
        for (Map.Entry<String, Subsekvens> entry : syke.hentBeholder().get(0).entrySet()) {
            Subsekvens value = entry.getValue();
            
            if (value.hentAntall() > flestForekomsterSyke) {
                flestForekomsterSyke = value.hentAntall();
                keySyke = entry.getKey();
            }
        }
        Subsekvens s = new Subsekvens(keySyke, flestForekomsterSyke);
        System.out.println(s.toString());



        // Teste output friske
        String keyFriske = "";
        int flestForekomsterFriske = 0;
        for (Map.Entry<String, Subsekvens> entry : friske.hentBeholder().get(0).entrySet()) {
            Subsekvens value = entry.getValue();
            
            if (value.hentAntall() > flestForekomsterFriske) {
                flestForekomsterFriske = value.hentAntall();
                keyFriske = entry.getKey();
            }
        }
        Subsekvens ss = new Subsekvens(keyFriske, flestForekomsterFriske);
        System.out.println(ss.toString());


        // for (Map.Entry<String, Subsekvens> entry : syke.hentBeholder().get(0).entrySet()) {
        //     Subsekvens value = entry.getValue();
            
        //     if (value.hentAntall() > 7) {
        //         flestForekomsterSyke = value.hentAntall();
        //         keySyke = entry.getKey();
        //     }
        // }
        // Subsekvens test = new Subsekvens(keySyke, flestForekomsterSyke);
        // System.out.println(test.toString());
	}
}
