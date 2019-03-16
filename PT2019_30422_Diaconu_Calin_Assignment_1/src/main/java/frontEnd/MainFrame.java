package frontEnd;

import backEnd.Monom;
import backEnd.Polinom;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFrame extends JFrame{
    private static Polinom eroareInput() {
        Polinom polinom = new Polinom();
        polinom.add(new Monom(1, -1));
        JOptionPane.showMessageDialog(null, "Incorrect input");
        return polinom;
    }

    private static String removeSpaces(String string) {
        while(string.length() > 0 && string.charAt(0) == ' ')
            string = string.substring(1);

        return string;
    }

    private static Polinom getInput(JTextField jTextField) {
        Polinom polinom = new Polinom();

        String string = jTextField.getText();

        if(string.length() == 0) {
            return eroareInput();
        }

        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(string);

        String regexString = "";
        String variableName = "0";

        while(matcher.find()) {
            String aux = matcher.group(1);
            regexString = regexString.concat(aux);

            int constanta = 0;
            int exponent = 0;

            boolean isNegative = false;

            aux = removeSpaces(aux);

            //check if it starts with +/-
            if(aux.length() > 0 && aux.charAt(0) == '+' || aux.charAt(0) == '-') {
                if(aux.charAt(0) == '-') {
                    isNegative = true;
                }

                if(aux.length() > 1) {
                    aux = aux.substring(1);
                }
            }
            aux = removeSpaces(aux);

            //check if this is the variable (in the case where there is no explicit constant)
            if(aux.length() > 0 && (aux.charAt(0) < '0' || aux.charAt(0) > '9')) {
                constanta = 1;

                if(variableName.equals("0")) {
                    variableName = String.valueOf(aux.charAt(0));
                }
                else {
                    if(variableName.compareTo(String.valueOf(aux.charAt(0))) != 0) {
                        return eroareInput();
                    }
                }
            }
            aux = removeSpaces(aux);

            //get constant
            while (aux.length() > 0 && aux.charAt(0) >= '0' && aux.charAt(0) <= '9') {
                constanta = constanta * 10 + aux.charAt(0) - '0';
                if (aux.length() >= 2) {
                    aux = aux.substring(1);
                } else {
                    aux = "";
                }
            }
            aux = removeSpaces(aux);

            //check if there is a '*' symbol
            if(aux.length() > 0 && aux.charAt(0) == '*') {
                if(aux.length() >= 2) {
                    aux = aux.substring(1);
                } else {
                    return eroareInput();
                }
            }
            aux = removeSpaces(aux);

            //check for explicit exponent (in the case of x^0)
            if(aux.length() == 1 && (aux.charAt(0) < '0' || aux.charAt(0) > '9')) {
                exponent = 1;
            }

            //check for variable
            if(aux.length() > 0) {
                if(variableName.equals("0")) {
                    variableName = String.valueOf(aux.charAt(0));
                } else {
                    if(variableName.compareTo(String.valueOf(aux.charAt(0))) != 0) {
                        return eroareInput();
                    }
                }

                aux = aux.substring(1);
            } else {
                exponent = 0;
            }
            aux = removeSpaces(aux);

            //check for '^' symbol
            if(aux.length() > 0 && aux.charAt(0) == '^') {
                if(aux.length() >= 2) {
                    aux = aux.substring(1);
                } else {
                    return eroareInput();
                }
            }

            //get exponent
            aux = removeSpaces(aux);
            while (aux.length() > 0 && aux.charAt(0) >= '0' && aux.charAt(0) <= '9') {
                exponent = exponent * 10 + aux.charAt(0) - '0';
                if (aux.length() >= 2) {
                    aux = aux.substring(1);
                } else {
                    aux = "";
                }
            }

            if(isNegative) {
                constanta = -constanta;
            }

            Monom monom = new Monom(constanta, exponent);
            polinom.add(monom);
        }

        if(regexString.compareTo(string) != 0) {
            return eroareInput();
        }

        return polinom;
    }

    public MainFrame() {
        //MainFrame parameters
        int w = 451;
        int h = 205;

        setLayout(null);
        setSize(w, h);
        setTitle("Polinoame");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //textFields
        final JTextField inputTextField1 = new JTextField(1);
        final JTextField inputTextField2 = new JTextField(1);
        final JTextField outputTextField = new JTextField(1);

        inputTextField1.setBounds(100, 15, 328, 20);
        inputTextField2.setBounds(100, 40, 328, 20);
        outputTextField.setBounds(100, 65, 328, 20);

        outputTextField.setEditable(false);

        add(inputTextField1);
        add(inputTextField2);
        add(outputTextField);


        //labels
        JLabel inputFieldLabel1 = new JLabel("Polinom 1:");
        JLabel inputFieldLabel2 = new JLabel("Polinom 2:");
        JLabel outputFieldLabel = new JLabel("Rezultat:");

        inputFieldLabel1.setBounds(15, 15, 80, 20);
        inputFieldLabel2.setBounds(15, 40, 80, 20);
        outputFieldLabel.setBounds(15, 65, 80, 20);

        add(inputFieldLabel1);
        add(inputFieldLabel2);
        add(outputFieldLabel);


        //buttons
        JButton jButtonAdunare = new JButton("Adunare");
        JButton jButtonScadere = new JButton("Scadere");
        JButton jButtonInmultire = new JButton("Inmultire");
        JButton jButtonImpartire = new JButton("Impartire");
        JButton jButtonDerivare1 = new JButton("DerivareP1");
        JButton jButtonIntegrare1 = new JButton("IntegrareP1");
        JButton jButtonDerivare2 = new JButton("DerivareP2");
        JButton jButtonIntegrare2 = new JButton("IntegrareP2");

        jButtonAdunare.setBounds(13, 100, 100, 20);
        jButtonScadere.setBounds(118, 100, 100, 20);
        jButtonInmultire.setBounds(224, 100, 100, 20);
        jButtonImpartire.setBounds(328, 100, 100, 20);
        jButtonDerivare1.setBounds(13, 130, 100, 20);
        jButtonIntegrare1.setBounds(118, 130, 100, 20);
        jButtonDerivare2.setBounds(224, 130, 100, 20);
        jButtonIntegrare2.setBounds(328, 130, 100, 20);

        add(jButtonAdunare);
        add(jButtonScadere);
        add(jButtonInmultire);
        add(jButtonImpartire);
        add(jButtonDerivare1);
        add(jButtonIntegrare1);
        add(jButtonDerivare2);
        add(jButtonIntegrare2);

        //events
        jButtonAdunare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = getInput(inputTextField1);
                Polinom p2 = getInput(inputTextField2);

                if(p1.getMonomList().get(0).getExponent() != -1 && p2.getMonomList().get(0).getExponent() != -1)
                    outputTextField.setText(Polinom.adunare(p1, p2).toString());
            }
        });

        jButtonScadere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = getInput(inputTextField1);
                Polinom p2 = getInput(inputTextField2);

                if(p1.getMonomList().get(0).getExponent() != -1 && p2.getMonomList().get(0).getExponent() != -1)
                    outputTextField.setText(Polinom.scadere(p1, p2).toString());
            }
        });

        jButtonInmultire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = getInput(inputTextField1);
                Polinom p2 = getInput(inputTextField2);

                if(p1.getMonomList().get(0).getExponent() != -1 && p2.getMonomList().get(0).getExponent() != -1)
                    outputTextField.setText(Polinom.inmultire(p1, p2).toString());
            }
        });

        jButtonImpartire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = getInput(inputTextField1);
                Polinom p2 = getInput(inputTextField2);

                boolean flag = false;

                for(Monom monom : p2.getMonomList()) {
                    if(monom.getConstanta() != 0)
                        break;
                    MainFrame.eroareInput();
                    flag = true;
                }

                List<Polinom> polinomList = Polinom.impartire(p1, p2);

                String output = "Cat: ";
                output = output.concat(polinomList.get(0).toString());
                output = output.concat("   Rest: ");
                output = output.concat(polinomList.get(1).toString());

                if(p1.getMonomList().get(0).getExponent() != -1 && p2.getMonomList().get(0).getExponent() != -1 && !flag)
                    outputTextField.setText(output);
            }
        });

        jButtonDerivare1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polinom polinom = getInput(inputTextField1);

                if(polinom.getMonomList().get(0).getExponent() != -1)
                    outputTextField.setText(Polinom.derivare(polinom).toString());
            }
        });

        jButtonIntegrare1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polinom polinom = getInput(inputTextField1);

                if(polinom.getMonomList().get(0).getExponent() != -1)
                    outputTextField.setText(Polinom.integrare(polinom).toString());
            }
        });

        jButtonDerivare2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polinom polinom = getInput(inputTextField2);

                if(polinom.getMonomList().get(0).getExponent() != -1)
                    outputTextField.setText(Polinom.derivare(polinom).toString());
            }
        });

        jButtonIntegrare2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polinom polinom = getInput(inputTextField2);

                if(polinom.getMonomList().get(0).getExponent() != -1)
                    outputTextField.setText(Polinom.integrare(polinom).toString());
            }
        });

        setVisible(true);
    }
}
