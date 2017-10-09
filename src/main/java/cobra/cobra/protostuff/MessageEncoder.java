package cobra.cobra.protostuff;

import cobra.cobra.modle.MessageResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Package: cobra.cobra.protostuff
 * @Description: ${todo}
 * @author: liuxin
 * @date: 2017/10/9 下午8:02
 */
public class MessageEncoder extends MessageToByteEncoder<MessageResponse> {
    private static final boolean rpcDirect = false;
    private static final int MESSAGE_LENGTH = 4;
    private static MessageCodecUtil util;

    public MessageEncoder(MessageCodecUtil messageCodecUtil) {
        this.util = messageCodecUtil;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageResponse msg, ByteBuf out) throws Exception {
        util.encode(out, msg);
    }
}
