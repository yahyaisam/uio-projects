import java.util.ArrayList;

public class Dataklynge {
	// Valgte ArrayList fremfor et array fordi det ikke var oppgitt hvor mange plasser vi hadde behov for
	// I tillegg slipper vi å lage et nytt Rack-objekt, siden vi slipper problemet med å ha for lite plass
	// (dersom vi hadde valgt et array)
	private ArrayList<Rack> racks = new ArrayList<Rack>();

	public Dataklynge() {}
	
	// Metode som tar imot et Node-objekt og plasserer det i et rack med ledig plass
	public void settInn(Node node) {
		// Denne løkka blir hoppet over dersom racks er tom (siden 0 ikke er mindre enn 0)
		for (int i = 0; i < racks.size(); i++) {
			if (! racks.get(i).erFull()) {
				racks.get(i).settInn(node);

				return;
			}
		}

		// Hvis racks er tom, eller hvis det ikke er racks med plass
		Rack nyRack = new Rack();
		nyRack.settInn(node);
		racks.add(nyRack);
	}

	public int antProsessorer() {
		int antPros = 0;

		for (int i = 0; i < racks.size(); i++) {
			antPros += racks.get(i).antProsessorer();
		}

		return antPros;
	}

	public int noderMedNokMinne(int paakrevdMinne) {
		int antNoder = 0;

		for (int i = 0; i < racks.size(); i++) {
			antNoder += racks.get(i).noderMedNokMinne(paakrevdMinne);
		}

		return antNoder;
	}

	public int antRacks() {
		return racks.size();
	}
}
