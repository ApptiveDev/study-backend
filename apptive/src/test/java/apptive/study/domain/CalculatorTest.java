package apptive.study.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    @DisplayName("더하기 테스트")
    void 더하기_테스트() {
        // given (테스트 할 때 필요한 것들)
        int a = 6;
        int b = 4;
        Calculator calculator = new Calculator();

        // when (테스트)
        int result = calculator.plus(a,b);

        // then (테스트 결과 비교)
        assertThat(result).isEqualTo(10);
    }

    @Test
    void minus() {
        // given
        int a = 6;
        int b = 4;
        Calculator calculator = new Calculator();

        // when
        int result = calculator.minus(a,b);

        // then
        assertThat(result).isEqualTo(2);
    }
}