/**
 * ImgUtils.java	  V1.0   2014-2-25 下午4:35:16
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImgUtils {
	private static final Log logger = LogFactory.getLog(ImgUtils.class);
	
    private static float alpha = 0.5f;
    // 水印文字字体
    private static Font font = new Font("宋体", Font.BOLD, 80);
    // 水印文字颜色
    private static Color color = Color.white;

	public static void waterMarkerImg(String srcPath,String waterMarkerImgPath,String imgType,String text) {

		InputStream is = null;
		OutputStream os = null;
		Integer degree = new Integer(-45);
		try {
			// 1、源图片
			Image srcImg = ImageIO.read(new File(srcPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

			int positionWidth = buffImg.getWidth() / 8;
			int positionHeight = buffImg.getHeight() / 150;
			// 2、得到画笔对象
			Graphics2D g = buffImg.createGraphics();
			// 3、设置对线段的锯齿状边缘处理
			// g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null),
							srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
					null);
			// 4、设置水印旋转
			if (null != degree) {
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2,
						(double) buffImg.getHeight() / 2);
			}
			// 5、设置水印文字颜色
			g.setColor(color);
			// 6、设置水印文字Font
			g.setFont(font);
			// 7、设置水印文字透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
			g.drawString(text, positionWidth, positionHeight);
			// 9、释放资源
			g.dispose();
			// 10、生成图片
			os = new FileOutputStream(waterMarkerImgPath);
			ImageIO.write(buffImg, "jpg", os);

		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				logger.error(e); e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				logger.error(e); e.printStackTrace();
			}
		}

	}

}
