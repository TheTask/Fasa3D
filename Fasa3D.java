import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.util.ArrayList;

public class Fasa3D extends Application 
{  
   public static void main( String[] args )
   {
      launch( args );
      Platform.setImplicitExit( true );
      Platform.exit();
   }
   
   public void start( Stage primaryStage ) throws Exception
   {   
      Graphics gfx = new Graphics();    
      gfx.createWindow( primaryStage ); 
      
      Space2D plane = new Space2D( gfx );
      
      Mat2 t = new Mat2( 1,0,0,1 );
      plane.applyTransformation( t );
      
      plane.drawSpace();
      
      
      
      //Space2D grid = new Space2D( gfx );
      //grid.drawSpace();
     
      gfx.present();
   }
}