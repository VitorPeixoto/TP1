package tp1;

/**
 *
 * @author Lucas Oliveira
 * @author Vítor Peixoto
 */
public class Main {

    public static void main(String[] args) {
        PlanType.register();
        PlanType.register();
        //PlanType.update();
        System.out.println("TRANSA");
        PlanType.list();
        //Client.register();
        //Client.update();
        //Client.list();
    }
    
}
