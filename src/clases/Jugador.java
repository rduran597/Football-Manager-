package clases;

public class Jugador extends Persona {

    private int dorsal;
    private double calidad;
    private String posicion;

    public static final String[] POSICIONES = {"POR", "DEF", "MIG", "DAV"};


    // constructores
    public Jugador(String nombre, String apellido, String fechaNacimiento, double sueldo, String posicion, int dorsal) {
        super(nombre, apellido, fechaNacimiento, sueldo); // super es para llamar al constructor de la clase padre (Persona)
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.calidad = 30 + Math.random() * 70; // calidad aleatoria entre 30 y 100
    }

    // getters
    public int getDorsal() {
        return dorsal;
    }

    public double getCalidad() {
        return calidad;
    }

    public String getPosicion() {
        return posicion;
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

    // cambiar de posicion (de momento pongo para elegir la posicion y luego la probabilidad. Pendiente de corregir (cambiar a solo tener la probabilidad de poder cambiar y se cambie a una posicion aleatoria)
    public void canviDePosicio(String nuevaPosicion) {
        if (nuevaPosicion.equals("POR") || nuevaPosicion.equals("DEF") || nuevaPosicion.equals("MIG") || nuevaPosicion.equals("DAV")) {

            // probabilidad del 5% para cambiar de posicion
            if (Math.random() < 0.05) {
                this.posicion = nuevaPosicion;
                this.calidad += 1;

                System.out.println("El jugador " + getNombre() + " ha cambiado de posición a " + this.posicion + " y su calidad ha aumentado en 1 punto. Su nueva calidad es: " + getCalidad());
            } else {
                System.out.println("El jugador " + getNombre() + " ha intentado cambiar de posición a " + nuevaPosicion + " pero no ha tenido éxito. Su calidad se mantiene en " + getCalidad());
            }
        } else {
            System.out.println("La posición " + nuevaPosicion + " no es válida. Las posiciones válidas son: POR, DEF, MIG, DAV.");
        }
    }

    // mostrar atributos + nuevos atributos cambiados (posicion cambiada)
    @Override
    public void mostrarAtributos() {
        super.mostrarAtributos();
        System.out.println(this.posicion);
    }
}