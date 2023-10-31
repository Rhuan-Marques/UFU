import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private JLabel inputtxt, hightxt, medtxt, lowtxt, valuetxt;
    private JTextField input, high, med, low, value;
    private JButton inputbut, displaybut, calcbut;
    private JComboBox operation;
    private String[] operationList = {"Somar", "Multiplicar"};
    private ArrayList<Integer>  displayList = new ArrayList<>();
    public MainWindow(){
        super("Programa Frame - V2 LP5");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(3, 1, 15, 5));
        setLocationRelativeTo(null);
        // ---- Creates objects ----
        inputtxt = new JLabel("Digite o numero:");
        input = new JTextField(20);
        inputbut = new JButton("OK");
        hightxt = new JLabel("   Maior >>> ");
        high = new JTextField(20);
        lowtxt = new JLabel("   Menor >>> ");
        low = new JTextField(20);
        medtxt = new JLabel("   Media >>> ");
        med = new JTextField(20);
        displaybut = new JButton("Exibir");
        valuetxt = new JLabel("  Valores:");
        value = new JTextField(20);
        operation = new JComboBox(operationList);
        calcbut = new JButton("Calcular");
        // ---- Aditional Setting ----
        high.setEditable(false);
        low.setEditable(false);
        med.setEditable(false);
        // ---- Panel management ----

        JPanel upperPanel = new JPanel(new GridLayout(3, 3, 5, 5));
            upperPanel.add(inputtxt);
            JPanel upperInputPanel = new JPanel(new GridLayout(1, 3, 5, 5));
                upperInputPanel.add(input);
                upperInputPanel.add(inputbut);
                upperInputPanel.add(new JPanel());
            upperPanel.add(upperInputPanel);
        add(upperPanel);

        JPanel midPanel = new JPanel(new GridLayout(3, 3, 5, 5));
            midPanel.add(hightxt);
            midPanel.add(high);
            midPanel.add(new JPanel());
            midPanel.add(lowtxt);
            midPanel.add(low);
            midPanel.add(displaybut);
            midPanel.add(medtxt);
            midPanel.add(med);
            midPanel.add(new JPanel());
        add(midPanel);

        JPanel lowPanel = new JPanel(new GridLayout(3, 3, 5, 5));
            lowPanel.add(valuetxt);
            lowPanel.add(value);
            lowPanel.add(new JPanel());
            lowPanel.add(new JPanel());
            lowPanel.add(operation);
            lowPanel.add(calcbut);
            lowPanel.add(new JPanel());
            lowPanel.add(new JPanel());
            lowPanel.add(new JPanel());
        add(lowPanel);

        // ---- Managers ----
        RegisterManager registerManager = new RegisterManager();
        inputbut.addActionListener(registerManager);

        DisplayerManager displayerManager = new DisplayerManager();
        displaybut.addActionListener(displayerManager);

        CalculatorManager calculatorManager = new CalculatorManager();
        calcbut.addActionListener(calculatorManager);

    }

    private class RegisterManager implements ActionListener{
        @ Override
        public void actionPerformed(ActionEvent e) {
            if(validateInput(input.getText())) {
                ArrayList<Integer> inputList = SplitToInt(input.getText());
                displayList.addAll(inputList);
                input.setText("");
            }
        }
    }

    private class DisplayerManager implements ActionListener{
        @ Override
        public void actionPerformed(ActionEvent e) {
            if(displayList.size()>0){
                Integer[] result = minMaxMed(displayList);
                low.setText(result[0].toString());
                high.setText(result[1].toString());
                med.setText(result[2].toString());
                DisplayPopUpWindow(displayList);
            }
            else
                JOptionPane.showMessageDialog(null, "Digite ao menos um número e clique em \"OK\" primeiro para usar esta função", "Erro: Nenhum número inputado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class CalculatorManager implements ActionListener{
        @ Override
        public void actionPerformed(ActionEvent e) {
            if(validateInput(value.getText())){
                ArrayList<Integer> inputList = SplitToInt(value.getText());
                Integer result;
                if(operation.getSelectedIndex() == 0){
                    result=0;
                    for(Integer i: inputList)
                        result+=i;
                }
                else{
                    result=1;
                    for(Integer i: inputList)
                        result*=i;
                }
                CalculatorPopUpWindow(operation, result);
            }
        }
    }

    private boolean validateInput(String input) {
        if(input.isBlank()){
            JOptionPane.showMessageDialog(this, "Preencha o campo de input", "Erro: Campo vazio", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!input.matches("^[0-9\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Insira somente números e/ou espaço", "Erro: Caractere não reconhecido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private Integer[] minMaxMed(ArrayList<Integer> list){
        Integer min, max, med=0;
        min=list.get(0);
        max=list.get(0);
        med=0;
        for(Integer i: list){
            if(i<min)
                min=i;
            if(i>max)
                max=i;
            med+=i;
        }
        med/=list.size();
        Integer[] result = {min, max, med};
        return result;
    }

    private ArrayList<Integer> SplitToInt(String fullInput) {
        String[] splitStr = fullInput.split("\\s+");
        ArrayList<Integer> inputList = new ArrayList<>();
        for(String str: splitStr){
            int intvalue = Integer.parseInt(str);
            inputList.add(intvalue);
        }
        return inputList;
    }

    private void DisplayPopUpWindow(ArrayList<Integer> list){
        String message;
        message = "Números digitados até agora:\n";
        for(Integer i: list){
            message = message + i.toString() + ' ';
        }
        JOptionPane.showMessageDialog(MainWindow.this, message, "Exibição de Números", JOptionPane.PLAIN_MESSAGE);
    }

    private void CalculatorPopUpWindow(JComboBox operation, Integer result){
        String message;
        message = operation.getSelectedItem().toString() + ": ";
        message += result;
        JOptionPane.showMessageDialog(MainWindow.this, message, "Resultado de Calculo", JOptionPane.INFORMATION_MESSAGE);
    }
}

