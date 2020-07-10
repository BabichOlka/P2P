package myChat.authorization;

import myChat.model.ClientModel;
import myChat.service.ClientService;
import myChat.service.CookiesService;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
public class ClientReg {

    ClientService clientService = new ClientService();

    private ClientModel clientModel;

    Scanner scanner = new Scanner(System.in);

    public ClientModel registration(String answer1) {

        if (answer1.equals("1")) {
            checkRegisteredClient();

        } else if (answer1.equals("2")) {
            String answer2 = replyYesNo();

            if (answer2.equalsIgnoreCase("Y")) {
                System.out.println("Your login: ");
                String login = scanner.nextLine();
                if (clientService.getClientByLogin(login) != null) {
                    while (clientService.getClientByLogin(login) != null) {
                        System.out.println("This login exists! Please try again");
                        System.out.println("Your login: ");
                        String login1 = scanner.nextLine();
                        login=login1;
                    }

                }
                System.out.println("Your password: ");
                String salt = UUID.randomUUID().toString();
                String password = Integer.toHexString(salt.concat(scanner.nextLine()).hashCode());
                Date dateConnection = new Date();
                ClientModel clientModel1 = new ClientModel(login, salt, password, dateConnection);
                new ClientService().createClient(clientModel1);
                clientModel = new ClientService().getClientByLogin(login);
            } else if (answer2.equalsIgnoreCase("N")) {
                System.out.println("See you next time!");
            }
        }
        return clientModel;
    }

    public String replyYesNo() {
        System.out.println("Would you like to register? Enter Y - yes or N - no");
        String answer2 = scanner.nextLine();
        while (!answer2.equalsIgnoreCase("Y") & !answer2.equalsIgnoreCase("N")) {
            System.out.println("You entered a wrong symbol. Please choose Y for yes, N for no to continue");
            answer2 = scanner.nextLine();
        }
        return answer2;
    }

    public void checkPassword(String login) {
        ClientModel clientSalt = clientService.getSaltByLogin(login);
        String passwordIn = scanner.nextLine();
        String password1 = Integer.toHexString(((clientSalt).toString().concat(passwordIn)).hashCode());
        while (!clientModel.getPassword().equals(password1)) {
            System.out.println("Wrong password! Please try again");
        }
        System.out.println("Success entry!");
    }

    public void checkRegisteredClient() {
        System.out.println("Please enter your login");
        String login = scanner.nextLine();
        if (clientService.getClientByLogin(login) != null) {
            if (new CookiesService().getKeyByLogin(login) != null) {
                new ClientService().updateClient(new ClientService().getClientByLogin(login));
                clientModel = new ClientService().getClientByLogin(login);
                System.out.println("Key is on server");
            } else {
                System.out.println("Please enter your password");
                checkPassword(login);
                new ClientService().updateClient(new ClientService().getClientByLogin(login));
                clientModel = new ClientService().getClientByLogin(login);
            }
        } else System.out.println("This login does not exist! Please try again");
    }

    public String checkLogin(String login) {
            while (clientService.getClientByLogin(login) != null) {
                System.out.println("This login exists! Please try again");
                System.out.println("Your login: ");
                String login1 = scanner.nextLine();
                login = login1;
            }
         return login;
    }

}
//
//public class ClientReg {
//
//    private ClientModel clientModel;
//
//    Scanner scanner = new Scanner(System.in);
//    ClientService clientService = new ClientService();
//
//    public ClientModel registration(String answer1) {
//
//        if (answer1.equals("1")) {
//            System.out.println("Please enter your login");
//            String login = scanner.nextLine();
//            if (clientService.getClientByLogin(login)!= null) {
//
//                if (new CookiesService().getKeyByLogin(login) != null) {
//                     new ClientService().updateClient(new ClientService().getClientByLogin(login));
//                    clientModel = new ClientService().getClientByLogin(login);
//                    System.out.println("Key is on server");
//                }
//                else {
//                    System.out.println("Please enter your password");
//                    checkPassword(login);
//                    new ClientService().updateClient(new ClientService().getClientByLogin(login));
//                    clientModel = new ClientService().getClientByLogin(login);
//                }
//            }
//            else System.out.println("This login does not exist! Please try again");
//
//        } else if (answer1.equals("2")) {
//            String answer2 = replyYesNo();
//
//            if (answer2.equalsIgnoreCase("Y")) {
//                System.out.println("Your login: ");
//                String login = scanner.nextLine();
//                if (clientService.getClientByLogin(login) != null) {
//                    while (clientService.getClientByLogin(login) != null) {
//                        System.out.println("This login exists! Please try again");
//                        System.out.println("Your login: ");
//                        String login1 = scanner.nextLine();
//                        login=login1;
//                    }
//                }
//                System.out.println("Your password: ");
//                String salt = UUID.randomUUID().toString();
//                String password = Integer.toHexString(salt.concat(scanner.nextLine()).hashCode());
//                Date dateConnection = new Date();
//                ClientModel clientModel1 = new ClientModel(login, salt, password, dateConnection);
//                new ClientService().createClient(clientModel1);
//                clientModel = new ClientService().getClientByLogin(login);
//
//            } else if (answer2.equalsIgnoreCase("N")) {
//                System.out.println("See you next time!");
//            }
//        }
//        return clientModel;
//    }
//
//    public String replyYesNo() {
//        System.out.println("Would you like to register? Enter Y - yes or N - no");
//        String answer2 = scanner.nextLine();
//        while (!answer2.equalsIgnoreCase("Y") & !answer2.equalsIgnoreCase("N")) {
//            System.out.println("You entered a wrong symbol. Please choose Y for yes, N for no to continue");
//            answer2 = scanner.nextLine();
//        }
//        return answer2;
//    }
//
//    public void checkPassword(String login) {
//        ClientModel clientSalt = clientService.getSaltByLogin(login);
//        String passwordIn = scanner.nextLine();
//        String password1 = Integer.toHexString(((clientSalt).toString().concat(passwordIn)).hashCode());
//        while (!clientModel.getPassword().equals(password1)) {
//            System.out.println("Wrong password! Please try again");
//        }
//        System.out.println("Success entry!");
//    }
//    public static void checkRegisteredClient(String login){
//
//    }
//}
