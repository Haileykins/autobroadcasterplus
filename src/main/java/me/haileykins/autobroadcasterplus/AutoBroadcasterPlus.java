package me.haileykins.autobroadcasterplus;

import me.haileykins.autobroadcasterplus.commands.ABCCommand;
import me.haileykins.autobroadcasterplus.listeners.ActivityListener;
import me.haileykins.autobroadcasterplus.utils.Autobroadcaster;
import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoBroadcasterPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        // Create Instances
        BroadcastMsgUtils bcmUtils = new BroadcastMsgUtils(this);
        ConfigUtils cfgUtils = new ConfigUtils(this);
        Autobroadcaster abc = new Autobroadcaster(this, bcmUtils, cfgUtils);

        // Load Files
        cfgUtils.loadConfig();
        bcmUtils.loadConfig();

        // Register Listeners
        getServer().getPluginManager().registerEvents(new ActivityListener(abc), this);

        // Register Commands
        getCommand("autobroadcast").setExecutor(new ABCCommand(abc, bcmUtils, cfgUtils, this));

        // Start Runnable Task
        abc.broadcast();
    }
}
