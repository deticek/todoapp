import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    static ArrayList<todo> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean zas=true;
        while (zas) {
            navodila();
            switch(sc.nextLine().toUpperCase()){
                case "P"-> izpis();
                case "D" -> dodaj();
                case "O" -> odstrani();
                case "U" -> ureddi();
                default -> zas=false;
            }

            System.out.println(list);
        }

    }

    public static void navodila(){
        String asdf= """
                Vnesite:
                    \u2022 (P)regled opravil
                    \u2022 (D)odaj opravilo
                    \u2022 (O)dstrani opravilo
                    \u2022 (U)redi opravilo
                    \u2022 (Q)uit program
                 Podajte samo prvo črko:""";
        System.out.println(asdf+" ");
    }

    public static void dodaj(){
        boolean zas = true;
        String naslov;
        while (zas) {
            boolean exists = false;
            do {
                System.out.println("Vnesite naslov opravila");
                naslov = sc.nextLine();
                if(naslov.isEmpty()){
                    exists = true;
                    System.out.println("""
                            Naslov opravila ne sme bit prazen!
                                Če želite poskusiti ponovno, vnesite: 1
                                Če želite nazaj na glavni menu, vnesite karkoli drugega!""");
                    String input = sc.nextLine();
                    if (!input.equals("1")) {
                        return;
                    }
                    break;
                }
            }while(naslov.isEmpty());

            for (todo todos : list) {
                if (todos.getNaslov().equalsIgnoreCase(naslov)) {
                    exists = true;
                    System.out.println("""
                            Opravilo z tem naslovom že obstaja!
                                Če želite poskusiti ponovno, vnesite: 1
                                Če želite nazaj na glavni menu, vnesite karkoli drugega!""");
                    String input = sc.nextLine();
                    if (!input.equals("1")) {
                        return;
                    }
                    break;
                }
            }

            if (!exists) {
                System.out.println("Vnesite opis opravila");
                String opis = sc.nextLine();

                Datum nd=pridobidatum();

                todo novoOpravilo = new todo(naslov, opis, nd);

                int ndl=nd.getLeto();

                for(todo t : list){

                    Datum d=t.getDatum();
                    int ld=d.getLeto();
                    if(ld>ndl){
                        if(d.getMesec()<nd.getMesec()){
                            if(d.getMesec()==nd.getDan()){
                                list.add(novoOpravilo);
                                zas=false;
                                return;
                            }
                        }
                    }

                }

                list.add(novoOpravilo);
                System.out.println("Opravilo je bilo dodano.\n");
                zas = false;
            }
        }
    }

    public static Datum pridobidatum() {

        LocalDate today = LocalDate.now();
        int dan = today.getDayOfMonth();
        int mesec = today.getMonthValue();
        int leto = today.getYear();

        print("Podajte datum do gdaj more bit opravilo opravljeno");

        int d;
        do{
            print("Podajte prvo dan: ");
            String prvo = sc.nextLine();
            if(prvo.isEmpty()){
                d=00;
            }else {
                d = Integer.parseInt(prvo);
            }
        }while(d<0&&d>31);
        int m;
        do{
            print("Podajte mesec:");
            String prvo = sc.nextLine();
            if(prvo.isEmpty()){
                m=00;
            }else {
                m = Integer.parseInt(prvo);
            }
            if(m<1&&m>12){
                print("Podati morate številko med 1 in 12");
            }
        }while(m<1&&m>12);
        int l;

        do{
            print("Podajte leto: ");
            String prvo = sc.nextLine();
            if(prvo.isEmpty()){
                l=0000;
            }else {
                l = Integer.parseInt(prvo);
            }
            if(l<2024 && l>9999){
                print("Podati morate veljavno leto!");
            }
        }while(l<2024 && l>9999);


        print("""
                Podajte uro gdaj more bit opravilo končano
                Podajte takšen vnos ura:min:sec
                Lahko pustite tudi prazno""");
        String u=sc.nextLine();
        if(u.isEmpty()){
            u="00:00:00";
        }

        if(d==0 && m==0 &&l==0){
            d=dan;
            m=mesec;
            l=leto;
        }

        Datum date =new Datum(d,m,l,u);
        return date;
    }

    public static void odstrani(){
        izpis();
        int st;
        print("Katero opravilo želite odstranit? \n za preklic urejanja vnesite: 0");
        st = Integer.parseInt(sc.nextLine());
        if(st==0){
            return;
        }
        list.remove(st-1);
        print("Opravilo je bilo uspešno odstranjeno");
    }

    public static void ureddi(){
        izpis();
        int st;

            print("Katero opravilo zelite spremenit? \n za preklic urejanja vnesite: 0");
            st = Integer.parseInt(sc.nextLine());
            if(st==0){
                return;
            }


        todo t=list.get(st-1);

        print("""
                Kaj zelite spremenit:
                    \u2022 1. Naslov
                    \u2022 2. Opis opravila
                    \u2022 3. Datum opravila
                Vnesite stevilko""");
        String s;
        switch (Integer.parseInt(sc.nextLine())){
            case 1 ->{
                boolean zas = true;
                String naslov = "";
                while (zas) {
                    boolean exists = false;
                    do {
                        System.out.println("Vnesite naslov opravila");
                        naslov = sc.nextLine();
                        s=naslov;
                        if (naslov.isEmpty()) {
                            exists = true;
                            System.out.println("""
                                    Naslov opravila ne sme bit prazen!
                                        Če želite poskusiti ponovno, vnesite: 1
                                        Če želite nazaj na glavni menu, vnesite karkoli drugega!""");
                            String input = sc.nextLine();
                            if (!input.equals("1")) {
                                return;
                            }
                            break;
                        }else{
                            zas=false;
                        }
                    } while (naslov.isEmpty());

                    for (todo todos : list) {
                        if (todos.getNaslov().equalsIgnoreCase(naslov)) {
                            exists = true;
                            System.out.println("""
                                    Opravilo z tem naslovom že obstaja!
                                        Če želite poskusiti ponovno, vnesite: 1
                                        Če želite nazaj na glavni menu, vnesite karkoli drugega!""");
                            String input = sc.nextLine();
                            if (!input.equals("1")) {
                                return;
                            }
                            break;
                        }
                    }
                }
                t.setNaslov(naslov);
            }
            case 2->{
                boolean zas = true;
                String naslov="";
                while (zas) {
                    boolean exists = false;
                    do {
                        System.out.println("Vnesite opis opravila");
                        naslov = sc.nextLine();
                        if (naslov.isEmpty()) {
                            System.out.println("""
                                   Oopis opravila ne sme bit prazen!
                                        Če želite poskusiti ponovno, vnesite: 1
                                        Če želite nazaj na glavni menu, vnesite karkoli drugega!""");
                            String input = sc.nextLine();
                            if (!input.equals("1")) {
                                return;
                            }
                            break;
                        }{
                            zas=false;
                        }
                    } while (naslov.isEmpty());

                    for (todo todos : list) {
                        if (todos.getOpis().equalsIgnoreCase(naslov)) {
                            exists = true;
                            System.out.println("""
                                    Opravilo z tem opisom že obstaja!
                                        Če želite poskusiti ponovno, vnesite: 1
                                        Če želite nazaj na glavni menu, vnesite karkoli drugega!""");
                            String input = sc.nextLine();
                            if (!input.equals("1")) {
                                return;
                            }
                            break;
                        }
                    }
                }
                t.setOpis(naslov);
            }
            case 3-> {
                Datum nd=pridobidatum();

                t.setDatum(nd);

            }
            default -> {
                return;
            }
        }

    }

    public static void print(String a){
        System.out.println(a);
    }

    public static void izpis(){

        if(list.size()==0){
            print("Nimate dobenih opravil!");
        }else{
            int i=1;
            for(todo t:list){
                print(String.valueOf(i+". "+t.toString()));
                i++;
            }
        }


    }
}