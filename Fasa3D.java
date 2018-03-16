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
      

      Vec2 v1 = new Vec2( 3,1 );
      Vec2 v2 = new Vec2( 3,3 );
      
      Vec2 v3 = new Vec2( 4,0 );
      Vec2 v4 = new Vec2( -1,3 );
      
      Mat2 m1 = new Mat2( v1,v2 );
      Mat2 m2 = new Mat2( v3,v4 );
      Mat2 m3 = m1.multiply( m2 );
      
      m1.displayMatrix();
      System.out.println( m1.determinant() );
      m2.displayMatrix();
      System.out.println( m2.determinant() );
      m3.displayMatrix();
      System.out.println( m3.determinant() );
      m3.transpose().displayMatrix();
      
      Vec2 a = new Vec2( 100,100 );
      Vec2 b = new Vec2( 590,590 );
      gfx.drawRectangle( a,b,Colors.BLACK );  
      
      gfx.present();
   }
}