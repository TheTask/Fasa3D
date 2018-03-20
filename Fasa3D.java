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
      

      Vec2 v1 = new Vec2( 100,100 );
      Vec2 v2 = new Vec2( 150,200 );
      Vec2 v3 = new Vec2( 700,400 );
      Vec2 v4 = new Vec2( 0,400 );
      Vec2 v5 = new Vec2( 200,800 );
      
      ArrayList< Vec2 > points = new ArrayList< Vec2 >();
      points.add( v1 );
      points.add( v2 );
      points.add( v3 );
      points.add( v4 );
      points.add( v5 );
   
      gfx.drawShape( points,Colors.BLACK );  
      
      Vec2 a = new Vec2( -100,100 );
      Vec2 b = new Vec2( 500,700 );
      Vec2 d = new Vec2( -100,200 );
      Vec2 c = new Vec2( 800,200 );
      gfx.drawLine( a,b,Colors.RED );
      gfx.drawLine( c,d,Colors.GREEN );
      
      gfx.present();
   }
}