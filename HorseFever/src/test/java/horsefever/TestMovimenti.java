package horsefever;

/**
 * testa i movimenti senza gli effetti delle carte Azione
 * @author alessiorossotti
 *
 */
public class TestMovimenti {

	 private Partita partitaprova;
	 private Turno turnoprova;
  
	 public TestMovimenti(){
		 
		 partitaprova=new Partita(6);
		 turnoprova=new Turno(partitaprova);
		 turnoprova.FaseCorsa();
		 
	 }
}
