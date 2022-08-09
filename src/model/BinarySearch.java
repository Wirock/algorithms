package model;

/**
 * 二分查找
 * @author chenzw
 * @date 2022/8/7
 */
public class BinarySearch {
    /*
        检查x是否满足某种性质
     */
    private boolean check(int x){
        return true;
    }

    // 区间[l, r]被划分成[l, mid]和[mid + 1, r]时使用
    public int search1(int l, int r){
        while(l<r){
            int mid = l + r >>1;
            if(check(mid)){
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return l;
    }

    // 区间[l, r]被划分成[l, mid - 1]和[mid, r]时使用：
    public int search2(int l, int r){
        while(l < r){
            int mid = l + r + 1 >> 1;
            if(check(mid)){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        return l;
    }

    // 检查x是否满足某种性质
    private boolean check(double x) {
        return true;
    }

    double bsearch_3(double l, double r){
        final double eps = 1e-6;   // eps 表示精度，取决于题目对精度的要求
        while (r - l > eps)
        {
            double mid = (l + r) / 2;
            if (check(mid)) r = mid;
            else l = mid;
        }
        return l;
    }
}
