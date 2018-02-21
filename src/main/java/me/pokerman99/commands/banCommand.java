package me.pokerman99.commands;

import java.awt.Color;
import java.lang.reflect.Array;

import me.pokerman99.constants.Ref;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class banCommand implements EventListener {

	@Override
	public void onEvent(Event event) {
		if (event instanceof MessageReceivedEvent) {
			if (((MessageReceivedEvent) event).isFromType(ChannelType.TEXT)) {
				if (((MessageReceivedEvent) event).getGuild().getId().equals(Ref.pixplus_id)) {
					String[] message = ((MessageReceivedEvent) event).getMessage().getRawContent().split(" ");
					if ((message[0].equalsIgnoreCase("/b")) || message[0].equalsIgnoreCase(".b")) {
						if (((MessageReceivedEvent) event).getGuild().getMemberById(((MessageReceivedEvent) event).getAuthor().getId()).getRoles().toString().contains("Moderation")) {
							int size = message.length;
							if (size == 1) {
								((MessageReceivedEvent) event).getChannel().sendMessage("You must supply a user!")
										.queue();
								return;
							}
							if (size > 2) {
								String user = message[1].replace("<", "").replace("@", "").replace(">", "").replace("!", "");
								User user2 = ((MessageReceivedEvent) event).getJDA().getUserById(user);
								Array.set(message, 0, "");
								Array.set(message, 1, "");
								String reason = String.join(" ", message);
								if (!user2.getJDA().getGuildById(Ref.pixplus_id).getMemberById(user2.getId()).getRoles().toString().contains("Staff")) {
									((MessageReceivedEvent) event).getChannel().sendMessage(banUser(user2, reason).setFooter("Banned by " + ((MessageReceivedEvent) event).getAuthor().getName(), ((MessageReceivedEvent) event).getAuthor().getAvatarUrl()).build()).queue();
									user2.openPrivateChannel().queue((channel) -> channel.sendMessage(userBanBessage(user2.getId(),((MessageReceivedEvent) event).getGuild().getId(), reason).setFooter("Banned by " + ((MessageReceivedEvent) event).getAuthor().getName(),((MessageReceivedEvent) event).getAuthor().getAvatarUrl()).build()).queue());((MessageReceivedEvent) event).getGuild().getController().ban(user2, 7,reason + " - banned by "+ ((MessageReceivedEvent) event).getAuthor().getName() + " ("+ ((MessageReceivedEvent) event).getAuthor().getId() + ")").complete();
								} else {
									((MessageReceivedEvent) event).getChannel()
											.sendMessage("You cannot ban another staff member!").queue();
								}
							} else {
								((MessageReceivedEvent) event).getChannel()
										.sendMessage("You must provide a reason!").queue();
							}
						} else {
							((MessageReceivedEvent) event).getChannel()
									.sendMessage("You do not have permission to use this command!").queue();
						}
					} else {
						return;
					}
				}
			}
		}

	}

	public EmbedBuilder userBanBessage(String banid, String serverid, String reason) {
		EmbedBuilder userBanMessage = new EmbedBuilder();
		userBanMessage.setTitle("You've been banned!").setColor(Color.red).getDescriptionBuilder()
				.append("You've been banned from the Pixelmon+ discord!\nReason: `" + reason + "`\n\nYour ID: `" + banid
						+ "`\nServer ID: `" + serverid + "`");
		return userBanMessage;

	}

	public EmbedBuilder banUser(User banid, String reason) {
		EmbedBuilder userBanMessage = new EmbedBuilder();
		userBanMessage.setTitle("User Banned!").setColor(Color.red).getDescriptionBuilder().append(
				"Banned user: " + banid.getAsMention() + " (" + banid.getId() + ")\nReason:" + reason + "");
		return userBanMessage;

	}

}
