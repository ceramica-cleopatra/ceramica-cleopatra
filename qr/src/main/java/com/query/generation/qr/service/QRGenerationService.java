package com.query.generation.qr.service;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.query.generation.qr.model.CrmkQRItems;
import com.query.generation.qr.model.DcreQRItems;
import com.query.generation.qr.repository.CrmkItemsRepository;
import com.query.generation.qr.repository.DcreItemsRepository;
import com.query.generation.qr.repository.SehyItemsRepository;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

@Service
public class QRGenerationService {
	
	@Autowired
	private CrmkItemsRepository crmkItemsRepository;
	
	@Autowired
	private DcreItemsRepository dcreItemsRepository;
	
	@Autowired
	private SehyItemsRepository sehyItemsRepository;
	
	private String dir = "/opt/web/query/QR/";
	//private String dir = "E:\\E\\qr\\";

	public static void generateQRcode(String data, String path , String imageName, String productName, String price, String charset, Map map, int h, int w, int imageHeight,
			int imageWidth,int productFontSize,int priceFontSize,int productFarFromTop,int priceFarFromBottom)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, w, h,map);
		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
		if(productName != null){
			ImagePlus image = IJ.openImage(path);
			Font productFont = new Font("Arial", Font.BOLD, productFontSize);
			Font priceFont = new Font("Arial", Font.BOLD, priceFontSize);
			ImageProcessor ip = image.getProcessor();
			ip.setColor(Color.BLACK);
			ip.setFont(productFont);
			FontMetrics metrics = ip.getFontMetrics();
			int positionX = (image.getWidth() - metrics.stringWidth(productName)) / 2;
			//int positionY = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
			ip.drawString(productName, positionX , productFarFromTop);
			ip.setFont(priceFont);
			int pricePositionX = (image.getWidth() - metrics.stringWidth("Price : " + price)) / 2;
			if(price != null && !price.equals("null")){
				ip.drawString("Price : " + price, pricePositionX-10 , image.getHeight() - priceFarFromBottom);
			}
			BufferedImage bufferedImage = Scalr.resize(ip.getBufferedImage(), imageWidth ,imageHeight);
			//bufferedImage = getQRCodeWithOverlay(bufferedImage);
			ImageIO.write(bufferedImage, "png", new File(path));
		}
	}
	
	
	public static BufferedImage getQRCodeWithOverlay(BufferedImage qrcode) 
	{
	    BufferedImage scaledOverlay = scaleOverlay(qrcode);

	    Integer deltaHeight = qrcode.getHeight() - scaledOverlay.getHeight();
	    Integer deltaWidth  = qrcode.getWidth()  - scaledOverlay.getWidth();

	    BufferedImage combined = new BufferedImage(qrcode.getWidth(), qrcode.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = (Graphics2D)combined.getGraphics();
	    g2.drawImage(qrcode, 0, 0, null);
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.72f));
	    g2.drawImage(scaledOverlay, Math.round(deltaWidth/2), Math.round(deltaHeight/2), null);
	    return combined;
	}

	private static BufferedImage scaleOverlay(BufferedImage qrcode)
	{
	    Integer scaledWidth = Math.round(qrcode.getWidth() * 0.2f);
	    Integer scaledHeight = Math.round(qrcode.getHeight() * 0.2f);

	    BufferedImage imageBuff = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics g = imageBuff.createGraphics();
	    BufferedImage overlay=null;
		try {
			overlay = ImageIO.read(QRGenerationService.class.getResourceAsStream("/images/ccg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    g.drawImage(overlay.getScaledInstance(scaledWidth, scaledHeight, BufferedImage.SCALE_SMOOTH), 0, 0, new Color(0,0,0), null);
	    g.dispose();
	    return imageBuff;
	}
	
	
	
	public String generateQRForProducts(Long typeId,Long sizeId,Long dekalaId,Long factoryNo,Long colorId,Long tablow,Long nameId,String crmkDcre,String showType,Long grpId,Long floor,
			Long brnId, String standNo, Integer h,Integer w,Integer imageHeight,Integer imageWidth,Integer productFontSize,Integer priceFontSize,
			Integer productFarFromTop,Integer priceFarFromBottom,Long how2show,Long sehyGrpId){
		String filePath = dir + UUID.randomUUID() + "/";
	//	String filePath = dir + UUID.randomUUID() + "\\";
		try {
			Path path = Paths.get(filePath);
			Files.createDirectory(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(crmkDcre.equalsIgnoreCase("C")){
			long start=System.currentTimeMillis();
			List<CrmkQRItems> itemList = crmkItemsRepository.getCrmkItems(typeId, sizeId, dekalaId, factoryNo,showType,grpId,floor,brnId,standNo,how2show);
			System.out.println(System.currentTimeMillis()-start);
			Set<CrmkQRItems> itemSet = new HashSet<>();
			itemSet.addAll(itemList);
			itemSet.stream().filter(i -> i.getEnglishName() != null).collect(Collectors.toList()).forEach(item->{
				Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
				hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); 
				String fullFilePath = filePath +  item.getEnglishName().replace("*","x").replaceAll("[\\*\\\\]", "-").replaceAll("[\\*/]", "-") + ".png";
				try {
					generateQRcode(item.getQrString(), fullFilePath , item.getNoCTone(), item.getEnglishName(), item.getPrice() == null ? "" : item.getPrice().toString(), "UTF-8", hashMap, h, w,imageHeight,
							imageWidth,productFontSize,priceFontSize,productFarFromTop,priceFarFromBottom);
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}else if(crmkDcre.equalsIgnoreCase("D")){
			List<DcreQRItems> itemList = dcreItemsRepository.getDcreItems(typeId, sizeId, dekalaId, factoryNo,colorId,tablow,showType,grpId,floor,brnId,standNo,how2show);
			Set<DcreQRItems> itemSet = new HashSet<>();
			itemSet.addAll(itemList);
			itemSet.stream().filter(i -> i.getEnglishName() != null).collect(Collectors.toList()).forEach(item->{
				Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
				hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); 
				String fullFillPath = filePath +  item.getEnglishName().replace("*","x").replaceAll("[\\*\\\\]", "-").replaceAll("[\\*/]", "-") + ".png";
				try {
					generateQRcode(item.getQrString(), fullFillPath , item.getNoCTone(),item.getEnglishName() ,item.getPrice()==null ? "" : item.getPrice().toString(), "UTF-8", hashMap, h, w,
							imageHeight,imageWidth,productFontSize,priceFontSize,productFarFromTop,priceFarFromBottom);
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}else if(crmkDcre.equalsIgnoreCase("S")){
			sehyItemsRepository.getSehyItems(typeId, nameId, dekalaId, colorId,sehyGrpId).forEach(item->{
				Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
				hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); 
				String fullFillPath = filePath +  item.getCode().replace("*","x").replaceAll("[\\*\\\\]", "-").replaceAll("[\\*/]", "-") + ".png";
				try {
					generateQRcode(item.getQrString(), fullFillPath , item.getCode() ,item.getSehyName(),item.getPrice(), "UTF-8", hashMap, h, w,imageHeight,imageWidth,productFontSize,priceFontSize
							,productFarFromTop,priceFarFromBottom);
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath+".zip");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(filePath);
        try {
			zipFile(fileToZip, fileToZip.getName(), zipOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			zipOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return filePath;
	}
	
	private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

}
