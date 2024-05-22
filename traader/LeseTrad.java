import java.util.concurrent.CountDownLatch;
import java.util.HashMap;

public class LeseTrad implements Runnable {
    private Monitor2 monitor;
    private CountDownLatch cdl;

	public LeseTrad(Monitor2 monitor, CountDownLatch cdl) {
        this.monitor = monitor;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        String filnavn;
        while (true) {
            filnavn = monitor.hentFilnavn();
            
            if (filnavn == null) {
                cdl.countDown();
                return;
            }
            
            HashMap<String, Subsekvens> hashmap = SubsekvensRegister.lesSubsekvens(filnavn);
            monitor.settInnHashMap(hashmap);
        }
    }
}
