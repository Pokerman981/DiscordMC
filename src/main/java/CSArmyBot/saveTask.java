package CSArmyBot;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

public class saveTask implements Runnable{

	@Override
	public void run() {
		try {
            String json = Main.gson.toJson(Main.userData, new TypeToken<Map<String, Map<String, String>>>(){}.getType());
            FileWriter writer = new FileWriter(Main.file);
            // Write to the file you passed
            writer.write(json);
            // Always close when done.
            writer.close();
            //Print that it saved
            System.out.println("Saved config!");
			} catch (IOException e){
				e.printStackTrace();
			}
	}

}
