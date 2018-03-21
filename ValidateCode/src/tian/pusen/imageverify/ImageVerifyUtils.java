package tian.pusen.imageverify;
/**
 * Created by tianpusen on 2017/4/11.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 图片验证码工具类
 */
public class ImageVerifyUtils {

    /**
     *  根据图片的长、宽以及验证码的字符个数生成图片验证码对象
     *  @param width
     *  @param height
     *  @param randNo
     *  @return
     * */
    public static ImageVerifyItem creatImageVerify(int width, int height, int randNo) {
        // 图片验证码对象
        ImageVerifyItem item = new ImageVerifyItem();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics graphics = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        graphics.setColor(getRandColor(200, 250));
        // 填充指定的矩形
        graphics.fillRect(0, 0, width, height);
        // 设定字体
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        // 画边框
        // graphics.setColor(new Color());
        // graphics.drawRect(0, 0, width-1, height-1);
        // 随机产生155条干扰线，使图像中的认证码不易被其他程序探测到
        graphics.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, xl, yl);
        }
        // 取随机产生的验证码
        String sRand = "";
        for (int i = 0; i < randNo; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将验证码显示到图像中
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            graphics.drawString(rand, 13*i +6, 16);
        }
        // 释放此图形的上下文以及它使用的所有系统资源。
        graphics.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpeg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        item.image = baos.toByteArray();
        item.randNum = sRand;
        return item;
    }

    /**
     * 根据给定范围获得随机颜色
     *
     * */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    // 测试类
    public static void main(String... args) {
        // 四位数字的验证码
        ImageVerifyItem item = creatImageVerify(60, 25, 4);
        String fileName = item.randNum + ".jpg";
        String path = System.getProperty("user.dir");
        try {
            FileOutputStream out = new FileOutputStream(path + "/" + fileName);
            out.write(item.image);
            if (out != null)
                out.close();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
