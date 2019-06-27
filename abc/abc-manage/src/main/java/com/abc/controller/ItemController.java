package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.pojo.Item;
import com.abc.pojo.ItemCat;
import com.abc.pojo.ItemDesc;
import com.abc.service.ItemService;
import com.abc.vo.EasyUIData;
import com.abc.vo.SysResult;

@RestController
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/query")
	public EasyUIData findItemByPage(Integer page,Integer rows) {
		return itemService.findItemByPage(page,rows);
	}
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		try {
			//实现数据新增
			itemService.saveItem(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
		
	}
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {
		try {
			itemService.updateItem(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	@RequestMapping("/delete")
	public SysResult deleteItem(Long[] ids) {
		try {
		  itemService.deleteItem(ids);
		  return SysResult.ok();
		} catch (Exception e) {
		  e.printStackTrace();
		  return SysResult.fail();
		  
		}
		
	}
	@RequestMapping("/instock")
	public SysResult instock(Long[] ids) {
		try {
		  int status = 2;
		  itemService.updateStatus(ids,status);
		  return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			 return SysResult.fail();
		}
	}
	@RequestMapping("/reshelf")
	public SysResult reshelf(Long[] ids) {
		try {
		  int status = 1;
		  itemService.updateStatus(ids,status);
		  return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			 return SysResult.fail();
		}
	}
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById(@PathVariable Long itemId) {
		try {
			ItemDesc itemDesc = 
					itemService.findItemDescById(itemId);
			return SysResult.ok(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	
}
