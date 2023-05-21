package search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import java.util.Random;

public class AgenteMain {

	public static void main(String[] args) throws PrologConnectorException {
		
		//Random rand = new Random();
		
		/** La cantidad de enemigos se genera aleatoriamente, siendo como maximo 23 porque de las 29 posiciones
		  * 5 estan ocupadas por Puntos de Recarga y 1 por el Jefe final */
		Integer cantidadEnemigos = 11; //Integer cantidadEnemigos = rand.nextInt(10,23);
		
		AgentePokemon agente = new AgentePokemon(cantidadEnemigos);
		
		Ambiente environment = new Ambiente(cantidadEnemigos);
		
		SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(environment, agente);
		
		simulator.start();
	}
	
}
