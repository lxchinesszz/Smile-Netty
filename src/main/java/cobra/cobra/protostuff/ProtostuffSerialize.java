package cobra.cobra.protostuff;

import cobra.cobra.modle.MessageRequest;
import cobra.cobra.modle.MessageResponse;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Package: cobra.cobra.protostuff
 * @Description: 因为protostuff相对于jdk自带的将流装换成，需要自动那诡异schame，所以我们在这个类中，将protostuff装换成jdk那种方法
 * @author: liuxin
 * @date: 2017/10/9 下午7:36
 */
public class ProtostuffSerialize {
    /**
     * 主要动态获取schema,使用google缓存技术
     */
    private static SchemaCache cachedSchema = SchemaCache.getInstance();
    /**
     * 方便类进行实例
     */
    private static Objenesis objenesis = new ObjenesisStd(true);
    /**
     * 根据方向判断入口使用MessageRequest的schema解码 入口: true
     * 出口使用MessageResponse的schema编码          出口: false
     */
    private boolean rpcDirect = false;

    public boolean isRpcDirect() {
        return rpcDirect;
    }

    public void setRpcDirect(boolean rpcDirect) {
        this.rpcDirect = rpcDirect;
    }

    private static <T> Schema<T> getSchema(Class<T> cls) {
        return (Schema<T>) cachedSchema.get(cls);
    }

    public Object deserialize(InputStream input) {
        try {
            Class cls = isRpcDirect() ? MessageRequest.class : MessageResponse.class;
            Object message = (Object) objenesis.newInstance(cls);
            Schema<Object> schema = getSchema(cls);
            ProtostuffIOUtil.mergeFrom(input, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void serialize(OutputStream output, Object object) {
        Class cls = (Class) object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema schema = getSchema(cls);
            ProtostuffIOUtil.writeTo(output, object, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }
}
