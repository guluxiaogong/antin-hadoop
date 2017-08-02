package com.antin.storm.lifecycle.topology;

import com.antin.storm.lifecycle.bolt.TransferBolt;
import com.antin.storm.lifecycle.bolt.WriterBolt;
import com.antin.storm.lifecycle.spout.RandomWordSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * /usr/hdp/2.6.1.0-129/storm/bin/storm jar /zoesoft/zoeJobJar/antin-storm-jar-with-dependencies.jar com.antin.storm.lifecycle.topology.TopoMain
 *
 * /usr/hdp/2.6.1.0-129/storm/bin/storm jar /zoesoft/zoeJobJar/antin-storm.jar com.antin.storm.lifecycle.topology.TopoMain
 */
public class TopoMain {

    private static final Logger log = LoggerFactory.getLogger(TopoMain.class);

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("random", new RandomWordSpout(), 2);
        builder.setBolt("transfer", new TransferBolt(), 4).shuffleGrouping("random");
        builder.setBolt("writer", new WriterBolt(), 4).fieldsGrouping("transfer", new Fields("word"));
        Config conf = new Config();
        conf.setNumWorkers(2);
        conf.setDebug(true);
        log.warn("444444444$$$$$$$$$$$ submitting topology...");
        //StormSubmitter.submitTopology("life-cycle", conf, builder.createTopology());
		LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology("life-cycle", conf, builder.createTopology());
        log.warn("$$$$$$$4$$$ topology submitted !");
    }

}
