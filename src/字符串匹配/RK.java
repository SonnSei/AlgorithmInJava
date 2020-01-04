package 字符串匹配;

import java.util.Arrays;

/**
 * @Classname RK
 * @Description Rabin-Karp
 * @Date 2020/1/3 7:37
 * @Author SonnSei
 */
public class RK {
    public static void main(String[] args) {
        String a = "sdfsafasdfsdf ";
        String b = "df";
        System.out.println(rk(a,b));
        System.out.println(a.indexOf(b));
    }

    public static int rk(String s, String p) {
        int n = s.length(),m = p.length();
        int radix = 26;
        if(n<m)return -1;

        int[] hash = new int[n - m + 1];
        int weight = (int) Math.pow(radix, m - 1);
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();

        int hashCode = 0,hashP = 0;
        for (int i = 0; i < m; i++) {
            // 模式串的哈希值
            hashP = hashP * radix + pChars[i] - 'a';
            // 主串前m个字符子串的哈希值
            hashCode = hashCode * radix + sChars[i] - 'a';
        }
        hash[0] = hashCode;
        if(hash[0]==hashP && check(sChars,pChars,0))return 0;
        for (int i = 1; i < n-m+1; i++) {
            hashCode-=(sChars[i-1]-'a')*weight;
            hashCode = hashCode * radix + sChars[i + m - 1] - 'a';
            hash[i] = hashCode;
            if(hash[i]==hashP && check(sChars,pChars,i))return i;
        }
        return -1;
    }

    /**
     * 哈希值相同的时候，需要依次检查每个字符，避免哈希冲突
     * @param sChars
     * @param pChars
     * @param start
     * @return
     */
    private static boolean check(char[] sChars, char[] pChars, int start) {
        for (int i = 0; i < pChars.length; i++) {
            if(sChars[i+start]!=pChars[i])return false;
        }
        return true;
    }
}
