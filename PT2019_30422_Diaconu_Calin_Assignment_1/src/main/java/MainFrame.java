import javax.swing.*;

public class MainFrame extends JFrame{

    public MainFrame() {
        int w = 430;
        int h = 205;

        setLayout(null);
        setSize(w, h);
        setTitle("Polinoame");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JTextField inputTextField1 = new JTextField(1);
        JTextField inputTextField2 = new JTextField(1);
        JTextField outputTextField = new JTextField(1);

        inputTextField1.setBounds(100, 15, 300, 20);
        inputTextField2.setBounds(100, 40, 300, 20);
        outputTextField.setBounds(100, 65, 300, 20);

        outputTextField.setEditable(false);

        add(inputTextField1);
        add(inputTextField2);
        add(outputTextField);


        JLabel inputFieldLabel1 = new JLabel("Polinom 1:");
        JLabel inputFieldLabel2 = new JLabel("Polinom 2:");
        JLabel outputFieldLabel = new JLabel("Rezultat:");

        inputFieldLabel1.setBounds(15, 15, 80, 20);
        inputFieldLabel2.setBounds(15, 40, 80, 20);
        outputFieldLabel.setBounds(15, 65, 80, 20);

        add(inputFieldLabel1);
        add(inputFieldLabel2);
        add(outputFieldLabel);


        JButton jButtonAdunare = new JButton("Adunare");
        JButton jButtonScadere = new JButton("Scadere");
        JButton jButtonInmultire = new JButton("Inmultire");
        JButton jButtonImpartire = new JButton("Impartire");
        JButton jButtonDerivare = new JButton("Derivare");
        JButton jButtonIntegrare = new JButton("Integrare");

        jButtonAdunare.setBounds(15, 100, 90, 20);
        jButtonScadere.setBounds(113, 100, 90, 20);
        jButtonInmultire.setBounds(212, 100, 90, 20);
        jButtonImpartire.setBounds(310, 100, 90, 20);
        jButtonDerivare.setBounds(113, 130, 90, 20);
        jButtonIntegrare.setBounds(212, 130, 90, 20);

        add(jButtonAdunare);
        add(jButtonScadere);
        add(jButtonInmultire);
        add(jButtonImpartire);
        add(jButtonDerivare);
        add(jButtonIntegrare);

        setVisible(true);
    }
}
