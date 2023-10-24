package pl.ksr.logic.set;

import java.io.Serializable;
import java.util.function.Function;

public interface SerializableFunction<T, Double> extends Function<T, Double>, Serializable {

}