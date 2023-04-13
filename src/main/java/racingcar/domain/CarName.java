package racingcar.domain;

public class CarName {

    private static final int CAR_NAME_LENGTH_MAX = 10;

    private final String name;

    public CarName(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String carName) {
        if (carName.isEmpty() || carName.isBlank()) {
            throw new IllegalArgumentException("자동차 이름을 입력해주세요.");
        }

        if (carName.length() > CAR_NAME_LENGTH_MAX) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하로 작성해주세요.");
        }
    }

    public String getName() {
        return name;
    }
}
