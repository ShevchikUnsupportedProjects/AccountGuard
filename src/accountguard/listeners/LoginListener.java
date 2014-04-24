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

package accountguard.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import accountguard.database.AccountData;
import accountguard.database.DataBase;

public class LoginListener implements Listener {

	private DataBase database;
	public LoginListener(DataBase database) {
		this.database = database;
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerLogin(PlayerLoginEvent e) {
		String nick = e.getPlayer().getName().toLowerCase();
		String ip = e.getAddress().getHostAddress();
		String host = e.getHostname().substring(0, e.getHostname().indexOf(":"));
		if (!database.hasAccount(nick)) {
			return;
		}
		AccountData account = database.getAccount(nick);
		if (account.hasAllowedIps() && !account.isValidIP(ip)) {
			e.disallow(Result.KICK_OTHER, "This ip is not allowed for this account");
			return;
		}
		if (account.hasForcedHosts() && !account.isValidHost(host)) {
			e.disallow(Result.KICK_OTHER, "This host is not allowed for this account");
			return;
		}
	}

}
