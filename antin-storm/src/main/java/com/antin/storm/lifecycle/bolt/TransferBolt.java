package com.antin.storm.lifecycle.bolt;

import org.apache.log4j.Logger;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;



/**
 *  将数据做简单的 传递的Bolt
 * @author Administrator
 *
 */
public class TransferBolt extends BaseBasicBolt {

	private static final long serialVersionUID = 4223708336037089125L;

	private static final Logger log = Logger.getLogger(TransferBolt.class);
	
	public TransferBolt() {
		log.warn("222222222&&&&&&&&&&&&&&&&& TransferBolt constructor method invoked");
	}
	
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		log.warn("11 11 11 11 11################# TransferBolt prepare() method invoked");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		log.warn("5555555555555################# TransferBolt declareOutputFields() method invoked");
		declarer.declare(new Fields("word"));
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		log.warn("################# TransferBolt execute() method invoked");
		String word = input.getStringByField("str");
		collector.emit(new Values(word));
	}

}
