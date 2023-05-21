package extras;

import java.util.ArrayList;
import java.util.Arrays;

public class Mapa {
	//Atributos
	private ArrayList<Nodo> mapa;
	private Nodo am1 = new Nodo();
	private Nodo am2 = new Nodo();
	private Nodo am3 = new Nodo();
	private Nodo am4 = new Nodo();
	private Nodo am5 = new Nodo();
	private Nodo am6 = new Nodo();
	private Nodo am7 = new Nodo();
	private Nodo am8 = new Nodo();
	private Nodo am9 = new Nodo();
	private Nodo am10 = new Nodo();
	private Nodo eu1 = new Nodo();
	private Nodo eu2 = new Nodo();
	private Nodo eu3 = new Nodo();
	private Nodo eu4 = new Nodo();
	private Nodo af1 = new Nodo();
	private Nodo af2 = new Nodo();
	private Nodo af3 = new Nodo();
	private Nodo af4 = new Nodo();
	private Nodo as1 = new Nodo();
	private Nodo as2 = new Nodo();
	private Nodo as3 = new Nodo();
	private Nodo as4 = new Nodo();
	private Nodo as5 = new Nodo();
	private Nodo as6 = new Nodo();
	private Nodo as7 = new Nodo();
	private Nodo as8 = new Nodo();
	private Nodo oc1 = new Nodo();
	private Nodo oc2 = new Nodo();
	private Nodo fin = new Nodo();
	
	//Constructor
	public Mapa() {
		super();
		mapa = new ArrayList<>();
		inicializarNodos();
		cargarSucesores();
		cargarMapa();
	}
	
	public Mapa(Mapa map) {
		this.mapa = (ArrayList<Nodo>) map.getMapa().clone();
		this.am1 = map.am1.clone();
		this.am2 = map.am2.clone();
		this.am3 = map.am3.clone();
		this.am4 = map.am4.clone();
		this.am5 = map.am5.clone();
		this.am6 = map.am6.clone();
		this.am7 = map.am7.clone();
		this.am8 = map.am8.clone();
		this.am9 = map.am9.clone();
		this.am10 = map.am10.clone();
		this.eu1 = map.eu1.clone();
		this.eu2 = map.eu2.clone();
		this.eu3 = map.eu3.clone();
		this.eu4 = map.eu4.clone();
		this.af1 = map.af1.clone();
		this.af2 = map.af2.clone();
		this.af3 = map.af3.clone();
		this.af4 = map.af4.clone();
		this.as1 = map.as1.clone();
		this.as2 = map.as2.clone();
		this.as3 = map.as3.clone();
		this.as4 = map.as4.clone();
		this.as5 = map.as5.clone();
		this.as6 = map.as6.clone();
		this.as7 = map.as7.clone();
		this.as8 = map.as8.clone();
		this.oc1 = map.oc1.clone();
		this.oc2 = map.oc2.clone();
		this.fin = map.fin.clone();
	}
	
	//Metodos
	public void inicializarNodos() {
		am1.setEstado(EstadoEnum.DESCONOCIDO);
		am1.setId(IdNodoEnum.AM1);
		am2.setEstado(EstadoEnum.DESCONOCIDO);
		am2.setId(IdNodoEnum.AM2);
		am3.setEstado(EstadoEnum.DESCONOCIDO);
		am3.setId(IdNodoEnum.AM3);
		am4.setEstado(EstadoEnum.DESCONOCIDO);
		am4.setId(IdNodoEnum.AM4);
		am5.setEstado(EstadoEnum.DESCONOCIDO);
		am5.setId(IdNodoEnum.AM5);
		am6.setEstado(EstadoEnum.DESCONOCIDO);
		am6.setId(IdNodoEnum.AM6);
		am7.setEstado(EstadoEnum.DESCONOCIDO);
		am7.setId(IdNodoEnum.AM7);
		am8.setEstado(EstadoEnum.DESCONOCIDO);
		am8.setId(IdNodoEnum.AM8);
		am9.setEstado(EstadoEnum.DESCONOCIDO);
		am9.setId(IdNodoEnum.AM9);
		am10.setEstado(EstadoEnum.DESCONOCIDO);
		am10.setId(IdNodoEnum.AM10);
		eu1.setEstado(EstadoEnum.DESCONOCIDO);
		eu1.setId(IdNodoEnum.EU1);
		eu2.setEstado(EstadoEnum.DESCONOCIDO);
		eu2.setId(IdNodoEnum.EU2);
		eu3.setEstado(EstadoEnum.DESCONOCIDO);
		eu3.setId(IdNodoEnum.EU3);
		eu4.setEstado(EstadoEnum.DESCONOCIDO);
		eu4.setId(IdNodoEnum.EU4);
		af1.setEstado(EstadoEnum.DESCONOCIDO);
		af1.setId(IdNodoEnum.AF1);
		af2.setEstado(EstadoEnum.DESCONOCIDO);
		af2.setId(IdNodoEnum.AF2);
		af3.setEstado(EstadoEnum.DESCONOCIDO);
		af3.setId(IdNodoEnum.AF3);
		af4.setEstado(EstadoEnum.DESCONOCIDO);
		af4.setId(IdNodoEnum.AF4);
		as1.setEstado(EstadoEnum.DESCONOCIDO);
		as1.setId(IdNodoEnum.AS1);
		as2.setEstado(EstadoEnum.DESCONOCIDO);
		as2.setId(IdNodoEnum.AS2);
		as3.setEstado(EstadoEnum.DESCONOCIDO);
		as3.setId(IdNodoEnum.AS3);
		as4.setEstado(EstadoEnum.DESCONOCIDO);
		as4.setId(IdNodoEnum.AS4);
		as5.setEstado(EstadoEnum.DESCONOCIDO);
		as5.setId(IdNodoEnum.AS5);
		as6.setEstado(EstadoEnum.DESCONOCIDO);
		as6.setId(IdNodoEnum.AS6);
		as7.setEstado(EstadoEnum.DESCONOCIDO);
		as7.setId(IdNodoEnum.AS7);
		as8.setEstado(EstadoEnum.DESCONOCIDO);
		as8.setId(IdNodoEnum.AS8);
		oc1.setEstado(EstadoEnum.DESCONOCIDO);
		oc1.setId(IdNodoEnum.OC1);
		oc2.setEstado(EstadoEnum.DESCONOCIDO);
		oc2.setId(IdNodoEnum.OC2);
		fin.setEstado(EstadoEnum.DESCONOCIDO);
		fin.setId(IdNodoEnum.FIN);
	}
	
	public void cargarSucesores() {
		am1.addSucesor(am2);
		am1.addSucesor(am4);
		am2.addSucesor(am3);
		am2.addSucesor(am1);
		am3.addSucesor(am2);
		am3.addSucesor(am4);
		am4.addSucesor(am3);
		am4.addSucesor(am5);
		am4.addSucesor(am1);
		am4.addSucesor(af1);
		am5.addSucesor(am4);
		am5.addSucesor(am6);
		am5.addSucesor(am5);
		am5.addSucesor(am7);
		am6.addSucesor(am5);
		am6.addSucesor(af1);
		am7.addSucesor(am5);
		am7.addSucesor(am9);
		am7.addSucesor(am8);
		am8.addSucesor(am7);
		am9.addSucesor(am7);
		am9.addSucesor(af1);
		am9.addSucesor(eu1);
		am9.addSucesor(am10);
		am10.addSucesor(am9);
		am10.addSucesor(eu1);
		am10.addSucesor(eu2);
		eu1.addSucesor(am9);
		eu1.addSucesor(am10);
		eu1.addSucesor(eu3);
		eu2.addSucesor(am10);
		eu2.addSucesor(eu4);
		eu3.addSucesor(eu1);
		eu3.addSucesor(af3);
		eu3.addSucesor(eu4);
		eu4.addSucesor(eu2);
		eu4.addSucesor(eu3);
		eu4.addSucesor(af3);
		eu4.addSucesor(as2);
		eu4.addSucesor(as7);
		af1.addSucesor(am9);
		af1.addSucesor(am6);
		af1.addSucesor(am4);
		af1.addSucesor(af2);
		af2.addSucesor(af1);
		af2.addSucesor(af3);
		af3.addSucesor(af4);
		af3.addSucesor(af2);
		af3.addSucesor(eu3);
		af3.addSucesor(eu4);
		af4.addSucesor(af3);
		af4.addSucesor(fin);
		as1.addSucesor(as2);
		as1.addSucesor(fin);
		as2.addSucesor(as1);
		as2.addSucesor(eu4);
		as2.addSucesor(as3);
		as2.addSucesor(as5);
		as3.addSucesor(as2);
		as3.addSucesor(oc1);
		as4.addSucesor(as5);
		as4.addSucesor(oc2);
		as5.addSucesor(as6);
		as5.addSucesor(as2);
		as5.addSucesor(as4);
		as6.addSucesor(as5);
		as6.addSucesor(as7);
		as7.addSucesor(as6);
		as7.addSucesor(as8);
		as7.addSucesor(eu4);
		as8.addSucesor(as7);
		oc1.addSucesor(as3);
		oc1.addSucesor(oc2);
		oc1.addSucesor(fin);
		oc2.addSucesor(oc1);
		oc2.addSucesor(as4);
		fin.addSucesor(af4);
		fin.addSucesor(as1);
		fin.addSucesor(oc1);
	}
	
	public void cargarMapa() {
		mapa.addAll(Arrays.asList(am1, am2, am3, am4, am5, am6, am7, am8, am9, am10, eu1, eu2, eu3, eu4, af1, af2, af3,
				af4, oc1, oc2, as1, as2, as3, as4, as5, as6, as7, as8, fin));
	}
	
	public Nodo getNodo(IdNodoEnum idNodo) {
		for(Nodo nodo: mapa) {
			if(nodo.getId() == idNodo){
				return nodo;
			}
		}
		return null;
	}
	
	public Nodo getNodo(Integer posicion) {
		return mapa.get(posicion);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) {
			Mapa mapa = (Mapa) obj;
			if(this.mapa.containsAll(mapa.getMapa())
					&& mapa.getMapa().containsAll(this.mapa)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public Mapa clone() {
		Mapa map = new Mapa(this);
		return map;
	}
	
	//Getters y Setters
	public ArrayList<Nodo> getMapa() {
		return mapa;
	}

	public void setMapa(ArrayList<Nodo> mapa) {
		this.mapa = mapa;
	}

	

}
