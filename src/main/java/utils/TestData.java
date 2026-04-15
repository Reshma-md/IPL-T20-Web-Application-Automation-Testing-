package utils;

import java.util.HashMap;
import java.util.Map;

public class TestData {

    public static Map<String, String> getTeamWinningData() {

        Map<String, String> data = new HashMap<>();

        data.put("Chennai Super Kings", "2010, 2011, 2018, 2021, 2023");
        data.put("Mumbai Indians", "2013, 2015, 2017, 2019, 2020");
        data.put("Kolkata Knight Riders", "2012, 2014");
        data.put("Sunrisers Hyderabad", "2016");
        data.put("Rajasthan Royals", "2008");
        data.put("Gujarat Titans", "2022");

        // Teams with NO trophies
        data.put("Delhi Capitals", "");
        data.put("Punjab Kings", "");
        data.put("Royal Challengers Bengaluru", "");
        data.put("Lucknow Super Giants", "");

        return data;
    }
}