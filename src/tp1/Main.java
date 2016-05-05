package tp1;

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
    private static void reports() {

    }

    private static void calls() {
        int op = -1;
        while (op != 6) {
            System.out.println(" ____________________________________________________");
            System.out.println("| Digite a opção desejada                            |");
            System.out.println("| 1 - Registrar uma chamada                          |");
            System.out.println("| 2 - Excluir uma chamada                            |");
            System.out.println("| 3 - Listar chamada                                 |");
            System.out.println("| 4 - Pesquisar chamada                              |");
            System.out.println("| 5 - Menu principal                                 |");
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
        while (op != 6) {
            System.out.println(" ____________________________________________________");
            System.out.println("| Digite a opção desejada                            |");
            System.out.println("| 1 - Cadastrar um telefone                          |");
            System.out.println("| 2 - Alterar um telefone                            |");
            System.out.println("| 3 - Excluir um telefone                            |");
            System.out.println("| 4 - Cancelar um telefone                           |");
            System.out.println("| 5 - Listar                                         |");
            System.out.println("| 6 - Pesquisar um telefone                          |");
            System.out.println("| 7 - Menu principal                                 |");
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
            System.out.println("| Digite a opção desejada                            |");
            System.out.println("| 1 - Cadastrar um cliente                           |");
            System.out.println("| 2 - Alterar um cliente                             |");
            System.out.println("| 3 - Excluir um cliente                             |");
            System.out.println("| 4 - Listar                                         |");
            System.out.println("| 5 - Pesquisar um cliente                           |");
            System.out.println("| 6 - Sair                                           |");
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
                    Client.update();
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
            System.out.println("| Digite a opção desejada                            |");
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
