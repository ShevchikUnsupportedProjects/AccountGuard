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

package accountguard.database;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import accountguard.core.AccountGuard;

public class DataBase {

	private AccountGuard plugin;
	public DataBase(AccountGuard plugin) {
		this.plugin = plugin;
	}

	private HashMap<String, AccountData> accounts = new HashMap<String, AccountData>();
	
	
	
	public void loadDataBase() {
		File databasefile = new File(plugin.getDataFolder(), "accounts.db");
		FileConfiguration config = YamlConfiguration.loadConfiguration(databasefile);
		for (String account : config.getKeys(false)) {
			List<String> hosts = config.getStringList(account+".hosts");
			List<String> ips = config.getStringList(account+".ips");
			AccountData adata = new AccountData(hosts, ips);
			accounts.put(account, adata);
		}
	}
	
	public void saveDataBase() {
		File databasefile = new File(plugin.getDataFolder(), "accounts.db");
		FileConfiguration config = new YamlConfiguration();
		for (Entry<String, AccountData> entry : accounts.entrySet()) {
			String account = entry.getKey();
			AccountData adata = entry.getValue();
			config.set(account+".hosts", adata.getForcedHosts());
			config.set(account+".ips", adata.getAllowedIPs());
		}
		try {
			config.save(databasefile);
		} catch (IOException e) {
		}
	}

}
