package br.ufla.felipe.util;

import br.ufla.felipe.problema3.Aviao;

public class MergeSort {
     
	static Ordenacao ordenacao;
	
	public static Integer[] ordenarAvioes(Aviao arr[], Ordenacao ord) { 
		
		ordenacao = ord;
		
        MergeSort ob = new MergeSort(); 
        ob.sort(arr, 0, arr.length-1); 
        
        Integer ordenacaoAvioes[] = new Integer[arr.length];
        for(int i= 0; i < arr.length; i++) {
        	ordenacaoAvioes[i] = arr[i].getPosicaoInicio();
        }
        return ordenacaoAvioes;
    } 
	
	private void merge(Aviao[] aviao, int l, int m, int r) { 
        // Encontra os 2 subarrays para realizar o merge 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Array temporario */
        Aviao L[] = new Aviao[n1]; 
        Aviao R[] = new Aviao[n2]; 
  
        /*Preenche arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = aviao[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = aviao[m + 1+ j]; 
  
        /* Merge */
  
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
        	if(Ordenacao.RADAR.equals(ordenacao)) {
	        	if (L[i].getRadar() < R[j].getRadar()) { 
	                aviao[k] = L[i]; 
	                i++; 
	            } 
	            else if (L[i].getRadar() == R[j].getRadar() &&
	            		L[i].getTempoFinalPouso() > R[j].getTempoFinalPouso()) { 
	                aviao[k] = L[i]; 
	                i++; 
	            } else { 
	                aviao[k] = R[j]; 
	                j++; 
	            } 
        	} else if (Ordenacao.TEMPO_IDEAL.equals(ordenacao)) {
	        	if (L[i].getTempoIdealPouso() < R[j].getTempoIdealPouso()) { 
	                aviao[k] = L[i]; 
	                i++; 
	            } 
	            else if (L[i].getTempoIdealPouso() == R[j].getTempoIdealPouso() &&
	            		L[i].getTempoFinalPouso() > R[j].getTempoFinalPouso()) { 
	                aviao[k] = L[i]; 
	                i++; 
	            } 
	            else { 
	                aviao[k] = R[j]; 
	                j++; 
	            } 
        	}
            k++; 
        } 
  
        /* Copia os elementos de L[], caso tenha */
        while (i < n1) { 
            aviao[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copia os elementos de R[], caso tenha */
        while (j < n2) { 
            aviao[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    // merge() 
    void sort(Aviao aviao[], int l, int r) { 
        if (l < r) { 
            // Encontra o meio
            int m = (l+r)/2; 
  
            // Analisa cada metade
            sort(aviao, l, m); 
            sort(aviao , m+1, r); 
  
            // junta as metades
            merge(aviao, l, m, r); 
        } 
    } 
  
    public static void printArray(Aviao arr[]) { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) {
        	System.out.print(arr[i].getPosicaoInicio() +  " ");
//            System.out.print(arr[i].getRadar() + "-"+ arr[i].getTempoFinalPouso() +  " "); 
        }
        System.out.println(); 
    } 
  
    
}