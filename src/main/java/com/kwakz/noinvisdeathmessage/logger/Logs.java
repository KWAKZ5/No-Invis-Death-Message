package com.kwakz.noinvisdeathmessage.logger;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Logs {
    private static final Logger logger = Logger.getLogger("Logs");

    public static void setupLogger() {
        try {
            long timestamp = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
            String timestampString = dateFormat.format(new Date(timestamp));
            // Get the parent directory of the current Java file
            String parentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();

            // Set up the "logs" directory path
            Path logsDirectoryPath = Paths.get(parentDirectory, "KillLogs");

            // Check if the "logs" directory exists; if not, create it
            if (!Files.exists(logsDirectoryPath)) {
                Files.createDirectories(logsDirectoryPath);
            }

            // Set up the file handler with the timestamp in the log file name and the custom directory
            String logFilePath = Paths.get(String.valueOf(logsDirectoryPath), timestampString + ".log").toString();
            FileHandler fileHandler = new FileHandler(logFilePath);

            // Create a SimpleFormatter to format the log messages
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);

            // Add the file handler to the logger
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addlogg(String message) {
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd - HH:mm");
        String timestampString = dateFormat.format(new Date(timestamp));
        logger.info(timestampString + " : " + message);
    }
}
