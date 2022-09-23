package hu.petrik.festmeny;

public class Main {
    public static void main(String[] args) {
        Festmeny f1 = new Festmeny("asd", "Van danieé","Goth");
        f1.licit();
        for (int i = 0; i < 100; i++) {
            f1.licit();
        }
    }
}
