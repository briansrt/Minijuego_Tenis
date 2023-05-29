
package PaqJuego;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

@SuppressWarnings("serial")
public class RankingTable extends JFrame {

    private Map<String, Integer> puntuaciones;

    public RankingTable(Map<String, Integer> puntuaciones) {
        this.puntuaciones = puntuaciones;

        setTitle("Ranking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(puntuaciones.size(), 2));

        // Ordenar las puntuaciones por valor de forma descendente
        List<Map.Entry<String, Integer>> sortedPuntuaciones = new ArrayList<>(puntuaciones.entrySet());
        sortedPuntuaciones.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> entry : sortedPuntuaciones) {
            JLabel nombreLabel = new JLabel(entry.getKey());
            nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel puntuacionLabel = new JLabel(entry.getValue().toString());
            puntuacionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            panel.add(nombreLabel);
            panel.add(puntuacionLabel);

        }

        JScrollPane scrollPane = new JScrollPane(panel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

}
