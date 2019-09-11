package fi.ambientia.sd.crawler.service;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class EurekaCrawlerService {

    private EurekaDiscoveryClient eurekaDiscoveryClient;

    @Autowired
    public EurekaCrawlerService(CompositeDiscoveryClient compositeDiscoveryClient) {

        this.eurekaDiscoveryClient = (EurekaDiscoveryClient) compositeDiscoveryClient.getDiscoveryClients()
                .stream().filter(x -> x.getClass().equals(EurekaDiscoveryClient.class))
                .findAny().orElseThrow(() -> new BeanCreationException("Unable to resolve eureka discovery client"));
    }

    public void crawl() {
    }

    private List<EurekaDiscoveryClient.EurekaServiceInstance> getUniqueServices(){
        List<String> services = eurekaDiscoveryClient.getServices();

        List<EurekaDiscoveryClient.EurekaServiceInstance> serviceInstances = services.stream().
                map(serviceId -> eurekaDiscoveryClient.getInstances(serviceId))
                .map( serviceInstanceList -> (EurekaDiscoveryClient.EurekaServiceInstance) serviceInstanceList.get(0) )
                .collect(Collectors.toList());

        return serviceInstances;

    }


}
