package ua.goit;

import ua.goit.client.ClientService;
import ua.goit.connection.ConnectionFactoryProvider;
import ua.goit.database.WorkDBInitializer;

public class App {
    static void main() {
        new WorkDBInitializer().initializeDB();
        ClientService clientService = new ClientService(ConnectionFactoryProvider.getFactory());
        System.out.println(clientService.listAll());
        System.out.println(clientService.getById(2));
        System.out.println(clientService.create("Paul Gardner"));
        clientService.setName(1, "Joshua Velaskes");
        System.out.println(clientService.listAll());
        clientService.deleteById(7);
        System.out.println(clientService.listAll());
    }
}
