package CSArmyBot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import CSArmyBot.commands.AddBlockedLink;
import CSArmyBot.commands.AddPrivate;
import CSArmyBot.commands.ChangePrivateName;
import CSArmyBot.commands.CreatePrivate;
import CSArmyBot.commands.DeletePrivate;
import CSArmyBot.commands.ListBlockedLinks;
import CSArmyBot.commands.RemovePrivate;
import CSArmyBot.commands.ShutDownCommand;
import CSArmyBot.commands.testCommand;
import CSArmyBot.events.GuildJoinEvent;
import CSArmyBot.events.GuildLeaveEvent;
import CSArmyBot.events.LinkBlockerEvent;
import CSArmyBot.events.OnGuildChatEvent;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Main extends ListenerAdapter {
	
	private Logger logger;
	
	public Logger getLogger(){
		return logger;
	}

	public static File file = new File("./userDataTemp.json");
	
    public static Map<String, Map<String, String>> userData = new HashMap<String, Map<String, String>>();
    
    public static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
	
	public static JDA jda;

	public static void main(String[] args) {
		try {
			//Login
			jda = new JDABuilder(AccountType.BOT).setToken(Ref.token).buildBlocking();
			//Set Status
			jda.getPresence().setGame(Game.playing("¯\\_(ツ)_/¯"));
			//Register Listeners
			//jda.addEventListener(new OnGuildChatEvent());
			//jda.addEventListener(new CreatePrivate());
			//jda.addEventListener(new AddPrivate());
			//jda.addEventListener(new ChangePrivateName());
			//jda.addEventListener(new testCommand());
			//jda.addEventListener(new DeletePrivate());
			//jda.addEventListener(new ShutDownCommand());
			//jda.addEventListener(new RemovePrivate());
			//jda.addEventListener(new GuildLeaveEvent());
			//jda.addEventListener(new GuildJoinEvent());
			jda.addEventListener(new LinkBlockerEvent());
			jda.addEventListener(new AddBlockedLink());
			jda.addEventListener(new ListBlockedLinks());
			//Load the config
			Config.init(file);
			//Start the saving proccess
			startSave();
			
		} catch (LoginException | IllegalArgumentException | InterruptedException | JsonIOException | JsonSyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static void startSave(){
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new saveTask(), 0, 5, TimeUnit.MINUTES);
	}
	
	public static void Save() throws IOException{
        String json = gson.toJson(Main.userData, new TypeToken<Map<String, Map<String, String>>>(){}.getType());
        FileWriter writer = new FileWriter(file);
        // Write to the file you passed
        writer.write(json);
        // Always close when done.
        writer.close();
        //Print that it's done
        System.out.println("Saved config!");
	}
	






}
