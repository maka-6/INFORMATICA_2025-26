/*
 * Autore: Makaoui youness
 * Classe: 4G
 * Data: 06/03/2026
 * Versione: 1.0
 * Luogo: Lab 53bis
 * Descrizione: Classe Menu che gestisce la grafica della pagina di prenotazione
 */

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    private Otello hotel;
    JPanel roomPanel;


    private final String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
            "17","18","19","20", "21","22","23","24","25","26","27","28","29","30","31"};
    private final String[] month = {"gennaio","febbraio","marzo","aprile","maggio","giugno",
            "luglio","agosto","settembre","ottobre","novembre","dicembre"};
    private final String[] year = {"2026","2027","2028","2029"};
    private JComboBox<String> bookDateYear;
    private JComboBox<String> bookDateMonth;
    private JComboBox<String> bookDateDay;
    JTextField selectedRoom;
    JPanel bookPanel;



    JButton cancel;
    JButton select;

    public Menu( Otello hotel ){

        super("Hotel Bernocchi");
        setSize(1000,800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.hotel = hotel;

        bookDateYear = new JComboBox<>(year);
        bookDateMonth = new JComboBox<>(month);
        bookDateDay = new JComboBox<>(day);
        bookPanel = new JPanel(new GridLayout(3,2));

        selectedRoom = new JTextField("Stanza selezionata: ");
        JPanel datePanel = new JPanel(new GridLayout(1,3));
        datePanel.add(bookDateDay);
        datePanel.add(bookDateMonth);
        datePanel.add(bookDateYear);
        bookPanel.add(datePanel);
        cancel = new JButton("Cancella");
        select = new JButton("Prenota");
        bookPanel.add(cancel);
        bookPanel.add(select);
        add(bookPanel, BorderLayout.NORTH);

        roomPanel = createRoomPanel(10, 14);
        add(roomPanel, BorderLayout.CENTER);

        setVisible(true);
    }


    JPanel createRoomPanel( int rows, int columns ) {
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(rows, columns));
        JButton[][] buttons = new JButton[rows][columns];
        int roomNumber = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                roomNumber++;
                buttons[i][j] = new JButton(Integer.toString(roomNumber));
                buttons[i][j].setBackground(Color.green);
                buttons[i][j].setSize(25, 25);
                roomPanel.add(buttons[i][j]);
            }
        }
        return roomPanel;
    }


}
