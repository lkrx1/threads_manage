
public class Trie_simple {
	private int[] t;
	private int debut,fin; //tranche de tableau qu'il faut trier
	
	public Trie_simple(int[] t) {
		this(t,0,t.length -1);
	}
	private Trie_simple(int[] t,int debut,int fin) {
		this.t = t;
		this.debut = debut;
		this.fin = fin;
		if (fin - debut <2) {
			if(getTab()[debut]>getTab()[fin]) {
				echanger(debut,fin);
			}
		}else {
			int milieu = debut + (fin - debut) / 2;
			triFusion(debut,fin);
		}
		//pas de start() pour permettre d'effectuer des traitements sur le Tri_threads 
		//avant le lancement (plus dynamiqyue )
	}
	public void setTab(int[] p_tab) {
		this.t = p_tab;
	}
	public void setValueTab(int ind,int value) {
		this.t[ind]=value;
	}
	public int[] getTab() {
		return this.t;
	}
	
	private void echanger(int i, int j) {
		int temp = getTab()[i];
		setValueTab(i, getTab()[j]);
		setValueTab(j, temp);
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
			if(getTab()[i1] < getTab()[i2]) {
				tFusion[iFusion++] = getTab()[i1++];
			}else {
				tFusion[iFusion++] = getTab()[i2++];
			}
		}
		if(i1 > milieu) {
			//la 1ere tranche est epuisée
			for(int i=i2;i<=fin;) {
				tFusion[iFusion++] = getTab()[i++];
			}
		}else {
			//la deuxieme tranche est epuisée
			for(int i=i1;i<=milieu;) {
				tFusion[iFusion++] = getTab()[i++];
			}
		}
		//Copie TFusion dans t
		for (int i= 0,j = debut ; i <=fin-debut;) {
			setValueTab(j++, tFusion[i++]);
		}
	}
	
}
