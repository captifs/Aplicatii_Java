
import java.util.*;
import java.io.File;

public class Main
{



	public static void main(String[] args)
	{
		//citirea din consola
		Scanner scanner = new Scanner(System.in);
		CitescImaginea ri = new CitescImaginea();
		NormalizezCuloare pi = new NormalizezCuloare();
		ScriuImaginea  wi = new ScriuImaginea();
		File currentDir = new File("C:\\Users\\Adrian\\Downloads\\cvculoare\\culoarea\\poza");

		try 
		{
			System.out.println("Bine ai venit in aplicatie!");
			System.out.println("Scrie 'list' daca vrei sa vezi calea imaginilor care sunt disponibile");
			System.out.println("Apasa ENTER daca doresti sa continui");
			String currLine = scanner.nextLine();

			if ("list".equals(currLine)){
				ArataFisiere.displayDirectoryContents(currentDir);
			}

			while (true)
			{


				boolean imageRead = false;
				do
				{
					System.out.println("Care este imaginea pe care vrei sa o prelucrezi? " +
									"Scrie iesi daca vrei sa inchizi aplicatia");
					//citirea caii spre fisier
					String line = scanner.nextLine();
					if ("iesi".equals(line)) {
						System.out.println("Te mai asteptam!");
						return;
					}


					//citirea imaginii de la calea introdusa in consola
					imageRead = ri.readFile(line);
				  } while (!imageRead);
				
				ri.timpCitire();
				//transformarea imaginii
				pi.process();
				//display();
				pi.timpProcesare();
				System.out.println("Introduceti calea catre un fisier de iesire");
				String line = scanner.nextLine();
				wi.writeImage(line);
				wi.timpScriere();
			}
		} finally 
			{
				scanner.close();
			}
	}

}
