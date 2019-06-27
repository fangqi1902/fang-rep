package com.abc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.mapper.ItemCatMapper;
import com.abc.pojo.ItemCat;
import com.abc.vo.EasyUITree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITree> findItemCatByParentId(Long parentId) {
		List<ItemCat> cartlist = findItemCatList(parentId);
		List<EasyUITree> treeList = new ArrayList<EasyUITree>();
		for(ItemCat itemCat:cartlist) {
			EasyUITree uiTree = new EasyUITree();
			uiTree.setId(itemCat.getId());
			uiTree.setText(itemCat.getName());
			String state = itemCat.getIsParent()?"closed":"open";
			uiTree.setState(state);
			treeList.add(uiTree);
			
		}
		return treeList;
	}

	private List<ItemCat> findItemCatList(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
		
	}

	@Override
	public String findItemCatNameById(Long itemCatId) {
		return itemCatMapper.selectById(itemCatId).getName();

	}

}
