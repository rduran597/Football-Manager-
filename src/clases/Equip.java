package clases;

import java.util.ArrayList;

public class Equip {

    protected String nombre, ciutat;
    protected String estadi; // opcional
    protected String president; // opcional
    protected int anyFundacio;

    protected int punts;
    protected int partitsGuanyats, partitsPerduts, partitsEmpatats;
    protected int golsAFavor, golsEnContra;


    // añadir entrenador al equipo
    protected Entrenador entrenador;

    // añadir un array de jugadores al equipo
    protected ArrayList<Jugador> llistaJugadors;


    // constructores
//    public Equip(String nombre, String ciutat, String estadi) {
//        this.nombre = nombre;
//        this.ciutat = ciutat;
//        this.estadi = estadi;
//    }
//
//    public Equip(String nombre, String ciutat, String estadi, String president) {
//        this.nombre = nombre;
//        this.ciutat = ciutat;
//        this.estadi = estadi;
//        this.president = president;
//    }
//
//    public Equip(String nombre, String ciutat, String estadi, int any_Fundacio) {
//        this.nombre = nombre;
//        this.ciutat = ciutat;
//        this.estadi = estadi;
//        this.any_Fundacio = any_Fundacio;
//    }
//
//    public Equip(String nombre, String ciutat, String estadi, String president, int any_Fundacio) {
//        this.nombre = nombre;
//        this.ciutat = ciutat;
//        this.estadi = estadi;
//        this.president = president;
//        this.any_Fundacio = any_Fundacio;
//    }

    public Equip(String nombre, String ciutat, String estadi, String president, int anyFundacio, Entrenador entrenador) {
        this.nombre = nombre;
        this.ciutat = ciutat;
        this.estadi = estadi;
        this.president = president;
        this.anyFundacio = anyFundacio;
        this.entrenador = null; // al poner null es porque al principio no tiene entrenador, se asigna al fichar uno
        this.llistaJugadors = new ArrayList<>();
    }

    // constructor con solo los datos obligatorios, el resto se asigna a "No indicat" o null
    public Equip(String nombre, String ciutat, int anyFundacio) {
        this(nombre, ciutat, "No indicat", "No indicat", anyFundacio, null);
    }

    // METODOS
    // calcular la media de calidad media de la plantilla
    public double calcularQualitatMitjana() {
        if (llistaJugadors == null || llistaJugadors.isEmpty()) return 0;

        double sumaTotal = 0;

        for (Jugador j : llistaJugadors) {
            sumaTotal += j.getCalidad();
        }
        return sumaTotal / llistaJugadors.size();
    }

    // getters and setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public String getEstadi() {
        return estadi;
    }

    public void setEstadi(String estadi) {
        this.estadi = estadi;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public int getAnyFundacio() {
        return anyFundacio;
    }

    public void setAnyFundacio(int anyFundacio) {
        this.anyFundacio = anyFundacio;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getPartitsGuanyats() {
        return partitsGuanyats;
    }

    public void setPartitsGuanyats(int partitsGuanyats) {
        this.partitsGuanyats = partitsGuanyats;
    }

    public int getPartitsPerduts() {
        return partitsPerduts;
    }

    public void setPartitsPerduts(int partitsPerduts) {
        this.partitsPerduts = partitsPerduts;
    }

    public int getPartitsEmpatats() {
        return partitsEmpatats;
    }

    public void setPartitsEmpatats(int partitsEmpatats) {
        this.partitsEmpatats = partitsEmpatats;
    }

    public int getGolsAFavor() {
        return golsAFavor;
    }

    public void setGolsAFavor(int golsAFavor) {
        this.golsAFavor = golsAFavor;
    }

    public int getGolsEnContra() {
        return golsEnContra;
    }

    public void setGolsEnContra(int golsEnContra) {
        this.golsEnContra = golsEnContra;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public ArrayList<Jugador> getLlistaJugadors() {
        return llistaJugadors;
    }

    public void setLlistaJugadors(ArrayList<Jugador> llistaJugadors) {
        this.llistaJugadors = llistaJugadors;
    }
}