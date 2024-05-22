import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;
import java.util.ArrayList;

public class Monitor2 {
    private SubsekvensRegister register = new SubsekvensRegister();
    private Lock laas = new ReentrantLock();

    // En beholder for å ta vare på filnavnene som blir lest inn fra metadata
    public ArrayList<String> filnavnBeholder = new ArrayList<>();

    // Henter SubsekvensRegister sin HashMap beholder
    public ArrayList<HashMap<String, Subsekvens>> hentBeholder() {
        return register.hentBeholder();
    }

    public ArrayList<String> hentFilnavnBeholder() {
        return filnavnBeholder;
    }
    
    // Setter inn et flettet HashMap i SubsekvensRegister sin HashMap beholder
    public void settInnHashMap(HashMap<String, Subsekvens> hm) {
        laas.lock();
        try {
            register.settInnHashMap(hm);
        }

        finally {
            laas.unlock();
        }
    }
    
    // Henter ut to HashMaper
    public HashMap[] hentToHashMaper() {
        laas.lock();

        try {
            if (register.hentBeholder().size() < 2) {
                return null;
            }

            HashMap[] hashMaper = new HashMap[2];
            hashMaper[0] = register.hentHashMap();
            hashMaper[1] = register.hentHashMap();

            return hashMaper;
        }

        finally {
            laas.unlock();
        }
    }

    // Henter et filnavn som brukes i LeseTrad
    public String hentFilnavn() {
        laas.lock();

        try {
            if (filnavnBeholder.isEmpty()) {
                return null;
            }

            return filnavnBeholder.remove(0);
        }

        finally {
            laas.unlock();
        }
    }
}
