package application.userInterface;

import application.service.ClientService;

import java.util.Scanner;

public class UserInterface {
    public void start() {
        keepCommandListVisibleWhileUserInputIsNotZero();
        closeProgram();
    }

    private void keepCommandListVisibleWhileUserInputIsNotZero() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        do {
            userInput = getUserCommand(scanner);
        } while (userInput != 0);
    }

    private void closeProgram() {
        System.out.println("Programma in chiusura..");
        System.exit(0);
    }

    private int getUserCommand(Scanner scanner) {
        int userInput;
        printToConsole();
        userInput = getUserInput(scanner);
        inputControl(userInput, scanner);
        ClientService clientService = new ClientService();
        clientService.getOperation(userInput);
        return userInput;
    }

    private Integer getUserInput(Scanner scanner) {
        System.out.println("Digita un comando:");
        return scanner.nextInt();
    }

    private void printToConsole() {
        System.out.println("\n#Quale operazione vuoi svolgere tra le seguenti?#:\n");
        System.out.println("1 - Visualizza tutti i viaggi");
        System.out.println("2 - Prenota un viaggio");
        System.out.println("3 - Disdici la prenotazione di un viaggio");
        System.out.println("4 - Aggiungi un nuovo utente");
        System.out.println("5 - Esporta un file con i viaggi disponibili");
        System.out.println("0 - Esci dal programma\n");
    }

    private void inputControl(Integer userInput, Scanner scanner) {
        while (userInput > 5 || userInput < 0) {
            System.out.println("Inserisci un comando valido");
            userInput = getUserInput(scanner);
        }
    }
}
