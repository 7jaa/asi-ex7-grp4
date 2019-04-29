package clientFencer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Geofence;
import model.PersonCoordinates;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.Service;

public class ClientFencer {

  URL url;
  private String ApplicationKey = "bc6a9fac-e4fd-5233-91f3-58640a752302";
  private String ZPlageKey = "1c065621-fbe6-4d2d-b31a-e52755757879";
  private String ZFoodNBeverageKey = "17c31b7e-7c57-40cf-b5f0-3630f53d41cd";
  private String ZSceneKey = "3b7fcd71-a98d-4d95-a917-9ae6e2524ea1";

  Service service = new Service();

  public ClientFencer() {

  }

  public String getZPlageKey() {
    return ZPlageKey;
  }

  public String getZFoodNBeverageKey() {
    return ZFoodNBeverageKey;
  }

  public String getZSceneKey() {
    return ZSceneKey;
  }

  public List<Geofence> getGeofences() {
    try {
      url = new URL("https://api.fencer.io/v1.0/geofence");

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setRequestMethod("GET");
      conn.setRequestProperty("Authorization",ApplicationKey);
      conn.setRequestProperty("Accept", "application/json");

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      } else {
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();

        String output;
        while ((output = br.readLine()) != null) {
          sb.append(output);

        }

        conn.disconnect();

        //System.out.println("Test zone list content:" + sb.toString());

    List<Geofence> geofences = new ArrayList<>();
    JSONParser parser = new JSONParser();
    JSONArray geofenceCoordinates = (JSONArray) ((JSONObject)parser.parse(sb.toString())).get("data"); // THIS IS WHY MY CODE DIDN'T RETURN ANYTHING !!! -> IT WAS MISSING THE "DATA" OBJECT

    for (Object geofence : geofenceCoordinates)

    {
      JSONObject geofenceJSON = (JSONObject) geofence;
      Geofence geofenceJava = new Geofence();


      String id = (String) geofenceJSON.get("id");
      geofenceJava.setId(id);

      String alias = (String) geofenceJSON.get("alias");
      geofenceJava.setAlias(alias);

      geofences.add(geofenceJava);

      //System.out.println(geofences.get(geofences.indexOf(geofenceJava)).getAlias()); - DO NOT UNCOMMENT -- infinite loop!!!!

    }
    }
    } catch (ProtocolException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return getGeofences();
  }

  public int nbPersonInsideGeofence(String zone) throws IOException, ParseException {
    int nbPersInZone = 0;

      url = new URL("https://api.fencer.io/v1.0/position/inside/" + zone);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setRequestProperty("Authorization", ApplicationKey);

    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuilder sb = new StringBuilder();

    String output;
    while ((output = br.readLine()) != null) {
      sb.append(output);

    }
      List<PersonCoordinates> participants = service.getAllParticipants();
      JSONParser parser = new JSONParser();


    for (int i=0; i<= participants.size();i++) {
          conn.setRequestProperty("Lat-Pos", participants.get(i).getGeoPosLat().toString());
          conn.setRequestProperty("Lng-Pos", participants.get(i).getGeoPosLon().toString());
          PersonCoordinates participant = new PersonCoordinates();
          JSONObject inside = (JSONObject) ((JSONObject) parser.parse(sb.toString())).get("inside");
          if (inside.toString() == "true") {
            nbPersInZone++;
          }

        }
        conn.disconnect();
        return nbPersInZone;
      }
    }
