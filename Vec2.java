public class Vec2
{
   private int x;
   private int y;
   
   public Vec2( int x,int y ) 
   {
      this.x = x;
      this.y = y;
   }
   
   public int getX(){ return this.x; }
   public int getY(){ return this.y; }
   
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