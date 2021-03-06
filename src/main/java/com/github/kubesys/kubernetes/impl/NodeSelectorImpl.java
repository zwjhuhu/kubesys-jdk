/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.kubernetes.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;

import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeCondition;
import io.fabric8.kubernetes.api.model.Taint;


/**
 * @author  wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.0.0
 * @since   2019/9/1
 * 
 * <p>
 * <code>NodeSelectorImpl<code> is used for selecting a optimal
 * machine based on the specified policy for each VM.
 * 
 **/
public class NodeSelectorImpl {
	
	/**
	 * m_logger
	 */
	protected final static Logger m_logger = Logger.getLogger(NodeSelectorImpl.class.getName());

	/**
	 * default node
	 */
	protected final static String DEFAULT_NODE = null;
	
	/**
	 * client
	 */
	protected final ExtendedKubernetesClient client;
	
	/**
	 * @param client  client
	 */
	public NodeSelectorImpl(ExtendedKubernetesClient client) {
		super();
		this.client = client;
	}

	/**
	 * Note that we only consider the RAM size in Ki, Mi, Gi and Ti,
	 * otherwise you will get an exception.
	 * 
	 * @param policy             policy
	 * @return                   node name or null
	 */
	public String getNodename(Policy policy) {
		
		Node[] nodes = getNodeCandidates();
		
		if (policy == Policy.minimumCPUUsageHostAllocatorStrategyMode) {
			sortByMinimumCPUUsage(nodes);
		} else if (policy == Policy.minimumMemoryUsageHostAllocatorStrategyMode) {
			sortByMinimumMemoryUsage(nodes);
		} else if (policy == Policy.maxInstancePerHost) {
			sortByMaxInstancePerHost(nodes);
		} else if (policy == Policy.minInstancePerHost) {
			sortByMinInstancePerHost(nodes);
		} 
		
		// get the optimized node name 
		for (Node node : nodes) {
			if (isMaster(node) || notReady(node) || unSched(node)) {
				continue;
			}
			return node.getMetadata().getName();
		}
		
		return DEFAULT_NODE;
	}

	/**
	 * @param nodes     the node with minimum instances
	 */
	protected void sortByMinInstancePerHost(Node[] nodes) {
		Arrays.sort(nodes, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				long lo1 = stringToLong(o1.getStatus()
						.getAllocatable().get("pods").getAmount());
				long lo2 = stringToLong(o2.getStatus()
						.getAllocatable().get("pods").getAmount());
				return (lo1 - lo2 < 0) ? -1 : 1;
			}
			
		});
	}

	/**
	 * @param nodes      the node with maximum instances
	 */ 
	protected void sortByMaxInstancePerHost(Node[] nodes) {
		Arrays.sort(nodes, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				long lo1 = stringToLong(o1.getStatus()
						.getAllocatable().get("pods").getAmount());
				long lo2 = stringToLong(o2.getStatus()
						.getAllocatable().get("pods").getAmount());
				return (lo1 - lo2 > 0) ? -1 : 1;
			}
			
		});
	}

	/**
	 * @param nodes             the node with minimum used RAM
	 */ 
	protected void sortByMinimumMemoryUsage(Node[] nodes) {
		Arrays.sort(nodes, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				long lo1 = stringToLong(o1.getStatus()
						.getAllocatable().get("memory").getAmount());
				long lo2 = stringToLong(o2.getStatus()
						.getAllocatable().get("memory").getAmount());
				return (lo2 - lo1 < 0) ? -1 : 1;
			}
			
		});
	}

	/**
	 * @param nodes             the node with minimum used CPU
	 */
	protected void sortByMinimumCPUUsage(Node[] nodes) {
		Arrays.sort(nodes, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				long lo1 = stringToLong(o1.getStatus()
						.getAllocatable().get("cpu").getAmount());
				long lo2 = stringToLong(o2.getStatus()
						.getAllocatable().get("cpu").getAmount());
				return (lo2 - lo1 < 0) ? -1 : 1;
			}
			
		});
	}

	/**
	 * @return      get all nodes from Kubernetes
	 */
	protected Node[] getNodeCandidates() {
		try {
			return client.nodes().list()
					.getItems().toArray(new Node[] {});
		} catch (Exception ex) {
			// if client is null or we encounter unknown problem 
			m_logger.log(Level.SEVERE, ex.getMessage());
			return new Node[] {};
		}
	}

	/**
	 * @param node         node
	 * @return             if this node is disable scheduling
	 */
	protected boolean unSched(Node node) {
		for (Taint taint : node.getSpec().getTaints()) {
			if (taint.getEffect().equals("NoSchedule")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param node          node
	 * @return              if this node encounters some errors 
	 */
	protected boolean notReady(Node node) {
		for (NodeCondition nc : node.getStatus().getConditions()) {
			if (nc.getType().equals("Ready")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param node           node
	 * @return               if this node is master
	 */
	protected boolean isMaster(Node node) {
		return node.getMetadata().getLabels()
				.containsKey("node-role.kubernetes.io/master");
	}

	/**
	 * @param value           node available capacity, such as CPU or RAM
	 * @return                real value    
	 */
	protected long stringToLong(String value) {
		long weight = 1;
		if (value.endsWith("Ki")) {
			value = value.substring(0, value.length() - 2);
			weight = 1;
		} else if (value.endsWith("Mi")) {
			value = value.substring(0, value.length() - 2);
			weight = 1024;
		} else if (value.endsWith("Gi")) {
			value = value.substring(0, value.length() - 2);
			weight = 1024*1024;
		} else if (value.endsWith("Ti")) {
			value = value.substring(0, value.length() - 2);
			weight = 1024*1024*1024;
		} 
		
		return Long.parseLong(value) * weight;
	}
	
	/**
	 * @author wuheng@otcaix.iscas.ac.cn
	 *
	 */
	public static enum Policy {
		/**
		 * tthe node with minimum used RAM 
		 */
		minimumMemoryUsageHostAllocatorStrategyMode,
		/**
		 * the node with minimum used CPU 
		 */
		minimumCPUUsageHostAllocatorStrategyMode,
		/**
		 * the node with maximum instances
		 */
		maxInstancePerHost,
		/**
		 * the node with minimum instances
		 */
		minInstancePerHost
	}
}

