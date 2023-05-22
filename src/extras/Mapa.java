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
	
	public Mapa(ArrayList<Nodo> map, Nodo n1, Nodo n2, Nodo n3, Nodo n4, Nodo n5, Nodo n6, Nodo n7, Nodo n8, Nodo n9,
			Nodo n10, Nodo n11, Nodo n12, Nodo n13, Nodo n14, Nodo n15, Nodo n16, Nodo n17, Nodo n18, Nodo n19, Nodo n20,
			Nodo n21, Nodo n22, Nodo n23, Nodo n24, Nodo n25, Nodo n26, Nodo n27, Nodo n28, Nodo n29) {
		this.mapa = map;
		this.am1 = n1;
		this.am2 = n2;
		this.am3 = n3;
		this.am4 = n4;
		this.am5 = n5;
		this.am6 = n6;
		this.am7 = n7;
		this.am8 = n8;
		this.am9 = n9;
		this.am10 = n10;
		this.eu1 = n11;
		this.eu2 = n12;
		this.eu3 = n13;
		this.eu4 = n14;
		this.af1 = n15;
		this.af2 = n16;
		this.af3 = n17;
		this.af4 = n18;
		this.as1 = n19;
		this.as2 = n20;
		this.as3 = n21;
		this.as4 = n22;
		this.as5 = n23;
		this.as6 = n24;
		this.as7 = n25;
		this.as8 = n26;
		this.oc1 = n27;
		this.oc2 = n28;
		this.fin = n29;
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
		Mapa map = new Mapa();
		ArrayList<Nodo> nuevoMapa = new ArrayList<Nodo>();
		for(Nodo nodo: mapa) {
			nuevoMapa.add(nodo.clone());
		}
		map.setMapa(nuevoMapa);
		map.setAm1(am1);
		map.setAm2(am2);
		map.setAm3(am3);
		map.setAm4(am4);
		map.setAm5(am5);
		map.setAm6(am6);
		map.setAm7(am7);
		map.setAm8(am8);
		map.setAm9(am9);
		map.setAm10(am10);
		map.setEu1(eu1);
		map.setEu2(eu2);
		map.setEu3(eu3);
		map.setEu4(eu4);
		map.setAf1(af1);
		map.setAf2(af2);
		map.setAf3(af3);
		map.setAf4(af4);
		map.setAs1(as1);
		map.setAs2(as2);
		map.setAs3(as3);
		map.setAs4(as4);
		map.setAs5(as5);
		map.setAs6(as6);
		map.setAs7(as7);
		map.setAs8(as8);
		map.setOc1(oc1);
		map.setOc2(oc2);
		map.setFin(fin);
		return map;
	}
	
	//Getters y Setters
	public ArrayList<Nodo> getMapa() {
		return mapa;
	}

	public void setMapa(ArrayList<Nodo> mapa) {
		this.mapa = mapa;
	}

	public Nodo getAm1() {
		return am1;
	}

	public void setAm1(Nodo am1) {
		this.am1 = am1;
	}

	public Nodo getAm2() {
		return am2;
	}

	public void setAm2(Nodo am2) {
		this.am2 = am2;
	}

	public Nodo getAm3() {
		return am3;
	}

	public void setAm3(Nodo am3) {
		this.am3 = am3;
	}

	public Nodo getAm4() {
		return am4;
	}

	public void setAm4(Nodo am4) {
		this.am4 = am4;
	}

	public Nodo getAm5() {
		return am5;
	}

	public void setAm5(Nodo am5) {
		this.am5 = am5;
	}

	public Nodo getAm6() {
		return am6;
	}

	public void setAm6(Nodo am6) {
		this.am6 = am6;
	}

	public Nodo getAm7() {
		return am7;
	}

	public void setAm7(Nodo am7) {
		this.am7 = am7;
	}

	public Nodo getAm8() {
		return am8;
	}

	public void setAm8(Nodo am8) {
		this.am8 = am8;
	}

	public Nodo getAm9() {
		return am9;
	}

	public void setAm9(Nodo am9) {
		this.am9 = am9;
	}

	public Nodo getAm10() {
		return am10;
	}

	public void setAm10(Nodo am10) {
		this.am10 = am10;
	}

	public Nodo getEu1() {
		return eu1;
	}

	public void setEu1(Nodo eu1) {
		this.eu1 = eu1;
	}

	public Nodo getEu2() {
		return eu2;
	}

	public void setEu2(Nodo eu2) {
		this.eu2 = eu2;
	}

	public Nodo getEu3() {
		return eu3;
	}

	public void setEu3(Nodo eu3) {
		this.eu3 = eu3;
	}

	public Nodo getEu4() {
		return eu4;
	}

	public void setEu4(Nodo eu4) {
		this.eu4 = eu4;
	}

	public Nodo getAf1() {
		return af1;
	}

	public void setAf1(Nodo af1) {
		this.af1 = af1;
	}

	public Nodo getAf2() {
		return af2;
	}

	public void setAf2(Nodo af2) {
		this.af2 = af2;
	}

	public Nodo getAf3() {
		return af3;
	}

	public void setAf3(Nodo af3) {
		this.af3 = af3;
	}

	public Nodo getAf4() {
		return af4;
	}

	public void setAf4(Nodo af4) {
		this.af4 = af4;
	}

	public Nodo getAs1() {
		return as1;
	}

	public void setAs1(Nodo as1) {
		this.as1 = as1;
	}

	public Nodo getAs2() {
		return as2;
	}

	public void setAs2(Nodo as2) {
		this.as2 = as2;
	}

	public Nodo getAs3() {
		return as3;
	}

	public void setAs3(Nodo as3) {
		this.as3 = as3;
	}

	public Nodo getAs4() {
		return as4;
	}

	public void setAs4(Nodo as4) {
		this.as4 = as4;
	}

	public Nodo getAs5() {
		return as5;
	}

	public void setAs5(Nodo as5) {
		this.as5 = as5;
	}

	public Nodo getAs6() {
		return as6;
	}

	public void setAs6(Nodo as6) {
		this.as6 = as6;
	}

	public Nodo getAs7() {
		return as7;
	}

	public void setAs7(Nodo as7) {
		this.as7 = as7;
	}

	public Nodo getAs8() {
		return as8;
	}

	public void setAs8(Nodo as8) {
		this.as8 = as8;
	}

	public Nodo getOc1() {
		return oc1;
	}

	public void setOc1(Nodo oc1) {
		this.oc1 = oc1;
	}

	public Nodo getOc2() {
		return oc2;
	}

	public void setOc2(Nodo oc2) {
		this.oc2 = oc2;
	}

	public Nodo getFin() {
		return fin;
	}

	public void setFin(Nodo fin) {
		this.fin = fin;
	}
	
	

	

}
