import java.util.*;
import collections.Exercise;

public class CollectionsMain {
    public static void main(String... args) {
        // T0)
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(0);
        Exercise.pushFront(nums, 1);
        System.out.println(nums);

        // T1)
        System.out.println(Exercise.getAt(nums, 1));
        System.out.println(Exercise.getAt(nums, 2));

        // T2)
        nums.add(0);
        System.out.println(nums);
        Exercise.removeThird((ArrayList) nums);
        System.out.println(nums);

        // T3)
        System.out.println(Exercise.find(nums, 1));
        System.out.println(Exercise.find(nums, 2));

        // T4)

    }
}
