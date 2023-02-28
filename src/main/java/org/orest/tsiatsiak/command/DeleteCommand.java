package org.orest.tsiatsiak.command;

import org.orest.tsiatsiak.UserCommunicationProvider;

import java.util.Map;

public class DeleteCommand extends Command<String, String> {

    @Override
    protected void doWork(Map<String, String> storage, UserCommunicationProvider userCommunicationProvider) {
        userCommunicationProvider.print("DELETE COMMAND\n");
        userCommunicationProvider.print("Stored info:\n");
        userCommunicationProvider.print("Country - currency:\n");
        storage.forEach((s, s2) -> userCommunicationProvider.print(s + " - " + s2 + "\n"));
        userCommunicationProvider.print("Enter name of the country you want to delete info about: ");
        String enteredName = userCommunicationProvider.readLine();
        if (storage.containsKey(enteredName)) {
            storage.remove(enteredName);
        } else {
            userCommunicationProvider.print("Entered country %s is not stored%n".formatted(enteredName));
        }
    }

    @Override
    public boolean isSuitableFor(CommandType commandType) {
        return commandType.equals(CommandType.DELETE);
    }

}
