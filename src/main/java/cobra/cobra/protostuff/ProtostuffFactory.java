package cobra.cobra.protostuff;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @Package: cobra.cobra.protostuff
 * @Description: 将protostuff进行池化
 * @author: liuxin
 * @date: 2017/10/9 下午7:17
 */
public class ProtostuffFactory extends BasePooledObjectFactory<ProtostuffSerialize> {
    @Override
    public ProtostuffSerialize create() throws Exception {
        return new ProtostuffSerialize();
    }

    @Override
    public PooledObject<ProtostuffSerialize> wrap(ProtostuffSerialize protostuffCodeUtil) {
        return new DefaultPooledObject<ProtostuffSerialize>(protostuffCodeUtil);
    }
}
