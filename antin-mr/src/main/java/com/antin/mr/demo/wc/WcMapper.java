package com.antin.mr.demo.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/2.
 */
public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    //map方法的生命周期：  框架每传一行数据就被调用一次
    //key :  这一行的起始点在文件中的偏移量
    //value: 这一行的内容
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //拿到一行数据转换为string
        String line = value.toString();
        //将这一行切分出各个单词
        String[] words = line.split(" ");
        //遍历数组，输出<单词，1>
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
