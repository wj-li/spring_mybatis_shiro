package com.demo.util;

import java.awt.Color;
import java.awt.Font;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * 验证码图片生成 CaptchaTool.java
 *
 * @author liwenjian
 * @date 2017年7月19日 上午11:52:05
 * @version 1.0.0
 */
public class CaptchaTool extends ListImageCaptchaEngine {

	/** 图片宽度 */
	private static final int IMAGE_WIDTH = 110;

	/** 图片高度 */
	private static final int IMAGE_HEIGHT = 30;

	/** 最小字体大小 */
	private static final int MIN_FONT_SIZE = 15;

	/** 最大字体大小 */
	private static final int MAX_FONT_SIZE = 15;

	/** 最小字符个数 */
	private static final int MIN_WORD_LENGTH = 4;

	/** 最大字符个数 */
	private static final int MAX_WORD_LENGTH = 4;

	/** 随机字符 */
	private static final String CHAR_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	/**
	 * 随机字体
	 */
	private static final Font[] FONTS = new Font[] { new Font("nyala", Font.BOLD, MAX_FONT_SIZE),
			new Font("Arial", Font.BOLD, MAX_FONT_SIZE), new Font("nyala", Font.BOLD, MAX_FONT_SIZE),
			new Font("Bell", Font.BOLD, MAX_FONT_SIZE), new Font("Bell MT", Font.BOLD, MAX_FONT_SIZE),
			new Font("Credit", Font.BOLD, MAX_FONT_SIZE), new Font("valley", Font.BOLD, MAX_FONT_SIZE),
			new Font("Impact", Font.BOLD, MAX_FONT_SIZE) };

	/**
	 * 随机颜色
	 */
	private static final Color[] COLORS = new Color[] { new Color(255, 255, 255), new Color(255, 220, 220),
			new Color(220, 255, 255), new Color(220, 220, 255), new Color(255, 255, 220), new Color(220, 255, 220) };

	/**
	 * 背景随机颜色
	 */
	private static final Color[] BGCOLORS = new Color[] { new Color(128, 138, 135), new Color(128, 128, 105),
			new Color(220, 220, 220), new Color(156, 102, 31), new Color(178, 34, 34), new Color(176, 48, 96),
			new Color(255, 69, 0), new Color(135, 38, 87), new Color(255, 0, 255), new Color(0, 0, 255),
			new Color(61, 89, 171), new Color(25, 25, 112), new Color(94, 38, 18), new Color(128, 42, 42),
			new Color(138, 54, 15), new Color(56, 94, 15), new Color(8, 46, 84), new Color(48, 128, 20),
			new Color(34, 139, 34), new Color(0, 0, 0), new Color(235, 142, 85), new Color(11, 23, 70) };

	/**
	 * 验证码图片生成
	 */
	@Override
	protected void buildInitialFactories() {
		FontGenerator fontGenerator = new RandomFontGenerator(MIN_FONT_SIZE, MAX_FONT_SIZE, FONTS);
		BackgroundGenerator backgroundGenerator = new GradientBackgroundGenerator(IMAGE_WIDTH, IMAGE_HEIGHT,
				new RandomListColorGenerator(BGCOLORS), new RandomListColorGenerator(BGCOLORS));
		TextPaster textPaster = new DecoratedRandomTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH,
				new RandomListColorGenerator(COLORS), new TextDecorator[] {});
		addFactory(new GimpyFactory(new RandomWordGenerator(CHAR_STRING),
				new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster)));
	}

}