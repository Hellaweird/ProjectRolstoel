package nl.daankoster.rolstoelen.managers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.daankoster.rolstoelen.Main;
import nl.daankoster.rolstoelen.objects.Region;
import nl.daankoster.rolstoelen.objects.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagManager {
    public List<Tag> tags = new ArrayList<>();


    public void updateTags(JsonElement array){
        array.getAsJsonArray().forEach(jsonElement -> {

        JsonObject object = jsonElement.getAsJsonObject();

        String TagId = getTagID(object.get("tagId").getAsInt());

        if (object.get("success").getAsBoolean()){

        Optional<Tag> optionalTag = getTagById(TagId);


        if (optionalTag.isPresent()){

            Tag tag = optionalTag.get();

            if (object.get("data") == null) return;

            JsonObject data = object.get("data").getAsJsonObject();

            if (data == null) return;

            JsonObject coordinates = data.get("coordinates").getAsJsonObject();

            if (coordinates == null) return;


            int x = coordinates.get("x").getAsInt();
            int y = coordinates.get("y").getAsInt();


            tag.setX(x);
            tag.setY(y);


            Optional<Region> optionalRegion = Main.regionManager.getRegionFromTag(tag);
            if (optionalRegion.isPresent()) {
                tag.setLocatie(optionalRegion.get().getName());
            }else {
                tag.setLocatie("geen");
            }

        } else {
            Tag tag = new Tag(TagId);
            tags.add(tag);
        }
            }
        });

    }


    public Optional<Tag> getTagById(String id){
        return tags.stream().filter(tag -> tag.getTagID().equals(id)).findFirst();
    }

    public String getTagID(int i){
        return "0x" + Integer.toHexString(i);
    }


}
