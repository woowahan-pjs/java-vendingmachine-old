package study;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class Junit5Test {
    @Test
    public void test() {
        assertThat(true).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void parameterized_test(final int value) {
        assertThat(value).isEqualTo(value);
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> {
            throw new IllegalArgumentException();
        }).isExactlyInstanceOf(IllegalArgumentException.class);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            throw new IllegalArgumentException();
        });
    }
}
