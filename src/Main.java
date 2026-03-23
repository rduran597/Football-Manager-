import clases.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
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

        Path equipsDesats = Paths.get("src/fitxers/equips_desats.txt");
        try {
            List<String> liniesFitxer = Files.readAllLines(equipsDesats);

            for (String line : liniesFitxer) {
                String[] dades = line.split(";");

                if (dades[0].equals("E")) {
                    // se crea un nuevo jugador
                    // Cambia el final por esto:
                    Equip e = new Equip(dades[1], dades[2], dades[3], dades[4], Integer.parseInt(dades[5]), null);

                    llistaEquips.add(e);
            }
                }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }


        // login
        System.out.print("Introdueix el teu perfil (admin / gestor): ");
        String perfil = sc.nextLine().toLowerCase();

        if (perfil.equals("admin")) {
            admin = true;
        } else if (perfil.equals("gestor")) {
            admin = false;
        }

        // menu con las opciones de eleccion para admin
        do {
            System.out.println("\nWelcome to Politècnics Football Manager:");

            if (admin) {
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
                        if (sc.nextLine().charAt(0) == 's') {
                            System.out.println("Escriu el nom de l'estadi: ");
                            estadi = sc.nextLine();
                        }

                        String president = "No indicat";
                        System.out.println("Vols afegir el nom de l'presidente?: ");
                        if (sc.nextLine().charAt(0) == 's') {
                            System.out.println("Escriu el nom de l'presidente: ");
                            president = sc.nextLine();
                        }

                        Equip nouEquip = new Equip(nomEquip, ciutat, estadi, president, anyFundacio, null);
                        llistaEquips.add(nouEquip);

                        System.out.println("El equip " + nouEquip.getNombre() + "afegit correctament.");
                        break;

                    case 3:
                        System.out.println("ALTA DE PERSONA");
                        System.out.println("Vols donar d'alta un Jugador o un Entrenador?: ");
                        String tipusPersona = sc.nextLine().toUpperCase();
                        char inicial = tipusPersona.charAt(0);

                        // datos para ambos
                        System.out.println("Nom: ");
                        String nomPersona = sc.nextLine();
                        System.out.println("Cognom: ");
                        String cognomPersona = sc.nextLine();
                        System.out.println("Data de naixament (dd/mm/aaaa): ");
                        String dataNaixament = sc.nextLine();
                        System.out.println("Sou anual: ");
                        int souAnual = sc.nextInt();
                        sc.nextLine();

                        if (inicial == 'J') {
                            System.out.println("Dorsal: ");
                            int dorsalJugador = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Posicions possibles: ");
                            for (String llistaPosicions : Jugador.POSICIONES) {
                                System.out.println(llistaPosicions);
                            }
                            System.out.print("Tria una posició: ");
                            String posicioJugador = sc.nextLine().toUpperCase();

                            Jugador nouJugador = new Jugador(nomPersona, cognomPersona, dataNaixament, souAnual, dorsalJugador, posicioJugador, 0);
                            mercat.add(nouJugador);

                            try (java.io.FileWriter fw = new java.io.FileWriter("src/fitxers/mercat_fitxatges.txt", true);
                                 java.io.PrintWriter pw = new java.io.PrintWriter(fw)) {

                                // Añadimos "\n" al principio para asegurar que la "J" NO se pegue al número anterior
                                pw.print("\nJ;" + nomPersona + ";" + cognomPersona + ";" + dataNaixament + ";" + souAnual + ";" + dorsalJugador + ";0;" + posicioJugador + ";5.0");

                            } catch (Exception e) { System.out.println("Error guardant fitxer"); }

                            System.out.println("Jugador afegit al mercat amb la motivació al 5 i la qualitat aleatòria");

                        } else if (inicial == 'E') {
                            System.out.println("Número de tornejos guanyats: ");
                            int tornejosEntrenador = sc.nextInt();
                            sc.nextLine();
                            boolean esSeleccionadorEntrenador = sc.nextBoolean();
                            sc.nextLine();

                            Entrenador nouEntrenador = new Entrenador(nomPersona, cognomPersona, dataNaixament, souAnual, tornejosEntrenador, esSeleccionadorEntrenador);
                            mercat.add(nouEntrenador);


                        } else {
                            System.out.println("opcio incorrecta");
                        }
                        break;

                    case 4:
                        System.out.println("CONSULTAR DADES D'EQUIP");
                        System.out.println("Introdueix el nom de l'equip que vols consultar: ");
                        String nomCerca = sc.nextLine();

                        Equip equipTrobat = null;

                        // cargar el contenido del array llistaEquips para buscar el nombre del equipo y encontrarlo si existe
                        for (Equip e : llistaEquips) {
                            if (nomCerca.equalsIgnoreCase(e.getNombre())) {
                                equipTrobat = e;
                                break;
                            }

                            if (equipTrobat == null) {
                                System.out.println("El equip no hi es");
                            } else {
                                System.out.println("DADES DE L'EQUIP: " + equipTrobat.getNombre().toUpperCase());
                                System.out.println("Ciutat: " + equipTrobat.getCiutat());
                                System.out.println("Any de fundació: " + equipTrobat.getAnyFundacio());
                                System.out.println("Estadi: " + equipTrobat.getEstadi());
                                System.out.println("President/a: " + equipTrobat.getPresident());

                                // mostrar la media de calidad de la plantilla
                                System.out.printf("Qualitat mitjana de la plantilla: %.2f\n", equipTrobat.calcularQualitatMitjana());

                                // mostrar datos del entrenador
                                System.out.print("Entrenador: ");
                                if (equipTrobat.getEntrenador() == null) {
                                    System.out.println("Aquest equip no te cap entrenador assignat");
                                } else {
                                    Entrenador ent = equipTrobat.getEntrenador();
                                    System.out.println(ent.getNombre() + " " + ent.getApellido());
                                    System.out.println("Motivació: " + ent.getMotivacion());
                                }

                                // 4. Mostrar lista de jugadores
                                System.out.println("Llistat de jugadors:");
                                if (equipTrobat.getLlistaJugadors() == null || equipTrobat.getLlistaJugadors().isEmpty()) {
                                    System.out.println("L'equip no té jugadors en plantilla.");
                                } else {
                                    for (Jugador jug : equipTrobat.getLlistaJugadors()) {
                                        System.out.println("   - [" + jug.getDorsal() + "] " + jug.getNombre() + " " + jug.getApellido() +
                                                " | Pos: " + jug.getPosicion() + " | Qualitat: " + String.format("%.1f", jug.getCalidad()));
                                    }
                                }
                                System.out.println("========================================\n");
                            }
                            break;
                        }
                    case 5:
                        System.out.println(" CONSULTAR JUGADOR D'UN EQUIP ");
                        System.out.print("Nom de l'equip on juga: ");
                        String nomEquipCerca = sc.nextLine();

                        Equip equipTrobatJugador = null;
                        // 1. buscar el equipo
                        for (Equip e : llistaEquips) {
                            if (nomEquipCerca.equalsIgnoreCase(e.getNombre())) {
                                equipTrobatJugador = e;
                                break;
                            }
                        }

                        if (equipTrobatJugador == null) {
                            System.out.println("L'equip no es troba a la llista");
                        } else {
                            // 2. pedir datos del jugador
                            System.out.print("Nom del jugador: ");
                            String nomJ = sc.nextLine();
                            System.out.print("Dorsal: ");
                            int dorsalJ = sc.nextInt();
                            sc.nextLine();

                            Jugador jugadorTrobat = null;

                            // comprobar si la lista de jugadores existe
                            if (equipTrobatJugador.getLlistaJugadors() != null) {
                                for (Jugador j : equipTrobatJugador.getLlistaJugadors()) {
                                    // Buscamos coincidencia de nombre y dorsal
                                    if (j.getNombre().equalsIgnoreCase(nomJ) && j.getDorsal() == dorsalJ) {
                                        jugadorTrobat = j;
                                        break;
                                    }
                                }
                            }

                            // 3. mostrar resultado
                            if (jugadorTrobat == null) {
                                System.out.println("No s'ha trobat cap jugador amb aquest nom i dorsal en aquest equip.");
                            } else {
                                System.out.println("DADES DEL JUGADOR/A:");
                                System.out.println("Nom complet: " + jugadorTrobat.getNombre() + " " + jugadorTrobat.getApellido());
                                System.out.println("Dorsal: " + jugadorTrobat.getDorsal());
                                System.out.println("Posició: " + jugadorTrobat.getPosicion());
                                System.out.println("Qualitat: " + String.format("%.1f", jugadorTrobat.getCalidad()));
                                System.out.println("Motivació: " + jugadorTrobat.getMotivacion());
                                System.out.println("Sou: " + jugadorTrobat.getSueldo() + "€");
                            }
                        }
                        break;

                    case 6:
                        System.out.println(" CREAR I DISPUTAR NOVA LLIGA ");
                        System.out.print("Introdueix el nom de la lliga: ");
                        String nomLligaNova = sc.nextLine();

                        // se crea un objeto de la clase Lliga
                        Lliga lligaCrear = new Lliga(nomLligaNova);

                        System.out.print("Quants equips participaran?: ");
                        int numEquipsParticipants = sc.nextInt();
                        sc.nextLine(); // Netejar buffer

                        // Validacion: no se puede hacer una liga de 4 equipos si solo hay 2 registrados
                        if (numEquipsParticipants > llistaEquips.size()) {
                            System.out.println("No hi ha prou equips registrats per crear aquesta lliga.");
                        } else {
                            // 1. añadir equipos a la liga
                            for (int i = 0; i < numEquipsParticipants; i++) {
                                System.out.println("Equips disponibles al sistema:");
                                for (int k = 0; k < llistaEquips.size(); k++) {
                                    System.out.println((k + 1) + ". " + llistaEquips.get(k).getNombre());
                                }

                                System.out.print("Selecciona el número de l'equip " + (i + 1) + ": ");
                                int opcioEquipEscollit = sc.nextInt();
                                sc.nextLine();

                                Equip equipTriat = llistaEquips.get(opcioEquipEscollit - 1);

                                // Control de duplicats: mirem si l'equip ja és dins de la llista d'aquesta lliga
                                if (lligaCrear.getLlistaEquips().contains(equipTriat)) {
                                    System.out.println("L'equip '" + equipTriat.getNombre() + "' ja està a la lliga. Tria'n un altre.");
                                    i--; // Restem 1 al comptador per repetir aquesta selecció
                                } else {
                                    lligaCrear.afegirEquip(equipTriat);
                                    System.out.println("Equip afegit correctament.");
                                }
                            }

                            // 2. DISPUTAR ELS PARTITS (Tots contra tots)
                            System.out.println("COMENÇA LA LLIGA: " + nomLligaNova.toUpperCase());
                            ArrayList<Equip> equipsEnCompeticio = lligaCrear.getLlistaEquips();

                            for (int i = 0; i < equipsEnCompeticio.size(); i++) {
                                // El segon bucle comença en i + 1 per no repetir partits
                                for (int j = i + 1; j < equipsEnCompeticio.size(); j++) {
                                    Equip local = equipsEnCompeticio.get(i);
                                    Equip visitant = equipsEnCompeticio.get(j);

                                    // Cridem al mètode jugarPartit que ja tens a Lliga.java
                                    lligaCrear.jugarPartit(local, visitant);
                                }
                            }

                            // Afegim la lliga acabada a la llista global
                            llistaLligues.add(lligaCrear);
                            System.out.println("La lliga '" + nomLligaNova + "' ha finalitzat amb èxit.");
                        }
                        break;

                    case 7:
                        System.out.println("INICIANT SESSIÓ D'ENTRENAMENT DEL MERCAT");

                        if (mercat.isEmpty()) {
                            System.out.println("No hi ha ningú al mercat de fitxatges per entrenar");
                        } else {
                            for (Persona p : mercat) {
                                // ahora necesitamos usar un Polimorfismo
                                p.entrenament();

                                if (p instanceof Jugador) {
                                    Jugador j = (Jugador) p;
                                    j.canviDePosicio();
                                    System.out.println("Jugador entrenat: " + j.getNombre() + " (Qualitat actual: " + String.format("%.1f", j.getCalidad()) + ")");
                                } else if (p instanceof Entrenador) {
                                    Entrenador e = (Entrenador) p;
                                    e.entrenament();
                                    System.out.println("Entrenador entrenat: " + e.getNombre() + "(Nou sou: " + e.getSueldo() + "€)");
                                }
                            }
                            System.out.println("Sessió d'entrenament finalitzada per a tot el mercat");
                        }
                        break;
                    case 8:
                        System.out.println("\n--- DESANT DADES DELS EQUIPS ---");
                        // Definim la ruta del fitxer (pots canviar el nom si vols)
                        Path path2 = Paths.get("src/fitxers/equips_desats.txt");

                        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path2))) {

                            if (llistaEquips.isEmpty()) {
                                System.out.println("No hi ha equips per desar");
                            } else {
                                for (Equip eq : llistaEquips) {
                                    // 1. Guardem dades de l'EQUIP (Prefix E)
                                    // Format: E;nom;ciutat;estadi;president;any
                                    writer.println("E;" + eq.getNombre() + ";" + eq.getCiutat() + ";" +
                                            eq.getEstadi() + ";" + eq.getPresident() + ";" + eq.getAnyFundacio());

                                    // 2. Guardem l'ENTRENADOR si en té (Prefix T de Tècnic)
                                    if (eq.getEntrenador() != null) {
                                        Entrenador ent = eq.getEntrenador();
                                        writer.println("T;" + ent.getNombre() + ";" + ent.getApellido() + ";" +
                                                ent.getSueldo() + ";" + ent.getTorneosGanados());
                                    }

                                    // 3. Guardem els JUGADORS (Prefix J)
                                    if (eq.getLlistaJugadors() != null) {
                                        for (Jugador jug : eq.getLlistaJugadors()) {
                                            writer.println("J;" + jug.getNombre() + ";" + jug.getApellido() + ";" +
                                                    jug.getDorsal() + ";" + jug.getPosicion() + ";" + jug.getCalidad());
                                        }
                                    }
                                    // Línia buida o separador opcional per llegibilitat al fitxer
                                    writer.println("---");
                                }
                                System.out.println("Dades desades correctament a: " + path2.toString());
                            }
                        } catch (IOException e) {
                            System.out.println("Error en escriure el fitxer: " + e.getMessage());
                        }
                        break;
                    case 0:
                        System.out.println("Gràcies per usar Politècnics Football Manager");
                        sortir = true;
                        break;
                }

                // menu con las opciones de eleccion para gestor
            } else { //menu de gestor
                System.out.println("1- Veure classificació lliga actual");
                System.out.println("2- Gestionar el meu equip");
                System.out.println("3- Consultar dades equip");
                System.out.println("4- Consultar dades jugador/a equip");
                System.out.println("5- Transferir jugador/a");
                System.out.println("6 - Desar dades equips");
                System.out.println("0- Sortir");

                int opcioGestor = sc.nextInt();
                sc.nextLine();

                switch (opcioGestor) {
                    case 1: // copia y pega del case 1 del admin
                        if (llistaLligues.isEmpty()) {
                            System.out.println("Primer has de crear una lliga");
                        } else {
                            System.out.println("Lligues disponibles: ");
                            for (int i = 0; i < llistaLligues.size(); i++) {
                                System.out.println((i + 1) + "- " + llistaLligues.get(i).getNomLliga());
                            }
                        }
                    case 2:
                        System.out.print("Quin equip vols gestionar? (Nom): ");
                        String nomEquipGestio = sc.nextLine();
                        Equip equipGestion = null;

                        // Busquem l'equip
                        for (Equip e : llistaEquips) {
                            if (e.getNombre().equalsIgnoreCase(nomEquipGestio)) {
                                equipGestion = e;
                                break;
                            }
                        }

                        if (equipGestion == null) {
                            System.out.println("L'equip no es troba al sistema.");
                        } else {
                            // si el equipo existe se entra al submenu
                            boolean sortirSubmenu = false;
                            do {
                                System.out.println("1- Donar de baixa l'equip");
                                System.out.println("2- Modificar president/a");
                                System.out.println("3- Destituir entrenador/a");
                                System.out.println("4- Fitxar jugador/a o entrenador/a");
                                System.out.println("0- Sortir");
                                System.out.print("Tria una opció: ");
                                int opcioSubMenu = sc.nextInt();
                                sc.nextLine();

                                switch (opcioSubMenu) {
                                    case 1: // eliminar equipo
                                        System.out.print("Estàs segur que vols eliminar l'equip? (s/n): ");
                                        if (sc.nextLine().equalsIgnoreCase("s")) {
                                            llistaEquips.remove(equipGestion);
                                            System.out.println("Equip eliminat correctament");
                                            sortirSubmenu = true;
                                        }
                                        break;

                                    case 2: // modificar el presidente
                                        if (equipGestion.getPresident().equalsIgnoreCase("No indicat")) {
                                            System.out.println("L'equip actualment no tenia cap president");
                                        }
                                        System.out.print("Nom del nou president: ");
                                        String nouPresident = sc.nextLine();

                                        if (nouPresident.equalsIgnoreCase(equipGestion.getPresident())) {
                                            System.out.println("Aquesta persona ja és el president actual");
                                        } else {
                                            equipGestion.setPresident(nouPresident);
                                            System.out.println("President actualitzat");
                                        }
                                        break;

                                    case 3: // destituir al entrenador
                                        if (equipGestion.getEntrenador() == null) {
                                            System.out.println("L'equip no té cap entrenador per destituir");
                                        } else {
                                            System.out.print("Segur que vols destituir a " + equipGestion.getEntrenador().getNombre() + "? (s/n): ");
                                            if (sc.nextLine().equalsIgnoreCase("s")) {
                                                // Passem l'entrenador al mercat abans d'esborrar-lo de l'equip
                                                mercat.add(equipGestion.getEntrenador());
                                                equipGestion.setEntrenador(null);
                                                System.out.println("Entrenador destituït i enviat al mercat");
                                            }
                                        }
                                        break;

                                    case 4: // fichar un jugador o un entrenador
                                        System.out.print("Què vols fitxar? (J per Jugador / E per Entrenador): ");
                                        String tipusFitxatge = sc.nextLine().toUpperCase();

                                        // se muestran los disponibles en el mercado
                                        System.out.println("DISPONIBLES AL MERCAT");
                                        ArrayList<Persona> candidats = new ArrayList<>();
                                        int comptador = 1;

                                        for (Persona p : mercat) {
                                            if ((tipusFitxatge.equals("J") && p instanceof Jugador) ||
                                                    (tipusFitxatge.equals("E") && p instanceof Entrenador)) {
                                                System.out.println(comptador + "- " + p.getNombre() + " " + p.getApellido());
                                                candidats.add(p);
                                                comptador++;
                                            }
                                        }

                                        if (candidats.isEmpty()) {
                                            System.out.println("No hi ha ningú disponible amb aquest perfil.");
                                        } else {
                                            System.out.print("Tria el número del fitxatge: ");
                                            int tria = sc.nextInt();
                                            sc.nextLine();

                                            Persona escollida = candidats.get(tria - 1);

                                            // ejecuta el fichaje
                                            if (escollida instanceof Jugador) {
                                                equipGestion.getLlistaJugadors().add((Jugador) escollida);
                                            } else {
                                                equipGestion.setEntrenador((Entrenador) escollida);
                                            }

                                            // se elimina del mercado
                                            mercat.remove(escollida);
                                            System.out.println("✅ Fitxatge realitzat amb èxit!");
                                        }
                                        break;

                                    case 0:
                                        sortirSubmenu = true;
                                        break;
                                }

                            } while (!sortirSubmenu);
                        }
                        break;
                    case 3:
                        System.out.println("CONSULTAR DADES D'EQUIP");
                        System.out.println("Introdueix el nom de l'equip que vols consultar: ");
                        String nomCerca = sc.nextLine();

                        Equip equipTrobat = null;

                        // cargar el contenido del array llistaEquips para buscar el nombre del equipo y encontrarlo si existe
                        for (Equip e : llistaEquips) {
                            if (nomCerca.equalsIgnoreCase(e.getNombre())) {
                                equipTrobat = e;
                                break;
                            }

                            if (equipTrobat == null) {
                                System.out.println("El equip no hi es");
                            } else {
                                System.out.println("DADES DE L'EQUIP: " + equipTrobat.getNombre().toUpperCase());
                                System.out.println("Ciutat: " + equipTrobat.getCiutat());
                                System.out.println("Any de fundació: " + equipTrobat.getAnyFundacio());
                                System.out.println("Estadi: " + equipTrobat.getEstadi());
                                System.out.println("President/a: " + equipTrobat.getPresident());

                                // mostrar la media de calidad de la plantilla
                                System.out.printf("Qualitat mitjana de la plantilla: %.2f\n", equipTrobat.calcularQualitatMitjana());

                                // mostrar datos del entrenador
                                System.out.print("Entrenador: ");
                                if (equipTrobat.getEntrenador() == null) {
                                    System.out.println("Aquest equip no te cap entrenador assignat");
                                } else {
                                    Entrenador ent = equipTrobat.getEntrenador();
                                    System.out.println(ent.getNombre() + " " + ent.getApellido());
                                    System.out.println("Motivació: " + ent.getMotivacion());
                                }

                                // 4. Mostrar lista de jugadores
                                System.out.println("Llistat de jugadors:");
                                if (equipTrobat.getLlistaJugadors() == null || equipTrobat.getLlistaJugadors().isEmpty()) {
                                    System.out.println("L'equip no té jugadors en plantilla.");
                                } else {
                                    for (Jugador jug : equipTrobat.getLlistaJugadors()) {
                                        System.out.println("   - [" + jug.getDorsal() + "] " + jug.getNombre() + " " + jug.getApellido() +
                                                " | Pos: " + jug.getPosicion() + " | Qualitat: " + String.format("%.1f", jug.getCalidad()));
                                    }
                                }
                                System.out.println("========================================\n");
                            }
                            break;

                        }
                    case 4:
                        System.out.println(" CONSULTAR JUGADOR D'UN EQUIP ");
                        System.out.print("Nom de l'equip on juga: ");
                        String nomEquipCerca = sc.nextLine();

                        Equip equipTrobatJugador = null;
                        // 1. buscar el equipo
                        for (Equip e : llistaEquips) {
                            if (nomEquipCerca.equalsIgnoreCase(e.getNombre())) {
                                equipTrobatJugador = e;
                                break;
                            }
                        }

                        if (equipTrobatJugador == null) {
                            System.out.println("L'equip no es troba a la llista");
                        } else {
                            // 2. pedir datos del jugador
                            System.out.print("Nom del jugador: ");
                            String nomJ = sc.nextLine();
                            System.out.print("Dorsal: ");
                            int dorsalJ = sc.nextInt();
                            sc.nextLine();

                            Jugador jugadorTrobat = null;

                            // comprobar si la lista de jugadores existe
                            if (equipTrobatJugador.getLlistaJugadors() != null) {
                                for (Jugador j : equipTrobatJugador.getLlistaJugadors()) {
                                    // Buscamos coincidencia de nombre y dorsal
                                    if (j.getNombre().equalsIgnoreCase(nomJ) && j.getDorsal() == dorsalJ) {
                                        jugadorTrobat = j;
                                        break;
                                    }
                                }
                            }

                            // 3. mostrar resultado
                            if (jugadorTrobat == null) {
                                System.out.println("❌ Error: No s'ha trobat cap jugador amb aquest nom i dorsal en aquest equip.");
                            } else {
                                System.out.println("----------------------------------------");
                                System.out.println("DADES DEL JUGADOR/A:");
                                System.out.println("Nom complet: " + jugadorTrobat.getNombre() + " " + jugadorTrobat.getApellido());
                                System.out.println("Dorsal: " + jugadorTrobat.getDorsal());
                                System.out.println("Posició: " + jugadorTrobat.getPosicion());
                                System.out.println("Qualitat: " + String.format("%.1f", jugadorTrobat.getCalidad()));
                                System.out.println("Motivació: " + jugadorTrobat.getMotivacion());
                                System.out.println("Sou: " + jugadorTrobat.getSueldo() + "€");
                                System.out.println("----------------------------------------");
                            }
                        }
                        break;
                    case 5:
                        System.out.println("TRANSFERIR JUGADOR");
                        System.out.print("Equip origen: ");
                        String ori = sc.nextLine();
                        System.out.print("Equip desti: ");
                        String des = sc.nextLine();

                        Equip equipsOrigen = null;
                        Equip equipDesti = null;

                        for (Equip e : llistaEquips) {
                            if (e.getNombre().equalsIgnoreCase(ori)) equipsOrigen = e;
                            if (e.getNombre().equalsIgnoreCase(des)) equipDesti = e;
                        }

                        if (equipsOrigen == null || equipDesti == null) {
                            System.out.println("Error: Un dels equips no existeix");
                        } else {
                            System.out.print("Nom jugador: ");
                            String nJ = sc.nextLine();
                            System.out.print("Dorsal actual: ");
                            int dJ = sc.nextInt();
                            sc.nextLine();

                            Jugador jTr = null;
                            for (Jugador j : equipsOrigen.getLlistaJugadors()) {
                                if (j.getNombre().equalsIgnoreCase(nJ) && j.getDorsal() == dJ) {
                                    jTr = j;
                                    break;
                                }
                            }

                            if (jTr == null) {
                                System.out.println("Error: Jugador no trobat");
                            } else {
                                // mirar si el dorsal esta disponible
                                boolean repetit;
                                int nouD = jTr.getDorsal();
                                do {
                                    repetit = false;
                                    for (Jugador jd : equipDesti.getLlistaJugadors()) {
                                        if (jd.getDorsal() == nouD) {
                                            repetit = true;
                                            break;
                                        }
                                    }
                                    if (repetit) {
                                        System.out.print("Dorsal ocupat al desti. Posa un altre: ");
                                        nouD = sc.nextInt();
                                        sc.nextLine();
                                    }
                                } while (repetit);

                                // mover al jugador
                                jTr.setDorsal(nouD);
                                equipsOrigen.getLlistaJugadors().remove(jTr);
                                equipDesti.getLlistaJugadors().add(jTr);
                                System.out.println("Transferencia feta");
                            }
                        }
                        break;
                    case 6:
                        System.out.println("\n--- DESANT DADES DELS EQUIPS ---");
                        // Definim la ruta del fitxer (pots canviar el nom si vols)
                        Path path2 = Paths.get("src/fitxers/equips_desats.txt");

                        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path2))) {

                            if (llistaEquips.isEmpty()) {
                                System.out.println("No hi ha equips per desar");
                            } else {
                                for (Equip eq : llistaEquips) {
                                    // 1. Guardem dades de l'EQUIP (Prefix E)
                                    // Format: E;nom;ciutat;estadi;president;any
                                    writer.println("E;" + eq.getNombre() + ";" + eq.getCiutat() + ";" +
                                            eq.getEstadi() + ";" + eq.getPresident() + ";" + eq.getAnyFundacio());

                                    // 2. Guardem l'ENTRENADOR si en té (Prefix T de Tècnic)
                                    if (eq.getEntrenador() != null) {
                                        Entrenador ent = eq.getEntrenador();
                                        writer.println("T;" + ent.getNombre() + ";" + ent.getApellido() + ";" +
                                                ent.getSueldo() + ";" + ent.getTorneosGanados());
                                    }

                                    // 3. Guardem els JUGADORS (Prefix J)
                                    if (eq.getLlistaJugadors() != null) {
                                        for (Jugador jug : eq.getLlistaJugadors()) {
                                            writer.println("J;" + jug.getNombre() + ";" + jug.getApellido() + ";" +
                                                    jug.getDorsal() + ";" + jug.getPosicion() + ";" + jug.getCalidad());
                                        }
                                    }
                                    // Línia buida o separador opcional per llegibilitat al fitxer
                                    writer.println("---");
                                }
                                System.out.println("Dades desades correctament a: " + path2.toString());
                            }
                        } catch (IOException e) {
                            System.out.println("Error en escriure el fitxer: " + e.getMessage());
                        }
                        break;
                    case 0:
                        System.out.println("Gràcies per usar Politècnics Football Manager");
                        sortir = true;
                        break;
                    }
                }
            } while (!sortir) ;

            sc.close();
            System.out.println("Programa tancat");
        }
    }