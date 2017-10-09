package cobra.cobra.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Package: cobra.cobra.event
 * @Description: 定义一个时间
 * @author: liuxin
 * @date: 2017/9/21 上午10:29
 */
public class EmailEvent extends ApplicationEvent {
    /**
     * <p>Description：</p>
     */
    private static final long serialVersionUID = 1L;
    public String address;
    public String text;

    public EmailEvent(Object source) {
        super(source);
    }

    public EmailEvent(Object source, String address, String text) {
        super(source);
        this.address = address;
        this.text = text;
    }

    public void print(){
        System.out.println("hello spring event!");
    }

}