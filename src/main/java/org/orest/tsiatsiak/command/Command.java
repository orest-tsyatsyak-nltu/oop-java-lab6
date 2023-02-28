package org.orest.tsiatsiak.command;

import org.orest.tsiatsiak.UserCommunicationProvider;

import java.util.Map;

public abstract class Command<K, V> {

    public final void run(Map<K, V> storage, UserCommunicationProvider userCommunicationProvider, CommandType commandType) {
        if (commandType.equals(CommandType.NONE)) {
            throw new IllegalArgumentException("Commands are not allowed to use command type 'NONE'");
        }
        if (commandType.equals(CommandType.EXIT)) {
            throw new IllegalArgumentException("Commands are not allowed to use command type 'EXIT'");
        }
        if (!isSuitableFor(commandType)) {
            throw new IllegalArgumentException(
                    getClass().getSimpleName() + " is not suitable for command type " + commandType.name()
            );
        }
        doWork(storage, userCommunicationProvider);
    }

    protected abstract void doWork(Map<K, V> storage, UserCommunicationProvider userCommunicationProvider);

    public abstract boolean isSuitableFor(CommandType commandType);

}
