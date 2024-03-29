#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define prt(x) printf("%s", &x)
#define ClearBuffer while((getchar()) != '\n')

#define DEBUG printf("\n --- DEBUUUUUUUUUUUUUUUUG ---\n")
#define Debug(x) printf("\n\nDEBUG %s\n\n", &x)

struct Func{
    char cpf[11];
    char nome[30];
    time_t entrada;
    time_t saida;
    char status;
};

struct Log{
    char cpf[11];
    time_t horario;
    char evento;
};

void printFunc(struct Func f){
    prt("----------------------------------\n");
    printf("CPF: %s\n\n", f.cpf);
    printf("Nome: %s\n\n", f.nome);
    printf("Entrada: %s", ctime(&f.entrada));
    printf("Saida: %s", ctime(&f.saida));
    prt("----------------------------------\n");
}

void printLog(struct Log l){
    prt("-------------------------------------------\n");
    printf("CPF: %s\n\n", l.cpf);
    if(l.evento == 'P') prt("ENTRADA - ");
    else prt("SAIDA - ");
    printf("Horas: %s", ctime(&l.horario));
    prt("-------------------------------------------\n");
}

void registro(FILE *func, FILE *log){
    struct Func f, aux;
    struct Log l;
    prt(" -- Lendo funcionario --\n\n");
    prt("CPF: ");
    scanf("%s", &f.cpf);
    ClearBuffer;
    int newcpf=1;
    fseek(func, 0, SEEK_SET);
    while(fread(&aux, sizeof(struct Func), 1, func)){
        printf("%s | %s | %d\n", f.cpf, aux.cpf, strcmp(f.cpf,aux.cpf));
        if(strcmp(aux.cpf, f.cpf) == 0){
            newcpf=0;
            strcpy(f.nome,aux.nome);
            do{
                prt("Status: ");
                scanf("%c", &f.status);
                ClearBuffer;
                if(f.status == 'P'){

                    time(&f.entrada);
                    f.saida = aux.saida;

                    l.horario = f.entrada;
                }
                else if(f.status == 'A'){
                    time(&f.saida);
                    f.entrada = aux.entrada;

                    l.horario = f.saida;
                }
            } while(f.status != 'P' && f.status != 'A');

            //---------- LOG -----------
            l.evento = f.status;
            strcpy(l.cpf, f.cpf);
            fseek(log, 0, SEEK_END);
            fwrite(&l, sizeof(struct Log), 1, log);
            //---------------------------

            fseek(func, -sizeof(struct Func), SEEK_CUR);
            break;
        }
    }
    if(newcpf){
        prt("Nome: ");
        scanf("%s", &f.nome);
        ClearBuffer;
        time(&f.entrada);
        time(&f.saida);
        f.status = ' ';
        fseek(func, 0, SEEK_END);
    }

    fwrite(&f, sizeof(struct Func), 1, func);
}


void listagem_funcionarios(FILE *func){
    struct Func f;
    fseek(func, 0, SEEK_SET);
    while(fread(&f, sizeof(struct Func), 1, func)){
        printFunc(f);
    }
}

void listagem_log(FILE *log) {
    struct Log l;
    char aux[11];
    prt("Qual funcionario voce deseja? (-1 para todos)\n");
    prt("CPF: ");
    scanf("%s", &aux);
    fseek(log, 0, SEEK_SET);
    while(fread(&l, sizeof(struct Log), 1, log)){
        if(strcmp(l.cpf,aux) == 0 || strcmp(aux, "-1") == 0) printLog(l);
    }
    ClearBuffer;
}




int main() {
    //  -------------- FILES -----------------
    FILE *funcionarios;
    funcionarios = fopen("funcionarios.bin", "rb+");
    if(funcionarios == NULL) funcionarios = fopen("funcionarios.bin", "wb+");
    FILE *log;
    log = fopen("log.bin", "rb+");
    if(log == NULL) log= fopen("log.bin", "wb+");

    // a partir daqui nesta função main() o código não deve ser alterado
    int opcao;
    do {
        printf("\n\n 0-sair\n 1-registro entrada/saida\n 2-lista funcionarios\n 3-lista log\n");
        printf("\n Opcao: "); scanf(" %d",&opcao);
        switch(opcao) {
            case 1: registro(funcionarios,log); break;
            case 2: listagem_funcionarios(funcionarios); break;
            case 3: listagem_log(log); break;
            printf("\n\n\n");
        }
    } while (opcao != 0);

    fclose(funcionarios);
    fclose(log);
    return 0;
}
