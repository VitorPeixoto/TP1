package tp1;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Peixoto on 26/04/2016.
 */
public class Client {
    private final String CPF;
    private String name,
                   address;
    private static Client clients[] = new Client[1];
    private static Scanner input = new Scanner(System.in);

    static {
        input.useDelimiter(Pattern.compile("[\\n;]"));
    }

    private Client(String CPF, String name, String address) {
        this.CPF = CPF;
        this.name = name;
        this.address = address;
    }

    private Client(String CPF) {
        this.CPF = CPF;
    }

    public static Client[] getClients() {
        return clients;
    }

    public static void register() {
        System.out.println("Digite o CPF");
        String newCPF = input.next();

        for(int x=0;x<(clients.length-1);x++){
           if(clients[x].getCPF()==newCPF){
               System.out.println("Esse CPF ja esta cadastrado ");
               return;
           }
        }
        System.out.println("Digite o nome do cliente");
        String newNOME =input.next();
        System.out.println("Digite o endereço do cliente");
        String newADDRESS =input.next();

        Client newClient = new Client(newCPF,newNOME,newADDRESS);
        addInClients(newClient);
        System.out.println("Cliente cadastrado com sucesso");
    }

    private static void addInClients(Client newClient){
        clients[(clients.length-1)] = newClient;
        clients = Arrays.copyOf(clients, (clients.length+1));
    }

    public static void update(){
        System.out.println("Digite o CPF do cliente a ser alterado");
        String cpf = input.next();
        Client newClient = null;

        for (int i = 0; i < (clients.length -1); i++) {
            if(clients[i].getCPF().equals(cpf)) {
                newClient  = clients[i];
            }
        }
        if(newClient == null) {
            System.out.println("Esse CPF não está cadastrado");
            return;
        }
        System.out.println("Digite o nome(-1 para pular)");
        String newName = input.next();
        System.out.println("Digite o endereço(-1 para pular)");
        String newEndereço = input.next();

        if(!newName.equals("-1")) {
            newClient.setName(newName);
        }
        if(!newEndereço.equals("-1")) {
            newClient.setAddress(newEndereço);
        }
        System.out.println("Cliente alterado com sucesso!");
        System.out.println(newClient.toString());
    }

    public static void list(){
        for(int x=0;x<(clients.length-1);x++){
            System.out.println(clients[x].toString());
        }

    }

    public static void search(){
        System.out.println("Digite o CPF do cliente: ");
        String CPF = input.next();
        for(int x=0; x < (clients.length-1); x++){
            if(clients[x].getCPF() == CPF){
                System.out.println(clients[x].toString());
            }
        }
    }

    public static void exclude() {
        System.out.println("Digite o CPF do cliente a ser excluído: ");
        String cpf = input.next();
        int clientIndex = -1;
        Client client = null;

        for (int x = 0; x < (clients.length-1); x++) {
            if(clients[x].getCPF().equals(cpf)) {
                client = clients[x];
                clientIndex = x;
            }
        }
        if(client == null) {
            System.out.println("Este cliente não está cadastrado.");
            return;
        }

        TelephoneNumber[] numbers = TelephoneNumber.getNumbers();
        for(int x = 0; x < (numbers.length-1); x++) {
            if(numbers[x].getClient().getCPF().equals(cpf)) {
                System.out.println("Este cliente está associado ao número "+numbers[x].getNumber()+".");
                return;
            }
        }

        if(JOptionPane.showConfirmDialog(null, "Deseja mesmo apagar este cliente?") == 0) {
            clients[clientIndex] = null;
            reshapeClients();
        }
    }

    private static void reshapeClients() {
        for(int x = 0; x < (clients.length-1); x++) {
            if(clients[x] == null) {
                for (int y = x; y < (clients.length-1); y++) {
                    clients[y] = clients[y+1];
                }
            }
        }
        clients = Arrays.copyOf(clients, (clients.length-1));
    }

    @Override
    public String toString() {
        return "\nCliente de CPF " + CPF + ":\nNome: " + name + ".\nEndereço: " + address +".";
    }

  /********************************************************************************************
   * Getters and setters                                                                      *
   ********************************************************************************************/
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCPF() {
        return CPF;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
