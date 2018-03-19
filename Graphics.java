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

import java.util.ArrayList;


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
      this.canvas = new Canvas( SCREEN_WIDTH,SCREEN_HEIGHT );
      this.gc = canvas.getGraphicsContext2D();
      this.img = new WritableImage( SCREEN_WIDTH,SCREEN_HEIGHT ); //layer
      this.pw  = img.getPixelWriter();
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
      grid.getChildren().addAll( this.canvas ); //put pixel buffer into layout
      

      Scene scene = new Scene( grid,SCREEN_WIDTH,SCREEN_HEIGHT );
      window.setScene( scene );
      window.show(); //present pixel buffer
   }
   
   
   private void putPixel( int x,int y,Color c )
   {
      this.pw.setColor( (int)x,(int)y,c );     //accessing pixel by coord;
   }
   
   public void present()
   {
      this.gc.drawImage( img,0,0 ); //draw layer onto pixel buffer
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
         int clippedEnd = end <= SCREEN_WIDTH ? end : SCREEN_WIDTH;

         for( int i = clippedStart; i < clippedEnd; i++ )
         {
            currentOffset += m ; //slope incremental
            
            if( ystart + (int)currentOffset > 0 && ystart + (int)currentOffset < SCREEN_HEIGHT )
            {
               putPixel( i,ystart + (int)currentOffset,c );
            }
         }
      }
      else  //y dominant
      {
         int xstart;
         m = (float)dx / (float)dy; //slope is inverted when looping through y
      
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
         int clippedEnd = end <= SCREEN_HEIGHT ? end : SCREEN_HEIGHT;
         
         for( int i = clippedStart; i < clippedEnd; i++ )
         {
            currentOffset += m ;
            
            if(  xstart + (int)currentOffset > 0 &&  xstart + (int)currentOffset < SCREEN_WIDTH )
            {
               putPixel( xstart + (int)currentOffset,i,c );
            }
         }
      }    
   }
   
   public void drawLine( Vec2 v1,Vec2 v2,Color c ) //overloaded to work with vec2
   {
      drawLine( (int)v1.getX(),(int)v1.getY(),(int)v2.getX(),(int)v2.getY(),c );
   }
   
   public void drawRectangle( Vec2 v1,Vec2 v2,Color c )
   {
      //sort vector coordinates so we loop from smaller to larger values
      double smallerX = v1.getX() < v2.getX() ? v1.getX() : v2.getX();
      double smallerY = v1.getY() < v2.getY() ? v1.getY() : v2.getY();
      double biggerX = v1.getX() < v2.getX() ? v2.getX() : v1.getX();
      double biggerY = v1.getY() < v2.getY() ? v2.getY() : v1.getY();
      
      for( ; smallerY <= biggerY; smallerY++ )
      {
         drawLine( (int)smallerX,(int)smallerY,(int)biggerX,(int)smallerY,c );
      }
   } 
   
   public void drawShape( ArrayList< Vec2 > points,Color c )
   {
      for( int i = 0; i < points.size() - 1; i++ )
      {
         drawLine( points.get( i ),points.get( i + 1 ),c );
      }
      drawLine( points.get( points.size() - 1 ),points.get( 0 ),c ); //last to first
   }
}