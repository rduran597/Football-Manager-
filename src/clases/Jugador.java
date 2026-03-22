package clases;

import java.util.Objects;

public class Jugador extends Persona {

    private int dorsal;
    private String posicion;
    private double calidad;


    public static final String[] POSICIONES = {"POR", "DEF", "MIG", "DAV"};


    // constructores

    // este es para crear jugadores, que tengan la calidad entre 30 y 100
    public Jugador(String nombre, String apellido, String fechaNacimiento, int sueldo, int dorsal, String posicion, double calidad) {
        super(nombre, apellido, fechaNacimiento, sueldo);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.calidad = 30 + Math.random() * 70; // calidad aleatoria entre 30 y 100

    }

    // este es para los jugadores del mercado
    public Jugador(String nombre, String apellido, String fechaNacimiento, double motivacion, int sueldo, int dorsal, String posicion, double calidad) {
        super(nombre, apellido, fechaNacimiento, motivacion, sueldo);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.calidad = calidad;
    }

    // getter sand setters

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public double getCalidad() {
        return calidad;
    }

    public void setCalidad(double calidad) {
        this.calidad = calidad;
    }


    // METODOS

    // metodo de entrenamiento
    @Override
    public void entrenament() {
        super.entrenament();

        double random = Math.random();
        double incremento;

        // el jugador tiene una probabilidad del 70% de aumentar su calidad un 0.1
        if (random < 0.70) {
            incremento = 0.1;
        } else if (random < 0.90) { // el jugador tiene una probabilidad del 20% de aumentar su calidad un 0.2
            incremento = 0.2;
        } else {
            incremento = 0.3; //
        }
        this.calidad += incremento;

        // mostrar resultado
        System.out.println("El jugador " + getNombre() + " ha entrenado y su calidad ha aumentado en " + incremento + " puntos. Su nueva calidad es: " + getCalidad());
    }

    // metodo para cambiar de posicion (de momento pongo para elegir la posicion y luego la probabilidad. Pendiente de corregir (cambiar a solo tener la probabilidad de poder cambiar y se cambie a una posicion aleatoria)
    public void canviDePosicio() {
        // un 5% de probabilidades de conseguir cambiar de posicion
        if (Math.random() < 0.05) {

            // eleccion aleatoria de cualquien posicion disponible en el array POSICIONES
            int indiceAleatorio = (int) (Math.random() * POSICIONES.length);
            posicion = POSICIONES[indiceAleatorio];

            // aumentar 1 punto a la calidad del jugador si cambia de posicion
            this.calidad += 1;
            if (this.calidad > 100) this.calidad = 100;

            System.out.println("El jugador " + getNombre() + " ha cambiado la posicion de " + posicion + " a" + this.posicion + " y ha aumentado la calidad en " + this.calidad);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return dorsal == jugador.dorsal &&
                getNombre().equalsIgnoreCase(jugador.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre().toLowerCase(), dorsal);
    }

    // mostrar atributos + nuevos atributos cambiados (posicion cambiada)
    @Override
    public void mostrarAtributos() {
        super.mostrarAtributos();
        System.out.println(this.posicion);
    }
}