package com.yanovski.exchangeapi.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Util class
 */
public class ApiUtils {
    /**
     * Turns varargs into a map.
     *
     * @param keyType key class
     * @param valueType value class
     * @param entries varargs
     * @param <K> key object
     * @param <V> value object
     * @return map
     */
    public static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}
