package com.ncode.invoice.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.ncode.invoice.entity.TblMstGstParam;
import com.ncode.invoice.pki.util.Base64Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Component
public class B2CPdfGeneration {

	public Image getQrCodeForB2C(Double amount, String invoicenmber, TblMstGstParam gstParams, String un2) throws WriterException, BadElementException {

		Image qrCode = null;
		try {
			String pa = gstParams.getPa();
			String am = amount.toString();
			String mc = gstParams.getMc();
			String uniqueNumber = un2;

			String qrstr = "upi://pay?pa=" + pa + "&tr=MNO" + invoicenmber + "&am=" + am + "&cu=INR&mc=" + mc + "&tn="
					+ uniqueNumber;

			String charset = "UTF-8";
			Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
			hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			String qrcoderes = createQR(qrstr, charset, hashMap, 350, 350);
			System.out.println("QR CODE ICICI : " + qrcoderes);

			String qrCodeText = qrcoderes;
//			String networkPath = AppConstants.B2CQRCODEPATH;
			String filename = null;
			for (int i = 0; i <= 1000; i++) {
				for (int j = 0; j < 9; j++) {
					filename = "ICICIQRCODE" + i + j;
				}
			}
//			String filePath = networkPath + filename + ".png";
			int size = 125;
			String fileType = "png";
//			File qrFile = new File(filePath);
//			qrCode = createQRImage(qrFile, qrstr, size, fileType);
			qrCode = createQRImage(qrstr, size, fileType);

			System.out.println("DONE");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return qrCode;
	}

	public static String createQR(String data, String charset, Map hashMap, int height, int width)
			throws WriterException, IOException {

		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, "PNG", pngOutputStream);
		byte[] pngData = pngOutputStream.toByteArray();
		String qrcoderes = Base64Service.encode(pngData);
		return qrcoderes;
	}

//	private static Image createQRImage(File qrFile, String qrCodeText, int size, String fileType)
//			throws WriterException, IOException, BadElementException {
	private static Image createQRImage(String qrCodeText, int size, String fileType)
			throws WriterException, IOException, BadElementException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		ImageIO.write(image, fileType, qrFile);

		ImageIO.write(image, fileType, byteArrayOutputStream);
		byte[] imageBytes = byteArrayOutputStream.toByteArray();
		return Image.getInstance(imageBytes);
	}

}
