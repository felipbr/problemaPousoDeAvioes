package br.ufla.felipe.util;

import br.ufla.felipe.problema3.Aviao;

public class Heuristica {

	/**
	 * Oreenche o tempo de pouso dos aviões com base na ordanção passada
	 * @param avioes
	 * @param ordenacaoAvioes
	 * @return
	 */
	public static Aviao[] preencherTempoPouso(Aviao avioes[], Integer ordenacaoAvioes[]) {
		
		int j = 0;
		int i = ordenacaoAvioes[0];
		
		int sobra =0;
		
		avioes[i].setTempoPouso(avioes[i].getTempoIdealPouso());
		avioes[i].setSobra(0);
		//N
		for(int k = 0; k < ordenacaoAvioes.length-1; k++) {
			
			i = ordenacaoAvioes[k];
			j = ordenacaoAvioes[k+1];//atual
				
			//tj = max(ta j, max(t i + Sij) )
			int tempIdealJ = avioes[j].getTempoIdealPouso();
			int maxTiSij = avioes[i].getTempoPouso()+ avioes[i].getTempoSaparacaoAviaoAnteriorEProximo()[j];
			
			int maxTjSji = avioes[i].getTempoPouso() + avioes[j].getTempoSaparacaoAviaoAnteriorEProximo()[i];
			if(maxTjSji > maxTiSij) {
				maxTiSij = maxTjSji;
			}
			
			int tempoPouso;
			//tempo ideal de pouso é sempre preferencia, apenas caso nao de que colocamos com a distancia do aviao anterior
			if( tempIdealJ >= maxTiSij ) {
				tempoPouso = tempIdealJ;
				sobra = tempIdealJ-maxTiSij;
			} else {
				int qtdPassouIdeal = maxTiSij-tempIdealJ;
				int qtdAjuste = 0;
				
				//verifica tem sobra do anterior e se a penalidade é menor ou igual que a do atual, 
				if(avioes[i].getSobra() > 0  && 
						avioes[i].getPenalidadePousoAntes() >= avioes[j].getPenalidadePousoDepois()) {
					
					// aplica a qtd de sobra possivel para ajuste e retira a sobra do aviao i(anterior)
					if(qtdPassouIdeal>=avioes[i].getSobra()) {
						qtdAjuste = avioes[i].getSobra();
						avioes[i].setSobra(0);
						
					} else {
						qtdAjuste = qtdPassouIdeal;
						avioes[i].setSobra(avioes[i].getSobra()-qtdPassouIdeal);
					}
					avioes[i].setTempoPouso(avioes[i].getTempoPouso()-qtdAjuste);
					qtdPassouIdeal -= qtdAjuste;
				}
				tempoPouso = maxTiSij-qtdAjuste;
				sobra = 0;
			}
			avioes[j].setTempoPouso(tempoPouso);
			avioes[j].setSobra(sobra);
		}
		
		System.out.println(" ");
			
		return avioes;
	}
	
public static Aviao[] preencherTempoPousoInverso(Aviao avioes[], Integer ordenacaoAvioes[]) {
		
		int j = 0;
		int i = ordenacaoAvioes[ordenacaoAvioes.length-1];
		
		int sobra =0;
		
		avioes[i].setTempoPouso(avioes[i].getTempoIdealPouso());
		avioes[i].setSobra(0);
		//N
		for(int k = ordenacaoAvioes.length-1; k > 0; k--) {
			
			i = ordenacaoAvioes[k];
			j = ordenacaoAvioes[k-1];//atual
				
			//tj = max(ta j, max(t i + Sij) )
			int tempIdealJ = avioes[j].getTempoIdealPouso();
			int minTiSij = avioes[i].getTempoPouso()- avioes[i].getTempoSaparacaoAviaoAnteriorEProximo()[j];
			
			int minTjSji = avioes[i].getTempoPouso() - avioes[j].getTempoSaparacaoAviaoAnteriorEProximo()[i];
			if(minTjSji < minTiSij) {
				minTiSij = minTjSji;
			}
			
			
			int tempoPouso;
			//tempo ideal de pouso é sempre preferencia, apenas caso nao de que colocamos com a distancia do aviao anterior
			if( tempIdealJ <= minTiSij ) {
				tempoPouso = tempIdealJ;
				sobra = minTiSij-tempIdealJ;
			} else {
				int qtdPassouIdeal = tempIdealJ-minTiSij;
				int qtdAjuste = 0;
				
				//verifica tem sobra do anterior e se a penalidade é menor ou igual que a do atual, 
				if(avioes[i].getSobra() > 0  && 
						avioes[i].getPenalidadePousoAntes() >= avioes[j].getPenalidadePousoDepois()) {
					
					// aplica a qtd de sobra possivel para ajuste e retira a sobra do aviao i(anterior)
					if(qtdPassouIdeal>=avioes[i].getSobra()) {
						qtdAjuste = avioes[i].getSobra();
						avioes[i].setSobra(0);
						
					} else {
						qtdAjuste = qtdPassouIdeal;
						avioes[i].setSobra(avioes[i].getSobra()-qtdPassouIdeal);
					}
					avioes[i].setTempoPouso(avioes[i].getTempoPouso()+qtdAjuste);
					qtdPassouIdeal -= qtdAjuste;
				}
				tempoPouso = minTiSij+qtdAjuste;
				sobra = 0;
			}
			avioes[j].setTempoPouso(tempoPouso);
			avioes[j].setSobra(sobra);
		}
		
		System.out.println(" ");
			
		return avioes;
	}
	
	
	public static Double pegarPenalidade(Aviao avioes[], Integer ordenacaoAvioes[]) {
        Double somaPenalidade = 0d;
        
		for(Aviao aviao :  avioes) {
        	somaPenalidade += ( aviao.getPenalidadePousoAntes() * aviao.tempoPousoAntesPrevisto())  +
        			(aviao.getPenalidadePousoDepois() * aviao.tempoPousoDepoisPrevisto());
        	
		}
		return somaPenalidade;
	}
}
