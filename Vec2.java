public class Vec2
{
   private double x;
   private double y;
   
   public Vec2( double x,double y ) 
   {
      this.x = x;
      this.y = y;
   }
   
   public double getX(){ return this.x; }
   public double getY(){ return this.y; }
   
   public static void displayVector( Vec2 v ) 
   {
      System.out.println( v.x );
      System.out.println( v.y );
   }
   
   public Vec2 add( Vec2 rhs )
   {
      Vec2 result = new Vec2( this.x + rhs.x,this.y + rhs.y );
      return result;
   } 
}