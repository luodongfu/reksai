package org.okboom.reksai.dht.node.config;

import org.okboom.reksai.dht.node.DhtNodeServer;
import org.okboom.reksai.dht.node.domain.Queue;
import org.okboom.reksai.dht.node.domain.RamQueue;
import org.okboom.reksai.dht.node.props.BittorrentProperties;
import org.okboom.reksai.dht.node.props.NettyProperties;
import org.okboom.reksai.dht.node.stream.InfoHashStreams;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tookbra
 */
@Configuration
@EnableConfigurationProperties({ BittorrentProperties.class, NettyProperties.class })
public class DhtNodeConfiguration {

    @Bean
    public RamQueue ramQueue(BittorrentProperties bittorrentProperties) {
        return new RamQueue(bittorrentProperties.getQueueSize());
    }

    @Bean
    public DhtNodeServer dhtNodeServer(BittorrentProperties bittorrentProperties, NettyProperties nettyProperties,
                                       Queue ramQueue, InfoHashStreams infoHashStreams) {
        return new DhtNodeServer(bittorrentProperties, nettyProperties, ramQueue, infoHashStreams);
    }
}
