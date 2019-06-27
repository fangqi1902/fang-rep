package com.abc.service;

import com.abc.pojo.Item;
import com.abc.pojo.ItemDesc;
import com.abc.vo.EasyUIData;

public interface ItemService {
	EasyUIData findItemByPage(Integer page,Integer rows);

	void saveItem(Item item, ItemDesc itemDesc);

	void updateItem(Item item, ItemDesc itemDesc);

	void deleteItem(Long[] ids);

    void updateStatus(Long[] ids, int status);

	ItemDesc findItemDescById(Long itemId);
	

}
