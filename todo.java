public class todo {
    private String naslov;
    private String opis;
    private Datum datum;

    public todo(String naslov, String opis, Datum datum) {
        this.naslov = naslov;
        this.opis = opis;
        this.datum = datum;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Datum getDatum() {
        return datum;
    }

    public void setDatum(Datum datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "Opravilo:\n" +
                " \u2022 naslov:" + naslov + '\n' +
                " \u2022 opis:" + opis + '\n' +
                " \u2022 " + datum;
    }
}
