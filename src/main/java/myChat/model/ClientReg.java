package myChat.model;

import myChat.dao.ClientDAO;
import myChat.dao.ClientDAOImpl;
import myChat.dao.CookiesDAOImpl;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class ClientReg {

    ClientDAO clientDAO = new ClientDAOImpl();

    private ClientM clientM;

    Scanner scanner = new Scanner(System.in);

    public ClientM askRegistration(String answer1) {

        if (answer1.equals("1")) {
            System.out.println("Please enter your login");
            String login = scanner.nextLine();
            if (clientDAO.getClientByLogin(login) != null) {

                if (new CookiesDAOImpl().getKeyByLogin(login) != null) {

                    new ClientDAOImpl().update(new ClientDAOImpl().getClientByLogin(login));
                    clientM = new ClientDAOImpl().getClientByLogin(login);
                    System.out.println("Key is on server");

                } else {
                    System.out.println("Please enter your password");
                    checkPassword(login);
                    new ClientDAOImpl().update(new ClientDAOImpl().getClientByLogin(login));
                    clientM = new ClientDAOImpl().getClientByLogin(login);
                }

            } else System.out.println("This login does not exist! Please try again");

        } else if (answer1.equals("2")) {
            String answer2 = replyYesNo();

            if (answer2.equalsIgnoreCase("Y")) {
                System.out.println("Your login: ");
                String login = scanner.nextLine();
                if (clientDAO.getClientByLogin(login) != null) {
                    while (clientDAO.getClientByLogin(login) != null) {
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
                ClientM clientM1 = new ClientM(login, salt, password, dateConnection);
                new ClientDAOImpl().create(clientM1);
                clientM = new ClientDAOImpl().getClientByLogin(login);
            } else if (answer2.equalsIgnoreCase("N")) {
                System.out.println("See you next time!");
            }

        }
        return clientM;
    }

    public String replyYesNo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to register? Enter Y - yes or N - no");
        String answer2 = scanner.nextLine();
        while (!answer2.equalsIgnoreCase("Y") & !answer2.equalsIgnoreCase("N")) {
            System.out.println("You entered a wrong symbol. Please choose Y for yes, N for no to continue");
            answer2 = scanner.nextLine();
        }
        return answer2;
    }

    public void checkPassword(String login) {
        Scanner scanner = new Scanner(System.in);
        ClientM clientM = clientDAO.getSaltByLogin(login);
        String passwordIn = scanner.nextLine();
        String password1 = Integer.toHexString((clientM.getSalt().concat(passwordIn)).hashCode());

        while (!clientM.getPassword().equals(password1)) {
            System.out.println("Wrong password! Please try again");
        }
        System.out.println("Success entry!");
    }
}
