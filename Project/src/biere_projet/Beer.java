package biere_projet;
import biere_projet.MainActivity.Brand;

public class Beer {
	float unitPrice;
	int volume;
	Brand brand;
	float margin;
	Tank tank;
	
	Beer(Brand a_brand, int a_volume, Tank a_tank){
		brand = a_brand;
		volume = a_volume;
		tank = a_tank;

		DeterminePrice(a_brand, a_volume, a_tank.GetPrice());
	}
	
	public void Sell() {
		MainActivity.capital += unitPrice;
		MainActivity.capital = MainActivity.Round(MainActivity.capital);
		
		tank.GetBeers().remove(this);
		if(tank.GetBeers().size() <= 0) MainActivity.stock.remove(tank);
	}
	
	private void DeterminePrice(Brand a_brand, int a_nb, float a_tankPrice) {
		int nbOfBeers = 0;
		
		switch(a_brand) {
		case Orval:
			nbOfBeers = 6;
			margin = 2;
			break;
		case Leffe:
			nbOfBeers = 12;
			margin = 1.45f;
			break;
		case PaixDieu:
			nbOfBeers = 8;
			margin = 2;
			break;
		case Jupiler:
			nbOfBeers = 24;
			margin = 1.45f;
			break;
		}
		
		
		if(nbOfBeers != 0) {
			unitPrice = (a_tankPrice / nbOfBeers) * margin;
			unitPrice = MainActivity.Round(unitPrice);
		}
		else {
			System.out.print("nbOfBeers value is 0");
		}
	}
}
