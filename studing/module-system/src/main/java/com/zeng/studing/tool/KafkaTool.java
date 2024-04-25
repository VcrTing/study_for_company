package org.jeecg.formality.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Slf4j
@Component
public class KafkaTool {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    final static long LIVE_TIME_CONSUMER_LOCK = 1000 * 60 * 60 * 12; // 12 小时
    final static ConcurrentHashMap<String, Long> consumerTimeMap;
    static {
        consumerTimeMap = new ConcurrentHashMap<>();
    }

    volatile long i = 0L;
    final static long MAX_I = 1000000L;
    public final static String TEST = "DEF_TOPIC_TEST";

    /**
    * 规则 KEY 生成，简单借用 KAFKA 自带的 话题 负载均衡
    */
    public String key() {
        i = i + 1L; if (Objects.equals(i, MAX_I)) i = 0L; return i + "";
    }

    /**
    * B 计划进行消费
    */
    private String mapKey(String k) {
        return "INDEX_" + i + "_" + k;
    }
    // 清理 MAP
    private void cleanMap() {
        Long now = System.currentTimeMillis();
        for (String k: consumerTimeMap.keySet()) {
            Long v = consumerTimeMap.get(k);
            // k 值 过去 时间了，删掉它
            if ((now - v) > LIVE_TIME_CONSUMER_LOCK) { consumerTimeMap.remove(k); }
        }
    }
    // B 计划消费
    private <T extends String> void consumer(Consumer<T> consumerWhenError, String topic, T json) {
        cleanMap();
        String k = mapKey(topic);
        Long time = consumerTimeMap.get(k);
        if (time != null) {
            log.info("consumerWhenError 已经被消费一次了，无需再次被消费"); }
        else {
            // 锁住该 K
            consumerTimeMap.put(k, System.currentTimeMillis());
            if (consumerWhenError != null) consumerWhenError.accept(json);
        }
    }

    /**
    * 仅根据话题进行发送，默认限死，只能发送 String
    */
    public <T extends String> void send(String topic, T json) {
        try { kafkaTemplate.send(topic, key(), json); } catch (Exception ignored) { }
    }
    // 功能健全的发送
    public <T extends String> void send(String topic, T json, Consumer<T> consumerWhenError) {
        try {
            // 里面是阻塞发送，使用的 Future 来收集数据 => ListenableFuture<SendResult<String, String>> future
            kafkaTemplate.send(topic, key(), json).addCallback(
                (SendResult<String, String> res) -> { },
                (Throwable throwable) -> consumer(consumerWhenError, topic, json));
        } catch (Exception e) {
            log.error("KAFKA_LOG: Kafka 死了，send 失败，启用 B 计划进行消费");
            consumer(consumerWhenError, topic, json);
        }
    }
    // 异步发送
    public <T extends String> void sendAsync(String topic, T json) {
        new Thread(() -> { try { kafkaTemplate.send(topic, key(), json); } catch (Exception ignored) { } }).start();
    }
    public <T extends String> void sendAsync(String topic, T json, Consumer<T> consumerWhenError) {
        new Thread(() -> send(topic, json, consumerWhenError)).start();
    }
}