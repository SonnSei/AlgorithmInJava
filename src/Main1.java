import java.util.Stack;

/**
 * @Classname Main1
 * @Description TODO
 * @Date 2020/1/5 10:54
 * @Author SonnSei
 */
public class Main1 {
    public static void main(String[] args) {
        String s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#";
        System.out.println(freqAlphabets(s));
    }

    public static String freqAlphabets(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }
        String ret = "";
        while (!stack.isEmpty()) {
            char c = stack.pop();
            char add;
            if (c == '#') {
                char c1 = stack.pop();
                char c2 = stack.pop();
                int i = Integer.parseInt("" + c2 + c1);
                add = (char) ('a'-1 + i);
            } else {
                add = (char) ('a' + c - '1');
            }
            ret = add+ret;
        }
        return ret;
    }
}
