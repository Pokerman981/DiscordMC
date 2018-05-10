package CSArmyBot.commands;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import CSArmyBot.Main;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class AddPrivate implements EventListener {

	@Override
	public void onEvent(Event event) {
		try {
			if (event instanceof GuildMessageReceivedEvent) {
				String[] message = ((GuildMessageReceivedEvent) event).getMessage().getContentRaw().split(" ");
				if (message[0].equals("!add")) {

					// Variables to make things easier
					User user = ((GuildMessageReceivedEvent) event).getMember().getUser();
					TextChannel channel = ((GuildMessageReceivedEvent) event).getChannel();

					// If they have a private channel
					if (!Main.userData.containsKey(user.getId()) || Main.userData.get(user.getId()).isEmpty()) {
							// Delete the bots message
							channel.sendMessage("You do not have a private channel <@" + user.getId() + ">! Start by creating one with !private").submit().get().delete().queueAfter(5, TimeUnit.SECONDS);
							// Delete the users message so it doesn't look weird
							((GuildMessageReceivedEvent) event).getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
							// Exit the code
							return;
					}

					// If they supply to many args or not enough
					if (message.length != 2) {
							// Deleting the bots user
							channel.sendMessage("You must supply a user in the second arguement! Or you have to many arguments!").submit().get().delete().queueAfter(5, TimeUnit.SECONDS);
							// Deleting the users message
							((GuildMessageReceivedEvent) event).getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
							// Exit the code
							return;
					}
					
					/*if (!Main.userData.get(user.getId()).contains(channel.getId())){
						// Deleting the bots user
						channel.sendMessage("You do not own this channel! Try this command in <#" + Main.userData.get(user.getId()).get(0) + ">!").submit().get().delete().queueAfter(5, TimeUnit.SECONDS);
						// Deleting the users message
						((GuildMessageReceivedEvent) event).getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
						// Exit the code
						return;
					}*/

					try {
						//Checking if the user exists
						((GuildMessageReceivedEvent) event).getGuild().getMemberById(message[1].replaceAll("<@", "").replaceAll(">", ""));
						
					} catch (NumberFormatException e) {
							//Delete the bots message
							channel.sendMessage("The user you supplied is invalid!").submit().get().delete().queueAfter(5,TimeUnit.SECONDS);
							//Delete the users message
							((GuildMessageReceivedEvent) event).getMessage().delete().queueAfter(5, TimeUnit.SECONDS);
							//Exit the code
							return;
					}

					//dependant variables
					Member user2 = ((GuildMessageReceivedEvent) event).getGuild().getMemberById(message[1].replaceAll("<@", "").replaceAll(">", ""));
					TextChannel channel2 = ((GuildMessageReceivedEvent) event).getGuild().getTextChannelById(Main.userData.get(user.getId()).get(0));
					//Chaning channel permissions & sending allow message
					channel2.putPermissionOverride(user2).setAllow(Permission.VIEW_CHANNEL).queue();
					channel.sendMessage("Successfully added user " + user2.getAsMention() + " to " + channel2.getAsMention()).queue();

				}
			}
		} catch (InterruptedException | ExecutionException e) {
			
		}
	}
	

}
