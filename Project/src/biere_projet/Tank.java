package biere_projet;

import java.util.ArrayList;
import java.util.List;

import biere_projet.MainActivity.Brand;

public class Tank {
	private List<Beer> beers = new ArrayList<Beer>();
	private float price;
	private int volume;
	private Brand brand;
	
	Tank(Brand a_brand, int a_volume) { 
		int nbOfBeers = 0;
		brand = a_brand;
		volume = a_volume;
		
		switch(a_brand) {
		case Orval:
			nbOfBeers = 6;
			OrvalPrice(a_volume);
			break;
		case Leffe:
			nbOfBeers = 12;
			LeffePrice(a_volume);
			break;
		case PaixDieu:
			nbOfBeers = 8;
			PaixDieuPrice(a_volume);
			break;
		case Jupiler:
			nbOfBeers = 24;
			JupilerPrice(a_volume);
			break;
		}
		
		for(int i = 0; i< nbOfBeers;i++) {
			Beer newBeer = new Beer(a_brand, a_volume, this);
			beers.add(newBeer);
		}
	}
	
	public List<Beer> GetBeers() {return beers;}
	
	public float GetPrice() { return price; }
	
	public Brand GetBrand() {return brand;}
	
	public int GetVolume() {return volume;}
	
	public void Buy() {
		MainActivity.capital -= price;
		MainActivity.stock.add(this);
	}
	
	public void Show(Boolean a_showPrice) {
		String toShow = brand.toString() + " " + volume + "cl x" + beers.size();
		
		if(a_showPrice) toShow += " Unit price: " + beers.get(0).unitPrice + "€\n";
		else toShow += "\n";
		
		System.out.print(toShow);
	}
	
	private void JupilerPrice(int a_volume) {
		switch(a_volume) {
		case 25:
			price = 10f;
			break;
		case 33:
			price = 14f;
			break;
		case 50:
			price = 21.2f;
			break;
		}	
	}

	private void PaixDieuPrice(int a_volume) {
		switch(a_volume) {
		case 25:
			price = 17.6f;
			break;
		case 33:
			price = 24f;
			break;
		case 50:
			price = 40f;
			break;
		}			
	}

	private void LeffePrice(int a_volume) {
		switch(a_volume) {
		case 25:
			price = 15.3f;
			break;
		case 33:
			price = 24f;
			break;
		case 50:
			price = 36f;
			break;
		}		
	}

	private void OrvalPrice(int a_volume) {
		switch(a_volume) {
		case 25:
			price = 10.8f;
			break;
		case 33:
			price = 15f;
			break;
		case 50:
			price = 22f;
			break;
		}	
		
	}

	
}
