package application;

import clientFencer.ClientFencer;
import org.json.simple.parser.ParseException;
import service.Service;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws ParseException, IOException {
        Service serv = new Service();
        serv.getAllParticipants();

        ClientFencer cliFencer = new ClientFencer();

        System.out.println("--- Exemple du nombre de participants par zone ---");
        System.out.println("Zone 1:");
        System.out.println(serv.getNumberOfParticipantsByZone(1));
        System.out.println("Zone 2:");
        System.out.println(serv.getNumberOfParticipantsByZone(2));
        System.out.println("Zone 3:");
        System.out.println(serv.getNumberOfParticipantsByZone(3));
        System.out.println("Zone 4:");
        System.out.println(serv.getNumberOfParticipantsByZone(4));

        System.out.println("\n--- Exemple du pourcentage de participants par zone ---");
        System.out.println("Zone 1 pourcentage:");
        System.out.println(serv.getPercentageOfParticipantsByZone(1));
        System.out.println("Zone 2 pourcentage:");
        System.out.println(serv.getPercentageOfParticipantsByZone(2));
        System.out.println("Zone 3 pourcentage:");
        System.out.println(serv.getPercentageOfParticipantsByZone(3));
        System.out.println("Zone 4 pourcentage:");
        System.out.println(serv.getPercentageOfParticipantsByZone(4));

        System.out.println(cliFencer.getGeofences().isEmpty());
    }
}
