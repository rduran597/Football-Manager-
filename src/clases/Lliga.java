package clases;

import java.util.ArrayList;

public class Lliga {

    protected String nomLliga;

    protected ArrayList<Equip> llistaEquips;


    // constructores
    public Lliga(String nomLliga) {
        this.nomLliga = nomLliga;
        this.llistaEquips = new ArrayList<>();
    }

    // METODOS
    // metodo para añadir un equipo a la liga
    public void afegirEquip(Equip equip) {
        this.llistaEquips.add(equip);
    }

    // metodo para jugar un partido entre 2 equipos, teniendo en cuenta la calidad media de cada plantilla para que gane el partido
    public void jugarPartit(Equip equipLocal, Equip equipVisitant) {
        // 1. variable para calcular la calidad media de cada equipo
        double calidadEquipLocal = equipLocal.calcularQualitatMitjana();
        double calidadEquipVisitant = equipVisitant.calcularQualitatMitjana();

        // 2. se suma la motivacion del entrenador (si tiene) a la calidad media de cada equipo
        if (equipLocal.entrenador != null) {
            calidadEquipLocal += equipLocal.entrenador.getMotivacion();
        }
        if (equipVisitant.entrenador != null) {
            calidadEquipVisitant += equipVisitant.entrenador.getMotivacion();
        }

        // 3. randomizacion para generar un numero de goles a favor para cada equipo, teniendo en cuenta la caldiad media de cada plantilla
        int golsLocals = (int) (Math.random() * (calidadEquipLocal / 15.0));
        int golsVisitants = (int) (Math.random() * (calidadEquipVisitant / 15.0));

        // 4. se actualizan los puntos, goles a favor y en contra, y partidos ganados, perdidos y empatados de cada equipo
        equipLocal.setGolsAFavor(equipLocal.getGolsAFavor() + golsLocals);
        equipLocal.setGolsEnContra(equipLocal.getGolsEnContra() + golsVisitants);

        equipVisitant.setGolsAFavor(equipVisitant.getGolsAFavor() + golsVisitants);
        equipVisitant.setGolsEnContra(equipVisitant.getGolsEnContra() + golsLocals);

        // 5. se determina el resultado del partido y se actualizan los puntos y partidos ganados, perdidos y empatados de cada equipo
        if (golsLocals > golsVisitants) {
            equipLocal.setPunts(equipLocal.getPunts() + 3);
            equipLocal.setPartitsGuanyats(equipLocal.getPartitsGuanyats() + 1);
            equipVisitant.setPartitsPerduts(equipVisitant.getPartitsPerduts() + 1);

            System.out.println("El equipo " + equipLocal.nombre + " ha ganado el partido contra " + equipVisitant.nombre);
            System.out.println("Resultado: " + equipLocal.nombre + " " + golsLocals + " - " + golsVisitants + " " + equipVisitant.nombre);

        } else if (golsVisitants > golsLocals) {
            equipVisitant.setPunts(equipVisitant.getPunts() + 3);
            equipVisitant.setPartitsGuanyats(equipVisitant.getPartitsGuanyats() + 1);
            equipLocal.setPartitsPerduts(equipLocal.getPartitsPerduts() + 1);

            System.out.println("El equipo " + equipVisitant.nombre + " ha ganado el partido contra " + equipLocal.nombre);
            System.out.println("Resultado: " + equipLocal.nombre + " " + golsLocals + " - " + golsVisitants + " " + equipVisitant.nombre);

        } else {
            equipLocal.setPunts(equipLocal.getPunts() + 1);
            equipVisitant.setPunts(equipVisitant.getPunts() + 1);
            equipLocal.setPartitsEmpatats(equipLocal.getPartitsEmpatats() + 1);
            equipVisitant.setPartitsEmpatats(equipVisitant.getPartitsEmpatats() + 1);

            System.out.println("El partido entre " + equipLocal.nombre + " y " + equipVisitant.nombre + " ha terminado en empate");
            System.out.println("Resultado: " + equipLocal.nombre + " " + golsLocals + " - " + golsVisitants + " " + equipVisitant.nombre);
        }

    }
    /**
     if (calidadEquipLocal > calidadEquipVisitant) {
     this.punts += 3;
     this.golsAFavor += 1; // se puede modificar para que sea un número aleatorio de goles a favor y en contra
     this.golsEnContra += 0;
     System.out.println("El equipo " + equipLocal.nombre + " ha ganado el partido contra " + equipVisitant.nombre);
     } else if (calidadEquipVisitant > calidadEquipLocal) {
     this.punts += 0;
     this.golsAFavor += 0;
     this.golsEnContra += 1;
     System.out.println("El equipo " + equipVisitant.nombre + " ha ganado el partido contra " + equipLocal.nombre);
     } else {
     this.punts += 1;
     this.golsAFavor += 1;
     this.golsEnContra += 1;
     System.out.println("El partido entre " + equipLocal.nombre + " y " + equipVisitant.nombre + " ha terminado en empate");
     }


     // metodo para jugar un partido entre 2 equipos, teniendo en cuenta la calidad media de cada plantilla para que gane el partido, actualizando los puntos, goles a favor y en contra, y partidos ganados, perdidos y empatados de cada equipo
     if (calidadEquipLocal > calidadEquipVisitant) {
     equipLocal.setPunts(equipLocal.getPunts() + 3);
     equipLocal.setPartitsGuanyats(equipLocal.getPartitsGuanyats() + 1);
     equipVisitant.setPartitsPerduts(equipVisitant.getPartitsPerduts() + 1);
     equipLocal.setGolsAFavor(equipLocal.getGolsAFavor() + 1); // se puede modificar para que sea un número aleatorio de goles a favor
     equipLocal.setGolsEnContra(equipLocal.getGolsEnContra() + 0); // se puede modificar para que sea un número aleatorio de goles en contra
     System.out.println("El equipo " + equipLocal.nombre + " ha ganado el partido contra " + equipVisitant.nombre);
     } else if (calidadEquipVisitant > calidadEquipLocal) {
     equipVisitant.setPunts(equipVisitant.getPunts() + 3);
     equipVisitant.setPartitsGuanyats(equipVisitant.getPartitsGuanyats() + 1);
     equipLocal.setPartitsPerduts(equipLocal.getPartitsPerduts() + 1);
     equipVisitant.setGolsAFavor(equipVisitant.getGolsAFavor() + 1); // se puede modificar para que sea un número aleatorio de goles a favor
     equipVisitant.setGolsEnContra(equipVisitant.getGolsEnContra() + 0); // se puede modificar para que sea un número aleatorio de goles en contra
     System.out.println("El equipo " + equipVisitant.nombre + " ha ganado el partido contra " + equipLocal.nombre);
     } else {
     equipLocal.setPunts(equipLocal.getPunts() + 1);
     equipVisitant.setPunts(equipVisitant.getPunts() + 1);
     equipLocal.setPartitsEmpatats(equipLocal.getPartitsEmpatats() + 1);
     equipVisitant.setPartitsEmpatats(equipVisitant.getPartitsEmpatats() + 1);
     equipLocal.setGolsAFavor(equipLocal.getGolsAFavor() + 1); // se puede modificar para que sea un número aleatorio de goles a favor
     equipLocal.setGolsEnContra(equipLocal.getGolsEnContra() + 1); // se puede modificar para que sea un número aleatorio de goles en contra (
     System.out.println("El partido entre " + equipLocal.nombre + " y " + equipVisitant.nombre + " ha terminado en empate");
     }
     } **/


    // getters y setters
    public String getNomLliga() {
        return nomLliga;
    }

    public void setNomLliga(String nomLliga) {
        this.nomLliga = nomLliga;
    }

    public ArrayList<Equip> getLlistaEquips() {
        return llistaEquips;
    }

    public void setLlistaEquips(ArrayList<Equip> llistaEquips) {
        this.llistaEquips = llistaEquips;
    }
}