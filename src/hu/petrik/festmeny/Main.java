package hu.petrik.festmeny;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Festmeny> festmenyek = new ArrayList<>();

        Festmeny f1 = new Festmeny("asd", "Van danieé","Goth");
        f1.licit();
        Festmeny f2 = new Festmeny("das", "Leornado Da...","idk");

        festmenyek.add(f1);
        festmenyek.add(f2);

        System.out.printf("Adja meg mennyi festményt szeretne felvenni: ");
        Scanner sc = new Scanner(System.in);
        int festmenyMennyiseg = sc.nextInt();
        sc.nextLine();
        String festmenyNev;
        String festoNeve;
        String stilus;
        while (festmenyMennyiseg != 0) {

            System.out.print("Adja meg festmény Nevét: ");
            festmenyNev  = sc.nextLine().toString();
            sc.nextLine();
            System.out.print("Adja meg Festő nevét: ");
            festoNeve =  sc.nextLine().toString();
            sc.nextLine();

            System.out.print("Adja meg a stílust: ");
            stilus = sc.nextLine().toString();
            sc.nextLine();

            festmenyek.add(new Festmeny(festmenyNev,festoNeve, stilus));
            festmenyMennyiseg--;
        }
        for (int i = 0; i < festmenyek.size(); i++) {
            System.out.println(festmenyek.get(i));
        }

        System.out.println(f1);
    }
}
