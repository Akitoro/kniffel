package de.akitoro.kniffel.combinations;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullHouseCombinationTest {

    @Test
    void test() {
        Combination fullCombination = new FullHouseCombination();

        Map<Integer, Integer> resultMap = new HashMap<>();
        resultMap.put(1, 2);
        resultMap.put(3, 3);

        assertEquals(25, fullCombination.points(resultMap));
    }

}
