package com.abc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@TableName("tb_item_desc")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDesc extends BasePojo{
	@TableId()
	private Long itemId;
	private String itemDesc;
	

}
