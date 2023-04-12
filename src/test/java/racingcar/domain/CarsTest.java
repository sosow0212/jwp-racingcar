package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.util.RandomFailPowerMaker;
import racingcar.util.RandomSuccessPowerMaker;

class CarsTest {

    private final RandomSuccessPowerMaker randomSuccessPowerMaker = new RandomSuccessPowerMaker();
    private final RandomFailPowerMaker randomFailPowerMaker = new RandomFailPowerMaker();

    @Test
    @DisplayName("validate() : 자동차 명이 중복이면 에러가 터진다.")
    void test_validate() {
        // given
        String jay = "jay";
        Car car = new Car(jay, 0);
        List<Car> cars = List.of(car, car);

        // when & then
        assertThatThrownBy(() -> new Cars(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 중복되지 않아야합니다.");
    }

    @Test
    @DisplayName("moveAll() : 자동차가 모두 움직인다.")
    void test_moveAll_success() {
        // given
        int defaultDistance = 0;
        int expectedDistanceAfterMoveSuccess = defaultDistance + 1;

        Car pobi = new Car("pobi", defaultDistance);
        Car crong = new Car("crong", defaultDistance);
        Cars cars = new Cars(List.of(pobi, crong));

        // when
        cars.moveAll(randomSuccessPowerMaker);

        // then
        assertThat(pobi.getDistance()).isEqualTo(expectedDistanceAfterMoveSuccess);
        assertThat(crong.getDistance()).isEqualTo(expectedDistanceAfterMoveSuccess);
    }

    @Test
    @DisplayName("moveAll() : 자동차가 모두 움직이지 않는다.")
    void test_moveAll_fail() {
        // given
        int defaultDistance = 0;
        int expectedDistanceAfterMoveSuccess = defaultDistance + 1;

        Car pobi = new Car("pobi", defaultDistance);
        Car crong = new Car("crong", defaultDistance);
        Cars cars = new Cars(List.of(pobi, crong));

        // when
        cars.moveAll(randomFailPowerMaker);

        // then
        assertThat(pobi.getDistance()).isNotEqualTo(expectedDistanceAfterMoveSuccess);
        assertThat(crong.getDistance()).isNotEqualTo(expectedDistanceAfterMoveSuccess);
    }

    @Test
    @DisplayName("test_getWinnerNames() : 가장 많이 움직인 자동차를 반환해준다.")
    void test_getWinnerNames() {
        // given
        Car pobi = new Car("pobi", 2);
        Car crong = new Car("crong", 1);

        Cars cars = new Cars(List.of(pobi, crong));

        // when
        List<String> winnerNames = cars.getWinnerNames();

        // then
        assertThat(winnerNames).containsOnly(pobi.getCarName());
        assertThat(winnerNames.size()).isEqualTo(1);
        assertThat(winnerNames).doesNotContain(crong.getCarName());
    }
}
