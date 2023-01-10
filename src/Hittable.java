public abstract class Hittable {
    public abstract boolean Hit(Ray r, double tMin, double tMax, HitRecord record);
}
