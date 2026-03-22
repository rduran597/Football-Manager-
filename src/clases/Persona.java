package clases;

public abstract class Persona {

    protected final String nombre, apellido, fechaNacimiento;
    protected double motivacion;
    protected int sueldo;
    private static int contadorPersonas = 0;

    // constructores
    protected Persona(String nombre, String apellido, String fechaNacimiento, int sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.motivacion = 5.0;
        this.sueldo = sueldo;

        contadorPersonas++;
    }

    public Persona(String nombre, String apellido, String fechaNacimiento, double motivacion, int sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.motivacion = motivacion;
        this.sueldo = sueldo;

        contadorPersonas++;
    }

    // metodo entrenamiento
    public void entrenament() {
        this.motivacion += 0.2;
        if (this.motivacion > 10.0) {
            this.motivacion = 10.0;
        }
    }

    // getters
    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getMotivacion() {
        return motivacion;
    }

    public double getSueldo() {
        return sueldo;
    }


    // metodos
    public static void mostrarContadorPersonas() {
        System.out.println("El número de personas creadas es: " + contadorPersonas);
    }

    public void mostrarAtributos() {
        System.out.println("Los datos son:");
        System.out.println(this.nombre);
        System.out.println(this.apellido);
        System.out.println(this.fechaNacimiento);
        System.out.println(this.motivacion);
        System.out.println(this.sueldo);

    }

}
