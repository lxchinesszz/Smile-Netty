package cobra.cobra.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Package: cobra.cobra.event
 * @Description: ${todo}
 * @author: liuxin
 * @date: 2017/9/21 上午10:30
 */
@Component
public class EmailListener implements ApplicationListener{

    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof EmailEvent){
            EmailEvent emailEvent = (EmailEvent)event;
            emailEvent.print();
            System.out.println("the source is:"+emailEvent.getSource());
            System.out.println("the address is:"+emailEvent.address);
            System.out.println("the email's context is:"+emailEvent.text);
        }

    }

}
