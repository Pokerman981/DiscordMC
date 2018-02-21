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

public class kickCommand implements EventListener {

	@Override
	public void onEvent(Event event) {
		if (event instanceof MessageReceivedEvent) {
			if (((MessageReceivedEvent) event).isFromType(ChannelType.TEXT)) {
				if (((MessageReceivedEvent) event).getGuild().getId().equals(Ref.pixplus_id)) {
					String[] message = ((MessageReceivedEvent) event).getMessage().getRawContent().split(" ");
					if ((message[0].equalsIgnoreCase("/k")) || message[0].equalsIgnoreCase(".k")) {
						if (((MessageReceivedEvent) event).getGuild().getMemberById(((MessageReceivedEvent) event).getAuthor().getId()).getRoles().toString().contains("Staff")) {
							int size = message.length;
							EmbedBuilder ban = new EmbedBuilder();
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
											((MessageReceivedEvent) event).getChannel().sendMessage(kickUser(user2, reason).setFooter("Kicked by " + 
											((MessageReceivedEvent) event).getAuthor().getName(),
											((MessageReceivedEvent) event).getAuthor().getAvatarUrl()).build()).queue();

									user2.openPrivateChannel().queue((channel) -> channel.sendMessage(userKickBessage(user2.getId(),
											((MessageReceivedEvent) event).getGuild().getId(), reason).setFooter("Kicked by " + ((MessageReceivedEvent) event).getAuthor().getName(),
											((MessageReceivedEvent) event).getAuthor().getAvatarUrl()).build()).complete());
											((MessageReceivedEvent) event).getGuild().getController().kick(user2.getId(),reason + " - kicked by "
											+ ((MessageReceivedEvent) event).getAuthor().getName() + " ("
											+ ((MessageReceivedEvent) event).getAuthor().getId() + ")").queue();

								} else {
									((MessageReceivedEvent) event).getChannel()
											.sendMessage("You cannot kick another staff member!").queue();
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

	public EmbedBuilder userKickBessage(String kickid, String serverid, String reason) {
		EmbedBuilder userKickMessage = new EmbedBuilder();
		userKickMessage.setTitle("You've been kicked!").setColor(Color.red).getDescriptionBuilder()
				.append("You've been kicked from the Pixelmon+ discord!\nReason: `" + reason + "`\n\nYour ID: `" + kickid
						+ "`\nServer ID: `" + serverid + "`");
		return userKickMessage;

	}

	public EmbedBuilder kickUser(User kickid, String reason) {
		EmbedBuilder userKickMessage = new EmbedBuilder();
		userKickMessage.setTitle("User Kicked!").setColor(Color.red).getDescriptionBuilder().append(
				"Kicked user: " + kickid.getAsMention() + " (" + kickid.getId() + ")\nReason:" + reason + "");
		return userKickMessage;

	}

}
