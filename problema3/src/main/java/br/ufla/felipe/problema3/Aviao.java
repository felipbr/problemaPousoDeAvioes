package br.ufla.felipe.problema3;

import java.util.List;

public class Aviao {
	
//	Integer radar[];//R[i]
//	
//	Integer tempoInicialPouso[];//E[i]
//	
//	Integer tempoIdealPouso[];//T[i]
//	
//	Integer tempoFinalPouso[];//L[i]
//
//	Integer penalidadePousoAntes[];//g[i]
//	
//	Integer penalidadePousoDepois[];//h[i]
//	
//	Integer tempoSaparacaoAviaoAnteriorEProximo[][];//S[i][j]
	
	Integer radar;//R
	
	Integer tempoInicialPouso;//E
	
	Integer tempoIdealPouso;//T
	
	Integer tempoFinalPouso;//L

	Double penalidadePousoAntes;//g
	
	Double penalidadePousoDepois;//h
	
	Integer tempoSaparacaoAviaoAnteriorEProximo[];//S[j]

	//Atributos a+
	//Tempo q o aviao pousou
	Integer tempoPouso;
	//Posicao inicio, valor de i inivial no vetor de avioes antes da ordenacao
	Integer posicaoInicio;
	
	
	//Integer sobraTempo;
	Integer sobra;
	
	public Aviao() {
		
	}
	
	public Aviao(String valores[]) {
		
		try {
			radar = new Integer(valores[0]);
			tempoInicialPouso = new Integer(valores[1]);
			tempoIdealPouso = new Integer(valores[2]);
			tempoFinalPouso = new Integer(valores[3]);
			penalidadePousoAntes = new Double(valores[4]);
			penalidadePousoDepois = new Double(valores[5]);
			
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Problema ao preencher os dados iniciais do avião");
		}
	}

	public Integer getRadar() {
		return radar;
	}
	public void setRadar(Integer radar) {
		this.radar = radar;
	}

	public Integer getTempoInicialPouso() {
		return tempoInicialPouso;
	}
	public void setTempoInicialPouso(Integer tempoInicialPouso) {
		this.tempoInicialPouso = tempoInicialPouso;
	}

	public Integer getTempoIdealPouso() {
		return tempoIdealPouso;
	}
	public void setTempoIdealPouso(Integer tempoIdealPouso) {
		this.tempoIdealPouso = tempoIdealPouso;
	}

	public Integer getTempoFinalPouso() {
		return tempoFinalPouso;
	}
	public void setTempoFinalPouso(Integer tempoFinalPouso) {
		this.tempoFinalPouso = tempoFinalPouso;
	}

	public Double getPenalidadePousoAntes() {
		return penalidadePousoAntes;
	}
	public void setPenalidadePousoAntes(Double penalidadePousoAntes) {
		this.penalidadePousoAntes = penalidadePousoAntes;
	}

	public Double getPenalidadePousoDepois() {
		return penalidadePousoDepois;
	}
	public void setPenalidadePousoDepois(Double penalidadePousoDepois) {
		this.penalidadePousoDepois = penalidadePousoDepois;
	}

	public Integer[] getTempoSaparacaoAviaoAnteriorEProximo() {
		return tempoSaparacaoAviaoAnteriorEProximo;
	}
	public void setTempoSaparacaoAviaoAnteriorEProximo(Integer[] tempoSaparacaoAviaoAnteriorEProximo) {
		this.tempoSaparacaoAviaoAnteriorEProximo = tempoSaparacaoAviaoAnteriorEProximo;
	}
	
	public Integer getPosicaoInicio() {
		return posicaoInicio;
	}
	public void setPosicaoInicio(Integer posicaoInicio) {
		this.posicaoInicio = posicaoInicio;
	}
	
	public Integer getTempoPouso() {
		return tempoPouso;
	}
	public void setTempoPouso(Integer tempoPouso) {
		this.tempoPouso = tempoPouso;
	}
	
	public Integer getSobra() {
		return sobra;
	}
	public void setSobra(Integer sobra) {
		this.sobra = sobra;
	}

	public int tempoPousoAntesPrevisto() {
		if(tempoPouso != null && tempoIdealPouso > tempoPouso) {
			return tempoIdealPouso-tempoPouso;
		}
		return 0;
	}
	
	public int tempoPousoDepoisPrevisto() {
		if(tempoPouso != null && tempoIdealPouso < tempoPouso) {
			return tempoPouso-tempoIdealPouso;
		}
		return 0;
	}

	//Preenche o vetor
	public void adicionarTempo(List<String> valores) {
		tempoSaparacaoAviaoAnteriorEProximo = new Integer[valores.size()];
		for(int i = 0; i < valores.size(); i++) {
			tempoSaparacaoAviaoAnteriorEProximo[i] = new Integer(valores.get(i));
		}
	}

	public void tempoQuePodeReduzir(Integer anterior) {
//		if(sobra > 0) {
//			return sobra;
//		}
	}
	
}
