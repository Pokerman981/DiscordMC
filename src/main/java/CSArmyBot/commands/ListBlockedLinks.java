package CSArmyBot.commands;

import java.util.ArrayList;
import java.util.List;

import CSArmyBot.Main;
import CSArmyBot.Ref;
import CSArmyBot.Utils;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class ListBlockedLinks implements EventListener{

	@Override
	public void onEvent(Event event) {
		if (event instanceof GuildMessageReceivedEvent) {
			//Make sure it's the csarmy discord will change 
			if (!((GuildMessageReceivedEvent) event).getGuild().getId().equals(Ref.csarmyguild)) return;
			
			String[] msg = ((GuildMessageReceivedEvent) event).getMessage().getContentRaw().split(" ");
			if (!msg[0].equals("!showlinks")) return;
		
			//Check that they're staff
			if (!Utils.isStaff((GuildMessageReceivedEvent) event)) return;
		
			List<String> links = new ArrayList<String>();
			links.addAll(Main.userData.get("links").values());
			
			((GuildMessageReceivedEvent) event).getChannel().sendMessage("```" + links.toString() + "```").queue();
		}
	}

}
