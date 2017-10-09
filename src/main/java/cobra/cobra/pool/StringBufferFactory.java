package cobra.cobra.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @Package: cobra.cobra.pool
 * @Description: 对象池
 * @author: liuxin
 * @date: 2017/9/21 下午6:39
 */
public class StringBufferFactory extends BasePooledObjectFactory<User> {
    @Override
    public User create() throws Exception {
        return new User();
    }
    /**
     * 使用默认 DefaultPooledObject 封装
     * @param user
     * @return
     */
    @Override
    public PooledObject<User> wrap(User user) {
        return new DefaultPooledObject<User>(user);
    }
}
