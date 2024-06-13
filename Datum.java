public class Datum {
    private int dan;
    private int mesec;
    private int leto;

    private String ura;


    @Override
    public String toString() {
        return "Datum: " +
                dan +
                "/" + mesec +
                "/" + leto +
                "\n ura=" + ura+
                '\n';
    }

    public Datum(int dan, int mesec, int leto, String ura) {
        this.dan = dan;
        this.mesec = mesec;
        this.leto = leto;
        this.ura = ura;
    }

    public int getDan() {
        return dan;
    }

    public void setDan(int dan) {
        this.dan = dan;
    }

    public int getMesec() {
        return mesec;
    }

    public void setMesec(int mesec) {
        this.mesec = mesec;
    }

    public int getLeto() {
        return leto;
    }

    public void setLeto(int leto) {
        this.leto = leto;
    }

    public String getUra() {
        return ura;
    }

    public void setUra(String ura) {
        this.ura = ura;
    }
}
