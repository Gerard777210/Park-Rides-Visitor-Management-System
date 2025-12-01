import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 第一排序条件：按姓名字母顺序
        int nameCompare = v1.getName().compareTo(v2.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }
        // 第二排序条件：按年龄升序
        return Integer.compare(v1.getAge(), v2.getAge());
    }
}