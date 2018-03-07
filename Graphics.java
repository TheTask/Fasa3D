import javafx.scene.image.PixelWriter;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//Window related imports
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.Platform;


public class Graphics
{
   private int SCREEN_WIDTH = 800;
   private int SCREEN_HEIGHT = 600;
   
   
   private Canvas          canvas; //pixel buffer
   private GraphicsContext gc;    
   private WritableImage   img; //layer
   private PixelWriter     pw;

   
   public Graphics()
   {
      canvas = new Canvas( SCREEN_WIDTH,SCREEN_HEIGHT );
      gc = canvas.getGraphicsContext2D();
      img = new WritableImage( SCREEN_WIDTH,SCREEN_HEIGHT ); //layer
      pw  = img.getPixelWriter();
   }
   
   public void createWindow( Stage primaryStage )
   {
      Stage window = primaryStage;
      
      window.setOnCloseRequest( new EventHandler< WindowEvent>()
      {
      public void handle( WindowEvent e )
         {
            Platform.exit();
            System.exit( 0 );
            Platform.setImplicitExit( true );
         }
      });
      
      window.setTitle( "Fasa3D" );

      GridPane grid = new GridPane();
      grid.getChildren().addAll( canvas ); //put pixel buffer into layout
      

      Scene scene = new Scene( grid,SCREEN_WIDTH,SCREEN_HEIGHT );
      window.setScene( scene );
      window.show(); //present pixel buffer
   }
   
   
   public void putPixel( int x,int y,Color c )
   {
      pw.setColor( x,y,c );     //accessing pixel by coord;
      gc.drawImage( img,0,0 ); //draw layer onto pixel buffer
   }
}