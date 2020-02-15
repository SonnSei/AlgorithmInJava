package 字符串匹配;

import java.util.Arrays;

/**
 * @Classname BM
 * @Description Boyer-Moore
 * @Date 2020/1/3 20:59
 * @Author SonnSei
 */
public class BM {
    public static void main(String[] args) {

        String a = "aabaabbbaabbbbabaaab";
        String b = "abaa";
        System.out.println(bm(a, b));
        System.out.println(a.indexOf(b));
    }

    private static int bm(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int n  = s.length();
        int m = p.length();
        if(n<m)return -1;
        int[] bc = generateBC(p);
        boolean[] prefix = new boolean[m];
        int[] suffix = new int[m];
        generateGS(pChars, prefix, suffix);
        int i = 0;
        while (i <=n-m) {
            int j = m-1;
            while(j>=0 && sChars[j+i]==pChars[j])j--;
            if(j<0)return i;
            int x = j-bc[sChars[j+i]];
            int y = moveByGS(j, m,prefix, suffix);
            i = i + Math.max(x, y);
        }
        return -1;
    }

    private static int moveByGS(int j, int m,boolean[] prefix, int[] suffix) {
        int len = m-1-j;
        if(len==0)return 0;
        if(suffix[len]!=-1)return j-suffix[len]+1;
        // i表示右移的位数，在第j位不匹配，则最少移动j+1次，但是如果是移动j+1次已经通过之前的判断排除
        // 所以从移动j+2步开始
        for (int i = j + 2; i < m; i++) {
            if(prefix[m-i])return i;
        }
        // 这个比较容易理解
//        for(int i = m-j+1;i>0;i--){
//            if(prefix[i])return m-i-1;
//        }
        return m;
    }


    /**
     * 生成好后缀记录
     * 如果模式串包含大量重复元素，可能导致预处理复杂度为O（n^2）
     * @param pChars
     * @param prefix
     * @param suffix
     */
    private static void generateGS(char[] pChars, boolean[] prefix, int[] suffix) {
        Arrays.fill(suffix, -1);
        for (int i = 0; i < pChars.length-1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && pChars[j] == pChars[pChars.length - 1 - k]) {
                j--;
                k++;
                suffix[k] = j+1;
            }
            if(j==-1)prefix[k] = true;
        }
    }

    private static int[] generateBC(String p) {
        int[] ret = new int[128];
        Arrays.fill(ret,-1);
        for (int i = 0; i < p.length(); i++) {
            ret[p.charAt(i)] = i;
        }
        return ret;
    }
}
