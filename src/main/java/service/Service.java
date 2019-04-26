/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.*;
import java.util.*;

import model.PersonCoordinates;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.lang.Math.floor;

/**
 * @author MJM
 */
public class Service {

    public Service() {
    }

    public List<PersonCoordinates> getAllParticipants() throws ParseException, IOException {
        List<PersonCoordinates> participants = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONArray peopleCoordinates = (JSONArray) parser
            .parse(new FileReader("FestivaliersGPSPos.json"));

        for (Object pers : peopleCoordinates) {
            JSONObject personJSON = (JSONObject) pers;
            PersonCoordinates person = new PersonCoordinates();

            Long id = (Long) personJSON.get("id");
            person.setId(id);

            Double latitude = (Double) personJSON.get("geoPosLat");
            person.setGeoPosLat(latitude);

            Double longitude = (Double) personJSON.get("geoPosLon");
            person.setGeoPosLon(longitude);

            //add zone to

            Random randomGenerator = new Random();
            int zoneRandom = randomGenerator.nextInt(4) + 1;
            //person.setZone(zoneRandom);

            participants.add(person);
            //System.out.println(person);
        }

        return participants;

    }

    /*public int getNumberOfParticipantsByZone(int zone) throws IOException, ParseException {
        int numberOfParticipants = 0;

        List<PersonCoordinates> participants = getAllParticipants();

        for (PersonCoordinates person : participants) {
            if (person.getZone() == zone) {
                numberOfParticipants++;
            }
        }

        return numberOfParticipants;
    }*/

    /*public double getPercentageOfParticipantsByZone(int zone) throws IOException, ParseException {
        double number = getNumberOfParticipantsByZone(zone);
        return floor(number * 100) / 1000;
    }*/

    public int getNumberOfParticipantsByGeofence(String idGeofence)
        throws IOException, ParseException {
        int nbParticipantsByGeofence = 0;
        List<PersonCoordinates> persons = this.getAllParticipants();



        return nbParticipantsByGeofence;
    }
}

