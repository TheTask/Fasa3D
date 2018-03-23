public class Space2D extends Graphics
{
   private Mat2 transformation;
   private Vec2 iHat;
   private Vec2 jHat;
   private Graphics go;
   
   public Space2D( Graphics gfx )
   {
      this.transformation = Mat2.getIdentity();
      refreshBasis();

      this.go = gfx;
   }
   
   private void refreshBasis()
   {
      this.iHat = transformation.getIhat();
      this.jHat = transformation.getJhat();
      this.jHat.x *= (-1);
   }
   
   public void applyTransformation( Mat2 t )
   {
      this.transformation = t;
      refreshBasis();
   }
   
   public void drawSpace() //currently only works for eigen vectors and shear transformation, this approach is probably bad.
   {
      int scale = 1000; //scales basis vectors so lines fill the screen
      int vectorLen = 25; //spacing between lines
      
      for( int i = 0; i < 10 * ( SCREEN_HEIGHT > SCREEN_WIDTH ? SCREEN_HEIGHT : SCREEN_WIDTH ); i += iHat.x * vectorLen ) //lines based on iHat
      {
           go.drawLine( i,0,i + ((int)jHat.x) * scale,((int)jHat.y) * scale,Colors.BLACK ); //fixes origin at 0,0 ( top left corner )
           go.drawLine( i,SCREEN_HEIGHT,i + ((int)jHat.x) * scale,((int)jHat.y) * scale,Colors.BLACK ); //fixes origin at bottom right corner
      }
      
      for( int i = -1000; i < 10 * ( SCREEN_HEIGHT > SCREEN_WIDTH ? SCREEN_HEIGHT : SCREEN_WIDTH ); i += jHat.y * vectorLen ) //lines based on jHat
      {
           go.drawLine( 0,i,((int)iHat.x) * scale,i - ((int)iHat.y) * scale,Colors.BLACK ); //fixes origin at 0,0 ( top left corner )
           go.drawLine( SCREEN_WIDTH,i,((int)iHat.x) * scale,i - ((int)iHat.y) * scale,Colors.BLACK ); //fixes origin at bottom right corner
      }
         
      go.drawLine( SCREEN_WIDTH/2,SCREEN_HEIGHT/2,SCREEN_WIDTH/2 + (int)iHat.x * vectorLen,SCREEN_HEIGHT/2 - (int)iHat.y * vectorLen,Colors.GREEN ); //draw iHat base
      go.drawLine( SCREEN_WIDTH/2,SCREEN_HEIGHT/2,SCREEN_WIDTH/2 - (int)jHat.x * vectorLen,SCREEN_HEIGHT/2 - (int)jHat.y * vectorLen,Colors.RED ); //draw jHat base
   }
}