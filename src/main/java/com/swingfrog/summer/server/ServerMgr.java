package com.swingfrog.summer.server;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;

import com.google.common.collect.Maps;
import com.swingfrog.summer.protocol.ProtocolConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swingfrog.summer.config.ConfigMgr;
import com.swingfrog.summer.config.ServerConfig;
import com.swingfrog.summer.ioc.ContainerMgr;

public class ServerMgr {

	private static final Logger log = LoggerFactory.getLogger(ServerMgr.class);
	private Server server;
	private final Map<String, Server> serverMap;
	
	private static class SingleCase {
		public static final ServerMgr INSTANCE = new ServerMgr();
	}
	
	private ServerMgr() {
		serverMap = Maps.newHashMap();
	}
	
	public static ServerMgr get() {
		return SingleCase.INSTANCE;
	}
	
	public void init() throws Exception {
		log.info("server init...");
		ServerConfig serverConfig = ConfigMgr.get().getServerConfig();
		if (serverConfig == null) {
			throw new NullPointerException("serverConfig is null");
		}
		boolean protobuf = ProtocolConst.isProtobuf(serverConfig.getProtocol());
		server = Server.create(serverConfig);
		Iterator<Class<?>> iteratorHandler = ContainerMgr.get().iteratorHandlerList();
		while (iteratorHandler.hasNext()) {
			Class<?> clazz = iteratorHandler.next();
			log.info("server register session handler {}", clazz.getSimpleName());
			server.addSessionHandler((SessionHandler) ContainerMgr.get().getDeclaredComponent(clazz));
		}
		ServerConfig[] minorConfigs = ConfigMgr.get().getMinorConfigs();
		if (minorConfigs != null && minorConfigs.length > 0) {
			for (ServerConfig sc : minorConfigs) {
				Server s = Server.createMinor(sc, server.getBossGroup(), server.getWorkerGroup(), server.getEventExecutor(), server.getPushExecutor());
				Iterator<Class<?>> scIteratorHandler = ContainerMgr.get().iteratorHandlerList(sc.getServerName());
				if (scIteratorHandler != null) {
					while (scIteratorHandler.hasNext()) {
						Class<?> clazz = scIteratorHandler.next();
						log.info("server [{}] register session handler {}", sc.getServerName(), clazz.getSimpleName());
						s.addSessionHandler((SessionHandler) ContainerMgr.get().getDeclaredComponent(clazz));
					}
				}
				serverMap.put(sc.getServerName(), s);
				protobuf |= ProtocolConst.isProtobuf(sc.getProtocol());
			}
		}
		RemoteDispatchMgr.get().init();
		if (protobuf) {
			RemoteProtobufDispatchMgr.get().init();
		}
	}
	
	public void launch() {
		log.info("server launch...");
		server.launch();
		for(Entry<String, Server> entry : serverMap.entrySet()) {
			entry.getValue().launch();
		}
	}

	public void shutdown() {
		log.info("server shutdown...");
		for(Entry<String, Server> entry : serverMap.entrySet()) {
			entry.getValue().shutdown();
		}
		server.shutdown();
	}

	public void shutdownEvent() {
		log.info("server shutdown event...");
		for(Entry<String, Server> entry : serverMap.entrySet()) {
			entry.getValue().shutdownEvent();
		}
		server.shutdownEvent();
	}
	
	public ServerPush getServerPush() {
		return server.getServerPush();
	}
	
	public void closeSession(SessionContext sctx) {
		server.closeSession(sctx);
	}
	
	public ExecutorService getEventExecutor() {
		return server.getEventExecutor();
	}
	
	public ServerPush getServerPush(String serverName) {
		return serverMap.get(serverName).getServerPush();
	}

	public void closeSession(String serverName, SessionContext sctx) {
		serverMap.get(serverName).closeSession(sctx);
	}
	
	public ExecutorService getEventExecutor(String serverName) {
		return serverMap.get(serverName).getEventExecutor();
	}

	public Server findServer(SessionContext sctx) {
		if (server.getServerContext().getSessionContextGroup().contains(sctx)) {
			return server;
		}
		return serverMap.values().stream()
				.filter(ser -> ser.getServerContext().getSessionContextGroup().contains(sctx))
				.findAny()
				.orElse(null);
	}
	
}
