package br.ufla.felipe.problema3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufla.felipe.util.Heuristica;
import br.ufla.felipe.util.MergeSort;
import br.ufla.felipe.util.Ordenacao;

public class App  {
	
	static Integer avioesOrdenados[];
	
	static Integer ordemAparicaoRadar[];
	
	static Integer solucoesOtimas[] = {700, 1480, 820, 2520, 3100, 24442, 1550, 1950, 0, 0, 0, 0, 0};
	
    public static void main( String[] args ) {
        
    	Aviao avioes[];
    	Double total = 0d;
    	Double totalOtimo = 0d;
    	
    	for(int arq = 1; arq <= 8; arq++) {
    		
	    	//monta objetos pelo arquivo 0(nL)// nL = numero de linhas
    		avioes = preencherAvioes(arq);
	        
    		if(avioes != null) {
    			Double somaPenalidade = null;
//    			for(Ordenacao ord: Ordenacao.values()) {
			    	//ordena por ordem de aparicao no radar
			    	avioesOrdenados = MergeSort.ordenarAvioes(avioes.clone(), Ordenacao.TEMPO_IDEAL);
			        
			    	Aviao avioesOriginais[] = avioes.clone();
			    	Aviao avioesList[] = Heuristica.preencherTempoPouso(avioes.clone(), avioesOrdenados);
			    	
			    	if(avioesList != null) {
				    	Double soma = Heuristica.pegarPenalidade(avioesOriginais, avioesOrdenados);
				        
				    	if(somaPenalidade == null || soma < somaPenalidade) {
				    		somaPenalidade = soma;
				    	}	
			    	}
//    			}
    			
//    			for(Ordenacao ord: Ordenacao.values()) {
			    	//ordena por ordem de aparicao no radar
			    	avioesOrdenados = MergeSort.ordenarAvioes(avioes.clone(), Ordenacao.TEMPO_IDEAL);
			        
			    	 avioesOriginais = avioes.clone();
			    	 avioesList = Heuristica.preencherTempoPousoInverso(avioes.clone(), avioesOrdenados);
			    	
			    	if(avioesList != null) {
				    	Double soma = Heuristica.pegarPenalidade(avioesOriginais, avioesOrdenados);
				        
				    	if(somaPenalidade == null || soma < somaPenalidade) {
				    		somaPenalidade = soma;
				    	}	
			    	}
//    			}

    			if(somaPenalidade != null) {
			        total += somaPenalidade;
			        totalOtimo += solucoesOtimas[arq-1];
			        
			        System.out.println("Arquivo ariland"+arq+".txt "+avioes.length+" aviões.");
					System.out.println("Penalidade: "+somaPenalidade+
							", tempo ótimo: "+solucoesOtimas[arq-1]+", diferença: "+(somaPenalidade-solucoesOtimas[arq-1]));
    			} else {
    				System.out.println("Arquivo ariland"+arq+".txt "+avioes.length+" aviões.");
					System.out.println("Penalidade: --- , tempo ótimo: "+solucoesOtimas[arq-1]);
    			}
				
    		} else {
    			System.out.println("Ocorreu erro na leitura dos aviões");
    		}
    	}
    	
    	
    	System.out.println("\nTotal: "+total+", total ótimo: "+totalOtimo+", diferença: "+(total-totalOtimo));
    	
    }

	private static Aviao[] preencherAvioes(Integer numeroArq) {
		
		Aviao avioes[] = null;
		try {
			Aviao aviao = null;
			String caminho = "/Users/fiocruz/Desktop/airlands/airland"+numeroArq+".txt";
			
			BufferedReader reader = new BufferedReader(new FileReader(new File(caminho)));
			String line;
			
			List<String> valoresAviao = new ArrayList<String>();
			int numeroAvioes = 0;
			
			int posicao = 0;
			
			while((line = reader.readLine()) != null) {
				if( ! line.trim().equals("")){
					
					String valores[] = line.trim().split(" ");
					//Se numero de avioes for 0, sinal que nao leu a primeira linha ainda
					if(numeroAvioes == 0) {
						numeroAvioes = new Integer(valores[0]);
						avioes = new Aviao[numeroAvioes];
						ordemAparicaoRadar = new Integer[numeroAvioes];
						
					} else {
						//Aviao nulo, le dados do aviao
						if(aviao == null) {
							aviao = new Aviao(valores);
							
						//se aviao tiver preenchido, le valores do tempoSaparacaoAviaoAnteriorEProximo[j], //S[i][j]]
						} else {
							valoresAviao.addAll(Arrays.asList(valores));
							
							// se o espacamento de tempo dos avioes bater com o numero dele, encerrou o preenchimento
							if(valoresAviao.size() == numeroAvioes) {
								aviao.adicionarTempo(valoresAviao);
								aviao.setPosicaoInicio(posicao);
								avioes[posicao] = aviao;
								
								//reinicializa para começar o preenchimento do proximo aviao
								aviao = null;
								valoresAviao = new ArrayList<String>();
								posicao += 1;
							}
						}
					}
				}
			}
			
			reader.close();
			
		} catch (IOException e) { }
		
		return avioes;
	}
	
}
