import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.util.Map;

public class SubsekvensRegister {
    private ArrayList<HashMap<String, Subsekvens>> hashBeholder = new ArrayList<>();

    // Henter listen av HashMaper
    public ArrayList<HashMap<String, Subsekvens>> hentBeholder() {
        return hashBeholder;
    }

    // Sette inn en HashMap i ArrayListen
    public void settInnHashMap(HashMap<String, Subsekvens> hm) {
        hashBeholder.add(hm);
    }

    // Henter en vilkårlig HashMap fra ArrayListen
    public HashMap<String, Subsekvens> hentHashMap() {
        if (hashBeholder.isEmpty()) {
            return null;
        }

        return hashBeholder.remove(0);
    }

    // Henter antall HashMaper i ArrayListen
    public int hentAntallHashMap() {
        return hashBeholder.size();
    }

    // Leser én fil med én person sin immunrepertoar og lager en HashMap av subsekvensene i filen
    // Returnerer en referanse til den nye HashMapen
    public static HashMap<String, Subsekvens> lesSubsekvens(String filnavn) {
        HashMap<String, Subsekvens> hashmap = new HashMap<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filnavn));

            String linje = "";
            while (scanner.hasNextLine()) {
                linje = scanner.nextLine();

                if (linje.contains("amino_acid")) {
                    continue;
                }

                for (int i = 0; i <= linje.length() - 3; i++) {
                    String ss = linje.substring(i, 3 + i);
                    if (hashmap.containsKey(ss)) {
                        continue;
                    }
                    
                    Subsekvens subsekvens = new Subsekvens(ss, 1);
                    hashmap.put(ss, subsekvens);
                }
            }
        }

        catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

        return hashmap;
    }

    public static HashMap<String, Subsekvens> flettHashMap(HashMap<String, Subsekvens> hashmap1, HashMap<String, Subsekvens> hashmap2) {
		for (Map.Entry<String, Subsekvens> entry : hashmap2.entrySet()) {
			String key = entry.getKey();
            Subsekvens value = entry.getValue();
			
            if (hashmap1.containsKey(key)) {
                int antall = entry.getValue().hentAntall();
                hashmap1.get(key).oekAntall(antall);
            }

            else {
                hashmap1.put(key, value);
            }
		}

        return hashmap1;
    }
}
