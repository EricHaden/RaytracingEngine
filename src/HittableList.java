import java.util.ArrayList;
import java.util.List;

public class HittableList {
    private List<Hittable> hittables;
    public HittableList(){
        hittables = new ArrayList<>();
    }
    public boolean add(Hittable h){
        return hittables.add(h);
    }
    public void clear(){
        hittables.clear();
    }
    public HitRecord Hit(Ray r, double tMin, double tMax, HitRecord record) {
        boolean hitAnything = false;
        HitRecord temp = new HitRecord();
        double closestHit = tMax;
        for (Hittable object: hittables){
            if (object.Hit(r,tMin,closestHit,temp)){
                hitAnything = true;
                closestHit = record.t;
                return temp;
            }
        }
        return null;

    }
}
