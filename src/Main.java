import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    /** Optionalを使わないMap */
    private static final Map<Integer, String> map1 = new HashMap<>();

    /** Optionalを使ったMap */
    private static final Map<Integer, Optional<String>> map2 = new HashMap<>();

    static {
        map1.put(1, "Koizumi Juntarou");
        map1.put(2, null);
        map1.put(3, "Kan Naotarou");

        map2.put(1, Optional.of("Koizumi Junko"));
        map2.put(2, Optional.<String>absent());
        map2.put(3, Optional.of("Kan Naoko"));
    }

    public static void main(String[] args) {

        // No.2とNo.4の登録状態（nullの意味が異なること）を区別できない
        System.out.println("No. " + 1 + " : " + getName1(1));
        System.out.println("No. " + 2 + " : " + getName1(2));
        System.out.println("No. " + 3 + " : " + getName1(3));
        System.out.println("No. " + 4 + " : " + getName1(4));

        System.out.println("###");

        // No.2とNo.4の登録状態を区別できる（isPresentを使って値の有無を判定）
        System.out.println("No. " + 1 + " : " + getName2(1));
        System.out.println("No. " + 2 + " : " + getName2(2));
        System.out.println("No. " + 3 + " : " + getName2(3));
        System.out.println("No. " + 4 + " : " + getName2(4));

        System.out.println("###");

        // No.2とNo.4の登録状態を区別できる（orを使って値が無い場合デフォルト値を返す）
        System.out.println("No. " + 1 + " : " + getName3(1));
        System.out.println("No. " + 2 + " : " + getName3(2));
        System.out.println("No. " + 3 + " : " + getName3(3));
        System.out.println("No. " + 4 + " : " + getName3(4));

        System.out.println("###");

        // Optional#asSetで要素を１つだけ持つImmutableなSetを生成できる。
        Set<String> nameSet = Optional.of("Mario").asSet();
        System.out.println(nameSet);

        // 追加操作等をしようとすると例外がスローされる
        try {
            nameSet.add("Luigi");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }

    }

    private static String getName1(int no) {
        String value = map1.get(no);
        if (value == null) {
            return "null";
        }
        return value;
    }

    /**
     * isPresentを使って判定して特定の文字列を返す
     */
    private static String getName2(int no) {
        Optional<String> value = map2.get(no);
        if (value == null) {
            return "null";
        }

        if (!value.isPresent()) {
            return "none";
        }

        return value.get();
    }

    /**
     * orを使って値が無い場合デフォルト値を返す
     */
    private static String getName3(int no) {
        Optional<String> value = map2.get(no);
        if (value == null) {
            return "null";
        }

        return value.or("none");
    }

}
