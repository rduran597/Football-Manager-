package clases;

public class Entrenador extends Persona {

    private int torneosGanados;
    private boolean esSeleccionador;


    // constructores


    public Entrenador(String nombre, String apellido, String fechaNacimiento, int sueldo, int torneosGanados, boolean esSeleccionador) {
        super(nombre, apellido, fechaNacimiento, sueldo);
        this.torneosGanados = torneosGanados;
        this.esSeleccionador = esSeleccionador;
    }

    public Entrenador(String nombre, String apellido, String fechaNacimiento, double motivacion, int sueldo, int torneosGanados, boolean esSeleccionador) {
        super(nombre, apellido, fechaNacimiento, motivacion, sueldo);
        this.torneosGanados = torneosGanados;
        this.esSeleccionador = esSeleccionador;
    }

    // metodos
    public void incrementSou() {
        double porcentajeAumento = 0.005;

        this.sueldo += (int) (this.sueldo * porcentajeAumento);
    }

    // metodo de entrenamiento
    @Override
    public void entrenament() {
        if (this.esSeleccionador) {
            this.motivacion += 0.3;
        } else {
            this.motivacion += 0.15;
        }
        if (this.motivacion > 10.0) {
            this.motivacion = 10.0;
        }
        System.out.println("El entrenador " + this.getNombre() + " ha entrenado y su motivación ha aumentado. Su nueva motivación es: " + this.motivacion);

    }

}