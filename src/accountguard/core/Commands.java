/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package accountguard.core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import accountguard.database.DataBase;

public class Commands implements CommandExecutor {

	private DataBase database;
	public Commands(DataBase database) {
		this.database = database;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("A player is expected");
			return true;
		}
		String nick = ((Player) sender).getName();
		if (args.length == 3 && args[0].equalsIgnoreCase("host")) {
			if (args[1].equalsIgnoreCase("add")) {
				database.addForcedHost(nick, args[2]);
				sender.sendMessage("Forced host added");
				return true;
			} else if (args[1].equalsIgnoreCase("remove")) {
				database.removeForcedHost(nick, args[2]);
				sender.sendMessage("Forced host removed");
				return true;
			}
		} else if (args.length == 3 && args[0].equalsIgnoreCase("ip")) {
			if (args[1].equalsIgnoreCase("add")) {
				database.addAllowedIP(nick, args[2]);
				sender.sendMessage("Allowed ip added");
				return true;
			} else if (args[1].equalsIgnoreCase("remove")) {
				database.removeAllowedIP(nick, args[2]);
				sender.sendMessage("Allowed ip removed");
				return true;
			}
		}
		return false;
	}

}
