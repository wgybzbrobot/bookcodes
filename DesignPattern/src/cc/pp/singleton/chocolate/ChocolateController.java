package cc.pp.singleton.chocolate;

public class ChocolateController {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ChocolateBoiler boiler = ChocolateBoiler.getInstance();
		boiler.fill();
		boiler.boil();
		boiler.drain();

		ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();
	}

}
