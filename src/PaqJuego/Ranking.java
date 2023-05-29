
package PaqJuego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.SwingUtilities;

public class Ranking {
    private Map<String, Integer> puntuaciones;

    public Ranking() {
        
        puntuaciones = new HashMap<>();
        cargarPuntuaciones();
    }

    public void agregarPuntuacion(String nombre, int puntuacion) {
        if (puntuaciones.containsKey(nombre)) {
        int puntuacionExistente = puntuaciones.get(nombre);
        if (puntuacion > puntuacionExistente) {
            puntuaciones.put(nombre, puntuacion);
            guardarPuntuacion();
        }
    } else {
        puntuaciones.put(nombre, puntuacion);
        guardarPuntuacion();
        }
    }
    public void mostrarRanking() {
    SwingUtilities.invokeLater(() -> {
        RankingTable rankingTable = new RankingTable(new HashMap<>(puntuaciones));
        rankingTable.setVisible(true);
    });
}
    
    private void cargarPuntuaciones() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ranking.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nombre = parts[0];
                    int puntuacion = Integer.parseInt(parts[1]);
                if (!puntuaciones.containsKey(nombre)) {
                    puntuaciones.put(nombre, puntuacion);
                } else {
                    int puntuacionExistente = puntuaciones.get(nombre);
                    if (puntuacion > puntuacionExistente) {
                        puntuaciones.put(nombre, puntuacion);
                    }
                }
            }
        }
        reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void guardarPuntuacion() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ranking.txt", true));
            for (Map.Entry<String, Integer> entry : puntuaciones.entrySet()) {
            String nombre = entry.getKey();
            int puntuacion = entry.getValue();
            writer.write(nombre + "," + puntuacion);
            writer.newLine();
        }
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
