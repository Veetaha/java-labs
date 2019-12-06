package v.e.e.t.a.h.a;

public class Main {

    public static void main(String[] args) {

        var set = FSetService.union(
            FSetService.singletonSet(5), FSetService.union(
            FSetService.singletonSet(42),
            FSetService.singletonSet(8)
        ));

        System.out.println(set.contains(42));
        System.out.println(set.contains(5));
        System.out.println(set.contains(8));
        System.out.println(set.contains(-1));
        System.out.println(set.contains(100));
    }
}

