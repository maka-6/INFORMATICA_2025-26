/*
 * Autore: Makaoui youness
 * Classe: 4G
 * Data: 12/04/2026
 * Versione: 1.0
 * Luogo: xx
 * Descrizione:
 */

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ImpiccatoGUI extends JFrame{

    // DATI DEL GIOCO
    private String word;
    private char[] state;
    private int attempts = 6;
    private String[] words = {"java","gatto","Galactus","aurafarmer"};

    // COMPONENTI GRAFICI
    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JTextField input;
    private JButton click;
    private TextArea output;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem AdminMenuItem;
    private JPanel wordPanel;
    private JPanel attemptsPanel;
    private JPanel feedBackPanel;
    private String attemptsGUI;

    public ImpiccatoGUI() {
        // parola scelta casualmente
        word = words[(int)(Math.random()*words.length)];
        drawAttemptsGUI(attempts);
        initializeGame();
        creaFinestra();
    }

    private void initializeGame() {
        state = new char[word.length()];
        for (int i = 0; i < state.length; i++) {
            state[i] = '_';
        }
    }

    private void creaFinestra() {
        super.setTitle("Impiccato");

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,50));

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        optionsMenu = new JMenu("Opzioni");
        menuBar.add(optionsMenu);
        AdminMenuItem = new JMenuItem("Admin");
        optionsMenu.add(AdminMenuItem);

        AdminMenuItem.addActionListener(e -> {
            //
            JDialog secondaria = new JDialog(this, "Parole Segrete", true); // 'this' la lega alla principale
            secondaria.setSize(250, 150);
            secondaria.setLayout(new FlowLayout());
            secondaria.add(new JLabel(Arrays.toString(words)));
            secondaria.setLocationRelativeTo(this); // La centra sopra il gioco
            secondaria.setVisible(true);
        });

        wordPanel = new JPanel();
        wordLabel = new JLabel(getWord());
        wordPanel.add(wordLabel);
        add(wordPanel, BorderLayout.NORTH);

        attemptsPanel = new JPanel();
        attemptsPanel.setLayout(new BoxLayout(attemptsPanel, BoxLayout.Y_AXIS)); // Imposta incolonamento
        attemptsLabel = new JLabel(attemptsGUI);
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra orizzontalmente
        attemptsPanel.add(attemptsLabel);

        input = new JTextField(1);
        input.setMaximumSize(new Dimension(50, 30)); // Blocca la dimensione
        input.setAlignmentX(Component.CENTER_ALIGNMENT);
        attemptsPanel.add(input);

        click = new JButton("Invia");
        click.setAlignmentX(Component.CENTER_ALIGNMENT);
        click.addActionListener(e -> checkLetter());
        attemptsPanel.add(click);

        add(attemptsPanel, BorderLayout.CENTER);

        feedBackPanel = new JPanel();
        output = new TextArea(5, 20);
        output.setEditable(false);
        output.setText("Benvenuto!\nInserisci 1 carattere \nalla volta.");
        feedBackPanel.add(output);
        add(feedBackPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void checkLetter() {
        String testo = input.getText().trim().toUpperCase(); // Prendi l'input e pulisci spazi
        if (testo.isEmpty()) return;

        char lettera = testo.charAt(0);
        input.setText("");

        boolean trovato = false;

        // Trasforma la parola in MAIUSCOLO per il confronto
        String wordUpper = word.toUpperCase();

        for (int i = 0; i < wordUpper.length(); i++) {
            if (wordUpper.charAt(i) == lettera) {
                state[i] = word.charAt(i); // Salva il carattere originale (mantiene il case)
                trovato = true;
            }
        }

        if (!trovato) {
            attempts--;
            drawAttemptsGUI(attempts);
        }

        updateDisplay();
        controllaFineGioco();
    }

    private void updateDisplay() {
        wordLabel.setText(getWord());
        attemptsLabel.setText(attemptsGUI);
    }

    private void controllaFineGioco() {
        if (!new String(state).contains("_")) {
            output.setText("Hai vinto!");
        }

        if (attempts == 0) {
            output.setText("Hai perso!");
            click.setEnabled(false);
        }
    }

    private String getWord() {
        String s = "";
        for (char c : state) {
            s += c + " ";
        }
        return s;
    }

    private void drawAttemptsGUI(int attempts) {
        attemptsGUI = "";
        for (int i = 0; i < attempts; i++) {
            attemptsGUI += "X";
        }
    }

    public static void main(String[] args) {
        new ImpiccatoGUI();
    }
}