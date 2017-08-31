package fnitcrafters.dropmoney

import net.milkbowl.vault.economy.Economy
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.java.JavaPlugin

class DropMoney : JavaPlugin() {

    private var econ: Economy? = null

    override fun onEnable() {
        // Plugin startup logic
        if (!setupEconomy()) {
            logger.info("Cannot enable Vault")
            server.pluginManager.disablePlugin(this)
        }

        server.pluginManager.registerEvents(FdmListeners(econ), this)
    }

    private fun setupEconomy() : Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            return false
        }

        val rsp = server.servicesManager.getRegistration(Economy::class.java)

        this.econ = rsp.provider

        return econ != null
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
