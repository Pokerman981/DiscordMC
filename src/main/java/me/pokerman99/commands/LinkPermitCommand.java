package me.pokerman99.commands;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class LinkPermitCommand implements EventListener {

	public static List<String> permited = new ArrayList<>();

	@Override
	public void onEvent(Event event) {
		if (event instanceof GuildMessageReceivedEvent) {
			if (((GuildMessageReceivedEvent) event).getGuild().getId().equals("258797004757532672")) {
				String[] command = ((GuildMessageReceivedEvent) event).getMessage().getContent().split(" ");
				String msg = ((GuildMessageReceivedEvent) event).getMessage().getContent();
				int size = command.length;
				String t = Integer.toString(size);
				if (((GuildMessageReceivedEvent) event).getMember().getRoles().toString().contains("Staff")) {
					if (!command[0].startsWith("*"))
						return;
					if (size == 1)
						return;
					if (command[0].equalsIgnoreCase("*permit")) {
						String end = command[1].replace("@", "");
						permited.add(end);
						((GuildMessageReceivedEvent) event).getChannel().sendMessage(permited.toString()).submit();
					}
				}
			}
		}
	}

}
