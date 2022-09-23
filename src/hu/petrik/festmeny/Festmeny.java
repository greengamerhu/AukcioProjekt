package hu.petrik.festmeny;

import java.time.LocalDate;

public class Festmeny {
    private String cim;
    private  String festo;
    private String stilus;
    private int licitekSzama;
    private int legmagasabbLicit;
    private LocalDate legutolsoLicitIdeje;
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

    public LocalDate getLegutolsoLicitIdeje() {
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
                licit(10);

            } else {
                this.legutolsoLicitIdeje = LocalDate.now();
                this.legmagasabbLicit += 100;
                this.licitekSzama += 1;
            }

        }
    }
    public void licit(int mertek) {
        if (mertek < 10 || mertek > 100) {
            System.out.println("Hiba");
        } else {
            double szam = mertek;
            int ertek = this.legmagasabbLicit + (int) (this.legmagasabbLicit * szam / 100);
            String szoveg = String.valueOf(ertek).toString();
            String vegeredmeny;
            StringBuilder formazottertek = new StringBuilder(String.valueOf(ertek).substring(0,2));
            vegeredmeny = formazottertek.toString();
            for (int i = 0; i < szoveg.length()-2; i++) {
                vegeredmeny += "0";
            }
            this.legmagasabbLicit =  Integer.parseInt(String.valueOf(vegeredmeny));
            System.out.println("" + this.legmagasabbLicit);
            this.licitekSzama += 1;
            this.legutolsoLicitIdeje = LocalDate.now();
        }
    }

    @Override
    public String toString() {
        if (this.elkelt) {
            return String.format("%s: %s (%s) \n %s \n %d$ - %s (összesen: %d)",
                    this.festo,
                    this.cim,
                    this.stilus,
                    "elkelt",
                    this.legmagasabbLicit,
                    this.legutolsoLicitIdeje,
                    this.licitekSzama);
        } else {
            return String.format("%s: %s (%s) \n %d$ - %s (összesen: %d)",
                    this.festo,
                    this.cim,
                    this.stilus,
                    this.legmagasabbLicit,
                    this.legutolsoLicitIdeje,
                    this.licitekSzama);
        }

    }
}
