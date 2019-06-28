package com.sxt.sys.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

	/***
	 * 使用双层循环处理成界面菜单需要的json格式
	 * 
	 * @param treeNodes
	 * @param rootId
	 *            根节点的id
	 * @return
	 */
	public static List<TreeNode> builder(List<TreeNode> treeNodes,
			Integer rootId) {
		List<TreeNode> nodes = new ArrayList<>();
		for (TreeNode treeNode1 : treeNodes) {
			if (treeNode1.getPid() == rootId) {// 如果相等，说时该根节点(汽车出租系统)
				nodes.add(treeNode1);
			}
			for (TreeNode treeNode2 : treeNodes) {
				if (treeNode2.getPid() == treeNode1.getId()) {// 相等，说明treeNode2是treeNode1的子节点
					treeNode1.getChildren().add(treeNode2);
				}
			}
		}
		return nodes;
	}
}
