package hu.petrik.festmeny;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
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
        f1.setElkelt(true);

        Festmenyek festmenylista = null;
        String fajlNev = "festmenyek.csv";
        try {
           festmenylista =  new Festmenyek(fajlNev);
        } catch (FileNotFoundException e) {
            System.out.printf("A %s nem található\n", fajlNev);
        } catch (IOException e) {
            System.out.println("ISmeretlen hiba történt");
        }
        festmenyek.addAll(festmenylista.getLista());


        for (int i = 0; i < 20; i++) {
            int index = (int)(Math.random()*((festmenyek.size()-1)+1)+0);
            int szazalek = (int)(Math.random()*(100-10+1)+10);
            try {
                festmenyek.get(index).licit(szazalek);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        int sorszam = 1;
        String szoveg;
        int szazalek = 10;
        Duration duration = null;
        for (Festmeny f : festmenyek) {
            System.out.println(f);
        }

        while (true) {
            duration = null;
            try {
                System.out.printf("Adjon meg egy sorszámot %d - %d: ", 1, festmenyek.size());
                sorszam = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Nem sorszámot adott meg");
                break;
            }
            if (sorszam == 0){
                for (int i = 0; i < festmenyek.size(); i++) {
                    if (festmenyek.get(i).getLicitekSzama() > 0) {
                        festmenyek.get(i).setElkelt(true);
                    }
                }
                break;
            }

            if (sorszam > -1 && sorszam <= festmenyek.size()) {
                sorszam -= 1;
                if (festmenyek.get(sorszam).getLegutolsoLicitIdeje() != null) {
                     duration = Duration.between(festmenyek.get(sorszam).getLegutolsoLicitIdeje(), LocalDateTime.now());
                }
                if ( duration == null || duration.getSeconds() < 120) {
                    if (!festmenyek.get(sorszam).isElkelt()) {
                        sc.nextLine();
                        System.out.print("Kérlek adjon meg egy százalékot (10-100): ");
                        szoveg = sc.nextLine();
                        try {
                            szazalek = Integer.parseInt(szoveg);
                        } catch (NumberFormatException exp) {
                            if (szoveg.isEmpty()) {
                                try {
                                    festmenyek.get(sorszam).licit(10);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }

                            } else {
                                System.out.println(szoveg + "Nem egy érvényes százalék ");
                                break;
                            }
                        }
                        try {
                            festmenyek.get(sorszam).licit(szazalek);

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else {
                        System.out.println("Ez a festmény már elkelt :(");
                    }
                } else {
                    if (festmenyek.get(sorszam).getLegutolsoLicitIdeje() != null) {
                        festmenyek.get(sorszam).setElkelt(true);
                        System.out.println("Ez a festmény már elkelt :( túl későn licitált rá :(");
                    }
                }


            } else {
                System.out.println("Nem jó sorszámot adott meg");
            }

        }


        for (int i = 0; i < festmenyek.size(); i++) {
            System.out.println(festmenyek.get(i));
        }


        Festmeny legdragabb = festmenyek.get(0);
        for (Festmeny f : festmenyek) {
            if (f.getLegmagasabbLicit() >= legdragabb.getLegmagasabbLicit()){
                legdragabb = f;
        }
        }

        System.out.println("\n" + legdragabb);



    }

}
