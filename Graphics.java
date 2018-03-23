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
   protected int SCREEN_WIDTH = 600;
   protected int SCREEN_HEIGHT = 600;
   
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
      if( x >= 0 && x < SCREEN_WIDTH && y >= 0 && y < SCREEN_HEIGHT ) //clipping
      {
         this.pw.setColor( (int)x,(int)y,c );     //accessing pixel by coord;
      }
   }
   
   public void present()
   {
      this.gc.drawImage( img,0,0 ); //draw layer onto pixel buffer
   }
   
   protected void drawLine( int x1,int y1,int x2,int y2,Color c )
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

         for( int i = start; i <= end; i++ )
         {
                 
            currentOffset += m ; //slope incremental
            putPixel( i,ystart + (int)currentOffset,c );
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
         
         for( int i = start; i <= end; i++ )
         {
            currentOffset += m ;
            putPixel( xstart + (int)currentOffset,i,c );

         }
      } 
              
   }

   public void drawLine( Vec2 v1,Vec2 v2,Color c ) //overloaded to work with vec2
   {
      drawLine( (int)v1.x,(int)v1.y,(int)v2.x,(int)v2.y,c );
   }
   
   public void drawRectangle( Vec2 v1,Vec2 v2,Color c )
   {
      //sort vector coordinates so we loop from smaller to larger values
      double smallerX = v1.x < v2.x ? v1.x : v2.x;
      double smallerY = v1.y < v2.y ? v1.y : v2.y;
      double biggerX  = v1.x < v2.x ? v2.x : v1.x;
      double biggerY  = v1.y < v2.y ? v2.y : v1.y;
      
      for( ; smallerY <= biggerY; smallerY++ ) 
      {
         drawLine( (int)smallerX,(int)smallerY,(int)biggerX,(int)smallerY,c );
      }
   } 
   
   public void drawShape( ArrayList< Vec2 > points,Color c )
   {
      for( int i = 0; i < points.size() - 1; i++ )
      {
         drawLine( points.get( i ),points.get( i + 1 ),c ); //draws line between 2 consecutive points
      }
      drawLine( points.get( points.size() - 1 ),points.get( 0 ),c ); //last to first
   }
      
   public void drawFlatTopTriangle( Vec2 v1,Vec2 v2,Vec2 v3,Color c ) //mark as private after done testing
   {
      Vec2 topLeft;
      Vec2 topRight;
      Vec2 bottom;
      
      double v1x = v1.x;
      double v1y = v1.y;
      double v2x = v2.x;
      double v2y = v2.y;
      double v3x = v3.x;
      double v3y = v3.y;
      
      
      if( v1y > v2y && v1y > v3y ) //naive sorting approach
      {
         bottom = new Vec2( v1x,v1y );
         
         if( v2x > v3x )
         {
            topRight = new Vec2( v2x,v2y );
            topLeft = new Vec2( v3x,v3y );
         }
         else
         {
            topRight = new Vec2( v3x,v3y );
            topLeft = new Vec2( v2x,v2y );
         }
      }
      else if( v2y > v1y && v2y > v3y )
      {
         bottom = new Vec2( v2x,v2y );
         
         if( v1x > v3x )
         {
            topRight = new Vec2( v1x,v1y );
            topLeft = new Vec2( v3x,v3y );
         }
         else
         {
            topRight = new Vec2( v3x,v3y );
            topLeft = new Vec2( v1x,v1y );
         }
      }
      else
      {
         bottom = new Vec2( v3x,v3y );
         
         if( v2x > v1x )
         {
            topRight = new Vec2( v2x,v2y );
            topLeft = new Vec2( v1x,v1y );
         }
         else
         {
            topRight = new Vec2( v1x,v1y );
            topLeft = new Vec2( v2x,v2y );
         }
      }
      
      double leftSlope = ( bottom.x - topLeft.x ) / ( bottom.y - topLeft.y );  //slopes
      double rightSlope =  ( bottom.x - topRight.x ) / ( bottom.y - topRight.y );
      
      for( ; topLeft.y <= bottom.y; topLeft.y++,topRight.y++ ) //increments left and right start y values
      {
         drawLine( (int)topLeft.x,(int)topLeft.y,(int)topRight.x,(int)topRight.y,c );
         topLeft.x += leftSlope; //increments left start x value
         topRight.x += rightSlope; //increments right start x value
      }
   }
}