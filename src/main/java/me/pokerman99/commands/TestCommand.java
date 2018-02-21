package me.pokerman99.commands;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import ch.jamiete.mcping.MinecraftPing;
import ch.jamiete.mcping.MinecraftPingOptions;
import ch.jamiete.mcping.MinecraftPingReply;
import me.pokerman99.constants.Ref;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class TestCommand extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
			String[] command = event.getMessage().getContent().split(" ");
			if (!command[0].startsWith("*"))
				return;

			if (command.length > 1)
				return;

			if (command[0].equals("*test")) {
				if (event.getGuild().getId().equals(Ref.pixplus_id)) {
					((MessageReceivedEvent) event).getGuild().getMember(((MessageReceivedEvent) event).getAuthor()).getGuild().getController()
					.addSingleRoleToMember(((MessageReceivedEvent) event).getGuild().getMemberById(((MessageReceivedEvent) event).getAuthor().getId()), ((MessageReceivedEvent) event).getGuild().getRoleById("401183019932581888")).submitAfter(4, TimeUnit.SECONDS);
					((MessageReceivedEvent) event).getGuild().getMember(((MessageReceivedEvent) event).getAuthor()).getGuild().getController()
					.addSingleRoleToMember(((MessageReceivedEvent) event).getGuild().getMemberById(((MessageReceivedEvent) event).getAuthor().getId()), ((MessageReceivedEvent) event).getGuild().getRoleById("401183246106361856")).submitAfter(3, TimeUnit.SECONDS);
					((MessageReceivedEvent) event).getGuild().getMember(((MessageReceivedEvent) event).getAuthor()).getGuild().getController()
					.addSingleRoleToMember(((MessageReceivedEvent) event).getGuild().getMemberById(((MessageReceivedEvent) event).getAuthor().getId()), ((MessageReceivedEvent) event).getGuild().getRoleById("401183132327608333")).submitAfter(2, TimeUnit.SECONDS);
					((MessageReceivedEvent) event).getGuild().getMember(((MessageReceivedEvent) event).getAuthor()).getGuild().getController()
					.addSingleRoleToMember(((MessageReceivedEvent) event).getGuild().getMemberById(((MessageReceivedEvent) event).getAuthor().getId()), ((MessageReceivedEvent) event).getGuild().getRoleById("401183075918151682")).submitAfter(1, TimeUnit.SECONDS);
					
					
					
				}

			}
	}
}