package v.e.e.t.a.h.a;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Objects;

public class FSetService {

    static final int HEURISTIC_MIN_INT = -1000;
    static final int HEURISTIC_MAX_INT = 1001;

    public static <T> FSet<T> empty() {
        return suspect -> false;
    }

    public static <T>FSet<T> singletonSet(T val) {
        return suspect -> Objects.equals(suspect, val);
    }
    public static <T> FSet<T> union(FSet<T> self, FSet<T> other) {
        return suspect -> self.contains(suspect) || other.contains(suspect);
    }

    public static <T> FSet<T> intersect(FSet<T> self, FSet<T> other) {
        return suspect -> self.contains(suspect) && other.contains(suspect);
    }

    public static <T> FSet<T> diff(FSet<T> self, FSet<T> other) {
        return suspect -> self.contains(suspect) && !other.contains(suspect);
    }

    public static <T> FSet<T> filter(FSet<T> self, Predicate<T> shouldLeave) {
        return suspect -> self.contains(suspect) && shouldLeave.test(suspect);
    }

    public static boolean forAll(FSet<Integer> self, Predicate<Integer> check) {
        return new Predicate<Integer>() {
            public boolean test(Integer i) {
                return i >= HEURISTIC_MAX_INT || ((!self.contains(i) || check.test(i)) && this.test(i + 1));
            }
        }
        .test(HEURISTIC_MIN_INT);
    }

    public static <T> boolean exists(FSet<Integer> self, Predicate<Integer> check) {
        return !forAll(self, element -> !check.test(element));
    }


    public static <R> FSet<R> map(FSet<Integer> self, Function<Integer, R> convert) {
        return suspect -> exists(self, item -> Objects.equals(suspect, convert.apply(item)));
    }

    public static FSet<Integer> createIntRange(int min, int max) {
        return suspect -> min <= suspect && suspect < max;
    }


}

