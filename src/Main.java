import clases.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Watchable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean sortir = false;
        boolean admin = false;


        ArrayList<Persona> mercat = new ArrayList<>();
        ArrayList<Equip> llistaEquips = new ArrayList<>();
        ArrayList<Lliga> llistaLligues = new ArrayList<>();

        // leer achivo mercat
        Path path = Paths.get("src/fitxers/mercat_fitxatges.txt");
        try {
            List<String> liniesFitxer = Files.readAllLines(path);

            for (String line : liniesFitxer) {
                String[] dades = line.split(";");

                if (dades[0].equals("J")) {
                    // se crea un nuevo jugador
                    Jugador j = new Jugador(dades[1], dades[2], dades[3], Double.parseDouble(dades[4]), Integer.parseInt(dades[5]), Integer.parseInt(dades[6]), dades[7], Double.parseDouble(dades[8]));

                    mercat.add(j);
                } else if (dades[0].equals("E")) {
                    Entrenador e = new Entrenador(dades[1], dades[2], dades[3], Double.parseDouble(dades[4]), Integer.parseInt(dades[5]), Integer.parseInt(dades[6]), Boolean.parseBoolean(dades[7]));

                    mercat.add(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }

        // login
        System.out.print("Introdueix el teu perfil (admin / gestor): ");
        String perfil = sc.nextLine().toLowerCase();

//        if (perfil.equals("admin")) {
//            admin = true;
//        }  else if (perfil.equals("gestor")) {
//            admin = false;
//        }

        // menu con las opciones de eleccion para admin
        do {
            System.out.println("\nWelcome to Politècnics Football Manager:");

            if  (admin) {
                System.out.println("\t1- Veure classificació lliga actual");
                System.out.println("\t2- Donar d'alta equip");
                System.out.println("\t3- Donar d'alta jugador/a o entrenador/a");
                System.out.println("\t4- Consultar dades equip");
                System.out.println("\t5- Consultar dades jugador/a equip");
                System.out.println("\t6- Disputar nova lliga");
                System.out.println("\t7- Realitzar sessió entrenament (del mercat fitxatges)");
                System.out.println("\t8- Desar dades equips");
                System.out.println("\t0- Sortir");
                System.out.println("\nTria una opció: ");
                int opcio = sc.nextInt();
                sc.nextLine();

                switch (opcio) {
                    case 1:
                        if (llistaLligues.isEmpty()) {
                            System.out.println("Primer has de crear una lliga");
                        } else {
                            System.out.println("Lligues disponibles: ");
                            for (int i = 0; i < llistaLligues.size(); i++) {
                                System.out.println((i + 1) + "- " + llistaLligues.get(i).getNomLliga());
                            }
                        }
                    case 2:
                        System.out.println("ALTA DE NOU EQUIP");
                        String nomEquip;
                        boolean repetit;

                        do {
                            repetit = false;
                            System.out.println("Introdueix el nombre del equip: ");
                            nomEquip = sc.nextLine();

                            for (Equip e : llistaEquips) {
                                if (e.getNombre().equals(nomEquip)) {
                                    System.out.println("Aquest equip ja existeix. Tira un altre nom");
                                    repetit = true;
                                    break;
                                }
                            }
                        } while (repetit);

                        // pedir datos si el equipo no existe
                        System.out.println("Ciutat: ");
                        String ciutat = sc.nextLine();
                        System.out.println("Any de fundació");
                        int anyFundacio = sc.nextInt();
                        sc.nextLine();

                        // datos opcionales para el equipo
                        String estadi = "No indicat";
                        System.out.println("Vols afegir el nom de l'estadi?: ");
                        if (sc.nextLine().equalsIgnoreCase("s")) {
                            System.out.println("Escriu el nom de l'estadi: ");
                            estadi = sc.nextLine();
                        }

                        String president = "No indicat";
                        System.out.println("Vols afegir el nom de l'presidente?: ");
                        if (sc.nextLine().equalsIgnoreCase("s")) {
                            System.out.println("Escriu el nom de l'presidente: ");
                            president = sc.nextLine();
                        }

                        Equip nouEquip = new Equip(nomEquip, ciutat, estadi, president, anyFundacio, null);
                        System.out.println("El equip " + nouEquip + "afegit correctament.");
                        break;

                    case 3:
                        // codigo
                        break;
                }

                    // menu con las opciones de eleccion para gestor
                } else {
                    System.out.println("1- Veure classificació lliga actual");
                    System.out.println("2- Gestionar el meu equip");
                    System.out.println("3- Consultar dades equip");
                    System.out.println("4- Consultar dades jugador/a equip");
                    System.out.println("5- Transferir jugador/a");
                    System.out.println("6 - Desar dades equips");
                    System.out.println("0- Sortir");

                    int opcio = sc.nextInt();
                    sc.nextLine();
                }

            } while (!sortir);
                }
    }