package com.logentries.logger;

import android.content.Context;

import java.io.IOException;

public class Logentries {

    private static Logentries instance;

    private AsyncLoggingWorker loggingWorker;

    private Logentries(Context context, boolean useHttpPost, boolean useSsl, boolean isUsingDataHub, String dataHubAddr, int dataHubPort,
                       String token, boolean logHostName) throws IOException {
        loggingWorker = new AsyncLoggingWorker(context, useSsl, useHttpPost, isUsingDataHub, token, dataHubAddr, dataHubPort, logHostName);
    }

    public static synchronized Logentries init(Context context, String token)
            throws IOException {
        if (instance != null) {
            instance.loggingWorker.close();
        }

        instance = new Logentries(context, false, false, false, null, 0, token, false);
        return instance;
    }

    public static synchronized Logentries getInstance() {
        if (instance != null) {
            return instance;
        } else {
            throw new IllegalArgumentException("Logger instance is not initialized. Call init() first!");
        }
    }

    public void log(String message) {
        loggingWorker.addLineToQueue(message);
    }

}
