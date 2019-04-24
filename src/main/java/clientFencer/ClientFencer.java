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

public class ClientFencer {

  URL url;

  public ClientFencer() {

  }

  public List<Geofence> getGeofences() {
    try {
      url = new URL("https://api.fencer.io/v1.0");

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setRequestMethod("GET");
      conn.setRequestProperty("Authorization", "eaf5b589-6831-5945-897e-79c4872f4358");
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



    List<Geofence> geofences = new ArrayList<>();
    JSONParser parser = new JSONParser();
    JSONArray geofenceCoordinates = (JSONArray) parser.parse(sb.toString()); // add json http response

    for (Object geofence : geofenceCoordinates)

    {
      JSONObject geofenceJSON = (JSONObject) geofence;
      Geofence geofenceJava = new Geofence();

      String id = (String) geofenceJSON.get("id");
      geofenceJava.setId(id);

      String alias = (String) geofenceJSON.get("alias");
      geofenceJava.setAlias(alias);

      geofences.add(geofenceJava);
      //System.out.println(person);
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
    return null;
  }

}

