package search;

import java.util.ArrayList;
import java.util.Random;

import extras.Enemigo;
import extras.EstadoEnum;
import extras.IdEnemigosEnum;
import extras.IdNodoEnum;
import extras.Mapa;
import extras.Nodo;
import extras.PuntoRecarga;
import extras.Satelite;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

	//Atributos
	private Mapa mapaAmbiente;
	private Nodo ubicacionAgente;
	private Double energiaAgente;
	private ArrayList<Enemigo> enemigos;
	private Integer cantidadEnemigos; // No incluye al jefe final
	private ArrayList<PuntoRecarga> puntosRecarga;
	private ArrayList<Integer> posicionesOcupadas;
	private ArrayList<IdEnemigosEnum> nombresUsados;
	private Satelite satelite;
	
	//Constructor
	public EstadoAmbiente(Integer cantidadEnemigos) {
		this.cantidadEnemigos = cantidadEnemigos;
		posicionesOcupadas = new ArrayList<Integer>();
		this.initState();
	}
	
	@Override
	public void initState() {
		this.mapaAmbiente = new Mapa(); // Mapa desconocido
		//this.posicionesOcupadas = new ArrayList<Integer>();
		this.satelite = new Satelite();
		this.nombresUsados = new ArrayList<IdEnemigosEnum>();
		inicializarMapa();
		this.ubicacionAgente = mapaAmbiente.getNodo(IdNodoEnum.AM1);
	}
	
	//Metodos
	private void inicializarMapa() {
		crearPuntosRecarga();
		crearEnemigos();
		crearJefeFinal();
		asignarPuntosVacios();
		satelite.setMapaSatelite(getMapaAmbiente());
	}
	
	private void crearPuntosRecarga() {
		ArrayList<PuntoRecarga> puntosCreados = new ArrayList<PuntoRecarga>();
		Integer posicion;
		// Se crean 3 puntos de recarga fijos consecutivos
		for(int i=0; i<3; i++) {
			posicion = i+3;
			posicionesOcupadas.add(posicion);
			this.mapaAmbiente.getNodo(posicion).setEstado(EstadoEnum.PUNTORECARGA); // Actualizo el nodo en el mapa
			Nodo nodo = this.mapaAmbiente.getNodo(posicion); // Busco el nodo actualizado
			PuntoRecarga punto = new PuntoRecarga(nodo); // Creo el punto de recarga correspondiente
			puntosCreados.add(punto); // Agrego el punto a la lista de puntos creados
		}
		this.puntosRecarga = puntosCreados; // Se actualizan los puntos de recarga
	}
	
	private void crearEnemigos() {
		ArrayList<Enemigo> enemigosCreados = new ArrayList<Enemigo>();
		Integer posicion;
		// Se crean 11 enemigos fijos consecutivos
		for(int i = 0; i < cantidadEnemigos; i++) {
			posicion = i+6;
			posicionesOcupadas.add(posicion);
			this.mapaAmbiente.getNodo(posicion).setEstado(EstadoEnum.ENEMIGO); // Actualizo el nodo en el mapa
			Nodo nodo = this.mapaAmbiente.getNodo(posicion); // Busco el nodo actualizado
			Enemigo enemigo = generarEnemigo(nodo); // Creo el enemigo correspondiente
			enemigosCreados.add(enemigo); // Agrego el enemigo a la lista de enemigos creados
		}
		this.enemigos = enemigosCreados; // Se actualizan los enemigos
	}
	
	private void crearJefeFinal() {
		this.mapaAmbiente.getNodo(IdNodoEnum.FIN).setEstado(EstadoEnum.ENEMIGO);
		Nodo nodoJefe = this.mapaAmbiente.getNodo(IdNodoEnum.FIN);
		Enemigo enem = new Enemigo("JEFE FINAL", 15.0, nodoJefe);
		this.enemigos.add(enem);
	}
	
	private void asignarPuntosVacios() {
		// Para las primeras 28 posiciones, es decir, sin contar la del jefe final
		for(int i=0; i<28; i++) {
			if(!this.posicionesOcupadas.contains(i)) {
				posicionesOcupadas.add(i);
				this.mapaAmbiente.getNodo(i).setEstado(EstadoEnum.VACIO);
			}
		}
		this.mapaAmbiente.getGoal().setEstado(EstadoEnum.VACIO);
	}
	
	private Enemigo generarEnemigo(Nodo nodo) {
		Integer cantNombresUsados = 0;
		Random rand = new Random();
		if(!nombresUsados.isEmpty()) {
			cantNombresUsados = nombresUsados.size();
		}
		Double energia;
		IdEnemigosEnum idNuevo = IdEnemigosEnum.randomId();
		
		// Si la lista de nombres usados no esta vacia, se verifica que no se asigne un nombre ya asignado al enemigo
		if (cantNombresUsados != 0) {
			while (nombresUsados.contains(idNuevo)) {
				idNuevo = IdEnemigosEnum.randomId();
			}
		}
		nombresUsados.add(idNuevo);
		
		// A un tercio de los enemigos se les asigna energia de la misma forma que al agente: un valor random entre 10 y 20
		if (cantNombresUsados < cantidadEnemigos / 3) {
			energia = (double) rand.nextInt(1, 5); //rand.nextDouble(10, 21);
		}
		// Al otro tercio se le asigna un valor random entre 20 y 30
		else if (cantNombresUsados < cantidadEnemigos * 2 / 3) {
			energia = (double) rand.nextInt(4, 8); //rand.nextDouble(20, 31);
		}
		// Al otro tercio se le asigna un valor random entre 30 y 40
		else {
			energia = (double) rand.nextInt(7, 11); // rand.nextDouble(30, 41);
		}
		
		Enemigo enem = new Enemigo(idNuevo.name(), energia, nodo);
		return enem;
	}
	
	// Cada cierta cantidad de turnos/ciclos, los enemigos pueden intentar moverse a un espacio vacio 
	public void comportamientoEnemigos() {
		Random rand = new Random();
		for(Enemigo e: enemigos) {
			// Se verifica que no sea el jefe final, ya que este no se mueve
			if(e.getPosicion().getId() != IdNodoEnum.FIN) {
				e.aumentarTurnosDetenido(); // Se aumentan los turnos detenido
				if(e.getTurnosDetenido() >= e.getTurnosAEsperar()) { // Si ya puede moverse
					ArrayList<Nodo> lista = retornarPosicionesAdyacentesVacias(e); // Se obtienen las posiciones adyacentes vacias
					if(!lista.isEmpty()) { // Si hay posiciones adyacentes vacias...
						Nodo nuevaPosicion = lista.get(rand.nextInt(0, lista.size())); // ...se mueve a una posicion al azar
						
						// Se actualizan los nodos en el mapa
						this.mapaAmbiente.getNodo(nuevaPosicion.getId()).setEstado(EstadoEnum.ENEMIGO); // La posicion a la que se mueve tiene estado ENEMIGO
						this.mapaAmbiente.getNodo(e.getPosicion().getId()).setEstado(EstadoEnum.VACIO); // La posicion que deja tiene estado VACIO
					
						// Se actualiza la nueva posicion del enemigo y sus turnos detenido
						e.setPosicion(nuevaPosicion);
						e.setTurnosDetenido(0);
					}
				}
			}
		}
	}
	
	public ArrayList<Nodo> retornarPosicionesAdyacentesVacias(Enemigo e){
		ArrayList<Nodo> posicionesVacias = new ArrayList<Nodo>();
		for(Nodo nodo: e.getPosicion().getSucesores()) { // Se revisan 1 a 1 las posiciones adyacentes al enemigo...
			if(nodo.getEstado() == EstadoEnum.VACIO) { // ...y si estan vacias...
				posicionesVacias.add(nodo); // ...se agregan a la lista de posiciones vacias
			}
		}
		return posicionesVacias;
	}
	
	// Cada cierta cantidad de turnos/ciclos, el satelite se activa
	public void comportamientoSatelite() {
		if(satelite.getCiclosAEsperar() == satelite.getCiclosSinUsar()) { // Se puede usar
			satelite.setMapaSatelite(this.getMapaAmbiente()); // Se le setea el mapa del ambiente (actualizado)
			satelite.setEnemigosSatelite(this.getEnemigos()); // Se le setean los enemigos (actualizados)
			satelite.setPuntosRecargaSatelite(this.getPuntosRecarga()); // Se le setean los puntos de recarga (actualizados)
			satelite.setHabilitado(true);
			satelite.setCiclosSinUsar(0);
		} else { // No se puede usar
			satelite.setHabilitado(false);
			satelite.aumentarCiclosSinUsar();
		}
	}
	
	// Se actualizan los turnos desde la ultima vez que se uso el punto
	public void comportamientoPuntosRecarga() {
		for(PuntoRecarga pr: puntosRecarga) {
			pr.aumentarTurnosSinUsar();
		}
	}

	@Override
	public String toString() {
		String mensaje = "\n";
		mensaje += "Ubicación del agente: " + ubicacionAgente.getId() + "\n";
		mensaje += "Posición de los enemigos: [";
		for (Enemigo e: enemigos) {
			mensaje += " " + e.getId() + "/"+ e.getPosicion().getId().toString()+ ", ";
		}
		mensaje += "]\n";
		mensaje += "Puntos de recarga: [";
		for (PuntoRecarga p: puntosRecarga) {
			mensaje += " " + p.toString() + ", ";
		}
		mensaje += "]\n";
		mensaje += "Cantidad de enemigos: " + cantidadEnemigos + "\n";
		mensaje += "Mapa: [";
		for (Nodo nodo: mapaAmbiente.getMapa()) {
			mensaje += " " + nodo.getId() + "/"
					+ nodo.getEstado().toString() + ", ";
		}
		mensaje += "]\n";

		return mensaje;
	}

	// Getters y Setters
	public Mapa getMapaAmbiente() {
		return mapaAmbiente;
	}

	public void setMapaAmbiente(Mapa mapaAmbiente) {
		this.mapaAmbiente = mapaAmbiente;
	}

	public Nodo getUbicacionAgente() {
		return ubicacionAgente;
	}

	public void setUbicacionAgente(Nodo ubicacionAgente) {
		this.ubicacionAgente = ubicacionAgente;
	}

	public Double getEnergiaAgente() {
		return energiaAgente;
	}

	public void setEnergiaAgente(Double energiaAgente) {
		this.energiaAgente = energiaAgente;
	}

	public ArrayList<Enemigo> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(ArrayList<Enemigo> enemigos) {
		this.enemigos = enemigos;
	}

	public Integer getCantidadEnemigos() {
		return cantidadEnemigos;
	}

	public void setCantidadEnemigos(Integer cantidadEnemigos) {
		this.cantidadEnemigos = cantidadEnemigos;
	}

	public ArrayList<PuntoRecarga> getPuntosRecarga() {
		return puntosRecarga;
	}

	public void setPuntosRecarga(ArrayList<PuntoRecarga> puntosRecarga) {
		this.puntosRecarga = puntosRecarga;
	}

	public ArrayList<IdEnemigosEnum> getNombresUsados() {
		return nombresUsados;
	}

	public void setNombresUsados(ArrayList<IdEnemigosEnum> nombresUsados) {
		this.nombresUsados = nombresUsados;
	}

	public Satelite getSatelite() {
		return satelite;
	}

	public void setSatelite(Satelite satelite) {
		this.satelite = satelite;
	}
	
	

}
