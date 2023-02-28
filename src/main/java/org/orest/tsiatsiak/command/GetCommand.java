package org.orest.tsiatsiak.command;

import org.orest.tsiatsiak.UserCommunicationProvider;

import java.util.Map;

public class GetCommand extends Command<String, String> {

    @Override
    protected void doWork(Map<String, String> storage, UserCommunicationProvider userCommunicationProvider) {
        userCommunicationProvider.print("GET COMMAND\n");
        userCommunicationProvider.print("Enter country name: ");
        String enteredName = userCommunicationProvider.readLine();
        if (storage.containsKey(enteredName)) {
            userCommunicationProvider.print(
                    "The currency of the %s is %s%n".formatted(enteredName, storage.get(enteredName))
            );
        } else {
            userCommunicationProvider.print("Entered country %s not found%n".formatted(enteredName));
        }
    }

    @Override
    public boolean isSuitableFor(CommandType commandType) {
        return commandType.equals(CommandType.GET);
    }

}
