package nl.daankoster.rolstoelen.utils;

import nl.daankoster.rolstoelen.Main;
import nl.daankoster.rolstoelen.objects.Tag;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class TagUpdater {
    public HashMap<String, String> oldLocations = new HashMap<>();

    public void start(){
        TimerTask task = new TimerTask() {
            public void run() {
                for (Tag tag : Main.tagManager.tags) {
                    if (!oldLocations.containsKey(tag.getTagID())){

                        oldLocations.put(tag.getTagID(), "");
                    }
                    if (oldLocations.get(tag.getTagID()).equalsIgnoreCase(tag.getLocatie())){
                        System.out.println("Skipped tag: " + tag.getTagID());
                        return;
                    }
                    System.out.println("updated tag" + tag.getTagID());
                    Main.databaseManager.updateRolstoel(tag);
                    oldLocations.put(tag.getTagID(), tag.getLocatie());
                    return;
                }
            }
        };

        Timer timer = new Timer("Timer");

        long delay = 5000L;
        timer.schedule(task, delay, delay);
    }
}
