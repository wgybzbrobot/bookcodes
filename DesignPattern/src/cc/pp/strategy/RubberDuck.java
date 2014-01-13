package cc.pp.strategy;

public class RubberDuck extends Duck {

	public RubberDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Squeak();
	}

	@Override
	void disp() {
		System.out.println("I'm a rubber duckie");
	}

}
