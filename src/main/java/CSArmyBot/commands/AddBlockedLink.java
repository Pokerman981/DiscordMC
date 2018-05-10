package CSArmyBot.commands;

import java.io.IOException;
import java.lang.reflect.Array;

import CSArmyBot.Main;
import CSArmyBot.Ref;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class AddBlockedLink implements EventListener{

	@Override
	public void onEvent(Event event) {
		if (event instanceof MessageReceivedEvent) {
			if (!((MessageReceivedEvent) event).getGuild().getId().equals(Ref.csarmyguild)) return;
			
			String[] msg = ((MessageReceivedEvent) event).getMessage().getContentRaw().split(" ");
			if (!msg[0].equals("!blocklink")) return;
			
			Member member = ((MessageReceivedEvent) event).getMember();
			if (!member.hasPermission(Permission.MESSAGE_MANAGE)) return;
			
			if (msg.length != 2) return;
			
			Array.set(msg, 0, "");
			
			int size = Main.userData.get("links").size();
			
			Main.userData.get("links").put(String.valueOf(size + 1), msg[1]);
			
			try {
				Main.Save();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			TextChannel channel = ((MessageReceivedEvent) event).getTextChannel();
			User user = ((MessageReceivedEvent) event).getAuthor();
			
			channel.sendMessage("Successfully added `" + msg[1] + "` to the blocked links, " + user.getAsMention() + "!").queue();
			
			
			
		}
		
	}
	
	

}
