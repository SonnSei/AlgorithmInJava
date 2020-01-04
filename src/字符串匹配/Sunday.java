package 字符串匹配;

/**
 * @Classname Sunday
 * @Description TODO
 * @Date 2020/1/4 19:52
 * @Author SonnSei
 */
public class Sunday {
    public static void main(String[] args) {
        String a = "josdjfoioifwejjfoioiowjjf";
        String b = "oioi";
        System.out.println(sunday(a,b));
        System.out.println(a.indexOf(b));
    }


    static final int ASCII_SIZE = 126;

    public static int sunday(String ss, String p){
        char[] total = ss.toCharArray();
        char[] part =  p.toCharArray();
        int tSize = total.length;
        int pSize = part.length;
        int[] move = new int[ASCII_SIZE];
        //主串参与匹配最末位字符移动到该位需要移动的位数
        for (int i= 0;i<ASCII_SIZE;i++){
            move[i] = pSize+1;
        }

        for (int i = 0;i<pSize;i++){
            move[part[i]] = pSize-i;
        }

        int s = 0;//模式串头部在字符串位置

        int j;//模式串已经匹配了的长度

        while(s<=tSize-pSize){//到达末尾之前
            j = 0;
            while(total[s+j]==part[j]){
                j++;
                if (j>=pSize){
                    return s;
                }
            }
            s+=move[total[s+pSize]];
        }
        return -1;
    }
}
