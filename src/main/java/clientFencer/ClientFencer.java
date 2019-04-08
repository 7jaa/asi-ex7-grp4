package clientFencer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;

public class ClientFencer {

  URL url;

  public ClientFencer(){

  }


  {
    try {
      url = new URL("https://api.fencer.io/v1.0");

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setRequestMethod("GET");
      conn.setRequestProperty("Authorization","eaf5b589-6831-5945-897e-79c4872f4358");
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

      }
    } catch (ProtocolException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}