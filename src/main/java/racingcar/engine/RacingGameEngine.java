package racingcar.engine;

import racingcar.console.InputView;
import racingcar.console.OutputView;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import java.util.InputMismatchException;
import racingcar.utils.CarsFactory;
import racingcar.utils.RandomPowerGenerator;
import racingcar.utils.RandomPowerMaker;

public class RacingGameEngine {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final RandomPowerGenerator randomPowerGenerator = new RandomPowerMaker();

    public void startGame() {

        final Cars cars = getCars();
        final TryCount tryCount = getTryCount();

        startRace(cars, tryCount);

        outputView.printWinners(cars);
    }

    private Cars getCars() {
        outputView.requestOfCarNames();

        try {
            final String[] carNames = inputView.inputCarNames();
            Cars cars = CarsFactory.createCars(carNames);
            return cars;
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getCars();
        }
    }

    private TryCount getTryCount() {
        outputView.requestOfTryCount();

        try {
            int input = inputView.inputTryCount();
            TryCount tryCount = new TryCount(input);
            return tryCount;
        } catch (IllegalArgumentException | InputMismatchException exception) {
            System.out.println(exception.getMessage());
            return getTryCount();
        }
    }

    private void startRace(Cars cars, TryCount tryCount) {
        outputView.printResultMessage();

        for (int i = 0; i < tryCount.getTryCount(); i++) {
            cars.moveAll(randomPowerGenerator);
            outputView.printCurrentRacingStatus(cars);
        }
    }
}
