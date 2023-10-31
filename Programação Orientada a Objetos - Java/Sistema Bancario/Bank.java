package POO.Entrega2.Bancos;

public class Bank{
    private String name;
    private String phone;
    private String cpf;
    private String password;
    private int age;
    private int type;
    private double balance;
    protected Bank(){}
    protected Bank(String account_name, String pass_word, String phone_number, int owner_age, String cpf_num, int account_type, double initial_balance){
        name = account_name;
        password = pass_word;
        phone = phone_number;
        age = owner_age;
        cpf = cpf_num;
        type = account_type;
        balance = Math.max(initial_balance, 100);
    };


    public double getBalance(){
        return balance;
    }

    protected String getType(){
        if(type == 0) return "Poupança";
        else return "Corrente";
    }

    public double withdraw(double input){
        double money;
        money = Math.min(input, Math.max(balance-100, 0));
        balance-=money;
        return money;
    }
    public void deposit(double money){
        balance+=money;
        return;
    }

    protected void transfer_receive(double money){
        balance+=money;
        return;
    }
    protected void transfer_send(double money){
        balance-=money;
        return;
    }

    public void personal_relatory(){
        System.out.println("--- " + name + " ---------------");
        System.out.println("Conta " + getType());
        System.out.println("CPF: " + cpf);
        System.out.println("Contato: " + phone);
        System.out.printf("Saldo: %.2f\n", balance);
        System.out.println("-------------------------");
        return;
    }

    protected void age_relatory(int lower, int higher){
        if(age >= lower && age <=higher){
            System.out.println(name);
        }
    }


    protected boolean autentication(String pass_word){
        if(password.equals(pass_word)) return true;
        else return false;
    }

};
