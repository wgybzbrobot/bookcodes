package cc.pp.strategy;

public class FakeQuack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("Qwak");
	}

}
