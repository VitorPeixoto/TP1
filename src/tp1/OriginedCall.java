package tp1;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Peixoto on 04/05/2016.
 */
public class OriginedCall {
    private TelephoneNumber originNumber;//,
    //                        destinationNumber;
    private String destinationNumber;

    private Date callBegin,
                 callEnd;

    private static OriginedCall calls[] = new OriginedCall[1];
    private static Scanner input = new Scanner(System.in);

    static {
        input.useDelimiter(Pattern.compile("[\\n;]"));
    }

    //private OriginedCall(TelephoneNumber originNumber, TelephoneNumber destinationNumber, Date callBegin, Date callEnd) {
    private OriginedCall(TelephoneNumber originNumber, String destinationNumber, Date callBegin, Date callEnd) {
        this.originNumber      = originNumber;
        this.destinationNumber = destinationNumber;
        this.callBegin         = callBegin;
        this.callEnd           = callEnd;
    }

    public static void register() {
        //Lendo a data de início
        System.out.println("Digite a data de inicio da chamada (DD/MM/AAAA 00:00:00):");
        String brazilianFormat = input.next();
        Date callBeginDate = new Date(brazilianToAmerican(brazilianFormat));

        //Lendo a data de fim
        System.out.println("Digite a data de término da chamada (DD/MM/AAAA 00:00:00):");
        brazilianFormat = input.next();
        Date callEndDate = new Date(brazilianToAmerican(brazilianFormat));
        if(callBeginDate.compareTo(callEndDate) > 0) {
            System.out.println("A data de início deve ser antes da data de término.");
            return;
        }

        //Lendo o número de origem
        System.out.println("Digite o numero de origem:");
        String newOriginNumber = input.next();
        TelephoneNumber originNumber = null;

        TelephoneNumber numbers[] = TelephoneNumber.getNumbers();
        for(int x = 0; x < (numbers.length-1); x++){
            if(numbers[x].getNumber().equals(newOriginNumber)){
                originNumber = numbers[x];
            }
        }
        if(originNumber == null) {
            System.out.println("Este telefone de origem não existe.");
            return;
        }
        if(originNumber.getCancelationDate() != null) {
            if(originNumber.getCancelationDate().compareTo(callBeginDate) > 0) {
                System.out.println("A data da chamada não pode ser após o cancelamento.");
            }
        }

        //Lendo o número de destino
        System.out.println("Digite o numero de destino:");
        String newDestinationNumber = input.next();
        TelephoneNumber destinationNumber = null;

        /*for(int x = 0; x < (numbers.length-1); x++){
            if(numbers[x].getNumber().equals(newDestinationNumber)){
                destinationNumber = numbers[x];
            }
        }
        if(destinationNumber == null) {
            System.out.println("Este telefone de destino não existe.");
            return;
        }
        if(originNumber.getCancelationDate() != null) {
            if(originNumber.getCancelationDate().compareTo(callEndDate) > 0) {
                System.out.println("A data de fim da chamada não pode ser após o cancelamento.");
            }
        }*/

        //OriginedCall newCall = new OriginedCall(originNumber, destinationNumber, callBeginDate, callEndDate);
        OriginedCall newCall = new OriginedCall(originNumber, newDestinationNumber, callBeginDate, callEndDate);
        addInCalls(newCall);
        System.out.println("Chamada cadastrada com sucesso!");
    }

    private static void addInCalls(OriginedCall newCall){
        calls[(calls.length-1)] = newCall;
        calls = Arrays.copyOf(calls, (calls.length+1));
    }

    public static void list() {
        for(int x = 0; x < (calls.length-1); x++) {
            System.out.println(calls[x].toString());
        }
    }

    public static void search(){
        System.out.println("Digite o número de telefone: ");
        String number = input.next();
        for(int x=0; x < (calls.length-1); x++){
            if(calls[x].getOriginNumber().getNumber().equals(number)){
                System.out.println(calls[x].toString());
            }
        }
    }

    public static void exclude() {
        System.out.println("Digite o número de origem da chamada a ser excluída: ");
        String originNumber = input.next();
        int callsIndex = -1;
        OriginedCall originCall = null;

        System.out.println("Digite a data de origem da chamada a ser excluída (DD/MM/AAAA 00:00:00): ");
        String originDateString = input.next();
        Date originDate = new Date(brazilianToAmerican(originDateString));

        for (int x = 0; x < (calls.length-1); x++) {
            if(calls[x].getOriginNumber().getNumber().equals(originNumber) &&
               (calls[x].getCallBegin().compareTo(originDate) == 0)) {
                callsIndex =x;
                originCall = calls[x];
            }
        }
        if(originCall == null) {
            System.out.println("Essa ligaçao não está cadastrado.");
            return;
        }

        if(JOptionPane.showConfirmDialog(null, "Deseja mesmo apagar essa ligação ?") == 0) {
            calls[callsIndex] = null;
            reshapeCalls();
            System.out.println("Ligação excluida com sucesso.");
        }
    }

    private static void reshapeCalls() {
        for(int x = 0; x < (calls.length-1); x++) {
            if(calls[x] == null) {
                for (int y = x; y < (calls.length-1); y++) {
                    calls[y] = calls[y+1];
                }
            }
        }
        calls = Arrays.copyOf(calls, (calls.length-1));
    }

    @Override
    public String toString() {
        //return "\nChamada de "+originNumber.getNumber() +":\nDestino: "+destinationNumber.getNumber()+".\nData de início: " + callBegin.toString() + ".\nData de fim: " + callEnd.toString() +".";
        return "\nChamada de "+originNumber.getNumber() +":\nDestino: "+destinationNumber+".\nData de início: " + callBegin.toString() + ".\nData de fim: " + callEnd.toString() +".";
    }
  /********************************************************************************************
   * Getters and setters                                                                      *
   ********************************************************************************************/
    /*public String getDuration () {
        Date duration = callEnd.getTime();
    }*/

    public TelephoneNumber getOriginNumber() {
        return originNumber;
    }

    public void setOriginNumber(TelephoneNumber originNumber) {
        this.originNumber = originNumber;
    }

    //public TelephoneNumber getDestinationNumber() {
    public String getDestinationNumber() {
        return destinationNumber;
    }

    //public void setDestinationNumber(TelephoneNumber destinationNumber) {
    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    public Date getCallBegin() {
        return callBegin;
    }

    public void setCallBegin(Date callBegin) {
        this.callBegin = callBegin;
    }

    public Date getCallEnd() {
        return callEnd;
    }

    public void setCallEnd(Date callEnd) {
        this.callEnd = callEnd;
    }

    public static OriginedCall[] getCalls() {
        return calls;
    }

    public static void setCalls(OriginedCall[] calls) {
        OriginedCall.calls = calls;
    }

    public static String brazilianToAmerican(String brasilianFormatedString) {
        return brasilianFormatedString.substring(3, 5) + "/" + brasilianFormatedString.substring(0, 2) + "/" + brasilianFormatedString.substring(6, brasilianFormatedString.length());
    }
}