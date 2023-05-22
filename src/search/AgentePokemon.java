package search;

import frsf.cidisi.faia.agent.Action;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.solver.search.*;

import java.util.Vector;
import java.util.logging.*;
import actions.*;
import extras.Nodo;

public class AgentePokemon extends SearchBasedAgent {

	//Constructor
		public AgentePokemon(Integer cantEnems) {
			// Objetivo del agente
			ObjetivoAgentePokemon objetivo = new ObjetivoAgentePokemon();
			
			// Estado del agente
			EstadoAgente estado = new EstadoAgente(cantEnems);
			this.setAgentState(estado);
			
			// Operadores del agente
			Vector<SearchAction> acciones = new Vector<SearchAction>();
			
			acciones.addElement(new CargarEnergia());
			//acciones.addElement(new ActivarPoderEspecial());
			
			for(int i=0; i<estado.getMapaConocido().getMapa().size(); i++) {
				acciones.addElement(new Moverse(estado.getMapaConocido().getMapa().get(i)));
			}
			
			for(int j=0; j<estado.getMapaConocido().getMapa().size(); j++) {
				acciones.addElement(new Huir(estado.getMapaConocido().getMapa().get(j)));
			}
			
			acciones.addElement(new Combatir());
			
			
			// Problema que el agente debe resolver
			Problem problem = new Problem(objetivo, estado, acciones);
			this.setProblem(problem);
		}
		
		@Override
		public Action selectAction() {
			/** Estrategias
			 * 1) Uniform Cost:
			 * IStepCostFunction costFunction = new FuncionCosto();
			 * UniformCostSearch strategy = new UniformCostSearch(costFunction);
			 * 
			 * 2) Breath First Search:
			 * BreathFirstSearch strategy = new BreathFirstSearch();
			 * 
			 * 2) Depth First Search:
			 * DepthFirstSearch strategy = new DepthFirstSearch();
			 * 
			 * 3) A Star Search:
			 * IStepCostFunction cost = new CostFunction();
			 * IEstimatedCostFunction heuristic = new Heuristic();
			 * AStarSearch strategy = new AStarSearch(cost, heuristic);
			 * */
			
			// Estrategia seleccionada (Reemplazar con las de arriba)
			BreathFirstSearch strategy = new BreathFirstSearch();
			
			// Crear objeto Search con la estrategia
			Search searchSolver = new Search(strategy);
			
			// Generar file XML con el arbol de busqueda
			searchSolver.setVisibleTree(Search.XML_TREE);
			
			// Setear el Solver
			this.setSolver(searchSolver);
			
			// Preguntar al Solver por la mejor accion
			Action selectedAction = null;
	        try {
	            selectedAction = this.getSolver().solve(new Object[]{this.getProblem()});
	        } catch (Exception ex) {
	            Logger.getLogger(AgentePokemon.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        // Retornar accion seleccionada
			return selectedAction;
		}
		
		@Override
		public void see(Perception p) {	
			this.getAgentState().updateState(p);
		}	
		
}
