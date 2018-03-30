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

      Vec2 v1 = new Vec2( 550,50 );
      Vec2 v2 = new Vec2( 100,500 );
      Vec2 v3 = new Vec2( 500,500 );
      
      
      gfx.drawFlatBottomTriangle( v1,v2,v3,Colors.BLACK );
     
      gfx.present();
   }
}