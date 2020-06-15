package com.mashirro.elasticsearch_demo.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * es客户端
 * 单例模式:
 * (1)单例类只能有一个实例
 * (2)单例类必须自己创建自己的唯一实例
 * (3)单例类必须给所有其他对象提供这一实例
 */
public class EsClient {

    //单例模式:饿汉式start(不是Lazy初始化,多线程安全,容易产生垃圾对象,没有加锁执行效果提高,类加载时就初始化浪费内存)
    private EsClient() {}//私有化构造函数
    private static EsClient instance = new EsClient();
    public static EsClient getInstance() {
        return instance;
    }
    //单例模式:饿汉式end


    private static Settings settings = null;
    private static TransportClient client = null;

    /**
     * 初始化es客户端连接(客户端必须与集群中的节点具有相同的主版本)
     * @return
     */
    public TransportClient init() throws UnknownHostException {
        //client.transport.sniff:true-->启用集群嗅探,允许它动态地添加新主机和删除旧主机。启用探查时，传输客户端将连接到其内部节点列表中的节点，该列表通过调用addTransportAddress生成。
        //cluster.name-->如果使用与"elasticsearch"不同的名称，则必须重新设置集群名称
        settings = Settings.builder().put("cluster.name", "my-es").put("client.transport.sniff", true).build();
        //TransportClient使用传输模块远程连接到Elasticsearch集群。它不加入集群，只是获取一个或多个初始传输地址，并在每个操作上以循环方式与它们通信（尽管大多数操作可能是"两跳"操作）。
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
                //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), port));

        //返回此客户端将使用的当前连接的传输节点。根据提供的传输地址，这些节点包括当前活动的所有节点。
        List<DiscoveryNode> connectedNodes = client.connectedNodes();
        System.out.println("connectedNodes: "+connectedNodes);
        //未连接到的筛选节点列表，for example，由于群集名称不匹配。
        List<DiscoveryNode> filteredNodes = client.filteredNodes();
        System.out.println("filteredNodes: "+filteredNodes);
        //返回传输客户端中列出的节点（添加到其中的节点）。
        List<DiscoveryNode> listedNodes = client.listedNodes();
        System.out.println("listedNodes: "+listedNodes);
        return client;
    }
}

