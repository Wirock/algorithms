package leetcode;

/**
 * @author chenzw
 * @date 2021/5/28
 */
public class Solution176 {
    public void test(){
        //1
        System.out.println("SELECT (SELECT DISTINCT Salary FROM Employee ORDER BY Salary DESC LIMIT 1 OFFSET 1) AS SecondHighestSalary");
        //2
        System.out.println("select ifnull((select distinct salary from employee order by salary desc limit 1,1),null) SecondHighestSalary");
    }
}
