package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MappingController {

    @GetMapping({"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("hello basic request");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("data = {}", data);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("userId : {}, orderId : {}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * mode=debug 파라미터와 data=good 파라미터가 모두 추가 되어야 매핑됨 (AND)
     */
    @GetMapping(value = "/mapping-param", params = {"mode=debug", "data=good"})
    public String mappingPath() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 헤더에 mode=debug가 추가되어야 매핑됨
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeaderPath() {
        log.info("header mappingPath");
        return "ok";
    }

    /**
     * 지정된 데이터 타입만 매핑됨
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumePath() {
        log.info("consume mappingPath");
        return "ok";
    }

    /**
     * Accept 헤더 기반으로 매핑됨
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProducePath() {
        log.info("produce mappingPath");
        return "ok";
    }
}