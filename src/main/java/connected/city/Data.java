package connected.city;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Data {

	private Logger log = org.slf4j.LoggerFactory.getLogger(Data.class);
	
	@Bean
	public Routes parseFile() {
		Routes cityConnectionStore = new Routes();
		try (FileReader fr = getFileReader()) {
			BufferedReader br = new BufferedReader(fr);
			addDataToStore(br, cityConnectionStore);
		} catch (IOException e) {
			log.error("Could not parse file ", e);
		}
		return cityConnectionStore;
	}

	private void addDataToStore(BufferedReader br, Routes cityConnectionStore) throws IOException {
		String line;
		CityRoutes cityRoutes = new CityRoutes();
		while ((line = br.readLine()) != null) {
			String[] cities = line.split(",");
			cityRoutes.addTwoWayVertex(cities[0].trim().toLowerCase(), cities[1].trim().toLowerCase());
		}
		cityConnectionStore.setCityRoutes(cityRoutes);
	}

	private FileReader getFileReader() throws IOException, FileNotFoundException {
		File file = new File("./src/main/resources/city.txt");
		FileReader fr = new FileReader(file);
		return fr;
	}
}
