package CSArmyBot.commands;

import java.io.IOException;

import CSArmyBot.Main;
import CSArmyBot.Ref;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class ShutDownCommand implements EventListener{

	@Override
	public void onEvent(Event event) {
		if (event instanceof GuildMessageReceivedEvent){

			if (!((GuildMessageReceivedEvent) event).getAuthor().getId().equals(Ref.owner_id)) return; 
			
			String[] msg = ((GuildMessageReceivedEvent) event).getMessage().getContentRaw().split(" ");

			if (msg[0].equals("!shutdown")){
				((GuildMessageReceivedEvent) event).getChannel().sendMessage("Shutting down...").queue();
				try {
					Main.Save();
				} catch (IOException e) {

					e.printStackTrace();
				}
				event.getJDA().shutdown();
				System.exit(0);
			}
		}
	}

}
