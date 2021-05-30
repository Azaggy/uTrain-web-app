package org.launchcode.uTrain.models.geocoder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GeocodingExample {

    public static double main() throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        Geocoder geocoder = new Geocoder();

        String response = geocoder.GeocodeSync("60901");
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("items");

//        for (JsonNode item : items) {
            JsonNode address = items.get("address");
            String label = address.get("label").asText();
            JsonNode position = items.get("position");

            double lat = position.get("lat").asDouble();
            double lng = position.get("lng").asDouble();
            System.out.println(label + " is located at " + lat + "," + lng + ".");
//        }

        return lat lng;
    }
}
