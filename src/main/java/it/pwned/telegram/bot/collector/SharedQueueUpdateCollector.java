package it.pwned.telegram.bot.collector;

import it.pwned.telegram.bot.api.type.Update;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class SharedQueueUpdateCollector implements UpdateCollector {

    private final BlockingQueue<Update> updateQueue;

    public SharedQueueUpdateCollector(BlockingQueue<Update> updateQueue) {
        this.updateQueue = updateQueue;
    }

    public void put(Update u) throws InterruptedException {
        updateQueue.put(u);
    }

    @Override
    public Optional<Update> next(boolean goOn) throws InterruptedException {
        return Optional.ofNullable(updateQueue.poll());
    }
}
