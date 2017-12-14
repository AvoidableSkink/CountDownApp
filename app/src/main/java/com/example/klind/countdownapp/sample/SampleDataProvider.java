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

        addItem(new Event(null,"Christmas","2017-12-25",1,"christmas.jpg"));

        addItem(new Event(null, "July 4th", "2018-07-04",2,"fireworks.png"));

        addItem(new Event(null,"My Birthday","2018-07-14",3,"christmas_2.jpg"));

    }

    private static void addItem(Event item) {
        eventItemList.add(item);
        eventItemMap.put(item.getEventId(), item);
    }

}
