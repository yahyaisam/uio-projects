public class Vanedannende extends Legemiddel {
    protected int styrke;
    public Vanedannende (String navn, int pris, int vanedannendeStyrke, double virkestoff){
        super(navn, pris, virkestoff);
        this.styrke = vanedannendeStyrke;
    }
    public int hentVanedannendeStyrke(){
        return styrke;
    }
    public String toString(){
    return "_______________" + "\nLegemiddel: " + navn + "\nType: Vanedannende" +  "\nPris: " + pris + "\nID: " + id + "\nVirkestoff: " + virkestoff + "\nVanedannende styrke: " + styrke;
    }
}