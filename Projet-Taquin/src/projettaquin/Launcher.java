



package projettaquin;

import java.util.logging.Level;
import java.util.logging.Logger;
import taquinconsole.TaquinConsole;

public class Launcher {
    public static void main(String[] args){
        if(args.length!=0){
            try {
                new TaquinConsole().main(args);
            } catch (Exception ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            new ProjetTaquin().main(args);
        }
        
    }
}
