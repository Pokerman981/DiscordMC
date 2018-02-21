package me.pokerman99.commands;

import java.awt.Color;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import me.pokerman99.constants.Ref;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class evalCommand implements EventListener {

	@Override
	public void onEvent(Event event) {
		if (event instanceof MessageReceivedEvent) {
			if (((MessageReceivedEvent) event).getAuthor().getId().equals(Ref.owner_id)) {
				String[] msg = ((MessageReceivedEvent) event).getMessage().getContent().split(" ");
				if (msg[0].equals("*eval")) {
					String command = ((MessageReceivedEvent) event).getMessage().getContent().replace("*eval ", "");
					final ScriptEngine se = new ScriptEngineManager().getEngineByName("Nashorn");
					se.put("event", event);
					se.put("jda", event.getJDA());
					se.put("guild", ((MessageReceivedEvent) event).getGuild());
					se.put("channel", ((GenericMessageEvent) event).getChannel());
					try {
						EmbedBuilder succ = new EmbedBuilder();
						succ.setColor(Color.GREEN).getDescriptionBuilder().append("**Input:**\n```" + command
								+ "```\n\n**Evaluated Successfully**:\n```" + se.eval(command) + "```");
						((MessageReceivedEvent) event).getChannel().sendMessage(succ.build()).queue();
					} catch (Exception e) {
						EmbedBuilder exce = new EmbedBuilder();
						exce.setColor(Color.RED).getDescriptionBuilder()
								.append("**Execption Thrown!**\n```" + e + "```");
						((MessageReceivedEvent) event).getChannel().sendMessage(exce.build()).queue();
					}
				}
			}
		}

	}

}



/*else {
				if (!((MessageReceivedEvent) event).getAuthor().getId().equals("282056777003171841")) {
					String user = ((MessageReceivedEvent) event).getAuthor().getAsMention();
					try {
						((MessageReceivedEvent) event).getChannel().sendMessage("Good try " + user + " :wink:").submit()
								.get().delete().queueAfter(3, TimeUnit.SECONDS);
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}*/
