package structure.proxy.dynamic;

import structure.proxy.Subject;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicProxyTest {

    public static void main(String[] args) {

        List<String> orgIds = new ArrayList<>();
        orgIds.add("测");
        orgIds.add("总");
        orgIds.add("理");
        orgIds = orgIds.stream().filter(item->item.equals("总")).collect(Collectors.toList());
        System.out.println(orgIds.stream().sorted(String::compareTo).collect(Collectors.toList()).toString());

        System.out.println(new BigDecimal(10).compareTo(BigDecimal.ZERO));
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(0);
        List<Long> longSort= Arrays.asList(1L,2L,3L,4L);
        longSort.sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return Long.compare(o2,o1);
            }
        });
        DynamicProxy proxy = new DynamicProxy();

        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");

        Subject s = (Subject) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(),
                new Class[]{Subject.class},
                new LogProxy(proxy));

        s.request();
    }
}
