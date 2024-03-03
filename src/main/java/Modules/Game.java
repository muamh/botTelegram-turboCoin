package Modules;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends JFrame implements ActionListener {

    private JButton[][] gemme;
    private int righe = 16;
    private int colonne = 16;
    private int dimensioneGemma = 30;
    private int punteggio = 0;
    private JLabel etichettaPunteggio;
    private int mosseRimanenti = 5;
    private JLabel etichettaMosse;

    private Color[] colori = {Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE, new Color(165, 42, 42)}; // Marrone

    private List<Point> gemmeSelezionate;

    public Game() {
        inizializzaGioco();
        preparaGUI();
        aggiornaEtichettaMosse();
    }

    private void inizializzaGioco() {
        gemme = new JButton[righe][colonne];
        gemmeSelezionate = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                int indiceColoreCasuale = random.nextInt(colori.length);
                gemme[i][j] = new JButton();
                gemme[i][j].setBackground(colori[indiceColoreCasuale]);
                gemme[i][j].setPreferredSize(new Dimension(dimensioneGemma, dimensioneGemma));
                gemme[i][j].addActionListener(this);
            }
        }
    }

    private void preparaGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pannelloGriglia = new JPanel(new GridLayout(righe, colonne));
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                pannelloGriglia.add(gemme[i][j]);
            }
        }

        etichettaPunteggio = new JLabel("Punteggio: " + punteggio);
        etichettaPunteggio.setHorizontalAlignment(JLabel.CENTER);
        add(etichettaPunteggio, BorderLayout.NORTH);

        add(pannelloGriglia, BorderLayout.CENTER);

        pack();

        etichettaMosse = new JLabel("Mosse: " + mosseRimanenti);
        etichettaMosse.setHorizontalAlignment(JLabel.CENTER);
        add(etichettaMosse, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void aggiornaEtichettaMosse() {
        etichettaMosse.setText("Mosse: " + mosseRimanenti);
    }
    /*Inizializzatore*/
    public static void avviaGioco() {
        SwingUtilities.invokeLater(() -> new Game());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bottoneCliccato = (JButton) e.getSource();
        Point puntoCliccato = trovaBottone(bottoneCliccato);

        // Logica per la gestione delle combinazioni...
        if (puntoCliccato != null) {
            gemmeSelezionate.add(puntoCliccato);
            aggiornaColoriSelezione();

            if (gemmeSelezionate.size() == 2) {
                scambiaGemme();
                resettaSelezione();
                controllaCombinazioni();
                decrementaMosse(); // Decrementa il numero di mosse dopo ogni mossa
                aggiornaEtichettaMosse(); // Aggiorna l'etichetta delle mosse
                controllaFineGioco(); // Verifica se il gioco è finito
            }
        }
    }

    private void decrementaMosse() {
        mosseRimanenti--;

    }

    private void controllaFineGioco() {
        if (mosseRimanenti == 0) {
            JOptionPane.showMessageDialog(this, "Game Over");
            // Disabilita l'input dell'utente o esegui altre azioni necessarie
            for (int i = 0; i < righe; i++) {
                for (int j = 0; j < colonne; j++) {
                    gemme[i][j].setEnabled(false);
                }
            }
        }
    }

    private Point trovaBottone(JButton bottone) {
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                if (gemme[i][j] == bottone) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private void aggiornaColoriSelezione() {
        if (gemmeSelezionate.size() == 2) {
            Point p1 = gemmeSelezionate.get(0);
            Point p2 = gemmeSelezionate.get(1);

            Color coloreTemp = gemme[p1.x][p1.y].getBackground();
            gemme[p1.x][p1.y].setBackground(gemme[p2.x][p2.y].getBackground());
            gemme[p2.x][p2.y].setBackground(coloreTemp);
        }
    }

    private void scambiaGemme() {
        if (gemmeSelezionate.size() == 2) {
            Point p1 = gemmeSelezionate.get(0);
            Point p2 = gemmeSelezionate.get(1);

            Color coloreTemp = gemme[p1.x][p1.y].getBackground();
            gemme[p1.x][p1.y].setBackground(gemme[p2.x][p2.y].getBackground());
            gemme[p2.x][p2.y].setBackground(coloreTemp);

            // Dopo lo swap, verifica se ci sono combinazioni
            List<Point> daRimuovere = trovaCombinazioni();
            if (!daRimuovere.isEmpty()) {
                int punti = calcolaPunti(daRimuovere.size());
                aggiornaPunteggio(punti);
                rimuoviCombinazioni(daRimuovere);
                riempiSpaziVuoti();
            } else {
                // Se non ci sono combinazioni, ripristina lo scambio
                coloreTemp = gemme[p1.x][p1.y].getBackground();
                gemme[p1.x][p1.y].setBackground(gemme[p2.x][p2.y].getBackground());
                gemme[p2.x][p2.y].setBackground(coloreTemp);
            }
        }
    }

    private void resettaSelezione() {
        gemmeSelezionate.clear();
    }

    private void controllaCombinazioni() {
        List<Point> daRimuovere = trovaCombinazioni();

        if (!daRimuovere.isEmpty()) {
            int punti = calcolaPunti(daRimuovere.size());
            aggiornaPunteggio(punti);
            rimuoviCombinazioni(daRimuovere);
            riempiSpaziVuoti();
        }
    }

    private List<Point> trovaCombinazioni() {
        List<Point> daRimuovere = new ArrayList<>();

        for (Point punto : gemmeSelezionate) {
            List<Point> comboOrizzontale = trovaComboOrizzontale(punto, gemme[punto.x][punto.y].getBackground());
            List<Point> comboVerticale = trovaComboVerticale(punto, gemme[punto.x][punto.y].getBackground());

            if (comboOrizzontale.size() >= 3) {
                daRimuovere.addAll(comboOrizzontale);
            }

            if (comboVerticale.size() >= 3) {
                daRimuovere.addAll(comboVerticale);
            }
        }

        return daRimuovere;
    }

    private void rimuoviCombinazioni(List<Point> daRimuovere) {
        for (Point punto : daRimuovere) {
            gemme[punto.x][punto.y].setBackground(Color.WHITE);
        }
    }

    private void riempiSpaziVuoti() {
        for (int col = 0; col < colonne; col++) {
            int contatoreVuoti = 0;

            for (int riga = righe - 1; riga >= 0; riga--) {
                if (gemme[riga][col].getBackground().equals(Color.WHITE)) {
                    contatoreVuoti++;
                } else if (contatoreVuoti > 0) {
                    Color colore = gemme[riga][col].getBackground();
                    gemme[riga + contatoreVuoti][col].setBackground(colore);
                    gemme[riga][col].setBackground(Color.WHITE);
                }
            }

            // Riempire gli spazi vuoti con nuove gemme
            for (int i = 0; i < contatoreVuoti; i++) {
                int indiceColoreCasuale = new Random().nextInt(colori.length);
                gemme[i][col].setBackground(colori[indiceColoreCasuale]);
            }
        }
    }

    private int calcolaPunti(int dimensioneCombo) {
        if (dimensioneCombo == 3) {
            return 10;
        } else if (dimensioneCombo == 4) {
            return 15;
        } else if (dimensioneCombo >= 5) {
            return 25;
        } else {
            return 0;
        }
    }

    private void aggiornaPunteggio(int punti) {
        punteggio += punti;
        mosseRimanenti = 6;
        aggiornaEtichettaPunteggio();
    }

    private void aggiornaEtichettaPunteggio() {
        etichettaPunteggio.setText("Punteggio: " + punteggio);
    }

    private List<Point> trovaComboOrizzontale(Point punto, Color colore) {
        List<Point> combo = new ArrayList<>();
        int riga = punto.x;
        int colonna = punto.y;

        // Controlla a sinistra
        for (int i = colonna; i >= 0 && gemme[riga][i].getBackground().equals(colore); i--) {
            combo.add(new Point(riga, i));
        }

        // Controlla a destra
        for (int i = colonna + 1; i < colonne && gemme[riga][i].getBackground().equals(colore); i++) {
            combo.add(new Point(riga, i));
        }

        return combo;
    }

    private List<Point> trovaComboVerticale(Point punto, Color colore) {
        List<Point> combo = new ArrayList<>();
        int riga = punto.x;
        int colonna = punto.y;

        // Controlla sopra
        for (int i = riga; i >= 0 && gemme[i][colonna].getBackground().equals(colore); i--) {
            combo.add(new Point(i, colonna));
        }

        // Controlla sotto
        for (int i = riga + 1; i < righe && gemme[i][colonna].getBackground().equals(colore); i++) {
            combo.add(new Point(i, colonna));
        }

        return combo;
    }

    private class AscoltatoreMouseGemma extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton bottoneCliccato = (JButton) e.getSource();
            Point puntoCliccato = trovaBottone(bottoneCliccato);

            if (puntoCliccato != null) {
                if (gemmeSelezionate.isEmpty()) {
                    gemmeSelezionate.add(puntoCliccato);
                } else {
                    Point primaSelezionata = gemmeSelezionate.get(0);
                    if (sonoAdiacenti(puntoCliccato, primaSelezionata)) {
                        gemmeSelezionate.add(puntoCliccato);
                        scambiaGemme();
                        resettaSelezione();
                        controllaCombinazioni();
                    } else {
                        resettaSelezione();
                        gemmeSelezionate.add(puntoCliccato);
                    }
                }
            }
        }

        private boolean sonoAdiacenti(Point p1, Point p2) {
            return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y) == 1;
        }
    }
}