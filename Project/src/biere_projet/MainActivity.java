package biere_projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class MainActivity {
	static float capital = 200;
	static List<Tank> stock = new ArrayList<Tank>();
	static Boolean run = true;

	/*
	 * @SuppressWarnings("serial") public static Map<Beer, Float> map = new
	 * HashMap<Beer, Float>(){{ put(new Beer(Brand.Orval, 25), 10.8f); put(new
	 * Beer(Brand.Orval, 33), 15f); put(new Beer(Brand.Orval, 50), 22f);
	 * 
	 * put(new Beer(Brand.Leffe, 25), 15.3f); put(new Beer(Brand.Leffe, 33), 24f);
	 * put(new Beer(Brand.Leffe, 50), 36f);
	 * 
	 * put(new Beer(Brand.PaixDieu, 25), 17.6f); put(new Beer(Brand.PaixDieu, 33),
	 * 24f); put(new Beer(Brand.PaixDieu, 50), 40f);
	 * 
	 * put(new Beer(Brand.Jupiler, 25), 10f); put(new Beer(Brand.Jupiler, 33), 14f);
	 * put(new Beer(Brand.Jupiler, 50), 21.2f); }};
	 */

	static void CLS() {
		System.out.println(new String(new char[10]).replace("\0", "\r\n"));
	}

	public static void main(String[] args) {
		StartingStock();

		do
			MainMenu();
		while (run);
	}

	static void StartingStock() {
		stock.add(new Tank(Brand.Jupiler, 33));
		stock.add(new Tank(Brand.Jupiler, 33));

		stock.add(new Tank(Brand.PaixDieu, 25));
		stock.add(new Tank(Brand.PaixDieu, 25));
		stock.add(new Tank(Brand.PaixDieu, 25));

		stock.add(new Tank(Brand.PaixDieu, 50));

		stock.add(new Tank(Brand.Orval, 33));

		stock.add(new Tank(Brand.Orval, 25));
	}

	@SuppressWarnings("resource")
	static void MainMenu() {
		int choice;

		do {
			DisplayChoices();
			choice = new Scanner(System.in).nextInt();
		} while (choice < 1 || choice > 5);

		Redirect(choice);
	}

	static void DisplayChoices() {
		System.out.println("What do you wanna do, Tony?");
		System.out.println("1. Buy a new tank of beers");
		System.out.println("2. View stocks and volumes");
		System.out.println("3. See current assets");
		System.out.println("4. Sell beer");
		System.out.println("5. Quit application");
	}

	static void Redirect(int choice) {
		switch (choice) {
		case 1:
			BuyTank();
			break;
		case 2:
			ShowStock(false);
			break;
		case 3:
			SeeAssets();
			break;
		case 4:
			SellBeers();
			break;
		case 5:
			Quit();
			break;
		}
	}

	@SuppressWarnings("resource")
	static void BuyTank() {
		System.out.println("What kind of tank do you want to buy?");

		int choice;

		do {
			System.out.println("1. Leffe\n2. Jupiler\n3. Paix dieu\n4. Orval");
			choice = new Scanner(System.in).nextInt();
		} while (choice < 1 || choice > 4);

		Brand brand = Brand.values()[choice - 1];

		do {
			System.out.println("1. 25 cl\n2. 33 cl\n3. 50 cl\n");
			choice = new Scanner(System.in).nextInt();
		} while (choice < 1 || choice > 3);

		int volumes[] = { 25, 33, 50 };

		AddBeerTank(brand, volumes[choice - 1]);
	}

	static void AddBeerTank(Brand brand, int volume) {
		Tank newTank = new Tank(brand, volume);

		float tankPrice = newTank.GetPrice();
		
		if (capital >= tankPrice) {
			stock.add(newTank);
			capital -= tankPrice;
		} else {
			System.out.println("Not enough money... You have " + capital + "€ whereas it costs " + tankPrice + "€");
		}

	}

	static void ShowStock(Boolean toSell) {
		for (int i = 0; i < stock.size(); i++) {
			Tank tank = stock.get(i);
			if (toSell)
				System.out.print((i + 1) + ":");
			tank.Show(toSell);
		}
	}

	static void SeeAssets() {
		System.out.println("You have " + capital + "€.");
	}

	@SuppressWarnings("resource")
	static void SellBeers() {
		ShowStock(true);
		int choice = 0;

		do {
			System.out.println("Which beer do you want to sell, Tony?");
			choice = new Scanner(System.in).nextInt();
		} while (choice < 1 || choice > stock.size());

		stock.get(choice - 1).GetBeers().get(0).Sell();
	}

	static void Quit() {
		run = false;
	}

	public static float Round(float v) {
		return (float) (Math.round(v * 100.0) / 100.0);
	}

	enum Brand {
		Leffe, Jupiler, PaixDieu, Orval
	}
}
