
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.random.RandomGenerator;


public class Main {
    public static Vector rayColour(Ray r, HittableList world, int depth){
        HitRecord recEmpty = new HitRecord();
        HitRecord rec = world.Hit(r,0.001,Double.POSITIVE_INFINITY, recEmpty);

        //limit number of child rays
        if (depth <=0){
            return new Vector(0.0,0.0,0.0);
        }

        if (rec != null){
            Vector target = rec.point.add(rec.normal).add(Vector.bounceVector()); //get the next vector's direction
            Ray bounceRay = new Ray(rec.point, target.sub(rec.point));
            return (rayColour(bounceRay, world, depth-1)).times(0.5);
        }
       //gradient colouring
        Vector unitV = r.direction().unitVector();
        double t = 0.5*(unitV.y() + 1.0);
        Vector c1 = new Vector(1.0,1.0,1.0);
        Vector c2 = c1.times(1-t);

        Vector c3 = new Vector(0.5, .7,1.0);
        Vector c4 = c3.times(t);

        return c2.add(c4);
    }

    public static void main(String[] args) throws IOException {
        String newline = System.lineSeparator();
        String matrix = "";

//Image
        double aspectRatio = 16.0/9.0;
        int imageWidth = 400;
        int imageHeight = (int) (imageWidth / aspectRatio);
        int samplesPerPixel = 100;
        int maxDepth = 50;

        //header for PPM file
        matrix += "P3" + newline + imageWidth+ " " + imageHeight + newline + "255" + newline;

//World
        HittableList world = new HittableList();
        Sphere s1 = new Sphere( 0,  0,  -1, 0.5);
        Sphere s2 = new Sphere(0,-100.5,-1,100);
        world.add(s1);
        world.add(s2);



//Camera
        Camera cam = new Camera();
        Random gen = new Random(1);

//Render
        for (int j = imageHeight - 1; j >= 0; --j) {
            System.out.println("Lines remaining: " + j + newline); //progress report
            for (int i = 0; i < imageWidth; ++i) {
                Vector pixelColour = new Vector(0.0,0.0,0.0);
                for (int s =0 ;s<samplesPerPixel;++s){
                    double random1 = gen.nextDouble();
                    double random2 = gen.nextDouble();
                    double u = (random1+i)/(imageWidth-1);
                    double v = (random2+j)/(imageHeight-1);
                    Ray r = cam.getRay(u,v);
                    pixelColour = pixelColour.add(rayColour(r,world, maxDepth));
                }
//              both u and v are scalars between 0 and 1, and represent percentage of distance between start and finish

                matrix += pixelColour.writeColour(pixelColour, samplesPerPixel); //caution
            }
        }
        FileOutputStream fos = new FileOutputStream("image");
        fos.write(matrix.getBytes());
        fos.close();
        System.out.println("FINISHED");
    }

}