package org.orest.tsiatsiak;

import org.orest.tsiatsiak.command.Command;
import org.orest.tsiatsiak.command.DeleteCommand;
import org.orest.tsiatsiak.command.GetCommand;
import org.orest.tsiatsiak.command.SaveCommand;

import java.util.List;

public class Lab6ConsoleApplication {

    public static void main(String[] args) {
        UserCommunicationProvider userCommunicationProvider = new UserCommunicationProvider(System.in, System.out);
        List<Command<String, String>> commands = List.of(
                new SaveCommand(),
                new GetCommand(),
                new DeleteCommand()
        );
        new CommandsRunner<>(userCommunicationProvider, commands).startExecution();
    }

}
