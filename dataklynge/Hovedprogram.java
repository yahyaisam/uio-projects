import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hovedprogram {
	public static void main(String[] args) throws FileNotFoundException {
		Dataklynge saga = new Dataklynge();
		
		String filnavn = args[0];
		File fil = new File(filnavn);
		Scanner scanner = new Scanner(fil);
		
		while (scanner.hasNextLine()) {
			String linje = scanner.nextLine();
			String[] biter = linje.split(" ");

			int antNoder = Integer.parseInt(biter[0]);
			int antPros = Integer.parseInt(biter[1]);
			int minne = Integer.parseInt(biter[2]);

			if (antPros > 16 || minne > 4096) {
				System.out.println("Det er ikke nok kapasitet!");
				System.exit(1);
			}

			for (int i = 0; i < antNoder; i++) {
				Node node = new Node(antPros, minne);
				saga.settInn(node);
			}
		}
		scanner.close();
		
		System.out.println("Noder med minst 128 GB: " + saga.noderMedNokMinne(128));
		System.out.println("Noder med minst 512 GB: " + saga.noderMedNokMinne(512));
		System.out.println("Noder med minst 1024 GB: " + saga.noderMedNokMinne(1024));
		
		System.out.println("\nAntall prosessorer: " + saga.antProsessorer());
		System.out.println("Antall racks: " + saga.antRacks());
	}
}
