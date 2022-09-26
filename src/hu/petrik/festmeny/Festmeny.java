package hu.petrik.festmeny;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Festmeny {
    private String cim;
    private  String festo;
    private String stilus;
    private int licitekSzama;
    private int legmagasabbLicit;
    private LocalDateTime legutolsoLicitIdeje;
    private boolean elkelt;


    public Festmeny(String cim, String festo, String stilus) {
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
        this.licitekSzama = 0;
        this.legmagasabbLicit = 0;
        this.legutolsoLicitIdeje = null;
        this.elkelt = false;
    }


    public String getFesto() {
        return festo;
    }

    public String getStilus() {
        return stilus;
    }

    public int getLicitekSzama() {
        return licitekSzama;
    }

    public int getLegmagasabbLicit() {
        return legmagasabbLicit;
    }

    public LocalDateTime getLegutolsoLicitIdeje() {
        return legutolsoLicitIdeje;
    }

    public boolean isElkelt() {
        return elkelt;
    }

    public void setElkelt(boolean elkelt) {
        this.elkelt = elkelt;
    }


    public void licit() {
        if (this.elkelt ) {
            System.out.println("Ez a festmény már elkelt");
        }
        else  {
            if (this.legmagasabbLicit >= 100) {
                try {
                    licit(10);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                this.legutolsoLicitIdeje = LocalDateTime.now();
                this.legmagasabbLicit += 100;
                this.licitekSzama += 1;
            }

        }
    }
    public void licit(int mertek) throws Exception {
        if (mertek < 10 || mertek > 100) {
            throw new Exception("Nem 10-100 között adott meg értéket");
        } else if (this.isElkelt()) {
            System.out.println("ez a festmény már elkelt");;
        } else {
            if (this.getLegmagasabbLicit() < 100) {
                licit();
            }
            else {
                double szam = mertek;
                System.out.println(szam);
                int ertek = this.legmagasabbLicit + (int) (this.legmagasabbLicit * szam / 100);
                System.out.println(ertek);
                String szoveg = String.valueOf(ertek).toString();
                String vegeredmeny;
                StringBuilder formazottertek = new StringBuilder(String.valueOf(ertek).substring(0, 2));
                vegeredmeny = formazottertek.toString();
                for (int i = 0; i < szoveg.length() - 2; i++) {
                    vegeredmeny += "0";
                }
                this.legmagasabbLicit = Integer.parseInt(String.valueOf(vegeredmeny));
                System.out.println("" + this.getLegmagasabbLicit());
                this.licitekSzama += 1;
                this.legutolsoLicitIdeje = LocalDateTime.now();
            }
        }
    }





    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formazott;
        if (this.getLegutolsoLicitIdeje() != null) {
            formazott = this.getLegutolsoLicitIdeje().format(formatter);
        } else {
            formazott = "Még nincs";
        }
        if (this.elkelt) {
            return String.format("%s: %s (%s) \n %s \n %d$ - %s (összesen: %d)\n",
                    this.festo,
                    this.cim,
                    this.stilus,
                    "elkelt",
                    this.legmagasabbLicit,
                    formazott,
                    this.licitekSzama);
        } else {
            return String.format("%s: %s (%s) \n %d$ - %s (összesen: %d)\n",
                    this.festo,
                    this.cim,
                    this.stilus,
                    this.legmagasabbLicit,
                    formazott,
                    this.licitekSzama);
        }

    }
}
