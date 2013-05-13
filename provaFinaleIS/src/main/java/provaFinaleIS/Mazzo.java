package provaFinaleIS;

import java.util.ArrayList;

public class Mazzo {

	private Carta carte;
	private ArrayList<String> mazzo;
	
	public Mazzo(Carta carte){
		this.carte=carte;
		String [] temp=carte.getCarte();
		mazzo = new ArrayList();
		
		System.out.println("LISTA ORDINATA");
		for (int i=0; i<temp.length; i++){
			this.inserisci(temp[i]);
			System.out.println(mazzo.get(i));
		}
		
		this.mischia();
		
		System.out.println("LISTA MISCHIATA");
		for (int j=0; j<mazzo.size(); j++){
			System.out.println(mazzo.get(j));
		}
	}
	
	public void mischia(){
		int numpermutazioni;
		int min=20, max=30;
		String temp1, temp2;
		int rand;
		numpermutazioni=min + (int)(Math.random() * ((max - min) + 1));
		for (int j=0; j<numpermutazioni; j++){
			rand=(int)(Math.random()*(mazzo.size()));
			temp1=mazzo.get(rand);
			mazzo.remove(rand);
			rand=(int)(Math.random()*(mazzo.size()-1));
			temp2=mazzo.set(rand, temp1);
			mazzo.add(temp2);
		}
	}
	
	public String pesca(){
		String c=mazzo.get(0);
		mazzo.remove(0);
		return c;
	}
	
	public void inserisci(String carta){
		mazzo.add(carta);
	}
}
