import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    //93b4ceb459cf6f5195575187e4f8e0db
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=93b4ceb459cf6f5195575187e4f8e0db");

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
            model.setIcon((String)obj.get("icon"));
            model.setMain((String)obj.get("main"));

        }
        return "City: "+model.getName()+"\n"+
                "Tempereture: "+model.getTemp()+ "C"+"\n"+
                "Humidity: "+model.getHumidity()+"%"+"\n"+
                "Main: "+model.getMain()+"\n"+
                "http://openweathermap.org/img/wn/"+model.getIcon()+".png";
    }
}
