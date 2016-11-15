package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeImgGenerator {

	 public static byte[] writeQRCode(String aAddrInput) {
		    QRCodeWriter writer = new QRCodeWriter();
		    int width = 256, height = 256;
		    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // create an empty image
		    int white = 255 << 16 | 255 << 8 | 255;
		    int black = 0;
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    byte[] ordQrPic = null;
		    
		    try {
		        BitMatrix bitMatrix = writer.encode(aAddrInput, BarcodeFormat.QR_CODE, width, height);
		        for (int i = 0; i < width; i++) {
		            for (int j = 0; j < height; j++) {
		                image.setRGB(i, j, bitMatrix.get(i, j) ? black : white); // set pixel one by one
		            }
		        }

//		        try {
//		            ImageIO.write(image, "jpg", new File("dynamsoftbarcode.jpg")); // save QR image to disk
//		        } catch (IOException e) {
//		            // TODO Auto-generated catch block
//		            e.printStackTrace();
//		        }
		 
		    } catch (WriterException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }

		    /* 把BufferedImage轉換成byte[] */	
			try {
				ImageIO.write(image, "jpg", baos);
				ordQrPic = baos.toByteArray();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return ordQrPic;
		}
}
