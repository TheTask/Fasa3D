public class Vec2
{
   public double x;
   public double y;
   
   public Vec2( double x,double y ) 
   {
      this.x = x;
      this.y = y;
   }
   
   public void displayVector()
   {
      System.out.println( this.x );
      System.out.println( this.y );
   }
   
   public Vec2 add( Vec2 rhs )
   {
      Vec2 result = new Vec2( this.x + rhs.x,this.y + rhs.y );
      return result;
   } 
   
   public Vec2 scale( double factor )
   {
      Vec2 result = new Vec2( this.x *= factor,this.y *= factor );
      return result;
   }
}