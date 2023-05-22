package search;

import extras.Enemigo;
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
		if(posicionAgente.getId() == IdNodoEnum.GOAL
				&& estadoAgente.getEnergiaDisponible() > 20.0) {
			return true;
		} 
		else {
			return false;
		}
	}

}
