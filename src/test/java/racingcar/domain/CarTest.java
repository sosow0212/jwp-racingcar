package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    @ParameterizedTest
    @ValueSource(strings = {"4", "5", "6"})
    @DisplayName("move() : 자동차의 이동 성공 테스트")
    void test_move_success(int givenPower) {
        // given
        int givenDistance = 0;
        int expectedDistance = givenDistance + 1;
        Car car = new Car("pobi", givenDistance);

        // when
        car.move(givenPower);

        // then
        assertThat(car.getDistance()).isEqualTo(expectedDistance);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "2", "3"})
    @DisplayName("move() : 자동차 이동 실패 테스트")
    void test_move_fail(int givenPower) {
        // given
        int givenDistance = 0;
        int expectedDistanceAfterMoveSuccess = givenDistance + 1;
        Car car = new Car("pobi", givenDistance);

        // when
        car.move(givenPower);

        // then
        assertThat(car.getDistance()).isNotEqualTo(expectedDistanceAfterMoveSuccess);
    }
}
