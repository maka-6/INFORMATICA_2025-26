public class Parallelepipedo implements Solido{
	
	private double height, base, width; // altezza, base, larghezza
	
	public Parallelepipedo( double height, double width, double base ) throws NumeriNulliException{
		
		if ( height < 1 || width < 0 || base < 0 ) {
			throw new NumeriNulliException("Numeri non validi per");
		}
		
		this.height = height;
		this.base = base;
		this.width = width;
	}
	
	public Parallelepipedo(){
		this.height = 2;
		this.base = 3;
		this.width = 4;
	}
	
	double getBase(){
		return this.base;
	}
	double getWidth(){
		return this.width;
	}
	double getHeight(){
		return this.height;
	}
	@Override
	public double getVolume(){
		return height * base * width;
	}
	
	@Override
	public double getSurface(){
		return (height*base+base*width+width*height)*2;
	}
	
	@Override
	public int compare( Parallelepipedo b2 ){
		if ( getVolume() < b2.getVolume() ){
			return -1;
		} else if ( getVolume() > b2.getVolume() ){
			return 1;
		} else {
			return 0;
		}
	}
}