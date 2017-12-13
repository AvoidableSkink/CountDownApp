package com.example.klind.countdownapp.sample;

import com.example.klind.countdownapp.model.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by klind on 12/13/2017.
 */

public class SampleDataProvider {
    public static List<Event> eventItemList;
    public static Map<String, Event> eventItemMap;

    static {
        eventItemList = new ArrayList<>();
        eventItemMap = new HashMap<>();

        addItem(new Event(null,"Christmas","12-25-2017",1,"christmas.jpg"));

        addItem(new Event(null, "July 4th", "07-04-2018",2,"fireworks.png"));

    }

    private static void addItem(Event item) {
        eventItemList.add(item);
        eventItemMap.put(item.getEventId(), item);
    }

}
