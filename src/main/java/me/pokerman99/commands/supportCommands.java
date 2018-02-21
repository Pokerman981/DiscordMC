package me.pokerman99.commands;

import java.awt.Color;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class supportCommands implements EventListener {

	@Override
	public void onEvent(Event event) {
		if (event instanceof GuildMessageReceivedEvent) {
			if (((GuildMessageReceivedEvent) event).getGuild().getId().equals("258797004757532672")) {
				String msg = ((GuildMessageReceivedEvent) event).getMessage().getContent().toLowerCase();
				String[] command = msg.split(" ");
				int cmdsize = command.length;
				//if (!command[0].startsWith("*") | (!command[0].startsWith("."))) return;
				if (command[0].equalsIgnoreCase(".modpack") || command[0].equalsIgnoreCase("*modpack")) {
					((GuildMessageReceivedEvent) event).getChannel().sendMessage("**Main Modpack** - https://www.technicpack.net/modpack/ultimate-reallife-roleplay.616349").queue();
					return;
				}
				
				if (command[0].equalsIgnoreCase("*crashlog") || command[0].equalsIgnoreCase(".crashlog")) {
					switch (cmdsize) {
					case 1: {
						try {((GuildMessageReceivedEvent) event).getChannel().sendMessage(notEnoughArgs().build()).submit().get().delete().queueAfter(10, TimeUnit.SECONDS);
							 ((GuildMessageReceivedEvent) event).getMessage().delete().queue();
						} catch (InterruptedException | ExecutionException e) {e.printStackTrace();}
						break;
					}
					case 2: {
						switch (command[1]) {
						case "win": {
							((GuildMessageReceivedEvent) event).getChannel().sendMessage(crashLogWin().build()).queue();
							break;
						}
						case "mac": {
							((GuildMessageReceivedEvent) event).getChannel().sendMessage(crashLogMac().build()).queue();
							break;
						}
						default: {
							break;
						}
						}
						break;
					}
					}
					if (cmdsize > 2) {
						((GuildMessageReceivedEvent) event).getChannel().sendMessage("unsupported args!").queue();
						return;
					}
					return;
				}
				if (command[0].equalsIgnoreCase("*ram") || command[0].equalsIgnoreCase(".ram")){
					((GuildMessageReceivedEvent) event).getChannel().sendMessage(ram().build()).queue();
					((GuildMessageReceivedEvent) event).getChannel().sendMessage(ram2().build()).queue();
				}
			} else {
				return;
			}
		}
	}
	
	private EmbedBuilder ram() {
		EmbedBuilder ram = new EmbedBuilder();
		ram.setColor(Color.green).setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/1200px-Java_programming_language_logo.svg.png").getDescriptionBuilder()
				.append( "**1.** Open the Technic launcher\n" 
						+ "**2.** Click 'Launcher Options' in the top right corner\n"
						+ "**3.** Click the 'Java Settings' tab\n"
						+ "**4.** In the 'Memory' dropdown menu, select 3 GB");
		return ram;
	}
	
	private EmbedBuilder ram2() {
		EmbedBuilder ram2 = new EmbedBuilder();
		ram2.setColor(Color.green).setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/1200px-Java_programming_language_logo.svg.png").getDescriptionBuilder()
				.append("If you cannot allocate more than **1 GB**, it's because you are running **32 bit java**. To be able to allocate more RAM you will need to install **64 bit java**.\n"
						+ "**1.** Go to http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html\n"
						+ "**2.** Click the second to last link called 'Windows x64 Offline'\n"
						+ "**3.** Install it and restart your PC");
		return ram2;
	}

	private EmbedBuilder notEnoughArgs() {
		EmbedBuilder notEnoughArgs = new EmbedBuilder();
		notEnoughArgs.setTitle(":x: **Not enough arguments!**").setColor(Color.red).getDescriptionBuilder()
				.append("**Proper usage:**\n\t*crashlog win").append("\n\t*crashlog mac");
		return notEnoughArgs;

	}

	private EmbedBuilder crashLogWin() {
		EmbedBuilder notEnoughArgs = new EmbedBuilder();
		notEnoughArgs.setTitle("**Windows crashlog**")
				.setThumbnail("https://gyazo.com/ead1b9f53aa354c03047e8ad64f2914c.png").setColor(Color.blue)
				.getDescriptionBuilder()
				.append("**1.** Hit the windows key + R\n" + "**2.** Type %appdata% in the run box\n"
						+ "**3.** Double-click .technic\n" + "**4.** Double click modpacks\n"
						+ "**5.** Double-click ultimate-reallife-roleplay\n"
						+ "**6.** Double-click crash-reports. Your crash report should be in the folder. They are labeled by date and time (crash-year-month-day_hour.minute.second.txt)  so choose the latest one to post. You should open it with Notepad or notepad++ (if you have it) and then copy and paste the contents\n"
						+ "**7.** Once you've got it, you can paste it here; http://pastebin.com/ and it'll give you a link which you can put in the discord (that makes reading it easier for us and also allows you to paste large amounts of info which you can't do here).");
		return notEnoughArgs;

	}

	private EmbedBuilder crashLogMac() {
		EmbedBuilder notEnoughArgs = new EmbedBuilder();
		notEnoughArgs.setTitle("**Mac crashlog**")
				.setThumbnail(
						"https://images-ext-1.discordapp.net/external/Zk8Qlq0xKIruN9BHsH9grABirbGKsyj42uVR8Utl-gU/https/gyazo.com/1d8ebb67d49be797d471525dc0b6dc9a.png?width=80&height=80")
				.setColor(Color.decode("#111111")).getDescriptionBuilder()
				.append("**1.** Open finder\n" + "**2.** Click go at the top left of the screen\n"
						+ "**3.**Select 'Go to Folder...' to open the search bar\n"
						+ "**4.** Type ~/Library/Application Support in the box and click go\n"
						+ "**5.** Double Click the technic folder\n" + "**6.** Double-click modpacks\n"
						+ "**7.** Double-click ultimate-reallife-roleplay\n"
						+ "**8.** Double-click crash-reports. Your crash report should be in the folder. They are labeled by date and time (crash-year-month-day_hour.minute.second.txt)  so choose the latest one to post. You should be able to open it with textedit.\n"
						+ "**9.** Once you've got it, you can paste it here: http://pastebin.com/ and it'll give you a link which you can put in the discord (that makes reading it easier for us and also allows you to paste large amounts of info which you can't do here).");

		return notEnoughArgs;

	}
	

}
