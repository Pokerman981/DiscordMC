package me.pokerman99.bots.discordbots.events;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import me.pokerman99.bots.discordmc.Main;
import me.pokerman99.commands.LinkPermitCommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class LinkBlockerEvent extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (event.getChannelType().equals(ChannelType.TEXT)) {
			if (event.getGuild().getId().equals("258797004757532672")) {
				String[] command = event.getMessage().getContent().split(" ");
				String msg = event.getMessage().getContent().toLowerCase();
				for (int i = 0; i < getlinks().size(); i++) {
					if (msg.contains(getlinks().get(i))) {
						try {
							event.getMessage().delete().submit();
							EmbedBuilder noAllow = new EmbedBuilder().setTitle("Error :x:").setColor(Color.red);
							noAllow.getDescriptionBuilder().append("**Sorry " + event.getAuthor().getAsMention()
									+ "\nwe don't allow this type of link at this time!**");
							event.getChannel().sendMessage(noAllow.build()).submit().get().delete().queueAfter(10,
									TimeUnit.SECONDS);
							return;
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}

					}
				}
			}

		}

	}

	public List<String> getlinks() {
		List<String> links = new ArrayList<>();
		links.add("go.twitch.tv");
		links.add("www.twitch.tv");
		links.add("twitch.tv");
		return links;
	}
}
