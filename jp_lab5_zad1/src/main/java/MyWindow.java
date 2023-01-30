import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MyWindow implements ActionListener {
    private JTextField a1Field;
    private JTextField a2Field;
    private JTextField a3Field;
    private JLabel a1Label;
    private JLabel a2Label;
    private JLabel a3Label;
    private JTextField b1Field;
    private JTextField b2Field;
    private JTextField b3Field;
    private JLabel b1Label;
    private JLabel b2Label;
    private JLabel b3Label;
    private JTextField c2Field;
    private JTextField c3Field;
    private JTextField c0Field;
    private JTextField c1Field;
    private JLabel c3Label;
    private JLabel c2Label;
    private JLabel c0Label;
    private JLabel c1Label;
    private JLabel outcomeLabel;
    private JTextField outcomeField;
    private JLabel jLabel;
    private JButton solveButton;
    private JTextField equationField;
    private JPanel jPanel;

    private final ArrayList<JTextField> textFields = new ArrayList<>();
    public MyWindow(){
        setFont();
        solveButton.addActionListener(this);

        textFields.add(a1Field);
        textFields.add(a2Field);
        textFields.add(a3Field);
        textFields.add(b1Field);
        textFields.add(b2Field);
        textFields.add(b3Field);
        textFields.add(c0Field);
        textFields.add(c1Field);
        textFields.add(c2Field);
        textFields.add(c3Field);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Zadanie1");
        MyWindow window = new MyWindow();
        frame.setContentPane(window.jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void calculate(){
        String[] given = new String[10];//a1, a2, a3, b1, b2, b3, c0, c1, c2, c3;
        setGiven(given);                //0,  1,  2,  3,  4,  5,  6,  7,  8,  9
        int[] digits = new int[10];
        int outcome = 0;
        int howManyUnknowns = 0;
        int index = 0;

        for(int i = 0; i < 10; i++){
            try {
                digits[i] = Integer.parseInt(given[i]);
            }catch (NumberFormatException e){
                index = i;
                howManyUnknowns++;
            }
        }

        int A = 0;
        int B = 0;
        int C = 0;

        if(index < 3){
            B = digits[5] + digits[4] * 10 + digits[3] * 100;
            C = digits[9] + digits[8] * 10 + digits[7] * 100 + digits[6] * 1000;
        }
        else if(index > 5){
            A = digits[2] + digits[1] * 10 + digits[0] * 100;
            B = digits[5] + digits[4] * 10 + digits[3] * 100;
        }
        else{
            A = digits[2] + digits[1] * 10 + digits[0] * 100;
            C = digits[9] + digits[8] * 10 + digits[7] * 100 + digits[6] * 1000;
        }

        if (howManyUnknowns != 1) JOptionPane.showMessageDialog(jPanel, "Musi być dokładnie jedna niewiadoma");

        else if(Objects.equals(equationField.getText(),"+")){
            switch (index){
                case 0 -> outcome = C - (B + digits[2] + digits[1] * 10);
                case 1 -> outcome = C - (B + digits[2] + digits[0] * 100);
                case 2 -> outcome = C - (B + digits[1] * 10 + digits[0] * 100);
                case 3 -> outcome = C - (digits[5] + digits[4] * 10 + A);
                case 4 -> outcome = C - (digits[5] + digits[3] * 100 + A);
                case 5 -> outcome = C - (digits[4] * 10 + digits[3] * 100 + A);
                case 6 -> outcome = A + B - (digits[7] * 100 + digits[8] * 10 + digits[9]);
                case 7 -> outcome = A + B - (digits[6] * 1000 + digits[8] * 10 + digits[9]);
                case 8 -> outcome = A + B - (digits[6] * 1000 + digits[7] * 100 + digits[9]);
                case 9 -> outcome = A + B - (digits[6] * 1000 + digits[7] * 100 + digits[8] * 10);
            }

            outcome = getBiggestDigit(outcome, index);
            if(isSolvable(index, outcome, digits)) outcomeField.setText(given[index] + " = " + outcome);
            else outcomeField.setText("Równania nie da się rozwiązać");
        }

        else if(Objects.equals(equationField.getText(),"-")){
            switch (index) {
                case 0 -> outcome = C + B - (digits[2] + digits[1] * 10);
                case 1 -> outcome = C + B - (digits[2] + digits[0] * 100);
                case 2 -> outcome = C + B - (digits[1] * 10 + digits[0] * 100);
                case 3 -> outcome = A - (digits[5] + digits[4] * 10 + C);
                case 4 -> outcome = A - (digits[5] + digits[3] * 100 + C);
                case 5 -> outcome = A - (digits[4] * 10 + digits[3] * 100 + C);
                case 6 -> outcome = A - (B + digits[7] * 100 + digits[8] * 10 + digits[9]);
                case 7 -> outcome = A - (B + digits[6] * 1000 + digits[8] * 10 + digits[9]);
                case 8 -> outcome = A - (B + digits[6] * 1000 + digits[7] * 100 + digits[9]);
                case 9 -> outcome = A - (B + digits[6] * 1000 + digits[7] * 100 + digits[8] * 10);
            }

            outcome = getBiggestDigit(outcome, index);
            if(isSolvable(index, outcome, digits)) outcomeField.setText(given[index] + " = " + outcome);
            else outcomeField.setText("Równania nie da się rozwiązać");
        }

        else if(Objects.equals(equationField.getText(),"*")){
            switch (index){
                case 0 -> outcome = C/B - (digits[2] + digits[1] * 10);
                case 1 -> outcome = C/B - (digits[2] + digits[0] * 100);
                case 2 -> outcome = C/B - (digits[1] * 10 + digits[0] * 100);
                case 3 -> outcome = C/A - (digits[5] + digits[4] * 10);
                case 4 -> outcome = C/A - (digits[5] + digits[3] * 100);
                case 5 -> outcome = C/A - (digits[4] * 10 + digits[3] * 100);
                case 6 -> outcome = A * B - (digits[7] * 100 + digits[8] * 10 + digits[9]);
                case 7 -> outcome = A * B - (digits[6] * 1000 + digits[8] * 10 + digits[9]);
                case 8 -> outcome = A * B - (digits[6] * 1000 + digits[7] * 100 + digits[9]);
                case 9 -> outcome = A * B - (digits[6] * 1000 + digits[7] * 100 + digits[8] * 10);
            }

            outcome = getBiggestDigit(outcome, index);
            if(isSolvable(index, outcome, digits)) outcomeField.setText(given[index] + " = " + outcome);
            else outcomeField.setText("Równania nie da się rozwiązać");
        }

        else JOptionPane.showMessageDialog(jPanel, "Błędny znak równania");
    }

    public int getBiggestDigit(int outcome, int index){

        if(index == 0 || index == 3 || index == 7) outcome = outcome/100;
        else if(index == 6) outcome = outcome/1000;
        else if(index == 1 || index == 4 || index == 8) outcome = outcome/10;

        return outcome;
    }

    public boolean isSolvable(int index, int outcome, int[] digits){

        int leftSide;
        int rightSide;
        digits[index] = outcome;

        if(Objects.equals(equationField.getText(),"+")){

            leftSide = digits[0] * 100 + digits[1] * 10 + digits[2] +
                    digits[3] * 100 + digits[4] * 10 + digits[5];
            rightSide = digits[6] * 1000 + digits[7] * 100 + digits[8] * 10 + digits[9];

            return leftSide == rightSide;
        }

        else if(Objects.equals(equationField.getText(),"-")){

            leftSide = digits[0] * 100 + digits[1] * 10 + digits[2] -
                    (digits[3] * 100 + digits[4] * 10 + digits[5]);
            rightSide = digits[6] * 1000 + digits[7] * 100 + digits[8] * 10 + digits[9];

            return leftSide == rightSide;
        }

        else if(Objects.equals(equationField.getText(),"*")){

            leftSide = (digits[0] * 100 + digits[1] * 10 + digits[2]) *
                    (digits[3] * 100 + digits[4] * 10 + digits[5]);
            rightSide = digits[6] * 1000 + digits[7] * 100 + digits[8] * 10 + digits[9];

            return leftSide == rightSide;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == solveButton){
            calculate();
        }
    }

    public void setGiven(String[] given){
        for(int i = 0; i < 10; i++){
            given[i] = textFields.get(i).getText();
        }
    }

    public void setFont(){
        ArrayList<JLabel> list = new ArrayList<>();
        list.add(a1Label);
        list.add(a2Label);
        list.add(a3Label);
        list.add(b1Label);
        list.add(b2Label);
        list.add(b3Label);
        list.add(c0Label);
        list.add(c1Label);
        list.add(c2Label);
        list.add(c3Label);
        list.add(outcomeLabel);
        list.add(jLabel);

        for(JLabel label : list){
            label.setFont(new Font("Unispace", Font.BOLD, 16));
            label.setForeground(new Color(255,255,255));
        }
    }
}
