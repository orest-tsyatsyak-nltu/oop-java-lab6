package org.orest.tsiatsiak.command;


import org.orest.tsiatsiak.UserCommunicationProvider;

import java.util.Map;

public class SaveCommand extends Command<String, String> {

    @Override
    protected void doWork(Map<String, String> storage, UserCommunicationProvider userCommunicationProvider) {
        userCommunicationProvider.print("SAVE COMMAND\n");
        userCommunicationProvider.print("Enter country name: ");
        String countryName = userCommunicationProvider.readLine();
        userCommunicationProvider.print("Enter the currency of the %s: ".formatted(countryName));
        String capitalName = userCommunicationProvider.readLine();
        storage.put(countryName, capitalName);
    }

    @Override
    public boolean isSuitableFor(CommandType commandType) {
        return commandType.equals(CommandType.SAVE);
    }

}
