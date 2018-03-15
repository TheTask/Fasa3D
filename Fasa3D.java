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
      
      
      Vec2 v1 = new Vec2( 100,100 );
      Vec2 v2 = new Vec2( 50,50 );
      
      gfx.drawRectangle( v1,v2,Colors.RED );  
   }
}