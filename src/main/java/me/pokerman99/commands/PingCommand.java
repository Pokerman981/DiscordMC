package me.pokerman99.commands;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class PingCommand extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] command = event.getMessage().getContent().split(" ");
		//String message = event.getMessage().getContent();
		if (!command[0].startsWith("*")) {
			return;
		}
		if (command[0].equalsIgnoreCase("*ping")) {
			String msg = "Pong `" + event.getJDA().getPing() + "`";
			if (command.length == 1) {
				event.getChannel().sendMessage(msg).queue();
			} else if (command.length == 2 && command[1].equalsIgnoreCase("-e")){
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("Ping");
				eb.setColor(Color.CYAN);
				eb.setDescription(msg);
				event.getChannel().sendMessage(eb.build()).queue();
			}
		}
	}

}
