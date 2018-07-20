package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zn on 2018/7/19.
 */

public class LambdaDemo {
    private void filter() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 100; i ++) {
            numbers.add(i);
        }
//        List<String> evenNumbers = numbers.stream().collect().forEach().map(p->{
//            return "当前数据是：" + p;
//        }).collect(Collectors.toList());

//        List<Integer> evenNumbers = numbers.stream()
//                .map()filter(p->p.intValue() % 2 == 0)
//                .collect(Collectors.toList());
    }
}


