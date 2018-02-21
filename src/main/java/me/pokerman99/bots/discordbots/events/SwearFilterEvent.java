package me.pokerman99.bots.discordbots.events;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SwearFilterEvent extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] command = event.getMessage().getContent().split(" ");
		String msg = event.getMessage().getContent().toLowerCase();
		if (event.isFromType(ChannelType.TEXT)) {
			if (event.getGuild().getId().equals("258797004757532672")) {
				if (event.getChannel().getId().equals("401096720244670464")) return;
				if (event.getChannel().getId().equals("401097790153031690")) return;
				if (event.getChannel().getId().equals("401097902354595840")) return;
				if (event.getChannel().getId().equals("401098015248482314")) return;
				if (event.isFromType(ChannelType.TEXT)) {

					if (event.getMember().getRoles().toString().contains("Staff") == false) {
						for (int i = 0; i < getSwears().size(); i++) {
							if (msg.contains(getSwears().get(i))) {
								try {
									event.getChannel()
											.sendMessage(event.getAuthor().getAsMention()
													+ " ***__im sorry sir but this is a ChRiStiAn SerVer so Noooo sweeaarriiinnggg__***")
											.submit().get().delete().submitAfter(3, TimeUnit.SECONDS);
									return;
								} catch (InterruptedException | ExecutionException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}

	public List<String> getSwears() {
		List<String> swears = new ArrayList<>();
		swears.add("fuck");
		swears.add("shit");
		swears.add("cunt");
		swears.add("anal");
		swears.add("bitch");
		swears.add("cock");
		swears.add("dick");
		swears.add("douche");
		swears.add("fag");
		swears.add("nigger");
		swears.add("nigglet");
		swears.add("nignog");
		swears.add("puta");
		swears.add("queer");
		swears.add("whore");
		swears.add("slut");
		return swears;

	}
}