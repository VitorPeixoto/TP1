package tp1;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Lucas Oliveira
 * @author Vítor Peixoto
 */
public class PlanType {
    private final int code;
    private String description;
    private double monthlyValue,
                   callPrice;
    private int allowance;
    
    private static PlanType[] types = new PlanType[1];
    private static int actualCode = -1;
    private static Scanner input = new Scanner(System.in);

    static {
        input.useDelimiter(Pattern.compile("[\\n;]"));
    }

    private PlanType() {
        this.code  = generateCode();
    }     

    private PlanType(String description, double monthlyValue, double callPrice, int allowance) {
        this.code         = generateCode();        
        this.description  = description;
        this.monthlyValue = monthlyValue;
        this.callPrice    = callPrice;
        this.allowance    = allowance;        
    }
    
    private int generateCode() {
        this.actualCode++;
        return actualCode;
    }
    
    private static void addInTypes(PlanType newPlanType) {
        types[(types.length-1)] = newPlanType;
        types = Arrays.copyOf(types, (types.length+1));
    }
    
    public static void register() {
        System.out.println("Digite a descrição do tipo de plano:");
        String description = input.next();
        
        System.out.println("Digite o preço mensal:");
        double monthlyValue = input.nextDouble();
        
        System.out.println("Digite o preço da ligação:");
        double callPrice = input.nextDouble();
        
        System.out.println("Digite a franquia:");
        int allowance = input.nextInt();
        
        PlanType newPlanType = new PlanType(description, monthlyValue, callPrice, allowance);
        addInTypes(newPlanType);
        
        System.out.println("Plano cadastrado com código: "+newPlanType.getCode());
    }

    public static void update() {
        System.out.println("Digite o código do plano a ser alterado:");
        int code = input.nextInt();
        
        PlanType newPlanType = null;
        
        for (int i = 0; i < (types.length -1); i++) {
            if(types[i].getCode() == code) {
                newPlanType = types[i];
            }
        }
        
        if(newPlanType == null) {
            System.err.println("Este tipo de plano não existe!");
            return;
        }
        
        System.out.println("Digite a descrição do tipo de plano (-1 para pular):");
        String description = input.next();
        
        System.out.println("Digite o preço mensal (-1 para pular):");
        double monthlyValue = input.nextDouble();
        
        System.out.println("Digite o preço da ligação (-1 para pular):");
        double callPrice = input.nextDouble();
        
        System.out.println("Digite a franquia (-1 para pular):");
        int allowance = input.nextInt();
        
        if(!description.equals("-1")) newPlanType.setDescription(description);
        if(monthlyValue != -1) newPlanType.setMonthlyValue(monthlyValue);
        if(callPrice != -1) newPlanType.setCallPrice(callPrice);
        if(allowance != -1) newPlanType.setAllowance(allowance);
        
        System.out.println("Plano alterado com sucesso!");
        System.out.println(newPlanType.toString());        
    }

    public static void list(){
        for(int x = 0; x < (types.length-1); x++){
            System.out.println(types[x].toString());
        }
    }

    public static void search(){
        System.out.println("Digite o codigo do plano");
        int code = input.nextInt();
        for(int x=0; x < (types.length-1); x++){
            if(types[x].getCode() == code){
                System.out.println(types[x].toString());
            }
        }
    }

    public static void exclude() {
        System.out.println("Digite o código do plano a ser excluído: ");
        int code = input.nextInt();
        int typeIndex = -1;
        PlanType type = null;

        for (int x = 0; x < (types.length-1); x++) {
            if(types[x].getCode() == code) {
                type = types[x];
                typeIndex = x;
            }
        }
        if(type == null) {
            System.out.println("Este tipo de plano não está cadastrado.");
            return;
        }

        TelephoneNumber[] numbers = TelephoneNumber.getNumbers();
        for(int x = 0; x < (numbers.length-1); x++) {
            if(numbers[x].getType().getCode() == code) {
                System.out.println("Este plano está associado ao número "+numbers[x].getNumber()+".");
                return;
            }
        }

        if(JOptionPane.showConfirmDialog(null, "Deseja mesmo apagar este plano?") == 0) {
            types[typeIndex] = null;
            reshapeTypes();
        }
    }

    private static void reshapeTypes() {
        for(int x = 0; x < (types.length-1); x++) {
            if(types[x] == null) {
                for (int y = x; y < (types.length-1); y++) {
                    types[y] = types[y+1];
                }
            }
        }
        types = Arrays.copyOf(types, (types.length-1));
    }

    @Override
    public String toString() {
        return "\nTipo de plano "+code+":\nDescrição: "+ description+".\nValor Mensal: "+monthlyValue+".\nPreço da Ligação: "+callPrice+".\nFranquia: "+allowance+".";
    }

  /********************************************************************************************
   * Getters and setters                                                                      *
   ********************************************************************************************/
    public static PlanType[] getTypes() {
        return types;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMonthlyValue() {
        return monthlyValue;
    }

    public void setMonthlyValue(double monthlyValue) {
        this.monthlyValue = monthlyValue;
    }

    public double getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(double callPrice) {
        this.callPrice = callPrice;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getCode() {
        return code;
    }
}
