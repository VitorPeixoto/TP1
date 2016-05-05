package tp1;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Peixoto on 04/05/2016.
 */
public class TelephoneNumber {
    private String number;

    private Client client;
    private PlanType type;

    private Date activationDate,
                 cancelationDate;

    private int payDay;
    private static Scanner input = new Scanner(System.in);
    private static TelephoneNumber[] numbers = new TelephoneNumber[1];

    static {
        input.useDelimiter(Pattern.compile("[\\n;]"));
    }

    private TelephoneNumber(String number, Client client, PlanType type, Date activationDate, Date cancelationDate, int payDay) {
        this.number = number;
        this.client = client;
        this.type = type;
        this.activationDate = activationDate;
        this.cancelationDate = cancelationDate;
        this.payDay = payDay;
    }

    private static void addInNumbers(TelephoneNumber newTelephoneNumber) {
        numbers[(numbers.length-1)] = newTelephoneNumber;
        numbers= Arrays.copyOf(numbers, (numbers.length+1));
    }

    public static void register(){
        System.out.println("Digite o telefone");
        String newTel = input.next();
        for(int x=0;x<(numbers.length-1);x++){
            if(numbers[x].getNumber().equals(newTel) && numbers[x].getCancelationDate() == null){
                System.out.println("Esse telefone ja esta cadastrado");
                return;
            }
        }


        System.out.println("Digite o codigo do plano");
        int newCode = input.nextInt();
        PlanType [] types = PlanType.getTypes();
        PlanType type = null;
        for(int x = 0; x < (types.length-1); x++){
            if(types[x].getCode() == newCode) {
                type = types[x];
            }
        }

        if(type == null) {
            System.out.println("Esse plano nao esta cadastrado");
            return;
        }

        System.out.println("Digite o CPF do cliente a ser alterado");
        String newCpf = input.next();
        Client [] clients = Client.getClients();
        Client client = null;
        for(int x = 0; x< (clients.length-1); x++){
            if(clients[x].getCPF().equals(newCpf)){
                client = clients[x];
            }
        }

        if(client == null ){
            System.out.println("Cliente nao cadastraado");
            return;
        }
        System.out.println("Digite a data de ativaçao no formato MM/DD/AA 00:00:00");
        String activation = input.next();
        Date activationDate = new Date(activation);
        //System.out.println(d.toString());
        /*try {
            Date d = DateFormat.getInstance().parse("10/04/1997 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        System.out.println("Digite a data de cancelamento no formato MM/DD/AA 00:00:00 (-1 para pular)");
        String cancelation =  input.next();
        Date cancelationDate = null;
        if(!cancelation.equals("-1")) {
            cancelationDate = new Date(cancelation);
        }
        System.out.println("Digite o dia do pagamento (1, 5, 10 ou 15):");
        int newPayday = input.nextInt();
        if(newPayday != 1 && newPayday != 5 && newPayday != 10 && newPayday != 15){
            System.out.println("Dia invalido");
            return;
        }
        TelephoneNumber newNumber = new TelephoneNumber(newTel,client,type,activationDate,cancelationDate,newPayday);
        addInNumbers(newNumber);

    }

    public static void update() {
        System.out.println("Digite o numero a ser altarado");
        String newTel = input.next();
        TelephoneNumber telephoneNumber = null;

        for (int x = 0; x < numbers.length-1; x++) {
            if (numbers[x].getNumber().equals(newTel)) {
                telephoneNumber = numbers[x];
            }
        }
        if (telephoneNumber == null) {
            System.out.println("Este número não existe.");
            return;
        }

        System.out.println("Digite o CPF (-1 para pular)");
        String newCPF = input.next();
        Client clients[] = Client.getClients();
        Client client = null;
        if(!newCPF.equals("-1")) {
            for (int x = 0; x < (clients.length-1); x++) {
                if (clients[x].getCPF().equals(newCPF)) {
                    client = clients[x];
                }
            }
            if (client == null) {
                System.out.println("Esse cpf não esta cadastrado.");
                return;
            }
        }
        System.out.println("Digite o codigo do novo plano (-1 para pular)");
        int newCode = input.nextInt();
        PlanType planTypes[] = PlanType.getTypes();
        PlanType planType = null;
        if(newCode != -1) {
            for (int x = 0; x < (planTypes.length-1); x++) {
                if (planTypes[x].getCode() == newCode) {
                    planType = planTypes[x];
                }

            }
            if (planType == null) {
                System.out.println("Plano nao cadastrado");
                return;
            }
        }
        System.out.println("Digite o novo dia de pagamento (1, 5, 10, 15 (-1 para pular)");
        int newPayDay = input.nextInt();
        if(newPayDay != 1 && newPayDay != 5 && newPayDay != 10 && newPayDay != 15 && newPayDay != -1) {
            System.out.println("Dia invalido");
            return;
        }
        if(!newCPF.equals("-1")) telephoneNumber.setClient(client);
        if(newCode !=-1) telephoneNumber.setType(planType);
        if(newPayDay != -1) telephoneNumber.setPayDay(newPayDay);

    }

    public static void cancelNumber(){
        System.out.println("Digite o numero que quer cancelar");
        String newNumber = input.next();
        TelephoneNumber tel = null;
        for(int x = 0;x<numbers.length-1; x++){
            if(numbers[x].getNumber().equals(newNumber)){
                tel = numbers[x];
            }
        }
        if(tel == null){
            System.out.println("Esse telefone nao esta cadastrado");
            return;
        }
        System.out.println("Digite a data de cancelamento no formato MM/DD/AA 00:00:00");
        String newCancelation = input.next();
        Date cancelation = new Date(newCancelation);
        if(cancelation.compareTo(tel.getActivationDate()) <= 0){
            System.out.println("A data de cancelamento deve ser depois da data de ativação.");
            return;
        }
        tel.setCancelationDate(cancelation);
        System.out.println("Telefone cancelado com sucesso");
    }

    public static void list(){
        for(int x = 0; x < (numbers.length-1); x++){
            System.out.println(numbers[x].toString());
        }
    }

    public static void search(){
        System.out.println("Digite o número do telefone:");
        String number = input.next();
        for(int x=0; x < (numbers.length-1); x++){
            if(numbers[x].getNumber().equals(number)){
                System.out.println(numbers[x].toString());
            }
        }
    }

    public static TelephoneNumber[] getNumbers() {
        return numbers;
    }

    public static void exclude() {
        System.out.println("Digite o numero a ser excluído: ");
        String number = input.next();
        int numbersIndex = -1;
        TelephoneNumber telephoneNumber = null;

        for (int x = 0; x < (numbers.length-1); x++) {
            if(numbers[x].getNumber().equals(number)) {
                telephoneNumber = numbers[x];
                numbersIndex = x;
            }
        }
        if(telephoneNumber == null) {
            System.out.println("Este número não está cadastrado.");
            return;
        }

        if(JOptionPane.showConfirmDialog(null, "Deseja mesmo apagar este núemro?") == 0) {
            numbers[numbersIndex] = null;
            reshapeNumbers();
        }
    }

    private static void reshapeNumbers() {
        for(int x = 0; x < (numbers.length-1); x++) {
            if(numbers[x] == null) {
                for (int y = x; y < (numbers.length-1); y++) {
                    numbers[y] = numbers[y+1];
                }
            }
        }
        numbers = Arrays.copyOf(numbers, (numbers.length-1));
    }

    @Override
    public String toString() {
        String cancelation = null;
        if(cancelationDate == null)
            cancelation = "Não cancelado.";
        else cancelation = cancelationDate.toString();

        return "\n Telefone "+number+":\nCliente: "+client.getName()+".\nData de ativação: "+activationDate.toString()+".\nData de cancelamento: "+cancelation+".\nData de pagamento: "+payDay+".";
    }

  /********************************************************************************************
   * Getters and setters                                                                      *
   ********************************************************************************************/
    public String getNumber() {
        return number;
    }

    public Client getClient() {
        return client;
    }

    public PlanType getType() {
        return type;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public Date getCancelationDate() {
        return cancelationDate;
    }

    public int getPayDay() {
        return payDay;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setType(PlanType type) {
        this.type = type;
    }

    public void setCancelationDate(Date cancelationDate) {
        this.cancelationDate = cancelationDate;
    }

    public void setPayDay(int payDay) {
        this.payDay = payDay;
    }
}
