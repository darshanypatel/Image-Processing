package histogramequalization;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class HistogramEqualization {

    public static void main(String[] args) throws IOException {
        
        BufferedImage img;
        File f = new File("/tmp/guest-ojx6hw/Desktop/input2.jpg");
        img = ImageIO.read(f);
        
        int height = img.getHeight();
        int width = img.getWidth();
        int p;
        int a, r, g, b;
        
        int[] redPixels, greenPixels, bluePixels;
        redPixels = new int[256];
        greenPixels = new int[256];
        bluePixels = new int[256];
                
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                p = img.getRGB(j, i);
                Color c = new Color(p);
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();
                
                redPixels[r]++;
                greenPixels[g]++;
                bluePixels[b]++;
                
            }
        }
        
        System.out.println("Original Histograms of Red, green, blue pixels :");
        System.out.println(Arrays.toString(redPixels));
        System.out.println(Arrays.toString(greenPixels));
        System.out.println(Arrays.toString(bluePixels));
        
        System.out.println("Final Histograms of Red, green, blue pixels :");
        
        int []newRed, newGreen, newBlue;
        newRed = equalizeHist(redPixels);
        newGreen = equalizeHist(greenPixels);
        newBlue = equalizeHist(bluePixels);
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                p = img.getRGB(j, i);
                Color c = new Color(p);
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();
                
                r = newRed[r];
                g = newGreen[g];
                b = newBlue[b];
                
                img.setRGB(j, i, new Color(r, g, b).getRGB());                
            }
        }
        
        f = new File("/tmp/guest-ojx6hw/Desktop/output2.jpg");
        ImageIO.write(img, "jpg", f);
        
    }
    
    static int[] equalizeHist(int[] pixels) {
        int total = 0;
        for (int i = 0; i < pixels.length; i++) {
            total += pixels[i];
        }
        
        int l = 256;
        double nk[] = new double[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            nk[i] = pixels[i] * 1.0 / total;
        }
        
        double sk[] = new double[pixels.length];
        sk[0] = nk[0];
        for (int i = 1; i < pixels.length; i++) {
            sk[i] += nk[i] + sk[i - 1];
        }
        
        int arr[] = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            arr[i] = (int)Math.floor(((l - 1) * sk[i]) + 0.5);            
        }
        
        int newPixels[] = new int[pixels.length];
        for (int i = 0; i < arr.length; i++) {
            newPixels[arr[i]] += pixels[i];
        }
        
        System.out.println(Arrays.toString(newPixels));
        return arr;
    }
    
}
