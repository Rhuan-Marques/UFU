package POO.Entrega2.Bancos;

import java.util.Scanner;
class Main {

  public static void main(String[] args){
    Banking_System sistema = new Banking_System();
    menu_principal(sistema);

  }

  private static void menu_principal(Banking_System sistema){
    Scanner input = new Scanner(System.in);
    int opcao;
    do{
      
      System.out.println("Bem vindo, do que gostaria?");
      System.out.println("1 - Acessar conta");
      System.out.println("2 - Criar nova conta");
      System.out.println("3 - Gerar relatórios");
      System.out.println("0 - Terminar atendimento");
      opcao = input.nextInt();
      input.nextLine();
      switch(opcao){
        case 1:
          System.out.println("Por favor indique seu CPF");
          String cpf = input.nextLine();
          System.out.println("Por favor indique sua senha");
          String senha = input.nextLine();
          Bank conta = sistema.autentication(cpf, senha);
          if(conta == null){
            System.out.println("CPF ou senha incorreta");
          }
          else{
            menu_da_conta(sistema, conta, input);
          }
        break;
        case 2:
          System.out.println(opcao);
          System.out.println("Para criar sua conta, informe seu CPF:");
          cpf = input.nextLine();
          if(sistema.cpf_autentication(cpf) != null){
            System.out.println("Voce ja tem uma conta");
          }
          else{
            criacao_de_conta(sistema, cpf, input);

          }
        break;
        case 3:
          relatorios(sistema, input);
        break;
      }
    }while(opcao != 0);
    input.close();
  }
  private static void menu_da_conta(Banking_System sistema, Bank conta, Scanner input){
    int opcao;
    double dinheiro;
    do{
      System.out.println("Conta acessada, o que deseja?");
      System.out.println("1 - Depósito");
      System.out.println("2 - Saque");
      System.out.println("3 - Transferência");
      System.out.println("4 - Ver saldo");
      System.out.println("5 - Relatório da conta");
      System.out.println("0 - Sair");
      opcao = input.nextInt();
      input.nextLine();
      switch(opcao){
        case 1:
          System.out.println("Quanto?");
          dinheiro = input.nextDouble();
          conta.deposit(dinheiro);
        break;
        case 2:
          System.out.println("Quanto?");
          dinheiro = input.nextDouble();
          System.out.printf("Voce sacou %.2f reais\n", conta.withdraw(dinheiro));
        break;
        case 3:
          System.out.println("Digite o CPF do destinatário:");
          String cpf = input.nextLine();
          Bank destinarario = sistema.cpf_autentication(cpf);
          if(destinarario == null){
            System.out.println("Este CPF não tem conta conosco");
          }
          else{
            System.out.println("Quanto deseja transferir?");
            dinheiro = input.nextDouble(); 
            if(sistema.transfer(conta, destinarario, dinheiro) == 1){
              System.out.println("Impossivel transferir para si mesmo");
            }
          }
        break;
        case 4:
          System.out.printf("Seu saldo eh: %.2f\n", conta.getBalance());
        break;
        case 5:
          conta.personal_relatory();
        break;
      }

    }while(opcao != 0);
  }
  private static void criacao_de_conta(Banking_System sistema, String cpf, Scanner input){
  String nome, telefone, senha, senha2;
  int tipo, idade;
  double saldo;
  System.out.println("Otimo, qual seu nome?");
  nome = input.nextLine();
  do{
    System.out.println("Que tipo de conta deseja criar?");
    System.out.println("1-Poupanca");
    System.out.println("2-Corrente");
    tipo = input.nextInt();
    input.nextLine();
  }while(tipo<1 || tipo>2);
  tipo-=1;
  System.out.println("Ok, qual sua idade?");
  idade = input.nextInt();
  input.nextLine();
  System.out.println("E qual seu numero de telefone");
  telefone = input.nextLine();
  do{
    System.out.println("Defina uma senha pra sua conta:");
    senha = input.nextLine();
    System.out.println("Confirme sua senha:");
    senha2 = input.nextLine();
    if(!senha.equals(senha2)){
      System.out.println("As senhas nao coincidem:");
    }
  }while(!senha.equals(senha2));
  System.out.println("Com quanto de saldo gostaria de comecar sua conta?");
  saldo = input.nextDouble();
  input.nextLine();
  sistema.create_account(nome, senha, telefone, idade, cpf, tipo, saldo);
  }

  private static void relatorios(Banking_System sistema, Scanner input){
    
    int opcao;
    do{
      System.out.println("Que relatórios gerar");
      System.out.println("1 - Relatorio pessoal de determinado cliente");
      System.out.println("2 - Contas com saldo negativo");
      System.out.println("3 - Clientes em faixa etária");
      System.out.println("0 - Terminar atendimento");
      opcao = input.nextInt();
      input.nextLine();
      switch(opcao){
        case 1:
          System.out.println("Gerar relatorio referente a qual CPF?");
          String cpf = input.nextLine();
          Bank conta = sistema.cpf_autentication(cpf);
          if(conta == null){
            System.out.println("Este CPF nao eh nosso cliente");
          }
          else{
            conta.personal_relatory();
          }
        break;
        case 2:
          sistema.neg_balace_relatory();
        break;
        case 3:
          System.out.println("Qual o a idade minima e maxima da faixa etaria que deseja?");
          int minimo = input.nextInt();
          int maximo = input.nextInt();
          sistema.age_relatory(minimo, maximo);
        break;
      }
    }while(opcao!=0);
    
  }
}
