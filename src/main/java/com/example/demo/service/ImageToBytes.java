package com.example.demo.service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ImageToBytes {
    public static byte[] image2Bytes(String imagePath) {
        ImageIcon ima = new ImageIcon(imagePath);
        BufferedImage bu = new BufferedImage(ima.getImage().getWidth(null), ima
                .getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
        try {
            //把这个jpg图像写到这个流中去,这里可以转变图片的编码格式
            boolean resultWrite = ImageIO.write(bu, "png", imageStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] tagInfo = imageStream.toByteArray();

        return tagInfo;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = image2Bytes("D:\\Users\\jtang7\\Desktop\\QQ图片20210816115633.jpg");
        for (byte b : bytes)
            System.out.print(b);
    }
}
