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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AccountData {

	public AccountData() {
	}

	public AccountData(List<String> hosts, List<String> ips) {
		forcedhosts.addAll(hosts);
		allowedips.addAll(ips);
	}

	private HashSet<String> forcedhosts = new HashSet<String>();
	private HashSet<String> allowedips = new HashSet<String>();

	public boolean hasForcedHosts() {
		return !forcedhosts.isEmpty();
	}

	public boolean isValidHost(String host) {
		return forcedhosts.contains(host.toLowerCase());
	}

	public boolean hasAllowedIps() {
		return !allowedips.isEmpty();
	}

	public boolean isValidIP(String IP) {
		return allowedips.contains(IP.toLowerCase());
	}


	protected List<String> getForcedHosts() {
		return new ArrayList<String>(forcedhosts);
	}

	protected List<String> getAllowedIPs() {
		return new ArrayList<String>(allowedips);
	}

	protected void addForcedHost(String host) {
		forcedhosts.add(host.toLowerCase());
	}

	protected void addAllowedIP(String IP) {
		allowedips.add(IP.toLowerCase());
	}

	protected void removeForcedHost(String host) {
		forcedhosts.remove(host.toLowerCase());
	}

	protected void removeAllowedIP(String ip) {
		allowedips.remove(ip.toLowerCase());
	}

}
