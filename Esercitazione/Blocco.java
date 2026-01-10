public class Blocco extends Parallelepipedo{
	
	private double cost; // costo del materiale
	private String material; // tipo di materiale
	
	public Blocco( double height, double width, double base, double cost, String material ) throws NumeriNulliException, NomeNulloException{
		
		super( height, width, base );
		
		if ( cost < 0 ) {
			throw new NumeriNulliException("Numeri non validi per le misure");
		}
		
		if( material.equals("") ){
			throw new NomeNulloException("Nome non valido");
		}
		
		this.cost = cost;
		this.material = material;
	}
		
	public Blocco() throws NumeriNulliException{
		super( 2.0, 4.0, 3.0 );
	}
}