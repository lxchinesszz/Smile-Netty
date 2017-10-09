package cobra.cobra.protostuff;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Package: cobra.cobra.protostuff
 * @Description: 主要是封装GenericObjectPool 添加需要
 * @author: liuxin
 * @date: 2017/10/9 下午7:21
 */
public class ProtostuffPool {
    private GenericObjectPool<ProtostuffSerialize> protostuffPool;
    private static volatile ProtostuffFactory poolFactory = null;

    public ProtostuffPool(){
        this(10,5,20L,40L);
    }

    public ProtostuffPool(int maxTotal,int minIdle,long maxWaitMillis,long minEvictableIdleTimeMillis){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        protostuffPool=new GenericObjectPool<ProtostuffSerialize>(poolFactory, config);
    }
    public ProtostuffPool(GenericObjectPoolConfig config) {
        protostuffPool = new GenericObjectPool<ProtostuffSerialize>(poolFactory, config);
    }

    /**
     * 获取
     *
     * @return
     */
    public ProtostuffSerialize borrow() {
        try {
            return getProtostuffPool().borrowObject();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 返池
     *
     * @param object
     */
    public void restore(final ProtostuffSerialize object) {
        getProtostuffPool().returnObject(object);
    }

    public GenericObjectPool<ProtostuffSerialize> getProtostuffPool() {
        return protostuffPool;
    }
}
