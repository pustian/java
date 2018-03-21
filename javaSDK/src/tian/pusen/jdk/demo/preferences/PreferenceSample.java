/**
 * 
 */
package tian.pusen.jdk.demo.preferences;

import java.util.prefs.Preferences;

/**
 * <p> Title:PreferenceSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月25日 下午5:22:33
 */
public final class PreferenceSample {
	public static void main(String[] args) {
		// getClass();
		Preferences preferences = Preferences.userNodeForPackage(PreferenceSample.class);
	}

}
