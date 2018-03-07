import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.application.Platform;

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
      
      
      gfx.putPixel( 1,1,Colors.BLACK );      
   }
}