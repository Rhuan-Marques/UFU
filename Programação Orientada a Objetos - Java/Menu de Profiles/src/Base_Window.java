import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Base_Window extends JFrame {
    private JButton fill_data, check_data;
    public Base_Window(){
        super("Cadastro de Conta");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200,200);
        setLayout(new GridLayout(5, 1));
        setLocationRelativeTo(null);

        fill_data = new JButton("Preencher Dados");
        check_data = new JButton("Visualizar Dados");


        //JPanel panel = new JPanel(new GridLayout(5, 1, 40, 40));
        add(new JPanel());
        JPanel fillPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fillPanel.add(fill_data);
        add(fillPanel);
        add(new JPanel());
        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        checkPanel.add(check_data);
        add(checkPanel);
        add(new JPanel());


        ButtonManager manager = new ButtonManager();
        fill_data.addActionListener(manager);
        check_data.addActionListener(manager);
    }

    private class ButtonManager implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == fill_data){
                FillDataWindow w = new FillDataWindow();

                w.setVisible(true);

            }
            if(e.getSource() == check_data){
                SelectCpfWindow w = new SelectCpfWindow();
                w.setVisible(true);
            }
            dispose();
        }
    }
}

