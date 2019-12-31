package com.gzz.client;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TimeClient {

	public void connect(String host, int port) {
		/** 配置客户端 NIO 线程组/池 */
		EventLoopGroup group = new NioEventLoopGroup();

		/**
		 * Bootstrap 与 ServerBootstrap 都继承(extends)于 AbstractBootstrap
		 * 创建客户端辅助启动类,并对其配置,与服务器稍微不同，这里的 Channel 设置为 NioSocketChannel 然后为其添加
		 * Handler，这里直接使用匿名内部类，实现 initChannel 方法 作用是当创建 NioSocketChannel
		 * 成功后，在进行初始化时,将它的ChannelHandler设置到ChannelPipeline中，用于处理网络I/O事件
		 */
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel channel) {
				channel.pipeline().addLast(new TimeClientHandler());
			}
		});
		try {
			/** connect：发起异步连接操作，调用同步方法 sync 等待连接成功 */
			ChannelFuture channelFuture = b.connect(host, port).sync();
			log.info("客户端发起异步连接..........");

			/** 等待客户端链路关闭 */
			channelFuture.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			/** 优雅退出，释放NIO线程组 */
			group.shutdownGracefully();
		}
	}
}
