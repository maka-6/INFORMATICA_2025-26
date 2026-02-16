/*
 * Autore: Makaoui Youness
 * Classe: 4G
 * Versione: 2.0
 * Luogo: Home
 * Data: 16/02/2026
 * Descrizione: Calcolatrice organizzata 2x2 con cronologia
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

	JTextField num1;
	JTextField num2;
	JTextField result;
	JTextArea history;

	public Calculator() {

		super("Calculator PRO MAX");
		setSize(600, 400);
		setLayout(new GridLayout(2, 2, 5, 5));
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		// numeri panel
		JPanel numbersPanel = new JPanel();
		num1 = new JTextField(8);
		num2 = new JTextField(8);

		numbersPanel.add(new JLabel("Num1:"));
		numbersPanel.add(num1);
		numbersPanel.add(new JLabel("Num2:"));
		numbersPanel.add(num2);
		add(numbersPanel);


		// risultato panel
		JPanel resultPanel = new JPanel();
		result = new JTextField(15);
		result.setEditable(false);

		resultPanel.add(new JLabel("Risultato:"));
		resultPanel.add(result);
		add(resultPanel);

		JPanel buttonsPanel = new JPanel();

		String[] operations = { "+", "-", "*", "/" };

		for (String op : operations) {
			JButton button = new JButton(op);
			button.addActionListener(this);
			buttonsPanel.add(button);
		}

		add(buttonsPanel);


		// cronologia panel
		history = new JTextArea();
		history.setEditable(false);
		JScrollPane scroll = new JScrollPane(history);

		add(scroll);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		double n1 = Double.parseDouble(num1.getText());
		double n2 = Double.parseDouble(num2.getText());
		double res = 0;

		String op = e.getActionCommand();

		if (op.equals("+")) {
			res = n1 + n2;
		} else if (op.equals("-")) {
			res = n1 - n2;
		} else if (op.equals("*")) {
			res = n1 * n2;
		} else if (op.equals("/")) {
			res = n1 / n2;
		}

		result.setText(String.valueOf(res));

		history.append(n1 + " " + op + " " + n2 + " = " + res + "\n");

	}

	public static void main(String[] args) {
		new Calculator();
	}
}
