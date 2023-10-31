package POO.Entrega2.Bancos;
import java.util.HashMap;
import java.util.Map;


public class Banking_System extends Bank{
    private Map<String, Bank> accounts = new HashMap<>();
    public int transfer(Bank sender, Bank receiver, double ammount){
        if(sender == receiver){
             return 1;
        }
        sender.transfer_send(ammount);
        receiver.transfer_receive(ammount);
        return 0;
    }

    public void create_account(String account_name, String pass_word, String phone_number, int owner_age, String cpf_num, int type_of_account, double initial_balance){
        Bank account = new Bank(account_name, pass_word, phone_number, owner_age, cpf_num, type_of_account, initial_balance);
        accounts.put(cpf_num, account);
        return;
    }

    public void relatory(Bank x){
        x.personal_relatory();
    }

    public void neg_balace_relatory(){
        for(String key: accounts.keySet()){
            Bank account = accounts.get(key);
            if(account.getBalance() < 0){
                account.personal_relatory();
            }
        }
    }

    public void age_relatory(int lower, int higher){
        for(String key: accounts.keySet()){
            Bank account = accounts.get(key);
            account.age_relatory(lower, higher);
        }
    }

    public Bank autentication(String cpf, String password){
        Bank account = cpf_autentication(cpf);
        if(account != null && account.autentication(password)) return account;
        else return null;
    }

    public Bank cpf_autentication(String cpf){
        if(accounts.containsKey(cpf)) return accounts.get(cpf);
        else return null;
    }

}
