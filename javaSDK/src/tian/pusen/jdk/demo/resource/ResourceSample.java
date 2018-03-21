/**
 * 
 */
package javaABC.demo.resource;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;

/**
 * <p> Title:ResourceSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月8日 下午2:00:57
 */
// 未测试
public final class ResourceSample {
	public static void main(String[] args) throws IOException {
		ResourceSample resourceSample = new ResourceSample();
		Properties properties = resourceSample.getDefaults();
		Image image = resourceSample.getIcon();
		System.out.println(properties);
		System.out.println(image);
	}
	private Properties getDefaults() throws IOException {
        Properties defaults = new Properties();
        try (InputStream defaultsStream =
            ClassLoader.getSystemResourceAsStream("db.properties")) {
            defaults.load(defaultsStream);
        }
        return defaults;
    }
	
    private Image getIcon() throws IOException {
        URL imageURL = ClassLoader.getSystemResource("icon.png");
        return ImageIO.read(imageURL);
    }
}
