public class Main{
	
	public static void main( String argv[] ){
		
		try {
			Blocco marmo = new Blocco( 3.0, 4, 6,  100, "marmo");
			
		} catch (NumeriNulliException e){
			System.out.println(e.toString());
		}catch (NomeNulloException e){
			System.out.println(e.toString());
		}
	}
}