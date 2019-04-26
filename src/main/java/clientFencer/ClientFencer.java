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
  private String ApplicationKey = "eaf5b589-6831-5945-897e-79c4872f4358";
  private String ZPlageKey = "cf5fb706-ab61-4127-bfea-75fdac0e4d3a";
  private String ZFoodNBeverageKey = "3957835b-0a5f-4b83-98b7-cea456e0698c";
  private String ZSceneKey = "553d30b3-d0aa-491d-8f6a-a2e3de1ee8ab";

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

        System.out.println("Result:" + sb.toString());

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

      //System.out.println(geofenceJava.getAlias());
      return geofences;
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

        return nbPersInZone;
      }
    }
