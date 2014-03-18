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

import org.bukkit.plugin.java.JavaPlugin;

import accountguard.database.DataBase;
import accountguard.listeners.LoginListener;

public class AccountGuard extends JavaPlugin {

	private DataBase database;

	@Override
	public void onEnable() {
		database = new DataBase(this);
		database.loadDataBase();
		getServer().getPluginManager().registerEvents(new LoginListener(database), this);
		getCommand("accountguard").setExecutor(new Commands(database));
	}

	@Override
	public void onDisable() {
		database.saveDataBase();
		database = null;
	}

}
