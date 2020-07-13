/*
 * Projet : JAVA2
 * Theme : Tri d'un tableau utilisant des threads(1/3)
 * Professeur : Mr Christian 
 * Auteur : RAFALIMANANTSOA Heritiana Lucka Erickson (L3-Generaliste)
 * 			NOMENJANAHARY RANDRIAMAHAFEHY Judy (L3-Generaliste)
 * Parcours : Informatique Generale
 * 
 * */
import java.util.Random;


public class Main {

	public static void main(String[] args) {
		Long start_time = System.currentTimeMillis();
		Random random = new Random();
		int nb = 200;
		int[]t = new int[nb];
		for (int i = 0; i<nb ; i++) {
			//generer les elements du tableau
			t[i] = random.nextInt(11)-2;
		}
		//Affichage du tableau avant le tri
		System.out.println("Avant triage : "+printTab(t));
		System.out.println("-------------------------");
		System.out.println("-------------------------\n");
	
		//Triage du tableau en lancant le thread Trie
		Trie trt = new Trie(t);
		trt.start();
		try {
			trt.join();
			System.out.println("Apres triage : "+printTab(t));
			System.out.println("Duree d'execution multithread traitement : "+((double)(System.currentTimeMillis()-start_time)/1000)+"s");
			System.out.println("-------------------------\n");
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
		//Triage du tableau en utilisant un simple calcul sans thread
		Trie_simple trt_simple = new Trie_simple(t);
		System.out.println("Apres triage : "+printTab(trt_simple.getTab()));
		System.out.println("Duree d'execution simple traitement : "+((double)(System.currentTimeMillis()-start_time)/1000)+"s");
	}
	
	//Fonction necessaire à l'affichage d'un tableau
	public static String printTab(int[] tab) {
		String text="{";
		for (int i = 0; i < tab.length; i++) {
			if(i==((tab.length)-1)) {
				text+=tab[i];
			}else {
				text+=tab[i]+",";
			}
		}
		text+="}";
		return text;
	}
}
