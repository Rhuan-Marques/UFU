import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCpfWindow extends JFrame {
    JLabel selectMessage;
    JComboBox<String> cpfSelector;

    SelectCpfWindow(){
        super("Contas Cadastradas");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 100);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setLocationRelativeTo(null);
        // ---- Object Creation ----
        selectMessage = new JLabel("Selecione o CPF que deseja visualizar:");
        cpfSelector = new JComboBox(FileManager.readCpfFromFile("dados.txt"));
        // ---- Aditional Changes ----
        cpfSelector.setPreferredSize(new Dimension(150, 30));
        cpfSelector.setSelectedIndex(-1);
        // ---- Panel Management ----
        add(selectMessage);
        add(cpfSelector);

        // ---- Managers ---
        cpfSelector.addActionListener(new WindowChangeManager());
    }

    private class WindowChangeManager implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cpfSelector.getSelectedIndex() != -1){
                String cpf = (String) cpfSelector.getSelectedItem();

                String[] data = FileManager.DataFromCpf("dados.txt", cpf);

                FillDataWindow window = new FillDataWindow(data[0], data[1], data[2], data[3], data[4], data[5],data[6], Boolean.parseBoolean(data[7]));
                window.setVisible(true);
                dispose();
            }
        }
    }

}
