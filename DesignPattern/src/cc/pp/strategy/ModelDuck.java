package cc.pp.strategy;

public class ModelDuck extends Duck {

	public ModelDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Quack();
	}

	@Override
	void disp() {
		System.out.println("I'm a model duck");
	}

}
