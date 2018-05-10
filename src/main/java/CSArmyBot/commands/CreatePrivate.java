package CSArmyBot.commands;

import java.io.IOException;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import CSArmyBot.Main;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class CreatePrivate implements EventListener {
	
	@Override
	public void onEvent(Event event) {
		try {

			if (event instanceof GuildMessageReceivedEvent) {
				String[] message = ((GuildMessageReceivedEvent) event).getMessage().getContentRaw().split(" ");
				if (message[0].equals("!private")) {

					Category priv = ((GuildMessageReceivedEvent) event).getGuild().getCategoriesByName("private", true).get(0);
					User user = ((GuildMessageReceivedEvent) event).getMember().getUser();
					TextChannel channel = ((GuildMessageReceivedEvent) event).getChannel();
					
					//TextChannel donatorchat = ((GuildMessageReceivedEvent) event).getGuild().getTextChannelsByName("donator-chat", true).get(0);
					
//					if (((GuildMessageReceivedEvent) event).getMember().getPermissions(donatorchat).contains(Permission.VIEW_CHANNEL) == false){
//				((GuildMessageReceivedEvent) event).getChannel().sendMessage("Only donators may create a private channel, " + ((GuildMessageReceivedEvent) event).getAuthor().getAsMention() + "!").queue();
	//					return;
	//				}
					
					
					
					if (Main.userData.containsKey(user.getId())) {

						channel.sendMessage("You already have a channel, <@" + user.getId() + "> it is located at <#"
								+ Main.userData.get(user.getId()).get(0) + ">").submit().get().delete()
								.queueAfter(5, TimeUnit.SECONDS);
						((GuildMessageReceivedEvent) event).getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
						return;
					}

					try {
						((GuildMessageReceivedEvent) event).getGuild().getController()
								.createTextChannel("private-" + user.getId()).setParent(priv).submit().get().getId();
					} catch (InterruptedException | ExecutionException e) {
					}

					TextChannel channel2 = ((GuildMessageReceivedEvent) event).getGuild()
							.getTextChannelsByName("private-" + user.getId(), true).get(0);

					Map<String, String> temp = new HashMap<String, String>();
					temp.put(user.getId(), channel2.getId());

					Main.userData.put("private", temp);
					
					Main.Save();

					channel.sendMessage("Successfully created a new channel, " + user.getAsMention() + ". " + channel2.getAsMention()).queue();
					
					channel2.createPermissionOverride(((GuildMessageReceivedEvent) event).getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
					channel2.sendMessage("This is your private channel, <@" + user.getId()+ ">. You can add other players to this channel by typing !add @username").queue();

				}

			}
		} catch (InterruptedException | ExecutionException | IOException e) {

		}

	}

}
