package nl.daankoster.rolstoelen.objects;

import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tag {
    public String tagID;
    public long x;
    public long y;
    public String locatie;

    public Tag(String tagId) {
        this.tagID = tagId;
    }
}
