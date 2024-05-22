public class LegeSpesialist extends Lege implements Godkjenningsfritak {
    protected String KontrollID;
    public LegeSpesialist(String navn, String KontrollID) {
        super(navn);
        this.KontrollID = KontrollID;
    }

    public String hentKontrollID() {
        return KontrollID;
    }
    
    public String toString(){
        return "Utskrivende lege: " + hentNavn() + ", kontroll-ID: " + hentKontrollID();
    }
}
