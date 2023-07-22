package com.kwakz.noinvisdeathmessage;

import com.kwakz.noinvisdeathmessage.logger.Logs;
import net.fabricmc.api.ModInitializer;

public class main implements ModInitializer {
    @Override
    public void onInitialize() {
        Logs.setupLogger();
    }
}