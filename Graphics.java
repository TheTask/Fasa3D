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
   private int SCREEN_WIDTH = 600;
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
   
   public void drawLine( int x1,int y1,int x2,int y2,Color c )
   {
      int dx = x2 - x1;
      int dy = y2 - y1;
      int start; //loop start point
      int end; //lopp end point
      float m;  //slope
      float currentOffset = 0; //relative slope offset
      
      if( Math.abs( dx ) > Math.abs( dy ) ) //x dominant
      {
         int ystart;
         m = (float)dy / (float)dx; //slope
         
         if( dx > 0 ) //we have to loop from smaller to larger, so we sort
         {
            start = x1;
            end = x2;
            ystart = y1;
         }
         else
         {
            start = x2;
            end = x1;
            ystart = y2;
         }
         
         int clippedStart = start >= 0 ? start : 0;  //clipping if we go outside of the screen
         int clippedEnd = end < SCREEN_WIDTH ? end : SCREEN_WIDTH - 1;

         for( int i = clippedStart; i < clippedEnd; i++ )
         {
            currentOffset += m ; //slope incremental
            putPixel( i,ystart + (int)currentOffset,c );
         }
      }
      else  //y dominant
      {
         int xstart;
         m = (float)dx / (float)dy; //slope
      
         if( dy > 0 ) //sorting through starting variables
         {
            start = y1;
            end = y2;
            xstart = x1;
         }
         else
         {
            start = y2;
            end = y1;
            xstart = x2;
         }
      
         int clippedStart = start >= 0 ? start : 0; //clipping
         int clippedEnd = end < SCREEN_HEIGHT ? end : SCREEN_HEIGHT - 1;
         
         for( int i = clippedStart; i < clippedEnd; i++ )
         {
            currentOffset += m ;
            putPixel( xstart + (int)currentOffset,i,c );
         }
      }    
   }
}