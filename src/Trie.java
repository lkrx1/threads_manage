
public class Trie extends Thread{
	private int[] t;
	private int debut,fin; //tranche de tableau qu'il faut trier
	
	public Trie(int[] t) {
		this(t,0,t.length -1);
	}
	private Trie(int[] t,int debut,int fin) {
		this.t = t;
		this.debut = debut;
		this.fin = fin;
		//start();
		//pas de start() pour permettre d'effectuer des traitements sur le Tri_threads 
		//avant le lancement (plus souples pour les sous classes eventuelles)
	}
	
	public void run() {
		if (fin - debut <2) {
			if(t[debut]>t[fin]) {
				echanger(debut,fin);
			}
		}else {
			int milieu = debut + (fin - debut) / 2;
			Trie trt1 = new Trie(t,debut,milieu);
			trt1.start();
			Trie trt2 = new Trie(t,milieu + 1,fin);
			trt2.start();
			try {
				trt1.join();
				trt2.join();
			}catch(InterruptedException e) {}
			triFusion(debut,fin);
		}
	}
	
	private void echanger(int i, int j) {
		int temp = t[i];
		t[i] = t[j];
		t[j] = temp;
	}
	
	private void triFusion(int debut,int fin) {
		//tableau ou va aller la fusion
		int[] tFusion = new int[fin-debut +1];
		int milieu = (debut +fin) / 2;
		//Indices des elements à comparer
		int i1 = debut,i2 = milieu +1;
		//indice de la prochaine case du tableau tFusion à remplir
		int iFusion = 0;
		while (i1 <= milieu && i2 <= fin) {
			if(t[i1] < t[i2]) {
				tFusion[iFusion++] = t[i1++];
			}else {
				tFusion[iFusion++] = t[i2++];
			}
		}
		if(i1 > milieu) {
			//la 1ere tranche est epuisée
			for(int i=i2;i<=fin;) {
				tFusion[iFusion++] = t[i++];
			}
		}else {
			//la deuxieme tranche est epuisée
			for(int i=i1;i<=milieu;) {
				tFusion[iFusion++] = t[i++];
			}
		}
		//Copie TFusion dans t
		for (int i= 0,j = debut ; i <=fin-debut;) {
			t[j++] = tFusion[i++];
		}
	}
	
	
}

