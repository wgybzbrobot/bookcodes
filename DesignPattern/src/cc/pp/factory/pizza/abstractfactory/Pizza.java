package cc.pp.factory.pizza.abstractfactory;

public abstract class Pizza {

	String name;

	Dough dough;
	Sauce sauce;
	Veggies veggies[];
	Cheese cheese;
	Pepperoni pepperoni;
	Clams clam;

	abstract void prepare();

	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	public void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}

	public void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuffer display = new StringBuffer();
		display.append("---- " + name + " ----\n");
		if (dough != null) {
			display.append(dough + "\n");
		}
		if (sauce != null) {
			display.append(sauce + "\n");
		}
		if (cheese != null) {
			display.append(cheese + "\n");
		}
		if (veggies != null) {
			for (int i = 0; i < veggies.length; i++) {
				display.append(veggies[i]);
				if (i < veggies.length - 1) {
					display.append(", ");
				}
			}
			display.append("\n");
		}
		if (clam != null) {
			display.append(clam + "\n");
		}
		if (pepperoni != null) {
			display.append(pepperoni + "\n");
		}

		return display.toString();
	}
}
