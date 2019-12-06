package v.e.e.t.a.h.a;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import v.e.e.t.a.h.a.FSetService;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FSetService")
public class FSetServiceTest {
  

    @Test
    void empty() {
        FSet<Integer> set = FSetService.empty();
        assertFalse(set.contains(-1));
        assertFalse(set.contains(0));
        assertFalse(set.contains(32));
    }

    @Test
    void singletonSet() {
        var strs = FSetService.singletonSet("hello");
        assertTrue(strs.contains("hello"));
        assertFalse(strs.contains("hallo"));

        var ints = FSetService.singletonSet(1);
        assertTrue(ints.contains(1));
        assertFalse(ints.contains(2));
    }

    @Test
    void union() {
        var set = FSetService.union(
            FSetService.createIntRange(1, 11), 
            FSetService.createIntRange(5, 16)
        );
        assertFalse(set.contains(16));
        assertFalse(set.contains(0));
        assertTrue(set.contains(3));
        assertTrue(set.contains(8));
        assertTrue(set.contains(13));
    }

    @Test
    void intersect() {
        var set = FSetService.intersect(
            FSetService.createIntRange(1, 11),
            FSetService.createIntRange(5, 16)
        );
        assertFalse(set.contains(16));
        assertFalse(set.contains(0));
        assertFalse(set.contains(3));
        assertFalse(set.contains(13));
        assertTrue(set.contains(8));
    }

    @Test
    void diff() {
        FSet<Integer> pfsInt3 = FSetService.diff(
            FSetService.createIntRange(1, 11),
            FSetService.createIntRange(5, 16)
        );
        assertFalse(pfsInt3.contains(16));
        assertFalse(pfsInt3.contains(0));
        assertFalse(pfsInt3.contains(13));
        assertFalse(pfsInt3.contains(8));
        assertTrue(pfsInt3.contains(3));
    }

    @Test
    void filter() {
        var set = FSetService.filter(
            FSetService.createIntRange(1, 11),
            x -> x % 2 == 0
        );

        assertFalse(set.contains(1));
        assertFalse(set.contains(3));
        assertFalse(set.contains(5));
        assertFalse(set.contains(7));
        assertFalse(set.contains(9));

        assertTrue(set.contains(2));
        assertTrue(set.contains(4));
        assertTrue(set.contains(6));
        assertTrue(set.contains(8));
        assertTrue(set.contains(10));
    }

    @Test
    void forAll() {
        var set = FSetService.createIntRange(1, 101);

        assertFalse(FSetService.forAll(set, x -> x < 0));
        assertFalse(FSetService.forAll(set, x -> x % 5 <= 1));
        assertTrue(FSetService.forAll(set, x -> x > 0));
        assertTrue(FSetService.forAll(set, x -> x <= 100));
    }

    @Test
    void exists() {
        var set = FSetService.createIntRange(1, 11);

        assertFalse(FSetService.exists(set, x -> x < 0));
        assertFalse(FSetService.exists(set, x -> x > 10));
        assertTrue(FSetService.exists(set, x -> x > 9));
        assertTrue(FSetService.exists(set, x -> x % 5 <= 1));
    }

    @Test
    void map() {
        var set = FSetService.map(
            FSetService.createIntRange(1, 11),
            x -> 2 * x
        );

        assertFalse(set.contains(1));
        assertFalse(set.contains(3));
        assertFalse(set.contains(19));
        assertFalse(set.contains(21));

        assertTrue(set.contains(2));
        assertTrue(set.contains(4));
        assertTrue(set.contains(20));
    }

}
