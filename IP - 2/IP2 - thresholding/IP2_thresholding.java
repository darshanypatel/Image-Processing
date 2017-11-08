import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

class IP2_thresholding {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter thresholding value: ");
        int thresholding_value = sc.nextInt();
    	BufferedImage img = null;
        try {
        img = ImageIO.read(new File("C:\\Users\\Foram\\Desktop\\input.jpg"));
        } catch (IOException e) {
        	System.out.println(e);
        }
        
        int w = img.getWidth();
        int h = img.getHeight();
        
        int rgb = 0, r, g, b;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
            	rgb = img.getRGB(j, i);
            	Color c = new Color(rgb);
            	r = c.getRed();
            	g = c.getGreen();
            	b = c.getBlue();
            	
            	int avg = (r + g + b) / 3;
            	if (avg > thresholding_value) {
            		img.setRGB(j, i, new Color(255, 255, 255, 255).getRGB());
            	} else {
            		img.setRGB(j, i, new Color(0, 0, 0, 0).getRGB());
            	}
                
            }
        }
        
        File f = new File("C:\\Users\\Foram\\Desktop\\output.jpg");
        ImageIO.write(img, "JPG", f);  
        sc.close();
        
    }
}
