package de.mcmdev.displayformatter.common.handler;

import de.mcmdev.displayformatter.common.platform.Platform;

public class ConnectionHandler<P> {

    private final Platform<P> platform;

    public ConnectionHandler(Platform<P> platform) {
        this.platform = platform;
    }

    public void handleJoin(P player)    {

    }

}
