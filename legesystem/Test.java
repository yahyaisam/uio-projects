import java.io.IOException;

public class Test {
    public static void main(String[] args) throws UlovligUtskrift, IOException {
        Legesystem legesystem = new Legesystem();

        legesystem.lesFraFil("litenfil.txt");
        /*
        System.out.println("*****************************************************");
        System.out.println("Legemidler:");
        System.out.println("*****************************************************");
        legesystem.skrivLegemiddelListe();
        System.out.println();
        System.out.println();

        System.out.println("*****************************************************");
        System.out.println("Resepter:");
        System.out.println("*****************************************************");
        legesystem.skrivReseptListe();
        System.out.println();
        System.out.println();

        
        System.out.println("*****************************************************");
        System.out.println("Leger:");
        System.out.println("*****************************************************");
        legesystem.skrivLegeListe();
        System.out.println();
        System.out.println();
        

        System.out.println("*****************************************************");
        System.out.println("Leger sortert:");
        System.out.println("*****************************************************");
        legesystem.skrivSortertLegeliste();
        System.out.println();
        System.out.println();

        System.out.println("*****************************************************");
        System.out.println("Pasienter:");
        System.out.println("*****************************************************");
        legesystem.skrivPasientListe();
        System.out.println();


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        */

        legesystem.leggTilNyttElement();
    }
}
