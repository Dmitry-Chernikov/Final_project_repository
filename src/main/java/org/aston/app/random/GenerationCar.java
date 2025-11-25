package org.aston.app.random;

import org.aston.app.model.Car;

public class GenerationCar {
    private RandomGeneration random;

    public GenerationCar(RandomGeneration random) {
        this.random = random;
    }

    public Car generationRandomCar() {
        return random.randomCar();
    }
}
