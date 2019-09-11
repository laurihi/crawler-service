package fi.ambientia.sd.crawler.controller;

import fi.ambientia.sd.crawler.service.EurekaCrawlerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Api
@RestController
@RequestMapping("/crawler")
public class CrawlerController {

    private EurekaCrawlerService eurekaCrawlerService;

    @Autowired
    public CrawlerController(EurekaCrawlerService eurekaCrawlerService){
        this.eurekaCrawlerService = eurekaCrawlerService;
    }
    @GetMapping("/clients")
    public HashMap<String, Object> crawlClients(){
        eurekaCrawlerService.crawl();
        return null;
    }
}
