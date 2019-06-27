package com.abc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.abc.pojo.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface ItemMapper extends BaseMapper<Item>{
	@Select("select * from tb_item order by updated desc limit #{startIndex},#{rows}")
	List<Item> findItemByPage(@Param("startIndex")int startIndex,@Param("rows")Integer rows);

}
