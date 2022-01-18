package nl.daankoster.rolstoelen.objects;

import lombok.Data;

@Data
public class Region {

    public final String name;
    public long x1;
    public long x2;
    public long y1;
    public long y2;


    public Region(String name, long x1, long x2, long y1, long y2){
        this.name = name;
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
    }


    public boolean isTagInRegion(Tag tag){
        long x = tag.getX();
        long y = tag.getY();

        return x > x1 && x < x2 && y > y1 && y < y2;
    }
}
