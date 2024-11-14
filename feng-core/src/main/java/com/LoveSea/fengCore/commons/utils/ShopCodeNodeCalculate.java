package com.LoveSea.fengCore.commons.utils;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 因为每次找一个不确定节点的店铺，都需要一个节点一个节点的去找，所以这里写一个工具类，用来计算节点
 * @author xiahaifeng
 */
@Slf4j
public class ShopCodeNodeCalculate {
    private static final int DEFAULT_VIRTUAL_BUCKET_TIMES=160;
    private static final int DEFAULT_WEIGHT=1;
    private static final Charset DEFAULT_CHARSET=Charset.forName("UTF-8");

    private int seed;
    private int count;
    private int virtualBucketTimes=DEFAULT_VIRTUAL_BUCKET_TIMES;
    private Map<Integer,Integer> weightMap=new HashMap<>();
//	private String bucketMapPath;

    private HashFunction hash;

    private SortedMap<Integer,Integer> bucketMap;
    public void init()  {
        try{
            bucketMap=new TreeMap<>();
            generateBucketMap();
        }catch(Exception e){
            log.error("初始化失败", e);
        }
    }

    private void generateBucketMap(){
        hash=Hashing.murmur3_32(seed);//计算一致性哈希的对象
        for(int i=0;i<count;i++){//构造一致性哈希环，用TreeMap表示
            StringBuilder hashName=new StringBuilder("SHARD-").append(i);
            for(int n=0,shard=virtualBucketTimes*getWeight(i);n<shard;n++){
                bucketMap.put(hash.hashUnencodedChars(hashName.append("-NODE-").append(n)).asInt(),i);
            }
        }
        weightMap=null;
    }
    /**
     * 得到桶的权重，桶就是实际存储数据的DB实例
     * 从0开始的桶编号为key，权重为值，权重默认为1。
     * 键值必须都是整数
     * @param bucket
     * @return
     */
    private int getWeight(int bucket){
        Integer w=weightMap.get(bucket);
        if(w==null){
            w=DEFAULT_WEIGHT;
        }
        return w;
    }
    /**
     * 创建murmur_hash对象的种子，默认0
     * @param seed
     */
    public void setSeed(int seed){
        this.seed=seed;
    }
    /**
     * 节点的数量
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }
    /**
     * 虚拟节点倍数，virtualBucketTimes*count就是虚拟结点数量
     * @param virtualBucketTimes
     */
    public void setVirtualBucketTimes(int virtualBucketTimes){
        this.virtualBucketTimes=virtualBucketTimes;
    }
    /**
     * 节点的权重，没有指定权重的节点默认是1。以properties文件的格式填写，以从0开始到count-1的整数值也就是节点索引为key，以节点权重值为值。
     * 所有权重值必须是正整数，否则以1代替
     * @param weightMapPath
     * @throws IOException
     * @throws
     */
    public void setWeightMapFile(String weightMapPath) throws IOException{
        Properties props=new Properties();
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(weightMapPath), DEFAULT_CHARSET))){
            props.load(reader);
            for(Map.Entry entry:props.entrySet()){
                int weight=Integer.parseInt(entry.getValue().toString());
                weightMap.put(Integer.parseInt(entry.getKey().toString()), weight>0?weight:1);
            }
        }
    }

    public Integer calculate(String columnValue) {
        SortedMap<Integer, Integer> tail = bucketMap.tailMap(hash.hashUnencodedChars(columnValue).asInt());
        if (tail.isEmpty()) {
            return bucketMap.get(bucketMap.firstKey());
        }
        return tail.get(tail.firstKey());
    }

    public int getPartitionNum() {
        int nPartition = this.count;
        return nPartition;
    }
    public static void main(String[] args) throws IOException {
        ShopCodeNodeCalculate hash=new ShopCodeNodeCalculate();
        hash.setSeed(0);
        String[] nodeName = {"节点1", "节点2", "节点3", "节点4"};
        hash.setCount(4);
        hash.setVirtualBucketTimes(160);
        hash.init();
        System.out.println(nodeName[hash.calculate("DBshop-VN")]);
    }


}
