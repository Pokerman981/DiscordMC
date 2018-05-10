package CSArmyBot.commands;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.google.gson.reflect.TypeToken;

import CSArmyBot.Config;
import CSArmyBot.Main;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class testCommand implements EventListener {

	@Override
	public void onEvent(Event event) {
		if (event instanceof GuildMessageReceivedEvent) {
			if (((GuildMessageReceivedEvent) event).getMessage().getContentRaw().equals("!test")) {
				((GuildMessageReceivedEvent) event).getChannel().sendMessage(Main.userData.toString()).queue();

				TextChannel donatorchat = ((GuildMessageReceivedEvent) event).getGuild().getTextChannelsByName("donator-chat", true).get(0);
				
				if (((GuildMessageReceivedEvent) event).getMember().getPermissions(donatorchat).contains(Permission.VIEW_CHANNEL) == false){
					((GuildMessageReceivedEvent) event).getChannel().sendMessage("Only donators may create a private channel, " + ((GuildMessageReceivedEvent) event).getAuthor().getAsMention() + "!").queue();
				}
			}
				
				
			}

		}
	}
