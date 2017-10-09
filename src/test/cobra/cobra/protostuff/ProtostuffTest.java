package cobra.cobra.protostuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.junit.Test;

/**
 * @Package: cobra.cobra.protostuff
 * @Description:
 * @author: liuxin
 * @date: 2017/10/9 下午6:48
 */
public class ProtostuffTest {

    @Test
    public void testProtostuff() {
        Person person = new Person();
        person.setName("测试");
        person.setAge("23");

        RuntimeSchema<Person> schema = RuntimeSchema.createFrom(Person.class);
        //参数三缓冲器
        byte[] bytes = ProtostuffIOUtil.toByteArray(person, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        /**
         *反序列化
         */
        // 空对象
        Person newCrab = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, newCrab, schema);
        System.out.println("Hi, My name is " + newCrab.getName());
    }

    static class Person {
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
