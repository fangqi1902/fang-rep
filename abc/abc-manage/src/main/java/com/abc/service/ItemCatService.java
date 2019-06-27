package com.abc.service;

import java.util.List;

import com.abc.pojo.ItemCat;
import com.abc.vo.EasyUITree;

public interface ItemCatService {
	List<EasyUITree> findItemCatByParentId(Long parentId);

	String findItemCatNameById(Long itemCatId);

}
