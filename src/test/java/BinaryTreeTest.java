import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class BinaryTreeTest {
    private final int[] treeNumbers;
    private final List<String> ways;

    public BinaryTreeTest(int[] treeNumbers, List<String> ways) {
        this.treeNumbers = treeNumbers;
        this.ways = ways;
    }

    @Parameterized.Parameters
    public static List<Object[]> cases() {
        return Arrays.asList(new Object[][]{
                {new int[] {20, 10, 5, 15, 2, 8, 12, 18, 71}, List.of("R")},
                {new int[] {}, null},
                {new int[] {20}, null},
                {new int[] {20, 10, 30}, List.of("R")},
                {new int[] {10, 5, 7, 12}, List.of("R", "L")},
                {new int[] {20, 10, 45, 5, 15, 13, 17, -20}, List.of("R", "LR")},
                {new int[] {-10, -20, 10, -30, -15, 8, 12}, List.of("R")}
        });
    }

    @Test
    public void findSubtreesTest() {
        Assert.assertEquals(ways, new BinaryTree(treeNumbers).findSubtrees());
    }
}