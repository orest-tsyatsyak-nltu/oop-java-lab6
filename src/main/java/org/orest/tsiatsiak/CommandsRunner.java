package org.orest.tsiatsiak;

import org.orest.tsiatsiak.command.Command;
import org.orest.tsiatsiak.command.CommandType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CommandsRunner<K, V> {

    private final List<Command<K, V>> availableCommands;

    private final HashMap<K, V> storage;

    private final UserCommunicationProvider userCommunicationProvider;

    public CommandsRunner(UserCommunicationProvider userCommunicationProvider, List<? extends Command<K, V>> commands) {
        availableCommands = new LinkedList<>(commands);
        this.userCommunicationProvider = userCommunicationProvider;
        storage = new HashMap<>();
    }

    public void startExecution() {
        CommandType enteredCommand;
        while (true) {
            printAvailableCommands();
            enteredCommand = readCommand();
            if (enteredCommand.equals(CommandType.EXIT)) {
                break;
            }
            executeCommand(enteredCommand);
        }
    }

    private void printAvailableCommands() {
        userCommunicationProvider.print("Available commands: \n");
        Arrays.stream(CommandType.values())
                .filter(commandType -> !commandType.equals(CommandType.NONE))
                .forEach(commandType -> userCommunicationProvider.print(commandType.name() + "\n"));
    }

    private CommandType readCommand() {
        userCommunicationProvider.print("\nEnter command here: ");
        String enteredString = userCommunicationProvider.readLine();
        userCommunicationProvider.print("\n");
        return tryGettingCommandTypeFromString(enteredString);
    }

    private CommandType tryGettingCommandTypeFromString(String enteredString) {
        try {
            return CommandType.valueOf(enteredString.toUpperCase());
        } catch (IllegalArgumentException e) {
            userCommunicationProvider.print("Entered command '%s' does not exist%n%n".formatted(enteredString));
        }
        return CommandType.NONE;
    }

    private void executeCommand(CommandType enteredCommand) {
        if (enteredCommand.equals(CommandType.NONE)) {
            return;
        }
        for (var command : availableCommands) {
            if (command.isSuitableFor(enteredCommand)) {
                command.run(storage, userCommunicationProvider, enteredCommand);
            }
        }
    }

}
