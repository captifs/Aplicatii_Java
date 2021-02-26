import java.awt.Color;
import java.awt.image.BufferedImage;

import java.util.ArrayList;


public class NormalizezCuloare extends CitescImaginea {
	
	private long timeProcess;
	public NormalizezCuloare()
	{
		timeProcess =  System.currentTimeMillis();
	}
	public  BufferedImage process() {
	        int red;
	        int green;
	        int blue;
	        int alpha;
	        int newPixel = 0;
	       timeProcess =  System.currentTimeMillis();
	        // obinem tabelul histogramei
	        ArrayList<int[]> histLUT = equalizeHistogram();
	   
	   	 
	        BufferedImage histogramEQ = new BufferedImage(getImage().getWidth(),getImage().getHeight(), getImage().getType());
	 
	        for(int i=0; i < getImage().getWidth(); i++) {
	            for(int j=0; j < getImage().getHeight(); j++) {
	 
	                // obtinem pixelii pentru R, G, B
	                alpha = new Color(getImage().getRGB (i, j)).getAlpha();
	                red = new Color(getImage().getRGB (i, j)).getRed();
	                green = new Color(getImage().getRGB (i, j)).getGreen();
	                blue = new Color(getImage().getRGB (i, j)).getBlue();
	 
	                // Setam noile valori ale pixelilor folosind histograma
	                red = histLUT.get(0)[red];
	                green = histLUT.get(1)[green];
	                blue = histLUT.get(2)[blue];
	 
	                // le combinam �n forma initiala
	                newPixel = convertColorToRGB(alpha, red, green, blue);
	 
	                // scriem pixelii in imagine
	                histogramEQ.setRGB(i, j, newPixel);
	 
	            }
	        }
	        
	        timeProcess = System.currentTimeMillis() - timeProcess;
	       
	        return histogramEQ;
	        
	    }
	 
	    // obtinem histograme pentru fiecare canal de culoare R, B, B
	   public static ArrayList<int[]> equalizeHistogram() {
		   BufferedImage original = getImage();
	        // calculam valoare histogramei pentru fiecare canal de culoare
	        ArrayList<int[]> imageHist = saveHistogram();
	 
	        // vectorul �n care se vor salva valorile
	        ArrayList<int[]> imageLUT = new ArrayList<int[]>();
	 
	        // Declaram vectorii
	        int[] rhistogram = new int[256];
	        int[] ghistogram = new int[256];
	        int[] bhistogram = new int[256];

	 
	        long sumr = 0;
	        long sumg = 0;
	        long sumb = 0;
	 
	        //Calculam factorul de scalare
	        float scaleFactor = (float) (255.0 / (original.getWidth() * original.getHeight()));
	 
	        for(int i=0; i<rhistogram.length; i++) {
	            sumr += imageHist.get(0)[i];
	            int valr = (int) (sumr * scaleFactor);
				rhistogram[i] = Math.min(valr, 255);
	 
	            sumg += imageHist.get(1)[i];
	            int valg = (int) (sumg * scaleFactor);
				ghistogram[i] = Math.min(valg, 255);
	 
	            sumb += imageHist.get(2)[i];
	            int valb = (int) (sumb * scaleFactor);
				bhistogram[i] = Math.min(valb, 255);
	        }
	 
	        imageLUT.add(rhistogram);
	        imageLUT.add(ghistogram);
	        imageLUT.add(bhistogram);
	 
	        return imageLUT;
	 
	    }
	 
	    // Returnam un ArrayList care va avea valorile histogramei salvate pentru fiecare canal de culoare
	    public static ArrayList<int[]> saveHistogram() {
	    	BufferedImage original = getImage();
	        int[] rhistogram = new int[256];
	        int[] ghistogram = new int[256];
	        int[] bhistogram = new int[256];
	 
	        for(int i=0; i<original.getWidth(); i++) {
	            for(int j=0; j<original.getHeight(); j++) {
	 
	                int red = new Color(original.getRGB (i, j)).getRed();
	                int green = new Color(original.getRGB (i, j)).getGreen();
	                int blue = new Color(original.getRGB (i, j)).getBlue();
	 
	                
	                rhistogram[red]++; ghistogram[green]++; bhistogram[blue]++;
	 
	            }
	        }
	 
	        ArrayList<int[]> hist = new ArrayList<int[]>();
	        hist.add(rhistogram);
	        hist.add(ghistogram);
	        hist.add(bhistogram);
	 
	        return hist;
	 
	    }
	 
	    // Converteste R, G, B, Alpha la 8 biti
	  public static int convertColorToRGB(int alpha, int red, int green, int blue) {
	 
	        int newPixel = 0;
	        newPixel += alpha; newPixel = newPixel << 8;
	        newPixel += red; newPixel = newPixel << 8;
	        newPixel += green; newPixel = newPixel << 8;
	        newPixel += blue;
	 
	        return newPixel;
	 
	    }
	  @Override
	 public long timpProcesare() {
		 display("Acesta","este", "timpul", "de", "procesare " );
		 System.out.println( timeProcess/ 1000.0f + " secunde");

		 return timeProcess;
	 }
	  
	 
}
	
