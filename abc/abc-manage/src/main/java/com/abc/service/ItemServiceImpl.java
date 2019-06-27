package com.abc.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc.mapper.ItemDescMapper;
import com.abc.mapper.ItemMapper;
import com.abc.pojo.Item;
import com.abc.vo.EasyUIData;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.abc.pojo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIData findItemByPage(Integer page,Integer rows) {
		Integer total = itemMapper.selectCount(null);
		int startIndex = (page-1)*rows;
		List<Item> listitem = itemMapper.findItemByPage(startIndex, rows);
		return new EasyUIData(total,listitem);
	}
	@Transactional
	@Override
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc
		.setItemId(item.getId())
		.setCreated(item.getCreated())
		.setUpdated(item.getUpdated());
		itemDescMapper.insert(itemDesc);
		
		
	}
    @Transactional
	@Override
	public void updateItem(Item item, ItemDesc itemDesc) {
    	item.setUpdated(new Date());
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId())
		.setCreated(item.getCreated())
		.setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
		
		}
    @Transactional
	@Override
	public void deleteItem(Long[] ids) {
		List<Long> idList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(idList);
		itemDescMapper.deleteBatchIds(idList);
		
	}
	@Override
	public void updateStatus(Long[] ids, int status) {
		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		List<Long> longids = Arrays.asList(ids);
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		updateWrapper.in("id", longids);
		itemMapper.update(item, updateWrapper);

	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		return itemDescMapper.selectById(itemId);
	}

}
