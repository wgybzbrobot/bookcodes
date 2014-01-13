package cc.pp.strategy;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//		MallardDuck mallard = new MallardDuck();
		//		RubberDuck rubber = new RubberDuck();
		//		DecoyDuck decoy = new DecoyDuck();
		//		ModelDuck model = new ModelDuck();
		//
		//		mallard.performQuack();
		//		rubber.performQuack();
		//		decoy.performQuack();
		//
		//		model.performFly();
		//		model.setFlyBehavior(new FlyRocketPowered());
		//		model.performFly();

		Duck mallard = new MallardDuck();
		mallard.performFly();
		mallard.performQuack();

		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();

	}

}
