package org.strassburger.serverlinksz;

import org.bukkit.plugin.java.JavaPlugin;
import org.strassburger.serverlinksz.util.*;
import org.strassburger.serverlinksz.util.bStats.CustomCharts;
import org.strassburger.serverlinksz.util.bStats.Metrics;

public final class ServerLinksZ extends JavaPlugin {
    private static ServerLinksZ instance;

    private CommandManager commandManager;
    private LanguageManager languageManager;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        languageManager = new LanguageManager();

        eventManager = new EventManager(this);
        eventManager.registerListeners();
        commandManager = new CommandManager(this);
        commandManager.registerCommands();

        LinkManager.updateLinks();

        initializeBStats();

        getLogger().info("ServerLinksZ has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ServerLinksZ has been disabled!");
    }

    public static ServerLinksZ getInstance() {
        return instance;
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    private void initializeBStats() {
        final int pluginId = 22795;
        Metrics metrics = new Metrics(this, pluginId);

        metrics.addCustomChart(CustomCharts.getLanguageChart(this));
        metrics.addCustomChart(CustomCharts.getLinksChart(this));
    }
}
