public class TestPasient {
    public static void main(String[] args) {
        Pasient pasient1 = new Pasient("Chi", "666DDD");
        Vanedannende coke = new Vanedannende("Coke", 3000, 215, 90);
        Lege lege = new Lege("AAAA");
        
        PResept resept = new PResept(coke, lege, pasient1);
        pasient1.leggTilResept(resept);
    }
}
