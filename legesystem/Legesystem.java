import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class Legesystem {
    protected IndeksertListe<Legemiddel> legemiddelListe = new IndeksertListe<>();
    protected IndeksertListe<Resept> reseptListe = new IndeksertListe<>();
    protected IndeksertListe<Lege> legeListe = new IndeksertListe<>();
    protected IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();
    protected Prioritetskoe<Lege> legerSortert = new Prioritetskoe<>();

    public void lesFraFil(String filnavn) throws UlovligUtskrift {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(filnavn));
        }

        catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }
        
        String linje = "";
        String type = "";
        while (scanner.hasNextLine()) {
            try{
            linje = scanner.nextLine();
            // Dersom indeks 0 av en String er '#'. Dette vil fungere for alle tilfeller siden linjen vil alltid starte med '#'
            if (linje.charAt(0) == '#') {
                // Eksempel: Dersom vi har '# Pasienter (navn, fnr)', blir [1] satt til 'Pasienter'
                type = linje.split(" ")[1];
                // Hopper over til neste linje (iterasjon) ved bruk av continue, for aa unngaa aa lese linjer som inneholder '#'
                continue;
            }

            // Tok med trim() for aa fjerne unoedvendige whitespaces i linjene i tekstfilene som leses inn
            String[] biter = linje.trim().split(",");

            // Legg til pasient
            if (type.equals("Pasienter")) {
                String navn = biter[0];
                String fnr = biter[1];
                boolean fnrEksisterer = false;
                
                for (Pasient pas : pasientListe){
                    if (fnr.equals(pas.hentFoedselsnummer())){
                        fnrEksisterer = true;
                        System.out.println(navn + " sitt foedselsnummer er allerede i systemet");  
                    }
                }
                if (fnrEksisterer == false) {
                    Pasient pasient = new Pasient(navn, fnr);
                    pasientListe.leggTil(pasient);
                }
            }

            // Legg til legemiddel
            if (type.equals("Legemidler")) {
                String navn = biter[0];
                int pris = Integer.parseInt(biter[2]);
                double virkestoff = Double.parseDouble(biter[3]);
                int styrke = 0;
                
                if (biter.length == 5) {
                    styrke = Integer.parseInt(biter[4]);
                }

                String legemiddelType = biter[1];

                if (legemiddelType.equals("narkotisk")) {
                    Narkotisk narkotisk = new Narkotisk(navn, pris, styrke, virkestoff);
                    legemiddelListe.leggTil(narkotisk);
                }

                if (legemiddelType.equals("vanedannende")) {
                    Vanedannende vanedannende = new Vanedannende(navn, pris, styrke, virkestoff);
                    legemiddelListe.leggTil(vanedannende);
                }

                if (legemiddelType.equals("vanlig")) {
                    VanligLegemiddel vanlig = new VanligLegemiddel(navn, pris, virkestoff);
                    legemiddelListe.leggTil(vanlig);
                }
            }

            // Legg til lege
            if (type.equals("Leger")) {
                String navn = biter[0];
                String kontrollID = biter[1];
                
                if (kontrollID.equals("0")) {
                    Lege lege = new Lege(navn);
                    legeListe.leggTil(lege);
                    legerSortert.leggTil(lege);
                }

                else {
                    LegeSpesialist spesialist = new LegeSpesialist(navn, kontrollID);
                    legeListe.leggTil(spesialist);
                    legerSortert.leggTil(spesialist);
                }
            }

            // Legg til resept
            if (type.equals("Resepter")) {
                Legemiddel legem = null;
                Lege lege = null;
                Pasient pas = null;

                for (int i = 0; i < legemiddelListe.stoerrelse(); i++) {
                    int id = Integer.parseInt(biter[0]);
                    if (legemiddelListe.hent(i).hentId() == id) {
                        legem = legemiddelListe.hent(i);
                    }
                }


                for (int i = 0; i < legeListe.stoerrelse(); i++) {
                    String navn = biter[1];
                    if (legeListe.hent(i).hentNavn().equals(navn)) {
                        lege = legeListe.hent(i);
                    }
                }
                
                for (int i = 0; i < pasientListe.stoerrelse(); i++) {
                    int id = Integer.parseInt(biter[2]);
                    if (pasientListe.hent(i).hentId() == id) {
                        pas = pasientListe.hent(i);
                    }
                }
                
                int reit = 0;
                if (biter.length == 5) {
                    reit = Integer.parseInt(biter[4]);
                }
                
                
                String reseptType = biter[3];
                if (reseptType.equals("hvit")) {
                    HvitResept hvitResept = lege.skrivHvitResept(legem, pas, reit);
                    reseptListe.leggTil(hvitResept);
                }

                if (reseptType.equals("blaa")){
                    BlaaResept blaaResept = lege.skrivBlaaResept(legem, pas, reit);
                    reseptListe.leggTil(blaaResept);
                }

                if (reseptType.equals("p")) {
                    PResept pResept = lege.skrivPResept(legem, pas, reit);
                    reseptListe.leggTil(pResept);
                }

                if (reseptType.equals("militaer")) {
                    MilResept milResept = lege.skrivMilResept(legem, pas);
                    reseptListe.leggTil(milResept);
                }
            }
        } catch (Exception f) {
            System.out.println(f);
        }}
        scanner.close();
    }

    // E3: Skriver ut en ryddig oversikt over elementene fra listene
    public void oversikt() {
        System.out.println("------------------Start paa oversikt------------------");
        System.out.println("*****************************************************");
        System.out.println("Legemidler:");
        System.out.println("*****************************************************");
        skrivLegemiddelListe();
        System.out.println();
        System.out.println();

        System.out.println("*****************************************************");
        System.out.println("Resepter:");
        System.out.println("*****************************************************");
        skrivReseptListe();
        System.out.println();
        System.out.println();

        System.out.println("*****************************************************");
        System.out.println("Leger sortert:");
        System.out.println("*****************************************************");
        skrivSortertLegeliste();
        System.out.println();
        System.out.println();

        System.out.println("*****************************************************");
        System.out.println("Pasienter:");
        System.out.println("*****************************************************");
        skrivPasientListe();
        System.out.println();
        System.out.println("------------------Slutt paa oversikt------------------");
    }

    // E4: Funksjonalitet for aa la brukeren legge til en lege, pasient, resept eller legemiddel
    public void leggTilNyttElement() throws UlovligUtskrift {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hva vil du legge til?");
        System.out.println("1: Resept");
        System.out.println("2: Lege");
        System.out.println("3: Pasient");
        System.out.println("4: Legemiddel\n");

        String input = scanner.nextLine();
        System.out.println();

        if (input.equals("1")) {
            System.out.println("Hva slags resept vil du legge til?");
            System.out.println("1: Blaa resept");
            System.out.println("2: Hvit resept");
            System.out.println("3: Militaer resept");
            System.out.println("4: P-resept\n");
            
            int valgtReseptType = scanner.nextInt();
            System.out.println();

            if (valgtReseptType < 1 || valgtReseptType > 4) {
                System.out.println("Ugyldig input! Tast heller inn '1', '2', '3' eller '4'\n");
                return;
            }

            int lmTeller = 0;
            System.out.println("Velg legemiddel");
            for (Legemiddel lm : legemiddelListe){
                System.out.println("" + lmTeller + ": | Navn: " + lm.hentNavn() + " | Type: " + lm.getClass().getSimpleName());
                lmTeller++;
            }

            int valgtIndeksLM = scanner.nextInt();

            if (valgtIndeksLM < 0 || valgtIndeksLM > (lmTeller-1)) {
                System.out.println("Ugyldig input: " + valgtIndeksLM +" \n");
                return;
            }

            try{

                Legemiddel valgtLegemiddel = legemiddelListe.hent(valgtIndeksLM);
                if (valgtLegemiddel == null){
                    System.out.println("Du har ikke valgt et gyldig legemiddel");
                    return;
                }
                if (valgtLegemiddel instanceof Narkotisk && valgtReseptType != 1 ){
                    System.out.println("Du kan kun legge til narkotiske legemidler paa blaa resepter!\n");
                    return;
                }

                int legeTeller = 0;
                System.out.println("Velg en utskrivende lege");
                for (Lege l : legeListe){
                    System.out.println("" + legeTeller + ": | Navn: " + l.hentNavn() + " | Type: " + l.getClass().getSimpleName());
                    legeTeller++;
                }
            

                int valgtIndeksLege = scanner.nextInt();
                Lege valgtLege = legeListe.hent(valgtIndeksLege);
                if (valgtLege == null){
                    System.out.println("Du har ikke valgt en gyldig lege");
                    return;
                }
                
                if ( !(valgtLege instanceof LegeSpesialist) && valgtLegemiddel instanceof Narkotisk) {
                    System.out.println("Ugyldig valg. Kun legespesialister kan skrive resepter for narkotiske legemidler!\n");
                    return;
                }
                    
                int pasientTeller = 0;
                System.out.println("Velg en pasient");
                for (Pasient p : pasientListe){
                    System.out.println(pasientTeller + ": | Navn: " + p.hentNavn() + " | ID: " + p.hentId());
                    pasientTeller++;
                }
                int valgtIndeksPasient = scanner.nextInt();
                Pasient valgtPasient = pasientListe.hent(valgtIndeksPasient);
                if (valgtPasient == null){
                    System.out.println("Du har ikke valgt en gyldig pasient");
                    return;
                }
                    
                //Oppretter en dummy ReIt variabel, sjekker om valgt resept er militaer. Hvis ja, skippes denne
                int nyReIt = 0;
                if (valgtReseptType != 2) {
                    System.out.println("Skriv antall reit");
                    nyReIt = scanner.nextInt();
                }

                //Oppretter reseptene basert paa foerste valgene
                if (valgtReseptType == 1) {
                    BlaaResept nyBlaaResept = valgtLege.skrivBlaaResept(valgtLegemiddel, valgtPasient, nyReIt);
                    reseptListe.leggTil(nyBlaaResept);

                    System.out.println("Blaa resept har blitt lagt til.");
                }
                    
                if (valgtReseptType == 2) {
                    HvitResept nyHvitResept = valgtLege.skrivHvitResept(valgtLegemiddel, valgtPasient, nyReIt);
                    reseptListe.leggTil(nyHvitResept);
                    
                    System.out.println("Hvit resept har blitt lagt til.");
                }
                    
                if (valgtReseptType == 3) {
                    MilResept nyMilResept = valgtLege.skrivMilResept(valgtLegemiddel, valgtPasient);
                    reseptListe.leggTil(nyMilResept);

                    System.out.println("Militaer resept har blitt lagt til.");
                }
                    
                if (valgtReseptType == 4) {
                    PResept nyPResept = valgtLege.skrivPResept(valgtLegemiddel, valgtPasient, nyReIt);
                    reseptListe.leggTil(nyPResept);
                    
                    System.out.println("P-resept resept har blitt lagt til.");
                }
            }

            catch(UgyldigListeindeks e){
                System.out.println(e);
            }
            return; 
        }
    

        //Legger til ny lege
        else if (input.equals("2")) {
            System.out.println("Legger til en ny lege");
                
            System.out.println("Oppgi navn paa lege");
            input = scanner.nextLine();
            String navn = input;

            System.out.println("Tast inn '0' hvis legen er en spesialist");
            System.out.println("Hvis ikke, tast inn '1'");
            input = scanner.nextLine();

            if (input.equals("0")) {
                System.out.println("Tast inn din kontroll-ID:");
                input = scanner.nextLine();
                String kontrollID = input;

                LegeSpesialist spes = new LegeSpesialist(navn, kontrollID);
                legeListe.leggTil(spes);
                legerSortert.leggTil(spes);
                System.out.println("Lagt til legespesialist!");
            }

            else if (input.equals("1")) {
                Lege lege = new Lege(navn);
                legeListe.leggTil(lege);
                legerSortert.leggTil(lege);
                System.out.println("Lagt til lege!");
            } else {
                System.out.println("Ugyldig input!");
            }
        }
            
        else if (input.equals("3")){
            System.out.println("Legger til en ny pasient");
            System.out.println("Oppgi navn paa pasienten: ");
            String nyttNavn = scanner.nextLine();
            System.out.println();
            System.out.println("Oppgi foedselsnummer paa pasienten: ");
            String nyttFoedselsNummer  = scanner.nextLine();
            System.out.println();

            Pasient nyPasient = new Pasient(nyttNavn, nyttFoedselsNummer);
            pasientListe.leggTil(nyPasient);
            System.out.println("Pasienten " + nyttNavn + " har blitt lagt til.");
        }    
            
        else if (input.equals("4")){
            int nyStyrke = 0;

            System.out.println("Legger til en nytt legemiddel");
            //scanner.nextLine maa visst komme foer nextInt
            System.out.println("Skriv navn paa legemiddelet");
            String lmNavn = scanner.nextLine();

            
            System.out.println("Velg legemiddeltype");
            System.out.println("1: Vanlig legemiddel");
            System.out.println("2: Vanedannende legemiddel");
            System.out.println("3: Narkotisk legemiddel");
        
            int valgtLegemiddel = scanner.nextInt();
            System.out.println();

            if (valgtLegemiddel < 1 || valgtLegemiddel > 3) {
                System.out.println("Ugyldig input! Tast heller inn '1', '2' eller '3'\n");
                return;
            }
            
            System.out.println("Skriv inn foerpris paa legemiddelet");
            int foerpris = scanner.nextInt();

            if (valgtLegemiddel == 2 || valgtLegemiddel == 3){
                System.out.println("Skriv inn styrken til legemiddelet");
                nyStyrke = scanner.nextInt();
            }

            System.out.println("Skriv inn virkestoffet til legemiddelet");
            double nyVirkestoff = scanner.nextDouble();


            if (valgtLegemiddel == 1){
                VanligLegemiddel nyVanligLegemiddel = new VanligLegemiddel(lmNavn, foerpris, nyVirkestoff);
                legemiddelListe.leggTil(nyVanligLegemiddel);
                System.out.println("Vanlig legemiddel '" + lmNavn + "'' har blitt lagt til");
            }
            
            if (valgtLegemiddel == 2){
                Vanedannende nyVanedannende = new Vanedannende(lmNavn, foerpris, nyStyrke, nyVirkestoff);
                legemiddelListe.leggTil(nyVanedannende);
                System.out.println("Vanedannende legemiddel '" + lmNavn + "'' har blitt lagt til");
            }
            
            if (valgtLegemiddel == 3){
                Narkotisk nyNarkotisk = new Narkotisk(lmNavn, foerpris, nyStyrke, nyVirkestoff);
                legemiddelListe.leggTil(nyNarkotisk);
                System.out.println("Narkotisk legemiddel '" + lmNavn + "'' har blitt lagt til");
            }
        }
    }

    
    public void statistikk() {
        int tellerVanedannende = 0;
        for (Resept r : reseptListe) {
            if (r.hentLegemiddel() instanceof Vanedannende) {
                tellerVanedannende++;
            }
        }
        
        System.out.println("****************************Start paa statistikk**************************************");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Det har blitt skrevet ut " + tellerVanedannende + " antall resepter paa vanedannende.");
        System.out.println("--------------------------------------------------------------------------------------");

        int tellerNarkotisk = 0;
        for (Resept r : reseptListe) {
            if (r.hentLegemiddel() instanceof Narkotisk) {
                tellerNarkotisk++;
            }
        }
        System.out.println("Det har blitt skrevet ut " + tellerNarkotisk + " antall resepter paa narkotisk.");
        System.out.println("--------------------------------------------------------------------------------------");
        
        int specialDrugCounter = 0;
        for (Lege l : legerSortert){
            if(l.antallNarkotiskSkrevet != 0){
                System.out.println("Lege: " + l.navn + " | Antall narkotiske resepter skrevet: " + l.antallNarkotiskSkrevet);
                specialDrugCounter++;
            }
        }
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Pasienter med resepter paa narkotiske legemidler: "); 
        for (Pasient pas : pasientListe) {
            int dangerCounter = 0;
            for (Resept r : pas.reseptListe){
                if (r.hentLegemiddel() instanceof Narkotisk){
                    dangerCounter++;
                }
            } if (dangerCounter != 0){
                System.out.println("" + pas.hentNavn() + " | Antall resepter med narkotiske legemidler: " + dangerCounter);
            }
        } 
        System.out.println("****************************Slutt paa statistikk**************************************");
    }

    public void skrivLegemiddelListe() {
        for (Legemiddel lm : legemiddelListe) {
            System.out.println(lm);
        }
    }
    
    public void brukResept(){
        //try {
        //Maa handle foelgende:
        // IndexOutOfBounds hvis man velger en ugyldig indeks
        // Feilmelding om det ikke er noen

        Scanner sc = new Scanner(System.in);
        System.out.println("Hvilken pasient vil du se resepter for?");
        int pasientTeller = 0;
        int reseptTeller = 0;

        //Cycler gjennom pasientene og presenterer dem med tilhoerende indeksnummer
        for (Pasient p : pasientListe){
            System.out.println(pasientTeller + ": " +p);
            pasientTeller++;
        }
        
        int valgtPasient = sc.nextInt();

        try{

            //feilmelding om det er ingen resepter i pasientens liste
            if(pasientListe.hent(valgtPasient).reseptListe.stoerrelse() == 0) {
                System.out.println("Denne pasienten har ingen resepter");
                return;
            }
            System.out.println("Hvilken resept vil du bruke?");
            //Cycler gjennom pasientens reseptliste og presenterer dem med tilhoerende indeksnummer
            for (Resept r : pasientListe.hent(valgtPasient).reseptListe ) {
                System.out.println("Resept: " + reseptTeller + "\n"+ r);
                reseptTeller++;
            }
            
            int valgtResept = sc.nextInt();

            //feilmelding om det er ingen ReIter igjen i valgt resept
            Resept reseptTilBruk = pasientListe.hent(valgtPasient).reseptListe.hent(valgtResept);
            if(reseptTilBruk.reit == 0) {
                System.out.println("Denne reseptein har ingen Reiter igjen");
                return;
            }
            //Bruk spesifikk resept
            reseptTilBruk.bruk();

            //Bekrefter overfor brukeren at resepten er brukt.
            System.out.println("Resepten til " + reseptTilBruk.hentLegemiddel().hentNavn() + " er blitt brukt. Reiten er naa paa " + reseptTilBruk.hentReit());
        }

        catch (Exception e) {
            System.out.println("Ugyldig input!");
            return;
        }
    }

    //finn ut mer om hvordan man bruker exceptions lol
    public void skrivTilFil() throws IOException{
        
        //Ber brukeren om aa bestemme filnavn
        Scanner input = new Scanner(System.in);
        System.out.println("Velg et navn paa filen");
        String fileName = "" +  input.nextLine() + ".txt";

        try {
            File lsData = new File(fileName);
            if (lsData.createNewFile()) {
                System.out.println("Fil opprettet: " + lsData.getName());
            } else {
                System.out.println("Filnavnet er allerede tatt");
            }
        } catch (IOException e) {
            System.out.println("Det skjedde en feil.");
            e.printStackTrace();
        }
        
        //Skriver alt til fil. Gir akkurat samme greie som oppgitt i oblig-pdfen
        try {
            FileWriter skriver = new FileWriter(fileName);
           
            //skriver pasientinfo
            skriver.write("# Pasienter (navn, fnr)");
            for (Pasient pasient : pasientListe) {
               
                skriver.write("\n" + pasient.hentNavn() + "," + pasient.hentFoedselsnummer());
            }
            
            //skriver legemiddelinfo
            skriver.write("\n# Legemidler (navn,type,pris,virkestoff,[styrke])");
            for (Legemiddel lm : legemiddelListe) {
                String lmTekst = "\n" + lm.hentNavn() + "," + lm.getClass().getSimpleName() + "," + lm.hentPris() + "," + lm.hentVirkestoff();
                if (lm instanceof Vanedannende){
                    lmTekst += ",";
                    Vanedannende v = (Vanedannende) lm;
                    lmTekst += v.hentVanedannendeStyrke();
                }
                else if (lm instanceof Narkotisk){
                    lmTekst += ",";
                    Narkotisk v = (Narkotisk) lm;
                    lmTekst += v.hentNarkotiskStyrke();
                }
                skriver.write(lmTekst);
            }

            //skriver legeinfo
            skriver.write("\n# Leger (navn,kontrollid / 0 hvis vanlig lege)");
            for (Lege l : legeListe) {
                
                if (l instanceof LegeSpesialist){
                    LegeSpesialist ls = (LegeSpesialist) l;
                    skriver.write("\n" + ls.hentNavn() + "," + ls.hentKontrollID());
                } else {
                skriver.write("\n" + l.hentNavn() + "," + 0);
                }
            }
            
            //skriver reseptinfo
            skriver.write("\n# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
            for (Resept resept : reseptListe) {
                String reseptString = "\n" + resept.hentLegemiddel().hentId() + "," + resept.hentLegeNavn() + "," + resept.hentPasient().hentId();
                if (resept instanceof BlaaResept) {
                    reseptString += ",blaa";
                } else if (resept instanceof MilResept) {
                    reseptString += ",militaer";
                } else if (resept instanceof PResept) {
                    reseptString += ",p";
                } else if (resept instanceof HvitResept) {
                    reseptString += ",hvit";
                }
                reseptString +="," + resept.hentReit();
                skriver.write(reseptString);
            }

            skriver.close();
            // input.close;
            System.out.println("Fil opprettet");
            
            } catch (IOException e) {
            System.out.println("Det skjedde en feil!");
            e.printStackTrace();
            }
    }

    // Skriver ut innholdet i reseptListe
    public void skrivReseptListe() {
        for (Resept r : reseptListe) {
            System.out.println(r);
        }
    }

    // Skriver ut innholdet i legeListe
    public void skrivLegeListe() {
        for (Lege l : legeListe) {
            System.out.println(l);
        }
    }

    // Skriver ut innholdet i pasientListe
    public void skrivPasientListe() {
        for (Pasient p : pasientListe) {
            System.out.println(p);
        }
    }
    public void skrivSortertLegeliste() {
        for (Lege l : legerSortert) {
            System.out.println(l);
        }
    }

}
