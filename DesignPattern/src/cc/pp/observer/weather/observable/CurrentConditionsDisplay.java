package cc.pp.observer.weather.observable;

import java.util.Observable;
import java.util.Observer;

import cc.pp.observer.weather.observable.DisplayElement;
import cc.pp.observer.weather.observable.WeatherData;
	
public class CurrentConditionsDisplay implements Observer, DisplayElement {

	private float temperature;
	private float humidity;
	
	public CurrentConditionsDisplay(Observable observable) {
		observable.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof WeatherData) {
			WeatherData weatherData = (WeatherData) o;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
	}
	
	@Override
	public void display() {
		System.out.println("Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity");
	}
}
