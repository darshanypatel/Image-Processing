import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

class IP2_GreyLevelSlicingWB {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the range(a and b): ");
		int A = sc.nextInt();
		int B = sc.nextInt();
		System.out.println("With background or without background? (1/0): ");
		int bg = sc.nextInt();
		BufferedImage img = null;
		try {
			img = ImageIO
					.read(new File("C:\\Users\\Foram\\Desktop\\input.jpg"));
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
				if (avg >= A && avg <= B) {
					img.setRGB(j, i, new Color(255, 255, 255, 255).getRGB());
				} else {
					if (bg == 0)
						img.setRGB(j, i, new Color(0, 0, 0, 0).getRGB());
				}

			}
		}

		File f = new File("C:\\Users\\Foram\\Desktop\\output3.jpg");
		ImageIO.write(img, "JPG", f);
		sc.close();

	}
}
