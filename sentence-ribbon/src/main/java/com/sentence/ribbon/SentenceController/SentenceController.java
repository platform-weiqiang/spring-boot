package com.sentence.ribbon.SentenceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;


@RestController
public class SentenceController {

    @Autowired
    private LoadBalancerClient client;

    @RequestMapping("/sentence")
    public  @ResponseBody String getSentence(){
        return
                "<h3>造句:</h3><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>"
                ;
    }

    public String buildSentence(){
        String sentence = String.format("%s",
//                            getWord("SUBJECT"),
//                            getWord("VERB"),
//                            getWord("ARTICLE"),
//                            getWord("ADJECTIVE"),
                    getWord("SERVICE-CLIENT") );
        return sentence;
    }

    public String getWord(String service){
        ServiceInstance instance=client.choose(service);
        URI uri=instance.getUri();
        System.out.println(uri);
        return (new RestTemplate()).getForObject(uri,String.class);
    }
}
