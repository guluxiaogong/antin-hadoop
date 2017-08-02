package com.antin.storm.lifecycle.bolt;

import com.antin.hdfs.HdfsHelper;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.util.Map;

/**
 * 将接收到的单词写入到一个文件当中
 *
 * @author Administrator
 */
public class WriterBolt extends BaseBasicBolt {

    private static final long serialVersionUID = -6586283337287975719L;

    private static final Logger log = LoggerFactory.getLogger(WriterBolt.class);

    private FileWriter writer = null;

    public WriterBolt() {
        log.warn("3333333333&&&&&&&&&&&&&&&&& WriterBolt constructor method invoked");
    }


    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        log.warn("12 12 12 12 12 12################# WriterBolt prepare() method invoked");
        try {
            writer = new FileWriter("/user/jcj/study/strom/life-cycle/" + this.getClass().getSimpleName());
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        log.warn("666666666666################# WriterBolt declareOutputFields() method invoked");
    }


    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        log.warn("################# WriterBolt execute() method invoked");
        String s = input.getString(0);
        try {
            //HdfsHelper.write("/user/jcj/study/storm/life-cycle/"+this.getClass().getSimpleName(), s + "\n", true);
            writer.write(s);
            writer.write("\n");
            writer.flush();
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }

}
