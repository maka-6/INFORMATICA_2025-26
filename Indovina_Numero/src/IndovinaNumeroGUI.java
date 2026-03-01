/*
 * Autore: Makaoui Youness
 * Classe: 4G
 * Luogo: xx
 * Data: 28/02/2026
 * Versione: 1.0
 * Descrizione:  Indovina il Numero
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class IndovinaNumeroGUI extends JFrame {

    private int numeroSegreto;
    private int tentativi;
    private int min = 1;
    private int max = 1000;

    private JLabel messaggioLabel;
    private JLabel tentativiLabel;
    private JTextField inputField;
    private JButton provaButton;
    private JButton resetButton;

    public IndovinaNumeroGUI() {
        setTitle("Indovina il Numero!");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pannello principale
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));
        panel.setBackground(new Color(230, 240, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        messaggioLabel = new JLabel("Indovina un numero tra 1 e 1000");
        messaggioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messaggioLabel.setFont(new Font("Arial", Font.BOLD, 14));

        tentativiLabel = new JLabel("Tentativi rimasti: 10");
        tentativiLabel.setHorizontalAlignment(SwingConstants.CENTER);

        inputField = new JTextField();
        inputField.setHorizontalAlignment(SwingConstants.CENTER);

        provaButton = new JButton("Prova");
        resetButton = new JButton("Nuova Partita");

        panel.add(messaggioLabel);
        panel.add(tentativiLabel);
        panel.add(new JLabel("Inserisci il numero:", SwingConstants.CENTER));
        panel.add(inputField);
        panel.add(provaButton);
        panel.add(resetButton);

        add(panel);

        iniziaNuovaPartita();

        provaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controllaTentativo();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniziaNuovaPartita();
            }
        });
    }

    // Ricrea una nuova partita
    private void iniziaNuovaPartita() {
        Random random = new Random();
        numeroSegreto = random.nextInt(1000) + 1;
        tentativi = 10;
        min = 1;
        max = 1000;

        messaggioLabel.setText("Indovina un numero tra 1 e 1000");
        tentativiLabel.setText("Tentativi rimasti: " + tentativi);
        inputField.setText("");
        inputField.setEnabled(true);
        provaButton.setEnabled(true);
    }

    // controlla i tentativi rimasti
    private void controllaTentativo() {
        try {
            int numeroInserito = Integer.parseInt(inputField.getText());

            // controllo che il numero inserito sia compreso tra min e max
            if (numeroInserito < min || numeroInserito > max) {
                messaggioLabel.setText("Inserisci un numero tra " + min + " e " + max);
                return;
            }

            tentativi--;

            // controllo se l'utente ha indovinato'
            if (numeroInserito == numeroSegreto) {
                messaggioLabel.setText("Hai indovinato!");
                finePartita();
            } else {
                // Imposto il nuovo range
                if (numeroInserito < numeroSegreto) {
                    min = numeroInserito + 1;
                } else {
                    max = numeroInserito - 1;
                }
                //
                messaggioLabel.setText("Numero tra " + min + " e " + max);
            }

            tentativiLabel.setText("Tentativi rimasti: " + tentativi);

            if (tentativi == 0 && numeroInserito != numeroSegreto) {
                messaggioLabel.setText("Hai perso! Il numero era: " + numeroSegreto);
                finePartita();
            }

            inputField.setText("");

        } catch (NumberFormatException ex) {
            messaggioLabel.setText("Inserisci un numero valido!");
        }
    }

    private void finePartita() {
        inputField.setEnabled(false);
        provaButton.setEnabled(false);
    }
}