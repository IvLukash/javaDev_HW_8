package ua.goit;

import ua.goit.client.ClientService;
import ua.goit.connection.ConnectionFactoryProvider;
import ua.goit.database.WorkDBInitializer;
import ua.goit.exception.InvalidIdException;
import ua.goit.exception.NameLengthException;

public class App {
    static void main() {
        new WorkDBInitializer().initializeDB();
        ClientService clientService = new ClientService(ConnectionFactoryProvider.getFactory());

        System.out.println(clientService.listAll());

        try {
            System.out.println(clientService.getById(2));
        } catch (InvalidIdException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(clientService.create("Paul Gardner"));
        } catch (NameLengthException e) {
            throw new RuntimeException(e);
        }

        try {
            clientService.setName(1, "i");
        } catch (InvalidIdException | NameLengthException e) {
            throw new RuntimeException(e);
        }

        try {
            clientService.setName(1, "Joshua Velaskes");
        } catch (InvalidIdException | NameLengthException e) {
            throw new RuntimeException(e);
        }

        System.out.println(clientService.listAll());

        try {
            clientService.deleteById(7);
        } catch (InvalidIdException e) {
            throw new RuntimeException(e);
        }

        System.out.println(clientService.listAll());

        try {
            clientService.create("Taras".repeat(300));
        } catch (NameLengthException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(clientService.getById(-1));
        } catch (InvalidIdException e) {
            throw new RuntimeException(e);
        }
    }
}
