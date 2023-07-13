package Model;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystems;
import java.util.Objects;

import static org.apache.tomcat.util.http.fileupload.FileUtils.deleteDirectory;

//Gestore delle immagini con relativi metodi
public class ImageManager {

	private static String fileSeparator = FileSystems.getDefault().getSeparator();
    private final int targetDim = 225; //Dimensione target del rescaling delle foto

    public ImageManager() {}

    public void renameFolder(String path, String url, String oldMarca, String oldModello, String marca, String modello) throws IOException {
        path = path.replace("file:", "");
        File oldDir = new File(path+url);
        File newDir = new File(path+"Images"+fileSeparator+marca+modello);

        if(!oldDir.renameTo(newDir))
            throw new IOException("Cannot rename "+oldDir.getPath()+" to this name"+newDir.getPath()+". Please select a different one");
    }

    //Concatena alla path da / fino alla root di tomcat "Images/" che è la cartella dove vogliamo salvare l'immagine
    //e rimuove la scritta "file:" che si trova all'inizio per risolvere il problema che
    //viene creato dal metodo getResource("/") del contesto, che restituisce la path
    //ma preceduta da "file:"
    public String getSavePath(String s){
        String path = (s + "Images"+fileSeparator);
        path = path.replace("file:", "");
        return path;
    }

    //Calcola quanto devi rimuovere all'imagine per farla risultare di 225 px
    private int calcScalingFactor(int x, int y){
        int workDim = Math.max(x, y);
        int scale = workDim/targetDim;
        return scale;
    }

    //Redimensiona l'immagine
    private BufferedImage resize(BufferedImage img) {
        //Vecchie width e height
        int w = img.getWidth();
        int h = img.getHeight();
        //Quanto va levato alle immagnini
        int scale = calcScalingFactor(w, h);
        //Calcola nuove dimensioni
        int newW = img.getWidth() / scale;
        int newH = img.getHeight() / scale;
        //Crea una nuova BufferedImage con le nuove dimensioni
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        //Magick
        Graphics2D g = dimg.createGraphics();
        //Altra roba noiosa per l'interpolazione e come scalarla
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        //Disegna la nuova immagnine
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        //(altrimenti rimane in ram)
        g.dispose();
        //Ritorna l'immagine bufferizzata scalata
        return dimg;
    }

    //Scrive l'immagine rappresentata come Part nella name/imagePath con nome
    // 1 per l'immagine a riaoluzione standard
    // 2 per l'immagnine a risoluzione minore
    private void writeImage(Part filePart, String imagePath, String name) throws IOException {
        //Crea la directory
        new File(imagePath+name).mkdirs();
        //Salva il file con dimensione maggiore
        filePart.write(imagePath+name+fileSeparator+"1.jpg");
        //Crea l'immagine con dimensione minore
        BufferedImage min = resize(ImageIO.read(new File(imagePath+name+fileSeparator+"1.jpg")));
        //Scrivi l'immagine
        ImageIO.write(min, "jpg", new File(imagePath+name+fileSeparator+"2.jpg"));
    }
    //Restituisce la path relativa dalla root di tomcat
    //ES: Images/nomeFile.jpg
    public String saveImage(String rootPath, Part filePart, String saveName) throws IOException {
        //Prendi la path completa fino a Images
        String imagePath = getSavePath(rootPath);
        writeImage(filePart, imagePath, saveName);
        return "Images"+fileSeparator+saveName;
    }

    public static boolean isImage(Part p) throws IOException {
        p.write(fileSeparator+"tmp"+fileSeparator+"imgTmp.jpg");
        BufferedImage bi = ImageIO.read(new File(fileSeparator+"tmp"+fileSeparator+"imgTmp.jpg"));
        try{
            int width = bi.getWidth();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void deleteDirectory(File file) throws IOException {
        if(file.listFiles() != null){
            File[] list=file.listFiles();
            if(list.length>=1) {
                for (File subfile : file.listFiles()) {
                    deleteDirectory(subfile);
            }
        }
    }else{
            if(!file.delete()){
                throw new IOException("Cannot delete this file "+file.getPath());
            }
        }
    }

    public void deleteImageDir(String url, String root) throws IOException {
        root = root.replace("file:", "");
        File file = new File(root+url);
        deleteDirectory(file);
        file.delete();
    }
}
