
import com.sun.media.jai.codec.SeekableStream;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.media.jai.JAI;
import javax.media.jai.OpImage;
import javax.media.jai.RenderedOp;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a.abbas
 */
public class ImageCompressor {
//    /**
//     * The JAI.create action name for handling a stream.
//     */
//    private static final String JAI_STREAM_ACTION = "stream";
//
//    /**
//     * The JAI.create action name for handling a resizing using a subsample averaging technique.
//     */
//    private static final String JAI_SUBSAMPLE_AVERAGE_ACTION = "SubsampleAverage";
//
//    /**
//     * The JAI.create encoding format name for JPEG.
//     */
//    private static final String JAI_ENCODE_FORMAT_JPEG = "JPEG";
//
//    /**
//     * The JAI.create action name for encoding image data.
//     */
//    private static final String JAI_ENCODE_ACTION = "encode";
//
//    /**
//     * The http content type/mime-type for JPEG images.
//     */
//    private static final String JPEG_CONTENT_TYPE = "image/jpeg";
//
//
//    private int mMaxWidth = 1920;
//
//    private int mMaxWidthThumbnail = 150;
    public void compress(String filePath) throws IOException {

 //       File infile = new File("D:\\images.jpg");
 //       File outfile = new File("D:\\images_after_compress.jpg");

//        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
//                infile));
//        BufferedOutputStream bos = new BufferedOutputStream(
//                new FileOutputStream(outfile));
//        SeekableStream s = SeekableStream.wrapInputStream(bis, true);
//
//        RenderedOp image = JAI.create("stream", s);
//        ((OpImage) image.getRendering()).setTileCache(null);
//
//        RenderingHints qualityHints = new RenderingHints(
//                RenderingHints.KEY_RENDERING,
//                RenderingHints.VALUE_RENDER_QUALITY);
//
//        RenderedOp resizedImage = JAI.create("SubsampleAverage", image,0.2,
//                0.2, qualityHints);
//
//        JAI.create("encode", resizedImage, bos, "JPEG", null);


     File input = new File(filePath);
      BufferedImage image = ImageIO.read(input);

      File compressedImageFile = new File(filePath);
      OutputStream os =new FileOutputStream(compressedImageFile);

      Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
      ImageWriter writer = (ImageWriter) writers.next();

      ImageOutputStream ios = ImageIO.createImageOutputStream(os);
      writer.setOutput(ios);

      ImageWriteParam param = writer.getDefaultWriteParam();

      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      param.setCompressionQuality(0.2f);
      writer.write(null, new IIOImage(image, null, null), param);



//        SeekableStream seekableImageStream = SeekableStream.wrapInputStream(bis, true);
//    RenderedOp originalImage = JAI.create(JAI_STREAM_ACTION, seekableImageStream);
//    ((OpImage) originalImage.getRendering()).setTileCache(null);
//    int origImageWidth = originalImage.getWidth();
//    // now resize the image
//    double scale = 1.0;
//    if (mMaxWidth > 0 && origImageWidth > mMaxWidth) {
//        scale = (double) mMaxWidth / originalImage.getWidth();
//    }
//    ParameterBlock paramBlock = new ParameterBlock();
//    paramBlock.addSource(originalImage); // The source image
//    paramBlock.add(scale); // The xScale
//    paramBlock.add(scale); // The yScale
//    paramBlock.add(0.1); // The x translation
//    paramBlock.add(0.1); // The y translation
//
//    RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_RENDERING,
//        RenderingHints.VALUE_RENDER_QUALITY);
//
//    RenderedOp resizedImage = JAI.create(JAI_SUBSAMPLE_AVERAGE_ACTION, paramBlock, qualityHints);
//
//    // lastly, write the newly-resized image to an output stream, in a specific encoding
//
//    JAI.create(JAI_ENCODE_ACTION, resizedImage, bos, JAI_ENCODE_FORMAT_JPEG, null);
    os.flush();
    os.close();

    ios.flush();
    ios.close();

    writer.dispose();

    }
}
