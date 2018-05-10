package CSArmyBot.commands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import CSArmyBot.Main;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class DeletePrivate implements EventListener {

	@Override
	public void onEvent(Event event) {
		try {

			if (event instanceof GuildMessageReceivedEvent) {
				String[] command = ((GuildMessageReceivedEvent) event).getMessage().getContentRaw().split(" ");
				if (command[0].equals("!deletechannel")) {

					User user = ((GuildMessageReceivedEvent) event).getMember().getUser();
					TextChannel channel = ((GuildMessageReceivedEvent) event).getChannel();

					// If they have a private channel
					if (!Main.userData.containsKey(user.getId())) {
						// Delete the bots message
						channel.sendMessage("You do not have a private channel " + user.getAsMention()+ "! Start by creating one with !private").submit().get().delete().queueAfter(3, TimeUnit.SECONDS);
						// Delete the users message so it doesn't look weird
						((GuildMessageReceivedEvent) event).getMessage().delete().queueAfter(3, TimeUnit.SECONDS);
						// Exit the code
						return;
					}
					
					/*if (Main.userData.get(user.getId()).contains(channel.getId())){
						channel.sendMessage("Deleteing channel " + user.getAsMention()+ "!").queue();
						Main.userData.remove(user.getId());
						channel.delete().queueAfter(5, TimeUnit.SECONDS);
					} else {
						// Deleting the bots user
						channel.sendMessage("You do not own this channel " + user.getAsMention() +"! Try this command in <#" + Main.userData.get(user.getId()).get(0) + ">!").submit().get().delete().queueAfter(3, TimeUnit.SECONDS);
						// Deleting the users message
						((GuildMessageReceivedEvent) event).getMessage().delete().queueAfter(3, TimeUnit.SECONDS);
						// Exit the code
						return;
					}*/
					
					

				}
			}

		} catch (InterruptedException | ExecutionException e) {

		}

	}

}
