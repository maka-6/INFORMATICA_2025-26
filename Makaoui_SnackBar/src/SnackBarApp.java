import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SnackBarApp extends JFrame {

    // COMPONENTI
    private JTextField txtNome;
    private JComboBox<String> comboProdotto;
    private JCheckBox chkFormaggio, chkInsalata, chkPomodoro;
    private JRadioButton acqua, coca, succo;
    private ButtonGroup gruppoBevande;
    private JTextArea area;

    public SnackBarApp() {
        setTitle("SnackBar");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        initComponents();
        setVisible(true);
    }

    public static void main(String[] args) {
        new SnackBarApp(); // MAIN PULITO
    }

    // 🔹 Inizializzazione GUI
    private void initComponents() {

        txtNome = new JTextField(15);

        comboProdotto = new JComboBox<>(new String[]{
                "Panino (€4)", "Toast (€3)", "Pizza (€5)"
        });

        chkFormaggio = new JCheckBox("Formaggio");
        chkInsalata = new JCheckBox("Insalata");
        chkPomodoro = new JCheckBox("Pomodoro");

        acqua = new JRadioButton("Acqua (€1)");
        coca = new JRadioButton("Coca-Cola (€2)");
        succo = new JRadioButton("Succo (€2)");

        gruppoBevande = new ButtonGroup();
        gruppoBevande.add(acqua);
        gruppoBevande.add(coca);
        gruppoBevande.add(succo);

        area = new JTextArea(10, 40);

        JButton btnAggiungi = new JButton("Aggiungi Ordine");
        JButton btnReset = new JButton("Reset");
        JButton btnSalva = new JButton("Salva CSV");

        btnAggiungi.addActionListener(e -> aggiungiOrdine());
        btnReset.addActionListener(e -> resetCampi());
        btnSalva.addActionListener(e -> salvaCSV());

        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("Prodotto:"));
        add(comboProdotto);

        add(new JLabel("Extra:"));
        add(chkFormaggio);
        add(chkInsalata);
        add(chkPomodoro);

        add(new JLabel("Bevanda:"));
        add(acqua);
        add(coca);
        add(succo);

        add(btnAggiungi);
        add(btnReset);
        add(btnSalva);

        add(new JScrollPane(area));
    }

    // 🔹 Logica ordine
    private void aggiungiOrdine() {
        String nome = txtNome.getText();
        String prodotto = comboProdotto.getSelectedItem().toString();

        int totale = calcolaTotale(prodotto);

        String extra = getExtra();
        totale += contaExtra();

        String bevanda = getBevanda();
        totale += prezzoBevanda(bevanda);

        area.append("Cliente: " + nome + "\n");
        area.append("Prodotto: " + prodotto + "\n");
        area.append("Extra: " + extra + "\n");
        area.append("Bevanda: " + bevanda + "\n");
        area.append("Totale: €" + totale + "\n");
        area.append("----------------------\n");
    }

    // 🔹 Calcoli separati
    private int calcolaTotale(String prodotto) {
        if (prodotto.contains("Panino")) return 4;
        if (prodotto.contains("Toast")) return 3;
        return 5;
    }

    private String getExtra() {
        String extra = "";
        if (chkFormaggio.isSelected()) extra += "Formaggio ";
        if (chkInsalata.isSelected()) extra += "Insalata ";
        if (chkPomodoro.isSelected()) extra += "Pomodoro ";
        return extra;
    }

    private int contaExtra() {
        int count = 0;
        if (chkFormaggio.isSelected()) count++;
        if (chkInsalata.isSelected()) count++;
        if (chkPomodoro.isSelected()) count++;
        return count;
    }

    private String getBevanda() {
        if (acqua.isSelected()) return "Acqua";
        if (coca.isSelected()) return "Coca-Cola";
        if (succo.isSelected()) return "Succo";
        return "";
    }

    private int prezzoBevanda(String bevanda) {
        if (bevanda.equals("Acqua")) return 1;
        if (bevanda.equals("Coca-Cola") || bevanda.equals("Succo")) return 2;
        return 0;
    }

    // 🔹 RESET
    private void resetCampi() {
        txtNome.setText("");
        comboProdotto.setSelectedIndex(0);
        chkFormaggio.setSelected(false);
        chkInsalata.setSelected(false);
        chkPomodoro.setSelected(false);
        gruppoBevande.clearSelection();
    }

    // 🔥 CSV
    private void salvaCSV() {
        File file = new File("ordini.csv");
        boolean nuovo = !file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {

            String nome = txtNome.getText();
            String prodotto = comboProdotto.getSelectedItem().toString();
            String extra = getExtra();
            String bevanda = getBevanda();

            int totale = calcolaTotale(prodotto) + contaExtra() + prezzoBevanda(bevanda);

            writer.write(nome + ";" + prodotto + ";" + extra + ";" + bevanda + ";" + totale + "\n");

            JOptionPane.showMessageDialog(this, "Salvato bene.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Errore.");
        }
    }
}