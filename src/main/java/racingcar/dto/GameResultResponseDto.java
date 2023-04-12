package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Cars;

public class GameResultResponseDto {

    private List<String> winners;
    private List<CarStatusResponseDto> racingCars;

    private GameResultResponseDto(final List<String> winners, final List<CarStatusResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResultResponseDto toDto(final List<String> winners, final Cars cars) {
        List<CarStatusResponseDto> carStatuses = cars.getCars().stream()
                .map(CarStatusResponseDto::toDto)
                .collect(Collectors.toList());

        return new GameResultResponseDto(winners, carStatuses);
    }

    public boolean isWinner(final String name) {
        return this.winners.contains(name);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarStatusResponseDto> getRacingCars() {
        return racingCars;
    }
}
