@file:Suppress("DEPRECATION")

package fnitcrafters.dropmoney

import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDeathEvent


/**
 * Created by taikifnit on 2017/08/31.
 */
class FdmListeners(econ: Economy?) : Listener {

    var econ: Economy? = null

    init {
        this.econ = econ
    }

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent?) {

        val econ = econ ?: return
        val event = event ?: return
        val entity = event.entity ?: return

        val killer = entity.killer

        val earnedMoney = event.droppedExp
        val r = econ.depositPlayer(killer.name, earnedMoney.toDouble())

        if(r.transactionSuccess()) {
            killer.sendMessage("You've earned " + econ.format(earnedMoney.toDouble()) + " from " + entity.name)
        }
    }
}
