package fnitcrafters.dropmoney

import net.milkbowl.vault.economy.Economy
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.java.JavaPlugin

class DropMoney : JavaPlugin() {

    private var econ: Economy? = null

    override fun onEnable() {
        // Plugin startup logic

        setConfig()

        if (!setupEconomy()) {
            logger.info("Cannot enable Vault")
            server.pluginManager.disablePlugin(this)
        }

        server.pluginManager.registerEvents(FdmListeners(econ, config), this)
    }

    private fun setupEconomy() : Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            return false
        }

        val rsp = server.servicesManager.getRegistration(Economy::class.java)

        this.econ = rsp.provider

        return econ != null
    }

    private fun setConfig() {
        config.addDefault("times", 1)
        config.options().copyDefaults(true)
        saveConfig()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
