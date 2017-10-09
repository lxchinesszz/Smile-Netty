package cobra.cobra.protostuff;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Package: cobra.cobra.protostuff
 * @Description: 入口解码要用MessageRequest   :   true
 * @author: liuxin
 * @date: 2017/10/9 下午7:01
 */
public class MessageDecoder extends ByteToMessageDecoder {
    private static final boolean rpcDirect = true;
    private static final int MESSAGE_LENGTH = 4;
    private static MessageCodecUtil util;

    public MessageDecoder(MessageCodecUtil messageCodecUtil) {
       this.util = messageCodecUtil;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < MessageDecoder.MESSAGE_LENGTH) {
            return;
        }
        in.markReaderIndex();
        int messageLength = in.readInt();

        if (messageLength < 0) {
            ctx.close();
        }

        if (in.readableBytes() < messageLength) {
            in.resetReaderIndex();
            return;
        } else {
            byte[] messageBody = new byte[messageLength];
            in.readBytes(messageBody);

            try {
                Object obj = util.decode(messageBody);
                out.add(obj);
            } catch (IOException ex) {
                Logger.getLogger(MessageDecoder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
