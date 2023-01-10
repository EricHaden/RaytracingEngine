public class Camera {
    private double aspectRatio = 16.0/9.0;
    private double viewPortHeight = 2.0;

    double viewPortWidth = aspectRatio*viewPortHeight;
    private double focalLength = 1;

    private Vector origin = new Vector(0.0,0.0,0.0);
    private Vector horizontal = new Vector(viewPortWidth,0.0,0.0);
    private Vector vertical = new Vector(0.0, viewPortHeight, 0.0);
    private Vector vectorFocalLength = new Vector(0.0,0.0,focalLength);
    private  Vector lowerLeftCorner = origin.sub(horizontal.divide(2.0)).sub(vertical.divide(2.0)).sub(vectorFocalLength);

    public Camera(){}

    public Ray getRay(double u, double v){
        return new Ray(origin, lowerLeftCorner.add(horizontal.times(u)).add(vertical.times(v)).sub(origin));
    }
}
