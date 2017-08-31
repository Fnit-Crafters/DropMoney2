package fnitcrafters.dropmoney

import org.bukkit.EntityEffect
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.ExperienceOrb
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntitySpawnEvent

/**
 * Created by taikifnit on 2017/08/31.
 */
class FdmCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {

        if (sender is Player) {
            val location = Location(sender.world, sender.location.x + 1, sender.location.y, sender.location.z )

            val orb = sender.world.spawn(location, ExperienceOrb::class.java)
            orb.customName = "Money$"
            orb.isCustomNameVisible = true
        }


        return true
    }
}