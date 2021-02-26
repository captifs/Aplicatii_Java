
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScriuImaginea extends NormalizezCuloare implements Interfata{

	private long timeWrite;
	public ScriuImaginea() {
		timeWrite = System.currentTimeMillis();
	}
	public void writeImage(String file) {
		timeWrite = System.currentTimeMillis();
		try (FileOutputStream stream = new FileOutputStream(file)) {
			//scrierea in fisier a imaginii bmp
			ImageIO.write(super.process(), "BMP", stream);
		} catch (FileNotFoundException e) {
			System.out.println("Calea " + file + " nu este valida");
			return;
		} catch (IOException e) {
			System.out.println("Eroare in timpul scrierii in fisier");
			return;
		}
		//timpul de scriere
		timeWrite = System.currentTimeMillis() - timeWrite;

	}
	@Override
	public long timpScriere() {
		super.display("Acesta", "este", "timpul", "de", "scriere ");
		System.out.println( timeWrite / 1000.0f + " secunde");
		return timeWrite;
	}
	
	
	
}
