import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {


    public static String getWeather(String messeage, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + messeage + "&units=metric&appid=ac8b82b3df8a2094343171d26b62115a");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon(obj.getString("icon"));
            model.setMain(obj.getString("main"));
        }


        return "City: " + model.getName() + "\n" +
                "Temperatura: " + model.getTemp() + "C" + "\n" +
                "Humidity: " + model.getHumidity() + "%" + "\n" +
                "Main: " + model.getMain() + "\n" +
                "http://openweathermap.org/img/wn/" + model.getIcon() + ".png";

    }
}
