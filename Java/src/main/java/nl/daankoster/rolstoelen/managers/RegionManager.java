package nl.daankoster.rolstoelen.managers;

import nl.daankoster.rolstoelen.objects.Region;
import nl.daankoster.rolstoelen.objects.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegionManager {
    public List<Region> regions = new ArrayList();

    public Optional<Region> getRegionFromTag(Tag tag){
        return regions.stream().filter(r -> r.isTagInRegion(tag)).findFirst();
    }

    public void registerRegion(Region region){
        regions.add(region);
    }

}

