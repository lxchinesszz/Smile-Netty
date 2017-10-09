package cobra.cobra.modle;

import lombok.Data;

import java.io.Serializable;

/**
 * @Package: cobra.cobra.modle
 * @Description:
 * @author: liuxin
 * @date: 2017/10/9 下午7:42
 */
@Data
public class MessageResponse implements Serializable {

    private String messageId;
    private String error;
    private Object result;
    private boolean returnNotNull;

    public boolean isReturnNotNull() {
        return returnNotNull;
    }

    public void setReturnNotNull(boolean returnNotNull) {
        this.returnNotNull = returnNotNull;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
