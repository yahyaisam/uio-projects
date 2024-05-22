import java.io.IOException;
import java.util.Scanner;

public class Hovedmeny {
    public static void main(String[] args) throws UlovligUtskrift {
        Legesystem GreyHospital = new Legesystem();
        GreyHospital.lesFraFil("litenfil.txt");

        boolean erPaa = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("Velkommen til legesystemet!");

        while (erPaa == true) {
            
            System.out.println("\nDette er hovedmenyen:");
            System.out.println("Tast inn '0' for aa avslutte programmet");
            System.out.println("Tast inn '1' for aa se et oversikt over elementene");
            System.out.println("Tast inn '2' for aa legge til nye elementer i systemet");
            System.out.println("Tast inn '3' for aa bruke en gitt resept fra listen til en pasient");
            System.out.println("Tast inn '4' for aa se forskjellige former for statistikk");
            System.out.println("Tast inn '5' for aa skrive data til fil");

            String input = sc.nextLine();
            try{
                int svar = Integer.parseInt(input);
                
                if (svar < 0 || svar > 5){
                    System.out.println("\nUgyldig valg!");
                }
            } catch(NumberFormatException e) {
                System.out.println("Ugyldig valg!");
            }
            
            // Avslutter programmet
            if (input.equals("0")) {
                System.out.println("Takk for din deltagelse!");
                erPaa = false;
            }

            // E3
            if (input.equals("1")) {
                GreyHospital.oversikt();
            }

            // E4
            if (input.equals("2")) {
                GreyHospital.leggTilNyttElement();
            }

            // E5
            if (input.equals("3")) {
                GreyHospital.brukResept();
            }

            // E6
            if (input.equals("4")) {
                GreyHospital.statistikk();
            }

            // E7
            if (input.equals("5")) {
                try {
                    GreyHospital.skrivTilFil();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }        
        }
        sc.close();
    }
}