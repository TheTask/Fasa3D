public class Mat2
{
   private double x1;
   private double y1;   
   private double x2;
   private double y2;
   
   public Mat2( double x1,double y1,double x2,double y2 )
   {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
   }
   
   public Mat2( Vec2 v1,Vec2 v2 )
   {
      this.x1 = v1.x;
      this.y1 = v1.y;
      this.x2 = v2.x;
      this.y2 = v2.y;
   }
   
   public static Mat2 getIdentity()
   {
      Mat2 result = new Mat2( 1,0,0,1 ); 
      return result;
   }
   
   public void displayMatrix()
   {
      System.out.println( "| " + this.x1 + " " + this.x2 + " |" );
      System.out.println( "| " + this.y1 + " " + this.y2 + " |" );
   }
   
   public double determinant()
   {
      return this.x1*this.y2 - this.x2*this.y1;
   }
   
   public Mat2 add( Mat2 rhs )
   {
      Mat2 result = new Mat2( 
         this.x1 + rhs.x1,
         this.y1 + rhs.y1,
         this.x2 + rhs.x2,
         this.y2 + rhs.y2 );
         
     return result;
   }
   
   public Mat2 multiply( Mat2 rhs )
   {
      Mat2 result = new Mat2( 
         this.x1 * rhs.x1 + this.x2 * rhs.y1,
         this.y1 * rhs.x1 + this.y2 * rhs.y1,
         this.x1 * rhs.x2 + this.x2 * rhs.y2,
         this.y1 * rhs.x2 + this.y2 * rhs.y2 );
         
     return result;
   }
   
   public Mat2 scale( double s )
   {
      Mat2 result = new Mat2( s*this.x1,s*this.y1,s*this.x2,s*this.y2 );
      return result;
   }
   
   public Mat2 adjoint()
   {
      Mat2 result = new Mat2( this.y2,(-1)*this.y1,(-1)*this.x2,this.x1 );
      return result;
   }
   
   public Mat2 transpose()
   {
      Mat2 result = new Mat2( this.x1,this.x2,this.y1,this.y2 );
      return result;
   }
   
   public Mat2 inverse() throws Exception
   {
      double det = this.determinant();
      
      if( det != 0 )
      {
         return this.adjoint().scale( 1/det );
      }
      else
      {
         throw new Exception( "Matrix not invertable!" ); 
      }
   }
   
   public Vec2 getIhat()
   {
      Vec2 iHat = new Vec2( this.x1,this.y1 );
      return iHat;
   }    
   
   public Vec2 getJhat()
   {
      Vec2 jHat = new Vec2( this.x2,this.y2 );
      return jHat;
   }
}