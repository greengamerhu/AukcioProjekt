package hu.petrik.festmeny;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Festmenyek {
    private List<Festmeny> festmenyek = new ArrayList<>();
    public Festmenyek(String fajlNev) throws IOException {
        festmenyek  = new ArrayList<>();
        FileReader fr = new FileReader(fajlNev);
        BufferedReader br = new BufferedReader(fr);
        String sor = br.readLine();
        while (sor != null) {
            String[] adatok = sor.split(";");
            Festmeny festmeny = new Festmeny(adatok[1], adatok[0], adatok[2]);
            this.festmenyek.add(festmeny);
            sor = br.readLine();
        }
        br.close();
        fr.close();
    }
    public List<Festmeny> getLista() {
        return this.festmenyek;
    }
}
