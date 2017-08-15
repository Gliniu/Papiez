package ovh.not.javamusicbot.command;

import ovh.not.javamusicbot.Command;
import ovh.not.javamusicbot.Constants;
import ovh.not.javamusicbot.CommandManager;
import ovh.not.javamusicbot.MusicBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HelpCommand extends Command {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "commands", "h", "music");
        this.commandManager = commandManager;
    }

    @Override
    public void on(Context context) {
        StringBuilder builder = new StringBuilder("**Commands:**");
        List<String> added = new ArrayList<>(commandManager.commands.size());
        Map<String, String> commandDescriptions = MusicBot.getConfigs().constants.commandDescriptions;
        for (Command command : commandManager.commands.values()) {
            if (added.contains(command.names[0]) || command.hide) {
                continue;
            }
            added.add(command.names[0]);
            builder.append("\n`").append(command.names[0]).append('`');
            if (commandDescriptions.containsKey(command.names[0])) {
                builder.append(" ").append(commandDescriptions.get(command.names[0]));
            }
        }
        builder.append("\n\n**Quick start:** Use `%prefix%play <link>` to start playing a song, use the same command to ")
                .append("add another song, `%prefix%skip` to go to the next song and `%prefix%stop` to stop playing and leave.");
        context.reply(builder.toString());
    }
}
