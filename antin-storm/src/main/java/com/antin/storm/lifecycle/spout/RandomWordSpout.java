package com.antin.storm.lifecycle.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;

/**
 * 随机从String数组当中读取一个单词发送给下一个bolt
 *
 * @author Administrator
 */
public class RandomWordSpout extends BaseRichSpout {

    private static final long serialVersionUID = -4287209449750623371L;

    private static final Logger log = LoggerFactory.getLogger(RandomWordSpout.class);

    private SpoutOutputCollector collector;

    private String[] words = new String[]{"storm", "hadoop", "hive", "flume"};

    private Random random = new Random();

    public RandomWordSpout() {
        log.warn("111111111&&&&&&&&&&&&&&&&& RandomWordSpout constructor method invoked");
    }

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        log.warn("8888888888################# RandomWordSpout open() method invoked");
        this.collector = collector;
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        log.warn("77777777777777################# RandomWordSpout declareOutputFields() method invoked");
        declarer.declare(new Fields("str"));
    }

    @Override
    public void nextTuple() {
        log.warn("10 10 10 10 10 10################# RandomWordSpout nextTuple() method invoked");
        Utils.sleep(500);
        String str = words[random.nextInt(words.length)];
        collector.emit(new Values(str));
    }


    @Override
    public void activate() {
        log.warn("99999999999################# RandomWordSpout activate() method invoked");
    }

    @Override
    public void deactivate() {
        log.warn("################# RandomWordSpout deactivate() method invoked");
    }


}
