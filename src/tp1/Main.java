package tp1;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Lucas Oliveira
 * @author Vítor Peixoto
 */
public class Main {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int op = -1;
        while (op != 6) {
            System.out.println(" ____________________________________________________");
            System.out.println("| Digite a opção desejada                            |");
            System.out.println("| 1 - Tipos de Plano                                 |");
            System.out.println("| 2 - Clientes                                       |");
            System.out.println("| 3 - Números de Telefone                            |");
            System.out.println("| 4 - Chamadas                                       |");
            System.out.println("| 5 - Relatórios                                     |");
            System.out.println("| 6 - Sair                                           |");
            System.out.println("|____________________________________________________|");
            System.out.print("Opção: ");
            op = input.nextInt();

            switch (op) {
                case 1:
                    planType();
                    break;
                case 2:
                    clients();
                    break;
                case 3:
                    telephoneNumbers();
                    break;
                case 4:
                    calls();
                    break;
                case 5:
                    reports();
                    break;
                case 6:
                    break;
            }
        }
    }

    private static double getDurationInMinutes(long milliseconds) {
        double seconds = milliseconds*0.001;
        double minutes = 0;
        //double hours   = 0;
        //double days    = 0;
        //double months  = 0;

        if(seconds >= 60) {
            minutes = Math.floor(seconds/60);
            seconds = (seconds%60);
        }
        /*if(minutes >= 60) {
            hours   = Math.floor(minutes/60);
            minutes = (minutes%60);
        }
        if(hours >= 24) {
            days  = Math.floor(hours/24);
            hours = (hours%24);
        }
        if(days >= 31) {
            months = Math.floor(days/31);
            days   = (days%31);
        }*/
        return minutes;
    }

    private static void reports() {
        int op = -1;
        while (op != 3) {
            System.out.println(" ____________________________________________________");
            System.out.println("| Relatórios                                         |");
            System.out.println("| 1 - Dados telefônicos                              |");
            System.out.println("| 2 - Conta telefônica                               |");
            System.out.println("| 3 - Voltar ao menu principal                       |");
            System.out.println("|____________________________________________________|");
            System.out.print("Opção: ");
            op = input.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Digite o CPF:");
                    String cpf = input.next();
                    TelephoneNumber[] numbers = TelephoneNumber.getNumbers();
                    for(int x=0;x<(numbers.length-1);x++){
                        if(numbers[x].getClient().getCPF().equals(cpf)){
                            System.out.println(numbers[x].toString());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Digite o número de telefone: ");
                    String number = input.next();

                    System.out.println("Digite o mês/ano de referência (MM/AAAA):");
                    String referenceString = input.next();
                    String referenceInitialString    = "01/"+referenceString+" 00:00:00";
                    String referenceFinalString      = "31/"+referenceString+" 23:59:59";
                    Date referenceInitialDate = new Date(OriginedCall.brazilianToAmerican(referenceInitialString));
                    Date referenceFinalDate   = new Date(OriginedCall.brazilianToAmerican(referenceFinalString));

                    OriginedCall calls[] = OriginedCall.getCalls();
                    TelephoneNumber[] telephoneNumbers = TelephoneNumber.getNumbers();
                    PlanType planType = null;
                    for(int y = 0; y < (telephoneNumbers.length -1); y++) {
                        if(telephoneNumbers[y].getNumber().equals(number)) {
                            planType = telephoneNumbers[y].getType();
                            break;
                        }
                    }

                    double over          = 0;
                    double totalDuration = 0;
                    int allowance = planType.getAllowance();
                    for(int x = 0; x < (calls.length-1); x++) {
                        if(calls[x].getCallBegin().compareTo(referenceInitialDate) >= 0 &&
                           calls[x].getCallBegin().compareTo(referenceFinalDate)   <= 0 &&
                           calls[x].getOriginNumber().getNumber().equals(number)          ) {
                            System.out.println(calls[x].toString());
                            double duration = getDurationInMinutes(calls[x].getCallEnd().getTime() - calls[x].getCallBegin().getTime());
                            System.out.println("Duração: "+duration+".");
                            totalDuration  += duration;
                            if(duration <= allowance){
                                allowance -= duration;
                                System.out.println("Preço: 0.");
                            }
                            else {                                
                                System.out.println("Preço: "+(duration - allowance)*planType.getCallPrice()+".");                                
                            }                           
                        }
                    }
                     if(planType.getAllowance() < totalDuration) {
                        over = totalDuration - planType.getAllowance();
                    }
                    else {
                        over = 0;
                    }
                    double valorTotal = planType.getMonthlyValue() + (over * planType.getCallPrice());
                    System.out.println("\nDuração total: "+totalDuration+" minutos.");
                    System.out.println("Franquia: "+planType.getAllowance()+" minutos.");
                    System.out.println("Minutos além da franquia: "+over+" minutos.");
                    System.out.println("Valor do plano: R$ "+planType.getMonthlyValue()+".");
                    System.out.println("Valor total: R$ "+valorTotal+".");
                    break;
                case 3:
                    return;
            }
        }

    }

    private static void calls() {
        int op = -1;
        while (op != 5) {
            System.out.println(" ____________________________________________________");
            System.out.println("| Chamadas                                           |");
            System.out.println("| 1 - Registrar uma chamada                          |");
            System.out.println("| 2 - Excluir uma chamada                            |");
            System.out.println("| 3 - Listar chamada                                 |");
            System.out.println("| 4 - Pesquisar chamada                              |");
            System.out.println("| 5 - Voltar ao menu principal                       |");
            System.out.println("|____________________________________________________|");
            System.out.print("Opção: ");
            op = input.nextInt();

            switch (op) {
                case 1:
                    OriginedCall.register();
                    break;
                case 2:
                    OriginedCall.exclude();
                    break;
                case 3:
                    OriginedCall.list();
                    break;
                case 4:
                    OriginedCall.search();
                    break;
                case 5:
                    return;

            }


        }
    }
    private static void telephoneNumbers() {
        int op = -1;
        while (op != 7) {
            System.out.println(" ____________________________________________________");
            System.out.println("| Números de telefone                                |");
            System.out.println("| 1 - Cadastrar um telefone                          |");
            System.out.println("| 2 - Alterar um telefone                            |");
            System.out.println("| 3 - Excluir um telefone                            |");
            System.out.println("| 4 - Cancelar um telefone                           |");
            System.out.println("| 5 - Listar                                         |");
            System.out.println("| 6 - Pesquisar um telefone                          |");
            System.out.println("| 7 - Voltar ao menu principal                       |");
            System.out.println("|____________________________________________________|");
            System.out.print("Opção: ");
            op = input.nextInt();

            switch (op) {
                case 1:
                    TelephoneNumber.register();
                    break;
                case 2:
                    TelephoneNumber.update();
                    break;
                case 3:
                    TelephoneNumber.exclude();
                    break;
                case 4:
                    TelephoneNumber.cancelNumber();
                    break;
                case 5:
                    TelephoneNumber.list();
                    break;
                case 6:
                    TelephoneNumber.search();
                    break;
                case 7:
                    return;
            }

        }
    }

    private static void clients() {
        int op = -1;
        while (op != 6) {
            System.out.println(" ____________________________________________________");
            System.out.println("| Clientes                                           |");
            System.out.println("| 1 - Cadastrar um cliente                           |");
            System.out.println("| 2 - Alterar um cliente                             |");
            System.out.println("| 3 - Excluir um cliente                             |");
            System.out.println("| 4 - Listar                                         |");
            System.out.println("| 5 - Pesquisar um cliente                           |");
            System.out.println("| 6 - Voltar ao menu principal                       |");
            System.out.println("|____________________________________________________|");
            System.out.print("Opção: ");
            op = input.nextInt();

            switch (op) {
                case 1:
                    Client.register();
                    break;
                case 2:
                    Client.update();
                    break;
                case 3:
                    Client.exclude();
                    break;
                case 4:
                    Client.list();
                    break;
                case 5:
                    Client.search();
                    break;
                case 6:
                    return;
            }

        }
    }

    private static void planType() {
        int op = -1;
        while (op != 6){
            System.out.println(" ____________________________________________________");
            System.out.println("| Tipo de plano                                      |");
            System.out.println("| 1 - Cadastrar Plano                                |");
            System.out.println("| 2 - Alterar Plano                                  |");
            System.out.println("| 3 - Excluir plano                                  |");
            System.out.println("| 4 - Listar                                         |");
            System.out.println("| 5 - Pesquisar                                      |");
            System.out.println("| 6 - Voltar ao menu principal                       |");
            System.out.println("|____________________________________________________|");
            System.out.print("Opção: ");
            op = input.nextInt();

            switch (op) {
                case 1:
                    PlanType.register();
                    break;
                case 2:
                    PlanType.update();
                    break;
                case 3:
                    PlanType.exclude();
                    break;
                case 4:
                    PlanType.list();
                    break;
                case 5:
                    PlanType.search();
                    break;
                case 6:
                    return;
            }
        }

    }


}
