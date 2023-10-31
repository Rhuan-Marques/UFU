import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class FillDataWindow extends JFrame {
    private JLabel title;
    private JLabel nametxt, cpftxt, birthtxt, sextxt, acttxt, passtxt, emailtxt;
    private JTextField name, cpf, birth, act, pass, email;
    private JButton save, cancel;
    private JCheckBox updates;
    private String[] sexoptions = {"Masculino", "Feminino", "Outro", "Prefiro não informar"};
    private JComboBox sex;

    public FillDataWindow() {
        super("Cadastro de Conta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 570);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // ---- Object Creation ----
        title = new JLabel(".................. Informe os dados cadastrais ..................\n");
        nametxt = new JLabel("Nome:");
        name = new JTextField(50);
        cpftxt = new JLabel("CPF:");
        cpf = new JTextField(50);
        birthtxt = new JLabel("Data de Nascimento:");
        birth = new JTextField(50);
        sextxt = new JLabel("Sexo:");
        sex = new JComboBox(sexoptions);
        acttxt = new JLabel("Numero da Conta:");
        act = new JTextField(50);
        passtxt = new JLabel("Senha:");
        pass = new JTextField(50);
        emailtxt = new JLabel("E-mail:");
        email = new JTextField(50);
        updates = new JCheckBox("Desejo receber notícias");
        save = new JButton("Salvar");
        cancel = new JButton("Cancelar");

        // ---- Panel Management ----
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
            EmptyBorder margin = new EmptyBorder(10, 10, 10, 10);
            mainPanel.setBorder(margin);
            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                titlePanel.add(title);
            mainPanel.add(titlePanel, BorderLayout.NORTH);
            JPanel contentPanel = new JPanel(new GridLayout(0, 1, 5, 5));
                contentPanel.add(nametxt);
                contentPanel.add(name);
                contentPanel.add(cpftxt);
                contentPanel.add(cpf);
                contentPanel.add(birthtxt);
                contentPanel.add(birth);
                contentPanel.add(sextxt);
                contentPanel.add(sex);
                contentPanel.add(acttxt);
                contentPanel.add(act);
                contentPanel.add(passtxt);
                contentPanel.add(pass);
                contentPanel.add(emailtxt);
                contentPanel.add(email);
                contentPanel.add(updates);
            mainPanel.add(contentPanel, BorderLayout.CENTER);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.add(save);
                buttonPanel.add(cancel);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        // ---- Managers ----
        ButtonManager buttonManager = new ButtonManager();
        save.addActionListener(buttonManager);
        cancel.addActionListener(buttonManager);
    }

    public FillDataWindow(String name, String cpf, String birth, String sex, String act, String pass, String email, boolean updates) {
        this();
        this.name.setText(name);
        this.cpf.setText(cpf);
        this.birth.setText(birth);
        this.sex.setSelectedItem(sex);
        this.act.setText(act);
        this.pass.setText(pass);
        this.email.setText(email);
        this.updates.setSelected(updates);
    }

    private class ButtonManager implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == save) {
                if (validateData()){
                    if (saveDataToFile() == 0)
                        SavedSuccesfullyPane("Salvo corretamente");
                    else
                        SavedSuccesfullyPane("Informações atualizadas");
                }
                else
                    return;
            }
            Base_Window window = new Base_Window();
            window.setVisible(true);
            dispose();
        }
    }

    private boolean validateData() {
        String name = this.name.getText();
        String cpf = this.cpf.getText();
        String birth = this.birth.getText();
        String act = this.act.getText();
        String pass = this.pass.getText();
        String email = this.email.getText();

        if (name.isBlank() || cpf.isBlank() || birth.isBlank() || act.isBlank() || pass.isBlank() || email.isBlank()){
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro: Campo em Branco", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isNumeric(cpf) || !isNumeric(act)) {
            JOptionPane.showMessageDialog(this, "Escreva CPF e Conta somente com números", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Função que manuzeia corretamente os valores pra salvar em file
    // 0 = Append | 1 = Update
    private int saveDataToFile() {
        String name = this.name.getText();
        String cpf = this.cpf.getText();
        String birth = this.birth.getText();
        String sex = (String) this.sex.getSelectedItem();
        String act = this.act.getText();
        String pass = this.pass.getText();
        String email = this.email.getText();
        boolean updates = this.updates.isSelected();

        String linha = name + "|" + cpf + "|" + birth + "|" + sex + "|"
                + act + "|" + pass + "|" + email + "|" + updates;

        if(FileManager.cpfExists("dados.txt", cpf)){
            FileManager.updateDataByCpf("dados.txt", cpf, linha);
            return 1;
        }
        else{
            FileManager.saveDataToFile("dados.txt", linha);
            return 0;
        }
    }


    private void SavedSuccesfullyPane(String message) {
        JOptionPane.showMessageDialog(
                FillDataWindow.this,
                message,
                "Confirmação",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}