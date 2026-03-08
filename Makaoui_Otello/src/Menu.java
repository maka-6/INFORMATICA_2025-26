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

    private int code = 0;

    private final String[] month = {"gennaio","febbraio","marzo","aprile","maggio","giugno",
            "luglio","agosto","settembre","ottobre","novembre","dicembre"};

    private final String[] year = {"2026","2027"};

    private final String[] day31 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
            "17","18","19","20", "21","22","23","24","25","26","27","28","29","30","31"};
    private final String[] day30 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
            "17","18","19","20", "21","22","23","24","25","26","27","28","29","30"};
    private final String[] day28 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
            "17","18","19","20", "21","22","23","24","25","26","27","28"};

    /*
    // [mesi: 12] [giorni: 31-30-28]
    private final String[][] date = new String[12][];
    */

    private JComboBox<String> bookedYear;
    private JComboBox<String> bookedMonth;
    private JComboBox<String> bookedDay;

    private JTextField selectedRoom;
    private int selectedRoomNumber = -1;
    private JTextField clientName;
    private JTextField clientSurname;
    private JTextField clientBookingName;
    private final JButton cancel;
    private final JButton select;

    private JPanel bookingPanel;


    private JPanel roomPanel;

    public Menu( Otello hotel ){

        super("Hotel Bernocchi");
        setSize(1000,800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        bookingPanel = new JPanel(new GridLayout(7,1));

        JPanel datePanel = new JPanel(new GridLayout(1,3));

        // data di partenza 01/01/2026
        bookedDay = new JComboBox<>(day31);
        bookedYear = new JComboBox<>(year);
        bookedMonth = new JComboBox<>(month);

        datePanel.add(bookedDay);
        datePanel.add(bookedMonth);
        datePanel.add(bookedYear);

        bookingPanel.add(datePanel);

        // imposta i giorni in base al mese
        bookedMonth.addActionListener(e -> {
            switch ( bookedMonth.getSelectedIndex() ) {
                case 1:
                    bookedDay.setModel(new DefaultComboBoxModel<>(day28));
                    break;

                case 3:
                case 5:
                case 8:
                case 10:
                    bookedDay.setModel(new DefaultComboBoxModel<>(day30));
                    break;

                default:
                    bookedDay.setModel(new DefaultComboBoxModel<>(day31));
                    break;
            }
        });


        clientName = new JTextField("Nome: ");
        clientSurname = new JTextField("Cognome: ");
        clientBookingName = new JTextField("Nome della prenotazione: ");
        bookingPanel.add(clientName);
        bookingPanel.add(clientSurname);
        bookingPanel.add(clientBookingName);
        cancel = new JButton("Cancella");
        select = new JButton("Prenota");
        bookingPanel.add(cancel);
        bookingPanel.add(select);
        selectedRoom = new JTextField("Scegli una Camera e prenota!!");
        selectedRoom.setEditable(false);
        bookingPanel.add(selectedRoom);
        add(bookingPanel, BorderLayout.NORTH);

        roomPanel = createRoomPanel(10,14);
        add(roomPanel, BorderLayout.CENTER);

        select.addActionListener(e -> {
            selectedRoom.setText( "Camera selezionata: " + selectedRoomNumber );
            Data date = new Data( (String)bookedDay.getSelectedItem(), (String)bookedMonth.getSelectedItem(), (String)bookedYear.getSelectedItem() );
            Prenotazione reservation = new Prenotazione( new Cliente( clientName.getText(), clientSurname.getText() ), date, clientBookingName.getText(), code, selectedRoomNumber );

            if ( selectedRoomNumber == -1 ) {
                return;
            }

            if ( hotel.bookRoom( reservation ) ){
                selectedRoom.setText("Prenotazione effettuata con successo!");
                code++;
            } else {
                selectedRoom.setText("Camera non disponibile!");
            }

            selectedRoomNumber = -1;
        });

        setVisible(true);
    }

    JPanel createRoomPanel( int rows, int columns ) {
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(rows, columns, 15, 15));
        JButton[][] buttons = new JButton[rows][columns];
        int roomNumber = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j] = new JButton(Integer.toString(roomNumber+1));
                buttons[i][j].setBackground(Color.green);
                buttons[i][j].setSize(25, 25);
                roomPanel.add(buttons[i][j]);
                int currentRoom = roomNumber+1;

                buttons[i][j].addActionListener(e -> {
                    selectedRoomNumber = currentRoom;
                    selectedRoom.setText("Stanza selezionata: " + currentRoom);

                });

                roomNumber++;
            }
        }
        return roomPanel;
    }

    public void saveOnCSV( String filename ){

    }
}
