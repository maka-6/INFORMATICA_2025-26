import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class AppMenuFile extends JFrame {
    private JLabel labelNomeFile;

    public AppMenuFile() {
        // Impostazioni del Frame
        setTitle("Esempio JMenuBar e JFileChooser");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Creazione della JLabel
        labelNomeFile = new JLabel("Nessun file selezionato", SwingConstants.CENTER);
        add(labelNomeFile, BorderLayout.CENTER);

        // Creazione della Barra dei Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem voceApri = new JMenuItem("Apri");

        // Azione del menu "Apri"
        voceApri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int risultato = fileChooser.showOpenDialog(AppMenuFile.this);

                if (risultato == JFileChooser.APPROVE_OPTION) {
                    File fileSelezionato = fileChooser.getSelectedFile();
                    labelNomeFile.setText("File selezionato: " + fileSelezionato.getName());
                }
            }
        });

        // Assemblaggio Menu
        menuFile.add(voceApri);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        new AppMenuFile().setVisible(true);
    }
}
