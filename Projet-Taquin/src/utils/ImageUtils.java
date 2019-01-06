package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Classe helper s'occupant du split d'images.
 * Cela permet de construire une grille de tailel variable avec l'image de ntore choix.
 */
public class ImageUtils {
    
    
   /**
    * Méthode qui prend une image en entrée ainsi qu'un nombre, et crée des sous images dans le dossier media/images/tmp 
    * @param path Chemin de l'image
    * @param nb Nombre de carrés que l'on souhaite
    */
    public static void split(String path,int nb){
        try{
            int compte = 1;
            BufferedImage source = ImageIO.read(new File(path));
           int x = 0,y = 0;
           int dx = (source.getWidth()/nb);
           int dy = (source.getHeight()/nb);
          // System.out.println("dx="+dx+ "et dy ="+dy);
           
           
           if(dx*nb==source.getWidth()){
              while( y < source.getHeight()){
               while( x < source.getWidth()){
                  ImageIO.write(source.getSubimage(x,y,dx,dy),"png",new File("media/images/tmp/"+compte+".png"));
                  x+=dx;
                        compte++;
                    }
                    x = 0;
                    y += dy;
                }

            } else {
                ImageIO.write(source.getSubimage(0, 0, dx * nb, dy * nb), "png", new File("media/images/tmp/image_temporaire" + compte + ".png"));
                BufferedImage source_temporaire = ImageIO.read(new File("media/images/tmp/image_temporaire" + compte + ".png"));

                while (y < source_temporaire.getHeight()) {
                    while (x < source_temporaire.getWidth()) {
                        ImageIO.write(source_temporaire.getSubimage(x,y,dx,dy), "png", new File("media/images/tmp/" + compte + ".png"));
                        x += dx;
                        compte++;
                    }
                    x = 0;
                    y += dy;

                }
            }
        } catch (Exception e) {
        }
    }
    
   
}
