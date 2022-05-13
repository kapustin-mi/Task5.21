import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class BinaryTreeTest {
    private final String[] treeNumbers;
    private final List<String> ways;

    public BinaryTreeTest(String[] treeNumbers, List<String> ways) {
        this.treeNumbers = treeNumbers;
        this.ways = ways;
    }

    @Parameterized.Parameters
    public static List<Object[]> cases() {
        return Arrays.asList(new Object[][]{
                {new String[]{"20", "-10", "5", "15", "2", "8", "12", "18", "-11"}, List.of("L")},
                {new String[]{}, null},
                {new String[]{"20"}, null},
                {new String[]{"20", "10", "30"}, List.of("R")},
                {new String[]{"10", "5", "7", "12"}, List.of("L")},
                {new String[]{"20", "10", "45", "5", "15", "13", "17", "-20"}, List.of("L")},
                {new String[]{null, "20", "15", "-10", "30"}, List.of("R", "L")},
                {new String[]{null, "-10", "-5", "-15", "5"}, List.of("R", "L")}
        });
    }

    @Test
    public void findSubtreesTest() {
        Assert.assertEquals(ways, new BinaryTree(treeNumbers).findSubtrees());
    }
}