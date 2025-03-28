package com.lovehai.javaShow;

import javafx.scene.image.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class CopyImageToClipboard {
    private void copyImage(Image image) {
        // 复制图片
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putImage(image);
        clipboard.setContent(content);
    }
    private Image convertToARGB(Image src) {
        if (isRGBImage(src)) {
            return src;
        }
        log.info("转换为ARGB格式");
        int width = (int) src.getWidth();
        int height = (int) src.getHeight();
        WritableImage argbImage = new WritableImage(width, height);
        PixelReader reader = src.getPixelReader();
        PixelWriter writer = argbImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = reader.getArgb(x, y);
                writer.setArgb(x, y, argb);
            }
        }
        return argbImage;
    }

    private static boolean isRGBImage(Image image) {
        PixelReader reader = image.getPixelReader();
        if (reader == null) {
            return false;
        }
        PixelFormat<?> format = reader.getPixelFormat();
        return format.getType() == PixelFormat.Type.BYTE_RGB
                || format.getType() == PixelFormat.Type.INT_ARGB_PRE
                || format.getType() == PixelFormat.Type.INT_ARGB;
    }
}