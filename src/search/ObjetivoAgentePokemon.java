package search;

import extras.EstadoEnum;
import extras.IdNodoEnum;
import extras.Nodo;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoAgentePokemon extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		EstadoAgente estadoAgente = (EstadoAgente) agentState;
		Nodo posicionAgente = estadoAgente.getPosicion();
		
		// El agente logra su objetivo de vencer al jefe final si:
		if(posicionAgente.getId() == IdNodoEnum.FIN // Esta en la posicion del jefe
				//&& posicionAgente.getEstado() == EstadoEnum.VACIO // La posicion esta vacia
				&& estadoAgente.getEnergiaDisponible() > 0.0) { // Le queda energia despues del combate
			return true;
		} 
		else {
			return false;
		}
	}

}
