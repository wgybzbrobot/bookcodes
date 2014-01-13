package cc.pp.adapter.ducks;

public class TurkeyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MallardDuck duck = new MallardDuck();
		Turkey duckAdapter = new DuckAdapter(duck);

		for (int i = 0; i < 5; i++) {
			System.out.println("The DuckAdapter says ...");
			duckAdapter.gobble();
			duckAdapter.fly();
		}
	}

}
