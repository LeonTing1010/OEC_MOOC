package com.gta.oec.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gta.oec.vo.common.CaptchaVO;

public abstract class ImageUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(ImageUtils.class); 
	/**
	 * 图片的后缀
	 */
	public static final String[] IMAGE_EXT = new String[] { "jpg", "jpeg",
			"gif", "png", "bmp" };

	/**
	 * 是否是图片
	 * 
	 * @param ext
	 * @return "jpg", "jpeg", "gif", "png", "bmp" 为文件后缀名者为图片
	 */
	public static boolean isValidImageExt(String ext) {
		ext = ext.toLowerCase(Locale.ENGLISH);
		for (String s : IMAGE_EXT) {
			if (s.equalsIgnoreCase(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得水印位置
	 * 
	 * @param width
	 *            原图宽度
	 * @param height
	 *            原图高度
	 * @param p
	 *            水印位置 1-5，其他值为随机。1：左上；2：右上；3：左下；4：右下；5：中央。
	 * @param offsetx
	 *            水平偏移。
	 * @param offsety
	 *            垂直偏移。
	 * @return 水印位置
	 */
	public static Position markPosition(int width, int height, int p,
			int offsetx, int offsety) {
		if (p < 1 || p > 5) {
			p = (int) (Math.random() * 5) + 1;
		}
		int x, y;
		switch (p) {
		// 左上
		case 1:
			x = offsetx;
			y = offsety;
			break;
		// 右上
		case 2:
			x = width + offsetx;
			y = offsety;
			break;
		// 左下
		case 3:
			x = offsetx;
			y = height + offsety;
			break;
		// 右下
		case 4:
			x = width + offsetx;
			y = height + offsety;
			break;
		// 中央
		case 5:
			x = (width / 2) + offsetx;
			y = (height / 2) + offsety;
			break;
		default:
			throw new RuntimeException("never reach ...");
		}
		return new Position(x, y);
	}

	/**
	 * 水印位置
	 * 
	 * 包含左边偏移量，右边偏移量。
	 * 
	 * @author chenjie
	 * 
	 */
	public static class Position {
		private int x;
		private int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
    /**
     * 
     * 功能描述：生成验证码
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-2 下午2:22:26</p>
     *
     * @param isReadom  是否随机产生验证码
     * @return
     * @throws IOException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
	public static  CaptchaVO creatImage(boolean isReadom)
			throws IOException {
	    //获取同步锁
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		Lock lock = readWriteLock.writeLock();
		lock.lock();
		// 在内存中创建图象
		int width = 76, height = 26;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		
		// 获取图形上下文
		Graphics2D g = image.createGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定图像背景色(因为是做背景，所以偏淡)
		g.setColor(getRandColor(180, 250));
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Times New Roman", Font.PLAIN, 26);
		// 设置字体。
		g.setFont(font);

		// 画边框。
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
		g.setColor(Color.GRAY);
		for (int i = 0; i < 50; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();

		// 设置默认生成4个验证码
		int length = 4;
		// 设置备选验证码:包括"a-z"和数字"0-9"
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";

		// 取随机产生的认证码(4位数字)

		int size = base.length();

		if (isReadom) {
			// 随机产生4位数字的验证码。
			for (int i = 0; i < length; i++) {
				// 得到随机产生的验证码数字。
				int start = random.nextInt(size);
				String strRand = base.substring(start, start + 1);
				randomCode.append(strRand);
				// 用随机产生的颜色将验证码绘制到图像中。
				// g.setColor(new Color(red,green,blue));
				// 生成随机颜色(因为是做前景，所以偏深)
				g.setColor(getRandColor(1, 100));
				g.drawString(strRand, 16 * i + 5, 19);
				// 将产生的四个随机数组合在一起。				
			}
		} else {
			randomCode.append("1234");
			// 将认证码显示到图象中
			g.setColor(getRandColor(1, 100));
			g.drawString("1", 17 * 0 + 6, 25);
			g.setColor(getRandColor(1, 100));
			g.drawString("2", 17 * 1 + 6, 25);
			g.setColor(getRandColor(1, 100));
			g.drawString("3", 17 * 2 + 6, 25);
			g.setColor(getRandColor(1, 100));
			g.drawString("4", 17 * 3 + 6, 25);
		}

		// 图象生效
		g.dispose();

		CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaValue(randomCode.toString());
        captchaVO.setImage(image);
        try {
			captchaVO.setCreateTime(DateUtils.getCurrentDate(null));
		} catch (ParseException e) {
			logger.error(e.getMessage());e.printStackTrace();
		}
        lock.unlock();
		return captchaVO;

	}

	private static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
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

}
