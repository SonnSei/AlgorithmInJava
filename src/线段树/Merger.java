package 线段树;

/**
 * @Classname Merger
 * @Description
 * @Date 2020/1/13 21:42
 * @Author SonnSei
 */
public interface Merger<E> {
    E merge(E a, E b);
}
