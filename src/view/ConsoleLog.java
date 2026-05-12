package view;

public class ConsoleLog {
    public String message;
    public static void Log(String message){
        System.out.println("| LOG |: " + message);
    }
}
