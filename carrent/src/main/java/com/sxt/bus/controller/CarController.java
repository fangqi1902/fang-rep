package com.sxt.bus.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sxt.bus.constast.BUS_Constast;
import com.sxt.bus.domain.Car;
import com.sxt.bus.service.CarService;
import com.sxt.bus.utils.RandomUtils;
import com.sxt.bus.vo.CarVo;
import com.sxt.sys.utils.DataGridView;

@Controller
@RequestMapping("car")
public class CarController {

	@Autowired
	private CarService carService;

	// 跳转转到汽车列表界面
	@RequestMapping("toCarManager")
	public String toCarManager() {
		return "business/carManager";
	}

	// 获取所有的汽车并以json类型返回
	@RequestMapping("queryAllCars")
	@ResponseBody
	public DataGridView queryAllCars(CarVo carVo) {
		return carService.queryAllCars(carVo);
	}

	// 添加
	@RequestMapping("addCar")
	@ResponseBody
	public Map<String, Object> addCar(CarVo carVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = carService.addCar(carVo);
			if (i > 0) {
				map.put("msg", "添加成功");
			} else {
				map.put("msg", "添加失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "添加失败");
		}
		return map;
	}

	// 修改
	@RequestMapping("updateCar")
	@ResponseBody
	public Map<String, Object> updateCar(CarVo carVo, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String path = carVo.getCarimg();
			Car car = carService.queryCarByCarNumber(carVo.getCarnumber());
			// 判断有没有修改照片，如不相等说明修改了,要删除原来的照片上传新的照片
			if (StringUtils.isNotBlank(car.getCarimg())
					&& !path.equals(car.getCarimg())) {
				// 得到文件路径
				String realPath = session.getServletContext().getRealPath("/");
				// 获取文件所在文件夹和文件名
				String p = realPath
						+ (car.getCarimg().substring(2, car.getCarimg()
								.length()));
				File file = new File(p);
				if (file.exists()) {
					file.delete();
				}
			}
			int i = carService.updateCar(carVo);
			if (i > 0) {
				map.put("msg", "修改成功");
			} else {
				map.put("msg", "修改失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "修改失败");
		}
		return map;
	}

	// 删除
	@RequestMapping("deleteCar")
	@ResponseBody
	public Map<String, Object> deleteCar(CarVo carVo, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Car car = carService.queryCarByCarNumber(carVo.getCarnumber());
			// if(null!=car.getCarimg()&&!car.getCarimg().equals("")&&!car.getCarimg().equals(BUS_Constast.IMG_DEFAULT)){
			// StringUtils.isNotBlank(car.getCarimg())判断car.getCarimg()是否为null或者""commons-lang-2.4.jar
			if (StringUtils.isNotBlank(car.getCarimg())
					&& !car.getCarimg().equals(BUS_Constast.IMG_DEFAULT)) {
				// 得到文件路径
				String realPath = session.getServletContext().getRealPath("/");
				// 获取文件所在文件夹和文件名
				String p = realPath
						+ (car.getCarimg().substring(2, car.getCarimg()
								.length()));
				File file = new File(p);
				if (file.exists()) {
					file.delete();
				}
			}
			int i = carService.deleteCar(carVo.getCarnumber());
			if (i > 0) {
				map.put("msg", "删除成功");
			} else {
				map.put("msg", "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "删除失败");
		}
		return map;
	}

	// 文件上传
	@RequestMapping("uploadCarImg")
	@ResponseBody
	public Map<String, Object> uploadCarImg(MultipartFile mf,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 1,得到文件上传的保存路径
		String path = session.getServletContext().getRealPath("/upload/");
		// 2,得到文件的老名字
		String oldName = mf.getOriginalFilename();
		// 3,得到文件夹的名字
		String dirName = RandomUtils.getDirNameUseTime();
		// 4,判断这个文件夹是否存在
		File dir = new File(path, dirName);
		if (!dir.exists()) {
			dir.mkdirs();// 创建文件夹
		}
		// 5,根据老名字生成新名字
		String newName = RandomUtils.createFileNewNameUseTime(oldName);
		// 6,组装文件 参数1 父文件夹的路径 参数2 文件保存的名字
		File file = new File(dir, newName);
		// 7,保存文件到file
		try {
			mf.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String rePath = "../upload/" + dirName + "/" + newName;
		map.put("path", rePath);
		return map;
	}

}
