package cobra.cobra.protostuff;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * @Package: cobra.cobra.protostuff
 * @Description: 定义解码编码的接口
 * @author: liuxin
 * @date: 2017/10/9 下午7:00
 */
public interface MessageCodecUtil {
    int MESSAGE_LENGTH = 4;

    void encode(final ByteBuf out, final Object message) throws IOException;

    Object decode(byte[] body) throws IOException;
}
