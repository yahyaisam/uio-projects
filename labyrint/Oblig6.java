import java.util.Scanner;

public class Oblig6 {
    public static void main(String[] args) {

        String mappe = "labyrinter";
        String filnavn = mappe + "/" + args[0];

        Labyrint labyrint = new Labyrint(filnavn);
        //System.out.println(labyrint.toString());
        
        //System.out.println();

        //labyrint.finnUtveiFra(1, 1);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
        
        while (true) {
            String[] koordinater = scanner.nextLine().split(" ");

            if (koordinater[0].equals("-1")) {
                break;
            }
            
            System.out.println();
            labyrint.finnUtveiFra(Integer.parseInt(koordinater[0]), Integer.parseInt(koordinater[1]));
        }

        scanner.close();
        
        // Rute rute = labyrint.hentRute(0, 1);
        // System.out.println(rute);

        // System.out.println();

        // System.out.println("Nord: " + rute.hentNord());
        // System.out.println("Syd: " + rute.hentSyd());
        // System.out.println("Vest: " + rute.hentVest());
        // System.out.println("Oest: " + rute.hentOest());
    }
}
