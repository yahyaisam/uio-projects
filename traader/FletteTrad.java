import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable {
    private Monitor2 monitor;
    private CountDownLatch cdl;

    public FletteTrad(Monitor2 monitor, CountDownLatch cdl) {
        this.monitor = monitor;
        this.cdl = cdl;
    }

    public void flettHashMaps() {
        HashMap[] hashMapArray = monitor.hentToHashMaper();

        HashMap<String, Subsekvens> hashmap1 = hashMapArray[0];
        HashMap<String, Subsekvens> hashmap2 = hashMapArray[1];

        for (Map.Entry<String, Subsekvens> entry : hashmap2.entrySet()) {
            String key = entry.getKey();
            Subsekvens value = entry.getValue();
            
            if (hashmap1.containsKey(key)) {
                hashmap1.get(key).oekAntall();
            }
    
            else {
                hashmap1.put(key, value);
            }
        }
    
        monitor.settInnHashMap(hashmap1);
    }

    @Override
    public void run() {
        while (true) {
            HashMap[] hashMapArray = monitor.hentToHashMaper();
            
            if (hashMapArray == null) {
                cdl.countDown();
                return;
            }

            HashMap<String, Subsekvens> hashmap = SubsekvensRegister.flettHashMap(hashMapArray[0], hashMapArray[1]);
            monitor.settInnHashMap(hashmap);
        }
    }
}
