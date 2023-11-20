package pkg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String api = "https://devdactic.fra1.digitaloceanspaces.com/twitter-ui/tweets.json";

        // Indica el num de solicitudes que se realizaran
        int numReq = 500;
        int i = 0;

        for (i = 0; i < numReq; i++) {
            int reqNumber = i + 1;
            // Cada solicitud se hace en un nuevo hilo
            new Thread(() -> {
                try {
                    String response = sendGetRequest(api);
                    System.out.println("Thread N" + reqNumber + " = " + response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    // Este metodo se encarga de realizar la solicitud GET a la API o url y devuelve la respuesta como un String
    private static String sendGetRequest(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}


