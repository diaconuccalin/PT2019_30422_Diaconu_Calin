package frontEnd;

import backEnd.Monomial;
import backEnd.Polynomial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFrame extends JFrame{
    private static Polynomial inputError() {
        Polynomial polynomial = new Polynomial();
        polynomial.add(new Monomial(1, -1));
        JOptionPane.showMessageDialog(null, "Incorrect input");
        return polynomial;
    }

    private static String removeSpaces(String string) {
        while(string.length() > 0 && string.charAt(0) == ' ')
            string = string.substring(1);

        return string;
    }

    private static Polynomial getInput(JTextField jTextField) {
        Polynomial polynomial = new Polynomial();

        String string = jTextField.getText();

        if(string.length() == 0) {
            return inputError();
        }

        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(string);

        String regexString = "";
        String variableName = "0";

        while(matcher.find()) {
            String aux = matcher.group(1);

            if(aux.contains("(") || aux.contains(")"))
                return inputError();

            regexString = regexString.concat(aux);

            int coefficient = 0;
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
                coefficient = 1;

                if(variableName.equals("0")) {
                    variableName = String.valueOf(aux.charAt(0));
                }
                else {
                    if(variableName.compareTo(String.valueOf(aux.charAt(0))) != 0) {
                        return inputError();
                    }
                }
            }
            aux = removeSpaces(aux);

            //get constant
            while (aux.length() > 0 && aux.charAt(0) >= '0' && aux.charAt(0) <= '9') {
                coefficient = coefficient * 10 + aux.charAt(0) - '0';
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
                    return inputError();
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
                        return inputError();
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
                    return inputError();
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
                coefficient = -coefficient;
            }

            Monomial monomial = new Monomial(coefficient, exponent);
            polynomial.add(monomial);
        }

        if(regexString.compareTo(string) != 0) {
            return inputError();
        }

        return polynomial;
    }

    public MainFrame() {
        //MainFrame parameters
        int w = 575;
        int h = 205;

        setLayout(null);
        setSize(w, h);
        setTitle("Polynomials");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //textFields
        final JTextField inputTextField1 = new JTextField(1);
        final JTextField inputTextField2 = new JTextField(1);
        final JTextField outputTextField = new JTextField(1);

        inputTextField1.setBounds(100, 15, 445, 20);
        inputTextField2.setBounds(100, 40, 445, 20);
        outputTextField.setBounds(100, 65, 445, 20);

        outputTextField.setEditable(false);

        add(inputTextField1);
        add(inputTextField2);
        add(outputTextField);


        //labels
        JLabel inputFieldLabel1 = new JLabel("Polynomial 1:");
        JLabel inputFieldLabel2 = new JLabel("Polynomial 2:");
        JLabel outputFieldLabel = new JLabel("Result:");

        inputFieldLabel1.setBounds(15, 15, 80, 20);
        inputFieldLabel2.setBounds(15, 40, 80, 20);
        outputFieldLabel.setBounds(15, 65, 80, 20);

        add(inputFieldLabel1);
        add(inputFieldLabel2);
        add(outputFieldLabel);


        //buttons
        JButton jButtonAddition = new JButton("Addition");
        JButton jButtonSubtraction = new JButton("Subtraction");
        JButton jButtonMultiplication = new JButton("Multiplication");
        JButton jButtonDivision = new JButton("Division");
        JButton jButtonDerivative1 = new JButton("Derivative P1");
        JButton jButtonAntiderivative1 = new JButton("Antiderivative P1");
        JButton jButtonDerivative2 = new JButton("Derivative P2");
        JButton jButtonAntiderivative2 = new JButton("Antiderivative P2");

        jButtonAddition.setBounds(15, 100, 130, 20);
        jButtonSubtraction.setBounds(150, 100, 130, 20);
        jButtonMultiplication.setBounds(285, 100, 130, 20);
        jButtonDivision.setBounds(420, 100, 130, 20);
        jButtonDerivative1.setBounds(15, 130, 130, 20);
        jButtonAntiderivative1.setBounds(150, 130, 130, 20);
        jButtonDerivative2.setBounds(285, 130, 130, 20);
        jButtonAntiderivative2.setBounds(420, 130, 130, 20);

        add(jButtonAddition);
        add(jButtonSubtraction);
        add(jButtonMultiplication);
        add(jButtonDivision);
        add(jButtonDerivative1);
        add(jButtonAntiderivative1);
        add(jButtonDerivative2);
        add(jButtonAntiderivative2);

        //events
        jButtonAddition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = getInput(inputTextField1);
                Polynomial p2 = getInput(inputTextField2);

                if(p1.getMonomialList().get(0).getExponent() != -1 && p2.getMonomialList().get(0).getExponent() != -1)
                    outputTextField.setText(Polynomial.addition(p1, p2).toString());
            }
        });

        jButtonSubtraction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = getInput(inputTextField1);
                Polynomial p2 = getInput(inputTextField2);

                if(p1.getMonomialList().get(0).getExponent() != -1 && p2.getMonomialList().get(0).getExponent() != -1)
                    outputTextField.setText(Polynomial.subtraction(p1, p2).toString());
            }
        });

        jButtonMultiplication.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = getInput(inputTextField1);
                Polynomial p2 = getInput(inputTextField2);

                if(p1.getMonomialList().get(0).getExponent() != -1 && p2.getMonomialList().get(0).getExponent() != -1)
                    outputTextField.setText(Polynomial.multiplication(p1, p2).toString());
            }
        });

        jButtonDivision.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial p1 = getInput(inputTextField1);
                Polynomial p2 = getInput(inputTextField2);

                boolean flag = false;

                for(Monomial monomial : p2.getMonomialList()) {
                    if(monomial.getCoefficient() != 0)
                        break;
                    MainFrame.inputError();
                    flag = true;
                }

                if(p1.getMonomialList().get(0).getExponent() != -1 && p2.getMonomialList().get(0).getExponent() != -1 && !flag) {
                    List<Polynomial> polynomialList = Polynomial.division(p1, p2);

                    String output = "Quotient: ";
                    output = output.concat(polynomialList.get(0).toString());
                    output = output.concat("   Remainder: ");
                    output = output.concat(polynomialList.get(1).toString());

                    outputTextField.setText(output);
                }
            }
        });

        jButtonDerivative1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial polynomial = getInput(inputTextField1);

                if(polynomial.getMonomialList().get(0).getExponent() != -1)
                    outputTextField.setText(Polynomial.derivative(polynomial).toString());
            }
        });

        jButtonAntiderivative1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial polynomial = getInput(inputTextField1);

                if(polynomial.getMonomialList().get(0).getExponent() != -1)
                    outputTextField.setText(Polynomial.antiderivative(polynomial).toString());
            }
        });

        jButtonDerivative2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial polynomial = getInput(inputTextField2);

                if(polynomial.getMonomialList().get(0).getExponent() != -1)
                    outputTextField.setText(Polynomial.derivative(polynomial).toString());
            }
        });

        jButtonAntiderivative2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial polynomial = getInput(inputTextField2);

                if(polynomial.getMonomialList().get(0).getExponent() != -1)
                    outputTextField.setText(Polynomial.antiderivative(polynomial).toString());
            }
        });

        setVisible(true);
    }
}
